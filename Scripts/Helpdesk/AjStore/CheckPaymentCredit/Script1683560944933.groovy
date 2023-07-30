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

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/AjStore/SharedScripts/LaunchFE'), [:],	FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/AjStore/SharedScripts/Login'), [:],	FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/AjStore/SharedScripts/ClearProductsFromCartPage'), [:],	FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/FE/Shared/Logo2'))
WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/AjStore/SharedScripts/OpenAndAddProductToCart'), [:],	FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/FE/Cart/view cart'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/FE/Cart/view cart'))

//Check Total Paid for Tabby and Tamara
TestObject totalPaidTO = new TestObject()

totalPaidTO.addProperty('xpath', ConditionType.EQUALS, '//span[text()="المجموع الكلي (شامل الضريبة)"]//following-sibling::span//div/span')

List<WebElement> totalPaidElements = WebUI.findWebElements(totalPaidTO, 10)

def totalText = ''

totalPaidElements.each({ 
        totalText += it.getText()
    })

//println(totalValue)
def totalValue = totalText.toDouble()

if (totalValue < 99) {
    //increase the products
    def neededQty = ((Math.ceil(99 / totalValue)) as int)

    TestObject numberOfItems = new TestObject()

    numberOfItems.addProperty('xpath', ConditionType.EQUALS, '//input[@aria-label="كمية المنتج"]')

    WebElement numberOfItemsElement = WebUiCommonHelper.findWebElement(numberOfItems, 10)

    numberOfItemsElement.sendKeys(Keys.chord(Keys.BACK_SPACE))

    numberOfItemsElement.sendKeys(Keys.chord(Keys.DELETE))

    println(neededQty.toString())

    numberOfItemsElement.sendKeys(neededQty.toString())

    numberOfItemsElement.sendKeys(Keys.chord(Keys.ENTER)) //   
    //remove and select another product (need to remove and seach again)
} else if (totalValue > 2500) {
	// To-Do Remove and add another product with less amount
}

WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/FE/Cart/PriceSummaryButton'),10)
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/AjStore/FE/Cart/PriceSummaryButton'))
WebUI.delay(1)
WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/FE/Cart/PriceSummaryButton'))
if(!WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/MapOnCheckout'),FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/FE/Cart/PriceSummaryButton'))
}

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/MapOnCheckout'))

//WebUI.verifyElementNotVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/MapErrorCannotLoad'))
//Steps
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/Step_4_PaymentMethods'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/Step_3_ShipmentMethod'),10)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/Step_3_ShipmentMethod'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/Step_3_ShipmentMethod'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/Step_2_ShipmentLocation'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/Step_1_Login'))

//Shipment Methods
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/ShipmentMethodsList'),10)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/ShipmentMethodsList'))

def ShipmentMethodsList = findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/ShipmentMethodsList')

def ULXPath = ShipmentMethodsList.findPropertyValue('xpath')

def LiXPath = ULXPath + '//li'

TestObject temp = new TestObject()

temp.addProperty('xpath', ConditionType.EQUALS, LiXPath)

List Shipmentlist = WebUiCommonHelper.findWebElements(temp, 30)

if (Shipmentlist.size() != 1) {
    assert false
} else {
    WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/ShpmentMethod_1'))

    WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/ShipmentMethod_1_FastShipmentText_1'))

    WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/ShipmentMethod_1_FastShipmentText_2'))

    WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/ShipmentMethod_1_FastShipmentText_3'))
}

//We should check if shipment fee is 0 when total paid is more than x and 20 if less than x
//PaymentMethods
TestObject paymentPath = new TestObject()

paymentPath.addProperty('xpath', ConditionType.EQUALS, '//div[contains(@class,"checkoutPage-paymentMethod-")]/div[not(@*)]//div[contains(@class,"paymentMethod-item-")]')

List Paymentlist = WebUiCommonHelper.findWebElements(paymentPath, 30)

if (Paymentlist.size() != 4) {
    KeywordUtil.markWarning("Expected 4 payments methods but was: " + Paymentlist.size().toString())
} else {
    WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/PaymentMethod_1_Text'),FailureHandling.CONTINUE_ON_FAILURE)

    WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/PaymentMethod_2_Text'),FailureHandling.CONTINUE_ON_FAILURE)

    WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/PaymentMethod_3_Text'),FailureHandling.CONTINUE_ON_FAILURE)

    WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/PaymentMethod_4_Text'),FailureHandling.CONTINUE_ON_FAILURE)
}

//Order with Credit 
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/PaymentMethod_3_Text'))
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/PaymentMethod_3_Text'))
WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/PaymentMethod_3_Text'))

CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/FinishPayment'))
WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/FinishPayment'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/credit/MainForm'), 10)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/credit/MainForm'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/credit/CardNumberInputFrame'))

WebUI.switchToFrame(findTestObject('Object Repository/Helpdesk/AjStore/FE/credit/CardNumberInputFrame'), 10)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/credit/CardNumberInput'))

WebUI.switchToDefaultContent()

WebUI.switchToFrame(findTestObject('Object Repository/Helpdesk/AjStore/FE/credit/CardExpiryFrame'), 10)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/credit/CardExpiryInput'))

WebUI.switchToDefaultContent()

WebUI.switchToFrame(findTestObject('Object Repository/Helpdesk/AjStore/FE/credit/CardCVVFrame'), 10)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/credit/CardCVVInput'))

WebUI.switchToDefaultContent()

