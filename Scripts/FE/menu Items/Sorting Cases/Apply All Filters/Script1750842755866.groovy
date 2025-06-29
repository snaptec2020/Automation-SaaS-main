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
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.WebElement as Keys

def prodSize = returnProducts()

for (def index : (1..5)) {
    if (prodSize != 0) {
        break
    } else {
        prodSize = returnProducts()
    }
}

WebUI.callTestCase(findTestCase('FE/Scrolling/scrollingAtTheBottom'), [:], FailureHandling.OPTIONAL)

List<WebElement> products = WebUI.findWebElements(findTestObject('Object Repository/Products/Product container in page'), 
    2)

WebUI.waitForElementVisible(findTestObject('Sorting/Sorting bar'), 2)

WebUI.enhancedClick(findTestObject('Sorting/Sort dropdown btn'))

List sortItems = CustomKeywords.'utility.Utility.findWebElements'('Object Repository/Sorting/Sort dropdown Items', 0)

for (def index : (0..sortItems.size() - 1)) {
    WebElement sortElement = sortItems.get(index)

    WebUI.executeJavaScript('arguments[0].click()', Arrays.asList(sortElement))

	CustomKeywords.'generalactions.generalActions.waiteSpinnerToHide'()


    verifyNumberOfProducts(prodSize) //Change Sorting A-Z or Z-A
	
	WebUI.enhancedClick(findTestObject('Sorting/Sorting Mode'))
	
	CustomKeywords.'generalactions.generalActions.waiteSpinnerToHide'()
	
	if(index != sortItems.size()-1) {
		WebUI.enhancedClick(findTestObject('Sorting/Sort dropdown btn'))
	}
}



def returnProducts() {
    List<WebElement> prod = WebUI.callTestCase(findTestCase('Test Cases/FE/menu Items/Select Category contains Product'), 
        [:], FailureHandling.STOP_ON_FAILURE)

    return prod.size()
}

def verifyNumberOfProducts(def prodSize) {
	WebUI.callTestCase(findTestCase('FE/Scrolling/scrollingAtTheBottom'), [:], FailureHandling.OPTIONAL)
	
    List<WebElement> products = WebUI.findWebElements(findTestObject('Object Repository/Products/Product container in page'), 
        2)

    WebUI.verifyEqual(products.size(), prodSize)
}

