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
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

if(WebUI.verifyElementVisible(findTestObject('login page/email page/Check context Success login'), FailureHandling.CONTINUE_ON_FAILURE)) {


WebUI.verifyEqual(WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Sign up Page/Verifications/firstName'),30).getAttribute("value"), firstName, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyEqual(WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Sign up Page/Verifications/lastName'),30).getAttribute("value"), lastName, FailureHandling.CONTINUE_ON_FAILURE)
if(isSignupByPhone==1) {
	WebUI.verifyEqual(WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Sign up Page/Verifications/phoneAccount'),30).getAttribute("placeholder").replaceAll('-', ''), phoneNumber, FailureHandling.CONTINUE_ON_FAILURE)
	GlobalVariable.phoneNumber = phoneNumber
} else {
	WebUI.verifyEqual(WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Sign up Page/Verifications/emailAccount'),30).getAttribute("value"), emailAccount, FailureHandling.CONTINUE_ON_FAILURE)
	GlobalVariable.Vaild_email = emailAccount
	GlobalVariable.ValidPassword= emailPassword
}
}