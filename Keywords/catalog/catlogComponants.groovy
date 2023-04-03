package catalog

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

public class catlogComponants {
	@Keyword
	public def getCategoryElements() {
		List Catalogs = WebUI.findWebElements(findTestObject('Object Repository/Mega Menu/Catalog list'),30)


		return Catalogs
	}

	@Keyword
	public def getSpecifiedCatalogElement(int elementIndex,List catalogList) {
		//if (elementIndex >=0)
		
		catalogList.get(elementIndex).click()
		Thread.sleep(2000);
		WebUI.mouseOver(findTestObject('Headers and Footers/Footer contents/Web footer'), FailureHandling.CONTINUE_ON_FAILURE)
		/*		else {
		 WebUI.scrollToPosition(9999999, 9999999)
		 WebUI.scrollToPosition(9999999, 9999999)
		 WebUI.click(findTestObject('Object Repository/Mega Menu/another catategory'), FailureHandling.STOP_ON_FAILURE)
		 //catalogList.get(2).click()
		 }*/
	}
}

