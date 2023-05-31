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


boolean isMobile=false


WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/QasrAlAwani/SharedScripts/LaunchFE'), [:],	FailureHandling.STOP_ON_FAILURE)
if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/BottomMenu-Mobile'), 10)) {
	isMobile=true
}else {
	isMobile=false
}

//Open Random Product
WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/QasrAlAwani/SharedScripts/OpenAndAddProductToCart'), [:],	FailureHandling.STOP_ON_FAILURE)

def ProductTitle = WebUI.getText(findTestObject('Object Repository/Helpdesk/Qasr/FE/Product/productFullDetail-Name'))
def ProductSKU = WebUI.getText(findTestObject('Object Repository/Helpdesk/Qasr/FE/Product/productFullDetail-sku'))
def ProductURL = WebUI.getUrl().replace(GlobalVariable.FE_URL, "")
def ProductPrice = WebUI.getText(findTestObject('Object Repository/Helpdesk/Qasr/FE/Product/Product_Price'))


WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'), 10)

//WebUI.callTestCase(findTestCase('Test Cases/FE/Website launch/Validations/Website launch'), [:], FailureHandling.STOP_ON_FAILURE)
//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Search/Search icon'))
//
//WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Qasr/FE/Search icon'))
//
//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Qasr/FE/Search Feild'))
//
//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Qasr/FE/Search Bar context'))

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/view cart'))

WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/view cart'))

Productlink_TO=new TestObject()
Productlink_TO.addProperty("xpath",ConditionType.EQUALS,'//a[@href="/' + ProductURL + '" and contains(text(),"' + ProductTitle + '")]')
//WebElement Productlink_Element = WebUiCommonHelper.findWebElement(Productlink_TO, 10)
WebUI.waitForElementVisible(Productlink_TO, 10, FailureHandling.STOP_ON_FAILURE)
WebUI.verifyElementVisible(Productlink_TO, FailureHandling.STOP_ON_FAILURE)

def ProductPrice_TO=new TestObject()
ProductPrice_TO.addProperty("xpath",ConditionType.EQUALS,Productlink_TO.findPropertyValue("xpath") + '//../..//div[contains(@class,"product-subTotal-")]//div//span[1]')
WebUI.verifyElementVisible(ProductPrice_TO, FailureHandling.STOP_ON_FAILURE)


WebUI.closeBrowser()