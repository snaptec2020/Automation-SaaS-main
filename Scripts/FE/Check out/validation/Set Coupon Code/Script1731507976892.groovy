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

int timeout = 2

String couponCode= GlobalVariable.couponCode
//String expectedDiscountLabelText=GlobalVariable.labelValue
//String actualDiscountLabelText=WebUI.getText(findTestObject('Check Out/Discount Code/Dicount label'))

TestObject discountCodeField= findTestObject('Check Out/Discount Code/Discount code field')
//TestObject discountLabelObj = findTestObject('Check Out/Discount Code/Dicount label', [('discountLabel') : GlobalVariable.discountLabel])




if (couponCode == null || couponCode.isEmpty()) {
	return 
}
if (WebUI.waitForElementVisible(findTestObject('Check Out/Discount container'), timeout)) {
    //set data in discount field
    // Define a timeout for visibility checks
    if (WebUI.waitForElementVisible(discountCodeField, timeout)) {
        boolean isDiscountFieldEditable = WebUI.getAttribute(discountCodeField, 
            'disabled', FailureHandling.OPTIONAL)

        if (!(isDiscountFieldEditable)) {
            applyOrRemoveDiscount()
        } // Verify the discount code field is visible after clicking the arrow
        else {
            applyOrRemoveDiscount(true)
        }
        //WebUI.comment('Discount code field is not visible even after clicking the discount arrow.')
    } else {
        WebUI.click(findTestObject('Check Out/Discount Code/discount arrow'))

        if (WebUI.waitForElementVisible(discountCodeField, timeout)) {
            boolean isDiscountFieldEditable = WebUI.getAttribute(discountCodeField, 
                'disabled', FailureHandling.OPTIONAL)

            if (!(isDiscountFieldEditable)) {
                applyOrRemoveDiscount()
				//checkDiscountLableValue(actualDiscountLabelText , expectedDiscountLabelText)
				
            } else {
                applyOrRemoveDiscount(true)
            }
        } else {
            KeywordUtil.markFailed('Discount code field is not visible even after clicking the discount arrow.')
        }
    }
}

def applyOrRemoveDiscount(boolean isRemoved = false) {
    if (isRemoved) {
		CustomKeywords.'utility.Utility.clickElementSafely'(findTestObject('Check Out/Discount Code/Apply Button discount'))
        //WebUI.click(findTestObject('Check Out/Discount Code/Apply Button discount'))
    }
    
    WebUI.setText(findTestObject('Check Out/Discount Code/Discount code field'), couponCode)
	CustomKeywords.'utility.Utility.clickElementSafely'(findTestObject('Check Out/Discount Code/Apply Button discount'))
    //WebUI.click(findTestObject('Check Out/Discount Code/Apply Button discount'))

    checkDiscountLableValue()
}

//	if (actualDiscountLabelText == expectedDiscountLabelText) {
//		WebUI.comment("The text of the element matches the global variable: " + expectedDiscountLabelText)
//	} else {
//		WebUI.comment("The text of the element does NOT match the global variable. Expected: " + expectedDiscountLabelText + ", but got: " + actualDiscountLabelText)
//	}

def checkDiscountLableValue() {
    WebUI.verifyElementVisible(findTestObject('Check Out/Discount Code/Dicount label'), FailureHandling.OPTIONAL)

    def discountAmountObj = findTestObject('Check Out/Discount Code/Dicount label').findPropertyValue('xpath') + '/following-sibling::div/span/span[1]' + '| ' + findTestObject('Check Out/Discount Code/Dicount label').findPropertyValue('xpath') + '/following-sibling::span/span/span[1]'

    TestObject tb = CustomKeywords.'utility.Utility.addXpathToTestObject'(discountAmountObj)

    if (WebUI.waitForElementVisible(tb, 2,FailureHandling.OPTIONAL)) {
        String discountAmountText = WebUI.getText(tb).replaceAll(',', '').replaceAll(' ', '')

        float discountAmount

        if (!(discountAmountText.isEmpty())) {
            discountAmount = Float.parseFloat(discountAmountText)

            WebUI.verifyNotEqual(discountAmount, 0.0, FailureHandling.OPTIONAL)
        }
    }
}
