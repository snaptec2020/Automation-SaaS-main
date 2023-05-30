import com.kms.katalon.core.model.FailureHandling

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
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
import com.kms.katalon.core.testobject.ConditionType as ConditionType
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
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/AlJedaie/SharedScripts/LaunchFE'), [:],	FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/AlJedaie/SharedScripts/Login'), [:],	FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/AlJedaie/SharedScripts/ClearProductsFromCartPage'), [:],	FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/AlJedaie/SharedScripts/ClickLogo'), [:],	FailureHandling.STOP_ON_FAILURE)


WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/AlJedaie/SharedScripts/OpenAndAddProductToCart'), [:],	FailureHandling.STOP_ON_FAILURE)
/////////////////////////
def ProductTitle = WebUI.getText(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Product/productFullDetail-Name'))
def ProductSKU = WebUI.getText(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Product/productFullDetail-sku'))
def ProductURL = WebUI.getUrl() //.replace(GlobalVariable.FE_URL, "")
def ProductPrice = WebUI.getText(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Product/Product_Price'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Shared/Cart'),10)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Shared/Cart'),10)
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Shared/Cart'), 10)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Cart/ViewCartMainPageBtn'),10)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Cart/ViewCartMainPageBtn'),10)
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Cart/ViewCartMainPageBtn'),10)


//Check Total Paid for Tabby and Tamara
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Cart/totalNeededPay'), 10)
String totalText=WebUI.getText(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Cart/totalNeededPay'))
println(totalText)
totalText =totalText.replace(" ", "").replace("٫", ".").replace("ر.س", "")
Float totalValue = totalText.toFloat()
println(totalValue)

if (totalValue < 99) {
    //increase the products
    int neededQty = ((Math.ceil(99 / totalValue)) as int)
	CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Cart/QtyDropdown'), 5)
	WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Cart/QtyDropdownList'),2)
//	WebUI.selectOptionByIndex(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Cart/QtyDropdownList'),neededQty)
	TestObject Option = new TestObject()
	Option.addProperty("xpath",ConditionType.EQUALS,findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Cart/QtyDropdownList').findPropertyValue("xpath") + '/li[@data-value="' + neededQty.toString() + '"]')
	if(WebUI.verifyElementPresent(Option, 3)) {
		CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(Option,10)
	}else {
		Option.addProperty("xpath",ConditionType.EQUALS,findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Cart/QtyDropdownList').findPropertyValue("xpath") + '/li[@data-value="22"]')
		if(WebUI.verifyElementPresent(Option, 3)) {
			CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(Option,10)
		}
	}
	WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Cart/RefreshCart'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Cart/RefreshCart'))
    //remove and select another product (need to remove and seach again)
} else if (totalValue > 2500) {
	// To-Do Remove and add another product with less amount
}

WebUI.delay(3)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Cart/PriceSummaryButton'),10)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Cart/PriceSummaryButton'),10)
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Cart/PriceSummaryButton'))
WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Cart/PriceSummaryButton'))

if(!WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Checkout/Step_4_PaymentMethods'),FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Cart/PriceSummaryButton'))
}




//Steps
WebUI.waitForPageLoad(20)
WebUI.waitForElementNotVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Shared/LoadingImg'), 10)
//WebUI.verifyElementNotVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Checkout/MapErrorCannotLoad'))
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Checkout/Step_4_PaymentMethods'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Checkout/Step_4_PaymentMethods'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Checkout/Step_3_ShipmentMethod'))
//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Checkout/Step_2_ShipmentLocation'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Checkout/Step_1_Login'))

//We should check if shipment fee is 0 when total paid is more than x and 20 if less than x
//PaymentMethods
TestObject paymentPath = new TestObject()
paymentPath.addProperty('xpath', ConditionType.EQUALS, '//div[@data-role="payment-methods-load"]/div[contains(@data-bind,"_active")]')
WebUI.waitForElementVisible(paymentPath,10)
List Paymentlist = WebUiCommonHelper.findWebElements(paymentPath, 30)

if (Paymentlist.size() != 2) {
 	println (Paymentlist.size())
   assert false
} else {
	// Tamara
	CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Checkout/PaymentMethod_1_Text'))
    WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Checkout/PaymentMethod_1_Text'))
	// Credit
	CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Checkout/PaymentMethod_2_Text'))
    WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Checkout/PaymentMethod_2_Text'))
}


//Order with Credit 
WebUI.scrollToElement(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Checkout/Step_4_PaymentMethods'), 5)
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Checkout/PaymentMethod_2_Text'),3)

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/credit/MainForm'), 10)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/credit/MainForm'))

WebUI.switchToFrame(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/credit/CardNumberInputFrame'), 10)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/credit/CardNumberInput'))

//WebUI.switchToDefaultContent()
//
//WebUI.switchToFrame(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/credit/CardExpiryFrame'), 10)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/credit/CardExpiryInput'))

//WebUI.switchToDefaultContent()
//
//WebUI.switchToFrame(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/credit/CardCVVFrame'), 10)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/credit/CardCVVInput'))

//WebUI.switchToDefaultContent()
//
//WebUI.switchToFrame(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/credit/CardNumberInputFrame'), 10)

WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/credit/CardNumberInput'), GlobalVariable.MadaCardNum)

