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
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory


boolean isMobile=false


WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/QasrAlAwani/SharedScripts/LaunchFE'), [:],	FailureHandling.STOP_ON_FAILURE)
if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/BottomMenu-Mobile'), 10)) {
	isMobile=true
}else {
	isMobile=false
}
WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/QasrAlAwani/SharedScripts/Login'), [:],	FailureHandling.STOP_ON_FAILURE)


WebUI.verifyElementNotPresent(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/EnterOTP'), 2)

/////////////////////////
//WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'))
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Logo'), 3)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Login'))
if(!isMobile) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/AccountPage/SignOut'))
}else {
//	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/AccountPage/SignOut-Mobile'))
	CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/Qasr/FE/AccountPage/SignOut-Mobile'), 2)
}


if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/AppDownload_close'), 5)) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/AppDownload_close'))
}

if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/SubscriptionNotificationIframe'), 20)) {
	WebUI.switchToFrame(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/SubscriptionNotificationIframe'), 5)
	WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/SubscriptionNotificationDialog-Allow'))
	WebUI.switchToDefaultContent()
	//WebUI.delay(10)
	//WebUiBuiltInKeywords.acceptAlert(FailureHandling.OPTIONAL)
}

WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Shared/Login'))

WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'),20)
WebUI.click(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountry'))
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/Qasr/FE/Login/SelectCountryDropDownKSA'),10)

WebUI.closeBrowser()

