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

String SearchResultsxpath='//table[@id="shopping-cart-table"]//a[@href="' + ProductURL + '" and contains(normalize-space(text()),normalize-space("' + ProductTitle + '"))]'
//'//table[@id="shopping-cart-table"]//strong/a[@href="' + ProductURL + '" and contains(normalize-space(text()),normalize-space("' + ProductTitle + '"))]'
println SearchResultsxpath
TestObject Productlink_TO=new TestObject()
Productlink_TO.addProperty("xpath",ConditionType.EQUALS,SearchResultsxpath)
WebElement Productlink_Element = WebUiCommonHelper.findWebElement(Productlink_TO, 10)
WebUI.waitForElementVisible(Productlink_TO, 10, FailureHandling.STOP_ON_FAILURE)
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(Productlink_Element)
WebUI.verifyElementVisible(Productlink_TO, FailureHandling.STOP_ON_FAILURE)

def ProductPrice_TO=new TestObject()
ProductPrice_TO.addProperty("xpath",ConditionType.EQUALS,Productlink_TO.findPropertyValue("xpath") + '/../../../../td/span/span/span[@class="price" and contains(text(),"' + ProductPrice.replace(".", "٫") + '")]')
WebUI.verifyElementVisible(ProductPrice_TO, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)
WebUI.closeBrowser()