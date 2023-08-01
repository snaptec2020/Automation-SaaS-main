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
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keyss

//KeywordUtil.logInfo('0000000000000000000000000000000000\t' + GlobalVariable.textSearch)


WebUI.callTestCase(findTestCase('Test Cases/FE/Search/Verification/Verify elemnts for the search'), [:], FailureHandling.STOP_ON_FAILURE)

switch (GlobalVariable.searchMode) {
    case 'Normal':
        WebUI.setText(findTestObject('Object Repository/Search contents/Search box/Search Test box'), GlobalVariable.textSearch[0])

        WebUI.verifyElementVisible(findTestObject('Object Repository/Search contents/Search box/Search results container'), 
            FailureHandling.CONTINUE_ON_FAILURE)

        WebUI.click(findTestObject('Object Repository/Search contents/Search button'), FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyElementVisible(findTestObject('Object Repository/Search contents/Search page/Filter button'))

        break
    case 'Non-Normal':
        WebUI.setText(findTestObject('Object Repository/Search contents/input'), GlobalVariable.textSearch[0])
		
		WebUI.verifyElementVisible(findTestObject('Object Repository/Search contents/Search box/Search results container'),
			FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.click(findTestObject('Search contents/Serach Button Icon'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Search contents/Search page/Filter button'))
		
		break
}
