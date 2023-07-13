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
import com.thoughtworks.selenium.webdriven.VariableDeclaration

import groovy.json.internal.Exceptions
import internal.GlobalVariable
import org.apache.commons.lang3.StringUtils
import org.apache.commons.text.similarity.JaroWinklerDistance
import org.apache.commons.text.similarity.JaroWinklerSimilarity
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement




WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/QasrAlAwani/SharedScripts/LaunchFE'), [:],	FailureHandling.STOP_ON_FAILURE)
if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/BottomMenu-Mobile'), 10)) {
	variableDeclation.isMobile=true
}else {
	variableDeclation.isMobile=false
}



//int currentTab = WebUI.getWindowIndex()
//WebDriver driver = DriverFactory.getWebDriver()
//JavascriptExecutor js = ((driver) as JavascriptExecutor)
//js.executeScript('window.open();')
//WebUI.switchToWindowIndex(currentTab + 1)
//WebUI.navigateToUrl(GlobalVariable.BE_URL)
//if(!WebUI.waitForElementPresent(findTestObject('Object Repository/Helpdesk/Qasr/BE/Login/UserAdministrationDropDown'), 5)) {
//	WebUI.setText(findTestObject('Object Repository/Helpdesk/Qasr/BE/Login/UserName'), GlobalVariable.BE_UserName)
//	WebUI.setText(findTestObject('Object Repository/Helpdesk/Qasr/BE/Login/Password'), GlobalVariable.BE_Password)
//	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Login/LoginButton'))
//}
//WebUI.switchToWindowIndex(currentTab)


//TC_1 get Canceled Order while not logged in
KeywordUtil.logInfo("TC_1 get Canceled Order while not logged in")
clearVars()
variableDeclation.currentOrder = GetOrCreateCanceledOrder()
variableDeclation.cancelledOrder = variableDeclation.currentOrder
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingMenu'))
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingMenu'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingMenu'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingPageTitle'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderNumber'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'))
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'),10)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountryDropDownKSA'))
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/LoginTolephone'), variableDeclation.orderTel)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderNumber'),variableDeclation.currentOrder)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingBtn'))
WebUI.verifyElementNotHasAttribute(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingBtn'),"disabled",10)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingBtn'))
//	try {
//		VerifyOrderTrackingContent()
//	}catch(Exception ex) {
//		//silient error until fix the issue with this feature ticket # HELP-3900
//		KeywordUtil.markFailedAndStop("TC_1 get Canceled Order while not logged in \n" + ex.message)
//	}
VerifyOrderTrackingContent()

//TC_2 get Pending Order while not logged in
KeywordUtil.logInfo("TC_2 get Pending Order while not logged in")
clearVars()
variableDeclation.currentOrder = GetOrCreatePendingOrder()
variableDeclation.penddingOrder = variableDeclation.currentOrder
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingMenu'))
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingMenu'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingMenu'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingPageTitle'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderNumber'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'))
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'),10)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountryDropDownKSA'))
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/LoginTolephone'), variableDeclation.orderTel)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderNumber'),variableDeclation.currentOrder)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingBtn'))
WebUI.verifyElementNotHasAttribute(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingBtn'),"disabled",10)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingBtn'))
//	try {
//		VerifyOrderTrackingContent()
//	}catch(Exception ex) {
//		//silient error until fix the issue with this feature ticket # HELP-3900
//		KeywordUtil.markFailedAndStop("TC_1 get Canceled Order while not logged in \n" + ex.message)
//	}
VerifyOrderTrackingContent()

//TC_3 get Closed Order while not logged in
KeywordUtil.logInfo("TC_3 get Closed Order while not logged in")
clearVars()
variableDeclation.currentOrder = GetOrCreateClosedOrder()
variableDeclation.closedOrder = variableDeclation.currentOrder
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingMenu'))
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingMenu'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingMenu'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingPageTitle'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderNumber'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'))
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'),10)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountryDropDownKSA'))
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/LoginTolephone'), variableDeclation.orderTel)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderNumber'),variableDeclation.currentOrder)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingBtn'))
WebUI.verifyElementNotHasAttribute(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingBtn'),"disabled",10)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingBtn'))
//	try {
//		VerifyOrderTrackingContent()
//	}catch(Exception ex) {
//		//silient error until fix the issue with this feature ticket # HELP-3900
//		KeywordUtil.markFailedAndStop("TC_1 get Canceled Order while not logged in \n" + ex.message)
//	}
VerifyOrderTrackingContent()

