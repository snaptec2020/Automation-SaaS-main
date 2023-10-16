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
import com.kms.katalon.core.appium.driver.AppiumDriverManager
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
		//KeywordUtil.logInfo(AppiumDriverManager.getDeviceOS(StringConstants.CONF_PROPERTY_MOBILE_DRIVER))
		if(GlobalVariable.testSuiteStatus == 'Not Run' & testCaseContext.getTestCaseId().indexOf("/Helpdesk/")<=0) {
			GlobalVariable.RunningMode=CustomKeywords.'generalactions.EnvironmentSettings.isRunningByMobile'()
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
	
	//@AfterTestCase
	def sampleAfterTestCase(TestCaseContext testCaseContext) {
		if(GlobalVariable.testSuiteStatus == 'Not Run' & testCaseContext.getTestCaseId().indexOf("/Helpdesk/")<=0) {
			WebUI.closeBrowser()
		}
	}

	@BeforeTestSuite
	def sampleBeforeTestSuite(TestSuiteContext testSuiteContext) {
		KeywordUtil.logInfo(testSuiteContext.testSuiteId.toString())
		GlobalVariable.testSuiteStatus = testSuiteContext.testSuiteId
		GlobalVariable.testSuiteReportFolder = RunConfiguration.getReportFolder()
		GlobalVariable.RunningMode=CustomKeywords.'generalactions.EnvironmentSettings.isRunningByMobile'()
		if(testSuiteContext.getTestSuiteId().indexOf("/Helpdesk/")<=0 && !GlobalVariable.isRunByMultiSites) {
			//String reportFolder = RunConfiguration.getReportFolder();
			//File reportFolderFile = new File(reportFolder);
			//finalReport = new File(reportFolderFile, reportFolderFile.getName() + ".html")
			GlobalVariable.launchingConfig.put("Mode",(testSuiteContext.testSuiteId=~"Test Suites/Test Suites/(.*?)/(.*?)/")[0][1])
			GlobalVariable.launchingConfig.put("BEMode",(testSuiteContext.testSuiteId=~"Test Suites/Test Suites/(.*?)/(.*?)/")[0][2])
			GlobalVariable.launchingConfig.put("SiteUrl",(RunConfiguration.executionProfile=~"^.*?-(.*?)-")[0][1])
			WebUI.callTestCase(findTestCase('FE/Website launch/Validations/Website launch'), [:], FailureHandling.STOP_ON_FAILURE)
			//checkIfMultiSites()
			//WebUI.refresh()
			
			
		}
	}
	@AfterTestSuite
	def sampleAfterTestSuite(TestSuiteContext testSuiteContext) {
		//KeywordUtil.logInfo(storesToVisit.toString())
		//Map suiteProperties =[:]
		//suiteProperties.put("retryCount", "0")
		if(testSuiteContext.getTestSuiteId().indexOf("/Helpdesk/")<=0/*&&isItFirstSite*/) {
			//for(int i = 0;i<=storesToVisit.size()-1;i++)
			/*storesToVisit.each({ def val ->
				isItFirstSite=false
				openMultiSiteDrop(multiwebsiteDrop)
				String country = ((val =~ 'text\\(\\)=\'(.*?)\'')[0])[1]
				KeywordUtil.logger.logInfoHighlight(('>>>>>>>>>>>>>>>>>>>>>>>>> This ' + country) + ' Started <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<')
				tb.addProperty('xpath', ConditionType.EQUALS, val)
				WebUI.click(tb)
				WebUI.delay(10)
				GlobalVariable.URL=WebUI.getUrl()
				TestCaseMain.startTestSuite(testSuiteContext.testSuiteId, RunConfiguration.getExecutionProperties(), new File(GlobalVariable.testSuiteReportFolder+"/testCaseBinding"))
				KeywordUtil.logger.logInfoHighlight(('>>>>>>>>>>>>>>>>>>>>>>>>> This ' + country) + ' Ended <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<')
				
			})*/
			//new File(logFolder, logFolder.getName() + ".html")
			generateReport(testSuiteContext)
			if(GlobalVariable.isItFirstSite) {
				WebUI.callTestCase(findTestCase('FE/Multi Sites/Multisites New way'), [:], FailureHandling.STOP_ON_FAILURE)
			}
			if(!GlobalVariable.isRunByMultiSites) {
			WebUI.closeBrowser()
			generateFinalReport()
			}
		}
		GlobalVariable.testSuiteStatus = 'Not Run'
	}
	def generateReport(TestSuiteContext testSuiteContext) {
		try {
			String script = 'return (JSON.parse(localStorage.getItem(\'BROWSER_PERSISTENCE__store_view_code\')).value).replaceAll(\'"\',\'\')'

			def store = WebUI.executeJavaScript(script, null)
			Map reportMap =[:]
			//script = (('return JSON.parse(JSON.parse(localStorage.getItem(\'persist:availableCacheData:' + store) + '\')).appState).storeConfig.default_country_code')

			//def countryCode = WebUI.executeJavaScript(script, null)
			String reportFolder = RunConfiguration.getReportFolder();
			String projectDir = RunConfiguration.getProjectDir();

			BundleSettingStore bundleSettingStore = new BundleSettingStore(projectDir, "com.katalon.plugin.report",
					true);

			boolean genereteHTML = bundleSettingStore.getBoolean("generateHTML", true);
			boolean genereteCSV = bundleSettingStore.getBoolean("generateCSV", true);
			// boolean genereteJUnit = bundleSettingStore.getBoolean("generateJUnit", true);
			boolean generetePDF = bundleSettingStore.getBoolean("generatePDF", false);
			if (!genereteHTML && !genereteCSV && !generetePDF) {
				return;
			}

			File reportFolderFile = new File(reportFolder);
			//KeywordUtil.logInfo("reportFolder ******************* " + reportFolder)
			File folderTemp = Files.createTempDirectory(reportFolderFile.getName()+"_"+store).toFile();
			// rename temp folder to match with report folder
			//KeywordUtil.logInfo("folderTemp ******************* " + folderTemp.toPath().getFileName())
			folderTemp = Files.move(folderTemp.toPath(), folderTemp.toPath().resolveSibling(reportFolderFile.getName()+"_"+store)).toFile();
			//KeywordUtil.logInfo("folderTemp ******************* " + folderTemp.absolutePath)
			FileUtils.copyDirectory(reportFolderFile, folderTemp);
			String folderTempString = folderTemp.getAbsolutePath();
			//KeywordUtil.logInfo("folderTempString ******************* " + folderTempString)
			TestSuiteLogRecord suiteLogEntity = ReportUtil.generate(folderTempString);

			if (genereteHTML) {
				KeywordUtil.logInfo("Start generating HTML report folder at: " + reportFolder + "...");
				ReportUtil.writeHtmlReport(suiteLogEntity, folderTemp);
				//StringBuilder htmlSb = ReportUtil.prepareHtmlContent(suiteLogEntity);
				//KeywordUtil.logInfo("String builder for " + store + "is " +htmlSb.toString());
				//KeywordUtil.logInfo(finalReport.getAbsolutePath())
				// Write main HTML Report
				//FileUtils.writeStringToFile(finalReport, htmlSb.toString(),StringConstants.DF_CHARSET,true);
				KeywordUtil.logInfo("HTML report generated");
			}

			if (genereteCSV) {
				KeywordUtil.logInfo("Start generating CSV report folder at: " + reportFolder + "...");
				ReportUtil.writeCSVReport(suiteLogEntity, folderTemp);
				KeywordUtil.logInfo("CSV report generated");
			}

//            if (genereteJUnit) {
//                KeywordUtil.logInfo("Start generating JUnit report folder at: " + reportFolder + "...");
//                ReportWriterUtil.writeJUnitReport(suiteLogEntity, reportFolderFile);
//                KeywordUtil.logInfo("JUnit report generated");
//            }

			/*
			 * if (generetePDF) {
			 * KeywordUtil.logInfo("Start generating PDF report folder at: " + reportFolder
			 * + "..."); ReportUtil.writePdfReport(suiteLogEntity, folderTemp);
			 * KeywordUtil.logInfo("PDF report generated"); }
			 */
			
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
		//def jsonBuilder = new groovy.json.JsonBuilder()
		/*jsonBuilder{
			GlobalVariable.testSuiteJsonReport.collect { 
					[[fail_on_total:it.fail_on_total,
					environment:it.environment,
					report_location:it.report_location,
					fail_color:it.fail_color,
					report:it.report,
					status_color:it.status_color,
					id:it.id,
					status:it.status
					]]
			}
		}*/
		//File finalReport
		
		String reportFolder = RunConfiguration.getReportFolder();
		File reportFolderFile = new File(reportFolder);
		//KeywordUtil.logInfo(GlobalVariable.testSuiteJsonReport.toString())
		/*StringBuffer sb = new StringBuffer();
		for (int i=0; i<GlobalVariable.testSuiteJsonReport.size()-1; i++)
        {
            if(i!=0){
            sb.append(",").append(GlobalVariable.testSuiteJsonReport.get(i));
            }else{
                sb.append(GlobalVariable.testSuiteJsonReport.get(i));
            }
        }*/
		
		File src = ReportUtil.writeTSCollectionHTMLReport("MultiSites Final", "["+GlobalVariable.testSuiteJsonReport.join(',')+"]", reportFolderFile, false)
		File dest = new File(reportFolder+"/"+reportFolderFile.getName()+".html")
		Files.copy(src.toPath(), dest.toPath())
		//Files.copy(reportFolder+"/index.html", reportFolder+"/"+reportFolderFile.getName()+".html")
		//File reportFolderFile = new File(reportFolder);
		//finalReport = new File(reportFolderFile, reportFolderFile.getName() + ".html")
		
		
	}

}