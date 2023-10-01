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
WebUI.verifyNotEqual(CustomKeywords.'utility.Utility.checkIfElementExist'('Object Repository/BE/MID/Landing Page/Side Bar contents'), '0', FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyEqual(CustomKeywords.'utility.Utility.checkIfElementExist'('Object Repository/BE/MID/Dashboard obj/Cards'), '10', FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyEqual(CustomKeywords.'utility.Utility.checkIfElementExist'('Object Repository/BE/MID/Dashboard obj/Store View contents'), '2', FailureHandling.CONTINUE_ON_FAILURE)
WebUI.callTestCase(findTestCase('BE/MID/General Actions/Open Menu Item by Name'), [('menuItemName') : "Orders", ('menuGroupName') : "Sales"], 
    FailureHandling.STOP_ON_FAILURE)

