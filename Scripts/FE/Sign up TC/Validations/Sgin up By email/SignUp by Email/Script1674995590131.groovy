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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('FE/Sign up TC/General Actions Sign up/Navigate to Sign up by Email page'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.setText(findTestObject('Sign up Page/Sign up By email/First Name field'), firstName)

WebUI.setText(findTestObject('Sign up Page/Sign up By email/Last Name field'), lastName)

WebUI.setText(findTestObject('Sign up Page/Sign up By email/Email field'), email)

WebUI.setText(findTestObject('Sign up Page/Sign up By email/Password feild'), password)

WebUI.click(findTestObject('Sign up Page/Sign up By email/Services and Privacy Policy Check box'))

WebUI.click(findTestObject('Sign up Page/Sgin up By phone/Submit Button Sign up mobile page'))
	//findTestObject('Sign up Page/Sign up By email/Sign Up Button'))

GlobalVariable.shouldRefresh = CustomKeywords.'generalactions.notificationsObject.waitNotificationVisble'('الرجاء الانتظار للحظة والمحاولة مرة أخرى', 'Please wait for a second and try again')


