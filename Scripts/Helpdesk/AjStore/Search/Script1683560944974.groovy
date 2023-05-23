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

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/AjStore/SharedScripts/LaunchFE'), [:],	FailureHandling.STOP_ON_FAILURE)


CustomKeywords.'helpdesk.HelpdeskUtil.OpenRandomProductAJStore'()



def ProductTitle = WebUI.getText(findTestObject('Object Repository/Helpdesk/AjStore/FE/Product/productFullDetail-Name'))
def ProductSKU = WebUI.getText(findTestObject('Object Repository/Helpdesk/AjStore/FE/Product/productFullDetail-sku'))
def ProductURL = WebUI.getUrl().replace(GlobalVariable.FE_URL, "")
def ProductPrice = WebUI.getText(findTestObject('Object Repository/Helpdesk/AjStore/FE/Product/Product_Price'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Shared/Logo'), 10)


// Search By Title
WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/FE/Shared/Logo2'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Search/Search icon'))

CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/AjStore/FE/Search/Search icon'), 5)
WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/FE/Search/Search icon'))
if(WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Search/Search Feild'),FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/FE/Search/Search icon'))
	WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Search/Search icon'))
}
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Search/Search Feild'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Search/Search Bar context'))
WebUI.setText(findTestObject('Object Repository/Helpdesk/AjStore/FE/Search/Search Bar context'), ProductTitle)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AjStore/FE/Search/Search Bar context'), Keys.chord(Keys.ENTER))

TestObject Productlink_TO=new TestObject()
Productlink_TO.addProperty("xpath",ConditionType.EQUALS,'//a[@href="/' + ProductURL + '"]//h2[contains(text(),"' + ProductTitle + '")]')
//WebElement Productlink_Element = WebUiCommonHelper.findWebElement(Productlink_TO, 10)
WebUI.waitForElementVisible(Productlink_TO, 20, FailureHandling.STOP_ON_FAILURE)
WebUI.verifyElementVisible(Productlink_TO, FailureHandling.STOP_ON_FAILURE)


// Search By SKU
WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/FE/Shared/Logo2'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Search/Search icon'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/FE/Search/Search icon'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Search/Search Feild'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/FE/Search/Search Bar context'))
WebUI.setText(findTestObject('Object Repository/Helpdesk/AjStore/FE/Search/Search Bar context'), ProductSKU)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AjStore/FE/Search/Search Bar context'), Keys.chord(Keys.ENTER))

Productlink_TO=new TestObject()
Productlink_TO.addProperty("xpath",ConditionType.EQUALS,'//a[@href="/' + ProductURL + '"]//h2[contains(text(),"' + ProductTitle + '")]')
//WebElement Productlink_Element = WebUiCommonHelper.findWebElement(Productlink_TO, 10)
WebUI.waitForElementVisible(Productlink_TO, 10, FailureHandling.STOP_ON_FAILURE)
WebUI.verifyElementVisible(Productlink_TO, FailureHandling.STOP_ON_FAILURE)



WebUI.closeBrowser()