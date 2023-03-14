package signIn

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class signInVerifications {

	TestObject signInObj = new TestObject();

	/*	@Keyword
	 def verificationValidationsMessageFailLoging(def expectedMessageAr, def expectedMessageEn) {
	 signInObj.addProperty('xpath', ConditionType.EQUALS, "//div[contains(@class,'styles_errorMessage')]//*[contains(text(),'"+expectedMessageAr+"') or contains(text(),'"+expectedMessageEn+"')]")
	 }*/
	@Keyword
	def verificationElementSignIn() {
		signInObj.addProperty('xpath', ConditionType.EQUALS, "//button[contains(text(),'تسجيل الدخول')or normalize-space()='Sign In']")
		WebUI.verifyElementNotClickable(signInObj, FailureHandling.CONTINUE_ON_FAILURE)
	}

	@Keyword
	def verifyActualMessageWithExpectedSignIn(def expectedMessage) {
		signInObj.addProperty('xpath', ConditionType.EQUALS, "//div[contains(@class,'styles_errorMessage')]")
		def actualMessage = WebUI.getText(signInObj)
		if (actualMessage==expectedMessage) {
			KeywordUtil.markPassed("expected message matched with the Actual")
		}else {
			KeywordUtil.markFailed("expected message not matched with the Actual")
		}
	}
}
