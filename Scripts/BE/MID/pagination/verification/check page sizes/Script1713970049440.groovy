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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.By as By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.WebElement as Keys
import org.openqa.selenium.support.ui.Select as Select
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

WebUI.scrollToElement(findTestObject('Object Repository/BE/MID/General Objects/pageSize Dropdown'), 3)

WebUI.click(findTestObject('Object Repository/BE/MID/General Objects/pageSize Dropdown'))

int dropdownSize = WebUI.findWebElements(findTestObject('Object Repository/BE/MID/Opened Page Items/NumberOfItem'), 10).size()

switch(checkAllSizes) {
	case true:
		for (int i = 0; i < dropdownSize; i++) {
			WebUI.callTestCase(findTestCase('BE/MID/pagination/General Action/Change page size'), [('value') : '', ('isByIndex') : true
					, ('indexID') : i], FailureHandling.STOP_ON_FAILURE)
			checkCurrentPage()
		}
		break;
		
	case false:
		WebUI.callTestCase(findTestCase('BE/MID/pagination/General Action/Change page size'), [('value') : pageSize, ('isByIndex') : false
			, ('indexID') : ''], FailureHandling.STOP_ON_FAILURE)
		checkCurrentPage()
	
}



def checkCurrentPage() {
	String records = WebUI.getText(findTestObject('Object Repository/BE/MID/Opened Page Items/foundsRecord'))
	String recordsNum = records.replaceAll("[^0-9]", "")
	int actualRecords = Integer.parseInt(recordsNum)
	
	Select select = new Select(DriverFactory.getWebDriver().findElement(By.xpath('//div[@id=\'table-with-scroll\']/following-sibling::div//div[contains(@class,\'styles_dropdown\')]/select')))	
	int selectedoption = Integer.parseInt(select.getFirstSelectedOption().getText())
	int itemsNum = WebUI.findWebElements(findTestObject('Object Repository/BE/MID/Opened Page Items/ItemsTable'), 10).size()
	
	if(selectedoption < actualRecords && WebUI.verifyElementVisible(findTestObject('Object Repository/BE/MID/Opened Page Items/pagenation'), FailureHandling.CONTINUE_ON_FAILURE))
		{
			WebUI.verifyEqual(itemsNum,selectedoption)
		}	
		else
		{
			WebUI.verifyLessThanOrEqual(itemsNum,selectedoption)
		}
	
}
