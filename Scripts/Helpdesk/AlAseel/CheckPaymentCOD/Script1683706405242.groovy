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



WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/Login'))
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/LoginPopup'),5)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/PhoneLoginTab'),10)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/PhoneLoginTab'),10)
WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/PhoneLoginTab'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/LoginTolephone'), 20)

WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/LoginTolephone'), GlobalVariable.FE_Tel)
WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/LoginButton'))

WebUI.delay(2)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/SendOTP'), 5)
WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/SendOTP'))
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/CheckOTPText'),5)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/CheckOTPText'))



int currentTab = WebUI.getWindowIndex()

//Robot robot = new Robot()
//robot.keyPress(KeyEvent.VK_CONTROL)
//robot.keyPress(KeyEvent.VK_T)
//robot.keyRelease(KeyEvent.VK_CONTROL)
//robot.keyRelease(KeyEvent.VK_T)
WebDriver driver = DriverFactory.getWebDriver()

JavascriptExecutor js = ((driver) as JavascriptExecutor)

js.executeScript('window.open();')

WebUI.switchToWindowIndex(currentTab + 1)
WebUI.authenticate(GlobalVariable.BE_URL, GlobalVariable.BEBasicAuthUser, GlobalVariable.BEBasicAuthPassword, 20, FailureHandling.OPTIONAL)

WebUI.setText(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Login/UserName'), GlobalVariable.BE_UserName)

//WebUI.setEncryptedText(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Login/Password'), 'h9YfHV16ZyMBoeJlmdP5xA==')
WebUI.setText(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Login/Password'), GlobalVariable.BE_Password)
WebUI.waitForPageLoad(10)
WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Login/LoginButton'),FailureHandling.CONTINUE_ON_FAILURE)
if(WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Shared/SomethingWentWrong'), 10,FailureHandling.OPTIONAL) & WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Shared/SomethingWentWrongOK'), 10,FailureHandling.OPTIONAL) ) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Shared/SomethingWentWrongOK'),FailureHandling.OPTIONAL)
}
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Menu/Menu_MageDelight'),20)
WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Menu/Menu_MageDelight'))
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Menu/Menu_MageDelight_MobileOTPLogin'),5)
WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Menu/Menu_MageDelight_MobileOTPLogin'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Menu/Menu_MageDelight_MobileOTPLogin_SMSLog'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/button_Filters'))