WebUI.switchToFrame(findTestObject('Object Repository/Helpdesk/AjStore/FE/credit/CardNumberInputFrame'), 10)

WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AjStore/FE/credit/CardNumberInput'), GlobalVariable.MadaCardNum)

WebUI.switchToDefaultContent()

WebUI.switchToFrame(findTestObject('Object Repository/Helpdesk/AjStore/FE/credit/CardExpiryFrame'), 10)

WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AjStore/FE/credit/CardExpiryInput'), GlobalVariable.MadaExpiryDate)

WebUI.switchToDefaultContent()

WebUI.switchToFrame(findTestObject('Object Repository/Helpdesk/AjStore/FE/credit/CardCVVFrame'), 10)

WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AjStore/FE/credit/CardCVVInput'), GlobalVariable.MadaCVV)

WebUI.switchToDefaultContent()

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/FE/credit/CreditSubmit'))

WebUI.waitForPageLoad(10)

WebUI.delay(20)

WebUI.waitForElementPresent(findTestObject('Object Repository/Helpdesk/AjStore/FE/credit/MainOTPFrame'), 30)

String url = WebUI.getUrl()

WebUI.verifyMatch(url, 'https://api.checkout.com/.*', true)

WebUI.switchToFrame(findTestObject('Object Repository/Helpdesk/AjStore/FE/credit/MainOTPFrame'), 10)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/credit/CheckoutOTP'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/credit/CheckoutConfirm'))

WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AjStore/FE/credit/CheckoutOTP'), '0000')

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/FE/credit/CheckoutConfirm'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/credit/WrongOTPErrorText'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/FE/credit/CheckoutCancel'))
//WebUI.delay(3)
//WebUI.waitForAlert(3)
//WebUI.acceptAlert()
////////////////////////////////////////////////////////////////////////
if (WebUI.verifyAlertPresent(5,FailureHandling.OPTIONAL)) {
    WebUI.delay(2)
	if (WebUI.verifyAlertPresent(5,FailureHandling.OPTIONAL)) {
		WebUI.acceptAlert()
	}else {
		WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/FE/credit/CheckoutCancel'))
		if (WebUI.verifyAlertPresent(5,FailureHandling.OPTIONAL)) {
			WebUI.acceptAlert()
		}
	}
}

WebUI.delay(5)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/ErrorAfterPayment_Tamara'), 20)

url = WebUI.getUrl()

WebUI.verifyMatch(url, GlobalVariable.FE_URL + '.*', true)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/ErrorAfterPayment_Text_ThankYou'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/ErrorAfterPayment_Text_OrderDetails'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/ErrorAfterPayment_Tamara'))

WebElement orderDetailsElement = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/AjStore/FE/Checkout/ErrorAfterPayment_Text_OrderDetails'), 
    10)

def orderDetailsText = orderDetailsElement.getText()

def orderNumber = orderDetailsText.findAll('\\d+').get(0)

//Check the order
WebUI.delay(2)
int currentTab = WebUI.getWindowIndex()

WebUI.switchToWindowIndex(currentTab + 1)

WebUI.switchToWindowIndex(currentTab + 1)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/BE/Menu/Menu_Sales'), 5)
WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/BE/Menu/Menu_Sales'))
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/BE/Menu/Menu_Sales_orders'), 5)
WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/BE/Menu/Menu_Sales_orders'))

if (WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/BE/Sales_Order_page/FilterClear'), FailureHandling.OPTIONAL)) {
    WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/BE/Sales_Order_page/FilterClear'))
}

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/BE/Sales_Order_page/button_Filters'))

WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/BE/Sales_Order_page/Filter_ID_FROM'), 2)

WebUI.setText(findTestObject('Object Repository/Helpdesk/AjStore/BE/Sales_Order_page/Filter_increment_id'), orderNumber)

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/BE/Sales_Order_page/Sales_orders_FilterButton'))

TestObject searchResultTO = new TestObject()

searchResultTO.addProperty('xpath', ConditionType.EQUALS, ('//div[@class="data-grid-cell-content" and text()="' + orderNumber) + 
    '"]')

List<WebElement> searchResultElm = WebUiCommonHelper.findWebElements(searchResultTO, 10)

if (searchResultElm.size().equals(1)) {
//    searchResultElm.get(0).click()
	CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(searchResultElm.get(0), 10)
    TestObject OrderHeaderTO = new TestObject()

    OrderHeaderTO.addProperty('xpath', ConditionType.EQUALS, '//div[@class="page-header-hgroup col-l-8 col-m-6"]//div[@class="page-title-wrapper"]//h1[@class="page-title"]')

    WebElement OrderHeaderElem = WebUiCommonHelper.findWebElement(OrderHeaderTO, 30)

    def OrderHeaderText = OrderHeaderElem.getText()

    if (OrderHeaderText.equals('#' + orderNumber)) {
        WebUI.verifyElementText(findTestObject('Object Repository/Helpdesk/AjStore/BE/Sales_Order_page/OrderStatus'), 'Canceled')

        TestObject CustomerNameTO = new TestObject()

        CustomerNameTO.addProperty('xpath', ConditionType.EQUALS, ('//a[@target="_blank"]//span[text()="' + GlobalVariable.CustomerName) + 
            '"]')

        WebElement CustomerNameElem = WebUiCommonHelper.findWebElement(CustomerNameTO, 30)

        if (!(CustomerNameElem.getText().equals(GlobalVariable.CustomerName))) {
            assert false
        }
    }
}

WebUI.delay(5)

WebUI.closeBrowser()