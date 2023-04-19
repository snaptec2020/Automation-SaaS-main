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

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Shared/Logo'), 10)



//CustomKeywords.'customUtils.Util.getElementByXPath'("//button[contains(@class,'storeSwitcher-trigger-')]")
WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Shared/Language'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Shared/Logo'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Shared/Cart'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Shared/Fav'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Shared/Login'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Shared/Search'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Shared/Web header'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementText(findTestObject('Object Repository/Helpdesk/AjStore/Page_/a_'), 'رجالي')

WebUI.verifyElementText(findTestObject('Object Repository/Helpdesk/AjStore/Page_/a__1'), 'شبابي')

WebUI.verifyElementText(findTestObject('Object Repository/Helpdesk/AjStore/Page_/a__1_2'), 'أطفال')

WebUI.verifyElementText(findTestObject('Object Repository/Helpdesk/AjStore/Page_/a__1_2_3'), 'نسائي')

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Shared/الأشمغه'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Shared/الغتر'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Shared/الأثواب'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementClickable(findTestObject('Object Repository/Helpdesk/AjStore/Shared/الملابس الداخلية'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Shared/click رجالي'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Shared/click شبابي'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Shared/click أطفال'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Shared/click نسائي'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Shared/click رجالي'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Shared/click الأشمغه'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Shared/click الغتر'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Shared/click الأثواب'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Shared/click الملابس الداخليه'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Shared/الأشمغة العالمية'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Shared/المنتجات الشتوية'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Shared/Click Logo'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Shared/Main root'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Shared/منتجات الموسم'), 10)

WebUI.verifyElementVisible(findTestObject('Helpdesk/AjStore/Shared/منتجات الموسم'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Shared/الاكثر مبيعاً'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Shared/المنتجات الجديده'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Shared/اخترنا لك'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('Helpdesk/AjStore/Shared/منتجات الموسم'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Shared/Click الاكثر مبيعاً'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Shared/click المنتجات الجديدة'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/Shared/clickاخترنا لك'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Shared/تصفح العروض'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Shared/Subscribe in footer'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Shared/Footer'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/AjStore/Shared/Footer payment Logo'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.closeBrowser()

