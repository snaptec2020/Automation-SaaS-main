import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.main.CustomKeywordDelegatingMetaClass
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
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver

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
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword as WebUIAbstractKeyword
import catalog.catlogComponants as catlogComponants
import java.util.List as List
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement as WebElement



boolean isMobile=false

String shownProductName
String shownProductPrice
String addedProductPrice
TestObject ProductPrice_TO=new TestObject()
TestObject Productlink_TO=new TestObject()
TestObject CartProductList=new TestObject()
TestObject ProductQty_TO=new TestObject()
List<WebElement> CartProductListItems

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/QasrAlAwani/SharedScripts/LaunchFE'), [:],	FailureHandling.STOP_ON_FAILURE)
if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/BottomMenu-Mobile'), 10)) {
	isMobile=true
}else {
	isMobile=false
}
int currentTab = WebUI.getWindowIndex()


String popUpSKU= getpopupSKU()
int popUpSKUQty= getpopupAvailableQty(popUpSKU)
String SearchResults='//div[contains(@class,"item-root")]//a[contains(@class,"item-nameProduct-") and @href]'
if(isMobile) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Search/Search icon'))
}
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Search/Search Bar context'))
WebUI.setText(findTestObject('Object Repository/Helpdesk/Qasr/FE/Search/Search Bar context'), popUpSKU)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/Search/Search Bar context'), Keys.chord(Keys.ENTER))
Productlink_TO=new TestObject()
Productlink_TO.addProperty("xpath",ConditionType.EQUALS,SearchResults)
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(Productlink_TO)
WebUI.waitForElementVisible(Productlink_TO, 10, FailureHandling.STOP_ON_FAILURE)
WebUI.click(Productlink_TO, FailureHandling.STOP_ON_FAILURE)
String ProductURL = WebUI.getUrl().replace(GlobalVariable.FE_URL, "")
String ProductTitle = WebUI.getText(findTestObject('Object Repository/Helpdesk/Qasr/FE/Product/productFullDetail-Name'))
String ProductSKU = WebUI.getText(findTestObject('Object Repository/Helpdesk/Qasr/FE/Product/productFullDetail-sku'))
String ProductPrice = WebUI.getText(findTestObject('Object Repository/Helpdesk/Qasr/FE/Product/Product_Price')).replace("ر.س", "").replace(" ", "")
ProductPrice=Float.parseFloat(ProductPrice).toString()
if(!ProductSKU.equals(popUpSKU)) {
	KeywordUtil.markFailed('unable to find the product')
}
println popUpSKUQty
println ProductTitle
println ProductSKU
println ProductPrice


// TC_1 visibility of popup When Cart is empty
KeywordUtil.logInfo('TC_1 visibility of popup When Cart is empty')
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))
WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/QasrAlAwani/SharedScripts/ClearProductsFromCartPage'), [:],	FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))
RemoveCartCookie()
WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/view cart'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/view cart'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCart'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancel'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancelAdd'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductName'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductPrice'))
shownProductName = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductName'), 3).getText()
shownProductPrice = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductPrice'), 3).getText().replace("ر.س", "").replace(" ", "")
shownProductPrice=Float.parseFloat(shownProductPrice).toString()
WebUI.verifyMatch(shownProductName, ProductTitle, false)
WebUI.verifyMatch(shownProductPrice, ProductPrice, false)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancel'))


// TC_2 visibility of popup When Cart is not empty
KeywordUtil.logInfo('TC_2 visibility of popup When Cart is not empty')
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))
WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/QasrAlAwani/SharedScripts/OpenAndAddProductToCart'), [:],	FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))
RemoveCartCookie()
WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/view cart'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/view cart'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCart'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancel'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancelAdd'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductName'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductPrice'))
shownProductName = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductName'), 3).getText()
shownProductPrice = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductPrice'), 3).getText().replace("ر.س", "").replace(" ", "")
shownProductPrice=Float.parseFloat(shownProductPrice).toString()
WebUI.verifyMatch(shownProductName, ProductTitle, false)
WebUI.verifyMatch(shownProductPrice, ProductPrice, false)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancel'))