//WebUI.switchToDefaultContent()
//
//WebUI.switchToFrame(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/credit/CardExpiryFrame'), 10)

WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/credit/CardExpiryInput'), GlobalVariable.MadaExpiryDate)

//WebUI.switchToDefaultContent()
//
//WebUI.switchToFrame(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/credit/CardCVVFrame'), 10)

WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/credit/CardCVVInput'), GlobalVariable.MadaCVV)

WebUI.switchToDefaultContent()

WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Checkout/FinishPayment'))

WebUI.waitForPageLoad(10)

WebUI.delay(20)

WebUI.waitForElementPresent(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/credit/MainOTPFrame'), 30)

String url = WebUI.getUrl()

if (!WebUI.verifyMatch(url, 'https://api.checkout.com/.*', true,FailureHandling.OPTIONAL)) {
	CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Checkout/FinishPayment'), 2)
	WebUI.waitForPageLoad(10)
	WebUI.delay(20)
	WebUI.waitForElementPresent(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/credit/MainOTPFrame'), 30)
	url = WebUI.getUrl()
	WebUI.verifyMatch(url, 'https://api.checkout.com/.*', true)
}



WebUI.switchToFrame(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/credit/MainOTPFrame'), 10)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/credit/CheckoutOTP'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/credit/CheckoutConfirm'))

WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/credit/CheckoutOTP'), '0000')

WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/credit/CheckoutConfirm'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/credit/WrongOTPErrorText'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/credit/CheckoutCancel'))
//WebUI.delay(3)
//WebUI.waitForAlert(3)
//WebUI.acceptAlert()
////////////////////////////////////////////////////////////////////////
if (WebUI.verifyAlertPresent(5,FailureHandling.OPTIONAL)) {
    WebUI.delay(2)
	if (WebUI.verifyAlertPresent(5,FailureHandling.OPTIONAL)) {
		WebUI.acceptAlert()
	}else {
		WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/credit/CheckoutCancel'))
		if (WebUI.verifyAlertPresent(5,FailureHandling.OPTIONAL)) {
			WebUI.acceptAlert()
		}
	}
}

WebUI.delay(5)
WebUI.waitForPageLoad(10)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/credit/CreditPaymentError'), 20)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/credit/CreditPaymentError'))

url = WebUI.getUrl()

WebUI.verifyMatch(url, GlobalVariable.FE_URL + '.*', true)
//
//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Checkout/ErrorAfterPayment_Text_ThankYou'))
//
//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Checkout/ErrorAfterPayment_Text_OrderDetails'))
//
//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Checkout/ErrorAfterPayment_Tamara'))
//
//WebElement orderDetailsElement = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Checkout/ErrorAfterPayment_Text_OrderDetails'), 
//    10)
//
//def orderDetailsText = orderDetailsElement.getText()
//
//def orderNumber = orderDetailsText.findAll('\\d+').get(0)

////Check the order
//WebUI.delay(2)
//
//WebUI.switchToWindowIndex(currentTab + 1)
//
//WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/Menu/Menu_Sales'))
//
//WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/Menu/Menu_Sales_orders'))
//
//if (WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/Sales_Order_page/FilterClear'), FailureHandling.OPTIONAL)) {
//    WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/Sales_Order_page/FilterClear'))
//}
//
//WebUI.delay(2)
//
//WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/Sales_Order_page/button_Filters'))
//
//WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/Sales_Order_page/Filter_ID_FROM'), 2)
//
//WebUI.setText(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/Sales_Order_page/Filter_increment_id'), orderNumber)
//
//WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/Sales_Order_page/Sales_orders_FilterButton'))
//
//TestObject searchResultTO = new TestObject()
//
//searchResultTO.addProperty('xpath', ConditionType.EQUALS, ('//div[@class="data-grid-cell-content" and text()="' + orderNumber) + 
//    '"]')
//
//List searchResultElm = WebUiCommonHelper.findWebElements(searchResultTO, 10)
//
//if (searchResultElm.size().equals(1)) {
//    searchResultElm.get(0).click()
//
//    TestObject OrderHeaderTO = new TestObject()
//
//    OrderHeaderTO.addProperty('xpath', ConditionType.EQUALS, '//div[@class="page-header-hgroup col-l-8 col-m-6"]//div[@class="page-title-wrapper"]//h1[@class="page-title"]')
//
//    WebElement OrderHeaderElem = WebUiCommonHelper.findWebElement(OrderHeaderTO, 30)
//
//    def OrderHeaderText = OrderHeaderElem.getText()
//
//    if (OrderHeaderText.equals('#' + orderNumber)) {
//        WebUI.verifyElementText(findTestObject('Object Repository/Helpdesk/AlJedaie/BE/Sales_Order_page/OrderStatus'), 'Canceled')
//
//        TestObject CustomerNameTO = new TestObject()
//
//        CustomerNameTO.addProperty('xpath', ConditionType.EQUALS, ('//a[@target="_blank"]//span[text()="' + GlobalVariable.CustomerName) + 
//            '"]')
//
//        WebElement CustomerNameElem = WebUiCommonHelper.findWebElement(CustomerNameTO, 30)
//
//        if (!(CustomerNameElem.getText().equals(GlobalVariable.CustomerName))) {
//            assert false
//        }
//    }
//}

WebUI.delay(5)

WebUI.closeBrowser()