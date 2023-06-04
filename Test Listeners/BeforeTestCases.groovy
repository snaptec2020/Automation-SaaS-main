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
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.main.TestSuiteExecutor

class BeforeTestCases {
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
		if(GlobalVariable.testSuiteStatus == 'Not Run') {
			if(testCaseContext.getTestCaseId().indexOf("/Helpdesk/")<=0) {
				WebUI.callTestCase(findTestCase('FE/Website launch/Validations/Website launch'), [:], FailureHandling.STOP_ON_FAILURE)
			}else {
				if(GlobalVariable.RunningMode.equals("2")) {
					List<List<String>> productList =findTestData("Data Files/Mobile/Mobile sizes").getAllData()
					Map chromeOptions =new HashMap<String, Object>()
					chromeOptions.put("deviceName", productList.get(GlobalVariable.MobileType.toString().toInteger()-1).get(0))
					RunConfiguration.setWebDriverPreferencesProperty('mobileEmulation', chromeOptions)
				}
			}
		}else {
			if(GlobalVariable.RunningMode.equals("2") & !testCaseContext.getTestCaseId().contains('RunOnAllMobileWebBrowsers')) {
				testCaseContext.skipThisTestCase()
			}else if(GlobalVariable.RunningMode.equals("1") & testCaseContext.getTestCaseId().contains('RunOnAllMobileWebBrowsers')) {
				testCaseContext.skipThisTestCase()
			}
		}
	}
	@AfterTestCase
	def sampleAfterTestCase(TestCaseContext testCaseContext) {
		if(testCaseContext.getTestCaseId().indexOf("/Helpdesk/")>0) {
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
		WebUI.callTestCase(findTestCase('FE/Website launch/Validations/Website launch'), [:], FailureHandling.STOP_ON_FAILURE)
		getTestSuitLanguage(testSuiteContext.testSuiteId)
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
	def getTestSuitLanguage(def testSuitPath) {
		def runningFolder = testSuitPath =~'^.*?/.*?/(.*?)/'
		if(runningFolder[0][1].toString().equalsIgnoreCase('En')) {
			WebUI.callTestCase(findTestCase('FE/Switch Language/Validations/SwitchLanguage to English'), [:], FailureHandling.STOP_ON_FAILURE)
		}
	}
}