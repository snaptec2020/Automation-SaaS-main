import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.webui.keyword.internal.WebUIKeywordMain
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebDriver as Keys


boolean isMobile=false
WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/QasrAlAwani/SharedScripts/LaunchFE'), [:],	FailureHandling.STOP_ON_FAILURE)
if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/BottomMenu-Mobile'), 10)) {
	isMobile=true
}else {
	isMobile=false
}

WebUI.closeBrowser()

//TC_1 not set permission
KeywordUtil.logInfo("TC_1 not set permission")
SetNotificationPermission(0)
WebUI.openBrowser('')
WebUI.maximizeWindow() 	
WebUI.navigateToUrl(GlobalVariable.FE_URL)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/SubscriptionNotificationIframe'),10)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/SubscriptionNotificationIframe'))
WebUI.switchToFrame(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/SubscriptionNotificationIframe'), 5)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/SubscriptionNotificationDialog-Allow'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/SubscriptionNotificationDialog-Deny'))
WebUI.closeBrowser()


//TC_2 Permission set to Allow
KeywordUtil.logInfo("TC_2 Permission set to Allow")
SetNotificationPermission(1)
WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl(GlobalVariable.FE_URL)
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/SubscriptionNotificationIframe'),10)
WebUI.closeBrowser()

//TC_3 Permission set to Deny
KeywordUtil.logInfo("TC_3 Permission set to Deny")
SetNotificationPermission(2)
WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl(GlobalVariable.FE_URL)
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/SubscriptionNotificationIframe'),10)
WebUI.closeBrowser()

//TC_4 Click Allow while it is not set
KeywordUtil.logInfo("TC_4 Click Allow while it is not set")
SetNotificationPermission(0)
WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl(GlobalVariable.FE_URL)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/SubscriptionNotificationIframe'),10)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/SubscriptionNotificationIframe'))
WebUI.switchToFrame(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/SubscriptionNotificationIframe'), 5)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/SubscriptionNotificationDialog-Allow'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/SubscriptionNotificationDialog-Deny'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/SubscriptionNotificationDialog-Allow'))
WebUI.switchToDefaultContent()
println  RunConfiguration.getDriverPreferencesProperties().get("WebUI")
println  RunConfiguration.getDriverPreferencesProperties().get("WebUI").getAt("prefs").getAt("profile.default_content_setting_values.notifications")
//Unable to handle verify the notification popup neither click Allow/Block due to selenium limitation
//WebUI.verifyMatch(RunConfiguration.getDriverPreferencesProperties().get("WebUI").getAt("prefs").getAt("profile.default_content_setting_values.notifications").toString(),"1",false)
WebUI.closeBrowser()

//TC_5 Click Deny while it is not set
KeywordUtil.logInfo("TC_5 Click Deny while it is not set")
SetNotificationPermission(0)
WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl(GlobalVariable.FE_URL)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/SubscriptionNotificationIframe'),10)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/SubscriptionNotificationIframe'))
WebUI.switchToFrame(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/SubscriptionNotificationIframe'), 5)
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/SubscriptionNotificationDialog-Allow'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/SubscriptionNotificationDialog-Deny'))
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/SubscriptionNotificationDialog-Deny'))
WebUI.switchToDefaultContent()
println  RunConfiguration.getDriverPreferencesProperties().get("WebUI")
println  RunConfiguration.getDriverPreferencesProperties().get("WebUI").getAt("prefs").getAt("profile.default_content_setting_values.notifications")
WebUI.verifyMatch(RunConfiguration.getDriverPreferencesProperties().get("WebUI").getAt("prefs").getAt("profile.default_content_setting_values.notifications").toString(),"0",false)
WebUI.closeBrowser()






//if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/SubscriptionNotificationIframe'), 10)) {
//	WebUI.switchToFrame(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/SubscriptionNotificationIframe'), 5)
//	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/SubscriptionNotificationDialog-Allow'))
//	WebUI.switchToDefaultContent()
//}
void SetNotificationPermission(int Permission) {
	Map<String, Object> NotificationPrefs = new HashMap<String, Object>()
	NotificationPrefs.put("profile.default_content_setting_values.notifications", Permission)
	RunConfiguration.setWebDriverPreferencesProperty('prefs',NotificationPrefs)
	
}
