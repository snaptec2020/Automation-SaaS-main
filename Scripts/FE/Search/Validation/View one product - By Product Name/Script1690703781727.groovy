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

WebUI.callTestCase(findTestCase('Test Cases/FE/Search/Verification/Verify elemnts for the search'), [:], FailureHandling.STOP_ON_FAILURE)

switch (GlobalVariable.searchMode) {
    case 'Normal':
        WebUI.setText(findTestObject('Object Repository/Search contents/Search'), GlobalVariable.textSearch[0])
        
        WebUI.verifyElementVisible(findTestObject('Object Repository/Search contents/Search box/Search results container'), 
            FailureHandling.OPTIONAL)
		
        WebUI.click(findTestObject('Object Repository/Search contents/Search box/Select Product in the container'), FailureHandling.STOP_ON_FAILURE)
		CustomKeywords.'generalactions.generalActions.waiteSpinnerToHide'()
		WebUI.verifyElementVisible(CustomKeywords.'utility.Utility.addXpathToTestObject'("//span[@class='sku__value'] | //div[starts-with(@class,'styles_sku__')]/div[starts-with(@class,'styles_groupValue__')]/span[starts-with(@class,'styles_value__')][1]"), FailureHandling.OPTIONAL)
		WebUI.verifyElementVisible(CustomKeywords.'utility.Utility.addXpathToTestObject'("//h2[@class='product-content__title'] | //h1[starts-with(@class,styles_nameProduct__)]"), FailureHandling.OPTIONAL)
        break
    case 'Non-Normal':

        WebUI.setText(findTestObject('Object Repository/Search contents/input'), GlobalVariable.textSearch[0])
        
        WebUI.verifyElementVisible(findTestObject('Object Repository/Search contents/Search box/Search results container'))

        WebUI.verifyElementVisible(findTestObject('Object Repository/Search contents/Search box/Select Product in the container'))

        WebUI.click(findTestObject('Object Repository/Search contents/Search box/Select Product in the container'), FailureHandling.STOP_ON_FAILURE)
		
		CustomKeywords.'generalactions.generalActions.waiteSpinnerToHide'()
		
		WebUI.verifyElementVisible(CustomKeywords.'utility.Utility.addXpathToTestObject'("//span[@class='sku__value'] | //div[starts-with(@class,'styles_sku__')]/div[starts-with(@class,'styles_groupValue__')]/span[starts-with(@class,'styles_value__')][1]"), FailureHandling.OPTIONAL)
		WebUI.verifyElementVisible(CustomKeywords.'utility.Utility.addXpathToTestObject'("//h2[@class='product-content__title'] | //h1[starts-with(@class,styles_nameProduct__)]"), FailureHandling.OPTIONAL)
		
        //  WebUI.callTestCase(findTestCase('null'), [:], FailureHandling.STOP_ON_FAILURE)
        break
}
