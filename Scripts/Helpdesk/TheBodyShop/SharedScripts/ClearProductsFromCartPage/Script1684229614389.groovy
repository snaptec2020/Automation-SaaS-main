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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper
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

WebUI.scrollToPosition(0,0)
String CartCounter = ""
String CartIcon=""
if(!isMobile) {
	CartCounter='Object Repository/Helpdesk/TheBodyShop/FE/Cart/CartCounter'
	CartIcon='Object Repository/Helpdesk/TheBodyShop/FE/Shared/Cart'
}else {
	CartCounter='Object Repository/Helpdesk/TheBodyShop/FE/Cart/CartCounter-Mobile'
	CartIcon='Object Repository/Helpdesk/TheBodyShop/FE/Shared/Cart-Mobile'
}
if(WebUI.waitForElementVisible(findTestObject(CartCounter),20)) {
	WebUI.click(findTestObject(CartIcon))
	WebUI.mouseOverOffset(findTestObject(CartIcon),10,10)
	CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Cart/ViewCartMainPageBtn'),10)
	TestObject removeProductFromCart = new TestObject()
	removeProductFromCart.addProperty('xpath', ConditionType.EQUALS, '//td[@class="col item"]//a[@title="أزل المنتج"]')
	WebUI.waitForElementVisible(removeProductFromCart, 10)
	List<WebElement> removeProductFromCartElements = WebUiCommonHelper.findWebElements(removeProductFromCart, 5)
	
	while (removeProductFromCartElements.size() != 0) {
		if (removeProductFromCartElements.size().equals(1)) {
			CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(removeProductFromCartElements.get(0))
			CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(removeProductFromCartElements.get(0), 10)
			removeProductFromCartElements.remove(0)
		} else {
			CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(removeProductFromCartElements.get(0))
			CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(removeProductFromCartElements.get(0), 10)
			removeProductFromCartElements = WebUiCommonHelper.findWebElements(removeProductFromCart, 10)
		}
	}
}

//
//WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Shared/Cart'),10)
//WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Shared/Cart'),5)
//CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Shared/Cart'), 10)
//
//
//WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Cart/ViewCartMainPageBtn'),5)
//
//if (WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Cart/ViewCartMainPageBtn'), 2,FailureHandling.OPTIONAL)) {
//	CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Cart/ViewCartMainPageBtn'),10)
//	
//	TestObject removeProductFromCart = new TestObject()
//	removeProductFromCart.addProperty('xpath', ConditionType.EQUALS, '//td[@class="col item"]//a[@title="إزالة منتج"]')
//	WebUI.waitForElementVisible(removeProductFromCart, 10)
//	List<WebElement> removeProductFromCartElements = WebUiCommonHelper.findWebElements(removeProductFromCart, 5)
//	
//	while (removeProductFromCartElements.size() != 0) {
//		if (removeProductFromCartElements.size().equals(1)) {
//			CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(removeProductFromCart, 10)
//			removeProductFromCartElements.remove(0)
//		} else {
//			CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(removeProductFromCart, 10)
//			removeProductFromCartElements = WebUiCommonHelper.findWebElements(removeProductFromCart, 10)
//		}
//	}
//}else {
//	WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/MiniCart/CloseMiniCart'),10)
//	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/MiniCart/CloseMiniCart'),5)
//	CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/MiniCart/CloseMiniCart'), 3)
//}
