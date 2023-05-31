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
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.SelectorMethod

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
import catalog.catlogComponants
import groovy.inspect.swingui.BytecodeCollector

import java.util.List as List
import java.util.concurrent.ConcurrentHashMap.KeySetView as KeySetView
import org.eclipse.jdt.internal.compiler.ast.ForeachStatement as ForeachStatement
import org.openqa.selenium.By
import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.html5.Location
import org.openqa.selenium.html5.LocationContext
import org.openqa.selenium.remote.server.handler.GetCurrentUrl as WebElement
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
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory


boolean isMobile=false


WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/Orange/SharedScripts/LaunchFE'), [:],	FailureHandling.STOP_ON_FAILURE)
if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Shared/BottomMenu-Mobile'), 10)) {
	isMobile=true
}else {
	isMobile=false
}

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/Orange/SharedScripts/Login'), [:],	FailureHandling.STOP_ON_FAILURE)

/////////////////////////
WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/Shared/Logo'))

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/Orange/SharedScripts/ClearProductsFromCartPage'), [:],	FailureHandling.STOP_ON_FAILURE)


WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/Orange/SharedScripts/OpenAndAddProductToCart'), [:],	FailureHandling.STOP_ON_FAILURE)


///// Save Data in variable gettext getSKU
def ProductTitle = WebUI.getText(findTestObject('Object Repository/Helpdesk/Orange/FE/Product/productFullDetail-Name'))
def ProductSKU = WebUI.getText(findTestObject('Object Repository/Helpdesk/Orange/FE/Product/productFullDetail-sku'))
def ProductURL = WebUI.getUrl().replace(GlobalVariable.FE_URL, "")

def ProductPrice = ""
if(WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/Orange/FE/Product/Product_Price'), 5,FailureHandling.OPTIONAL)) {
	ProductPrice=WebUI.getText(findTestObject('Object Repository/Helpdesk/Orange/FE/Product/Product_Price'))
}else {
	ProductPrice=WebUI.getText(findTestObject('Object Repository/Helpdesk/Orange/FE/Product/Product_Special_Price'))
}

//WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Shared/Logo'), 10)

//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Cart/Add to cart - Active'))
//WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/Cart/Add to cart - Active'))


WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Orange/FE/Cart/view cart'),10)
WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/Orange/FE/Cart/view cart'))

WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/Cart/view cart'))

if(!isMobile) {
	WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/MiniCart/MiniCartFrame'))
	WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/MiniCart/MiniCartShowCartButton'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/MiniCart/MiniCartShowCartButton'))
}


//Check Total Paid for Tabby and Tamara
TestObject totalPaidTO = new TestObject()

totalPaidTO.addProperty('xpath', ConditionType.EQUALS, '//span[text()="المجموع الكلي (شامل الضريبة)"]//following-sibling::span//div/span')

List<WebElement> totalPaidElements = WebUI.findWebElements(totalPaidTO, 10)

def totalText = ''

totalPaidElements.each({ 
        totalText += it.getText()
    })

totalText=totalText.replace("ر.س.", "").trim()
println(totalText)
def totalValue = totalText.toDouble()

if (totalValue < 99) {
    //increase the products
    def neededQty = ((Math.ceil(99 / totalValue)) as int)

    TestObject numberOfItems = new TestObject()

    numberOfItems.addProperty('xpath', ConditionType.EQUALS, '(//input[@aria-label="كمية العنصر"])[2]')

    WebElement numberOfItemsElement = WebUiCommonHelper.findWebElement(numberOfItems, 10)

//    numberOfItemsElement.sendKeys(Keys.chord(Keys.BACK_SPACE))
//
//    numberOfItemsElement.sendKeys(Keys.chord(Keys.DELETE))
//
//    println(neededQty.toString())
//
//    numberOfItemsElement.sendKeys(neededQty.toString())
//
//    numberOfItemsElement.sendKeys(Keys.chord(Keys.ENTER)) 
} else if (totalValue > 2500) {
	// To-Do Remove and add another product with less amount
}

WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Orange/FE/Cart/PriceSummaryButton'),10)
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Orange/FE/Cart/PriceSummaryButton'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/Cart/PriceSummaryButton'))
if(!WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/Step_4_PaymentMethods'),FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/Cart/PriceSummaryButton'))
}



WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/DeliverHere'),5)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/DeliverHere'),5)
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/DeliverHere'))
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/DeliverHere'), 5)

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/Step_3_ShipmentMethod'), 3)
//Steps
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/Step_4_PaymentMethods'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/Step_3_ShipmentMethod'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/Step_2_ShipmentLocation'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/Step_1_Login'))

//Shipment Methods
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/ShipmentMethodsList'))

def ShipmentMethodsList = findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/ShipmentMethodsList')

def ULXPath = ShipmentMethodsList.findPropertyValue('xpath')

def LiXPath = ULXPath + '//li'

TestObject temp = new TestObject()

temp.addProperty('xpath', ConditionType.EQUALS, LiXPath)

List<WebElement> Shipmentlist = WebUiCommonHelper.findWebElements(temp, 30)

if (Shipmentlist.size() != 1) {
	println (Shipmentlist.size())
    assert false
} else {
	CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/ShpmentMethod_1'))
    WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/ShpmentMethod_1'))

    WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/ShipmentMethod_1_FastShipmentText_1'))

//    WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/ShipmentMethod_1_FastShipmentText_2'))

    WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/ShipmentMethod_1_FastShipmentText_3'))
}

//We should check if shipment fee is 0 when total paid is more than x and 20 if less than x


// To-Do DeliveryTime Checking
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/deliveryTime-root-Div'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/deliveryTime-topHeader-text_1'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/deliveryTime-topHeader-text_2'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/deliveryDays-headerList'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/deliveryTime-headerList'))



