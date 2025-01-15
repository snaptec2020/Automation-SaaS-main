import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.eclipse.persistence.jpa.jpql.parser.BooleanExpressionBNF

import com.eviware.soapui.config.TestSuiteRunTypes
import com.eviware.soapui.config.impl.TestSuiteRunTypesImpl
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.constants.StringConstants
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
import io.netty.util.concurrent.GlobalEventExecutor
import sun.security.util.KeyUtil

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.SetupTestCase
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.main.TestCaseMain
import com.kms.katalon.core.main.TestSuiteExecutor
import java.nio.file.Files
import org.apache.commons.io.FileUtils
import com.kms.katalon.core.logging.model.TestSuiteLogRecord;
import com.kms.katalon.core.reporting.ReportUtil//ortWriterUtil
//basic.reporting.ReportWriterUtil;
import com.kms.katalon.core.setting.BundleSettingStore;
import com.kms.katalon.core.util.internal.ExceptionsUtil;
import groovy.xml.MarkupBuilder
import groovy.xml.XmlUtil
class TestCaseAndSuiteListener {
	/**
	 * Executes before every test case starts.
	 * @param testCaseContext related information of the executed test case.
	 */

	//WebUI.click(findTestObject('Multi Sites/Multi site dropdown menu'))
	//@BeforeTestSuite
	//public TestSuiteContext testSuiteContext
	//def testCases = []
	List storesToVisit = []
	TestObject tb = new TestObject()
	def multiwebsiteDrop
	//File finalReport
	//boolean isItFirstSite = true
	@BeforeTestCase
	def sampleBeforeTestCase(TestCaseContext testCaseContext) {
		
		if(testCaseContext.getTestCaseId().indexOf("/Helpdesk/")<=0) {

			GlobalVariable.launchingConfig.put("Mode",(testCaseContext.testCaseId=~"Test Cases/(.*?)/(.*?)/")[0][1])
			GlobalVariable.launchingConfig.put("BEMode",(testCaseContext.testCaseId=~"Test Cases/(.*?)/(.*?)/")[0][2])

			GlobalVariable.launchingConfig.put("SiteUrl",(RunConfiguration.executionProfile=~"^.*?-(.*?)-")[0][1])
			WebUI.callTestCase(findTestCase('FE/Website launch/Validations/Website launch'), [:], FailureHandling.STOP_ON_FAILURE)
		}
		if (GlobalVariable.languageMode.toString().equalsIgnoreCase('EN')) {
			WebUI.callTestCase(findTestCase('FE/Switch Language/Validations/SwitchLanguage to English'), [:], FailureHandling.STOP_ON_FAILURE)
		}
		if(!GlobalVariable.isItFirstSite & testCaseContext.getTestCaseId().equalsIgnoreCase("Test Cases/FE/Multi Sites/Multisites New way")) {
			testCaseContext.skipThisTestCase()
		}
		//KeywordUtil.logInfo(GlobalVariable.textSearch.toString())
		if(testCaseContext.getTestCaseId().indexOf("/FE/Search/")>0 && GlobalVariable.textSearch.toString().equalsIgnoreCase('[]')) {
			CustomKeywords.'products.productsFromCatalog.getSpecifiedinStockProductsText'()
		}
	}

	@AfterTestCase
	def sampleAfterTestCase(TestCaseContext testCaseContext) {

		//		if(GlobalVariable.testSuiteStatus == 'Not Run' & testCaseContext.getTestCaseId().indexOf("/Helpdesk/")<=0) {
		//			WebUI.takeFullPageScreenshot("./ScreenAfterTestcase_${testCaseContext.getTestCaseId()}.png",FailureHandling.CONTINUE_ON_FAILURE)
		//			//WebUI.closeBrowser()
		//		}
		WebUI.closeBrowser()
//		WebUI.comment("done")
	}

	@BeforeTestSuite
	def sampleBeforeTestSuite(TestSuiteContext testSuiteContext) {		
		KeywordUtil.logInfo(testSuiteContext.testSuiteId.toString())
		GlobalVariable.testSuiteStatus = testSuiteContext.testSuiteId
		GlobalVariable.testSuiteReportFolder = RunConfiguration.getReportFolder()
	}
	@AfterTestSuite
	def sampleAfterTestSuite(TestSuiteContext testSuiteContext) {
		generateReport(testSuiteContext)
//		if(testSuiteContext.getTestSuiteId().indexOf("/Helpdesk/")<=0/*&&isItFirstSite*/) {
//			generateReport(testSuiteContext)
//			if(GlobalVariable.isItFirstSite) {
//				WebUI.callTestCase(findTestCase('FE/Multi Sites/Multisites New way'), [:], FailureHandling.STOP_ON_FAILURE)
//			}
//			if(!GlobalVariable.isRunByMultiSites) {
//				WebUI.closeBrowser()
//				generateFinalReport()
//			}
//		}
//		GlobalVariable.testSuiteStatus = 'Not Run'
	}

