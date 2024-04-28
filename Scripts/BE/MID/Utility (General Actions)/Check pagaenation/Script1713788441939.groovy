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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import org.openqa.selenium.WebElement as Keys
 

int numberOfItem = 40
int itemsNum = 0

String records = WebUI.getText(findTestObject('Object Repository/BE/MID/Opened Page Items/foundsRecord'))

String recordsNum = records.replaceAll("[^0-9]", "")

int actualRecords = Integer.parseInt(recordsNum)

WebUI.scrollToElement(findTestObject('Object Repository/BE/MID/General Objects/pageSize Dropdown'),3)

WebUI.click(findTestObject('Object Repository/BE/MID/General Objects/pageSize Dropdown'))

WebUI.click(findTestObject('Object Repository/BE/MID/Opened Page Items/NumberOfItem', [('NumberOfItem') : numberOfItem]),FailureHandling.STOP_ON_FAILURE)

itemsNum=WebUI.findWebElements(findTestObject('Object Repository/BE/MID/Opened Page Items/ItemsTable'),10).size()

if(numberOfItem < actualRecords && WebUI.verifyElementVisible(findTestObject('Object Repository/BE/MID/Opened Page Items/pagenation'), FailureHandling.CONTINUE_ON_FAILURE))
{	if(numberOfItem != itemsNum ) {
		KeywordUtil.markFailed("The number of items is not equal to the actual number of items.")
	}
}

else 
{
	if(actualRecords != itemsNum) {
		KeywordUtil.markFailed("The number of items is not equal to the actual number of items.")
	}
}
	
WebUI.getWindowIndex()