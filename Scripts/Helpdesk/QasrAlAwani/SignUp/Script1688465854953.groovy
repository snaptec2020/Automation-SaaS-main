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


WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/QasrAlAwani/SharedScripts/LaunchFE'), [:],	FailureHandling.STOP_ON_FAILURE)
if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/BottomMenu-Mobile'), 10)) {
	isMobile=true
}else {
	isMobile=false
}

int currentTab = WebUI.getWindowIndex()
WebDriver driver = DriverFactory.getWebDriver()
JavascriptExecutor js = ((driver) as JavascriptExecutor)
js.executeScript('window.open();')
WebUI.switchToWindowIndex(currentTab + 1)
WebUI.navigateToUrl(GlobalVariable.BE_URL)
WebUI.setText(findTestObject('Object Repository/Helpdesk/Qasr/BE/Login/UserName'), GlobalVariable.BE_UserName)
WebUI.setText(findTestObject('Object Repository/Helpdesk/Qasr/BE/Login/Password'), GlobalVariable.BE_Password)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Login/LoginButton'))
if(WebUI.waitForElementPresent(findTestObject('Object Repository/Helpdesk/Qasr/BE/Shared/SomethingWentWrong'), 5)
	& WebUI.waitForElementPresent(findTestObject('Object Repository/Helpdesk/Qasr/BE/Shared/SomethingWentWrongOK'), 5) ) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Shared/SomethingWentWrongOK'))
}

WebUI.switchToWindowIndex(currentTab)


WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Login'), 20)

WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Login'))

WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'),20)

String SignNewAccountUp = 'Object Repository/Helpdesk/Qasr/FE/SignUp/SignNewAccountUp'
if(isMobile) {
	SignNewAccountUp = 'Object Repository/Helpdesk/Qasr/FE/SignUp/SignNewAccountUp-Mobile'
}

WebUI.waitForElementClickable(findTestObject(SignNewAccountUp), 5)
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject(SignNewAccountUp))
WebUI.click(findTestObject(SignNewAccountUp))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/SignUp/FirstName'), 5)

//WebUI.delay(1)
//WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/SignUp/CustomerPhone'), GlobalVariable.SignUp_Phone)
//WebUI.delay(1)

WebUI.delay(1)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'),20)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'))
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountryDropDownKSA'),10)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountryDropDownKSA'))
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/LoginTolephone'), 20)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/LoginTolephone'), 20)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/LoginTolephone'), GlobalVariable.SignUp_Phone)
WebUI.delay(1)


String randomString = RandomStringUtils.random(5, "0123456789".toCharArray())

CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Qasr/FE/SignUp/FirstName'))
WebUI.delay(1)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/SignUp/FirstName'), 'Snaptec_' + randomString)
WebUI.delay(1)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/SignUp/FamilyName'), 'testing_' + randomString)
WebUI.delay(1)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/SignUp/Email'), 'mahmoud_' + randomString + '@snaptec.co')
WebUI.delay(1)
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Qasr/FE/SignUp/Acknowledgement'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/SignUp/Acknowledgement'))

WebUI.delay(1)
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Qasr/FE/SignUp/ConfirmSignUp'))
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/Qasr/FE/SignUp/ConfirmSignUp'), 5)
WebUI.delay(1)

if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/SignUp/ErrAccountAlreadyExists'), 15)){
	deleteCustomerProfile()
	WebUI.switchToWindowIndex(currentTab)
	WebUI.delay(1)
	CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Qasr/FE/SignUp/ConfirmSignUp'))
	CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/Qasr/FE/SignUp/ConfirmSignUp'), 5)
	WebUI.delay(1)
}

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/SignUp/OTPField'),10)
String OTP = getSignUpOTP()
WebUI.switchToWindowIndex(currentTab)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/SignUp/OTPField'), OTP)



WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/EnterOTP'), 2)

/////////////////////////
//WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'), 3)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Login'))
if(!isMobile) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/AccountPage/SignOut'))
}else {
//	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/AccountPage/SignOut-Mobile'))
	CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/Qasr/FE/AccountPage/SignOut-Mobile'), 2)
}


if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/AppDownload_close'), 5)) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/AppDownload_close'))
}

WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Login'))

WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'),20)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'))
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountryDropDownKSA'),10)

WebUI.delay(5)
WebUI.closeBrowser()



String getSignUpOTP() {
	int currentTab = WebUI.getWindowIndex()
	WebUI.switchToWindowIndex(currentTab + 1)
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_MageDelight'),20)
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_MageDelight'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_MageDelight_MobileOTPLogin'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_MageDelight_MobileOTPLogin_SMSLog'))
	if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/BE/SmsLogPage/ClearFilter'), 10)) {
		WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/SmsLogPage/ClearFilter'))
	}
	WebUI.delay(1)
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/SmsLogPage/button_Filters'))
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/BE/SmsLogPage/recipient_phone'), 2)
	WebUI.setText(findTestObject('Object Repository/Helpdesk/Qasr/BE/SmsLogPage/recipient_phone'), GlobalVariable.SignUp_Phone)
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Sales_Order_page/Sales_orders_FilterButton'))
	String OTP = WebUI.getText(findTestObject('Object Repository/Helpdesk/Qasr/BE/SmsLogPage/SmsContentFirstRow'))
	println('>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>')
	println(OTP)
	OTP = OTP.replace('كلمة المرور لتسجيل الدخول رمز: ', '')
	println(OTP)
	return OTP
}

void deleteCustomerProfile() {
	int currentTab = WebUI.getWindowIndex()
	WebUI.switchToWindowIndex(currentTab + 1)
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_Customer'),5)
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_Customer'))
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_Customer_AllCustomers'),5)
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_Customer_AllCustomers'))
	
	if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/BE/SmsLogPage/ClearFilter'), 5)) {
		WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/SmsLogPage/ClearFilter'))
	}
	WebUI.delay(1)
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/CustomerPage/button_Filters'))
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/BE/CustomerPage/Filter_Phone'), 2)
	WebUI.setText(findTestObject('Object Repository/Helpdesk/Qasr/BE/CustomerPage/Filter_Phone'), "966" +  GlobalVariable.SignUp_Phone.toString())
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/CustomerPage/Customers_FilterButton'))
	WebUI.delay(3)
	WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/BE/CustomerPage/PhoneColumn'), 10)
	List<WebElement> SearchResultsPhone = WebUiCommonHelper.findWebElements(findTestObject('Object Repository/Helpdesk/Qasr/BE/CustomerPage/PhoneColumn'), 10)
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
	if (WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/BE/CustomerPage/CustomerInformationTab'),
		10)) {
		CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/Qasr/BE/CustomerPage/CustomerInformationTab'),5)
		String PhoneOnCustomerProfile = WebUI.getAttribute(findTestObject('Object Repository/Helpdesk/Qasr/BE/CustomerPage/CustomerMobile'),'value')
		if (PhoneOnCustomerProfile.equalsIgnoreCase('966' + GlobalVariable.SignUp_Phone.toString())) {
			WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/CustomerPage/CustomerProfileDelete'))
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
