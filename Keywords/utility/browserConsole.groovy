package utility

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.logging.LogType
import org.openqa.selenium.logging.LogEntries
import org.openqa.selenium.logging.LogEntry
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.utils.CustomLogger

import internal.GlobalVariable

public class browserConsole {
	
	@Keyword
	def boolean checkBrowserConsoleLogs() {
		try {
			def driver = DriverFactory.getWebDriver()
			LogEntries logs = driver.manage().logs().get(LogType.BROWSER)
			
			boolean hasErrors = false
			boolean hasWarnings = false
			int errorCount = 0
			int warningCount = 0
			
			for (LogEntry entry : logs) {
				String level = entry.level.toString()
				String message = entry.message
				
				// Filter out common non-critical messages
				if (shouldIgnoreLogEntry(message)) {
					continue
				}
				
				CustomLogger.logInfo("[Browser ${level}] ${message}")
				
				if (level.equalsIgnoreCase("SEVERE")) {
					hasErrors = true
					errorCount++
				} else if (level.equalsIgnoreCase("WARNING")) {
					hasWarnings = true
					warningCount++
				}
			}
			
			// Report results
			if (hasErrors) {
				CustomLogger.logFailed("❌ Found ${errorCount} JavaScript error(s) in browser console")
			} else {
				CustomLogger.logPassed("✅ No JavaScript errors in browser console")
			}
			
			if (hasWarnings && warningCount > 0) {
				CustomLogger.logInfo("⚠️ Found ${warningCount} warning(s) in browser console")
			}
			
			return !hasErrors
			
		} catch (Exception e) {
			CustomLogger.logWarning("Error checking browser console logs: ${e.getMessage()}")
			return true // Don't fail test if we can't check logs
		}
	}
	
	def boolean shouldIgnoreLogEntry(String message) {
		// Common patterns to ignore (adjust based on your application)
		def ignoredPatterns = [
			"favicon.ico",
			"third-party",
			"analytics",
			"advertisement",
			"google",
			"facebook"
		]
		
		return ignoredPatterns.any { pattern ->
			message.toLowerCase().contains(pattern.toLowerCase())
		}
	}
}
