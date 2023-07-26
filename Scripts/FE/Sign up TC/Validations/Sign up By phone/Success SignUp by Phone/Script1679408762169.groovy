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
import com.kms.katalon.entity.global.GlobalVariableEntity as GlobalVariableEntity
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('FE/Sign up TC/General Actions Sign up/Navigate to Sign up page'), [:], FailureHandling.STOP_ON_FAILURE)

def phoneNumber = CustomKeywords.'generalactions.generalStrings.generateRandomPhoneNumber'()
def firstName = 'Automationtest'
//GlobalVariable.phoneNumber = phoneNumber
boolean isPhoneExist = true
int traials = 0
while(isPhoneExist && traials<=10) {
WebUI.callTestCase(findTestCase('FE/Sign up TC/Validations/Sign up By phone/SignUp by phone'), [('firstname') : firstName, ('lastname') : phoneNumber
        , ('PhoneNumber') : phoneNumber, ('isCheck') : '1'], FailureHandling.STOP_ON_FAILURE)
if(GlobalVariable.shouldRefresh) {
	WebUI.delay(2)
	WebUI.navigateToUrl(GlobalVariable.URL)
	WebUI.callTestCase(findTestCase('FE/Sign up TC/Validations/Sign up By phone/Success SignUp by Phone'), [:], FailureHandling.STOP_ON_FAILURE)
}
isPhoneExist = WebUI.waitForElementVisible(findTestObject('OTP/Phone exist error'), 3, FailureHandling.CONTINUE_ON_FAILURE)
traials++

if(!isPhoneExist) {
	WebUI.callTestCase(findTestCase('FE/OTP/General Actions/Insert fixed OTP'), [:], FailureHandling.STOP_ON_FAILURE)
	
	//WebUI.verifyElementVisible(findTestObject('login page/email page/Check context Success login'))
	WebUI.callTestCase(findTestCase('FE/Sign up TC/Verifications/Verification After Signup'), [('firstName') : firstName, ('lastName') : phoneNumber
		, ('phoneNumber') : phoneNumber, ('emailAccount') : '', ('isSignupByPhone') : 1, ('emailPassword') : ''], FailureHandling.STOP_ON_FAILURE)
} else {
	phoneNumber = CustomKeywords.'generalactions.generalStrings.generateRandomPhoneNumber'()
	}
}


