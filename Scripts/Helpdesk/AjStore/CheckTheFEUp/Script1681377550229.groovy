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
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl('https://www.ajstore.com/')
//CustomKeywords.'customUtils.Util.getElementByXPath'("//button[contains(@class,'storeSwitcher-trigger-')]")
WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Shared/Language'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Page_/img__image-image-2gD image-loaded-SHk image_3b67db'))

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Page_/svg'))

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Page_/svg_1'))

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Page_/svg_1_2'))

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Page_/svg_1_2_3'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Page_/div_'))

WebUI.verifyElementText(findTestObject('Object Repository/Helpdesk/AjStore/Page_/a_'), 'رجالي')

WebUI.verifyElementText(findTestObject('Object Repository/Helpdesk/AjStore/Page_/a__1'), 'شبابي')

WebUI.verifyElementText(findTestObject('Object Repository/Helpdesk/AjStore/Page_/a__1_2'), 'أطفال')

WebUI.verifyElementText(findTestObject('Object Repository/Helpdesk/AjStore/Page_/a__1_2_3'), 'نسائي')

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Page_/a__1_2_3_4'))

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Page_/a__1_2_3_4_5'))

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Page_/a__1_2_3_4_5_6'))

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Page_/a__1_2_3_4_5_6_7'))

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Page_/a__1_2_3_4_5_6_7_8'))

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Page_/a__1_2_3_4_5_6_7_8_9'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Page_/a__1'))

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Page_-/a_'))

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Page_-/a__1'))

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Page_-/a__1_2'))

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Page_-/a__1_2_3'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Page_-/a__1_2_3_4'))

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Page_-/a__1_2_3_4_5'))

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Page_-/a__1_2_3_4_5_6'))

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Page_-/a__1_2_3_4_5_6_7'))

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Page_-/a__1_2_3_4_5_6_7_8'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Page_-/a__1_2_3_4_5_6_7_8_9'))

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Page_-/a__1_2_3_4_5_6_7_8_9_10'))

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Page_-/a__1_2_3_4_5_6_7_8_9_10_11'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Page_-/img__image-image-2gD image-loaded-SHk image_3b67db'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Page_/div_0      5354555657795.00 .  0  ()  113.8_ee034d'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Page_/div__1'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Page_/div__1_2'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Page_/div__1_2_3'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Page_/div__1_2_3_4'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Page_/div__1_2'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Page_/div__1_2_3'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Page_/div__1_2_3_4'))

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Page_/div__1'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Page_/h2_'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Page_/div__1_2_3_4_5'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Page_/footer_2022'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Page_/div__footer-paymentsLogo-CLu'))



