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

//We want to add all check after Launch here like Header, footer and header contents (signin, favorit, logo)
WebUI.callTestCase(findTestCase('FE/Website launch/Website launch'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('Headers and Footers/Header contents/Web Header'))

WebUI.verifyElementVisible(findTestObject('Headers and Footers/Header contents/Logo')) //Verify the logo is exists

WebUI.verifyElementVisible(findTestObject('Headers and Footers/Header contents/Login'))

WebUI.verifyElementVisible(findTestObject('Headers and Footers/Header contents/Fav'))

WebUI.verifyElementVisible(findTestObject('Headers and Footers/Header contents/Search'))

WebUI.verifyElementVisible(findTestObject('Headers and Footers/Header contents/Cart'))

WebUI.verifyElementVisible(findTestObject('Headers and Footers/Footer contents/Web footer'))

