import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.sql.Driver

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

import ch.qos.logback.core.joran.action.Action
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions



Actions actions = new Action();
WebUI.callTestCase(findTestCase('Test Cases/FE/Website launch/Mobile/Andriod/Browser/Launch website on Samsung S9'), [:], FailureHandling.STOP_ON_FAILURE)


WebUI.waitForElementPresent(findTestObject('Object Repository/Mega Menu/MenuSider on mobile'), 10, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementClickable(findTestObject('Object Repository/Mega Menu/MenuSider on mobile'), FailureHandling.STOP_ON_FAILURE)

WebUI.scrollToElement(findTestObject('Object Repository/Mega Menu/MenuSider on mobile'), 10)

//Actions actions = new Action();
actions.moveToElement(findTestObject('Object Repository/Mega Menu/MenuSider on mobile'))
//actions.moveToElement(findTestObject('Object Repository/Mega Menu/MenuSider on mobile'));
actions.click().perform();
//actions.sendKeys(“Some Name”);
//**actions.build().perform();
//WebUI.sendKeys(findTestObject, null, FailureHandling.STOP_ON_FAILURE)
//WebUI.click(findTestObject('Object Repository/Mega Menu/MenuSider on mobile'), FailureHandling.STOP_ON_FAILURE)