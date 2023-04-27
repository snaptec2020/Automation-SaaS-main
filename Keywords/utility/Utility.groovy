package utility

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

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
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

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
	List findWebElements(def testObject, int timedOut) {
		return WebUI.findWebElements(findTestObject(testObject),timedOut)
	}
	@Keyword
	def clickOnObjectusingJavaScript(TestObject testObject) {
		WebElement element = WebUiCommonHelper.findWebElement(testObject,30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(element))
	}
	@Keyword
	def scrollToVerifyElementVisiblity(def testObjectRelativeId) {
		try {

	for(int i=4;i<=0;i--) {
		
		if (WebUI.verifyElementVisible(findTestObject(testObjectRelativeId),FailureHandling.CONTINUE_ON_FAILURE)) {
			//WebUI.executeJavaScript("window.scrollTo(0, document.body.scrollHeight/2);", null);
			
			break;
		}
		KeywordUtil.markPassed("try to scroll again")
		//WebUI.scrollToElement(findTestObject('Headers and Footers/Footer contents/Web footer'), 30, FailureHandling.CONTINUE_ON_FAILURE) 
		WebUI.executeJavaScript("window.scrollTo(0, document.body.scrollHeight/"+i.toString()+");", null);
		//WebUI.scrollToElement(findTestObject('Headers and Footers/Footer contents/Web footer'), 30, FailureHandling.CONTINUE_ON_FAILURE) 
		Thread.sleep(1000);
		
		//KeywordUtil.logInfo(lastHeight.toString())
		//KeywordUtil.logInfo(newHeight.toString())
		
		
	}
} catch (InterruptedException e) {
	e.printStackTrace();
	}
		
	}
}


