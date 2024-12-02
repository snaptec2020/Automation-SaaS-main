import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.utils.CustomLogger

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

//KeywordUtil.logInfo(RunConfiguration.getExecutionProfile())
//Map geoLocationOption =[:]
//geoLocationOption.put("latitude",21.3369007)
//geoLocationOption.put("longitude",39.1291145)
//RunConfiguration.setWebDriverPreferencesProperty('Emulation.setGeolocationOverride', geoLocationOption)
switch (GlobalVariable.launchingConfig.get('Mode')) {
    case 'FE':
        WebUI.openBrowser('')

        WebUI.navigateToUrl(GlobalVariable.URL)

        CustomKeywords.'generalactions.generalActions.waiteSpinnerToHide'()

        WebUI.callTestCase(findTestCase('FE/Website launch/Validations/Add locatin New workflow'), [:], FailureHandling.STOP_ON_FAILURE)

        break
    case 'BE':
        WebUI.openBrowser('')

        switch (GlobalVariable.launchingConfig.get('BEMode')) {
            case 'MID':
                WebUI.navigateToUrl(GlobalVariable.MID_URL.get(GlobalVariable.launchingConfig.get('SiteUrl')))

                break
        }
}

GlobalVariable.RunningMode = CustomKeywords.'generalactions.EnvironmentSettings.isRunningByMobile'()


if (GlobalVariable.RunningMode == "1") {
    WebUI.maximizeWindow(FailureHandling.CONTINUE_ON_FAILURE)
}