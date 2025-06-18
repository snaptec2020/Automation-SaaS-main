package generalactions

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.logging.model.TestSuiteLogRecord
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.reporting.ReportUtil
import com.kms.katalon.core.setting.BundleSettingStore
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.util.internal.ExceptionsUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import java.io.File
import java.nio.file.Files

import org.apache.commons.io.FileUtils

import internal.GlobalVariable

public class reporting {
	@Keyword
	public void exportKatalonReports(TestSuiteContext testSuiteContext) {
		try {
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
			File folderTemp = Files.createTempDirectory(reportFolderFile.getName() + "_").toFile();
			// rename temp folder to match with report folder
			folderTemp = Files.move(folderTemp.toPath(), folderTemp.toPath().resolveSibling(reportFolderFile.getName())).toFile();

			FileUtils.copyDirectory(reportFolderFile, folderTemp);
			String folderTempString = folderTemp.getAbsolutePath();

			TestSuiteLogRecord suiteLogEntity = ReportUtil.generate(folderTempString);

			if (genereteHTML) {
				KeywordUtil.logInfo("Start generating HTML report folder at: " + reportFolder + "...");
				ReportUtil.writeHtmlReport(suiteLogEntity, folderTemp);
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
		} catch (Exception e) {
			KeywordUtil.markWarning(ExceptionsUtil.getStackTraceForThrowable(e));
		}


	}
	@Keyword
	public static void takeScreenshot(String screenshotName = "screenshot_${System.currentTimeMillis()}") {
		try {
			String screenshotPath = KeywordUtil.takeScreenshot(screenshotName, FailureHandling.OPTIONAL)
			KeywordUtil.logInfo("Captured screenshot: ${screenshotPath}")
		} catch(Exception e) {
			KeywordUtil.logWarning("Failed to capture screenshot: ${ExceptionsUtil.getMessageForThrowable(e)}")
		}
	}
}