// TC_3 Use Add while the cart is empty
KeywordUtil.logInfo('TC_3 Use Add while the cart is empty')
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))
WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/QasrAlAwani/SharedScripts/ClearProductsFromCartPage'), [:],	FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))
WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/view cart'))
RemoveCartCookie()
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/view cart'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCart'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancel'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancelAdd'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductName'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductPrice'))
shownProductName = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductName'), 3).getText()
shownProductPrice = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductPrice'), 3).getText().replace("ر.س", "").replace(" ", "")
shownProductPrice=Float.parseFloat(shownProductPrice).toString()
WebUI.verifyMatch(shownProductName, ProductTitle, false)
WebUI.verifyMatch(shownProductPrice, ProductPrice, false)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancelAdd'))
Productlink_TO=new TestObject()
Productlink_TO.addProperty("xpath",ConditionType.EQUALS,'//a[@href="/' + ProductURL + '" and contains(text(),"' + ProductTitle + '")]')
WebUI.waitForElementVisible(Productlink_TO, 10, FailureHandling.STOP_ON_FAILURE)
WebUI.verifyElementVisible(Productlink_TO, FailureHandling.STOP_ON_FAILURE)
ProductPrice_TO.addProperty("xpath",ConditionType.EQUALS,Productlink_TO.findPropertyValue("xpath") + '//../..//div[contains(@class,"product-subTotal-")]//div')
WebUI.verifyElementVisible(ProductPrice_TO, FailureHandling.STOP_ON_FAILURE)
addedProductPrice = WebUiCommonHelper.findWebElement(ProductPrice_TO, 3).getText().replace("ر.س", "").replace(" ", "")
addedProductPrice=Float.parseFloat(addedProductPrice).toString()
WebUI.verifyMatch(addedProductPrice, ProductPrice, false)
CartProductList = new TestObject()
CartProductList.addProperty("xpath", ConditionType.EQUALS, '//ul[contains(@class,"productListing-root-")]/li')
CartProductListItems = WebUiCommonHelper.findWebElements(CartProductList,3)
WebUI.verifyMatch(CartProductListItems.size().toString(),"1",false)


// TC_4 Use Add while the cart is not empty
KeywordUtil.logInfo('TC_4 Use Add while the cart is not empty')
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))
WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/QasrAlAwani/SharedScripts/ClearProductsFromCartPage'), [:],	FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))
WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/QasrAlAwani/SharedScripts/OpenAndAddProductToCart'), [:],	FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))
WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/view cart'))
RemoveCartCookie()
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/view cart'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCart'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancel'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancelAdd'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductName'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductPrice'))
shownProductName = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductName'), 3).getText()
shownProductPrice = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductPrice'), 3).getText().replace("ر.س", "").replace(" ", "")
shownProductPrice=Float.parseFloat(shownProductPrice).toString()
WebUI.verifyMatch(shownProductName, ProductTitle, false)
WebUI.verifyMatch(shownProductPrice, ProductPrice, false)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancelAdd'))
Productlink_TO=new TestObject()
Productlink_TO.addProperty("xpath",ConditionType.EQUALS,'//a[@href="/' + ProductURL + '" and contains(text(),"' + ProductTitle + '")]')
WebUI.waitForElementVisible(Productlink_TO, 10, FailureHandling.STOP_ON_FAILURE)
WebUI.verifyElementVisible(Productlink_TO, FailureHandling.STOP_ON_FAILURE)
ProductPrice_TO=new TestObject()
ProductPrice_TO.addProperty("xpath",ConditionType.EQUALS,Productlink_TO.findPropertyValue("xpath") + '//../..//div[contains(@class,"product-subTotal-")]//div')
WebUI.verifyElementVisible(ProductPrice_TO, FailureHandling.STOP_ON_FAILURE)
addedProductPrice = WebUiCommonHelper.findWebElement(ProductPrice_TO, 3).getText().replace("ر.س", "").replace(" ", "")
addedProductPrice=Float.parseFloat(addedProductPrice).toString()
WebUI.verifyMatch(addedProductPrice, ProductPrice, false)
CartProductList = new TestObject()
CartProductList.addProperty("xpath", ConditionType.EQUALS, '//ul[contains(@class,"productListing-root-")]/li')
CartProductListItems = WebUiCommonHelper.findWebElements(CartProductList,3)
WebUI.verifyMatch(CartProductListItems.size().toString(),"2",false)




