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
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys


WebUI.openBrowser('')
WebUI.maximizeWindow()


WebUI.navigateToUrl(GlobalVariable.BE_URL)

WebUI.setText(findTestObject('Object Repository/Helpdesk/AlShamasy/BE/Login/UserName'), GlobalVariable.BE_UserName)

	
//WebUI.setEncryptedText(findTestObject('Object Repository/Helpdesk/AlShamasy/BE/Login/Password'), 'h9YfHV16ZyMBoeJlmdP5xA==')
WebUI.setText(findTestObject('Object Repository/Helpdesk/AlShamasy/BE/Login/Password'), GlobalVariable.BE_Password)
WebUI.waitForPageLoad(10)
WebUI.click(findTestObject('Object Repository/Helpdesk/AlShamasy/BE/Login/LoginButton'),FailureHandling.CONTINUE_ON_FAILURE)
if(WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/AlShamasy/BE/Shared/SomethingWentWrong'), 10,FailureHandling.OPTIONAL) & WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/AlShamasy/BE/Shared/SomethingWentWrongOK'), 10,FailureHandling.OPTIONAL) ) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/AlShamasy/BE/Shared/SomethingWentWrongOK'))
}
WebUI.waitForPageLoad(20)

WebUI.click(findTestObject('Object Repository/Helpdesk/AlShamasy/BE/Menu/Menu_Reports'))
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlShamasy/BE/Menu/Menu_Reports_Orders'),5)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlShamasy/BE/Menu/Menu_Reports_Orders'),5)
WebUI.click(findTestObject('Object Repository/Helpdesk/AlShamasy/BE/Menu/Menu_Reports_Orders'))

//DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
//GregorianCalendar cal = new GregorianCalendar();
def now = new Date().format('MM/dd/yyyy')

WebUI.setText(findTestObject('Object Repository/Helpdesk/AlShamasy/BE/BEReports/Orders_From'),now.toString())
WebUI.setText(findTestObject('Object Repository/Helpdesk/AlShamasy/BE/BEReports/Orders_to'),now.toString())
WebUI.click(findTestObject('Object Repository/Helpdesk/AlShamasy/BE/BEReports/Orders_ShowReports'))

//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlShamasy/BE/BEReports/Empty_grid'))


WebUI.setText(findTestObject('Object Repository/Helpdesk/AlShamasy/BE/BEReports/Orders_From'),'1/1/23')
WebUI.setText(findTestObject('Object Repository/Helpdesk/AlShamasy/BE/BEReports/Orders_to'),'1/1/23')
WebUI.click(findTestObject('Object Repository/Helpdesk/AlShamasy/BE/BEReports/Orders_ShowReports'))

//long lastHeight=((Number) WebUI.executeJavaScript("return document.body.scrollHeight", null)).longValue();
WebUI.executeJavaScript("window.scrollTo(0, document.body.scrollHeight);", null);

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlShamasy/BE/BEReports/dataInOrderson01012023'), 10)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlShamasy/BE/BEReports/dataInOrderson01012023'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AlShamasy/BE/Menu/Menu_Sales'))
WebUI.executeJavaScript("arguments[0].scrollIntoView();", WebUiCommonHelper.findWebElements(findTestObject('Object Repository/Helpdesk/AlShamasy/BE/Menu/Menu_Sales_orders'), 10))

WebUI.click(findTestObject('Object Repository/Helpdesk/AlShamasy/BE/Menu/Menu_Sales_orders'))

WebUI.delay(2)
WebUI.closeBrowser()
