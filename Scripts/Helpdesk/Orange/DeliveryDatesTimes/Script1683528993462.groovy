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
import org.openqa.selenium.chrome.ChromeOptions as WebElement
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
import org.openqa.selenium.remote.RemoteWebDriver
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

// Only Chrome for now

WebUI.openBrowser('')
WebUI.closeBrowser()


// Only Chrome for now
Map prefs = new HashMap<String, Object>()
prefs.put("profile.default_content_setting_values.geolocation", 1) // 1:allow 2:block
ChromeOptions options = new ChromeOptions()
options.setExperimentalOption("prefs", prefs)
ChromeDriver drivertest = new ChromeDriver()
((LocationContext)drivertest).setLocation(new Location(GlobalVariable.Latitude,GlobalVariable.Longtitude , 1))

DriverFactory.changeWebDriver(drivertest)

WebUI.navigateToUrl(GlobalVariable.FE_URL)

WebUI.maximizeWindow()

WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Orange/FE/Shared/Login'), 20)

WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/Shared/Login'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Login/LoginTolephone'), 20)

WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Orange/FE/Login/LoginTolephone'), 20)

WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Orange/FE/Login/LoginTolephone'), GlobalVariable.FE_Tel)

WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Orange/FE/Login/LoginButton'), 10)
WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/Login/LoginButton'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Login/EnterOTP'), 10)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Login/EnterOTP'), FailureHandling.STOP_ON_FAILURE)

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

WebUI.navigateToUrl(GlobalVariable.BE_URL)

//WebUI.switchToWindowTitle('متجر عجلان واخوانه')
WebUI.setText(findTestObject('Object Repository/Helpdesk/Orange/BE/Login/UserName'), GlobalVariable.BE_UserName)

//WebUI.setEncryptedText(findTestObject('Object Repository/Helpdesk/Orange/BE/Login/Password'), 'h9YfHV16ZyMBoeJlmdP5xA==')
WebUI.setText(findTestObject('Object Repository/Helpdesk/Orange/BE/Login/Password'), GlobalVariable.BE_Password)

WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/BE/Login/LoginButton'))

WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/BE/Menu/Menu_MageDelight'))

WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/BE/Menu/Menu_MageDelight_MobileOTPLogin'))

WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/BE/Menu/Menu_MageDelight_MobileOTPLogin_SMSLog'))

//WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/Page_SMS Log  Magento Admin/button_Remove'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/BE/SmsLogPage/button_Filters'))

WebUI.setText(findTestObject('Object Repository/Helpdesk/Orange/BE/SmsLogPage/transaction_type'), '')

WebUI.setText(findTestObject('Object Repository/Helpdesk/Orange/BE/SmsLogPage/status'), '')

WebUI.setText(findTestObject('Object Repository/Helpdesk/Orange/BE/SmsLogPage/s_id'), '')

WebUI.setText(findTestObject('Object Repository/Helpdesk/Orange/BE/SmsLogPage/recipient_phone'), '')

WebUI.setText(findTestObject('Object Repository/Helpdesk/Orange/BE/SmsLogPage/message_body'), '')

WebUI.setText(findTestObject('Object Repository/Helpdesk/Orange/BE/SmsLogPage/entity_id_to'), '')

WebUI.setText(findTestObject('Object Repository/Helpdesk/Orange/BE/SmsLogPage/entity_id_from'), '')

WebUI.setText(findTestObject('Object Repository/Helpdesk/Orange/BE/SmsLogPage/api_service'), '')

WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/BE/Sales_Order_page/Sales_orders_FilterButton'))

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/BE/SmsLogPage/button_Filters'))

WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Orange/BE/SmsLogPage/recipient_phone'), 2)

WebUI.setText(findTestObject('Object Repository/Helpdesk/Orange/BE/SmsLogPage/recipient_phone'), GlobalVariable.FE_Tel)

//WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Orange/BE/SmsLogPage/recipient_phone'), 
//    Keys.chord(Keys.ENTER))
WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/BE/Sales_Order_page/Sales_orders_FilterButton'))

