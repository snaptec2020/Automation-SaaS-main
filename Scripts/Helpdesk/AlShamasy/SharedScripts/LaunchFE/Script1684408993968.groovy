import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.util.concurrent.TimeUnit

import com.google.gson.Gson
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.network.ProxyInformation
import com.kms.katalon.core.network.ProxyOption
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




//ProxyInformation proxyInfo = RunConfiguration.getProxyInformation()
////ProxyInformation proxy = RunConfiguration.getProxyInformation()
//def proxy_ip = "65.160.224.144"
//def proxy_port = "80"
//proxyInfo.setProxyServerAddress(proxy_ip)
//proxyInfo.setProxyServerPort(proxy_port)
//
//// use the proxyInfo variable for what ever
//println proxyInfo


//ProxyInformation proxy = RunConfiguration.getProxyInformation()
//println(proxy)
//
//// Switch proxy
//proxy.setProxyOption(ProxyOption.MANUAL_CONFIG.name())
//proxy.setProxyServerAddress("65.108.90.92")
//proxy.setProxyServerPort(8080)
//Map<String, Object> generalProperties = RunConfiguration.getExecutionGeneralProperties();
//generalProperties.put(RunConfiguration.PROXY_PROPERTY, new Gson().toJson(proxy));



//WebUI.disableSmartWait()
WebUI.openBrowser('')
//DriverFactory.getWebDriver().manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS)

WebUI.maximizeWindow()
WebUI.navigateToUrl(GlobalVariable.FE_URL,FailureHandling.OPTIONAL)
//if(WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Shared/MailChimpIFrame'), 5, FailureHandling.OPTIONAL)) {
//	//Close the MailChimp ifram
//	WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Shared/MailChimpClose'),5)
//	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Shared/MailChimpClose'),5)
//	WebUI.click(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Shared/MailChimpClose'))
//}
//
//if(WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Shared/AllowCookie'), 5, FailureHandling.OPTIONAL)) {
//	//Close the MailChimp ifram
//	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Shared/AllowCookie'),5)
//	WebUI.click(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Shared/AllowCookie'))
//}

