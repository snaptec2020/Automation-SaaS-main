import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.eclipse.persistence.jpa.jpql.parser.BooleanExpressionBNF

import com.eviware.soapui.config.TestSuiteRunTypes
import com.eviware.soapui.config.impl.TestSuiteRunTypesImpl
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
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

import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.SetupTestCase
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
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
		
		//testCases << testCaseContext.testCaseId
		
			if(GlobalVariable.testSuiteStatus == 'Not Run') {
			WebUI.callTestCase(findTestCase('FE/Website launch/Validations/Website launch'), [:], FailureHandling.STOP_ON_FAILURE)
			//CustomKeywords.'products.productsFromCatalog.getSpecifiedinStockProductsText'()
			}
	}
	@AfterTestCase
	def sampleAfterTestCase(TestCaseContext testCaseContext) {

			if(GlobalVariable.testSuiteStatus == 'Not Run') {
			WebUI.closeBrowser()
			}
	}
	
	@BeforeTestSuite
	def sampleBeforeTestSuite(TestSuiteContext testSuiteContext) {
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