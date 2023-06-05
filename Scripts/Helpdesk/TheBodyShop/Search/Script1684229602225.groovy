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
import java.util.List

import org.eclipse.persistence.jpa.jpql.parser.ConditionalTermBNF
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement


boolean isMobile=false

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/TheBodyShop/SharedScripts/LaunchFE'), [:],	FailureHandling.STOP_ON_FAILURE)
if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Shared/RightNav-Mobile'), 3)) {
	isMobile=true
}else {
	isMobile=false
}

WebUI.scrollToPosition(0,0)
WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/TheBodyShop/SharedScripts/OpenAndAddProductToCart'), [:],	FailureHandling.STOP_ON_FAILURE)


String productFullDetailName = ""
String SearchIcon=""
if(!isMobile) {
	productFullDetailName='Object Repository/Helpdesk/TheBodyShop/FE/Product/productFullDetail-Name'
	SearchIcon='Object Repository/Helpdesk/TheBodyShop/FE/Search/Search icon'
}else {
	productFullDetailName='Object Repository/Helpdesk/TheBodyShop/FE/Product/productFullDetail-Name-Mobile'
	SearchIcon='Object Repository/Helpdesk/TheBodyShop/FE/Search/Search icon-Mobile'
}
/////////////////////////
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject(productFullDetailName))
def ProductTitle = WebUI.getText(findTestObject(productFullDetailName))
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Product/productFullDetail-sku'))
def ProductSKU = WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Product/productFullDetail-sku')).findAll('\\(.*\\)').get(0).replace("#", "").replace("(", "").replace(")", "")
def ProductURL = WebUI.getUrl() //.replace(GlobalVariable.FE_URL, "")
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Product/Product_Price'))
def ProductPrice = WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Product/Product_Price')).replace("SAR", "").replace("ر.س", "").replace(" ", "")

println ProductTitle
println ProductSKU
println ProductURL
println ProductPrice

// Search By Title
WebUI.scrollToPosition(0,0)
WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/TheBodyShop/SharedScripts/ClickLogo'), [:],	FailureHandling.STOP_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject(SearchIcon))

WebUI.click(findTestObject(SearchIcon))


WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Search/Search Bar context'))
WebUI.setText(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Search/Search Bar context'), ProductTitle)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Search/Search Bar context'), Keys.chord(Keys.ENTER))



String SearchXPath='//h3//a[@href="' + ProductURL + '" and text()="' + ProductTitle + '"]'
println SearchXPath
TestObject Productlink_TO=new TestObject()
Productlink_TO.addProperty("xpath",ConditionType.EQUALS,SearchXPath)
//WebElement Productlink_Element = WebUiCommonHelper.findWebElement(Productlink_TO, 10)
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(Productlink_TO)
WebUI.waitForElementVisible(Productlink_TO, 10, FailureHandling.STOP_ON_FAILURE)
WebUI.verifyElementVisible(Productlink_TO, FailureHandling.STOP_ON_FAILURE)


// Search By SKU
WebUI.scrollToPosition(0,0)
WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/TheBodyShop/SharedScripts/ClickLogo'), [:],	FailureHandling.STOP_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject(SearchIcon))

//WebUI.click(findTestObject(SearchIcon))
WebUI.click(findTestObject(SearchIcon))

//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Search/Search Feild'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Search/Search Bar context'))
WebUI.setText(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Search/Search Bar context'), ProductSKU)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Search/Search Bar context'), Keys.chord(Keys.ENTER))

Productlink_TO=new TestObject()
Productlink_TO.addProperty("xpath",ConditionType.EQUALS,SearchXPath)
//WebElement Productlink_Element = WebUiCommonHelper.findWebElement(Productlink_TO, 10)
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(Productlink_TO)
WebUI.waitForElementVisible(Productlink_TO, 10, FailureHandling.STOP_ON_FAILURE)
WebUI.verifyElementVisible(Productlink_TO, FailureHandling.STOP_ON_FAILURE)


WebUI.delay(5)
WebUI.closeBrowser()