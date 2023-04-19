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
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory


WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.FE_URL)
WebUI.maximizeWindow()

WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Shared/Login'), 20)

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Shared/Login'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Login/LoginTolephone'), 20)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Login/LoginTolephone'), 20)

WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AjStore/Login/LoginTolephone')
, GlobalVariable.FE_Tel)

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Login/acknowledgement'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Login/LoginButton'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Login/EnterOTP'), FailureHandling.STOP_ON_FAILURE)


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

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/BE/Menu/Menu_MageDelight'))


WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/BE/Menu/Menu_MageDelight_MobileOTPLogin'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/BE/Menu/Menu_MageDelight_MobileOTPLogin_SMSLog'))



//WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Page_SMS Log  Magento Admin/button_Remove'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/button_Filters'))



WebUI.setText(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/transaction_type'), 
    '')
WebUI.setText(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/status'), 
    '')
WebUI.setText(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/s_id'), 
    '')
WebUI.setText(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/recipient_phone'), 
    '')
WebUI.setText(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/message_body'), 
    '')
WebUI.setText(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/entity_id_to'), 
    '')
WebUI.setText(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/entity_id_from'), 
    '')
WebUI.setText(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/api_service'), 
    '')

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/BE/Sales_Order_page/Sales_orders_FilterButton'))


WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/button_Filters'))
WebUI.setText(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/recipient_phone'), 
    '966581492572')

//WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/recipient_phone'), 
//    Keys.chord(Keys.ENTER))
WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/button_Filters'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/SmsContentFirstRow'), 20)
String OTP = WebUI.getText(findTestObject('Object Repository/Helpdesk/AjStore/BE/SmsLogPage/SmsContentFirstRow'))
println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
println OTP


OTP=OTP.replace("كلمة المرور لتسجيل الدخول رمز: ", "")
println OTP

//WebUI.switchToWindowTitle('SMS Log / Magento Admin')
//WebUI.switchToWindowTitle('متجر عجلان واخوانه')

WebUI.switchToWindowIndex(currentTab)



String xPath = "//input[@type='tel' and contains( @aria-label,'Digit 1')]"
TestObject firstOTPDigit = new TestObject('objectName')
firstOTPDigit.addProperty('xpath', ConditionType.EQUALS, xPath)

WebUI.sendKeys(firstOTPDigit,OTP)

//WebUI.waitForElementNotPresent(findTestObject('Object Repository/Helpdesk/AjStore/Login/EnterOTP'), 20, FailureHandling.STOP_ON_FAILURE)


WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/AccountPage/AccountPageTitle'), 20)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/AccountPage/AccountPageTitle')
, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/AjStore/Login/EnterOTP'), 10)
WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/AccountPage/SignOut'))

//WebUI.closeBrowser()

