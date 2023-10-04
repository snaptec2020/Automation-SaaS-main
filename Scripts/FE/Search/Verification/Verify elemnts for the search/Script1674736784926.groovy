import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL

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



//WebUI.callTestCase(findTestCase('Test Cases/FE/Website launch/Validations/Website launch'), [:], FailureHandling.STOP_ON_FAILURE)


if (WebUI.waitForElementVisible(findTestObject('Object Repository/Search contents/Search button'), 5)) {
	GlobalVariable.searchMode='Normal'
	WebUI.verifyElementVisible(findTestObject('Object Repository/Search contents/Search'))
	
	WebUI.verifyElementVisible(findTestObject('Object Repository/Search contents/Search button'));
}else {

	GlobalVariable.searchMode='Non-Normal'
WebUI.verifyElementVisible(findTestObject('Search contents/Serach button container'))

WebUI.click(findTestObject('Search contents/Serach button container'))
WebUI.delay(2)
WebUI.verifyElementVisible(findTestObject('Search contents/Serach Input group'),FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('Search contents/Serach Button Icon'),FailureHandling.CONTINUE_ON_FAILURE)
}