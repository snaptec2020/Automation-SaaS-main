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
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.server.handler.GetCurrentUrl as WebElement



WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/AlAseel/SharedScripts/LaunchFE'), [:],	FailureHandling.STOP_ON_FAILURE)
WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/AlAseel/SharedScripts/Login'), [:],	FailureHandling.STOP_ON_FAILURE)

/////////////////////////
WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/Logo'),FailureHandling.OPTIONAL)
WebUI.waitForPageLoad(20)

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/AlAseel/SharedScripts/ClearProductsFromCartPage'), [:],	FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/AlAseel/SharedScripts/OpenAndAddProductToCart'), [:],	FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/Cart'),10)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/Cart'),10)
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/Cart'), 10)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/ViewCartMainPageBtn'),10)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/ViewCartMainPageBtn'),10)
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/ViewCartMainPageBtn'),10)

//Check Total Paid for Tabby and Tamara
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/totalNeededPay'), 10)
String totalText=WebUI.getText(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/totalNeededPay'))
println(totalText)
totalText =totalText.replace(" ", "").replace("٫", ".").replace("ر.س", "")
Float totalValue = totalText.toFloat()
println(totalValue)

if (totalValue < 99) {
    //increase the products
    int neededQty = ((Math.ceil(99 / totalValue)) as int)
	//WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/OutSideCart'))
	for(int increase = 1 ; increase<neededQty ; increase++) {
		WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/IncreaseQtyBtn'),5)
		WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/IncreaseQtyBtn'))
		WebUI.delay(2)
	}
	WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/RefreshCart'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/RefreshCart'))
    //remove and select another product (need to remove and seach again)
} else if (totalValue > 2500) {
	// To-Do Remove and add another product with less amount
}

WebUI.delay(3)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/PriceSummaryButton'),10)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/PriceSummaryButton'),10)
WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/PriceSummaryButton'))

if(!WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Checkout/Step_4_PaymentMethods'),FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/PriceSummaryButton'))
}

//Steps
//WebUI.verifyElementNotVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Checkout/MapErrorCannotLoad'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Checkout/Step_4_PaymentMethods'))
//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Checkout/Step_3_ShipmentMethod'))
//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Checkout/Step_2_ShipmentLocation'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Checkout/Step_1_Login'))

//We should check if shipment fee is 0 when total paid is more than x and 20 if less than x
//PaymentMethods
TestObject paymentPath = new TestObject()
paymentPath.addProperty('xpath', ConditionType.EQUALS, '//div[@data-role="payment-methods-load"]/div[contains(@data-bind,"_active")]')
WebUI.delay(3)
WebUI.waitForElementVisible(paymentPath,10)
List Paymentlist = WebUiCommonHelper.findWebElements(paymentPath, 30)

if (Paymentlist.size() != 2) {
 	println (Paymentlist.size())
   assert false
} else {
	// Tamara
    WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Checkout/PaymentMethod_1_Text'))
	// Credit
    WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Checkout/PaymentMethod_2_Text'))
}

//Order with COD
//WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Checkout/PaymentMethod_4_Text'),5)


//To-Do to be remove
//WebUI.click()
//
//WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Checkout/FinishPayment'))
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
//WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Menu/Menu_Sales'))
//
//WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Menu/Menu_Sales_orders'))
//
//if (WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Sales_Order_page/FilterClear'), FailureHandling.OPTIONAL)) {
//    WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Sales_Order_page/FilterClear'))
//}
//
//WebUI.delay(2)
//
//WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Sales_Order_page/button_Filters'))
//
//WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Sales_Order_page/Filter_ID_FROM'), 2)
//
//WebUI.setText(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Sales_Order_page/Filter_increment_id'), orderNumber)
//
//WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Sales_Order_page/Sales_orders_FilterButton'))
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