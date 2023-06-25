import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.util.Map.Entry as Entry
import java.util.logging.Level as Level
import java.util.logging.Logger as Logger
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.context.TestCaseContext as TestCaseContext
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseBinding as TestCaseBinding
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.util.internal.JsonUtil as JsonUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword as WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import groovy.util.slurpersupport.GPathResult
import internal.GlobalVariable as GlobalVariable
import org.apache.commons.io.FileUtils as FileUtils
import org.apache.poi.hssf.record.CountryRecord as CountryRecord
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.util.internal.XMLUtil as XMLUtil
import com.kms.katalon.core.logging.XmlKeywordLogger as XmlKeywordLogger

//TestCaseContext tcx //= new TestCaseContext()
TestObject tb = new TestObject()

//XmlKeywordLogger xmlKeywordLogger

//KeywordLogger logger = KeywordLogger.getInstance(getClass())

//Logger loggerLevels = Logger.getLogger(XmlKeywordLogger.class.getName())

//loggerLevels.setLevel(Level.OFF)

String projectDir = RunConfiguration.getProjectDir()

String testSuiteDir = ((projectDir + '/') + GlobalVariable.testSuiteStatus) + '.ts'

KeywordUtil.logInfo(testSuiteDir)

def multiwebsiteDrop = findTestObject('Object Repository/Multi Sites/Multi site dropdown menu')

def sitesCount = 0

switch (GlobalVariable.RunningMode) {
    case '1':
        multiwebsiteDrop.findProperty('xpath').setValue('//div[@class=\'store-container\']//div[@id=\'storeTriggerDropdownMenuButton\']')

        sitesCount = WebUI.findWebElements(multiwebsiteDrop, 30).size()

        break
    case '2':
        WebUI.click(findTestObject('Object Repository/Mega Menu/MegaMenuefromMobile'))

        multiwebsiteDrop.findProperty('xpath').setValue('//div[@class=\'store-switcher\']//div[@id=\'storeTriggerDropdownMenuButton\']')

        sitesCount = WebUI.findWebElements(multiwebsiteDrop, 30).size()

        WebUI.click(findTestObject('Object Repository/Mega Menu/Close MM Mobile'))

        break
}

if (sitesCount != 0) {
    //def tc = XMLUtil.
    /*
 * for (int i = 0; i < 2; i++) {
 * KeywordUtil.logInfo(bindings.testCaseLink[i].testCaseId[i].value.toString())
 * }
 */
    openMultiSiteDrop(multiwebsiteDrop)

    List sites = WebUI.findWebElements(findTestObject('Object Repository/Multi Sites/Multi sites Sites context'), 30)

    if (sites.size() != 0) {
        /*
			  * WebElement element = WebUiCommonHelper.findWebElement(findTestObject('Object
			  * Repository/Multi Sites/Multi sites Sites context'),30) element.
			  */
        List storesToVisit = []

        for (int i = 0; i <= (sites.size() - 1); i++) {
            //tb.addProperty('xpath', ConditionType.EQUALS,findTestObject('Object Repository/Multi Sites/Multi sites Sites context').add+"["+i+"]")
            storesToVisit << (('//div[@class=\'dropdown-menu show\']//button[contains(@class,\'dropdown-item drop-item\')][text()=\'' + 
            sites.get(i).text) + '\']' //KeywordUtil.logInfo(sites.get(i).text)
            ) //WebUI.click(findTestObject('Object Repository/Multi Sites/Multi sites Sites context').addProperty('xpath', ConditionType.EQUALS, "["+sites.get(i).text+"]"))
            //WebUI.delay(10)
        }
        
        String testSuiteFile = new File(testSuiteDir).getText()

        //List<String>
        //def bindings = new XmlParser().parse(testSuiteFile)
		GPathResult bindings = new XmlSlurper().parseText(testSuiteFile)
       // loggerLevels.setLevel(Level.ALL)

        storesToVisit.each({ def val ->
                //KeywordUtil.logInfo(val)
				String country = ((val =~ 'text\\(\\)=\'(.*?)\'')[0])[1]

				//logger.logInfoHighlight(('>>>>>>>>>>>>>>>>>>>>>>>>> This ' + country) + ' Started <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<')
				KeywordUtil.logger.logInfoHighlight(('>>>>>>>>>>>>>>>>>>>>>>>>> This ' + country) + ' Started <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<')
                tb.addProperty('xpath', ConditionType.EQUALS, val)



                WebUI.click(tb)

                WebUI.delay(10)

		bindings.testCaseLink.each {
			if(it.isRun.toString().equals("true") & !it.testCaseId.toString().equals('Test Cases/FE/Multi Sites/Multisites')) {
				try {
					WebUI.callTestCase(findTestCase(it.testCaseId.toString()), [:],	FailureHandling.CONTINUE_ON_FAILURE)
				}catch(Exception e) {
					KeywordUtil.markFailed("This Test case is not success " + it.testCaseId.toString())
				}
			}
		 }
			/*
			 * bindings.testCaseLink.each({ def bk -> //
			 * KeywordUtil.logInfo("$bk.testCaseId.text()") String tescCaseId =
			 * "$bk.testCaseId.text()"
			 * 
			 * String isRun = "$bk.isRun.text()"
			 * 
			 * KeywordUtil.logInfo((tescCaseId + '\t Is Run = \t') + isRun) if ((tescCaseId
			 * != 'Test Cases/FE/Multi Sites/Multisites') && isRun.equals('true')) {
			 * WebUI.callTestCase(findTestCase(tescCaseId), [:],
			 * FailureHandling.CONTINUE_ON_FAILURE) }
			 * 
			 * //logger.logInfoHighlight(('>>>>>>>>>>>>>>>>>>>>>>>>> This ' + country) + '
			 * Ended <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<')
			 * //loggerLevels.setLevel(Level.FINEST) })
			 */
				KeywordUtil.logger.logInfoHighlight(('>>>>>>>>>>>>>>>>>>>>>>>>> This ' + country) + ' Ended <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<')
                //CustomKeywords.'generalactions.reporting.exportKatalonReports'(testSuiteContext)
                openMultiSiteDrop(multiwebsiteDrop)
            })
    } //WebUI.click(findTestObject('Object Repository/Mega Menu/Close MM Mobile'))
}

def openMultiSiteDrop(def multiwebsiteDrop) {
    switch (GlobalVariable.RunningMode) {
        case '1':
            WebUI.click(multiwebsiteDrop)

            break
        case '2':
            WebUI.click(findTestObject('Object Repository/Mega Menu/MegaMenuefromMobile'))

            WebUI.click(multiwebsiteDrop)

            break
    }
}