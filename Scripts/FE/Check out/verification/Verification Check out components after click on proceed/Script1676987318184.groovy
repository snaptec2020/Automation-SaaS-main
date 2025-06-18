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
import com.utils.CustomLogger

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

boolean otpRequierd = WebUI.waitForElementVisible(findTestObject('Sign up Page/Sgin up By phone/insert phone number'), 5, 
    FailureHandling.OPTIONAL) //WebUI.findWebElements(findTestObject('Sign up Page/Sgin up By phone/insert phone number'), 20).size()

if (otpRequierd) {
    boolean isPhoneExist = true

    int traials = 0

    while (isPhoneExist && (traials <= 10)) {
        WebUI.setText(findTestObject('Sign up Page/Sgin up By phone/insert phone number'), CustomKeywords.'generalactions.generalStrings.generateRandomPhoneNumber'())

        WebUI.click(findTestObject('Check Out/Update added phone number'))

        isPhoneExist = WebUI.waitForElementVisible(findTestObject('OTP/Phone exist error'), 3, FailureHandling.OPTIONAL)

        traials++
    }
    
    WebUI.callTestCase(findTestCase('FE/OTP/General Actions/Insert fixed OTP'), [:], FailureHandling.STOP_ON_FAILURE)
}
CustomKeywords.'generalactions.generalActions.waiteSpinnerToHide'()
if(CustomKeywords.'com.utils.URLUtils.waitForURLContains'('/cart', 5)) {
	WebUI.callTestCase(findTestCase('FE/Cart/Validations/Modify the cart subtotals'), [:], FailureHandling.STOP_ON_FAILURE)
	CustomKeywords.'utility.Utility.clickOnObjectusingJavaScript'(findTestObject('Check Out/Proceed To Checkout Button'))
	CustomKeywords.'generalactions.generalActions.waiteSpinnerToHide'()
}
//WebUI.verifyEqual(CustomKeywords.'cart.cartItems.getCartSubtotal'(), SumOfProductsPriceInCart) // after proceed
if (WebUI.waitForElementVisible(findTestObject('Check Out/Checkout Email'), 2)) {
    def randomEmail = CustomKeywords.'generalactions.generalStrings.generatRandomEmail'()

    WebUI.setText(findTestObject('Check Out/Checkout Email'), randomEmail, FailureHandling.STOP_ON_FAILURE)

    WebUI.click(findTestObject('Check Out/Submit Email Checkout'))
}

if (WebUI.waitForElementVisible(findTestObject('Map Objs/Map Block'), 2)) {
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

// Set Delivery Date & time
setDeliveryDate()

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

WebUI.callTestCase(findTestCase('FE/Check out/validation/Set Coupon Code'), [:], FailureHandling.OPTIONAL)


// This method for set Delivery date & Time
def setDeliveryDate() {
	TestObject dateInput = findTestObject('Check Out/PickUp Date')
	
	if (WebUI.waitForElementVisible(dateInput, 3)) {
	
		// Click on date input to open date picker
		WebUI.click(dateInput)
		
		// Select first available (non-disabled) date
		WebUI.click(findTestObject('Check Out/SelectActiveDate'))
		
		// Click on time picker
		WebUI.click(findTestObject('Check Out/PickUp time'))
		
		// Get count of available hours
		TestObject availableHours = new TestObject('availableHours')
		availableHours.addProperty('xpath', ConditionType.EQUALS,
			"//div[starts-with(@class,'styles_timePicker__')]/div[starts-with(@class,'styles_pickerContainer__')]/div[starts-with(@class,'styles_select__')][2]/p[not(starts-with(@class,'styles_disabled__'))]")
		
		// Wait for hour elements to be present
		WebUI.waitForElementPresent(availableHours, 5)
		List<WebElement> hourElements = WebUI.findWebElements(availableHours, 5)
		int availableHourCount = hourElements.size()
		// Retry mechanism for time selection
		int maxMinuteRetries = 3
		int currentHourIndex = 1
		int maxRetries = 5
		boolean saveSuccessful = false
		
		// Try up to 3 different hours
		int hourAttempts = Math.min(availableHourCount, maxRetries)
		while (hourAttempts && !saveSuccessful ) {
			try {
				// Use parameterized TestObject for hour selection
				TestObject hourSelector = findTestObject('Check Out/ActualHour', ['index': (currentHourIndex)])
				WebUI.click(hourSelector)
				
				// Get available minutes for this hour
				TestObject availableMinutes = new TestObject('availableMinutes')
				availableMinutes.addProperty('xpath', ConditionType.EQUALS,
					"//div[starts-with(@class,'styles_timePicker__')]/div[starts-with(@class,'styles_pickerContainer__')]/div[starts-with(@class,'styles_select__')][1]/p[not(starts-with(@class,'styles_disabled__'))]")
				
				WebUI.waitForElementPresent(availableMinutes, 3)
				List<WebElement> minuteElements = WebUI.findWebElements(availableMinutes, 3)
				int availableMinuteCount = minuteElements.size()
				
				// Try up to 3 different minutes for this hour
				int minuteAttempts = Math.min(maxMinuteRetries, availableMinuteCount)
				
				for (int minuteRetry = 1; minuteRetry <= minuteAttempts && !saveSuccessful; minuteRetry++) {
					try {
						// Use parameterized TestObject for minute selection
						TestObject minuteSelector = findTestObject('Check Out/ActualMinute', ['index': minuteRetry])
						WebUI.click(minuteSelector)
						
						// Save date & time
						WebUI.click(findTestObject('Check Out/Save Pickup date and Time'))
						CustomKeywords.'generalactions.generalActions.waiteSpinnerToHide'()
						
						// Check if save button is hidden (save was successful)
						if (WebUI.verifyElementNotPresent(findTestObject('Check Out/Save Pickup date and Time'), 2, FailureHandling.OPTIONAL)) {
							saveSuccessful = true
							CustomLogger.logPassed("  ✓ SUCCESS: Saved with hour " + currentHourIndex + "")
							break
						} else {
							CustomLogger.logPassed("  ✗ Save failed for hour " + currentHourIndex + ", minute " + minuteRetry)
							// If not the last minute attempt, re-select the hour before trying next minute
								if (minuteRetry < minuteAttempts) {
									WebUI.click(findTestObject('Check Out/PickUp time'))
									WebUI.click(hourSelector) // Re-select the same hour
									WebUI.delay(0.5) // Wait for minutes to refresh
								}
						}
					}catch (Exception e) {
                    CustomLogger.logError("  Error with minute " + minuteRetry + " for hour " + currentHourIndex + ": " + e.getMessage())
                }
				}
				} catch (Exception e) {
				CustomLogger.logError("Error with hour " + currentHourIndex + ": " + e.getMessage())
			}
			// Move to next hour if current hour's minutes all failed
			if (!saveSuccessful) {
				currentHourIndex++
				WebUI.click(findTestObject('Check Out/PickUp time'))
				CustomLogger.logPassed("Moving to next hour...")
			}
		}
		
		// Final verification
		if (!saveSuccessful) {
			CustomLogger.logFailed("Failed to save date and time after trying " + availableHourCount + " hours with up to " + maxMinuteRetries + " minutes each")
		}
	}
}
//WebUI.click(findTestObject('Check Out/CheckOut Button'))