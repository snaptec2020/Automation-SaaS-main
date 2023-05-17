import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.util.concurrent.TimeUnit

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys






//WebUI.disableSmartWait()
WebUI.openBrowser('')
//DriverFactory.getWebDriver().manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS)






WebUI.maximizeWindow()
WebUI.navigateToUrl(GlobalVariable.FE_URL,FailureHandling.OPTIONAL)
//if(WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/FE/Shared/MailChimpIFrame'), 5, FailureHandling.OPTIONAL)) {
//	//Close the MailChimp ifram
//	WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/FE/Shared/MailChimpClose'),5)
//	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/FE/Shared/MailChimpClose'),5)
//	WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/FE/Shared/MailChimpClose'))
//}

if(WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/FE/Shared/AllowCookie'), 5)) {
	//Close the MailChimp ifram
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/FE/Shared/AllowCookie'),5)
	WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/FE/Shared/AllowCookie'))
}

