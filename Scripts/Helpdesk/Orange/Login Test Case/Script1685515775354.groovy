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


WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/Orange/SharedScripts/LaunchFE'), [:],	FailureHandling.STOP_ON_FAILURE)
if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Shared/BottomMenu-Mobile'), 5)) {
	isMobile=true
}else {
	isMobile=false
}

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/Orange/SharedScripts/Login'), [:],	FailureHandling.STOP_ON_FAILURE)

/////////////////////////
WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/Shared/Logo'))

if(!isMobile) {
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Orange/FE/Shared/Login'), 20)
	WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/Shared/Login'))
	
}else {
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Orange/FE/Shared/Login-Mobile'), 20)
	WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/Shared/Login-Mobile'))
	
}

CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/Orange/FE/AccountPage/SignOut'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/AccountPage/SignOut'))

WebUI.delay(2)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Login/LoginTolephone'), 20)

WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Orange/FE/Login/LoginTolephone'), 20)

//WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Orange/FE/Login/SelectCountry'),20)
//WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/Login/SelectCountry'))
//WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Orange/FE/Login/SelectCountryDropDownKSA'),10)

WebUI.closeBrowser()

