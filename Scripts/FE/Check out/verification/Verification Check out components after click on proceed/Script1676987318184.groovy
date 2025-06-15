import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.time.LocalDate as LocalDate
import java.time.format.DateTimeFormatter as DateTimeFormatter
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.main.CustomKeywordDelegatingMetaClass as CustomKeywordDelegatingMetaClass
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.apache.commons.lang3.StringUtils as StringUtils
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.ConditionType as ConditionType

WebUI.callTestCase(findTestCase('FE/Check out/verification/Verification Check out components'), [:], FailureHandling.STOP_ON_FAILURE)

//def SumOfProductsPriceInCart = CustomKeywords.'cart.cartItems.getSumOfProductsPriceInCart'()
//WebUI.verifyEqual(CustomKeywords.'cart.cartItems.getCartSubtotal'(), SumOfProductsPriceInCart) // before proceed
//CustomKeywords.'checkout.Payments.checkTheTarget'()
//checkTheTarget()
//WebUI.waitForElementClickable(findTestObject('Check Out/Proceed To Checkout Button'), 10, FailureHandling.CONTINUE_ON_FAILURE)
//WebUI.click(findTestObject('Check Out/Proceed To Checkout Button'))
CustomKeywords.'utility.Utility.clickOnObjectusingJavaScript'(findTestObject('Check Out/Proceed To Checkout Button'))

CustomKeywords.'com.utils.URLUtils.waitForURLContains'('/checkout', 10)

CustomKeywords.'generalactions.generalActions.waiteSpinnerToHide'()

if (StringUtils.indexOfIgnoreCase(WebUI.getUrl(), '/checkout/registration') > 0) {
    WebUI.callTestCase(findTestCase('FE/Sign in TC/validations/Login by phone/Success Sign in by phone For Checkout'), [:], 
        FailureHandling.STOP_ON_FAILURE)
}
CustomKeywords.'generalactions.generalActions.waiteSpinnerToHide'()
if(CustomKeywords.'com.utils.URLUtils.waitForURLContains'('/cart', 5)) {
	WebUI.callTestCase(findTestCase('FE/Cart/Validations/Modify the cart subtotals'), [:], FailureHandling.STOP_ON_FAILURE)
	CustomKeywords.'utility.Utility.clickOnObjectusingJavaScript'(findTestObject('Check Out/Proceed To Checkout Button'))
}
boolean otpRequierd = WebUI.waitForElementVisible(findTestObject('Sign up Page/Sgin up By phone/insert phone number'), 5, 
    FailureHandling.CONTINUE_ON_FAILURE) //WebUI.findWebElements(findTestObject('Sign up Page/Sgin up By phone/insert phone number'), 20).size()

if (otpRequierd) {
    boolean isPhoneExist = true

    int traials = 0

    while (isPhoneExist && (traials <= 10)) {
        WebUI.setText(findTestObject('Sign up Page/Sgin up By phone/insert phone number'), CustomKeywords.'generalactions.generalStrings.generateRandomPhoneNumber'())

        WebUI.click(findTestObject('Check Out/Update added phone number'))

        isPhoneExist = WebUI.waitForElementVisible(findTestObject('OTP/Phone exist error'), 3, FailureHandling.CONTINUE_ON_FAILURE)

        traials++
    }
    
    WebUI.callTestCase(findTestCase('FE/OTP/General Actions/Insert fixed OTP'), [:], FailureHandling.STOP_ON_FAILURE)
}

//WebUI.verifyEqual(CustomKeywords.'cart.cartItems.getCartSubtotal'(), SumOfProductsPriceInCart) // after proceed
if (WebUI.waitForElementVisible(findTestObject('Check Out/Checkout Email'), 5)) {
    def randomEmail = CustomKeywords.'generalactions.generalStrings.generatRandomEmail'()

    WebUI.setText(findTestObject('Check Out/Checkout Email'), randomEmail, FailureHandling.STOP_ON_FAILURE)

    WebUI.click(findTestObject('Check Out/Submit Email Checkout'))
}

