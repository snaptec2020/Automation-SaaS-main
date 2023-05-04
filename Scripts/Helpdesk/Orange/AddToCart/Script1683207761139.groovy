import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
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
import com.sun.org.apache.bcel.internal.generic.GOTO

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
import org.openqa.selenium.WebElement
import org.testng.TestRunner as WebElement

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.FE_URL)

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

//TestObject Productlink_TO=new TestObject()
TestObject Productlink = new TestObject()

if(ProductTitle.length()<=30) {
	Productlink.addProperty("xpath",ConditionType.EQUALS,'//a[@href="/' + ProductURL + '" and contains(text(),"' + ProductTitle + '")]')
}else {
	Productlink.addProperty("xpath",ConditionType.EQUALS,'//a[@href="/' + ProductURL + '" and contains(text(),"' + ProductTitle.substring(1,20) + '")]')
}
//WebElement Productlink_Element = WebUiCommonHelper.findWebElement(Productlink_TO, 10)
WebUI.verifyElementPresent(Productlink, 10)

TestObject ProductPrice_TO=new TestObject()
ProductPrice_TO.addProperty("xpath",ConditionType.EQUALS,Productlink.findPropertyValue("xpath") + '//../..//div[contains(@class,"product-subTotal-")]//div//span[1]')
WebUI.verifyElementVisible(ProductPrice_TO, FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()