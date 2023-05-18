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
CustomKeywords.'products.productsFromCatalog.OpenRandomProductAlShamasy'()
def ProductTitle = WebUI.getText(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Product/productFullDetail-Name'))
def ProductSKU = WebUI.getText(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Product/productFullDetail-sku'))
def ProductURL = WebUI.getUrl() //.replace(GlobalVariable.FE_URL, "")
def ProductPrice = WebUI.getText(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Product/Product_Price'),FailureHandling.OPTIONAL)

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Cart/Add to cart'),5)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Cart/Add to cart'),5)
WebUI.click(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Cart/Add to cart'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/MiniCart/CloseMiniCart'), 5)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/MiniCart/CloseMiniCart'),5)
if(WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/MiniCart/CloseMiniCart'), FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/MiniCart/CloseMiniCart'))
}else {
	// Check if qty accepted
	def trials=1
	while (!WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/MiniCart/CloseMiniCart'), FailureHandling.OPTIONAL) && trials<10) {
		//Open another Random Product
		if(WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Product/WarningPromptOK'), FailureHandling.OPTIONAL)) {
			WebUI.click(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Product/WarningPromptOK'))
		}
		trials = trials+1
		
		if(WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Shared/Logo'),5,FailureHandling.OPTIONAL)){
			WebUI.click(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Shared/Logo'),FailureHandling.OPTIONAL)
		}
		WebUI.waitForPageLoad(20)
		WebUI.delay(2)
		
		CustomKeywords.'products.productsFromCatalog.OpenRandomProductAlShamasy'()
		ProductTitle = WebUI.getText(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Product/productFullDetail-Name'))
		ProductSKU = WebUI.getText(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Product/productFullDetail-sku'))
		ProductURL = WebUI.getUrl() //.replace(GlobalVariable.FE_URL, "")
		ProductPrice = WebUI.getText(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Product/Product_Price'),FailureHandling.OPTIONAL)
		
		WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Cart/Add to cart'))
		WebUI.click(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Cart/Add to cart'))

		if(trials>=10) {
			assert false,"Could not find available products"
		}
		WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/MiniCart/CloseMiniCart'), 5)
		WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/MiniCart/CloseMiniCart'),5)
	}

	WebUI.click(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/MiniCart/CloseMiniCart'), FailureHandling.CONTINUE_ON_FAILURE)
}