WebUI.setText(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/transaction_type'), '')

WebUI.setText(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/status'), '')

WebUI.setText(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/s_id'), '')

WebUI.setText(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/recipient_phone'), '')


WebUI.setText(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/entity_id_to'), '')

WebUI.setText(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/entity_id_from'), '')

WebUI.setText(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/api_service'), '')

WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Sales_Order_page/Sales_orders_FilterButton'))

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/button_Filters'))

WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/recipient_phone'), 2)

WebUI.setText(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/recipient_phone'), GlobalVariable.FE_Tel)

//    Keys.chord(Keys.ENTER))
WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/BE/Sales_Order_page/Sales_orders_FilterButton'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/SmsViewFirstRow'), 20)
WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/SmsViewFirstRow'))

String OTP = WebUI.getText(findTestObject('Object Repository/Helpdesk/AlAseel/BE/SmsLogPage/SMSContentField'))
println('>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>')

println(OTP)
OTP=OTP.findAll('\\d+').get(0)
println(OTP)

WebUI.delay(2)

WebUI.switchToWindowIndex(currentTab)

WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/OTPField'), OTP)

WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/ConfirmOTPbtn'))
WebUI.waitForPageLoad(30)
WebUI.delay(3)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/AccountPage/AccountPageTitle'), 20)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/AccountPage/AccountPageTitle'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/ConfirmOTPbtn'), 2)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/AccountBtn'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/AccountBtn'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/AccountPopup'),5)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/AccountPopup'))
WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Login/CloseAccountPopup'))

/////////////////////////
WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/Logo'),FailureHandling.OPTIONAL)
WebUI.waitForPageLoad(20)

///Clear Cart
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/Cart'),10)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/Cart'),10)
CustomKeywords.'products.productsFromCatalog.clickJS'(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/Cart'), 10)

TestObject removeProductFromCart = new TestObject()
removeProductFromCart.addProperty('xpath', ConditionType.EQUALS, '//div[@class="md-sidenav-right cdz-sidebar" and @style="top: 0px;"]//ol[@id="mini-cart"]//span[text()="ازالة"]//parent::a[@class="action delete" and @title="أزل المنتج"]')
WebUI.waitForElementVisible(removeProductFromCart, 10)
List<WebElement> removeProductFromCartElements = WebUiCommonHelper.findWebElements(removeProductFromCart, 10)

while (removeProductFromCartElements.size() != 0) {
    if (removeProductFromCartElements.size().equals(1)) {
		CustomKeywords.'products.productsFromCatalog.clickJS'(removeProductFromCartElements.get(0), 10)
//        removeProductFromCartElements.get(0).click()
		WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/ConfirmDelete'),3,FailureHandling.OPTIONAL)
		if(WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/ConfirmDelete'),FailureHandling.OPTIONAL)) {
			WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/ConfirmDelete'))
			WebUI.waitForElementNotVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/ConfirmDelete'), 3)
		}
        removeProductFromCartElements.remove(0)
    } else {
		CustomKeywords.'products.productsFromCatalog.clickJS'(removeProductFromCartElements.get(0), 10)
//        removeProductFromCartElements.get(0).click()
			WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/ConfirmDelete'),3,FailureHandling.OPTIONAL)
			if(WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/ConfirmDelete'),FailureHandling.OPTIONAL)) {
				WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/ConfirmDelete'))
				WebUI.waitForElementNotVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/ConfirmDelete'), 3)
		}
        removeProductFromCartElements = WebUiCommonHelper.findWebElements(removeProductFromCart, 10)
    }
}
WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/OutSideCart'))
//Open Random Product
CustomKeywords.'products.productsFromCatalog.OpenRandomProductAlAseel'()

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/Add to cart'))
WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/Add to cart'))

if(WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/MiniCart/ContinueShoping'), FailureHandling.OPTIONAL)) {
	WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/MiniCart/ContinueShoping'),5)
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlAseel/FE/MiniCart/ContinueShoping'),5)
	WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/MiniCart/ContinueShoping'))
}else {
	// Check if qty accepted
	def trials=1
	TestObject errorQTY_TO = new TestObject()
	errorQTY_TO.addProperty("xpath",ConditionType.EQUALS,'//div[@class="page messages"]//div[@class="message message-error error"]/div[text()="الكمية المطلوبة غير متوفرة"]')
//	List<WebElement> errorQTY_Element = WebUiCommonHelper.findWebElements(errorQTY_TO, 5)
	while (!WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/MiniCart/ContinueShoping'), FailureHandling.OPTIONAL) && trials<10) {
		//Open Random Product
		trials = trials+1
		CustomKeywords.'products.productsFromCatalog.OpenRandomProductAlAseel'()
		WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/Add to cart'))
		WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/Add to cart'))
//		errorQTY_Element = WebUiCommonHelper.findWebElements(errorQTY_TO, 5)
		if(trials>=10) {
			assert false,"Could not find available products"
		}
		WebUI.delay(5)
	}
	WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/MiniCart/ContinueShoping'), FailureHandling.CONTINUE_ON_FAILURE)
}


WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/Cart'),10)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/Cart'),10)
CustomKeywords.'products.productsFromCatalog.clickJS'(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/Cart'), 10)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/ViewCartMainPageBtn'),10)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/ViewCartMainPageBtn'),10)
CustomKeywords.'products.productsFromCatalog.clickJS'(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/ViewCartMainPageBtn'),10)

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