//TC_4 get Processing Order while not logged in
KeywordUtil.logInfo("TC_4 get Processing Order while not logged in")
clearVars()
variableDeclation.currentOrder = GetOrCreateProcessingOrder()
variableDeclation.processingOrder = variableDeclation.currentOrder
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingMenu'))
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingMenu'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingMenu'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingPageTitle'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderNumber'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'))
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'),10)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountryDropDownKSA'))
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/LoginTolephone'), variableDeclation.orderTel)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderNumber'),variableDeclation.currentOrder)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingBtn'))
WebUI.verifyElementNotHasAttribute(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingBtn'),"disabled",10)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingBtn'))
//	try {
//		VerifyOrderTrackingContent()
//	}catch(Exception ex) {
//		//silient error until fix the issue with this feature ticket # HELP-3900
//		KeywordUtil.markFailedAndStop("TC_1 get Canceled Order while not logged in \n" + ex.message)
//	}
VerifyOrderTrackingContent()



//TC_5 get Order while logged in for the same user
KeywordUtil.logInfo("TC_5 get Order while logged in for the same user")
clearVars()
variableDeclation.currentOrder = GetOrCreateCanceledOrder()
variableDeclation.cancelledOrder = variableDeclation.currentOrder
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'), 3)
WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/QasrAlAwani/SharedScripts/Login'), [:],	FailureHandling.STOP_ON_FAILURE)
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'), 3)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingMenu'))
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingMenu'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingMenu'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingPageTitle'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderNumber'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'))
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'),10)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountryDropDownKSA'))
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/LoginTolephone'), variableDeclation.orderTel)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderNumber'),variableDeclation.currentOrder)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingBtn'))
WebUI.verifyElementNotHasAttribute(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingBtn'),"disabled",10)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingBtn'))
//	try {
//		VerifyOrderTrackingContent()
//	}catch(Exception ex) {
//		//silient error until fix the issue with this feature ticket # HELP-3900
//		KeywordUtil.markFailedAndStop("TC_1 get Canceled Order while not logged in \n" + ex.message)
//	}
VerifyOrderTrackingContent()

CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'), 3)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Login'))
if(!variableDeclation.isMobile) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/AccountPage/SignOut'))
}else {
	CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/Qasr/FE/AccountPage/SignOut-Mobile'), 2)
}
if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/AppDownload_close'), 5)) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/AppDownload_close'))
}

//TC_6 get Order while logged in for the different user
KeywordUtil.logInfo("TC_6 get Order while logged in for the different user")
clearVars()
variableDeclation.currentOrder = getRandomOrderForOtherUsers()
variableDeclation.cancelledOrder_2 = variableDeclation.currentOrder
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'), 3)
WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/QasrAlAwani/SharedScripts/Login'), [:],	FailureHandling.STOP_ON_FAILURE)
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'), 3)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingMenu'))
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingMenu'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingMenu'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingPageTitle'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderNumber'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'))
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'),10)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountryDropDownKSA'))
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/LoginTolephone'), variableDeclation.orderTel)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderNumber'),variableDeclation.currentOrder)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingBtn'))
WebUI.verifyElementNotHasAttribute(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingBtn'),"disabled",10)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingBtn'))
//	try {
//		VerifyOrderTrackingContent()
//	}catch(Exception ex) {
//		//silient error until fix the issue with this feature ticket # HELP-3900
//		KeywordUtil.markFailedAndStop("TC_1 get Canceled Order while not logged in \n" + ex.message)
//	}
VerifyOrderTrackingContent()

CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'), 3)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Login'))
if(!variableDeclation.isMobile) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/AccountPage/SignOut'))
}else {
	CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/Qasr/FE/AccountPage/SignOut-Mobile'), 2)
}
if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/AppDownload_close'), 5)) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/AppDownload_close'))
}

//TC_7 enter existing Order but with wrong mobile number
KeywordUtil.logInfo("TC_7 enter existing Order but with wrong mobile number")
clearVars()
variableDeclation.currentOrder = getRandomOrderForOtherUsers()
variableDeclation.cancelledOrder_2 = variableDeclation.currentOrder
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'), 3)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingMenu'))
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingMenu'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingMenu'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingPageTitle'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderNumber'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'))
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'),10)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountryDropDownKSA'))
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/LoginTolephone'),GlobalVariable.FE_Tel)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderNumber'),variableDeclation.currentOrder)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingBtn'))
WebUI.verifyElementNotHasAttribute(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingBtn'),"disabled",10)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingBtn'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/ErrorWrongTel'),FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingPageTitle'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderNumber'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'))
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'), 3)



WebUI.delay(5)
WebUI.closeBrowser()



String GetOrCreateCanceledOrder(){
	String existingOrder =getOrderIfExists("Canceled",true)
	if (existingOrder !=null) {
		return existingOrder
	}
	if(!variableDeclation.loggedIn) {
		CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'), 3)
		WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/QasrAlAwani/SharedScripts/Login'), [:],	FailureHandling.STOP_ON_FAILURE)
		variableDeclation.loggedIn=true
	}
	CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'), 3)
	WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/QasrAlAwani/SharedScripts/ClearProductsFromCartPage'), [:],	FailureHandling.STOP_ON_FAILURE)
	CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'), 3)
	WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/QasrAlAwani/SharedScripts/OpenAndAddProductToCart'), [:],	FailureHandling.STOP_ON_FAILURE)
	CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'), 3)
	WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/view cart'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/view cart'))
	if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCart'), 5)) {
		WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancel'))
	}
	CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PriceSummaryButton'))
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PriceSummaryButton'),10)
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PriceSummaryButton'))
	if(!WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/Step_4_PaymentMethods'),FailureHandling.OPTIONAL)) {
		WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PriceSummaryButton'))
	}
	CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/ShipmentCity'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/ShipmentCity'))
	WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/ShipmentCity'), "الرياض")
	WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/ShipmentCity'),Keys.chord(Keys.ENTER))
	CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/PaymentMethod_3_Text'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/PaymentMethod_3_Text'))
	WebUI.scrollToElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/Step_1_Login'), 5)
	CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/FinishPayment'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/FinishPayment'))
	WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/credit/MainForm'), 10)
	WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/credit/MainForm'))
	WebUI.switchToFrame(findTestObject('Object Repository/Helpdesk/Qasr/FE/credit/CardNumberInputFrame'), 10)
	WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/credit/CardNumberInput'), GlobalVariable.MadaCardNum)
	WebUI.switchToDefaultContent()
	WebUI.switchToFrame(findTestObject('Object Repository/Helpdesk/Qasr/FE/credit/CardExpiryFrame'), 10)
	WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/credit/CardExpiryInput'), GlobalVariable.MadaExpiryDate)
	WebUI.switchToDefaultContent()
	WebUI.switchToFrame(findTestObject('Object Repository/Helpdesk/Qasr/FE/credit/CardCVVFrame'), 10)
	WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/credit/CardCVVInput'), GlobalVariable.MadaCVV)
	WebUI.switchToDefaultContent()
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/credit/CreditSubmit'))
	WebUI.waitForPageLoad(10)
	WebUI.delay(20)
	WebUI.waitForElementPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/credit/MainOTPFrame'), 30)
	String url = WebUI.getUrl()
	WebUI.verifyMatch(url, 'https://api.checkout.com/.*', true)
	WebUI.switchToFrame(findTestObject('Object Repository/Helpdesk/Qasr/FE/credit/MainOTPFrame'), 10)
	WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/credit/CheckoutOTP'))
	WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/credit/CheckoutConfirm'))
	WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/credit/CheckoutOTP'), '0000')
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/credit/CheckoutConfirm'))
	WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/credit/WrongOTPErrorText'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/credit/CheckoutCancel'))
	if (WebUI.verifyAlertPresent(5,FailureHandling.OPTIONAL)) {
	    WebUI.delay(2)
		if (WebUI.verifyAlertPresent(5,FailureHandling.OPTIONAL)) {
			WebUI.acceptAlert()
		}else {
			WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/credit/CheckoutCancel'))
			if (WebUI.verifyAlertPresent(5,FailureHandling.OPTIONAL)) {
				WebUI.acceptAlert()
			}
		}
	}
	WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/ErrorAfterPayment_Tamara'), 20)
	url = WebUI.getUrl()
	WebUI.verifyMatch(url, GlobalVariable.FE_URL + '.*', true)
	WebElement orderDetailsElement = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/ErrorAfterPayment_Text_OrderDetails'), 10)
	def orderDetailsText = orderDetailsElement.getText()
	def orderNumber = orderDetailsText.findAll('\\d+').get(0)
	if(variableDeclation.loggedIn) {
		CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'), 3)
		WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Login'))
		if(!variableDeclation.isMobile) {
			WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/AccountPage/SignOut'))
		}else {
			CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/Qasr/FE/AccountPage/SignOut-Mobile'), 2)
		}
		if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/AppDownload_close'), 5)) {
			WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/AppDownload_close'))
		}
		variableDeclation.loggedIn=false
	}
	return orderNumber
}

