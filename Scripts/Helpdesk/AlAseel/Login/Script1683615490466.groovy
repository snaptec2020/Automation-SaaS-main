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
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.testobject.ConditionType
import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory


WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/AlAseel/SharedScripts/LaunchFE'), [:],	FailureHandling.STOP_ON_FAILURE)



WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/Login'))
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/LoginPopup'),5)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/PhoneLoginTab'),10)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/PhoneLoginTab'),10)
WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/PhoneLoginTab'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/LoginTolephone'), 20)

WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/LoginTolephone'), GlobalVariable.FE_Tel)
WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/LoginButton'))

WebUI.delay(2)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/SendOTP'), 5)
WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/SendOTP'))
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/CheckOTPText'),5)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/CheckOTPText'))



int currentTab = WebUI.getWindowIndex()

//Robot robot = new Robot()
//robot.keyPress(KeyEvent.VK_CONTROL)
//robot.keyPress(KeyEvent.VK_T)
//robot.keyRelease(KeyEvent.VK_CONTROL)
//robot.keyRelease(KeyEvent.VK_T)
WebDriver driver = DriverFactory.getWebDriver()

JavascriptExecutor js = ((driver) as JavascriptExecutor)

js.executeScript('window.open();')

WebUI.switchToWindowIndex(currentTab + 1)
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

WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Menu/Menu_MageDelight'),20)
WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Menu/Menu_MageDelight'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Menu/Menu_MageDelight_MobileOTPLogin'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Menu/Menu_MageDelight_MobileOTPLogin_SMSLog'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/button_Filters'))

WebUI.setText(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/transaction_type'), '')

WebUI.setText(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/status'), '')

WebUI.setText(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/s_id'), '')

WebUI.setText(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/recipient_phone'), '')


WebUI.setText(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/entity_id_to'), '')

WebUI.setText(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/entity_id_from'), '')

WebUI.setText(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/api_service'), '')

WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Sales_Order_page/Sales_orders_FilterButton'))

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/button_Filters'))

WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/recipient_phone'), 2)

WebUI.setText(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/recipient_phone'), GlobalVariable.FE_Tel)

//    Keys.chord(Keys.ENTER))
WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Sales_Order_page/Sales_orders_FilterButton'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/SmsViewFirstRow'), 20)
WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/SmsViewFirstRow'))

String OTP = WebUI.getText(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/SMSContentField'))
println('>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>')

println(OTP)
OTP=OTP.findAll('\\d+').get(0)
println(OTP)

WebUI.delay(2)

WebUI.switchToWindowIndex(currentTab)

WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/OTPField'), OTP)

WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/ConfirmOTPbtn'))
WebUI.waitForPageLoad(20)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/AccountPage/AccountPageTitle'), 20)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/AccountPage/AccountPageTitle'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/ConfirmOTPbtn'), 2)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/AccountBtn'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/AccountBtn'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/AccountPopup'),5)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/AccountPopup'))
WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/CloseAccountPopup'))

/////////////////////////
WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/Logo'))
WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/AccountBtn'))

WebUI.waitForElementPresent(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/AccountPopup'),3)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/AccountPopup'),3)

WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/AccountPage/SignOut'))

WebUI.waitForElementNotVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/AccountBtn'),20)

WebUI.closeBrowser()

