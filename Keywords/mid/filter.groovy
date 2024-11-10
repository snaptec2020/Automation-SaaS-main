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

class FilterPage {
	String url = '/'

	@Keyword
	def expandFilter() {
		TestObject isClosedFilter = OR.findTestObject('itemsPageObj/filterPage/closedFilterList')
		boolean filterClosed = WebUI.verifyElementPresent(isClosedFilter, 0)

		if (filterClosed) {
			TestObject expandButton = OR.findTestObject('itemsPageObj/filterPage/expandButton')
			WebUI.verifyElementPresent(expandButton, 0)
			WebUI.click(expandButton)
		} else {
			println('Filter list already opened')
		}
	}

	@Keyword
	def filter(Map<String, Object>[] params) {
		expandFilter()
		params.each { param ->
			def filterName = param.get('filterName')
			def value = param.get('value')

			if (value instanceof List) {
				value.each { val ->
					TestObject selector = OR.findTestObject("itemsPageObj/filterPage/" + filterName)
					WebUI.waitForElementPresent(selector, 0)
					WebUI.click(selector)
					TestObject liSelector = new TestObject().addProperty('xpath', ConditionType.EQUALS, selector.getSelectorCollection()['xpath'] + "/following-sibling::div[text()='" + val + "']")
					WebUI.click(liSelector)
				}
			} else {
				TestObject field = OR.findTestObject("itemsPageObj/filterPage/" + filterName)
				if (filterName == 'incrementId') {
					TestObject incrementIdField = new TestObject().addProperty('xpath', ConditionType.EQUALS, field.getSelectorCollection()['xpath'].replace('${menuName}', GlobalVariable.menuName))
					WebUI.setText(incrementIdField, value.toString())
				} else {
					WebUI.setText(field, value.toString())
				}
			}
		}
	}

	@Keyword
	def clearFilter(boolean allFilter = true, def filterData = '') {
		TestObject itemSelected = OR.findTestObject('itemsPageObj/filterPage/listOfItemSelected')
		if (allFilter) {
			TestObject clearAll = OR.findTestObject('itemsPageObj/filterPage/clearAll')
			if (WebUI.verifyElementVisible(itemSelected, FailureHandling.OPTIONAL)) {
				WebUI.verifyElementPresent(clearAll, 0)
				WebUI.click(clearAll)
			}
		} else if (filterData instanceof List) {
			filterData.each { data ->
				TestObject removeFilter = new TestObject().addProperty('xpath', ConditionType.EQUALS, OR.findTestObject("itemsPageObj/filterPage/removeFilter").getSelectorCollection()['xpath'].replace('${data}', data.toString()))
				WebUI.click(removeFilter)
			}
		} else {
			TestObject removeFilter = new TestObject().addProperty('xpath', ConditionType.EQUALS, OR.findTestObject("itemsPageObj/filterPage/removeFilter").getSelectorCollection()['xpath'].replace('${data}', filterData.toString()))
			WebUI.click(removeFilter)
		}
	}

	@Keyword
	def enabledSearchButton() {
		TestObject searchButton = OR.findTestObject('itemsPageObj/filterPage/searchButton')
		WebUI.click(searchButton)
	}
}