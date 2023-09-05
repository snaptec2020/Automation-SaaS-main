package signup

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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class Signupemailverifications {
	TestObject signUpObj = new TestObject();

	@Keyword
	def verificationMessage(def expectedMessageAr, def expectedMessageEn) {
		//p[@class='error-message' and (normalize-space(text()) ='الرجاء ادخال كلمة مرور من 8 خانات على الأقل' or normalize-space(text()) ='')]
		signUpObj.addProperty('xpath', ConditionType.EQUALS, "//p[@class='error-message' and (normalize-space(text()) ='"+expectedMessageAr+"' or normalize-space(text()) ='"+expectedMessageEn+"')]")
		//WebUI.verifyElementVisible(findTestObject('Sign up Page/Sign up By email/Error message by email'))
		WebUI.verifyElementVisible(signUpObj)
	}
	@Keyword
	def verificationElementSignUp() {
		signUpObj.addProperty('xpath', ConditionType.EQUALS, "//button[contains(text(),'اشتراك')or normalize-space()='Sign Up']")
		WebUI.verifyElementNotClickable(findTestObject('Sign up Page/Sgin up By phone/Submit Button Sign up mobile page'), FailureHandling.CONTINUE_ON_FAILURE)
	}
}
