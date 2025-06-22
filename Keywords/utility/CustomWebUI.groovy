package utility

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class CustomWebUI {

	static boolean verifyElementVisibleWithTimeout(TestObject testObject, int timeoutInSeconds, FailureHandling failureHandling = FailureHandling.STOP_ON_FAILURE) {
		try {
			if (WebUI.waitForElementVisible(testObject, timeoutInSeconds, FailureHandling.OPTIONAL)) {
				KeywordUtil.markPassed("Object Visible")
				return true
			} else {
				String errorMessage = "Object is not visible"
				KeywordUtil.markFailed(errorMessage)

				// Handle failure based on the parameter
				if (failureHandling == FailureHandling.CONTINUE_ON_FAILURE || failureHandling == FailureHandling.OPTIONAL) {
					return false
				} else {
					throw new Exception(errorMessage)
				}
			}
		} catch (Exception e) {
			String errorMessage = "Element not visible within ${timeoutInSeconds} seconds: ${e.getMessage()}"
			KeywordUtil.markFailed(errorMessage)

			// Handle failure based on the parameter
			if (failureHandling == FailureHandling.CONTINUE_ON_FAILURE || failureHandling == FailureHandling.OPTIONAL) {
				return false
			} else {
				throw new Exception(errorMessage)
			}
		}
	}
}
