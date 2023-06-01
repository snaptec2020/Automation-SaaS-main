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
CustomKeywords.'helpdesk.HelpdeskUtil.OpenRandomProductTheBeautySecrets'()
WebElement MoreDetailsPlus = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Product/MoreDetailsPlus'), 3)
if(!MoreDetailsPlus.getAttribute("class").contains("active")) {
	CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Product/MoreDetailsPlus'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Product/MoreDetailsPlus'))
}

def ProductTitle = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Product/productFullDetail-Name'), 5).getText().split("\n")[0]
def ProductSKU = WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Product/productFullDetail-sku')).replace(":", "").replace(" ", "")
def ProductURL = WebUI.getUrl() //.replace(GlobalVariable.FE_URL, "")
def ProductPrice = ""
if(WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Product/SpecialPrice'), 2,FailureHandling.OPTIONAL)) {
	ProductPrice = WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Product/SpecialPrice')).replace("٫", ".").replace("ر.س.", "")
}else {
	ProductPrice = WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Product/Product_Price')).replace("٫", ".").replace("ر.س.", "")
}

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/Add to cart'),5)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/Add to cart'),5)
//WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/Add to cart'))
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/Add to cart'))
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/Add to cart'), 0)

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/MiniCart/ContinueShoping'), 5)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/MiniCart/ContinueShoping'),5)
if(WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/MiniCart/ContinueShoping'), FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/MiniCart/ContinueShoping'))
}else {
	// Check if qty accepted
	def trials=1
	while (!WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/MiniCart/ContinueShoping'), FailureHandling.OPTIONAL) && trials<10) {
		//Open another Random Product
		trials = trials+1
		CustomKeywords.'helpdesk.HelpdeskUtil.OpenRandomProductTheBeautySecrets'()
		CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Product/MoreDetailsPlus'))
		WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Product/MoreDetailsPlus'))
		ProductTitle = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Product/productFullDetail-Name'), 5).getText().split("\n")[0]
		ProductSKU = WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Product/productFullDetail-sku')).replace(":", "").replace(" ", "")
		ProductURL = WebUI.getUrl() //.replace(GlobalVariable.FE_URL, "")
		ProductPrice = ""
		if(WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Product/SpecialPrice'), 2,FailureHandling.OPTIONAL)) {
			ProductPrice = WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Product/SpecialPrice')).replace("٫", ".").replace("ر.س.", "")
		}else {
			ProductPrice = WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Product/Product_Price')).replace("٫", ".").replace("ر.س.", "")
		}
		
		WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/Add to cart'))
//		WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/Add to cart'))
		CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/Add to cart'))
		CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/Add to cart'), 0)
		
		if(trials>=10) {
			assert false,"Could not find available products"
		}
		WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/MiniCart/ContinueShoping'), 5)
		WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/MiniCart/ContinueShoping'),5)
	}

	WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/MiniCart/ContinueShoping'), FailureHandling.CONTINUE_ON_FAILURE)
}