// TC_5 Use Add while the cart is not empty and contains the same suggested product
KeywordUtil.logInfo('TC_5 Use Add while the cart is not empty and contains the same suggested product')
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))
WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/QasrAlAwani/SharedScripts/ClearProductsFromCartPage'), [:],	FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))
if(isMobile) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Search/Search icon'))
}
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Search/Search Bar context'))
WebUI.setText(findTestObject('Object Repository/Helpdesk/Qasr/FE/Search/Search Bar context'), popUpSKU)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/Search/Search Bar context'), Keys.chord(Keys.ENTER))
Productlink_TO=new TestObject()
Productlink_TO.addProperty("xpath",ConditionType.EQUALS,SearchResults)
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(Productlink_TO)
WebUI.waitForElementVisible(Productlink_TO, 10, FailureHandling.STOP_ON_FAILURE)
WebUI.click(Productlink_TO, FailureHandling.STOP_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/Add to cart'))
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/Add to cart'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/Add to cart'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))
WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/view cart'))
RemoveCartCookie()
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/view cart'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCart'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancel'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancelAdd'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductName'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductPrice'))
shownProductName = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductName'), 3).getText()
shownProductPrice = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductPrice'), 3).getText().replace("ر.س", "").replace(" ", "")
shownProductPrice=Float.parseFloat(shownProductPrice).toString()
WebUI.verifyMatch(shownProductName, ProductTitle, false)
WebUI.verifyMatch(shownProductPrice, ProductPrice, false)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancelAdd'))
Productlink_TO=new TestObject()
Productlink_TO.addProperty("xpath",ConditionType.EQUALS,'//a[@href="/' + ProductURL + '" and contains(text(),"' + ProductTitle + '")]')
WebUI.waitForElementVisible(Productlink_TO, 10, FailureHandling.STOP_ON_FAILURE)
WebUI.verifyElementVisible(Productlink_TO, FailureHandling.STOP_ON_FAILURE)
ProductPrice_TO=new TestObject()
ProductPrice_TO.addProperty("xpath",ConditionType.EQUALS,Productlink_TO.findPropertyValue("xpath") + '//../..//div[contains(@class,"product-subTotal-")]//div')
WebUI.verifyElementVisible(ProductPrice_TO, FailureHandling.STOP_ON_FAILURE)
addedProductPrice = WebUiCommonHelper.findWebElement(ProductPrice_TO, 3).getText().replace("ر.س", "").replace(" ", "")
addedProductPrice=Float.parseFloat(addedProductPrice).toString()
WebUI.verifyMatch(addedProductPrice, (Float.parseFloat(ProductPrice)*2).toString(), false)
ProductQty_TO=new TestObject()
ProductQty_TO.addProperty("xpath",ConditionType.EQUALS,Productlink_TO.findPropertyValue("xpath") + '//../..//input[@name="quantity"]')
WebUI.verifyElementAttributeValue(ProductQty_TO, 'value', "2", 30)
CartProductList = new TestObject()
CartProductList.addProperty("xpath", ConditionType.EQUALS, '//ul[contains(@class,"productListing-root-")]/li')
CartProductListItems = WebUiCommonHelper.findWebElements(CartProductList,3)
WebUI.verifyMatch(CartProductListItems.size().toString(),"1",false)


