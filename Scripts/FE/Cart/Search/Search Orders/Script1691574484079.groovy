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

WebUI.openBrowser('')

WebUI.navigateToUrl('https://release-mid.snaptec.co/')

WebUI.maximizeWindow()

WebUI.setText(findTestObject('Object Repository/Helpdesk/AjStore/FE/Search/input_Welcome back_styles_input__0Zh_0'), 'NTsupport')

WebUI.setText(findTestObject('Object Repository/Helpdesk/AjStore/FE/Search/input_Welcome back_styles_input__0Zh_0_1'), 'admin123')

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/FE/Search/span_Sign-In'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/FE/Search/div_Sales'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/FE/Search/div_Orders'))

WebUI.setText(findTestObject('Object Repository/Helpdesk/AjStore/FE/Search/input_Order Number_increment_id'), '15000000384')

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/FE/Search/span_Search'))

WebUI.verifyElementText(findTestObject('Object Repository/Helpdesk/AjStore/FE/Search/div_15000000384'), '15000000384')

