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

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/TheBeautySecrets/KSA/SharedScripts/LaunchFE'), [:],	FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/TheBeautySecrets/KSA/SharedScripts/Login'), [:],	FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/TheBeautySecrets/KSA/SharedScripts/ClearProductsFromCartPage'), [:],	FailureHandling.STOP_ON_FAILURE)

if(WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/Logo'),5,FailureHandling.OPTIONAL)){
	WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/Logo'),FailureHandling.OPTIONAL)
}

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/TheBeautySecrets/KSA/SharedScripts/OpenAndAddProductToCart'), [:],	FailureHandling.STOP_ON_FAILURE)
/////////////////////////
//WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Product/MoreDetailsPlus'))
def ProductTitle = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Product/productFullDetail-Name'), 5).getText().split("\n")[0]
def ProductSKU = WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Product/productFullDetail-sku')).replace(":", "").replace(" ", "")
def ProductURL = CustomKeywords.'helpdesk.HelpdeskUtil.decodeEncodedValue'(WebUI.getUrl()) //.replace(GlobalVariable.FE_URL, "")
def ProductPrice = ""

if(WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Product/SpecialPrice'), 2,FailureHandling.OPTIONAL)) {
	ProductPrice = WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Product/SpecialPrice')).replace("٫", ".").replace("ر.س.", "")
}else {
	ProductPrice = WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Product/Product_Price')).replace("٫", ".").replace("ر.س.", "")
}

println ProductTitle
println ProductSKU
println ProductPrice
println ProductURL

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/Cart'),10)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/Cart'),10)
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/Cart'), 10)
if(WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/ViewCartMainPageBtn'), 5,FailureHandling.OPTIONAL)) {
	WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/ViewCartMainPageBtn'),10)
}else {
	WebUI.refresh()
	WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/Cart'),10)
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/Cart'),10)
	CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/Cart'), 10)
}
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/ViewCartMainPageBtn'),10)
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/ViewCartMainPageBtn'),10)


//Check Total Paid for Tabby and Tamara
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/totalNeededPay'), 10)
String totalText=WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/totalNeededPay'))
println(totalText)
totalText =totalText.replace(" ر.س.", "").replace(" ", "").replace("٫", ".")
println(totalText)
Float totalValue = totalText.toFloat()
println(totalValue)

if (totalValue < 150) {
    //increase the products
    int neededQty = ((Math.ceil(150 / totalValue)) as int)
	CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/QTYProduct'))
	WebUI.focus(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/QTYProduct'))
	WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/QTYProduct'), Keys.chord(Keys.BACK_SPACE))
	WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/QTYProduct'), Keys.chord(Keys.DELETE))
	WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/QTYProduct'), neededQty.toString())
	WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/RefreshCart'))
	CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/RefreshCart'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/RefreshCart'))
    //remove and select another product (need to remove and seach again)
} else if (totalValue > 2500) {
	// To-Do Remove and add another product with less amount
}

WebUI.delay(3)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/PriceSummaryButton'),10)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/PriceSummaryButton'),10)
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/PriceSummaryButton'))
WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/PriceSummaryButton'))

if(!WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/Step_4_PaymentMethods'),FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/PriceSummaryButton'))
}




//Steps
WebUI.waitForPageLoad(20)
WebUI.waitForElementNotVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/LoadingImg'), 20)
//WebUI.verifyElementNotVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/MapErrorCannotLoad'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/Step_4_PaymentMethods'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/Step_3_ShipmentMethod'))
//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/Step_2_ShipmentLocation'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/Step_1_Login'))


if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShippedToForm'), 10)) {
	WebElement FullName = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShippedToForm-FullName'),5)
	WebUI.verifyMatch(FullName.getAttribute('value'), GlobalVariable.CustomerName, false)

	WebElement Phone = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShippedToForm-Phone'),5)
	WebUI.verifyMatch(Phone.getAttribute('value'), GlobalVariable.FE_Tel, false)
	
	WebUI.verifyOptionsPresent(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShippedToForm-Country')
		,['الإمارات العربية المتحدة', 'الكويت', 'المملكة العربية السعودية'])
	WebUI.selectOptionByLabel(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShippedToForm-City'), 'الرياض', false)
	WebUI.waitForElementNotVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/LoadingImg'), 3)
	
	WebUI.setText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShippedToForm-Street'), 'شارع العليا، العليا، الرياض 12214، المملكة العربية السعودية')
	WebUI.waitForElementNotVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/LoadingImg'), 3)
	
	WebUI.setText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShippedToForm-Neighbourhood'), 'العليا')
	WebUI.waitForElementNotVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/LoadingImg'), 3)
	
	WebUI.setText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShippedToForm-HomePhone'),GlobalVariable.FE_Tel)
	WebUI.waitForElementNotVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/LoadingImg'), 3)
	
	CustomKeywords.'helpdesk.HelpdeskUtil.uncheckUsingJS'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShippedToForm-SaveAddressCheckbox'),3)
	WebUI.waitForElementNotVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/LoadingImg'), 3)
}
//We should check if shipment fee is 0 when total paid is more than x and 20 if less than x
//PaymentMethods
TestObject paymentPath = new TestObject()
paymentPath.addProperty('xpath', ConditionType.EQUALS, '//div[@data-role="payment-methods-load"]/div[contains(@data-bind,"_active")]')
WebUI.waitForElementVisible(paymentPath,10)
List Paymentlist = WebUiCommonHelper.findWebElements(paymentPath, 30)

CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(paymentPath)
if (Paymentlist.size() != 3) {
	KeywordUtil.markWarning("Expected 3 payments methods but was: " + Paymentlist.size().toString())
} else {
	// COD
	WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/PaymentMethod_1_Text'),FailureHandling.CONTINUE_ON_FAILURE)
	// Credit
	WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/PaymentMethod_2_Text'),FailureHandling.CONTINUE_ON_FAILURE)
	// Tamara
	WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/PaymentMethod_3_Text'),FailureHandling.CONTINUE_ON_FAILURE)
}

//Order with Tamara
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/PaymentMethod_3_Text'))
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/PaymentMethod_3_Text'))
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/PaymentMethod_3_Text'),3)
//WebElement element = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/FinishPayment'), 10)
//WebUI.scrollToPosition(0, element.getLocation().getY())

WebUI.scrollToElement(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/Step_1_Login'), 5)
WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/FinishPayment'))

WebUI.waitForPageLoad(20)

WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Tamara/TamaraProceed'), 20)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Tamara/TamaraProceed'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Tamara/PrependPhone'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Tamara/PhoneLogin'))

String url = WebUI.getUrl()

WebUI.verifyMatch(url, 'https://checkout.tamara.co/.*', true)

WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Tamara/TamaraProceed'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Tamara/EnterOTPText_1'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Tamara/EnterOTPText_2'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Tamara/OTPFirstDigit'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Tamara/TamaraProceedButtonPage2'))

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Tamara/TamaraCancel'))

//WebUI.verifyElementNotClickable(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Tamara/TamaraProceedButtonPage2'))
WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Tamara/TamaraCancel'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Tamara/CancleDialogText_1'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Tamara/CancleDialogReturnBtn'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Tamara/CancleDialogContinueBtn'))

WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Tamara/CancleDialogReturnBtn'))


WebUI.delay(5)
WebUI.waitForPageLoad(10)
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Tamara/TamaraPaymentError'))
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Tamara/TamaraPaymentError'), 20)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Tamara/TamaraPaymentError'))

url = WebUI.getUrl()

WebUI.verifyMatch(url, GlobalVariable.FE_URL + '.*', true)


//WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ErrorAfterPayment_Tamara'), 20)
//
//url = WebUI.getUrl()
//
//WebUI.verifyMatch(url, GlobalVariable.FE_URL + '.*', true)
//
//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ErrorAfterPayment_Text_ThankYou'))
//
//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ErrorAfterPayment_Text_OrderDetails'))
//
//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ErrorAfterPayment_Tamara'))
//
//WebElement orderDetailsElement = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ErrorAfterPayment_Text_OrderDetails'), 
//    10)
//
//def orderDetailsText = orderDetailsElement.getText()
//
//def orderNumber = orderDetailsText.findAll('\\d+').get(0)
//
////Check the order
//WebUI.delay(2)
//
//WebUI.switchToWindowIndex(currentTab + 1)
//
//WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/Menu/Menu_Sales'))
//
//WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/Menu/Menu_Sales_orders'))
//
//if (WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/Sales_Order_page/FilterClear'), FailureHandling.OPTIONAL)) {
//    WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/Sales_Order_page/FilterClear'))
//}
//
//WebUI.delay(2)
//
//WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/Sales_Order_page/button_Filters'))
//
//WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/Sales_Order_page/Filter_ID_FROM'), 2)
//
//WebUI.setText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/Sales_Order_page/Filter_increment_id'), orderNumber)
//
//WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/Sales_Order_page/Sales_orders_FilterButton'))
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
//        WebUI.verifyElementText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/BE/Sales_Order_page/OrderStatus'), 'Canceled')
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