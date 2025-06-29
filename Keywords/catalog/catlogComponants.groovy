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
import com.utils.CustomLogger as CustomLogger
import generalactions.generalActions as generalActions
import internal.GlobalVariable as GlobalVariable
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
		CustomLogger.logInfo("Running Mode is: ${GlobalVariable.RunningMode}")
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
		CustomLogger.logInfo("Waiting ${elementIndex.toString()} to be clickable")
		WebUI.waitForElementClickable(tb, 2,FailureHandling.OPTIONAL)
		CustomLogger.logInfo("Clicking on: ${elementIndex.toString()}")
		//CustomKeywords.'utility.Utility.clickOnObjectusingJavaScript'(tb)
		WebUI.click(tb,FailureHandling.OPTIONAL)
		CustomLogger.logInfo("Checking the sppinner if it's displaying")
		generalActions.waiteSpinnerToHide()
		CustomLogger.logInfo("Moving to the element")
		utilityFunctions.moveToElement()
		CustomLogger.logInfo("getSpecifiedCatalogElement finished")
	}

	/**
	 * Get total count of available catalog elements
	 */
	@Keyword
	private int getCatalogElementCount() {
		try {
			String xpath = ""
			switch(GlobalVariable.RunningMode) {
				case "1":
					xpath = findTestObject('Object Repository/Mega Menu/Catalog list').findPropertyValue('xpath')
					break
				case "2":
				// Open mobile menu first
					WebUI.waitForElementClickable(findTestObject('Object Repository/Mega Menu/MegaMenuefromMobile'), 0)
					WebUI.click(findTestObject('Object Repository/Mega Menu/MegaMenuefromMobile'))
					Thread.sleep(1000)
					xpath = findTestObject('Object Repository/Mega Menu/MenuSider on mobile').findPropertyValue('xpath')
					break
			}

			// Count elements using JavaScript
			def elementCount = WebUI.executeJavaScript("""
            var xpath = "${xpath.replace('xpath=', '')}";
            var result = document.evaluate(xpath, document, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
            return result.snapshotLength;
        """, [])

			return elementCount as Integer
		} catch (Exception e) {
			CustomLogger.logInfo("Error counting elements: ${e.getMessage()}")
			return 0
		}
	}

	/**
	 * Select and click a random catalog element
	 */
	@Keyword
	public def getRandomCatalogElement(List catalogList = []) {
		CustomLogger.logInfo("Getting random catalog element...")

		int totalElements = getCatalogElementCount()
		if (totalElements <= 0) {
			CustomLogger.logInfo("No catalog elements found")
			return -1
		}

		// Generate random index (0-based)
		Random random = new Random()
		int randomIndex = random.nextInt(totalElements)

		CustomLogger.logInfo("Total elements: ${totalElements}, Selected random index: ${randomIndex}")

		// Use your existing method
		getSpecifiedCatalogElement(randomIndex, catalogList)
		return randomIndex
	}

	/**
	 * Select random catalog element excluding specific ones
	 */
	@Keyword
	public def getRandomCatalogElementExcluding(List<Integer> excludeIndices, List catalogList = []) {
		CustomLogger.logInfo("Getting random catalog element with exclusions...")

		int totalElements = getCatalogElementCount()
		if (totalElements <= 0) {
			CustomLogger.logInfo("No catalog elements found")
			return -1
		}

		// Build list of available indices
		List<Integer> availableIndices = []
		for (int i = 0; i < totalElements; i++) {
			if (!excludeIndices.contains(i)) {
				availableIndices.add(i)
			}
		}

		if (availableIndices.isEmpty()) {
			CustomLogger.logInfo("No available elements after exclusions")
			return -1
		}

		// Select random from available
		Random random = new Random()
		int selectedIndex = availableIndices[random.nextInt(availableIndices.size())]

		CustomLogger.logInfo("Available indices: ${availableIndices}, Selected: ${selectedIndex}")

		// Use your existing method
		getSpecifiedCatalogElement(selectedIndex, catalogList)
		return selectedIndex
	}
}

