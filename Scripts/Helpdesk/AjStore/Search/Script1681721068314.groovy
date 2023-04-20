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
import org.codehaus.groovy.runtime.powerassert.AssertionRenderer as AssertionRenderer
import org.codehaus.groovy.tools.shell.completion.KeywordSyntaxCompletor as KeywordSyntaxCompletor
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

def ProductName = WebUI.getText(findTestObject('Object Repository/Helpdesk/AjStore/Product/productFullDetail-Name'))

def ProductSKU = WebUI.getText(findTestObject('Object Repository/Helpdesk/AjStore/Product/productFullDetail-sku'))

def producURL = WebUI.getUrl()
println producURL

producURL = producURL.replace(GlobalVariable.FE_URL, '')
println GlobalVariable.FE_URL
println producURL





WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Shared/Logo'), 10)





//Search by Name
WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Shared/Logo2'))


//WebUI.callTestCase(findTestCase('Test Cases/FE/Website launch/Validations/Website launch'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Search/Search icon'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Search/Search icon'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Search/Search Feild'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Search/Search Bar context'))


WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AjStore/Search/Search Feild'), ProductName)

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Search/SearchBarResults'), 10)

if (WebUI.getText(findTestObject('Object Repository/Helpdesk/AjStore/Search/SearchBarResults')).trim().equals('لاتوجد نتائج')) {
	assert false
}else if(WebUI.getText(findTestObject('Object Repository/Helpdesk/AjStore/Search/SearchBarResults')).trim().indexOf(' عنصر') > 0) {
	assert true
}else {
	assert false
	
}
//if (!(WebUI.verifyElementText(findTestObject('Object Repository/Helpdesk/AjStore/Search/SearchBarResults'), '1 عنصر', FailureHandling.CONTINUE_ON_FAILURE))) {
//    //لاتوجد نتائج 
//    def resultsInBar = WebUI.getText(findTestObject('Object Repository/Helpdesk/AjStore/Search/SearchBarResults'))
//
//    if (resultsInBar.trim().equals('لاتوجد نتائج')) {
//        assert false
//    }
//}

WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AjStore/Search/Search Feild'), Keys.chord(Keys.ENTER))

TestObject searchRestb = new TestObject()

searchRestb.addProperty('xpath', ConditionType.EQUALS, '//h1[contains(@class,\'searchPage-heading-\')]')

WebElement searchRes = WebUiCommonHelper.findWebElement(searchRestb, 30)

if (!(searchRes.getText().equals(('عرض النتائج ل' + ProductName) + ':'))) {
    assert false
}

TestObject productInResults = new TestObject()

productInResults.addProperty('xpath', ConditionType.EQUALS, "//a[@href='/" + producURL + "']")

WebUI.waitForElementClickable(productInResults, 10)




//Search by SKU
WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Shared/Logo2'))

WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AjStore/Search/Search Feild'), ProductSKU)

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Search/SearchBarResults'), 10)

if (WebUI.getText(findTestObject('Object Repository/Helpdesk/AjStore/Search/SearchBarResults')).trim().equals('لاتوجد نتائج')) {
	assert false
}else if(WebUI.getText(findTestObject('Object Repository/Helpdesk/AjStore/Search/SearchBarResults')).trim().indexOf(' عنصر') > 0) {
	assert true
}else {
	assert false
	
}
//if (!(WebUI.verifyElementText(findTestObject('Object Repository/Helpdesk/AjStore/Search/SearchBarResults'), '1 عنصر', FailureHandling.CONTINUE_ON_FAILURE))) {
//    //لاتوجد نتائج
//    def resultsInBar = WebUI.getText(findTestObject('Object Repository/Helpdesk/AjStore/Search/SearchBarResults'))
//
//    if (resultsInBar.trim().equals('لاتوجد نتائج')) {
//        assert false
//    }
//}

WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AjStore/Search/Search Feild'), Keys.chord(Keys.ENTER))

searchRestb = new TestObject()

searchRestb.addProperty('xpath', ConditionType.EQUALS, '//h1[contains(@class,\'searchPage-heading-\')]')

searchRes = WebUiCommonHelper.findWebElement(searchRestb, 30)

if (!(searchRes.getText().equals(('عرض النتائج ل' + ProductSKU) + ':'))) {
    assert false
}

productInResults = new TestObject()

productInResults.addProperty('xpath', ConditionType.EQUALS, "//a[@href='/" + producURL + "']")

WebUI.waitForElementClickable(productInResults, 10)

WebUI.closeBrowser()