if (WebUI.waitForElementVisible(findTestObject('Map Objs/Map Block'), 5)) {
    for (int i = 1; i <= 2; i++) {
        WebUI.click(findTestObject('Check Out/Save location Map Button'), FailureHandling.CONTINUE_ON_FAILURE)

        //		def addressMessage = CustomKeywords.'generalactions.notificationsObject.getMessageText'()
        //		if(addressMessage == 'حفظ عنوان الشحن بنجاح' || addressMessage == 'Save Shipping Address successfully') {
        //			break;
        //		}
        for (int j = 1; j <= 3; j++) {
            WebUI.doubleClick(findTestObject('Map Objs/Zoom In'))
        }
        
        WebUI.delay(2)

        WebUI.clickOffset(findTestObject('Map Objs/Map Block'), 0, 0 //		if(i == 5) {
            //			KeywordUtil.markFailedAndStop("Cannot select address");
            ) //		}
    }
}

//
////div[starts-with(@class,'styles_timePicker__')]/div[starts-with(@class,'styles_pickerContainer__')]/div[starts-with(@class,'styles_select__')][2]/p[not(starts-with(@class,'styles_disabled__'))]
////div[starts-with(@class,'styles_timePicker__')]/div[starts-with(@class,'styles_pickerContainer__')]/div[starts-with(@class,'styles_select__')][1]/p[not(starts-with(@class,'styles_disabled__'))]
//$x("//*[@id='expectedDeliveryDate']")[0].click()
//$x("//*[@class='flatpickr-day' or @class='flatpickr-day today selected']")[1].click()
//$x("//div[starts-with(@class,'styles_timePicker__')]/div[starts-with(@class,'styles_pickerContainer__')]/div[starts-with(@class,'styles_select__')][2]/p[not(starts-with(@class,'styles_disabled__'))]")[2].click()
//$x("//div[starts-with(@class,'styles_timePicker__')]/div[starts-with(@class,'styles_pickerContainer__')]/div[starts-with(@class,'styles_select__')][1]/p[not(starts-with(@class,'styles_disabled__'))]")[2].click()
TestObject dateInput = findTestObject('Check Out/PickUp Date')


if (WebUI.waitForElementVisible(dateInput, 3)) {

	// Select Date 
	//	WebUI.click(dateInput)
	WebUI.click(findTestObject('Check Out/SelectActiveDate'))
	
	//Select Time 
    WebUI.click(findTestObject('Check Out/PickUp time'))
	WebUI.click(findTestObject('Check Out/ActualHour'))
	WebUI.click(findTestObject('Check Out/ActualMinute'))
	
	// Save date & time 
	WebUI.click(findTestObject('Check Out/Save Pickup date and Time'))

	

}

if (WebUI.waitForElementVisible(findTestObject('Check Out/CheckOut Wallet'), 3)) {
    TestObject walletAmountObject = new TestObject().addProperty('xpath', ConditionType.EQUALS, '//div[@class=\'checkout-wallet\']//span')

    TestObject walletInputField = new TestObject().addProperty('xpath', ConditionType.EQUALS, '//input[@type=\'text\' and @class=\'wallet-input \']')

    // Get the text of the wallet amount element
    String walletAmountText = WebUI.getText(walletAmountObject)

    double walletAmount = Double.parseDouble(walletAmountText)

    String walletAmountString = String.format('%.2f', walletAmount // Optional: Format to 2 decimal places
        )

    if (walletAmount > 0.0) {
        boolean isWalletFieldEdittable = WebUI.getAttribute(walletInputField, 'disabled', FailureHandling.CONTINUE_ON_FAILURE)

        if (!(isWalletFieldEdittable)) {
            WebUI.click(walletInputField)

            WebUI.sendKeys(walletInputField, walletAmountString)

            WebUI.click(findTestObject('Check Out/Wallet Submit Button'), FailureHandling.CONTINUE_ON_FAILURE)
        } /*else {
        WebUI.comment('The wallet amount is zero or negative.')

        WebUI.verifyGreaterThan(walletAmount, 0, FailureHandling.STOP_ON_FAILURE)
    }*/
    }
}



WebUI.verifyElementVisible(findTestObject('Check Out/checkout user details 1'))

WebUI.verifyElementVisible(findTestObject('Check Out/Checkout address 2'))

WebUI.verifyElementVisible(findTestObject('Check Out/Checkout payment method'))

WebUI.callTestCase(findTestCase('FE/Check out/validation/Set Coupon Code'), [:], FailureHandling.STOP_ON_FAILURE)

//WebUI.click(findTestObject('Check Out/CheckOut Button'))