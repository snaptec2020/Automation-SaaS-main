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
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.remote.server.handler.GetCurrentUrl 

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/QasrAlAwani/SharedScripts/LaunchFE'), [:],	FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/QasrAlAwani/SharedScripts/Login'), [:],	FailureHandling.STOP_ON_FAILURE)



/////////////////////////
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/QasrAlAwani/SharedScripts/ClearProductsFromCartPage'), [:],	FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/QasrAlAwani/SharedScripts/OpenAndAddProductToCart'), [:],	FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/view cart'))

WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/view cart'))

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

    numberOfItems.addProperty('xpath', ConditionType.EQUALS, '//input[@name="quantity"]')

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

WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PriceSummaryButton'),10)

WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PriceSummaryButton'))
if(!WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/Step_4_PaymentMethods'),FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PriceSummaryButton'))
}

//
//
//
//
//WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/ReturnPolicyCheckbox'),10)
//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/ReturnPolicyText'))
//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/ReturnPolicyContentLink'))
//
//
//WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/ReturnPolicyContentLink'))
////int PolicyNewTab = WebUI.getWindowIndex()
////println PolicyNewTab
//WebUI.delay(2)
//WebUI.switchToWindowIndex(currentTab+2)
//WebUI.waitForElementPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/ReturnPolicyPage/Title'),3)
//int PolicyTab = WebUI.getWindowIndex()
//String Policyurl = WebUI.getUrl()
//WebUI.verifyMatch(Policyurl, GlobalVariable.FE_URL+"exchange-and-return", true)
//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/ReturnPolicyPage/Title'))
//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/ReturnPolicyPage/Paragraph_1'))
//WebUI.switchToWindowIndex(currentTab)
//
//WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/ReturnPolicyCheckbox'))
//






WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/Step_4_PaymentMethods'))

//WebUI.verifyElementNotVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/MapErrorCannotLoad'))
//Steps
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/Step_4_PaymentMethods'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/Step_3_ShipmentMethod'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/Step_2_ShipmentLocation'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/Step_1_Login'))

//Shipment Methods
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/ShipmentMethodsList'))

def ShipmentMethodsList = findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/ShipmentMethodsList')

def ULXPath = ShipmentMethodsList.findPropertyValue('xpath')

def LiXPath = ULXPath + '//li'

TestObject temp = new TestObject()

temp.addProperty('xpath', ConditionType.EQUALS, LiXPath)

List Shipmentlist = WebUiCommonHelper.findWebElements(temp, 30)

if (Shipmentlist.size() != 1) {
	println (Shipmentlist.size())
    assert false
} else {
    WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/ShpmentMethod_1'))

    WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/ShipmentMethod_1_FastShipmentText_1'))

    WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/ShipmentMethod_1_FastShipmentText_2'))

    WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/ShipmentMethod_1_FastShipmentText_3'))
}

//We should check if shipment fee is 0 when total paid is more than x and 20 if less than x
//PaymentMethods
TestObject paymentPath = new TestObject()

paymentPath.addProperty('xpath', ConditionType.EQUALS, '//div[contains(@class,"checkoutPage-paymentMethod-")]/div[not(@*)]//div[contains(@class,"paymentMethod-item-")]')

List Paymentlist = WebUiCommonHelper.findWebElements(paymentPath, 30)

if (Paymentlist.size() != 3) {
 	println (Paymentlist.size())
   assert false
} else {
    WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/PaymentMethod_1_Text'))

    WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/PaymentMethod_2_Text'))

    WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/PaymentMethod_3_Text'))
}

//Order with COD
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/PaymentMethod_4_Text'),5)


//To-Do to be remove
//WebUI.click()
//
//WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Checkout/FinishPayment'))
//
//TestObject OrderDetails = new TestObject()
//
//OrderDetails.addProperty('xpath', ConditionType.EQUALS, '//h4[contains(@class,"styles-orderNumber-")]')
//
//WebElement OrderDetailsElement = WebUiCommonHelper.findWebElement(OrderDetails, 30)
//
//def orderDetailsText = OrderDetailsElement.getText()
//
//println(orderDetailsText)
//
//def orderNumber = orderDetailsText.findAll('\\d+').get(0)
//
////remove the order
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
//        TestObject CustomerNameTO = new TestObject()
//
//        CustomerNameTO.addProperty('xpath', ConditionType.EQUALS, '//a[@target="_blank"]//span[text()="' + GlobalVariable.CustomerName + '"]')
//
//        WebElement CustomerNameElem = WebUiCommonHelper.findWebElement(CustomerNameTO, 30)
//
//        if (CustomerNameElem.getText().equals(GlobalVariable.CustomerName)) {
//            TestObject CancelButtonTO = new TestObject()
//
//            CancelButtonTO.addProperty('xpath', ConditionType.EQUALS, '//button[@id="order-view-cancel-button"]')
//
//            WebElement CancelButtonElem = WebUiCommonHelper.findWebElement(CancelButtonTO, 30)
//
//            CancelButtonElem.click()
//
//            TestObject CancelButtonOKTO = new TestObject()
//
//            CancelButtonOKTO.addProperty('xpath', ConditionType.EQUALS, '//button[@class="action-primary action-accept"]')
//
//            WebElement CancelButtonOKElem = WebUiCommonHelper.findWebElement(CancelButtonOKTO, 30)
//
//            CancelButtonOKElem.click()
//        }
//    }
//}

WebUI.delay(5)

WebUI.closeBrowser()