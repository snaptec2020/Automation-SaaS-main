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

//Open Random Product
//WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'), 3)
CustomKeywords.'helpdesk.HelpdeskUtil.OpenRandomProductQasr'()

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'), 10)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/Add to cart'))

if(WebUI.verifyElementNotHasAttribute(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/Add to cart'), 'disabled', 3,FailureHandling.OPTIONAL)) {
	CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/Add to cart'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/Add to cart'))
}

// Check if qty accepted
def trials=1
TestObject errorQTY_TO = new TestObject()
errorQTY_TO.addProperty("xpath",ConditionType.EQUALS,'//*[contains(text(),"Could not add the product" ) and contains(text(), "The requested qty is not available" )]')
List<WebElement> errorQTY_Element = WebUiCommonHelper.findWebElements(errorQTY_TO, 5)
while (	
		(errorQTY_Element.size()>0 | 
		WebUI.verifyElementHasAttribute(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/Add to cart'), 'disabled', 3, FailureHandling.OPTIONAL)
		) && trials<10) {
	//Open Random Product
	trials = trials+1
//	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))
	CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'), 3)
CustomKeywords.'helpdesk.HelpdeskUtil.OpenRandomProductQasr'()

	WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'), 10)
	WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/Add to cart'))
	CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/Add to cart'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Cart/Add to cart'))
	errorQTY_Element = WebUiCommonHelper.findWebElements(errorQTY_TO, 5)
	if(trials>=10) {
		assert false,"Could not find available products"
	}
}