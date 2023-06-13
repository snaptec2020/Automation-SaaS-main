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
import org.openqa.selenium.Keys as Keys
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.testobject.ConditionType
import java.awt.Robot as Robot
import java.awt.event.KeyEvent

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory


private SwitchToStore(String StoreName) {
	if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/StoreSelect'), 40)) {
		WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/StoreSelect'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/StoreSelectList'))
		String StoreXPath = './/p[text()="' + StoreName + '"]//ancestor::a[contains(@class,"storeTrigger-listFlags")]'
		WebElement Store = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/StoreSelectList'), 5).findElement(By.xpath(StoreXPath))
		Store.click()
	}else {
		WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/StoreSelect-Mobile'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/StoreSelectList-Mobile'))
		String StoreXPath = '//p[text()="' + StoreName + '"]//ancestor::li[contains(@class,"bottomTab-listFlags")]'
		WebElement Store = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/StoreSelectList-Mobile'), 5).findElement(By.xpath(StoreXPath))
		Store.click()
	}
}

boolean isMobile=false
isMobile=WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/karazlinen/karazlinen-KSA/SharedScripts/isMobile'), [:],	FailureHandling.CONTINUE_ON_FAILURE)
//if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/BottomMenu-Mobile'),3)) {
//	isMobile=true
//}


String LastURLPart = CustomKeywords.'helpdesk.HelpdeskUtil.getSecondURLPart'(GlobalVariable.FE_URL)

if(!LastURLPart.equals('/ar-sa/')) {
	SwitchToStore('السعودية')
}

String AccountIcon = "Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/AccountIcon"
if(!WebUI.waitForElementVisible(findTestObject(AccountIcon),3)) {
	AccountIcon="Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/AccountIcon-Mobile"
}
WebUI.click(findTestObject(AccountIcon))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Login/LoginTolephone'), 20)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Login/LoginTolephone'), Keys.chord(Keys.HOME))
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Login/LoginTolephone'), GlobalVariable.FE_Tel)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Login/LoginTolephone'), Keys.chord(Keys.ENTER))
WebUI.delay(2)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Login/CheckOTPText'),5)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Login/CheckOTPText'))



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

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/karazlinen/karazlinen-KSA/SharedScripts/LaunchBE'), [:],	FailureHandling.STOP_ON_FAILURE)

WebUI.setText(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Login/UserName'), GlobalVariable.BE_UserName)

//WebUI.setEncryptedText(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Login/Password'), 'h9YfHV16ZyMBoeJlmdP5xA==')
WebUI.setText(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Login/Password'), GlobalVariable.BE_Password)
WebUI.waitForPageLoad(10)
WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Login/LoginButton'),FailureHandling.CONTINUE_ON_FAILURE)
if(WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Shared/SomethingWentWrong'), 5,FailureHandling.OPTIONAL) & WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Shared/SomethingWentWrongOK'), 10,FailureHandling.OPTIONAL) ) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Shared/SomethingWentWrongOK'))
}


if(WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Shared/SurveyDivClose'), 5,FailureHandling.OPTIONAL) & WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Shared/SurveyDivClose'), 5,FailureHandling.OPTIONAL) ) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Shared/SurveyDivClose'))
}

//WebUI.waitForPageLoad(20)

WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Menu/Menu_MageDelight'),20)
WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Menu/Menu_MageDelight'))

WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Menu/Menu_MageDelight_MobileOTPLogin'))

WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Menu/Menu_MageDelight_MobileOTPLogin_SMSLog'))

if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/SmsLogPage/ClearFilter'), 10)) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/SmsLogPage/ClearFilter'))
}
	
//WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/SmsLogPage/button_Filters'))
//
//WebUI.setText(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/SmsLogPage/transaction_type'), '')
//
//WebUI.setText(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/SmsLogPage/status'), '')
//
//WebUI.setText(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/SmsLogPage/s_id'), '')
//
//WebUI.setText(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/SmsLogPage/recipient_phone'), '')
//
//
//WebUI.setText(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/SmsLogPage/entity_id_to'), '')
//
//WebUI.setText(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/SmsLogPage/entity_id_from'), '')
//
//WebUI.setText(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/SmsLogPage/api_service'), '')
//
//WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Sales_Order_page/Sales_orders_FilterButton'))

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/SmsLogPage/button_Filters'))

WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/SmsLogPage/recipient_phone'), 2)

WebUI.setText(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/SmsLogPage/recipient_phone'), GlobalVariable.FE_Tel)

//    Keys.chord(Keys.ENTER))
WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Sales_Order_page/Sales_orders_FilterButton'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/SmsLogPage/SmsViewFirstRow'), 10)
WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/SmsLogPage/SmsViewFirstRow'))

String OTP = WebUI.getText(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/SmsLogPage/SMSContentField'))
println('>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>')

println(OTP)
OTP=OTP.findAll('\\d+').get(0)
println(OTP)

WebUI.delay(2)

WebUI.switchToWindowIndex(currentTab)

WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Login/OTPField'), OTP)

WebUI.waitForPageLoad(20)


LastURLPart = CustomKeywords.'helpdesk.HelpdeskUtil.getSecondURLPart'(GlobalVariable.FE_URL)
switch(LastURLPart) {
	case '/ar-ae/':
		SwitchToStore('الإمارات')
		break
	case '/ar-kw/':
		SwitchToStore('الكويت')
		break
	case '/ar-om/':
		SwitchToStore('سلطنة عمان')
		break
	case '/ar-bh/':
		SwitchToStore('البحرين')
		break
	case '/ar-qa/':
		SwitchToStore('قطر')
		break
}

