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
import com.kms.katalon.core.logging.XmlKeywordLogger
import com.kms.katalon.core.main.TestCaseMain
import com.kms.katalon.core.main.TestCaseMain as XmlKeywordLogger

//TestCaseContext tcx //= new TestCaseContext()


//XmlKeywordLogger xmlKeywordLogger

//KeywordLogger logger = KeywordLogger.getInstance(getClass())

//Logger loggerLevels = Logger.getLogger(XmlKeywordLogger.class.getName())

//loggerLevels.setLevel(Level.OFF)
//TestCaseContext testCaseContext
//KeywordUtil.logInfo("********************************"+testCaseContext.testCaseId)

//public List storesToVisit = []
	checkIfMultiSites()
	GlobalVariable.isRunByMultiSites =false
	return null

	def checkIfMultiSites() {
		TestObject multiwebsiteDrop = findTestObject('Object Repository/Multi Sites/Multi site dropdown menu')
		TestObject tb = new TestObject()
		List storesToVisit = []
		/*def script
		def countryCode
		def store*/
		//multiwebsiteDrop = findTestObject('Object Repository/Multi Sites/Multi site dropdown menu')
	   
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
		   openMultiSiteDrop(multiwebsiteDrop)
		   List sites = WebUI.findWebElements(findTestObject('Object Repository/Multi Sites/Multi sites Sites context'), 30)
		   if (sites.size() != 0) {
			   GlobalVariable.isRunByMultiSites=true
			   for (int i = 0; i <= (sites.size() - 1); i++) {
				   storesToVisit << (('//div[@class=\'dropdown-menu show\']//button[contains(@class,\'dropdown-item drop-item\')][text()=\'' +
				   sites.get(i).text) + '\']' //KeywordUtil.logInfo(sites.get(i).text)
				   )
				   
			   }
			   
		   }
	   }
	   if (storesToVisit.size() != 0) {
		   GlobalVariable.isItFirstSite = false
		   Map suiteProperties =[:]
		   suiteProperties.put("retryCount", "0")
		   def suiteID = GlobalVariable.testSuiteStatus
			   storesToVisit.each({ def val ->
				   WebUI.callTestCase(findTestCase('FE/Website launch/Validations/Website launch'), [:], FailureHandling.STOP_ON_FAILURE)

				   openMultiSiteDrop(multiwebsiteDrop)
				   WebUI.delay(5)

				   //script = (('return JSON.parse(JSON.parse(localStorage.getItem(\'persist:availableCacheData:' + store) + '\')).appState).storeConfig.default_country_code')

				  // def countryCode = WebUI.executeJavaScript(script, null)
				   String country = ((val =~ 'text\\(\\)=\'(.*?)\'')[0])[1]
				   KeywordUtil.logger.logInfoHighlight(('>>>>>>>>>>>>>>>>>>>>>>>>> This ' + country) + ' Started <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<')
				   tb.addProperty('xpath', ConditionType.EQUALS, val)
				   WebUI.click(tb)
				   WebUI.delay(10)
				   String script = 'return (JSON.parse(localStorage.getItem(\'BROWSER_PERSISTENCE__store_view_code\')).value).replaceAll(\'"\',\'\')'
				   
				   def store = WebUI.executeJavaScript(script, null)
				   
				  // suiteID = 
				   WebUI.delay(10)
				   GlobalVariable.URL=WebUI.getUrl()
				   TestCaseMain.startTestSuite(suiteID+"_"+store, suiteProperties, new File(GlobalVariable.testSuiteReportFolder+"/testCaseBinding"))
				   KeywordUtil.logger.logInfoHighlight(('>>>>>>>>>>>>>>>>>>>>>>>>> This ' + country) + ' Ended <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<')
				   //if(val.)
				   WebUI.delay(20)
			   })
	   }
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
