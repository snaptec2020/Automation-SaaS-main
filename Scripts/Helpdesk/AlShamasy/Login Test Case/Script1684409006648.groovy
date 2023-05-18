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


WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/AlShamasy/SharedScripts/LaunchFE'), [:],	FailureHandling.STOP_ON_FAILURE)


WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/AlShamasy/SharedScripts/Login'), [:],	FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotPresent(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Shared/Login'),3)
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Shared/Login'),3)

WebUI.click(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Login/MyAccount'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/AccountPage/AccountPageTitle'), 3)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/AccountPage/AccountPageTitle'), FailureHandling.STOP_ON_FAILURE)
/////////////////////////
WebUI.click(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Shared/Logo'))
WebUI.click(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/AccountPage/SignOut'))

WebUI.waitForElementNotPresent(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/AccountPage/SignOut'),3)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Shared/Login'),10)

WebUI.closeBrowser()

