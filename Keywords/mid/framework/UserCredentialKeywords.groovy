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
import com.kms.katalon.core.configuration.RunConfiguration

import internal.GlobalVariable

public class UserCredentialKeywords {
	@Keyword
	def static getUserCredentials() {
		String currentProfileName = RunConfiguration.getExecutionProfile()
		TestData data = findTestData('Data Files/MidData')

		String username = ""
		String password = ""
		String email = ""
		
		int rowCount = data.getRowNumbers()
		for (int i = 1; i <= rowCount; i++) {
			if (data.getValue("Profile name", i) == currentProfileName) {
				username = data.getValue("User Name", i)
				password = data.getValue("Password", i)
				email = data.getValue("email", i)
				break
			}
		}

		if (username != "" && password != "") {
			return [username: username, password: password, email:email]
		} else {
			throw new Exception("Profile not found in data file!")
		}
	}
}
