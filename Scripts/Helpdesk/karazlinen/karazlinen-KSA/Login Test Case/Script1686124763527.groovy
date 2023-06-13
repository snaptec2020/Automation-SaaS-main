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
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

boolean isMobile=false


WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/karazlinen/karazlinen-KSA/SharedScripts/LaunchFE'), [:],	FailureHandling.STOP_ON_FAILURE)
isMobile=WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/karazlinen/karazlinen-KSA/SharedScripts/isMobile'), [:],	FailureHandling.CONTINUE_ON_FAILURE)
//if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/BottomMenu-Mobile'),3)) {
//	isMobile=true
//}


WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/karazlinen/karazlinen-KSA/SharedScripts/Login'), [:],	FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotPresent(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Login/OTPField'),5)
//WebUI.click(findTestObject(AccountIcon))
//WebUI.mouseOver(findTestObject(AccountIcon))
//WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/Login'),5)
//CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Login/MyAccount'),3)
//WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Login/MyAccount'))

String AccountIcon = "Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/AccountIcon"
if(!WebUI.waitForElementVisible(findTestObject(AccountIcon),3)) {
	AccountIcon="Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/AccountIcon-Mobile"
}

WebUI.click(findTestObject(AccountIcon))
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/AccountPage/AccountInfoIcon'), 10)
WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/AccountPage/AccountInfoIcon'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/AccountPage/AccountPageTitle'), 10)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/AccountPage/AccountPageTitle'), FailureHandling.STOP_ON_FAILURE)
/////////////////////////

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/karazlinen/karazlinen-KSA/SharedScripts/ClickLogo'), [:],	FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject(AccountIcon))
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/AccountPage/SignOut'),3)
WebUI.waitForPageLoad(10)

WebUI.delay(5)
WebUI.click(findTestObject(AccountIcon))
WebUI.waitForElementNotPresent(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/AccountPage/SignOut'),5)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Login/LoginTolephone'), 20)

WebUI.delay(5)
WebUI.closeBrowser()

