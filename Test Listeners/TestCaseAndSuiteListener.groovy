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
import com.utils.CustomLogger
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable
//import io.netty.util.concurrent.GlobalEventExecutor
//import sun.security.util.KeyUtil

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.SetupTestCase
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.logging.XMLParserException
import com.kms.katalon.core.main.TestCaseMain
import com.kms.katalon.core.main.TestSuiteExecutor
import java.nio.file.Files

import javax.xml.stream.XMLStreamException

import org.apache.commons.io.FileUtils
import com.kms.katalon.core.logging.model.TestSuiteLogRecord;
import com.kms.katalon.core.reporting.KatalonExportReportProvider
import com.kms.katalon.core.reporting.ReportUtil
import com.kms.katalon.core.reporting.ReportWriterUtil
import com.kms.katalon.core.reporting.ReportWriterUtil.SuiteReportGenerationOptions
import com.kms.katalon.core.reporting.ReportWriterUtil.SuiteReportGenerationOptionsBuilder
import com.kms.katalon.core.reporting.pdf.TestSuitePdfGenerator
import com.kms.katalon.core.reporting.pdf.exception.JasperReportException
import com.kms.katalon.core.setting.BundleSettingStore;
import com.kms.katalon.core.setting.ReportBundleSettingStore
import com.kms.katalon.core.util.internal.ExceptionsUtil;
import groovy.xml.MarkupBuilder
import groovy.xml.XmlUtil
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.xml.stream.XMLStreamException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.StringBuilderWriter;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

