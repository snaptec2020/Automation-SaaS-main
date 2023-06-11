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

//Open Random Product
CustomKeywords.'helpdesk.HelpdeskUtil.OpenRandomProductkarazlinenKSA'()

/////////////////////////
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

ProductPrice=ProductPrice.replace("ر.س", "").replace(" ", "")
println ProductTitle
println ProductSKU
println ProductURL
println ProductPrice

//CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/IncreaseQty'))
//for(int i=1;i<50;i++) {
//	WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/IncreaseQty'))
//}

//WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Cart/Add to cart'),5)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Cart/Add to cart'),3)
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Cart/Add to cart'))
WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Cart/Add to cart'))

if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/MiniCart/CloseMiniCart'), 5)) {
	CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/MiniCart/CloseMiniCart'), 10)
}else {
	// Check if qty accepted
	def trials=1
	while (!WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/MiniCart/CloseMiniCart'), 5) && trials<10) {
		//Open another Random Product
		if(WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/WarningPromptOK'), FailureHandling.OPTIONAL)) {
			WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/OutSideError'))
			WebUI.delay(2)
		}
		trials = trials+1
		
		WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/karazlinen/karazlinen-KSA/SharedScripts/ClickLogo'), [:],	FailureHandling.STOP_ON_FAILURE)
		
		CustomKeywords.'helpdesk.HelpdeskUtil.OpenRandomProductkarazlinenKSA'()
		CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/productFullDetail-Name'))
		ProductTitle = WebUI.getText(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/productFullDetail-Name'))
		CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/productFullDetail-sku'))
		if(!WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/productFullDetail-sku'), 3)) {
			CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/DetailsSectionClosed'))
			WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/DetailsSectionClosed'))
		}
		CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/productFullDetail-sku'))
		ProductSKU = WebUI.getText(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/productFullDetail-sku'))
		ProductURL = WebUI.getUrl() //.replace(GlobalVariable.FE_URL, "")
		ProductPrice = WebUI.getText(findTestObject(PriceElement))
		
		ProductPrice=ProductPrice.replace("ر.س", "").replace(" ", "")
		println ProductTitle
		println ProductSKU
		println ProductURL
		println ProductPrice
		
		WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Cart/Add to cart'),3)
		CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Cart/Add to cart'))
		WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Cart/Add to cart'))

		if(trials>=10) {
			assert false,"Could not find available products"
		}
		WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/MiniCart/CloseMiniCart'),5)
	}

//	WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/MiniCart/CloseMiniCart'), FailureHandling.CONTINUE_ON_FAILURE)
	CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/MiniCart/CloseMiniCart'), 3)
}

