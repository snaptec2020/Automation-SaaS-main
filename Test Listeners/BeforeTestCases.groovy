import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.eclipse.persistence.jpa.jpql.parser.BooleanExpressionBNF

import com.eviware.soapui.config.TestSuiteRunTypes
import com.eviware.soapui.config.impl.TestSuiteRunTypesImpl
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.entity.testsuite.TestSuiteCollectionEntity

import groovy.util.slurpersupport.GPathResult

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable
import sun.security.util.KeyUtil

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.SetupTestCase
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.logging.ErrorCollector
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.main.TestSuiteExecutor

class BeforeTestCases {
	boolean failed=false
	/**
	 * Executes before every test case starts.
	 * @param testCaseContext related information of the executed test case.
	 */
	
	//WebUI.click(findTestObject('Multi Sites/Multi site dropdown menu'))
	//@BeforeTestSuite
	//public TestSuiteContext testSuiteContext
	//def testCases = []
	
	
	
	@BeforeTestCase
	def sampleBeforeTestCase(TestCaseContext testCaseContext) {
		if(testCaseContext.getTestCaseId().indexOf("/Helpdesk/")>0) {
			//WebUI.callTestCase(findTestCase( 'Test Cases/Helpdesk/AjStore/MobileView'), ['testCaseID':testCaseContext.getTestCaseId()],	FailureHandling.CONTINUE_ON_FAILURE)
			if(GlobalVariable.RunningMode=="2") {
				List<String> productList =findTestData("Data Files/Mobile/Mobile sizes").getAllData()
				Map chromeOptions =new HashMap<String, Object>()
				for(int i=0;i<productList.size();i++) {
					chromeOptions.put("deviceName", productList.get(i).get(0))
					RunConfiguration.setWebDriverPreferencesProperty('mobileEmulation', chromeOptions)
					try {
						WebUI.callTestCase(findTestCase(testCaseContext.getTestCaseId()), [:],	FailureHandling.STOP_ON_FAILURE)
					}catch(Exception e) {
						failed=true
						testCaseContext.skipThisTestCase()
						KeywordUtil.markFailed("Mobile test Failed with Mobile version: " + productList.get(i).get(0))
					}
					chromeOptions.remove("deviceName")
					RunConfiguration.setWebDriverPreferencesProperty('mobileEmulation', chromeOptions)
				}
			}
			return
		}
		
		
		if(GlobalVariable.testSuiteStatus == 'Not Run') {
			WebUI.callTestCase(findTestCase('FE/Website launch/Validations/Website launch'), [:], FailureHandling.STOP_ON_FAILURE)
			//CustomKeywords.'products.productsFromCatalog.getSpecifiedinStockProductsText'()
		}
	}
	
	@AfterTestCase
	def sampleAfterTestCase(TestCaseContext testCaseContext) {
		if(testCaseContext.getTestCaseId().indexOf("/Helpdesk/")>0) {
			if (failed) {
				KeywordUtil.markFailed("Mobile test Failed with Mobile version: ")
				ErrorCollector.getCollector().addError(new StepFailedException("Mobile test Failed with Mobile version: "));
			}
			return
		}
		
		
			if(GlobalVariable.testSuiteStatus == 'Not Run') {
			WebUI.closeBrowser()
			}
	}
	