String GetOrCreatePendingOrder(){
	String existingOrder =getOrderIfExists("Pending",true)
	if (existingOrder !=null) {
		return existingOrder
	}
	return ""
}

String GetOrCreateClosedOrder(){
	String existingOrder =getOrderIfExists("Closed",true)
	if (existingOrder !=null) {
		return existingOrder
	}
	return ""
}

String GetOrCreateProcessingOrder(){
	String existingOrder =getOrderIfExists("Processing",true)
	if (existingOrder !=null) {
		return existingOrder
	}
	return ""
}

String getRandomOrderForOtherUsers(){
	String existingOrder =getOrderIfExists("Canceled",false)
	if (existingOrder !=null) {
		return existingOrder
	}
	return ""
}

String getOrderIfExists(String status,boolean sameUser){
	int currentTab = WebUI.getWindowIndex()
	try {
		WebUI.switchToWindowIndex(currentTab + 1)
	}catch(Exception ex) {
		WebDriver driver = DriverFactory.getWebDriver()
		JavascriptExecutor js = ((driver) as JavascriptExecutor)
		js.executeScript('window.open();')
		WebUI.switchToWindowIndex(currentTab + 1)
		WebUI.navigateToUrl(GlobalVariable.BE_URL)
		if(!WebUI.waitForElementPresent(findTestObject('Object Repository/Helpdesk/Qasr/BE/Login/UserAdministrationDropDown'), 5)) {
			WebUI.setText(findTestObject('Object Repository/Helpdesk/Qasr/BE/Login/UserName'), GlobalVariable.BE_UserName)
			WebUI.setText(findTestObject('Object Repository/Helpdesk/Qasr/BE/Login/Password'), GlobalVariable.BE_Password)
			WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Login/LoginButton'))
		}
	}
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_Sales'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_Sales_orders'))
	if (WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/BE/Sales_Order_page/FilterClear'), FailureHandling.OPTIONAL)) {
		WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Sales_Order_page/FilterClear'))
	}
	WebUI.delay(2)
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Sales_Order_page/button_Filters'))
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/BE/Sales_Order_page/FilterStatus'), 2)
	WebUI.selectOptionByLabel(findTestObject('Object Repository/Helpdesk/Qasr/BE/Sales_Order_page/FilterStatus'), status, false)
	String xPathStr = ''
	if(sameUser) {
		WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/BE/Sales_Order_page/FilterMobileNumber'), "966" + variableDeclation.orderTel)
		xPathStr = '(//div[@class="data-grid-cell-content"])[1]'
	}else {
		xPathStr = '(//tr[contains(@class,"data-row")]/td[13]//div[@class="data-grid-cell-content" and not(text()="' + "966" + variableDeclation.orderTel + '")])[1]'
	}
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Sales_Order_page/Sales_orders_FilterButton'))
	
	TestObject orderByDesc =new TestObject()
	orderByDesc.addProperty("xpath", ConditionType.EQUALS, '//div[@data-role="grid-wrapper"]//span[text()="Purchase Date"]/parent::th[@class="data-grid-th _sortable _draggable _descend"]')
	if(!WebUI.waitForElementVisible(orderByDesc,20)) {
		println "Re-Order based on date"
		TestObject orderBy =new TestObject()
		orderBy.addProperty("xpath", ConditionType.EQUALS, '//div[@data-role="grid-wrapper"]//span[text()="Purchase Date"]/parent::th')
		WebUI.click(orderBy)
	}
	TestObject searchResultTO = new TestObject()
	searchResultTO.addProperty('xpath', ConditionType.EQUALS, xPathStr)
	List<WebElement> searchResultElm = WebUiCommonHelper.findWebElements(searchResultTO, 10)
	if (searchResultElm.size().equals(0)) {
		WebUI.switchToWindowIndex(currentTab)
		return null
	}
	println WebUI.getText(searchResultTO)
	searchResultElm.get(0).click()
	String orderNo = WebUI.getText(findTestObject('Object Repository/Helpdesk/Qasr/BE/Sales_Order_page/OrderHeaderNumber')).replace("#", "")
	variableDeclation.orderTel=WebUI.getText(findTestObject('Object Repository/Helpdesk/Qasr/BE/Sales_Order_page/OrderShippingAddress'))
			.findAll('T: \\d+').get(0).replace("T: 966", "")
	KeywordUtil.logInfo(variableDeclation.orderTel)
	String OrderDate = WebUI.getText(findTestObject('Object Repository/Helpdesk/Qasr/BE/Sales_Order_page/OrderDate'))
	Date date = Date.parse('MMM d, yyyy', OrderDate.substring(0,12) )
	String newDate = date.format( 'MM.dd.YYYY' )
	variableDeclation.orderDate=newDate
	
	String OrderTotalAmount = WebUI.getText(findTestObject('Object Repository/Helpdesk/Qasr/BE/Sales_Order_page/OrderTotalAmount'))
		.replace("SAR", "").replace(" ", "").replace(",", "")
	variableDeclation.orderTotalAmount=Float.parseFloat(OrderTotalAmount)
	
	String orderLastStatus = WebUI.getText(findTestObject('Object Repository/Helpdesk/Qasr/BE/Sales_Order_page/OrderStatus'))
	variableDeclation.orderLastStatus=orderLastStatus

	String orderShipmentAddress = WebUI.getText(findTestObject('Object Repository/Helpdesk/Qasr/BE/Sales_Order_page/OrderShippingAddress'))
	variableDeclation.orderShipmentAddress=orderShipmentAddress
	
	TestObject orderProductsTO = new TestObject()
	orderProductsTO.addProperty("xpath", ConditionType.EQUALS, '//div[@class="admin__table-wrapper"]/table/tbody')
	List<WebElement> orderProducts=WebUiCommonHelper.findWebElements(orderProductsTO, 5)
	orderProducts.each { 
		 HashMap<String, String> map = new HashMap<>();
		 map.put('title', it.findElement(By.xpath('.//div[@class="product-title"]')).getText())
		 map.put('sku', it.findElement(By.xpath('.//div[@class="product-sku-block"]')).getText().replace("SKU: ", ""))
		 map.put('qty', it.findElement(By.xpath('.//table[@class="qty-table"]//td')).getText())
		 variableDeclation.orderProductsSKS.add(map)
		 println variableDeclation.orderProductsSKS
	}
	
	String oderSubTotal = WebUI.getText(findTestObject('Object Repository/Helpdesk/Qasr/BE/Sales_Order_page/OrderSubTotalAmount'))
		.replace("SAR", "").replace(" ", "").replace(",", "")
	variableDeclation.oderSubTotal=Float.parseFloat(oderSubTotal)

	String orderShipmentAmount = WebUI.getText(findTestObject('Object Repository/Helpdesk/Qasr/BE/Sales_Order_page/OrderShippmentAmount'))
		.replace("SAR", "").replace(" ", "").replace(",", "")
	variableDeclation.orderShipmentAmount=Float.parseFloat(orderShipmentAmount)

	String orderTaxAmount = WebUI.getText(findTestObject('Object Repository/Helpdesk/Qasr/BE/Sales_Order_page/OrderTaxAmount'))
		.replace("SAR", "").replace(" ", "").replace(",", "")
	variableDeclation.orderTaxAmount=Float.parseFloat(orderTaxAmount)

	if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/BE/Sales_Order_page/OrderDiscountAmount'), 3)) {
		String orderDiscount = WebUI.getText(findTestObject('Object Repository/Helpdesk/Qasr/BE/Sales_Order_page/OrderDiscountAmount'))
			.replace("SAR", "").replace(" ", "").replace(",", "")
		variableDeclation.orderDiscount=Float.parseFloat(orderDiscount)
	}
	WebUI.switchToWindowIndex(currentTab)
	return orderNo
}