	def generateReport(TestSuiteContext testSuiteContext) {
		try {
			String script = 'return (JSON.parse(localStorage.getItem(\'BROWSER_PERSISTENCE__store_view_code\')).value).replaceAll(\'"\',\'\')'
			def store = WebUI.executeJavaScript(script, null)
			Map reportMap =[:]
			String reportFolder = RunConfiguration.getReportFolder();
			String projectDir = RunConfiguration.getProjectDir();

			BundleSettingStore bundleSettingStore = new BundleSettingStore(projectDir, "com.katalon.plugin.report",
					true);

			boolean genereteHTML = bundleSettingStore.getBoolean("generateHTML", true);
			boolean genereteCSV = bundleSettingStore.getBoolean("generateCSV", true);
			boolean generetePDF = bundleSettingStore.getBoolean("generatePDF", false);
			
//			if (!genereteHTML && !genereteCSV && !generetePDF) {
//				return;
//			}

			File reportFolderFile = new File(reportFolder);
			File folderTemp = Files.createTempDirectory(reportFolderFile.getName()+"_"+store).toFile();
			folderTemp = Files.move(folderTemp.toPath(), folderTemp.toPath().resolveSibling(reportFolderFile.getName()+"_"+store)).toFile();
			FileUtils.copyDirectory(reportFolderFile, folderTemp);
			String folderTempString = folderTemp.getAbsolutePath();
			TestSuiteLogRecord suiteLogEntity = ReportUtil.generate(folderTempString);

			if (genereteHTML) {
				KeywordUtil.logInfo("Start generating HTML report folder at:" + reportFolder + "...");
				ReportUtil.writeHtmlReport(suiteLogEntity, folderTemp);
				KeywordUtil.logInfo("HTML report generated");
			}

			if (genereteCSV) {
				KeywordUtil.logInfo("Start generating CSV report folder at: " + reportFolder + "...");
				ReportUtil.writeCSVReport(suiteLogEntity, folderTemp);
				KeywordUtil.logInfo("CSV report generated");
			}

			FileUtils.copyDirectory(folderTemp, reportFolderFile, new FileFilter() {
						@Override
						public boolean accept(File pathname) {
							String path = pathname.getAbsolutePath().toLowerCase();
							return path.contains(".csv") || path.contains(".html") || path.contains(".pdf");
						}
					});
			FileUtils.deleteQuietly(folderTemp);
			FileUtils.forceDeleteOnExit(folderTemp);
			reportMap.put("fail_on_total", suiteLogEntity.totalFailedTestCases.toString()+"/"+suiteLogEntity.totalTestCases.toString())
			reportMap.put("environment", RunConfiguration.getExecutionProfile())
			reportMap.put("report_location", reportFolderFile.getAbsolutePath().replaceAll("\\\\", "/").replaceAll("^.*?/Reports/", "Reports/"))
			reportMap.put("fail_color", "")
			reportMap.put("report", reportFolderFile.getName()+"_"+store+'.html')
			reportMap.put("status_color", "")
			reportMap.put("id", reportFolderFile.getName()+"_"+store)
			reportMap.put("status", testSuiteContext.status)
			GlobalVariable.testSuiteJsonReport.add(new groovy.json.JsonBuilder(reportMap).toString())
		} catch (Exception e) {
			KeywordUtil.markWarning(ExceptionsUtil.getStackTraceForThrowable(e));
		}
	}

	def generateFinalReport() {

		String reportFolder = RunConfiguration.getReportFolder();
		File reportFolderFile = new File(reportFolder);
		File src = ReportUtil.writeTSCollectionHTMLReport("MultiSites Final", "["+GlobalVariable.testSuiteJsonReport.join(',')+"]", reportFolderFile, false)
		File dest = new File(reportFolder+"/"+reportFolderFile.getName()+".html")
		Files.copy(src.toPath(), dest.toPath())
	}
}