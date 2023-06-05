import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.html5.Location
import org.openqa.selenium.html5.LocationContext

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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword as Keyword
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword as WebUIAbstractKeyword
import catalog.catlogComponants as catlogComponants
import java.util.List as List
import java.util.concurrent.ConcurrentHashMap.KeySetView as KeySetView
import org.eclipse.jdt.internal.compiler.ast.ForeachStatement as ForeachStatement
import org.openqa.selenium.By as By
import org.openqa.selenium.remote.DesiredCapabilities


//Map chromeOptions =new HashMap<String, Object>()
//chromeOptions.put("deviceName", "iPhone 6/7/8 Plus")
//RunConfiguration.setWebDriverPreferencesProperty('mobileEmulation', chromeOptions)

Map prefs = new HashMap<String, Object>()


prefs.put("profile.default_content_setting_values.geolocation", 1) // 1:allow 2:block
RunConfiguration.setWebDriverPreferencesProperty('prefs', prefs)

// Only Chrome for now
ChromeOptions options = new ChromeOptions()
((Map)RunConfiguration.getDriverPreferencesProperties().get("WebUI")).each {
	println it.getKey()
	println it.getValue()
	options.setExperimentalOption(it.getKey(), it.getValue())
}

WebUI.openBrowser('')
WebUI.closeBrowser()

//DesiredCapabilities capabilities = new DesiredCapabilities();
//capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//DesiredCapabilities capabilities = new DesiredCapabilities();
//capabilities.setPlatform(Platform.ANDROID)
//capabilities.setCapability(ChromeOptions.CAPABILITY, options);

ChromeDriver drivertest = new ChromeDriver(options)
((LocationContext)drivertest).setLocation(new Location(GlobalVariable.Latitude,GlobalVariable.Longtitude , 0))


DriverFactory.changeWebDriver(drivertest)

//DesiredCapabilities capabilities = new DesiredCapabilities();
//HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
//browserstackOptions.put("gpsLocation", "24.70437792668048,46.67946359838521");
//RunConfiguration.setWebDriverPreferencesProperty("args",browserstackOptions)

WebUI.navigateToUrl(GlobalVariable.FE_URL)

WebUI.maximizeWindow()


