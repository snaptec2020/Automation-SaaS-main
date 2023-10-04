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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.waitForElementVisible(findTestObject('Map Objs/Address title'), 0)
def phoneNumber = CustomKeywords.'generalactions.generalStrings.generateRandomPhoneNumber'('Map Objs/Address Phone')
KeywordUtil.logInfo(WebUI.getText(findTestObject('Map Objs/Address title'), FailureHandling.CONTINUE_ON_FAILURE))
if (WebUiCommonHelper.findWebElement(findTestObject('Map Objs/Address title'),5).getAttribute('value').isEmpty()) {


    def addressTitle = 'Automationtest ' + phoneNumber

    WebUI.setText(findTestObject('Map Objs/Address title'), addressTitle)
}
//KeywordUtil.logInfo(WebUI.getText(findTestObject('OTP/input Phone number').trim(), FailureHandling.CONTINUE_ON_FAILURE))
if(WebUiCommonHelper.findWebElement(findTestObject('OTP/input Phone number'), 5).getAttribute('value').isEmpty()) {
	
//WebUI.click(findTestObject('OTP/input Phone number'))
WebUI.setText(findTestObject('Map Objs/Address Phone'), phoneNumber)
}

WebUI.click(findTestObject('Map Objs/Continue After select Address'))