//TestObject FirstRowReceiptPhone = new TestObject()
//FirstRowReceiptPhone.addProperty("xpath",ConditionType.EQUALS,"//tr[@class='data-row' and @data-repeat-index='0']/td[6]/div")
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Orange/BE/SmsLogPage/SmsContentFirstRow'), 20)

String OTP = WebUI.getText(findTestObject('Object Repository/Helpdesk/Orange/BE/SmsLogPage/SmsContentFirstRow'))

println('>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>')

println(OTP)

OTP = OTP.replace('كلمة المرور لتسجيل الدخول رمز: ', '')

println(OTP)

//WebUI.switchToWindowTitle('SMS Log / Magento Admin')
//WebUI.switchToWindowTitle('متجر عجلان واخوانه')
WebUI.delay(2)

WebUI.switchToWindowIndex(currentTab)

String xPath = '//input[@type=\'tel\' and contains( @aria-label,\'Digit 1\')]'

TestObject firstOTPDigit = new TestObject('objectName')

firstOTPDigit.addProperty('xpath', ConditionType.EQUALS, xPath)

WebUI.sendKeys(firstOTPDigit, OTP)

WebUI.waitForElementNotPresent(findTestObject('Object Repository/Helpdesk/Orange/FE/Login/EnterOTP'), 5, FailureHandling.STOP_ON_FAILURE)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/AccountPage/AccountPageTitle'), 20)

//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/AccountPage/AccountPageTitle'), FailureHandling.STOP_ON_FAILURE)
WebElement AccountPageTitle = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/Orange/FE/AccountPage/AccountPageTitle'), 10)
WebUI.verifyEqual(AccountPageTitle.getText(), GlobalVariable.CustomerName)
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/Orange/FE/Login/EnterOTP'), 2)


WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/Orange/FE/AccountPage/SignOut'))

/////////////////////////
WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/Shared/Logo'))


WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Orange/FE/Cart/view cart'),10)
WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/Cart/view cart'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/MiniCart/MiniCartFrame'))

if(WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/MiniCart/MiniCartShowCartButton'),FailureHandling.OPTIONAL)) {
	///Clear Cart
	WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/MiniCart/MiniCartShowCartButton'))
	
	TestObject removeProductFromCart = new TestObject()
	
	removeProductFromCart.addProperty('xpath', ConditionType.EQUALS, '//span[text()="إزالة بند"]//parent::button[contains(@class,"product-button-")]')
	
	List<WebElement> removeProductFromCartElements = WebUiCommonHelper.findWebElements(removeProductFromCart, 10)
	
	while (removeProductFromCartElements.size() != 0) {
		if (removeProductFromCartElements.size().equals(1)) {
			removeProductFromCartElements.get(0).click()
	
			removeProductFromCartElements.remove(0)
		} else {
			removeProductFromCartElements.get(0).click()
			removeProductFromCartElements = WebUiCommonHelper.findWebElements(removeProductFromCart, 10)
		}
	}
}else {
	//Close the Mini Cart
	WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/Cart/view cart'))
}


//Open Random Product
WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/Shared/Logo'))
CustomKeywords.'products.productsFromCatalog.OpenRandomProductOrange'()

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

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Cart/Add to cart - Active'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/Cart/Add to cart - Active'))


WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Orange/FE/Cart/view cart'),10)
WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/Orange/FE/Cart/view cart'))

WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/Cart/view cart'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/MiniCart/MiniCartFrame'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/MiniCart/MiniCartShowCartButton'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/MiniCart/MiniCartShowCartButton'))

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

WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/Cart/PriceSummaryButton'))
if(!WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/Step_4_PaymentMethods'),FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/Cart/PriceSummaryButton'))
}


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

List Shipmentlist = WebUiCommonHelper.findWebElements(temp, 30)

