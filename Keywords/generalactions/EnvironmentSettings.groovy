package generalactions

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.utils.CustomLogger

import internal.GlobalVariable
import com.kms.katalon.core.webui.driver.DriverFactory
public class EnvironmentSettings {

	@Keyword
	String isRunningByMobile() {
		String runningMode='1'
		def driverPreferences = (Map<String,Object>)RunConfiguration.getDriverPreferencesProperties().get('WebUI')
		//CustomLogger.logInfo("----------------> ${driverPreferences.toString()}")
		//println mobileEmulation.get('mobileEmulation')
		if(driverPreferences != null) {
		if(driverPreferences.get('mobileEmulation')!=null || driverPreferences.get('platformName') == 'android' || driverPreferences.get('platformName') == 'ios') {
			return runningMode='2'
		}
		} else if((Map<String,Object>)RunConfiguration.getDriverPreferencesProperties().get('Mobile') != null) {
			return runningMode='3'
		} else {
			return runningMode
		}

	}



//    @Keyword
//    static String getEnvironmentType() {
//        try {
//            if (Mobile.getCurrentSessionMobileDriver() != null) {
//                return RunConfiguration.getDriverType() == 'WebUI' ? 'Mobile Browser' : 'Mobile App'
//            }
//            return 'Browser'
//        } catch (Exception e) {
//            return 'Unknown'
//        }
//    }
}