	@BeforeTestSuite
	def sampleBeforeTestSuite(TestSuiteContext testSuiteContext) {
		if(testSuiteContext.getTestSuiteId().indexOf("/Helpdesk/")>0) {
			return
		}

		GlobalVariable.testSuiteStatus = testSuiteContext.testSuiteId
		setRunningMode(testSuiteContext.testSuiteId)
		//KeywordUtil.logInfo('**************************'+GlobalVariable.testSuiteStatus)
		//sampleBeforeTestCase(testCaseContext.skipThisTestCase())
		
		RunSuiteOnMobileTests(testSuiteContext.getTestSuiteId(),testSuiteContext)
		
		WebUI.callTestCase(findTestCase('FE/Website launch/Validations/Website launch'), [:], FailureHandling.STOP_ON_FAILURE)
		//getTestSuitLanguage(testSuiteContext.testSuiteId)
		//WebUI.callTestCase(findTestCase('FE/Website launch/Verifications/Verifications after launch (headers and footers)'), [:],
			//FailureHandling.STOP_ON_FAILURE)
		
		CustomKeywords.'products.productsFromCatalog.getSpecifiedinStockProductsText'()
		
		
	}
	@AfterTestSuite
	def sampleAfterTestSuite(TestSuiteContext testSuiteContext) {
		if(testSuiteContext.getTestSuiteId().indexOf("/Helpdesk/")>0) {
			return
		}

		
		GlobalVariable.testSuiteStatus = 'Not Run'
		//KeywordUtil.logInfo('**************************'+GlobalVariable.testSuiteStatus)
		WebUI.closeBrowser()
		//sampleBeforeTestCase(testCaseContext.skipThisTestCase())
		//WebUI.callTestCase(findTestCase('FE/Website launch/Validations/Website launch'), [:], FailureHandling.STOP_ON_FAILURE)
		
	}
	def setRunningMode(def testSuitPath) {
		def runningFolder = testSuitPath =~'^.*?/(.*?)/.*'
		switch(runningFolder[0][1].toString()) {
			case 'Web Browsers': GlobalVariable.RunningMode='1'
				 break
			case 'Mobile browsers': GlobalVariable.RunningMode='2'
				 break
			case 'Mobile Apps': GlobalVariable.RunningMode='3'
				 break
		}
	}

	
	/**
	 * Returns a string containing references to all Test Cases found in the
	 * specified Test Suite.
	 *
	 * @param suiteName (String) The Test Suite to be examined.
	 * @param before (String) Typically used to pass HTML to the output.
	 * @param after (String) Typically used to pass HTML to the output.
	 * @return String.
	 */
	def RunSuiteOnMobileTests(String suiteName,TestSuiteContext testSuiteContext) {
		List<String> productList =findTestData("Data Files/Mobile/Mobile sizes").getAllData()
		Map chromeOptions =new HashMap<String, Object>()
		for(int i=0;i<productList.size();i++) {
			chromeOptions.put("deviceName", productList.get(i).get(0))
			RunConfiguration.setWebDriverPreferencesProperty('mobileEmulation', chromeOptions)
			String projDir = RunConfiguration.getProjectDir()
			String fname = projDir + "/" + suiteName + ".ts"
			println ("getSuiteTests reading: " + fname)
			String xmlText = new File(fname).getText()
			println ("getSuiteTests parsing: " + fname)
			GPathResult testList = new XmlSlurper().parseText(xmlText)
			WebUI.callTestCase(findTestCase('FE/Website launch/Validations/Website launch'), [:], FailureHandling.STOP_ON_FAILURE)
			CustomKeywords.'products.productsFromCatalog.getSpecifiedinStockProductsText'()
			//getTestSuitLanguage(testSuiteContext.testSuiteId)
			testList.testCaseLink.each {
				if(it.isRun.toString().equals("true")) {
					try {
						WebUI.callTestCase(findTestCase(it.testCaseId.toString()), [:],	FailureHandling.STOP_ON_FAILURE)
					}catch(Exception e) {
						KeywordUtil.markFailed("Mobile test Failed with Mobile version: " + productList.get(i).get(0))
					}
				}
			}
			chromeOptions.remove("deviceName")
			RunConfiguration.setWebDriverPreferencesProperty('mobileEmulation', chromeOptions)
			WebUI.closeBrowser()
		}
	}
	
	def getTestSuitLanguage(def testSuitPath) {
		def runningFolder = testSuitPath =~'^.*?/.*?/(.*?)/'
		if(runningFolder[0][1].toString().equalsIgnoreCase('En')) {
			WebUI.callTestCase(findTestCase('FE/Switch Language/Validations/SwitchLanguage to English'), [:], FailureHandling.STOP_ON_FAILURE)
		}
	}
}



