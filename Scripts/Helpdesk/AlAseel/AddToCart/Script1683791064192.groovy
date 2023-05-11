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

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/AlAseel/SharedScripts/LaunchFE'), [:],	FailureHandling.STOP_ON_FAILURE)

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



def ProductTitle = WebUI.getText(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Product/productFullDetail-Name'))
def ProductSKU = WebUI.getText(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Product/productFullDetail-sku'))
def ProductURL = WebUI.getUrl() //.replace(GlobalVariable.FE_URL, "")
def ProductPrice = WebUI.getText(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Product/Product_Price'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/Cart'),10)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/Cart'),10)
CustomKeywords.'products.productsFromCatalog.clickJS'(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/Cart'), 10)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/ViewCartMainPageBtn'),10)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/ViewCartMainPageBtn'),10)
CustomKeywords.'products.productsFromCatalog.clickJS'(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/ViewCartMainPageBtn'),10)

String SearchResultsxpath='//strong//a[@href="' + ProductURL + '" and contains(normalize-space(text()),normalize-space("' + ProductTitle + '"))]'
TestObject Productlink_TO=new TestObject()
Productlink_TO.addProperty("xpath",ConditionType.EQUALS,SearchResultsxpath)
WebElement Productlink_Element = WebUiCommonHelper.findWebElement(Productlink_TO, 10)
WebUI.waitForElementVisible(Productlink_TO, 10, FailureHandling.STOP_ON_FAILURE)
WebUI.verifyElementVisible(Productlink_TO, FailureHandling.STOP_ON_FAILURE)

def ProductPrice_TO=new TestObject()
ProductPrice_TO.addProperty("xpath",ConditionType.EQUALS,Productlink_TO.findPropertyValue("xpath") + '/../../../../td/span/span/span[@class="price" and contains(text(),"' + ProductPrice + '")]')
WebUI.verifyElementVisible(ProductPrice_TO, FailureHandling.STOP_ON_FAILURE)


WebUI.closeBrowser()