TestObject availableDates = new TestObject()
availableDates.addProperty("xpath",ConditionType.EQUALS,findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/deliveryDays-headerList').getSelectorCollection().get(SelectorMethod.XPATH) + "//li")
List<WebElement> availableDatesElements = WebUiCommonHelper.findWebElements(availableDates, 30)
WebUI.verifyEqual(availableDatesElements.size(), 7)

//availableDatesElements.get(0).click()

TestObject availableTime = new TestObject()
availableTime.addProperty("xpath",ConditionType.EQUALS,findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/deliveryTime-headerList').getSelectorCollection().get(SelectorMethod.XPATH) + '//li[contains(@class,"deliveryTime-item-")]')
List<WebElement> availableTimesElements = WebUiCommonHelper.findWebElements(availableTime, 30)
WebUI.verifyEqual(availableTimesElements.size(), 8)

TestObject availableActiveTime = new TestObject()
availableActiveTime.addProperty("xpath",ConditionType.EQUALS,findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/deliveryTime-headerList').getSelectorCollection().get(SelectorMethod.XPATH) + '//li[contains(@class,"deliveryTime-item-") and not(contains(@class,"deliveryTime-disableBtn-"))]')
List<WebElement> availableActiveTimesElements = WebUiCommonHelper.findWebElements(availableActiveTime, 30)



//WebUI.scrollToElement(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/ShpmentMethod_1'), 10)
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/ShpmentMethod_1'))
TestObject firstActiveTime = new TestObject()
firstActiveTime.addProperty("xpath",ConditionType.EQUALS,'(' + findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/deliveryTime-headerList').getSelectorCollection().get(SelectorMethod.XPATH) + '//li[contains(@class,"deliveryTime-item-") and not(contains(@class,"deliveryTime-disableBtn-"))])[1]')
WebElement firstAvailableActiveTimesElements = WebUiCommonHelper.findWebElement(firstActiveTime, 30)
WebUI.waitForElementClickable(firstActiveTime, 10)
//firstAvailableActiveTimesElements.click()
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(firstAvailableActiveTimesElements)
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(firstAvailableActiveTimesElements, 3)



//PaymentMethods
TestObject paymentPath = new TestObject()

paymentPath.addProperty('xpath', ConditionType.EQUALS, '//div[contains(@class,"checkoutPage-paymentMethod-")]/div[not(@*)]//div[contains(@class,"paymentMethod-item-")]')

List Paymentlist = WebUiCommonHelper.findWebElements(paymentPath, 30)

if (Paymentlist.size() != 4) {
 	println (Paymentlist.size())
   assert false
} else {
	CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/PaymentMethod_1_Text'))
    WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/PaymentMethod_1_Text'))

    WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/PaymentMethod_2_Text'))

    WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/PaymentMethod_3_Text'))

	WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/PaymentMethod_4_Text'))
}

//Order with Tabby

TestObject tabbyObj = new TestObject()
tabbyObj.addProperty("xpath",ConditionType.EQUALS,'//*[contains(text(),"tabby")]')
if(WebUI.verifyElementPresent(tabbyObj,5, FailureHandling.OPTIONAL)) {
	assert false
}
//WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/PaymentMethod_1_Text'))
//WebUI.scrollToElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/Step_1_Login'), 5)
//
////WebElement element = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/FinishPayment'), 10)
////WebUI.scrollToPosition(0, element.getLocation().getY())
//
//
//TestObject newTotalPaidTO = new TestObject()
//
//newTotalPaidTO.addProperty('xpath', ConditionType.EQUALS, '//span[text()="الإجمالي الكلي"]//following-sibling::span//div/span')
//
//List newTotalPaidElements = WebUI.findWebElements(newTotalPaidTO, 10)
//
//def newTotalText = ''
//
//newTotalPaidElements.each({ 
//        newTotalText += it.getText()
//    })
//
//println(newTotalText)
//
//WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/FinishPayment'))
//
//WebUI.waitForPageLoad(20)
//
//WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Tabby/OTPFirstDigit_Tabby'), 20)
//
//String url = WebUI.getUrl()
//
//WebUI.verifyMatch(url, 'https://checkout.tabby.ai/.*', true)
//
//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Tabby/BackToStore'))
//
//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Tabby/OTPFirstDigit_Tabby'))
//
//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Tabby/AmountText'))
//
//TestObject TabbyRequiredPaymentTO = new TestObject()
//
////TabbyRequiredPaymentTO.addProperty('xpath', ConditionType.EQUALS, '//div[@data-test="order.amount"]')
//TabbyRequiredPaymentTO.addProperty('xpath', ConditionType.EQUALS, '//div[text()="قيمة الطلب"]//following-sibling::div')
//
//WebElement TabbyRequiredPaymentElement = WebUI.findWebElement(TabbyRequiredPaymentTO, 10)
//
//def TabbyRequiredPaymentValue = TabbyRequiredPaymentElement.getText().replace(' SAR', '')
//
//println(TabbyRequiredPaymentValue)
//
//WebUI.verifyEqual(TabbyRequiredPaymentValue, newTotalText)
//
//WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Tabby/BackToStore'), 10)
//
//WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Tabby/BackToStore'))
//
//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Tabby/ReturnDialog_Text'))
//
//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Tabby/ReturnDialog_ReturnToStoreBtn'))
//
//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Tabby/ReturnDialog_ContinueWithTabbyBtn'))
//
//WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Tabby/ReturnDialog_ReturnToStoreBtn'))
//
//WebUI.waitForElementPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/ErrorAfterPayment_Tamara'), 20)
//
//url = WebUI.getUrl()
//
//WebUI.verifyMatch(url, GlobalVariable.FE_URL + '.*', true)
//
//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/ErrorAfterPayment_Text_ThankYou'))
//
//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/ErrorAfterPayment_Text_OrderDetails'))
//
//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/ErrorAfterPayment_Tamara'))
//
//WebElement orderDetailsElement = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/ErrorAfterPayment_Text_OrderDetails'), 
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
//WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_Sales'))
//
//WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_Sales_orders'))
//
//if (WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/BE/Sales_Order_page/FilterClear'), FailureHandling.OPTIONAL)) {
//    WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Sales_Order_page/FilterClear'))
//}
//
//WebUI.delay(2)
//
//WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Sales_Order_page/button_Filters'))
//
//WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/BE/Sales_Order_page/Filter_ID_FROM'), 2)
//
//WebUI.setText(findTestObject('Object Repository/Helpdesk/Qasr/BE/Sales_Order_page/Filter_increment_id'), orderNumber)
//
//WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Sales_Order_page/Sales_orders_FilterButton'))
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
//		WebUI.verifyElementText(findTestObject('Object Repository/Helpdesk/Qasr/BE/Sales_Order_page/OrderStatus'), 'Canceled')
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

