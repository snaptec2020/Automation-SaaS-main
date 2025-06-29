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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.utils.CustomLogger

import com.kms.katalon.core.annotation.Keyword
import internal.GlobalVariable

public class LocalStorageUtilityClass {

	@Keyword
	def getLocalStorageValueBasedOnKey(def keyToCheck) {
		// Note: This method returns the actual value or null
		try {
			String alternativeValue = WebUI.executeJavaScript("return localStorage.getItem('${keyToCheck}');", null)

			if (alternativeValue != null && !alternativeValue.trim().isEmpty()) {
				CustomLogger.logInfo("Key has value: ${alternativeValue}")
				return alternativeValue
			} else if (alternativeValue != null && alternativeValue.trim().isEmpty()) {
				CustomLogger.logWarning("Key exists but is empty")
				return ""
			} else {
				CustomLogger.logWarning("Key does not exist")
				return null  // Return null instead of empty string to distinguish between empty and non-existent
			}
		} catch (Exception e) {
			CustomLogger.logError("Error with alternative method: ${e.getMessage()}")
			return null
		}
	}

	@Keyword
	def boolean isLocalStorageValueBasedOnKeyExists(def keyToCheck) {
		def value = getLocalStorageValueBasedOnKey(keyToCheck)
		// Check for both null and empty string
		return (value != null && value != "")
	}

	@Keyword
	def checkMultipleKeys(List<String> keys) {
		// Function to check multiple keys at once
		def mappedValues = [:]
		keys.each { key ->
			try {
				def value = getLocalStorageValueBasedOnKey(key)
				mappedValues[key] = value  // Correct map syntax
			} catch (Exception e) {
				CustomLogger.logError("Error checking ${key}: ${e.getMessage()}")
				mappedValues[key] = null  // Set to null on error
			}
		}
		return mappedValues
	}

	@Keyword
	def boolean doesLocalStorageKeyExist(def keyToCheck) {
		// Additional utility method to check if key exists (regardless of value)
		try {
			String value = WebUI.executeJavaScript("return localStorage.getItem('${keyToCheck}');", null)
			return value != null  // Returns true if key exists, even if empty
		} catch (Exception e) {
			CustomLogger.logError("Error checking key existence: ${e.getMessage()}")
			return false
		}
	}

	@Keyword
	def getAllLocalStorageKeys() {
		// Method to get all localStorage keys
		try {
			def keys = WebUI.executeJavaScript("return Object.keys(localStorage);", null)
			CustomLogger.logInfo("Found ${keys.size()} keys in localStorage")
			return keys
		} catch (Exception e) {
			CustomLogger.logError("Error getting all keys: ${e.getMessage()}")
			return []
		}
	}

	@Keyword
	def removeLocalStorageKey(def keyToRemove) {
		// Method to remove a key from localStorage
		try {
			WebUI.executeJavaScript("localStorage.removeItem('${keyToRemove}');", null)
			CustomLogger.logInfo("Removed key: ${keyToRemove}")
			return true
		} catch (Exception e) {
			CustomLogger.logError("Error removing key ${keyToRemove}: ${e.getMessage()}")
			return false
		}
	}

	@Keyword
	def setLocalStorageValue(def key, def value) {
		// Method to set a value in localStorage
		try {
			WebUI.executeJavaScript("localStorage.setItem('${key}', '${value}');", null)
			CustomLogger.logInfo("Set key: localStorage.setItem('${key}', '${value}');")
			return true
		} catch (Exception e) {
			CustomLogger.logError("Error setting key ${key}: ${e.getMessage()}")
			return false
		}
	}
}