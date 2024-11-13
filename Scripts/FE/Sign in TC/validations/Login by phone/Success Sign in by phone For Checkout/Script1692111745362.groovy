import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable

import org.apache.commons.lang3.StringUtils
import org.openqa.selenium.Keys as Keys

//WebUI.callTestCase(findTestCase('FE/Sign in TC/validations/General Actions/Navigate to Sgin in'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.setText(findTestObject('Sign up Page/Sgin up By phone/insert phone number'), GlobalVariable.phoneNumber)

WebUI.click(findTestObject('login page/phone page/Submit Button phone number'))
//GlobalVariable.shouldRefresh = CustomKeywords.'generalactions.notificationsObject.waitNotificationVisble'('الرجاء الانتظار للحظة والمحاولة مرة أخرى', 'Please wait for a second and try again')
CustomKeywords.'generalactions.notificationsObject.shouldRefresh'()
CustomKeywords.'generalactions.notificationsObject.refreshSignByPhone'('Test Cases/FE/Sign in TC/validations/Login by phone/Success Sign in by phone For Checkout')
//if(GlobalVariable.shouldRefresh) {
//	WebUI.delay(2)
//	WebUI.refresh(FailureHandling.CONTINUE_ON_FAILURE)
//	WebUI.callTestCase(findTestCase('Test Cases/FE/Sign in TC/validations/Login by phone/Success Sign in by phone For Checkout'), [:], FailureHandling.STOP_ON_FAILURE)
//}
//WebUI.verifyMatch((WebUI.getUrl(FailureHandling.CONTINUE_ON_FAILURE)=~"checkout(.*)")[0][1].toString(), "/registration", true, FailureHandling.STOP_ON_FAILURE)
if(StringUtils.indexOfIgnoreCase(WebUI.getUrl(), "/checkout/signup") >0) {
	WebUI.callTestCase(findTestCase('Test Cases/FE/Sign up TC/Validations/Sign up By phone/Success SignUp by Phone For Checkout'), [:], FailureHandling.STOP_ON_FAILURE)
} else {
WebUI.callTestCase(findTestCase('FE/OTP/General Actions/Insert fixed OTP'), [:], FailureHandling.STOP_ON_FAILURE)
}
//WebUI.verifyElementVisible(findTestObject('login page/email page/Check context Success login'))

