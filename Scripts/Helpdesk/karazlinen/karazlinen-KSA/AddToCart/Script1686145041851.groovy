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



boolean isMobile=false


WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/karazlinen/karazlinen-KSA/SharedScripts/LaunchFE'), [:],	FailureHandling.STOP_ON_FAILURE)

isMobile=WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/karazlinen/karazlinen-KSA/SharedScripts/isMobile'), [:],	FailureHandling.CONTINUE_ON_FAILURE)
//if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/BottomMenu-Mobile'),3)) {
//	isMobile=true
//}



WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/karazlinen/karazlinen-KSA/SharedScripts/LaunchFE'), [:],	FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/karazlinen/karazlinen-KSA/SharedScripts/OpenAndAddProductToCart'), [:],	FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/productFullDetail-Name'))
def ProductTitle = WebUI.getText(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/productFullDetail-Name'))
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/productFullDetail-sku'))
if(!WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/productFullDetail-sku'), 3)) {
	CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/DetailsSectionClosed'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/DetailsSectionClosed'))
}
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/productFullDetail-sku'))
def ProductSKU = WebUI.getText(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/productFullDetail-sku'))
def ProductURL = WebUI.getUrl() //.replace(GlobalVariable.FE_URL, "")
String PriceElement = 'Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/Product_Price'
if(!WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/Product_Price'), 3)) {
	PriceElement = 'Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/Product_SpecialPrice'
}

def ProductPrice = WebUI.getText(findTestObject(PriceElement))
def ProductPriceOrg = ProductPrice
ProductPrice=ProductPrice.replace("ر.س", "").replace(" ", "").replace(",", "")
println ProductTitle
println ProductSKU
println ProductURL
println ProductPrice

String CartIcon='Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/Cart'
if(!WebUI.waitForElementClickable(findTestObject(CartIcon),5)) {
	CartIcon='Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/Cart-Mobile'
}

WebUI.waitForElementClickable(findTestObject(CartIcon),10)
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject(CartIcon), 10)

//String SearchResultsxpath='//table[@id="shopping-cart-table"]//strong/a[@href="' + ProductURL + '" and contains(normalize-space(text()),normalize-space("' + ProductTitle + '"))]'
String SearchResultsxpath='//div[contains(@class,"cartPage-productsList-")]//li/a[@href="' + ProductURL.replace(GlobalVariable.FE_URL, '/ar-sa/') + '"]'
println SearchResultsxpath
TestObject Productlink_TO=new TestObject()
Productlink_TO.addProperty("xpath",ConditionType.EQUALS,SearchResultsxpath)
WebElement Productlink_Element = WebUiCommonHelper.findWebElement(Productlink_TO, 10)
WebUI.waitForElementVisible(Productlink_TO, 10, FailureHandling.STOP_ON_FAILURE)
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(Productlink_TO)
WebUI.verifyElementVisible(Productlink_TO, FailureHandling.STOP_ON_FAILURE)

def ProductPrice_TO=new TestObject()
//ProductPrice_TO.addProperty("xpath",ConditionType.EQUALS,Productlink_TO.findPropertyValue("xpath") + '/../../../../td/span/span/span[@class="price" and contains(text(),"' + ProductPrice + '")]')
ProductPrice_TO.addProperty("xpath",ConditionType.EQUALS,Productlink_TO.findPropertyValue("xpath") + '/..//div[contains(@class,"product-price-") or contains(@class,"productFull-price-")]')
WebUI.verifyMatch(WebUI.getText(ProductPrice_TO), ProductPriceOrg, false)

//WebUI.verifyElementVisible(ProductPrice_TO, FailureHandling.STOP_ON_FAILURE)
WebUI.delay(5)
WebUI.closeBrowser()