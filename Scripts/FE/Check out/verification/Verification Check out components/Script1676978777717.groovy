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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

//if (GlobalVariable.testSuiteStatus == 'NotRun') {
//    WebUI.callTestCase(findTestCase('FE/Sign up TC/Validations/Sgin up By email/Success Sign up By email'), [:], FailureHandling.STOP_ON_FAILURE)
//}
WebUI.callTestCase(findTestCase('FE/Cart/General Actions/View Cart'), [:], FailureHandling.OPTIONAL)

WebUI.callTestCase(findTestCase('FE/Cart/Validations/Modify the cart subtotals'), [:], FailureHandling.OPTIONAL)

WebUI.callTestCase(findTestCase('FE/Cart/General Actions/View Cart'), [:], FailureHandling.OPTIONAL)

WebUI.callTestCase(findTestCase('FE/Check out/validation/Set Coupon Code'), [:], FailureHandling.OPTIONAL)

WebUI.verifyElementVisible(findTestObject('Check Out/Cart Calculation'))

WebUI.verifyElementVisible(findTestObject('Check Out/Proceed To Checkout Button'))

WebUI.verifyElementVisible(findTestObject('Check Out/Shopping Cart head'))



