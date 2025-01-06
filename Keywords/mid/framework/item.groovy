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
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import mid.framework.generalAction
import mid.framework.PaginationPage


public class item {

	@Keyword
	def editItem(boolean isView = false, boolean isRandom = false, int incrementID = 0) {
		def itemsList = generalAction.createTestObject("//div[contains(@class,'row-table-custom')]/div[starts-with(@class,'styles_list__')]//div[starts-with(@class,'styles_content__')]")
		def itemNumberTitle = generalAction.createTestObject("//span[contains(@class, 'styles_title') and contains(text(),'#')]")
		def itemTitle = generalAction.createTestObject("//span[contains(@class, 'styles_title')]")
		def item
		WebUI.waitForElementVisible(itemsList, 10)

		if (isRandom) {
			int randomNumber = (Math.random() * 20).toInteger() + 1
			item = generalAction.createTestObject("//div[@id='table-with-scroll']/div[contains(@class, 'row-table-custom')]["+randomNumber+"]//*[contains(@class, 'feather-edit')] | //div[@id='table-with-scroll']/div[contains(@class,'styles_container')]["+randomNumber+"]//*[contains(@class, 'feather-edit')]")
		} else {
			item = generalAction.createTestObject("//div[@id='table-with-scroll']/div[contains(@class, 'row-table-custom')]["+incrementID+"]//*[contains(@class, 'feather-edit')] | //div[@id='table-with-scroll']/div[contains(@class,'styles_container')]["+incrementID+"]//*[contains(@class, 'feather-edit')]")
		}

		WebUI.waitForElementVisible(item, 10)
		WebUI.click(item)
		boolean isItemNumberTitlePresent = WebUI.verifyElementPresent(itemNumberTitle, 10, FailureHandling.OPTIONAL)
		boolean isItemTitlePresent = WebUI.verifyElementPresent(itemTitle, 10, FailureHandling.OPTIONAL)
		
		if(isItemNumberTitlePresent) {
				// Wait for the itemNumberTitle to be visible
				WebUI.waitForElementVisible(itemNumberTitle, 10)
				// Retrieve the text from the element
				def itemNumberText = WebUI.getText(itemNumberTitle)
				// Check if the text matches the expected format
				def myRegex = ~/^\w+ # \d+$/
				if (!myRegex.matcher(itemNumberText).matches()) {
					println("Item number title did not match the expected format.")
				}
		}else if (isItemTitlePresent) {
			WebUI.waitForElementVisible(itemTitle, 10)
		}else {
					
		}
			
	}

	@Keyword
	def selectItem(boolean isRandom = false, List<Integer> incrementID = []) {
		def itemsList = generalAction.createTestObject("//div[contains(@class,'row-table-custom')]/div[starts-with(@class,'styles_list__')]//div[starts-with(@class,'styles_content__')]")
		def itemNumberTitle = generalAction.createTestObject("//span[contains(@class, 'styles_title') and contains(text(),'#')]")
		def item
		WebUI.waitForElementVisible(itemsList, 10)
		PaginationPage p = new PaginationPage()
		def ItemsNum = p.getItemsNum()
		if (isRandom) {
			while (ItemsNum > 0) {
				item = generalAction.createTestObject("//div[@id='table-with-scroll']/div[contains(@class, 'row-table-custom')]["+ItemsNum+"]//*[@type = 'checkbox']")
				WebUI.waitForElementVisible(item, 10)
				WebUI.click(item)
				ItemsNum-=2
			}
		} else if(incrementID.size() > 0) {
			incrementID.each { id ->
				item = generalAction.createTestObject("//div[@id='table-with-scroll']/div[contains(@class, 'row-table-custom')][" + id + "]//*[@type = 'checkbox']")
				WebUI.waitForElementVisible(item, 10)
				WebUI.click(item)
			}
		}
	}
	
	@Keyword
	def exportItems(String text) {
		WebUI.click(findTestObject('Object Repository/Mid/items/Export Button'))
		def exportType = generalAction.createTestObject("//span[text()='"+text+"']")
		WebUI.click(exportType)
	}
}
