import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebElement

TestObject foundsRecord = findTestObject('Object Repository/Mid/pagination/foundsRecord')
TestObject pageSizeDropdown = findTestObject('Object Repository/Mid/pagination/pageSizeDropdown')
TestObject listOfItemsPerPage = findTestObject('Object Repository/Mid/pagination/listOfItemsPerPage')


WebUI.callTestCase(findTestCase('Test Cases/BE/MID/Sales/Credit Memos/Open Credit Memo Page'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(foundsRecord, 10, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(pageSizeDropdown, 10, FailureHandling.STOP_ON_FAILURE)


def selectedPageNumber = CustomKeywords.'mid.framework.PaginationPage.getSelectedOptionValue'(listOfItemsPerPage)
int ItemsNum = 0

List<WebElement> allPageLinks = WebUI.findWebElements(findTestObject("Object Repository/Mid/pagination/SpecificPageLink"), 10)

for (WebElement li : allPageLinks) {
	
	li.click()

	ItemsNum = CustomKeywords.'mid.framework.PaginationPage.getItemsNum'()

	// Validate the number of items
	if ((ItemsNum <= selectedPageNumber) && (ItemsNum > 0)) {
		WebUI.comment("Number of items ($ItemsNum) is less than or equal to product")
	} else {
		throw new Exception("Number of items ($ItemsNum) is greater than selectedPageNumber or no product shows")
	}
}



