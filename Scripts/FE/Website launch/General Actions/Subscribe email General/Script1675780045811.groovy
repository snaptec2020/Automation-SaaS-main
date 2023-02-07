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

//WebUI.callTestCase(findTestCase('FE/Website launch/Validations/Website launch'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.waitForPageLoad(0)

'Verify footer block visible'
if (WebUI.verifyElementVisible(findTestObject('Headers and Footers/Footer contents/Web footer'), FailureHandling.CONTINUE_ON_FAILURE)) {
    'Verify subscribe block visible'
    for (int j = 1; j <= 3; j++) {
        for (int i; i <= 7; i++) {
            WebUI.scrollToElement(findTestObject('Headers and Footers/Footer contents/Web footer'), 0, FailureHandling.CONTINUE_ON_FAILURE)
        }
        
        if (!(WebUI.verifyElementVisible(findTestObject('Subscribe/Input Subcribe on touch'), FailureHandling.CONTINUE_ON_FAILURE))) {
            //KeywordUtil.markPassed('This object not find')
            continue
        } else {
            WebUI.verifyElementVisible(findTestObject('Headers and Footers/Header contents/Lets stay in touch field'), FailureHandling.STOP_ON_FAILURE)

            if (subscribeMode == 'Footer') {
                WebUI.setText(findTestObject('Subscribe/Subcribe Footer field'), subscribeEmail)

                WebUI.click(findTestObject('Subscribe/Subscribe Footer Button'))
            } else {
                WebUI.setText(findTestObject('Headers and Footers/Header contents/Lets stay in touch field'), subscribeEmail)

                WebUI.click(findTestObject('Subscribe/Subcribe Button on touch'))
            }
            
            break
        }
    }
}

