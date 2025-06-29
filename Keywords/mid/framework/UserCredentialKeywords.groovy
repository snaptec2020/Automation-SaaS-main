package mid.framework

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
import com.kms.katalon.core.configuration.RunConfiguration

import internal.GlobalVariable as GlobalVariable

public class UserCredentialKeywords {

	@Keyword
	def static getUserCredentials() {
		CustomLogger.logInfo("Starting getUserCredentials method")

		try {
			// Get current execution profile
			String currentProfileName = RunConfiguration.getExecutionProfile()
			CustomLogger.logInfo("Current execution profile: '${currentProfileName}'")

			// Load test data
			CustomLogger.logInfo("Loading test data from 'Data Files/MidData'")
			TestData data = findTestData('Data Files/MidData')
			CustomLogger.logInfo("Test data loaded successfully")

			// Initialize variables
			String username = ""
			String password = ""
			String email = ""

			// Get row count and log it
			int rowCount = data.getRowNumbers()
			CustomLogger.logInfo("Total rows in test data: ${rowCount}")

			// Search for matching profile
			CustomLogger.logInfo("Searching for profile '${currentProfileName}' in test data")
			boolean profileFound = false

			for (int i = 1; i <= rowCount; i++) {
				String profileInData = data.getValue("Profile name", i)
				CustomLogger.logInfo("Checking row ${i}: Profile name = '${profileInData}'")

				if (profileInData == currentProfileName) {
					CustomLogger.logInfo("Profile match found at row ${i}")

					username = data.getValue("User Name", i)
					password = data.getValue("Password", i)
					email = data.getValue("email", i)

					// Log retrieved values (mask password for security)
					CustomLogger.logInfo("Retrieved username: '${username}'")
					CustomLogger.logInfo("Retrieved password: ${password ? '*'.multiply(password.length()) : 'empty'}")
					CustomLogger.logInfo("Retrieved email: '${email}'")

					profileFound = true
					break
				}
			}

			// Validate retrieved credentials
			if (username != "" && password != "") {
				CustomLogger.logPassed("User credentials retrieved successfully for profile '${currentProfileName}'")
				CustomLogger.logInfo("Returning credentials map with username, password, and email")

				return [username: username, password: password, email: email]
			} else {
				String errorMsg = "Profile '${currentProfileName}' found but credentials are incomplete. Username: '${username}', Password: ${password ? 'present' : 'missing'}"
				CustomLogger.logError(errorMsg)
				throw new Exception(errorMsg)
			}
		} catch (Exception e) {
			if (e.message.contains("Profile") && e.message.contains("found")) {
				// Re-throw our custom exception
				CustomLogger.logFailed("Failed to retrieve user credentials: ${e.message}")
				throw e
			} else {
				// Handle other exceptions (data file not found, etc.)
				String errorMsg = "Error occurred while retrieving user credentials: ${e.message}"
				CustomLogger.logError(errorMsg)
				CustomLogger.logFailed("getUserCredentials method failed due to unexpected error")
				throw new Exception(errorMsg)
			}
		}
	}

	// Additional helper method with logging for profile validation
	@Keyword
	def static validateProfileExists(String profileName = null) {
		CustomLogger.logInfo("Starting profile validation")

		try {
			String targetProfile = profileName ?: RunConfiguration.getExecutionProfile()
			CustomLogger.logInfo("Validating profile: '${targetProfile}'")

			TestData data = findTestData('Data Files/MidData')
			int rowCount = data.getRowNumbers()
			CustomLogger.logInfo("Loaded test data with ${rowCount} rows")

			for (int i = 1; i <= rowCount; i++) {
				String profileInData = data.getValue("Profile name", i)
				if (profileInData == targetProfile) {
					CustomLogger.logPassed("Profile '${targetProfile}' exists in test data")
					return true
				}
			}

			CustomLogger.logWarning("Profile '${targetProfile}' not found in test data")
			return false
		} catch (Exception e) {
			CustomLogger.logError("Error during profile validation: ${e.message}")
			return false
		}
	}

	// Additional helper method to list all available profiles
	@Keyword
	def static getAvailableProfiles() {
		CustomLogger.logInfo("Retrieving list of available profiles")

		try {
			TestData data = findTestData('Data Files/MidData')
			int rowCount = data.getRowNumbers()
			CustomLogger.logInfo("Scanning ${rowCount} rows for available profiles")

			List<String> profiles = []
			for (int i = 1; i <= rowCount; i++) {
				String profileName = data.getValue("Profile name", i)
				if (profileName && !profiles.contains(profileName)) {
					profiles.add(profileName)
				}
			}

			CustomLogger.logInfo("Found ${profiles.size()} unique profiles: ${profiles}")
			CustomLogger.logPassed("Successfully retrieved available profiles list")

			return profiles
		} catch (Exception e) {
			CustomLogger.logError("Error retrieving available profiles: ${e.message}")
			CustomLogger.logFailed("getAvailableProfiles method failed")
			throw new Exception("Failed to retrieve available profiles: ${e.message}")
		}
	}
}
