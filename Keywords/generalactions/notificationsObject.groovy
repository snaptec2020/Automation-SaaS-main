package generalactions

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class notificationsObject {

	TestObject tb = new TestObject()

	TestObject getNotificationObject(def arText, def enText) {
		tb.addProperty('xpath', ConditionType.EQUALS, "//div[starts-with(@class,'react-toast-notifications__')]//*[starts-with(@class,'react-toast-notifications__toast__content')][normalize-space()='"+arText+"' or normalize-space() ='"+enText+"']")
		return tb
	}

	@Keyword
	def verifyNotificationVisble(def arText, def enText) {
		if(GlobalVariable.languageMode.toString().equalsIgnoreCase('Ar')) {
			WebUI.verifyElementVisible(getNotificationObject(arText,''), FailureHandling.CONTINUE_ON_FAILURE)
		}else {
			WebUI.verifyElementVisible(getNotificationObject('',enText), FailureHandling.CONTINUE_ON_FAILURE)
		}
	}
	@Keyword
	def verifyNotificationNotVisble(def arText, def enText) {
		WebUI.verifyElementNotVisible(getNotificationObject(arText,enText), FailureHandling.CONTINUE_ON_FAILURE)
	}
	@Keyword
	boolean waitNotificationVisble(def arText, def enText) {
		return WebUI.waitForElementVisible(getNotificationObject(arText,enText), 10)
	}
	@Keyword
	def getMessageText() {
		tb.addProperty('xpath', ConditionType.EQUALS, "//div[starts-with(@class,'react-toast-notifications__')]//*[starts-with(@class,'react-toast-notifications__toast__content')]")
		waitForSpecificChildren(findTestObject('Toast - Parent'), 3, 10)
		WebUI.waitForElementVisible(tb, 10)
		return WebUI.getText(tb, FailureHandling.CONTINUE_ON_FAILURE)
	}

	@Keyword
	String getTextAfterVisible(TestObject testObject, int timeoutSeconds) {
		// Custom keyword or test case script
		String script = '''
        function waitForElementVisible(selector, timeout) {
            return new Promise((resolve, reject) => {
                const startTime = Date.now();
                
                const checkElement = () => {
                    const element = document.querySelector(selector);
                    
                    if (element && element.offsetParent !== null && element.innerText.trim() !== '') {
                        resolve(element.innerText);
                        return;
                    }
                    
                    if (Date.now() - startTime >= timeout) {
                        reject(new Error('Timeout waiting for element to be visible'));
                        return;
                    }
                    
                    setTimeout(checkElement, 100);
                };
                
                checkElement();
            });
        }
        
        return waitForElementVisible(arguments[0], arguments[1]);
    '''

		// Convert TestObject to CSS selector
		WebElement element = WebUiCommonHelper.findWebElement(testObject, 5)
		String cssSelector = WebUI.executeJavaScript("return arguments[0].tagName.toLowerCase() + (arguments[0].id ? '#' + arguments[0].id : '') + (arguments[0].className ? '.' + arguments[0].className.replace(/\\s+/g, '.') : '');", Arrays.asList(element))

		// Execute the script and wait for result
		String text = WebUI.executeJavaScript(script, Arrays.asList(cssSelector, timeoutSeconds * 1000))
		return text
	}
	@Keyword
	def waitForSpecificChildren(TestObject parentObject, int expectedCount, int timeoutSeconds) {
		long endTime = System.currentTimeMillis() + (timeoutSeconds * 1000)

		while (System.currentTimeMillis() < endTime) {
			try {
				WebElement parent = WebUiCommonHelper.findWebElement(parentObject, 5)
				List<WebElement> children = parent.findElements(By.xpath(".//*"))

				if (children.size() >= expectedCount) {
					KeywordUtil.logInfo("Found ${children.size()} children")
					return true
				}

				Thread.sleep(500) // Wait before next check
			} catch (Exception e) {
				// Continue waiting if element not found
			}
		}

		KeywordUtil.markWarning("Timeout waiting for children elements")
		return false
	}

	@Keyword
	def shouldRefresh() {
		if(WebUI.waitForElementVisible(findTestObject('Sign up Page/Sgin up By phone/Retry Captcha'), 3)) {
			def errorMessage = WebUI.getText(findTestObject('Sign up Page/Sgin up By phone/Retry Captcha'))
			KeywordUtil.logInfo(errorMessage)
			if(errorMessage.equalsIgnoreCase('الرجاء المحاولة بعد قليل') || errorMessage.equalsIgnoreCase('Please try again.')) {
				GlobalVariable.shouldRefresh = true
			}
		}
	}

	@Keyword
	def refreshSignByPhone(def testCasePath) {
		if(GlobalVariable.shouldRefresh) {
			WebUI.delay(2)
			WebUI.refresh(FailureHandling.CONTINUE_ON_FAILURE)
			GlobalVariable.shouldRefresh = false
			WebUI.callTestCase(findTestCase(testCasePath), [:], FailureHandling.STOP_ON_FAILURE)
		}
	}
}
