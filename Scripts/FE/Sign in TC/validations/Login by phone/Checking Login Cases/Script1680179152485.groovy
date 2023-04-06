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
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('FE/Sign in TC/validations/General Actions/Navigate to Sgin in'), [:], FailureHandling.STOP_ON_FAILURE)

for (def i = 1; i <= 3; i++) {
    switch (i) {
        case 3:
            WebUI.setText(findTestObject('Sign up Page/Sgin up By phone/insert phone number'), CustomKeywords.'generalactions.generalStrings.generatePhoneWithConditions'(
                    9, 0))

            WebUI.waitForElementClickable(findTestObject('Object Repository/Sign up Page/Sgin up By phone/Submit Button Sign up mobile page'), 
                0)

            WebUI.click(findTestObject('Object Repository/Sign up Page/Sgin up By phone/Submit Button Sign up mobile page'))

            WebUI.verifyElementVisible(findTestObject('Sign up Page/Sign up By email/First Name field'), FailureHandling.CONTINUE_ON_FAILURE)

            WebUI.verifyElementVisible(findTestObject('Sign up Page/Sgin up By phone/Last name sign up Mobile'), FailureHandling.CONTINUE_ON_FAILURE)

            WebUI.delay(5)

            break
        case 2:
            WebUI.setText(findTestObject('Sign up Page/Sgin up By phone/insert phone number'), CustomKeywords.'generalactions.generalStrings.generatePhoneWithConditions'(
                    8, 0))

            WebUI.verifyElementNotClickable(findTestObject('Object Repository/Sign up Page/Sgin up By phone/Submit Button Sign up mobile page'), 
                FailureHandling.CONTINUE_ON_FAILURE)
			WebUI.delay(5)
            break
        case 1:
            WebUI.setText(findTestObject('Sign up Page/Sgin up By phone/insert phone number'), CustomKeywords.'generalactions.generalStrings.generatePhoneWithConditions'(
                    9, 1))

            WebUI.verifyElementNotClickable(findTestObject('Object Repository/Sign up Page/Sgin up By phone/Submit Button Sign up mobile page'), 
                FailureHandling.CONTINUE_ON_FAILURE)
			WebUI.delay(5)
            break
    }
}

