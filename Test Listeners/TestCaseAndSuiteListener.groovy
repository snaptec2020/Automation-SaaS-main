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

class TestCaseAndSuiteListener {
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
		if(GlobalVariable.testSuiteStatus == 'Not Run' & testCaseContext.getTestCaseId().indexOf("/Helpdesk/")<=0) {
			GlobalVariable.RunningMode=CustomKeywords.'generalactions.EnvironmentSettings.isRunningByMobile'()
			WebUI.callTestCase(findTestCase('FE/Website launch/Validations/Website launch'), [:], FailureHandling.STOP_ON_FAILURE)

		}
		if(testCaseContext.getTestCaseId().indexOf("/FE/Search/")>0 && GlobalVariable.textSearch.toString().equalsIgnoreCase('Not Defined')) {
			CustomKeywords.'products.productsFromCatalog.getSpecifiedinStockProductsText'()
		}
	}
	
	@AfterTestCase
	def sampleAfterTestCase(TestCaseContext testCaseContext) {
		if(GlobalVariable.testSuiteStatus == 'Not Run' & testCaseContext.getTestCaseId().indexOf("/Helpdesk/")<=0) {
			WebUI.closeBrowser()
		}
	}

	@BeforeTestSuite
	def sampleBeforeTestSuite(TestSuiteContext testSuiteContext) {
		GlobalVariable.testSuiteStatus = testSuiteContext.testSuiteId
		GlobalVariable.RunningMode=CustomKeywords.'generalactions.EnvironmentSettings.isRunningByMobile'()
		if(testSuiteContext.getTestSuiteId().indexOf("/Helpdesk/")<=0) {
			WebUI.callTestCase(findTestCase('FE/Website launch/Validations/Website launch'), [:], FailureHandling.STOP_ON_FAILURE)
			
		}
	}
	@AfterTestSuite
	def sampleAfterTestSuite(TestSuiteContext testSuiteContext) {
		GlobalVariable.testSuiteStatus = 'Not Run'
		if(testSuiteContext.getTestSuiteId().indexOf("/Helpdesk/")<=0) {
			WebUI.closeBrowser()
		}
	}
}