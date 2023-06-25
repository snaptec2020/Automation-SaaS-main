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


WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/karazlinen/karazlinen-KSA/SharedScripts/LaunchFE'), [:],	FailureHandling.STOP_ON_FAILURE)
isMobile=WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/karazlinen/karazlinen-KSA/SharedScripts/isMobile'), [:],	FailureHandling.CONTINUE_ON_FAILURE)

String LastURLPart = CustomKeywords.'helpdesk.HelpdeskUtil.getSecondURLPart'(GlobalVariable.FE_URL)

if(!LastURLPart.equals('/ar-sa/')) {
	SwitchToStore('السعودية')
}

int currentTab = WebUI.getWindowIndex()
WebDriver driver = DriverFactory.getWebDriver()
JavascriptExecutor js = ((driver) as JavascriptExecutor)
js.executeScript('window.open();')
WebUI.switchToWindowIndex(currentTab + 1)
WebUI.navigateToUrl(GlobalVariable.BE_URL)
WebUI.setText(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Login/UserName'), GlobalVariable.BE_UserName)
WebUI.setText(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Login/Password'), GlobalVariable.BE_Password)
WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Login/LoginButton'))
if(WebUI.waitForElementPresent(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Shared/SomethingWentWrong'), 5)
	& WebUI.waitForElementPresent(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Shared/SomethingWentWrongOK'), 5) ) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Shared/SomethingWentWrongOK'))
}

WebUI.switchToWindowIndex(currentTab)


String AccountIcon = "Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/AccountIcon"
if(!WebUI.waitForElementVisible(findTestObject(AccountIcon),3)) {
	AccountIcon="Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/AccountIcon-Mobile"
}
WebUI.click(findTestObject(AccountIcon))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Login/LoginTolephone'), 20)

WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/SignUp/SignNewAccountUp'), 5)
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/SignUp/SignNewAccountUp'))
WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/SignUp/SignNewAccountUp'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/SignUp/FirstName'), 5)

WebUI.delay(1)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/SignUp/CustomerPhone'), Keys.chord(Keys.HOME))
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/SignUp/CustomerPhone'), GlobalVariable.SignUp_Phone)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/SignUp/CustomerPhone'), Keys.chord(Keys.ENTER))
WebUI.delay(2)
String randomString = RandomStringUtils.random(5, "0123456789".toCharArray())

CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/SignUp/FirstName'))
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/SignUp/FirstName'), 'Snaptec_' + randomString)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/SignUp/Email'), 'mahmoud_' + randomString + '@snaptec.co')

CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/SignUp/ConfirmSignUp'))
WebUI.waitForElementNotHasAttribute(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/SignUp/ConfirmSignUp'), 'disabled', 5)
WebUI.delay(1)
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/SignUp/ConfirmSignUp'), 5)
WebUI.delay(2)

if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Login/OTPField'), 10)) {
	String OTP = getSignUpOTP()
	WebUI.switchToWindowIndex(currentTab)
	WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Login/OTPField'), OTP)
}else if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/SignUp/ErrAccountAlreadyExists'), 10)){
	WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/SignUp/CancelSignUp'))
	deleteCustomerProfile()
	WebUI.switchToWindowIndex(currentTab)
	CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/SignUp/ConfirmSignUp'), 5)
	if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Login/OTPField'), 20)) {
		String OTP = getSignUpOTP()
		WebUI.switchToWindowIndex(currentTab)
		WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Login/OTPField'), OTP)
	}else {
		KeywordUtil.markFailedAndStop('Something went wrong')
	}
}else {
	KeywordUtil.markFailedAndStop('Something went wrong')
}

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


AccountIcon = "Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/AccountIcon"
if(!WebUI.waitForElementVisible(findTestObject(AccountIcon),3)) {
	AccountIcon="Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/AccountIcon-Mobile"
}

WebUI.click(findTestObject(AccountIcon))
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/AccountPage/AccountInfoIcon'), 5)
WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/AccountPage/AccountInfoIcon'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/AccountPage/AccountPageTitle'), 5)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/AccountPage/AccountPageTitle'), FailureHandling.STOP_ON_FAILURE)
/////////////////////////

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/karazlinen/karazlinen-KSA/SharedScripts/ClickLogo'), [:],	FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject(AccountIcon))
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/AccountPage/SignOut'),3)
WebUI.waitForPageLoad(10)

WebUI.delay(5)
WebUI.click(findTestObject(AccountIcon))
WebUI.waitForElementNotPresent(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/AccountPage/SignOut'),5)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Login/LoginTolephone'), 20)

WebUI.delay(5)
WebUI.closeBrowser()



String getSignUpOTP() {
	int currentTab = WebUI.getWindowIndex()
	WebUI.switchToWindowIndex(currentTab + 1)
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Menu/Menu_MageDelight'),20)
	WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Menu/Menu_MageDelight'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Menu/Menu_MageDelight_MobileOTPLogin'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Menu/Menu_MageDelight_MobileOTPLogin_SMSLog'))
	if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/SmsLogPage/ClearFilter'), 10)) {
		WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/SmsLogPage/ClearFilter'))
	}
	WebUI.delay(1)
	WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/SmsLogPage/button_Filters'))
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/SmsLogPage/recipient_phone'), 2)
	WebUI.setText(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/SmsLogPage/recipient_phone'), GlobalVariable.SignUp_Phone)
	WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Sales_Order_page/Sales_orders_FilterButton'))
	WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/SmsLogPage/SmsViewFirstRow'), 10)
	WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/SmsLogPage/SmsViewFirstRow'))
	String OTP = WebUI.getText(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/SmsLogPage/SMSContentField'))
	println('>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>')
	println(OTP)
	OTP=OTP.findAll('\\d+').get(0)
	println(OTP)
	return OTP
}

void deleteCustomerProfile() {
	int currentTab = WebUI.getWindowIndex()
	WebUI.switchToWindowIndex(currentTab + 1)
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Menu/Menu_Customer'),5)
	WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Menu/Menu_Customer'))
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Menu/Menu_Customer_AllCustomers'),5)
	WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/Menu/Menu_Customer_AllCustomers'))
	
	if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/SmsLogPage/ClearFilter'), 5)) {
		WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/SmsLogPage/ClearFilter'))
	}
	WebUI.delay(1)
	WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/CustomerPage/button_Filters'))
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/CustomerPage/Filter_Phone'), 2)
	WebUI.setText(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/CustomerPage/Filter_Phone'), "966" +  GlobalVariable.SignUp_Phone.toString())
	WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/CustomerPage/Customers_FilterButton'))
	WebUI.delay(3)
	WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/CustomerPage/PhoneColumn'), 10)
	List<WebElement> SearchResultsPhone = WebUiCommonHelper.findWebElements(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/CustomerPage/PhoneColumn'), 10)
	SearchResultsPhone.any {
		String Phone = it.getText()
		println Phone
		if(Phone.equalsIgnoreCase("966" + GlobalVariable.SignUp_Phone.toString())) {
			WebElement editCustomer = it.findElement(By.xpath('./ancestor::tr//a[text()="Edit"]'))
			CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(editCustomer)
			editCustomer.click()
			return
		}
	}
	if (WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/CustomerPage/CustomerInformationTab'),
		10)) {
		CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/CustomerPage/CustomerInformationTab'),5)
		String PhoneOnCustomerProfile = WebUI.getAttribute(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/CustomerPage/CustomerMobile'),'value')
		if (PhoneOnCustomerProfile.equalsIgnoreCase('966' + GlobalVariable.SignUp_Phone.toString())) {
			WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/BE/CustomerPage/CustomerProfileDelete'))
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
