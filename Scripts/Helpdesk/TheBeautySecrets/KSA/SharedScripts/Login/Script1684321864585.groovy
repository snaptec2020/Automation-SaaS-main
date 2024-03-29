import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.keyword.builtin.CallTestCaseKeyword
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
import org.openqa.selenium.Keys
import org.openqa.selenium.TimeoutException

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

WebUI.mouseOver(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/AccountIcon'))
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/Login'),3)

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Login/LoginTolephone'), 20)
WebUI.delay(1)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Login/LoginTolephone'), GlobalVariable.FE_Tel)
WebUI.focus(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Login/SaveNumber'))
WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Login/SelectCountry'))
WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Login/SelectCountry'))
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Login/SaveNumber'),3)

//CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Login/LoginButton'),3)

WebUI.delay(2)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Login/OTPField'),5)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Login/OTPField'))



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

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/TheBeautySecrets/KSA/SharedScripts/LaunchBE'), [:],	FailureHandling.STOP_ON_FAILURE)

WebUI.setText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/Login/UserName'), GlobalVariable.BE_UserName)

//WebUI.setEncryptedText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/Login/Password'), 'h9YfHV16ZyMBoeJlmdP5xA==')
WebUI.setText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/Login/Password'), GlobalVariable.BE_Password)
WebUI.waitForPageLoad(10)
WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/Login/LoginButton'),FailureHandling.CONTINUE_ON_FAILURE)
if(WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/Shared/SomethingWentWrong'), 3,FailureHandling.OPTIONAL) & WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/Shared/SomethingWentWrongOK'), 10,FailureHandling.OPTIONAL) ) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/Shared/SomethingWentWrongOK'))
}
//WebUI.waitForPageLoad(20)

WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/Menu/Menu_MageDelight'),10)
WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/Menu/Menu_MageDelight'))

WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/Menu/Menu_MageDelight_MobileOTPLogin'))

WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/Menu/Menu_MageDelight_MobileOTPLogin_SMSLog'))

WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/SmsLogPage/button_Filters'))

WebUI.setText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/SmsLogPage/transaction_type'), '')

WebUI.setText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/SmsLogPage/status'), '')

WebUI.setText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/SmsLogPage/s_id'), '')

WebUI.setText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/SmsLogPage/recipient_phone'), '')


WebUI.setText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/SmsLogPage/entity_id_to'), '')

WebUI.setText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/SmsLogPage/entity_id_from'), '')

WebUI.setText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/SmsLogPage/api_service'), '')

WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/Sales_Order_page/Sales_orders_FilterButton'))

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/SmsLogPage/button_Filters'))

WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/SmsLogPage/recipient_phone'), 2)

WebUI.setText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/SmsLogPage/recipient_phone'), GlobalVariable.FE_Tel)

//    Keys.chord(Keys.ENTER))
WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/Sales_Order_page/Sales_orders_FilterButton'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/SmsLogPage/SmsViewFirstRow'), 10)
WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/SmsLogPage/SmsViewFirstRow'))

String OTP = WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/SmsLogPage/SMSContentField'))
println('>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>')

println(OTP)
OTP=OTP.findAll('\\d+').get(0)
println(OTP)

WebUI.delay(2)

WebUI.switchToWindowIndex(currentTab)

WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Login/OTPField'), OTP)

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Login/ConfirmOTPbtn'), 3)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Login/ConfirmOTPbtn'), 3)
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Login/ConfirmOTPbtn'),3)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Login/OTPConfirmed'), 5)

//WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Login/OTPField'),2)
//if(WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Login/OTPField'))) {
//	assert false
//}

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Login/LoginButton'), 3)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Login/LoginButton'), 3)
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Login/LoginButton'),3)

WebUI.waitForPageLoad(20)