import com.kms.katalon.core.constants.StringConstants;
import com.kms.katalon.core.logging.TestSuiteXMLLogParser;
import com.kms.katalon.core.logging.XMLParserException;
import com.kms.katalon.core.logging.model.ILogRecord;
import com.kms.katalon.core.logging.model.TestCaseLogRecord;
import com.kms.katalon.core.logging.model.TestSuiteCollectionLogRecord;
import com.kms.katalon.core.logging.model.TestSuiteLogRecord;
import com.kms.katalon.core.reporting.html.JsSuiteModel;
import com.kms.katalon.core.reporting.html.ResourceLoader;
import com.kms.katalon.core.reporting.newreport.NewHTMLReportDataWriter;
import com.kms.katalon.core.reporting.newreport.NewReportModelMapper;
import com.kms.katalon.core.reporting.pdf.TestSuitePdfGenerator;
import com.kms.katalon.core.reporting.pdf.exception.JasperReportException;
import com.kms.katalon.core.reporting.util.ResourceUtil;
import com.kms.katalon.core.setting.ReportSettings;
import com.kms.katalon.core.testdata.reader.CsvWriter;
import com.kms.katalon.core.util.ObjectUtil;
import com.kms.katalon.core.util.internal.JsonUtil;
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
		//WebUI.closeBrowser()
		GlobalVariable.isFirstTime = true
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
		//newReportGenerator()
		//generateReport(testSuiteContext)
		CustomLogger.logError("")
		//KeywordUtil.logInfo(testSuiteContext.testSuiteId.toString())
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
	def newReportGenerator() {
		//KatalonExportReportProvider kerp = new KatalonExportReportProvider()
		String reportFolder = RunConfiguration.getReportFolder();
		String projectDir = RunConfiguration.getProjectDir();
		def store = "test"
		File reportFolderFile = new File(reportFolder);
		File folderTemp = Files.createTempDirectory(reportFolderFile.getName()+"_${store}").toFile();
		FileUtils.copyDirectory(reportFolderFile, folderTemp);
		String folderTempString = folderTemp.getAbsolutePath();
		exportTestSuite(folderTemp, projectDir, folderTempString, "HTML")
	}
	public File exportTestSuite(File exportLocation, String projectDir, String reportId, String formatType)
	throws IOException, URISyntaxException, XMLParserException, XMLStreamException {
		def settings = ReportBundleSettingStore.getStore(projectDir).getSettings();

		File reportFolder = new File(reportId);
		TestSuiteLogRecord testSuiteLogRecord = ReportWriterUtil.parseTestSuiteLog(reportFolder.getAbsolutePath());
		switch (formatType) {
			case "HTML": //ReportWriterUtil.writeHTMLReport
				ReportWriterUtil.writeHTMLReport(SuiteReportGenerationOptionsBuilder.create()
				.suiteLogRecord(testSuiteLogRecord)
				.settings(settings)
				.reportDir(reportFolder)
				.outputFile(exportLocation)
				.build());
				return exportLocation;
			case "CSV (Summary)":
				ReportWriterUtil.writeLogRecordToCSVFile(testSuiteLogRecord, exportLocation,
				Arrays.asList(testSuiteLogRecord.filterFinalTestCasesResult()), false);
				return exportLocation;
			case "CSV (Details)":
				ReportWriterUtil.writeLogRecordToCSVFile(testSuiteLogRecord, exportLocation,
				Arrays.asList(testSuiteLogRecord.filterFinalTestCasesResult()), true);
				return exportLocation;
			case "PDF":
				TestSuitePdfGenerator pdfGenerator = new TestSuitePdfGenerator(testSuiteLogRecord);
				try {
					pdfGenerator.exportToPDF(exportLocation.getAbsolutePath());
				} catch (JasperReportException e) {
					CustomLogger.logWarning(ExceptionsUtil.getStackTraceForThrowable(e));
					throw new IOException(e);
				}

				return exportLocation;
			default:
				break;
		}

		return null;
	}
	private static interface ReportWritingPhase {
		void write(Writer writer) throws IOException, URISyntaxException
	}

	private static void writeReport(File destFile, Closure... phases)
	throws IOException, URISyntaxException {
		new FileOutputStream(destFile).withStream { outputStream ->
			new OutputStreamWriter(outputStream, StringConstants.DF_CHARSET).withWriter { writer ->
				phases.each { phase ->
					phase.call(writer)
				}
			}
		}
	}
	private static void writeNewTestSuiteHTMLReport(SuiteReportGenerationOptions options)
	throws IOException, URISyntaxException {

		def newHTMLTemplate = ReportWriterUtil.readFileToStringBuilder(ResourceLoader.NEW_HTML_TEMPLATE_FILE)
		def parts = newHTMLTemplate.split(ResourceLoader.NEW_HTML_DATA_PLACEHOLDER)
		def header = parts[0]
		def footer = parts[1]

		def testSuiteLogRecord = options.suiteLogRecord
		def filteredTestCases = options.filteredTestCases
		def finalTestCases = filteredTestCases ?: Arrays.asList(testSuiteLogRecord.finalTestCases)

		def testSuiteLogRecordClone = new TestSuiteLogRecord("", "")
		ObjectUtil.clone(testSuiteLogRecord, testSuiteLogRecordClone)

		def newChildRecords = []
		newChildRecords.addAll(testSuiteLogRecordClone.beforeTestSuiteLogRecords)
		newChildRecords.addAll(finalTestCases)
		newChildRecords.addAll(testSuiteLogRecordClone.afterTestSuiteLogRecords)
		testSuiteLogRecordClone.childRecords = newChildRecords as ILogRecord[]

		def reportDir = options.reportDir
		def outputFile = options.outputFile
		def settings = options.settings

		writeReport(outputFile, { writer ->
			writer.write(header)
		}, { writer ->
			def newHTMLWriter = new NewHTMLReportDataWriter(writer, outputFile.parentFile,
					settings.useSplitReportData)
			new NewReportModelMapper(settings, reportDir).toTestExecutionParts(testSuiteLogRecordClone) { String part, String partKey ->
				newHTMLWriter.writePart(part, partKey)
			}
		} as ReportWritingPhase, { writer ->
			writer.write(footer)
		} as ReportWritingPhase
		)
	}
	def generateReport(TestSuiteContext testSuiteContext) {
		try {
			//			String script = 'return (JSON.parse(localStorage.getItem(\'BROWSER_PERSISTENCE__store_view_code\')).value).replaceAll(\'"\',\'\')'
			def store = "test"//WebUI.executeJavaScript(script, null)
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

			//			if (genereteHTML) {
			//				KeywordUtil.logInfo("Start generating HTML report folder at:" + reportFolder + "...");
			ReportUtil.writeHtmlReport(suiteLogEntity, folderTemp);
			//				KeywordUtil.logInfo("HTML report generated");
			//			}

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