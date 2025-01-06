package mid.framework;

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
import com.kms.katalon.core.testobject.ConditionType
import internal.GlobalVariable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

public class generalAction {

	@Keyword
	def setTextToInputFieldDependOnName(String fieldName, String fieldData) {
		def inputField = generalAction.createTestObject("//*[translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '"+fieldName.toLowerCase()+"']/parent::div//input | //*[translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '"+fieldName.toLowerCase()+"']/parent::span/parent::*//input")
		WebUI.setText(inputField, fieldData)
	}

	@Keyword
	def setDate(String dateValue=LocalDate.now().toString()) {
		//		String script = "document.evaluate('//input[@name=\"dob\"]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.value = '2024-12-30';"
		//		WebUI.executeJavaScript(script, null)
		def inputField = generalAction.createTestObject("//*[translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = 'date of birth']/parent::div//input")
		WebUI.sendKeys(inputField, dateValue)
	}

	@Keyword
	def clickOnDependOnName(String fieldName) {
		def fieldObject = generalAction.createTestObject("//*[translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '"+fieldName.toLowerCase()+"']")
		WebUI.click(fieldObject)
	}

	@Keyword
	def unableFieldDependOnName(String fieldName) {
		def fieldObject = generalAction.createTestObject("//*[translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '"+fieldName.toLowerCase()+"']/parent::div//label")
		WebUI.click(fieldObject)
	}

	@Keyword
	def selectOptionDependOnName(String fieldName, String OptionLabel) {
		def fieldObject = generalAction.createTestObject("//*[translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '"+fieldName.toLowerCase()+"']/../..//select")
		WebUI.selectOptionByLabel(fieldObject, OptionLabel, false)
	}

	@Keyword
	def verifyHeaderText(String expectedText) {
		def headerObject = generalAction.createTestObject("//*[local-name() = 'h1'or local-name() = 'h2'or local-name() = 'h3'or local-name() = 'h4' or local-name() = 'span'][text()='"+expectedText+"']")
		WebUI.verifyElementText(headerObject, expectedText)
	}

	@Keyword
	def verifyMessagePresent(String text) {
		def itemNumberTitle = generalAction.createTestObject("//*[contains(@class,'toast-notifications') and text()='"+text+"'] | //*[contains(@class,'styles_message')]//*[text()='"+text+"']")
		WebUI.verifyElementPresent(itemNumberTitle, 5)
	}

	@Keyword
	def generateRandomString() {
		def randomString = UUID.randomUUID().toString().substring(0, 8)
		return randomString
	}

	public static TestObject createTestObject(String xpath) {
		TestObject testObject = new TestObject("dynamicTestObject_" + xpath.hashCode())
		testObject.addProperty("xpath", ConditionType.EQUALS, xpath)
		return testObject
	}
}
