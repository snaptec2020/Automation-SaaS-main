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
import utility.LocalStorageUtility as LocalStorageUtility
import org.openqa.selenium.Keys as Keys

if (!(CustomKeywords.'utility.localStorageUtility.isLocalStorageValueBasedOnKeyExists'('BROWSER_PERSISTENCE__signin_token'))) {
    WebUI.callTestCase(findTestCase('Test Cases/BE/MID/SignIn - SingOut/Sucess SignIn'), [:], FailureHandling.STOP_ON_FAILURE)
}

WebUI.click(findTestObject('BE/MID/Sign Out/Dropdown to logout'))

WebUI.click(findTestObject('BE/MID/Sign Out/Signout button'))

WebUI.click(findTestObject('BE/MID/Sign Out/Confirm Logout'))

WebUI.verifyElementVisible(findTestObject('BE/MID/Sign In/button_Sign-In'))