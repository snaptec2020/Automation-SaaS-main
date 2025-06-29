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

WebUI.callTestCase(findTestCase('FE/Sign in TC/validations/General Actions/Navigate to Sgin in'), [:], FailureHandling.STOP_ON_FAILURE)

if (!(WebUI.waitForElementVisible(findTestObject('login page/email page/Check context Success login'), 5, FailureHandling.CONTINUE_ON_FAILURE))) {
    WebUI.callTestCase(findTestCase('FE/Sign in TC/validations/General Actions/Navigate sign in By email'), [:], FailureHandling.STOP_ON_FAILURE)

    WebUI.waitForElementClickable(findTestObject('login page/email page/Forget password context'), 2)

    WebUI.enhancedClick(findTestObject('login page/email page/Forget password context'))

    WebUI.waitForElementVisible(findTestObject('login page/email page/Forget password Email Field'), 0)

    WebUI.setText(findTestObject('login page/email page/Forget password Email Field'), GlobalVariable.Vaild_email)

    WebUI.waitForElementClickable(findTestObject('login page/email page/Forget password button'), 2)

    WebUI.enhancedClick(findTestObject('login page/email page/Forget password button'))
	
	CustomKeywords.'generalactions.generalActions.waiteSpinnerToHide'()
	
	def title = WebUI.getText(findTestObject('login page/email page/forget password title'))
	
	def description = WebUI.getText(findTestObject('login page/email page/Forget password description'))
	
	if(GlobalVariable.languageMode == 'ar') {
		WebUI.verifyEqual(title, "استرداد كلمة المرور")
		WebUI.verifyEqual(description, /إذا كان هناك حساب مرتبط بـ ${GlobalVariable.Vaild_email.toString().trim()} , فستتلقى بريدًا إلكترونيًا به رابط لتغيير كلمة المرور الخاصة بك./)
	}else {
		WebUI.verifyEqual(title, "Recover Password")
		WebUI.verifyEqual(description, "If there is an account associated with ${GlobalVariable.Vaild_email.toString().trim()} you will receive an email with a link to change your password.")
	}
}