if (Shipmentlist.size() != 1) {
	println (Shipmentlist.size())
    assert false
} else {
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

//TodayDate = new Date().format( 'ddMMyyyy' )
//def date = new Date()
//def sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")

def DaysMap=[
	1:"الأحد",
	2:"الاثنين",
	3:"الثلاثاء",
	4:"الأربعاء",
	5:"الخميس",
	6:"الجمعة",
	7:"السبت"]
Date date = new Date()
int NowTimeHour = new Date().format( 'kk' ).toInteger()
int disabledPeriods=0
switch(NowTimeHour) {            
         case 23..24: 
            disabledPeriods = 8
            break; 
         case 21..23: 
            disabledPeriods = 7
            break; 
         case 19..21: 
            disabledPeriods = 6
            break; 
         case 17..19: 
            disabledPeriods = 5
            break; 
         case 15..17: 
            disabledPeriods = 4
            break; 
         case 13..15: 
            disabledPeriods = 3
            break; 
         case 11..13: 
            disabledPeriods = 2
            break; 
         case 9..11: 
            disabledPeriods = 1
            break; 
		default:
			disabledPeriods = 0
			break;
      }

println NowTimeHour
println disabledPeriods

TestObject availableDates = new TestObject()
availableDates.addProperty("xpath",ConditionType.EQUALS,findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/deliveryDays-headerList').getSelectorCollection().get(SelectorMethod.XPATH) + "//li")
List<WebElement> availableDatesElements = WebUiCommonHelper.findWebElements(availableDates, 30)
WebUI.verifyEqual(availableDatesElements.size(), 7)

for(int i=0;i<7;i++) {
	if(!i.equals(0)) {
		WebUI.scrollToElement(findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/ShipmentMethodsList'), 5)
		availableDatesElements.get(i).click()
		}
	Calendar calendar = Calendar.getInstance()
	calendar.setTime(date)
	int day = calendar.get(Calendar.DAY_OF_WEEK)
	String LoopDateString =date.format( 'yyyy/MM/dd' )
	String LoopDay=DaysMap[day]
	TestObject LoopDateTO = new TestObject()
	LoopDateTO.addProperty("xpath",
		ConditionType.EQUALS,
		findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/deliveryDays-headerList').getSelectorCollection().get(SelectorMethod.XPATH) + "//li[" + (i+1).toString() + "]//h6")
	List<WebElement> LoopDateElement = WebUiCommonHelper.findWebElements(LoopDateTO, 30)
	
	WebUI.verifyEqual(LoopDateElement.get(0).getText() , LoopDay)
	WebUI.verifyEqual(LoopDateElement.get(1).getText() , LoopDateString)
	
	TestObject availableTime = new TestObject()
	availableTime.addProperty("xpath",ConditionType.EQUALS,findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/deliveryTime-headerList').getSelectorCollection().get(SelectorMethod.XPATH) + '//li[contains(@class,"deliveryTime-item-")]')
	List<WebElement> availableTimesElements = WebUiCommonHelper.findWebElements(availableTime, 30)
	if(LoopDay.equals("الجمعة")) {
		WebUI.verifyEqual(availableTimesElements.size(),5)
	}else {
		WebUI.verifyEqual(availableTimesElements.size(),8)
	}
	
	int jj=-1
	for(int j=0;j<8;j++) {
		
		if(LoopDay.equals("الجمعة") & j<=2) {
			continue;
		}else {
			jj+=1
		}
		TestObject LoopedTimePeriodTO=new TestObject()
		LoopedTimePeriodTO.addProperty("xpath",ConditionType.EQUALS,
			findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/deliveryTime-headerList').getSelectorCollection().get(SelectorMethod.XPATH) + "//li[" + (jj+1).toString() + "]"
			)
		println findTestObject('Object Repository/Helpdesk/Orange/FE/Checkout/deliveryTime-headerList').getSelectorCollection().get(SelectorMethod.XPATH) + "//li[" + (jj+1).toString() + "]"
		WebElement LoopedTimePeriodElement = WebUiCommonHelper.findWebElement(LoopedTimePeriodTO, 5)

		if(i.equals(0)) {
			if((j+1)<=disabledPeriods) {
				WebUI.verifyMatch(WebUI.getAttribute(LoopedTimePeriodTO, "class"), '.*deliveryTime-disableBtn-.*', true)
//				WebUI.verifyElementNotClickable(LoopedTimePeriodTO)
			}else {
				WebUI.verifyElementClickable(LoopedTimePeriodTO)
			}
		}else {
			WebUI.verifyElementClickable(LoopedTimePeriodTO)
		}
	}	
	date=date.next()
}

WebUI.delay(5)

WebUI.closeBrowser()

