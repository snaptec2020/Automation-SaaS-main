package generalactions

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
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class notificationsObject {

	TestObject tb = new TestObject()

	TestObject getNotificationObject(def arText, def enText) {
		tb.addProperty('xpath', ConditionType.EQUALS, "//div[contains(@class,'react-toast-notifications__container')]//*[contains(@class,'react-toast-notifications__toast__content')][normalize-space()='"+arText+"' or normalize-space() ='"+enText+"']")
		return tb
	}

	@Keyword
	def verifyNotificationVisble(def arText, def enText) {
		WebUI.verifyElementVisible(getNotificationObject(arText,enText), FailureHandling.CONTINUE_ON_FAILURE)
	}
	@Keyword
	def verifyNotificationNotVisble(def arText, def enText) {
		WebUI.verifyElementNotVisible(getNotificationObject(arText,enText), FailureHandling.CONTINUE_ON_FAILURE)
	}
	@Keyword
	def getMessageText() {
		tb.addProperty('xpath', ConditionType.EQUALS, "//div[contains(@class,'react-toast-notifications__container')]//*[contains(@class,'react-toast-notifications__toast__content')]")
		
		return WebUI.getText(tb, FailureHandling.CONTINUE_ON_FAILURE)
	}
}
