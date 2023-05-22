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

//WebUI.mouseOver(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Shared/AccountIcon'))


int trials=0
int trialCount = 50
while(true & trials<trialCount) {
	trials++
	CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Shared/Login'),3)
	
	WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Login/LoginTolephone'), 5)
	WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Login/LoginTolephone'), GlobalVariable.FE_EmailLogin)
	WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Login/EmailLoginPassword'), GlobalVariable.FE_EmailPassword)
	
	CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Login/LoginButton'),3)
	if(WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Login/CaptachIFrame'),FailureHandling.OPTIONAL)) {
		WebUI.back()
	}else {
		break
	}
	
	if(trials.equals(trialCount)) {
		assert false,"Could not login due to captcha"
		break
	}
	
}

