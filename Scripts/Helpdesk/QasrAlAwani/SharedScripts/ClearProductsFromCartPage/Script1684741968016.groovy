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
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.remote.server.handler.GetCurrentUrl

///Clear Cart
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Cart'))
if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCart'), 20)) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/PopupOnCartCancel'))
}
TestObject removeProductFromCart = new TestObject()

removeProductFromCart.addProperty('xpath', ConditionType.EQUALS, '//button[contains(@class,"product-button")]//span//div/img[@alt="Remove" and @loading="lazy"]')

List<WebElement> removeProductFromCartElements = WebUiCommonHelper.findWebElements(removeProductFromCart, 10)

while (removeProductFromCartElements.size() != 0) {
	if (removeProductFromCartElements.size().equals(1)) {
		CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(removeProductFromCartElements.get(0))
		removeProductFromCartElements.get(0).click()
		removeProductFromCartElements.remove(0)
	} else {
		CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(removeProductFromCartElements.get(0))
		removeProductFromCartElements.get(0).click()
		removeProductFromCartElements = WebUiCommonHelper.findWebElements(removeProductFromCart, 10)
	}
}