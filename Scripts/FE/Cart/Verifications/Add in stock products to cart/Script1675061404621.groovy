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
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
TestObject tb = new TestObject()
WebUI.callTestCase(findTestCase('Test Cases/FE/Products/General Actions/in Stock products'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

WebUI.verifyElementPresent(findTestObject('Object Repository/Products/Add To Cart'), 5, FailureHandling.STOP_ON_FAILURE)

tb.addProperty('xpath', ConditionType.EQUALS, '//div[contains(@class,\'attributesContainer_attributesContainer\')]//div[contains(@class,\'attributesContainer_optionsList\')]')
List genralDropDowns = WebUI.findWebElements(tb, 5)
if (genralDropDowns.size()!=0) {
	KeywordUtil.markPassed("Maybe it's a configurable product")
	CustomKeywords.'products.productsFromCatalog.configurableProduct'()
}

WebUI.waitForElementClickable(findTestObject('Object Repository/Products/Add To Cart'), 10)



