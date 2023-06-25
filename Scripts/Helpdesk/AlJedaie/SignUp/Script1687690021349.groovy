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
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable

import org.apache.commons.lang.RandomStringUtils
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement



boolean isMobile=false

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/AlJedaie/SharedScripts/LaunchFE'), [:],	FailureHandling.STOP_ON_FAILURE)

if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Shared/Toggle_Nav_Left-Mobile'),3)) {
	isMobile=true
}

int currentTab = WebUI.getWindowIndex()
WebDriver driver = DriverFactory.getWebDriver()
JavascriptExecutor js = ((driver) as JavascriptExecutor)
js.executeScript('window.open();')
WebUI.switchToWindowIndex(currentTab + 1)
WebUI.navigateToUrl(GlobalVariable.BE_URL)
WebUI.setText(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/Login/UserName'), GlobalVariable.BE_UserName)
WebUI.setText(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/Login/Password'), GlobalVariable.BE_Password)
WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/Login/LoginButton'))
if(WebUI.waitForElementPresent(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/Shared/SomethingWentWrong'), 5)
	& WebUI.waitForElementPresent(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/Shared/SomethingWentWrongOK'), 5) ) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/Shared/SomethingWentWrongOK'))
}

WebUI.switchToWindowIndex(currentTab)

WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Shared/AccountIcon'))
WebUI.mouseOver(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Shared/AccountIcon'))
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Shared/Login'),3)



WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Login/LoginPopup'),5)

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Login/LoginTolephone'), 20)

WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/SignNewAccountUp'), 5)
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/SignNewAccountUp'))
WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/SignNewAccountUp'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/FirstName'), 5)

WebUI.delay(1)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/CustomerPhone'), GlobalVariable.SignUp_Phone)
WebUI.delay(1)
String randomString = RandomStringUtils.random(5, "0123456789".toCharArray())

CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/FirstName'))
WebUI.delay(1)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/FirstName'), 'Snaptec_' + randomString)
WebUI.delay(1)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/FamilyName'), 'testing_' + randomString)
WebUI.delay(1)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/Email'), 'mahmoud_' + randomString + '@snaptec.co')
WebUI.delay(1)
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/Acknowledgement'))
WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/Acknowledgement'))
WebUI.delay(1)
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/SendOTP'))
WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/SendOTP'))
WebUI.delay(1)

String OTP = getSignUpOTP()
WebUI.switchToWindowIndex(currentTab)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/OTPField'), OTP)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Login/ConfirmOTPbtn'), 3)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Login/ConfirmOTPbtn'), 3)
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Login/ConfirmOTPbtn'))
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Login/ConfirmOTPbtn'),3)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Login/OTPConfirmed'), 5)

CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/ConfirmSignUp'))
WebUI.waitForElementNotHasAttribute(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/ConfirmSignUp'), 'disabled', 5)
WebUI.delay(1)
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/ConfirmSignUp'))
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/ConfirmSignUp'), 5)
WebUI.delay(1)

if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/ErrAccountAlreadyExists'), 10)){
	deleteCustomerProfile()
	WebUI.switchToWindowIndex(currentTab)
	WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/CustomerPhone'), GlobalVariable.SignUp_Phone)
	WebUI.delay(1)
	CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/Acknowledgement'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/Acknowledgement'))
	WebUI.delay(1)
	CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/SendOTP'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/SendOTP'))
	WebUI.delay(1)
	OTP = getSignUpOTP()
	WebUI.switchToWindowIndex(currentTab)
	WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/OTPField'), OTP)
	WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Login/ConfirmOTPbtn'), 3)
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Login/ConfirmOTPbtn'), 3)
	CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Login/ConfirmOTPbtn'))
	CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Login/ConfirmOTPbtn'),3)
	WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Login/OTPConfirmed'), 5)
	CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/ConfirmSignUp'))
	WebUI.waitForElementNotHasAttribute(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/ConfirmSignUp'), 'disabled', 5)
	WebUI.delay(1)
	CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/ConfirmSignUp'))
	CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/SignUp/ConfirmSignUp'), 5)
	WebUI.delay(1)
}


WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Shared/AccountIcon'))
WebUI.mouseOver(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Shared/AccountIcon'))
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Shared/Login'),5)
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Login/MyAccount'),3)
//WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Login/MyAccount'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/AccountPage/AccountPageTitle'), 10)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/AccountPage/AccountPageTitle'), FailureHandling.STOP_ON_FAILURE)
/////////////////////////

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/AlJedaie/SharedScripts/ClickLogo'), [:],	FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Shared/AccountIcon'))
WebUI.mouseOver(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Shared/AccountIcon'))
//WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/AccountPage/SignOut'),FailureHandling.OPTIONAL)
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/AccountPage/SignOut'),3)
WebUI.waitForPageLoad(10)

WebUI.delay(5)
WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Shared/AccountIcon'))
WebUI.mouseOver(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Shared/AccountIcon'))
WebUI.waitForElementNotPresent(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/AccountPage/SignOut'),5)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Shared/Login'),10)


WebUI.delay(5)
WebUI.closeBrowser()



String getSignUpOTP() {
	int currentTab = WebUI.getWindowIndex()
	WebUI.switchToWindowIndex(currentTab + 1)
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/Menu/Menu_MageDelight'),20)
	WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/Menu/Menu_MageDelight'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/Menu/Menu_MageDelight_MobileOTPLogin'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/Menu/Menu_MageDelight_MobileOTPLogin_SMSLog'))
	if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/SmsLogPage/ClearFilter'), 10)) {
		WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/SmsLogPage/ClearFilter'))
	}
	WebUI.delay(1)
	WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/SmsLogPage/button_Filters'))
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/SmsLogPage/recipient_phone'), 2)
	WebUI.setText(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/SmsLogPage/recipient_phone'), GlobalVariable.SignUp_Phone)
	WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/Sales_Order_page/Sales_orders_FilterButton'))
	WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/SmsLogPage/SmsViewFirstRow'), 10)
	WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/SmsLogPage/SmsViewFirstRow'))
	String OTP = WebUI.getText(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/SmsLogPage/SMSContentField'))
	println('>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>')
	println(OTP)
	OTP=OTP.findAll('\\d+').get(0)
	println(OTP)
	return OTP
}

void deleteCustomerProfile() {
	int currentTab = WebUI.getWindowIndex()
	WebUI.switchToWindowIndex(currentTab + 1)
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/Menu/Menu_Customer'),5)
	WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/Menu/Menu_Customer'))
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/Menu/Menu_Customer_AllCustomers'),5)
	WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/Menu/Menu_Customer_AllCustomers'))
	
	if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/SmsLogPage/ClearFilter'), 5)) {
		WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/SmsLogPage/ClearFilter'))
	}
	WebUI.delay(1)
	WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/CustomerPage/button_Filters'))
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/CustomerPage/Filter_Phone'), 2)
	WebUI.setText(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/CustomerPage/Filter_Phone'), "966" +  GlobalVariable.SignUp_Phone.toString())
	WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/CustomerPage/Customers_FilterButton'))
	WebUI.delay(3)
	WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/CustomerPage/PhoneColumn'), 10)
	List<WebElement> SearchResultsPhone = WebUiCommonHelper.findWebElements(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/CustomerPage/PhoneColumn'), 10)
	SearchResultsPhone.any {
		String Phone = it.getText()
		println Phone
		if(Phone.equalsIgnoreCase("+966" + GlobalVariable.SignUp_Phone.toString())) {
			WebElement editCustomer = it.findElement(By.xpath('./ancestor::tr//a[text()="Edit"]'))
			CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(editCustomer)
			editCustomer.click()
			return
		}
	}
	if (WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/CustomerPage/CustomerInformationTab'),
		10)) {
		CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/CustomerPage/CustomerInformationTab'),5)
		String PhoneOnCustomerProfile = WebUI.getAttribute(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/CustomerPage/CustomerMobile'),'value')
		if (PhoneOnCustomerProfile.equalsIgnoreCase('+966' + GlobalVariable.SignUp_Phone.toString())) {
			WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/CustomerPage/CustomerProfileDelete'))
			TestObject DeleteButtonOKTO = new TestObject()
			DeleteButtonOKTO.addProperty('xpath', ConditionType.EQUALS, '//button[@class="action-primary action-accept"]')
			WebElement DeleteButtonOKElem = WebUiCommonHelper.findWebElement(DeleteButtonOKTO, 30)
			WebUI.waitForElementVisible(DeleteButtonOKTO, 10)
			DeleteButtonOKElem.click()
			WebUI.delay(2)
		} else {
			KeywordUtil.markFailedAndStop('Fail to find the customer profile with phone: ' + GlobalVariable.SignUp_Phone.toString())
		}
	} else {
		KeywordUtil.markFailedAndStop('Fail to find the customer profile with phone: ' + GlobalVariable.SignUp_Phone.toString())
	}
}
