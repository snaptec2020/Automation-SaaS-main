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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.chrome.ChromeOptions as Keys
import org.openqa.selenium.html5.Location
import org.openqa.selenium.html5.LocationContext


//Map<String, Object> chromePrefs = new HashMap<String, Object>()
//chromePrefs.put("profile.default_content_setting_values.notifications", 1)
//RunConfiguration.setWebDriverPreferencesProperty('prefs',chromePrefs)
//
//// Only Chrome for now
//ChromeOptions options = new ChromeOptions()
//((Map)RunConfiguration.getDriverPreferencesProperties().get("WebUI")).each {
//	println it.getKey()
//	println it.getValue()
//	options.setExperimentalOption(it.getKey(), it.getValue())
//}
//WebUI.openBrowser('')
//WebUI.closeBrowser()
//
//ChromeDriver drivertest = new ChromeDriver(options)
//((LocationContext)drivertest).setLocation(new Location(GlobalVariable.Latitude,GlobalVariable.Longtitude , 0))
//
//
//DriverFactory.changeWebDriver(drivertest)
//
////WebUI.disableSmartWait()
//WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.BE_URL)
