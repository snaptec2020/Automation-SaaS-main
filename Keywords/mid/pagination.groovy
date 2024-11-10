package mid

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

import internal.GlobalVariable

class PaginationPage {

	String url = "/"

	@Keyword
	int getSelectedOptionValue(TestObject selector) {
		WebUI.waitForElementVisible(selector, 10)
		String selectedOptionText = WebUI.getText(selector)
		return Integer.parseInt(selectedOptionText.trim())
	}

	@Keyword
	int getItemsNum(TestObject itemsTableSelector) {
		WebUI.waitForElementVisible(itemsTableSelector, 10)
		return WebUI.findWebElements(itemsTableSelector, 10).size()
	}

	@Keyword
	void selectOption(TestObject dropdownSelector, Boolean isByIndex = false, Object indexID = null, Object value = null) {
		WebUI.waitForElementClickable(dropdownSelector, 10)
		WebUI.click(dropdownSelector)

		if (isByIndex && indexID != null) {
			WebUI.selectOptionByIndex(dropdownSelector, indexID as int)
		} else if (value != null) {
			WebUI.selectOptionByValue(dropdownSelector, value.toString(), false)
		}
	}
}