void VerifyOrderTrackingContent() {
	WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingBack'))
	WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderNumberHeader'))
	WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderDetails'))
	WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderStatusDetails'))
	WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderSubTotal'))
	WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderShipmentAmount'))
	WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderTotalAmount'))
	WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderTaxAmount'))
	WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderDiscountAmount'))
	
	String OrderNumberHeader = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderNumberHeader'), 5).getText()
	println OrderNumberHeader
	def displayedOrder = OrderNumberHeader.findAll('\\d+').get(0)
	WebUI.verifyMatch(displayedOrder, variableDeclation.currentOrder, false)
	
	String OrderDate = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderDetails'), 10)
		.findElement(By.xpath('//div[text()="تاريخ الطلب"]/following-sibling::div')).getText()
	WebUI.verifyMatch(OrderDate, variableDeclation.orderDate, false)
	
	String OrderAmount = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderDetails'), 10)
		.findElement(By.xpath('//div[text()="المبلغ"]/following-sibling::div')).getText().replace("ر.س", "").replace(" ", "").replace(",", "")
	OrderAmount=Float.parseFloat(OrderAmount).toString()
	WebUI.verifyMatch(OrderAmount, variableDeclation.orderTotalAmount.toString(), false)
	
	String orderLastStatus = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderDetails'), 10)
		.findElement(By.xpath('//div[text()="حالة الطلب"]/following-sibling::div')).getText()
		.replace("تم الغاء الطلب", "Canceled").replace("تحت المعالجة", "Processing").replace("قيد الانتظار", "Pending")
		.replace("pending_payment", "Pending Payment").replace("الطلب مكتمل", "Complete").replace("الطلب مغلق", "Closed")
	WebUI.verifyMatch(orderLastStatus, variableDeclation.orderLastStatus.toString(), false)
	
	String orderShipmentAddress = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderDetails'), 10)
		.findElement(By.xpath('//div[contains(@class,"shippingInformation-root-")]')).getText()
	Double distPercentage = StringUtils.getJaroWinklerDistance(orderShipmentAddress, variableDeclation.orderShipmentAddress)
	if(distPercentage>=0.5) {
		KeywordUtil.markPassed("Address matches the order shipment address with " + (distPercentage*100).toString() + "%")
	}else {
		KeywordUtil.markFailedAndStop("Address matches the order shipment address with " + (distPercentage*100).toString() + "% which less than 50%")
	}
	
	variableDeclation.orderProductsSKS.each { 
		TestObject titleTO=new TestObject()
		titleTO.addProperty("xpath", ConditionType.EQUALS, '//a[contains(@class,"orderDetails-imageTitle-") and contains(text(),"' + it.get("title").trim() + '")]')
		WebElement productTitleElement=WebUiCommonHelper.findWebElement(titleTO, 3) 
		WebUI.verifyElementVisible(titleTO)
		KeywordUtil.markPassed("found the title: " + productTitleElement.getText())
		
		WebElement productSKUElement = productTitleElement.findElement(
			By.xpath('.//../..//div[contains(@class,"orderDetails-imageRow-")]//span[contains(@class,"orderDetails-subImageText-") and text()="' + it.get("sku") + '"]'))
		WebUI.verifyMatch(productSKUElement.isDisplayed().toString(),"true",false)
		KeywordUtil.markPassed("found the sku: " + productSKUElement.getText())
		
		WebElement productQtyElement = productTitleElement.findElement(
			By.xpath('.//../..//div[contains(@class,"orderDetails-imageRow-")]//span[contains(@class,"orderDetails-subImageText-") and normalize-space(text())=normalize-space("' + it.get("qty") + '")]'))
//			By.xpath('.//../..//div[contains(@class,"orderDetails-imageRow-")]//span[contains(@class,"orderDetails-subImageText-") and text()="' + "1" + '"]'))
		WebUI.verifyMatch(productQtyElement.isDisplayed().toString(),"true",false)
		KeywordUtil.markPassed("found the sku: " + productQtyElement.getText())
	}
	
	String oderSubTotal = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderDetails'), 10)
		.findElement(By.xpath('//div[text()="الإجمالي:"]/following-sibling::div')).getText().replace("ر.س", "").replace(" ", "").replace(",", "")
	oderSubTotal=Float.parseFloat(oderSubTotal).toString()
	WebUI.verifyMatch(oderSubTotal, variableDeclation.oderSubTotal.toString(), false)

	String orderShipmentAmount = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderDetails'), 10)
		.findElement(By.xpath('//div[text()="الشحن (شامل الضريبة):"]/following-sibling::div')).getText().replace("ر.س", "").replace(" ", "").replace(",", "")
	orderShipmentAmount=Float.parseFloat(orderShipmentAmount).toString()
	WebUI.verifyMatch(orderShipmentAmount, variableDeclation.orderShipmentAmount.toString(), false)

	String orderTaxAmount = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderDetails'), 10)
		.findElement(By.xpath('//div[text()="ضريبة القيمة المضافة:"]/following-sibling::div')).getText().replace("ر.س", "").replace(" ", "").replace(",", "")
	orderTaxAmount=Float.parseFloat(orderTaxAmount).toString()
	WebUI.verifyMatch(orderTaxAmount, variableDeclation.orderTaxAmount.toString(), false)

	if(variableDeclation.orderDiscount !=null) {
		String orderDiscount = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/OrderTracking/OrderTrackingOrderDetails'), 10)
			.findElement(By.xpath('//div[text()="خصم:"]/following-sibling::div')).getText().replace("ر.س", "").replace(" ", "").replace(",", "")
			.replace("\n", "")
		orderDiscount=Float.parseFloat(orderDiscount).toString()
		WebUI.verifyMatch(orderDiscount, variableDeclation.orderDiscount.toString(), false)
	}

}

void clearVars() {
	variableDeclation.orderDiscount=null
	variableDeclation.currentOrder=""
	variableDeclation.orderProductsSKS=new ArrayList()
	variableDeclation.orderTel=GlobalVariable.FE_Tel.toString()
}
class variableDeclation{
	static String orderTel=GlobalVariable.FE_Tel.toString()
	static String cancelledOrder=""
	static String otherUsersOrder=""
	static String penddingOrder=""
	static String closedOrder=""
	static String processingOrder=""
	static String processingOrder_2=""
	static String cancelledOrder_2=""
	static String currentOrder=""
	static String orderDate=""
	static String orderLastStatus=""
	static List<HashMap<String, String>> orderProductsSKS=new ArrayList()
	static Float oderSubTotal=0
	static Float orderShipmentAmount=0
	static Float orderTotalAmount=0
	static Float orderTaxAmount=0
	static Float orderDiscount=null
	static String orderShipmentAddress=""
	static boolean loggedIn=false
	static boolean isMobile=false
}