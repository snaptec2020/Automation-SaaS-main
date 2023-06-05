import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
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
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.SelectorMethod

import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword as Keyword
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword as WebUIAbstractKeyword
import catalog.catlogComponants
import groovy.inspect.swingui.BytecodeCollector

import java.util.List as List
import java.util.concurrent.ConcurrentHashMap.KeySetView as KeySetView
import org.eclipse.jdt.internal.compiler.ast.ForeachStatement as ForeachStatement
import org.openqa.selenium.By
import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.html5.Location
import org.openqa.selenium.html5.LocationContext
import org.openqa.selenium.remote.server.handler.GetCurrentUrl as WebElement
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
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.testobject.ConditionType
import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory



boolean isMobile=false

if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Shared/BottomMenu-Mobile'), 10)) {
	isMobile=true
}else {
	isMobile=false
}

WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Orange/FE/Cart/view cart'),10)
WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/Cart/view cart'))


if(!isMobile) {
	WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/MiniCart/MiniCartFrame'))
	if(WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/MiniCart/MiniCartShowCartButton'),FailureHandling.OPTIONAL)) {
		///Clear Cart
		WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/MiniCart/MiniCartShowCartButton'))
	}else {
		//Close the Mini Cart
		WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/Cart/view cart'))
	}
}

	
TestObject removeProductFromCart = new TestObject()
removeProductFromCart.addProperty('xpath', ConditionType.EQUALS, '//span[text()="إزالة بند"]//parent::button[contains(@class,"product-button")]')
List<WebElement> removeProductFromCartElements = WebUiCommonHelper.findWebElements(removeProductFromCart, 10)
while (removeProductFromCartElements.size() != 0) {
	if (removeProductFromCartElements.size().equals(1)) {
		CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(removeProductFromCartElements.get(0))
		CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(removeProductFromCartElements.get(0), 0)
		removeProductFromCartElements.remove(0)
	} else {
		CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(removeProductFromCartElements.get(0))
		CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(removeProductFromCartElements.get(0), 10)
		removeProductFromCartElements = WebUiCommonHelper.findWebElements(removeProductFromCart, 10)
	}
	WebUI.delay(1)
}

