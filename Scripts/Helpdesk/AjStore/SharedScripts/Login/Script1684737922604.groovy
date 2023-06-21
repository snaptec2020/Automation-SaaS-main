import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.html5.Location
import org.openqa.selenium.html5.LocationContext

import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
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
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword as Keyword
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword as WebUIAbstractKeyword
import catalog.catlogComponants as catlogComponants
import java.util.List as List
import java.util.concurrent.ConcurrentHashMap.KeySetView as KeySetView
import org.eclipse.jdt.internal.compiler.ast.ForeachStatement as ForeachStatement
import org.openqa.selenium.By as By
import org.openqa.selenium.remote.DesiredCapabilities



WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/FE/Shared/Login'), 20)

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/FE/Shared/Login'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Login/LoginTolephone'), 20)

WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/FE/Login/LoginTolephone'), 20)

WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AjStore/FE/Login/LoginTolephone'), GlobalVariable.FE_Tel)

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/FE/Login/acknowledgement'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/FE/Login/LoginButton'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Login/EnterOTP'), 10)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Login/EnterOTP'), FailureHandling.STOP_ON_FAILURE)

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

WebUI.navigateToUrl(GlobalVariable.BE_URL)

//WebUI.switchToWindowTitle('متجر عجلان واخوانه')
WebUI.setText(findTestObject('Object Repository/Helpdesk/AjStore/BE/Login/UserName'), GlobalVariable.BE_UserName)

//WebUI.setEncryptedText(findTestObject('Object Repository/Helpdesk/AjStore/BE/Login/Password'), 'h9YfHV16ZyMBoeJlmdP5xA==')
WebUI.setText(findTestObject('Object Repository/Helpdesk/AjStore/BE/Login/Password'), GlobalVariable.BE_Password)

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/BE/Login/LoginButton'))

if(WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/AjStore/BE/Shared/SomethingWentWrong'), 5,FailureHandling.OPTIONAL) 
	& WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/AjStore/BE/Shared/SomethingWentWrongOK'), 10,FailureHandling.OPTIONAL) ) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/BE/Shared/SomethingWentWrongOK'))
}
WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/BE/Menu/Menu_MageDelight'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/BE/Menu/Menu_MageDelight_MobileOTPLogin'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/BE/Menu/Menu_MageDelight_MobileOTPLogin_SMSLog'))

//WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Page_SMS Log  Magento Admin/button_Remove'))
if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/ClearFilter'), 10)) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/ClearFilter'))
}
//WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/button_Filters'))
//
//WebUI.setText(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/transaction_type'), '')
//
//WebUI.setText(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/status'), '')
//
//WebUI.setText(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/s_id'), '')
//
//WebUI.setText(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/recipient_phone'), '')
//
//WebUI.setText(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/message_body'), '')
//
//WebUI.setText(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/entity_id_to'), '')
//
//WebUI.setText(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/entity_id_from'), '')
//
//WebUI.setText(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/api_service'), '')
//
//WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/BE/Sales_Order_page/Sales_orders_FilterButton'))

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/button_Filters'))

WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/recipient_phone'), 2)

WebUI.setText(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/recipient_phone'), GlobalVariable.FE_Tel)

//WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/recipient_phone'),
//    Keys.chord(Keys.ENTER))
WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/BE/Sales_Order_page/Sales_orders_FilterButton'))

//TestObject FirstRowReceiptPhone = new TestObject()
//FirstRowReceiptPhone.addProperty("xpath",ConditionType.EQUALS,"//tr[@class='data-row' and @data-repeat-index='0']/td[6]/div")
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/SmsContentFirstRow'), 20)

String OTP = WebUI.getText(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/SmsContentFirstRow'))

println('>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>')

println(OTP)

OTP = OTP.replace('كلمة المرور لتسجيل الدخول رمز: ', '')

println(OTP)

//WebUI.switchToWindowTitle('SMS Log / Magento Admin')
//WebUI.switchToWindowTitle('متجر عجلان واخوانه')
WebUI.delay(2)

WebUI.switchToWindowIndex(currentTab)

String xPath = "//input[@type='tel' and contains( @aria-label,'Digit 1')]"

TestObject firstOTPDigit = new TestObject('objectName')

firstOTPDigit.addProperty('xpath', ConditionType.EQUALS, xPath)

WebUI.sendKeys(firstOTPDigit, OTP)

//WebUI.waitForElementNotPresent(findTestObject('Object Repository/Helpdesk/AjStore/FE/Login/EnterOTP'), 20, FailureHandling.STOP_ON_FAILURE)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/AccountPage/AccountPageTitle'), 20)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/AccountPage/AccountPageTitle'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/AjStore/FE/Login/EnterOTP'), 2)