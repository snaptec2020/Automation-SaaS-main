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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable

import org.apache.commons.lang3.StringUtils
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('FE/Check out/verification/Verification Check out components'), [:], FailureHandling.STOP_ON_FAILURE)

//def SumOfProductsPriceInCart = CustomKeywords.'cart.cartItems.getSumOfProductsPriceInCart'()
//WebUI.verifyEqual(CustomKeywords.'cart.cartItems.getCartSubtotal'(), SumOfProductsPriceInCart) // before proceed
//CustomKeywords.'checkout.Payments.checkTheTarget'()
//checkTheTarget()
WebUI.click(findTestObject('Check Out/Proceed To Checkout Button'))
//boolean otpRequierd=WebUI.verifyElementVisible(findTestObject('OTP/OTP container'))
//def currentUrl = WebUI.getUrl()
//def isSpennerVisable= WebUI.waitForElementVisible(findTestObject('Spinner'), 10, FailureHandling.CONTINUE_ON_FAILURE)
//while(isSpennerVisable) {
//	isSpennerVisable= WebUI.waitForElementVisible(findTestObject('Spinner'), 10, FailureHandling.CONTINUE_ON_FAILURE)
//}
CustomKeywords.'generalactions.generalActions.waiteSpinnerToHide'()
if(StringUtils.indexOfIgnoreCase(WebUI.getUrl(), "/checkout/registration") >0) {
	WebUI.callTestCase(findTestCase('FE/Sign in TC/validations/Login by phone/Success Sign in by phone For Checkout'), [:], FailureHandling.STOP_ON_FAILURE)
}
int otpRequierd = WebUI.findWebElements(findTestObject('Sign up Page/Sgin up By phone/insert phone number'), 20).size()

if (otpRequierd != 0) {
	boolean isPhoneExist = true
	int traials = 0
	while(isPhoneExist && traials<=10) {
    WebUI.setText(findTestObject('Sign up Page/Sgin up By phone/insert phone number'), CustomKeywords.'generalactions.generalStrings.generateRandomPhoneNumber'())

    WebUI.click(findTestObject('Check Out/Update added phone number'))
	isPhoneExist = WebUI.waitForElementVisible(findTestObject('OTP/Phone exist error'), 3, FailureHandling.CONTINUE_ON_FAILURE)
	traials++
	}

    WebUI.callTestCase(findTestCase('FE/OTP/General Actions/Insert fixed OTP'), [:], FailureHandling.STOP_ON_FAILURE)
}

//WebUI.verifyEqual(CustomKeywords.'cart.cartItems.getCartSubtotal'(), SumOfProductsPriceInCart) // after proceed
WebUI.verifyElementVisible(findTestObject('Check Out/checkout user details 1'))

WebUI.verifyElementVisible(findTestObject('Check Out/Checkout address 2'))

WebUI.verifyElementVisible(findTestObject('Check Out/Checkout payment method'))


//def checkTheTarget() {
//    double minimum = 1000
//
//    double maximum = 2500
//
//    double Total = (((WebUI.getText(findTestObject('Object Repository/Cart/Cart Subtotal (Inc VAT)')).replaceAll(',', '') =~ '\\d+\\.\\d+')[0]) as double)
//
//    if ((minimum <= Total) & (Total <= maximum)) {
//        WebUI.click(findTestObject('Check Out/Proceed To Checkout Button'))
//    } else if ((minimum > Total) && (maximum > Total)) {
//        WebUI.click(findTestObject('Object Repository/Check Out/remove product'))
//		
//		CustomKeywords.'products.productsFromCatalog.getRandominStockProductsFromRandomCategory'()
//    }
//}

