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
import internal.GlobalVariable
import utility.Utility

public class catlogComponants {
	TestObject tb=new TestObject()
	def utilityFunctions = new Utility()
	@Keyword
	public def getCategoryElements() {
		List Catalogs
		switch(GlobalVariable.RunningMode) {
			case "1": Catalogs = WebUI.findWebElements(findTestObject('Object Repository/Mega Menu/Catalog list'),30)
				break
			case "2": //WebUI.waitForElementVisible(findTestObject('Object Repository/Mega Menu/MegaMenuefromMobile'), 10)
				WebUI.click(findTestObject('Object Repository/Mega Menu/MegaMenuefromMobile'))
				Catalogs= WebUI.findWebElements(findTestObject('Object Repository/Mega Menu/MenuSider on mobile'),30)
				WebUI.click(findTestObject('Object Repository/Mega Menu/Close MM Mobile'))
				break
		}


		return Catalogs
	}

	@Keyword
	public def getSpecifiedCatalogElement(int elementIndex,List catalogList) {

		elementIndex =elementIndex+1



		//catalogList.get(elementIndex).click()


		//if (elementIndex >=0)
		switch(GlobalVariable.RunningMode) {
			case "1":
				tb = utilityFunctions.addXpathToTestObject(findTestObject('Object Repository/Mega Menu/Catalog list').findPropertyValue('xpath') + "["+elementIndex+"]")
			//tb.addProperty('xpath', ConditionType.EQUALS, "//a[contains(@class,'styles_megaMenuItem')]["+elementIndex+"]")
				break
			case "2":WebUI.waitForElementClickable(findTestObject('Object Repository/Mega Menu/MegaMenuefromMobile'), 0)
				WebUI.click(findTestObject('Object Repository/Mega Menu/MegaMenuefromMobile'))

				Thread.sleep(1000);								//button[@class='mega-menu-sidebar__item-title']
				tb = utilityFunctions.addXpathToTestObject("("+findTestObject('Object Repository/Mega Menu/MenuSider on mobile').findPropertyValue('xpath') + ")["+elementIndex+"]")
			//tb.addProperty('xpath', ConditionType.EQUALS, "(//button[@class='mega-menu-sidebar__item-title'])["+elementIndex+"]")
				break
		}
		/*		catalogList.get(elementIndex).click()
		 Thread.sleep(2000);*/
		WebUI.waitForElementClickable(tb, 30)
		WebUI.click(tb,FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.delay(2)
		//WebUI.mouseOver(findTestObject('Headers and Footers/Header contents/Logo'), FailureHandling.CONTINUE_ON_FAILURE)
		utilityFunctions.moveToElement()
		/*		else {
		 WebUI.scrollToPosition(9999999, 9999999)
		 WebUI.scrollToPosition(9999999, 9999999)
		 WebUI.click(findTestObject('Object Repository/Mega Menu/another catategory'), FailureHandling.STOP_ON_FAILURE)
		 //catalogList.get(2).click()
		 }*/
	}
}

