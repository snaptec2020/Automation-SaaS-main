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
import com.kms.katalon.core.webui.keyword.builtin.ScrollToElementKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys


WebUI.openBrowser('')
WebUI.maximizeWindow()

WebUI.authenticate(GlobalVariable.BE_URL, GlobalVariable.BEBasicAuthUser, GlobalVariable.BEBasicAuthPassword, 20, FailureHandling.OPTIONAL)

WebUI.setText(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Login/UserName'), GlobalVariable.BE_UserName)
	
//WebUI.setEncryptedText(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Login/Password'), 'h9YfHV16ZyMBoeJlmdP5xA==')
WebUI.setText(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Login/Password'), GlobalVariable.BE_Password)
WebUI.waitForPageLoad(10)
WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Login/LoginButton'),FailureHandling.CONTINUE_ON_FAILURE)
if(WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Shared/SomethingWentWrong'), 10,FailureHandling.OPTIONAL) & WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Shared/SomethingWentWrongOK'), 10,FailureHandling.OPTIONAL) ) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Shared/SomethingWentWrongOK'))
}
WebUI.waitForPageLoad(20)

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Menu/Menu_Dashboard'))
WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Menu/Menu_Sales'))
WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Menu/Menu_Catalog'))
//WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Menu/Menu_MobileApp'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Menu/Menu_Sales'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Menu/Menu_Sales_orders'),10)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Menu/Menu_Sales_orders'),10)
WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Menu/Menu_Sales_orders'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Sales_Order_page/Sales_order_Grid'))

WebUI.delay(2)
WebUI.closeBrowser()