// TC_6 Use Add while the cart is not empty and contains the same suggested product as one of the products
KeywordUtil.logInfo('TC_6 Use Add while the cart is not empty and contains the same suggested product as one of the products')
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))
WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/QasrAlAwani/SharedScripts/ClearProductsFromCartPage'), [:],	FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))
WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/QasrAlAwani/SharedScripts/OpenAndAddProductToCart'), [:],	FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))
if(isMobile) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Search/Search icon'))
}
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Search/Search Bar context'))
WebUI.setText(findTestObject('Object Repository/Helpdesk/Qasr/FE/Search/Search Bar context'), popUpSKU)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/Qasr/FE/Search/Search Bar context'), Keys.chord(Keys.ENTER))
Productlink_TO=new TestObject()
Productlink_TO.addProperty("xpath",ConditionType.EQUALS,SearchResults)
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(Productlink_TO)
WebUI.waitForElementVisible(Productlink_TO, 10, FailureHandling.STOP_ON_FAILURE)
WebUI.click(Productlink_TO, FailureHandling.STOP_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/Add to cart'))
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/Add to cart'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/Add to cart'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))
WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/view cart'))
RemoveCartCookie()
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/view cart'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCart'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancel'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancelAdd'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductName'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductPrice'))
shownProductName = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductName'), 3).getText()
shownProductPrice = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductPrice'), 3).getText().replace("ر.س", "").replace(" ", "")
shownProductPrice=Float.parseFloat(shownProductPrice).toString()
WebUI.verifyMatch(shownProductName, ProductTitle, false)
WebUI.verifyMatch(shownProductPrice, ProductPrice, false)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancelAdd'))
Productlink_TO=new TestObject()
Productlink_TO.addProperty("xpath",ConditionType.EQUALS,'//a[@href="/' + ProductURL + '" and contains(text(),"' + ProductTitle + '")]')
WebUI.waitForElementVisible(Productlink_TO, 10, FailureHandling.STOP_ON_FAILURE)
WebUI.verifyElementVisible(Productlink_TO, FailureHandling.STOP_ON_FAILURE)
ProductPrice_TO=new TestObject()
ProductPrice_TO.addProperty("xpath",ConditionType.EQUALS,Productlink_TO.findPropertyValue("xpath") + '//../..//div[contains(@class,"product-subTotal-")]//div')
WebUI.verifyElementVisible(ProductPrice_TO, FailureHandling.STOP_ON_FAILURE)
addedProductPrice = WebUiCommonHelper.findWebElement(ProductPrice_TO, 3).getText().replace("ر.س", "").replace(" ", "")
addedProductPrice=Float.parseFloat(addedProductPrice).toString()
WebUI.verifyMatch(addedProductPrice, (Float.parseFloat(ProductPrice)*2).toString(), false)
ProductQty_TO=new TestObject()
ProductQty_TO.addProperty("xpath",ConditionType.EQUALS,Productlink_TO.findPropertyValue("xpath") + '//../..//input[@name="quantity"]')
WebUI.verifyElementAttributeValue(ProductQty_TO, 'value', "2", 30)
CartProductList = new TestObject()
CartProductList.addProperty("xpath", ConditionType.EQUALS, '//ul[contains(@class,"productListing-root-")]/li')
CartProductListItems = WebUiCommonHelper.findWebElements(CartProductList,3)
WebUI.verifyMatch(CartProductListItems.size().toString(),"2",false)


