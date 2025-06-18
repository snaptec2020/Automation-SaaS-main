package utility

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

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
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.utils.CustomLogger

import internal.GlobalVariable
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory

import org.openqa.selenium.ElementClickInterceptedException
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
public class Utility {
	TestObject tb=new TestObject()
	@Keyword
	def checkIfElementExist(def objPath) {


		def elementSize = WebUI.findWebElements(findTestObject(objPath),10).size()


		return elementSize
	}
	@Keyword
	TestObject addXpathToTestObject(def xpath) {
		tb.addProperty('xpath', ConditionType.EQUALS, xpath)
		return tb
	}
	@Keyword
	def switchToIframeByXpath(def xpath) {

		WebUI.switchToFrame(addXpathToTestObject(xpath), 0)
	}
	@Keyword
	List findWebElements(def testObjectPathId, int timedOut) {
		return WebUI.findWebElements(findTestObject(testObjectPathId),timedOut)
	}
	@Keyword
	def clickOnObjectusingJavaScript(TestObject testObject) {
		try {
		WebUI.waitForElementClickable(testObject, 0, FailureHandling.OPTIONAL)
		WebElement element = WebUiCommonHelper.findWebElement(testObject,10)
				
		if (element != null) {
			CustomLogger.logInfo("Element found, proceeding with JavaScript click")
			// First scroll to element
			WebUI.executeJavaScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", Arrays.asList(element))
			
			// Wait a moment for scroll to complete
			Thread.sleep(500)
			// Direct click without setTimeout
			WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(element))
			CustomLogger.logInfo("JavaScript click executed successfully")
		} else {
            CustomLogger.logInfo("Element not found for JavaScript click")
            throw new Exception("Element not found")
		}
		} catch (Exception e) {
        CustomLogger.logInfo("Error in JavaScript click: ${e.getMessage()}")
        throw e
    }
	}
	@Keyword
	def clickOnObjectusingJavaScriptEnhanced(TestObject testObject) {
		WebUI.waitForElementClickable(testObject, 0, FailureHandling.OPTIONAL)
		WebElement element = WebUiCommonHelper.findWebElement(testObject, 10)
		
		if (element != null) {
			// Scroll into view and add visual feedback
			WebUI.executeJavaScript("""
            // Scroll to element
            arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});
            
            // Add highlight effect
            var originalBg = arguments[0].style.backgroundColor;
            arguments[0].style.backgroundColor = 'yellow';
            arguments[0].style.transition = 'background-color 0.3s';
            
            // Click after short delay
            setTimeout(function() {
                arguments[0].style.backgroundColor = originalBg;
                arguments[0].click();
            }, 300);
        """, Arrays.asList(element))
			
			CustomLogger.logInfo("Clicked element with enhanced JavaScript method")
		} else {
			CustomLogger.logInfo("Element not found for enhanced JavaScript click")
		}
	}
	@Keyword
	def setDateUsingJavaScript() {
		// Set date format to yyyy-MM-dd for date input
		LocalDate date = LocalDate.now().plusDays(1)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
		def dateString = date.format(formatter)

		// JavaScript to set the date value directly in the date field by ID
		def js = "document.getElementById('date').value = '${dateString}';"
		WebUI.executeJavaScript(js, null)
	}

	@Keyword
	def clickOnObjectusingJavaScript(WebElement element) {
		//WebElement element = WebUiCommonHelper.findWebElement(testObject,30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(element))
	}
	@Keyword
	def moveToElement() {
		WebElement logoElm =WebUiCommonHelper.findWebElement(findTestObject('Headers and Footers/Header contents/Logo'),5)

		Actions actions = new Actions(DriverFactory.getWebDriver())
		actions.moveToElement(logoElm).perform()
	}
	// Method to click using JavaScript
	@Keyword
	def clickUsingJavaScript(WebElement element) {
		try {
			//WebDriver driver = DriverFactory.getWebDriver()
			//JavascriptExecutor executor = (JavascriptExecutor) driver
			CustomLogger.logInfo("Clicking using JavaScript")
			WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(element))
		} catch (Exception e) {
			WebUI.takeScreenshot()
			CustomLogger.logError("JavaScript cannot click on object")
			KeywordUtil.markFailedAndStop("Failed to click element: " + e.getMessage())
		}
	}

	// Alternative method with explicit wait
	@Keyword
	def clickElementSafely(TestObject testObject) {
		try {
			// Wait for element to be clickable
			CustomLogger.logInfo("waiting to click on button")
			WebUI.waitForElementClickable(testObject, 10)

			// Try regular click first
			CustomLogger.logInfo("clicking on button")
			WebUI.click(testObject)
		} catch (Exception e) {
			// If regular click fails, try JavaScript click
			CustomLogger.logInfo("The button is not clickable and we are trying to click using JavaScript")
			WebElement element = WebUiBuiltInKeywords.findWebElement(testObject,5)
			clickUsingJavaScript(element)
		}
	}
}


