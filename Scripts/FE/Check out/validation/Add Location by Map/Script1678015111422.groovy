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

/*
 * WebUI.click(findTestObject('Check Out/Zoom In (Map)'))
 * 
 * int mapHeight = WebUI.getElementHeight(findTestObject('Object Repository/Check Out/Map Block')) / 2
 * 
 * int mapWidth = WebUI.getElementWidth(findTestObject('Object Repository/CheckOut/Map Block')) / 2
 */
WebUI.clickOffset(findTestObject('Check Out/Map Block'), 0, 0, FailureHandling.CONTINUE_ON_FAILURE)
//WebUI.doubleClick(findTestObject('Check Out/Map Block'), FailureHandling.CONTINUE_ON_FAILURE)


//WebUI.doubleClick(findTestObject('Check Out/map ic marker'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('Check Out/Map postion after click on maker'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('Check Out/Save location Map Button'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('Check Out/Save location Map Button'), FailureHandling.CONTINUE_ON_FAILURE)