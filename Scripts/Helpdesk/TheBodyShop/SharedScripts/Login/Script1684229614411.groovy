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
import java.awt.event.KeyEvent

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.Wait

import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory


WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Shared/Login'), 20)

WebUI.click(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Shared/Login'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Login/LoginTolephone'), 20)
WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Login/LoginTolephone')
, GlobalVariable.FE_EmailLogin)

WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Login/EmailLoginPassword')
	, GlobalVariable.FE_EmailPassword)
	
//WebUI.click(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Login/LoginButton'))
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Login/LoginButton'), 5)

//Wait wait
//String expectedValue = "أهلا " + GlobalVariable.CustomerName
//WebElement elm = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Shared/WelcomeCustomer'), 20)
//String actualValue = elm.getText().toLowerCase()
//wait.until(ExpectedConditions.textToBePresentInElement(elm, actualValue));

WebUI.waitForPageLoad(20)



