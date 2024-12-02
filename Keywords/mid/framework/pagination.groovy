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
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select
import internal.GlobalVariable

class PaginationPage {

	String url = "/"

	@Keyword
	int getSelectedOptionValue2(TestObject selector) {
		WebUI.waitForElementVisible(selector, 10)
		String selectedOptionText = WebUI.getText(selector)
		return Integer.parseInt(selectedOptionText.trim())
	}

	@Keyword
	int getSelectedOptionValue(TestObject selector) {

		WebElement dropdownElement = WebUI.findWebElement(selector)
		Select dropdown = new Select(dropdownElement)
		WebElement selectedOption = dropdown.getFirstSelectedOption()
		String selectedValue = selectedOption.getAttribute('value')
		String selectedText = selectedOption.getText()
		return Integer.parseInt(selectedValue) 
	}

	@Keyword
	List<Integer> getSelectedOptionValue3(TestObject selector) {
		WebUI.waitForElementVisible(selector, 10)
		String selectedOptionText = WebUI.getText(selector).trim()

		// Log the selected option text for debugging
		println("Selected option text: '${selectedOptionText}'")

		// Remove the brackets [] and split the string by commas
		selectedOptionText = selectedOptionText.replaceAll("[\\[\\]']","") // Remove brackets and quotes
		def values = selectedOptionText.split(",") // Split by comma

		// Convert the values to integers
		List<Integer> result = values.collect { Integer.parseInt(it.trim()) }

		// Return the list of integers
		return result
	}

	@Keyword
	int getItemsNum() {
		// Convert the object path to a TestObject
		def testObject = findTestObject("Object Repository/Mid/pagination/ItemsTable")

		// Wait for the elements to be visible
		WebUI.waitForElementVisible(testObject, 10)

		// Find the web elements and return the size of the list
		def elements = WebUI.findWebElements(testObject, 5) // Timeout here is 5 seconds
		return elements.size()
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