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

boolean isMobile=false
if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Shared/RightNav-Mobile'), 3)) {
	isMobile=true
}else {
	isMobile=false
}

String CartCounter = ""
String CartIcon=""
if(!isMobile) {
	CartCounter='Object Repository/Helpdesk/TheBodyShop/FE/Cart/CartCounter'
	CartIcon='Object Repository/Helpdesk/TheBodyShop/FE/Shared/Cart'
}else {
	CartCounter='Object Repository/Helpdesk/TheBodyShop/FE/Cart/CartCounter-Mobile'
	CartIcon='Object Repository/Helpdesk/TheBodyShop/FE/Shared/Cart-Mobile'
}

//Open Random Product
CustomKeywords.'helpdesk.HelpdeskUtil.OpenRandomProductTBS'()
def ProductTitle = WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Product/productFullDetail-Name'))
def ProductSKU = WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Product/productFullDetail-sku'))
def ProductURL = WebUI.getUrl() //.replace(GlobalVariable.FE_URL, "")
def ProductPrice = WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Product/Product_Price'),FailureHandling.OPTIONAL)

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Product/AddToCartFromProduct'),5)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Product/AddToCartFromProduct'),5)

List<WebElement> counter = WebUiCommonHelper.findWebElements(findTestObject(CartCounter), 5)
int noOfItemsBefore = 0
if (counter.size()>0) {
	noOfItemsBefore=Integer.parseInt(counter.get(0).findElement(By.xpath("./span[@class='counter-number']")).getText())
}

println noOfItemsBefore

CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Product/AddToCartFromProduct'), 2)

WebUI.scrollToPosition(0,0)
WebUI.waitForElementVisible(findTestObject(CartCounter),10)

counter = WebUiCommonHelper.findWebElements(findTestObject(CartCounter), 5)
int noOfItemsAfter = 0
if (counter.size()>0) {
	noOfItemsAfter=counter.get(0).findElement(By.xpath("./span[@class='counter-number']")).getText().toInteger()
}
println noOfItemsAfter

//WebUI.mouseOver(findTestObject(CartIcon))
//WebUI.mouseOver(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Cart/MiniCartDialog'))
//WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Cart/MiniCartDialog'),10)
//WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Cart/ViewCartMainPageBtn'),10)
//WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Cart/ViewCartMainPageBtn'),10)
//CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Cart/ViewCartMainPageBtn'),10)

if(noOfItemsAfter!=noOfItemsBefore+1) {
	// Check if qty accepted
	def trials=1
	while ((noOfItemsAfter!=noOfItemsBefore+1) && trials<10) {
		//Open another Random Product
		trials = trials+1
		WebUI.scrollToPosition(0,0)
		if(WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Shared/Logo'),5,FailureHandling.OPTIONAL)){
			WebUI.click(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Shared/Logo'),FailureHandling.OPTIONAL)
		}
		WebUI.waitForPageLoad(20)
		WebUI.delay(2)
		
		CustomKeywords.'helpdesk.HelpdeskUtil.OpenRandomProductTBS'()
		ProductTitle = WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Product/productFullDetail-Name'))
		ProductSKU = WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Product/productFullDetail-sku'))
		ProductURL = WebUI.getUrl() //.replace(GlobalVariable.FE_URL, "")
		ProductPrice = WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Product/Product_Price'),FailureHandling.OPTIONAL)
		
		WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Product/AddToCartFromProduct'),5)
		WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Product/AddToCartFromProduct'),5)
		
		counter = WebUiCommonHelper.findWebElements(findTestObject(CartCounter), 5)
		noOfItemsBefore = 0
		if (counter.size()>0) {
			noOfItemsBefore=Integer.parseInt(counter.get(0).findElement(By.xpath("./span[@class='counter-number']")).getText())
		}
		println noOfItemsBefore
		CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Product/AddToCartFromProduct'), 2)
		WebUI.scrollToPosition(0,0)
		WebUI.waitForElementVisible(findTestObject(CartCounter),10)
		counter = WebUiCommonHelper.findWebElements(findTestObject(CartCounter), 5)
		noOfItemsAfter = 0
		if (counter.size()>0) {
			noOfItemsAfter=counter.get(0).findElement(By.xpath("./span[@class='counter-number']")).getText().toInteger()
		}
		println noOfItemsAfter
	}
}

