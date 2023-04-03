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
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('FE/Check out/verification/Verification Check out components'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Check Out/Proceed To Checkout Button'))
//boolean otpRequierd=WebUI.verifyElementVisible(findTestObject('OTP/OTP container'))
int otpRequierd=WebUI.findWebElements(findTestObject('Sign up Page/Sgin up By phone/insert phone number'),10).size()
if(otpRequierd!= 0) {
	WebUI.setText(findTestObject('Sign up Page/Sgin up By phone/insert phone number'), CustomKeywords.'generalactions.generalStrings.generateRandomPhoneNumber'())
	WebUI.click(findTestObject('Check Out/Update added phone number'))
	WebUI.callTestCase(findTestCase('FE/OTP/General Actions/Insert fixed OTP'), [:], FailureHandling.STOP_ON_FAILURE)
}

WebUI.verifyElementVisible(findTestObject('Check Out/checkout user details 1'))

WebUI.verifyElementVisible(findTestObject('Check Out/Checkout address 2'))

WebUI.verifyElementVisible(findTestObject('Check Out/Checkout payment method'))

