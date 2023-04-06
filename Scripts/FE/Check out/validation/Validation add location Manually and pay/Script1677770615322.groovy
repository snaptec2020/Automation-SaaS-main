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

WebUI.callTestCase(findTestCase('FE/Check out/verification/Verification Check out components after click on proceed'), [:], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Check Out/Add manually address Button'))

WebUI.click(findTestObject('Check Out/Add Country icon Button'))

WebUI.click(findTestObject('Check Out/Add First option from list'))

WebUI.click(findTestObject('Check Out/Add City icon button'))

WebUI.click(findTestObject('Check Out/Add First option from list'))

if (WebUI.verifyElementVisible(findTestObject('Object Repository/Check Out/Add Distric icon Button'), FailureHandling.CONTINUE_ON_FAILURE)) {
    WebUI.click(findTestObject('Object Repository/Check Out/Add First option from list'))
} else {
    WebUI.sendKeys(findTestObject('Object Repository/Check Out/Distric Feild'), 'Test')
}

WebUI.sendKeys(findTestObject('Check Out/Street Address Field'), 'Test2')

WebUI.sendKeys(findTestObject('Check Out/Zip Code Field'), 'Test3')

CustomKeywords.'checkout.Payments.getRandomPaymentMethods'()

WebUI.takeFullPageScreenshot('./paymentResult.png')

WebUI.acceptAlert()

