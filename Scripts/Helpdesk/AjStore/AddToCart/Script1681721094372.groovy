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

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl('https://www.ajstore.com/')

List prod = CustomKeywords.'products.productsFromCatalog.getinStockProductFromOnePage'()

Random randomNumberforProduct = new Random()

def elementIndexproduct = Math.abs(randomNumberforProduct.nextInt(prod.size()))

def currentURL = WebUI.getUrl()

TestObject tb = new TestObject()

tb.addProperty('xpath', ConditionType.EQUALS, ('(//button[contains(text(),\'Add to Cart\') or contains(text(),\'أضف إلى السلة\')])[' +
	elementIndexproduct) + ']')

WebElement element = WebUiCommonHelper.findWebElement(tb, 30)

WebUI.executeJavaScript('arguments[0].click()', Arrays.asList(element))

if (WebUI.getUrl() == currentURL) {
	WebUI.click(findTestObject('Object Repository/Cart/Continue Shopping'), FailureHandling.CONTINUE_ON_FAILURE)
} else {
	KeywordUtil.markPassed('Trying to Get Configurable product')

	tb.addProperty('xpath', ConditionType.EQUALS, '//section[starts-with(@class,\'productFullDetail-groupOption-\')]//div[starts-with(@class,\'option-root-\')]')

	List genralDropDowns = WebUI.findWebElements(tb, 30)

	if (genralDropDowns.size() != 0) {
		for (int i = 1; i <= genralDropDowns.size(); i++) {
			tb.addProperty('xpath', ConditionType.EQUALS, ('(//section[starts-with(@class,\'productFullDetail-groupOption-\')]//div[starts-with(@class,\'option-root-\')])[' +
				i) + ']//div//button[not( @disabled)]')

			WebUI.click(tb)
		}
	}
}


///// Save Data in variable gettext getSKU





WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Shared/Logo'), 10)

//WebUI.callTestCase(findTestCase('Test Cases/FE/Website launch/Validations/Website launch'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Search/Search icon'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Search/Search icon'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Search/Search Feild'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Search/Search Bar context'))


WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Cart/Add to cart'))


WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Cart/Add to cart'))

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Cart/view cart'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Cart/view cart'))