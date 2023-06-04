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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import groovy.util.slurpersupport.GPathResult
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys


String runningSource = RunConfiguration.getExecutionSource()

if(runningSource.substring(runningSource.lastIndexOf(".")).equals(".ts")) {
	println "found Test Suit"
	println runningSource
	ReRunSuiteOnMobileVersions()
}else if(runningSource.substring(runningSource.lastIndexOf(".")).equals(".tc")) {
	println "found Test Case"
	println runningSource
	ReRunScriptsInSameLevelOnMobileVersions()
}else {
	println runningSource.substring(runningSource.lastIndexOf("."))
	println runningSource
}

def ReRunSuiteOnMobileVersions() {
	String runningSource = RunConfiguration.getExecutionSource()
	String xmlText = new File(runningSource).getText()
	GPathResult testList = new XmlSlurper().parseText(xmlText)
	List<List<String>> productList =findTestData("Data Files/Mobile/Mobile sizes").getAllData()
	Map chromeOptions =new HashMap<String, Object>()
	for(int i=0;i<productList.size();i++) {
		chromeOptions.put("deviceName", productList.get(i).get(0))
		RunConfiguration.setWebDriverPreferencesProperty('mobileEmulation', chromeOptions)
		testList.testCaseLink.each {
			if(it.isRun.toString().equals("true") & !it.testCaseId.toString().equals('Test Cases/Shared/RunOnAllMobileWebBrowsers')) {
				try {
					WebUI.callTestCase(findTestCase(it.testCaseId.toString()), [:],	FailureHandling.CONTINUE_ON_FAILURE)
				}catch(Exception e) {
					KeywordUtil.markFailed("Mobile test Failed with Mobile version: " + productList.get(i).get(0))
				}
			}
		 }
		 chromeOptions.remove("deviceName")
		 RunConfiguration.setWebDriverPreferencesProperty('mobileEmulation', chromeOptions)
	}
	
}

def ReRunScriptsInSameLevelOnMobileVersions() {
	
}