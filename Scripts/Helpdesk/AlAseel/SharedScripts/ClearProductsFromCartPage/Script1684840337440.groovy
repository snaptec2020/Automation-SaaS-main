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
import catalog.catlogComponants as catlogComponants
import java.util.List as List
import java.util.concurrent.ConcurrentHashMap.KeySetView as KeySetView
import org.eclipse.jdt.internal.compiler.ast.ForeachStatement as ForeachStatement
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.server.handler.GetCurrentUrl as WebElement




///Clear Cart
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/Cart'),10)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/Cart'),10)
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/Cart'), 10)

TestObject removeProductFromCart = new TestObject()
removeProductFromCart.addProperty('xpath', ConditionType.EQUALS, '//div[@class="md-sidenav-right cdz-sidebar" and @style="top: 0px;"]//ol[@id="mini-cart"]//span[text()="ازالة"]//parent::a[@class="action delete" and @title="أزل المنتج"]')
WebUI.waitForElementVisible(removeProductFromCart, 10)
List<WebElement> removeProductFromCartElements = WebUiCommonHelper.findWebElements(removeProductFromCart, 10)

while (removeProductFromCartElements.size() != 0) {
	if (removeProductFromCartElements.size().equals(1)) {
		CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(removeProductFromCartElements.get(0), 10)
//        removeProductFromCartElements.get(0).click()
		WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/ConfirmDelete'),3,FailureHandling.OPTIONAL)
		if(WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/ConfirmDelete'),FailureHandling.OPTIONAL)) {
			WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/ConfirmDelete'))
			WebUI.waitForElementNotVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/ConfirmDelete'), 3)
		}
		removeProductFromCartElements.remove(0)
	} else {
		CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(removeProductFromCartElements.get(0), 10)
//        removeProductFromCartElements.get(0).click()
			WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/ConfirmDelete'),3,FailureHandling.OPTIONAL)
			if(WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/ConfirmDelete'),FailureHandling.OPTIONAL)) {
				WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/ConfirmDelete'))
				WebUI.waitForElementNotVisible(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/ConfirmDelete'), 3)
		}
		removeProductFromCartElements = WebUiCommonHelper.findWebElements(removeProductFromCart, 10)
	}
}
WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Cart/OutSideCart'))