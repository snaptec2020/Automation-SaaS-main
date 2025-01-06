package catalog

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import generalactions.generalActions
import internal.GlobalVariable
import utility.Utility

public class catlogComponants {
	TestObject tb=new TestObject()
	def generalActions = new generalActions()
	def utilityFunctions = new Utility()
	@Keyword
	public def getCategoryElements() {
		List Catalogs
		switch(GlobalVariable.RunningMode) {
			case "1": Catalogs = WebUI.findWebElements(findTestObject('Object Repository/Mega Menu/Catalog list'),10)
				break
			case "2":
				WebUI.click(findTestObject('Object Repository/Mega Menu/MegaMenuefromMobile'))
				Catalogs= WebUI.findWebElements(findTestObject('Object Repository/Mega Menu/MenuSider on mobile'),10)
				WebUI.click(findTestObject('Object Repository/Mega Menu/Close MM Mobile'))
				break
		}


		return Catalogs
	}

	@Keyword
	public def getSpecifiedCatalogElement(int elementIndex,List catalogList) {

		elementIndex =elementIndex+1

		switch(GlobalVariable.RunningMode) {
			case "1":
				tb = utilityFunctions.addXpathToTestObject(findTestObject('Object Repository/Mega Menu/Catalog list').findPropertyValue('xpath') + "["+elementIndex+"]")
				break
			case "2":WebUI.waitForElementClickable(findTestObject('Object Repository/Mega Menu/MegaMenuefromMobile'), 0)
				WebUI.click(findTestObject('Object Repository/Mega Menu/MegaMenuefromMobile'))

				Thread.sleep(1000);						
				tb = utilityFunctions.addXpathToTestObject("("+findTestObject('Object Repository/Mega Menu/MenuSider on mobile').findPropertyValue('xpath') + ")["+elementIndex+"]")
				break
		}
		WebUI.waitForElementClickable(tb, 5)
		WebUI.click(tb,FailureHandling.CONTINUE_ON_FAILURE)
		generalActions.waiteSpinnerToHide()
		utilityFunctions.moveToElement()
	}
}

