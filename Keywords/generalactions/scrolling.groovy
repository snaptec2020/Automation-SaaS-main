package generalactions

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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class scrolling {
	/*int scrollingPosition =4
	 public int getScrollingPosition() {
	 return scrollingPosition;
	 }
	 public void setScrollingPosition(int scrollingPosition) {
	 this.scrollingPosition = scrollingPosition;
	 }*/
	@Keyword
	def scrollingAtTheBottom() {

		try {
			long lastHeight=((Number) WebUI.executeJavaScript("return document.body.scrollHeight", null)).longValue();
			int  scrollingCount=0

			while (true) {
				WebUI.executeJavaScript("window.scrollTo(0, document.body.scrollHeight);", null);
				Thread.sleep(2000);
				long newHeight = ((Number)WebUI.executeJavaScript("return document.body.scrollHeight", null)).longValue();
				KeywordUtil.logInfo(lastHeight.toString())
				KeywordUtil.logInfo(newHeight.toString())
				if (newHeight == lastHeight || scrollingCount==10) {
					break;
				}
				lastHeight = newHeight;
				scrollingCount++;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
				WebUI.executeJavaScript("window.scrollTo(0, document.body.scrollHeight/"+i.toString()+");", null);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Keyword
	def scrollToClick(def testObjectRelativeId) {
		boolean elementClicable =WebUI.waitForElementClickable(findTestObject(testObjectRelativeId),10,FailureHandling.CONTINUE_ON_FAILURE)
		boolean elementVisable = WebUI.waitForElementVisible(findTestObject(testObjectRelativeId),10,FailureHandling.CONTINUE_ON_FAILURE)
		KeywordUtil.logInfo(elementClicable.toString())
		KeywordUtil.logInfo(elementVisable.toString())
		//for(int scrollingPosition = 4;scrollingPosition!=0;scrollingPosition--) {
		//KeywordUtil.logInfo(elementVisable.toString())
		if (elementClicable && elementVisable) {
			//KeywordUtil.logInfo(elementClicable.toString())
			//WebUI.executeJavaScript("window.scrollTo(0, document.body.scrollHeight/"+scrollingPosition.toString()+");", null);
			//KeywordUtil.logInfo(elementClicable.toString())
			javaScriptToScrollToElement(testObjectRelativeId)
			WebUI.click(findTestObject(testObjectRelativeId),FailureHandling.CONTINUE_ON_FAILURE)
			//KeywordUtil.logInfo(elementClicable.toString())
			//break
		}else {
			javaScriptToScrollToElement(testObjectRelativeId)
			WebUI.click(findTestObject(testObjectRelativeId),FailureHandling.CONTINUE_ON_FAILURE)

		}

		//}
	}
	@Keyword
	def javaScriptToScrollToElement(def testObjectRelativeId) {
		WebElement element = WebUiCommonHelper.findWebElement(findTestObject(testObjectRelativeId),30)
		WebUI.executeJavaScript("arguments[0].scrollIntoView()", Arrays.asList(element))
		WebUI.delay(2)
	}
}