// TC_7 Verify the popup will be shown once
KeywordUtil.logInfo('TC_7 Verify the popup will be shown once')
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))
WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/QasrAlAwani/SharedScripts/ClearProductsFromCartPage'), [:],	FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))
RemoveCartCookie()
WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/view cart'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/view cart'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCart'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancel'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancelAdd'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductName'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductPrice'))
shownProductName = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductName'), 3).getText()
shownProductPrice = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductPrice'), 3).getText().replace("ر.س", "").replace(" ", "")
shownProductPrice=Float.parseFloat(shownProductPrice).toString()
WebUI.verifyMatch(shownProductName, ProductTitle, false)
WebUI.verifyMatch(shownProductPrice, ProductPrice, false)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancel'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))
WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/view cart'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/view cart'))
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCart'),2)
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancel'),2)
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancelAdd'),2)
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductName'),2)
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductPrice'),2)
WebUI.refresh()
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCart'),2)
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancel'),2)
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancelAdd'),2)
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductName'),2)
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductPrice'),2)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))
WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/QasrAlAwani/SharedScripts/OpenAndAddProductToCart'), [:],	FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))
WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/view cart'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/view cart'))
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCart'),2)
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancel'),2)
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancelAdd'),2)
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductName'),2)
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductPrice'),2)
WebUI.refresh()
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCart'),2)
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancel'),2)
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancelAdd'),2)
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductName'),2)
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartProductPrice'),2)



WebUI.delay(5)
WebUI.closeBrowser()

String getpopupSKU() {
	int currentTab = WebUI.getWindowIndex()
	WebDriver driver = DriverFactory.getWebDriver()
	JavascriptExecutor js = ((driver) as JavascriptExecutor)
	js.executeScript('window.open();')
	WebUI.switchToWindowIndex(currentTab + 1)
	WebUI.navigateToUrl(GlobalVariable.BE_URL)
	WebUI.setText(findTestObject('Object Repository/Helpdesk/Qasr/BE/Login/UserName'), GlobalVariable.BE_UserName)
	WebUI.setText(findTestObject('Object Repository/Helpdesk/Qasr/BE/Login/Password'), GlobalVariable.BE_Password)
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Login/LoginButton'))
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_Store'), 5)
	CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_Store'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_Store'))
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_Store_Configuration'), 5)
	CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_Store_Configuration'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_Store_Configuration'))
	String hideShow= WebUI.getAttribute(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_Store_Configuration_Snaptec'), 'class')
	if(hideShow.contains('_hide')) {
		CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_Store_Configuration_Snaptec'))
		WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_Store_Configuration_Snaptec'))
	}
	CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_Store_Configuration_Snaptec_ProductOnCart'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_Store_Configuration_Snaptec_ProductOnCart'))
	String popUpSKU = WebUI.getAttribute(findTestObject('Object Repository/Helpdesk/Qasr/BE/ConfigurationPage/PopupOnCartSKU'), 'value')
	WebUI.switchToWindowIndex(currentTab)
	return popUpSKU
}

int getpopupAvailableQty(String popUpSKU) {
	int currentTab = WebUI.getWindowIndex()
	WebUI.switchToWindowIndex(currentTab + 1)
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_Catalog'), 5)
	CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_Catalog'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_Catalog'))
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_Catalog_Product'), 5)
	CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_Catalog_Product'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Menu/Menu_Catalog_Product'))
	if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/BE/SmsLogPage/ClearFilter'), 10)) {
		WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/SmsLogPage/ClearFilter'))
	}
	WebUI.delay(2)
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/SmsLogPage/button_Filters'))
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/BE/ProductPage/sku'), 2)
	WebUI.setText(findTestObject('Object Repository/Helpdesk/Qasr/BE/ProductPage/sku'), popUpSKU)
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/BE/Sales_Order_page/Sales_orders_FilterButton'))
	TestObject qtyTO=new TestObject()
	qtyTO.addProperty('xpath', ConditionType.EQUALS, '//tr[@class="data-row"]/td[10]/div[@class="data-grid-cell-content"]')
	int qty =((int) Float.parseFloat(WebUI.getText(qtyTO).toString()))
	WebUI.switchToWindowIndex(currentTab )
	return qty
}

Void RemoveCartCookie() {
	CustomKeywords.'helpdesk.HelpdeskUtil.RemoveItemFromLocalStorage'("M2_VENIA_BROWSER_PERSISTENCE__product_on_cart")
//	WebUI.executeJavaScript('localStorage.removeItem("M2_VENIA_BROWSER_PERSISTENCE__product_on_cart")', null)
}