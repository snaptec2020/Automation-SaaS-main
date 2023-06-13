import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.util.logging.Logger as Logger
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
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

KeywordLogger logger = KeywordLogger.getInstance(getClass())

def reuiredFieldError = '/following::span[starts-with(@class,\'styles_errorText__\')][1]'

def errorMessage = findTestObject('Object Repository/BE/Sign In/Fail login popup').findPropertyValue('xpath') + '/span[starts-with(@class,\'styles_message__\')]'

def closeErrorPopup = findTestObject('Object Repository/BE/Sign In/Fail login popup').findPropertyValue('xpath') + '//button/*[text()=\'Close\']'

for (int i = 1; i <= 3; i++) {
    switch (i) {
        case 1:
            logger.logInfo('Checking Required Fields')

            WebUI.callTestCase(findTestCase('BE/Sign In/General Action/Sign In General Steps'), [('userName') : Keys.chord(
                        Keys.BACK_SPACE), ('password') : Keys.chord(Keys.BACK_SPACE)], FailureHandling.CONTINUE_ON_FAILURE)

            WebUI.verifyElementVisible(CustomKeywords.'utility.Utility.addXpathToTestObject'(findTestObject('Object Repository/BE/Sign In/User Name').findPropertyValue(
                        'xpath') + reuiredFieldError), FailureHandling.CONTINUE_ON_FAILURE)

            WebUI.verifyElementVisible(CustomKeywords.'utility.Utility.addXpathToTestObject'(findTestObject('Object Repository/BE/Sign In/Password').findPropertyValue(
                        'xpath') + reuiredFieldError), FailureHandling.CONTINUE_ON_FAILURE)

            break
        case 2:
            logger.logInfo('Checking Wrong Password')

            WebUI.callTestCase(findTestCase('BE/Sign In/General Action/Sign In General Steps'), [('userName') : GlobalVariable.MIDUserName
                    , ('password') : 'hjfdknghfdgnnbbvhfdhvnmn'], FailureHandling.CONTINUE_ON_FAILURE)

            WebUI.verifyEqual(WebUI.getText(CustomKeywords.'utility.Utility.addXpathToTestObject'(errorMessage), FailureHandling.CONTINUE_ON_FAILURE), 
                'The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.')

            WebUI.click(CustomKeywords.'utility.Utility.addXpathToTestObject'(closeErrorPopup), FailureHandling.CONTINUE_ON_FAILURE)

            break
        case 3:
            logger.logInfo('Checking Wrong username and Password')

            WebUI.callTestCase(findTestCase('BE/Sign In/General Action/Sign In General Steps'), [('userName') : 'hjfdknghfdgnnbbvhfdhvnmn'
                    , ('password') : 'hjfdknghfdgnnbbvhfdhvnmn'], FailureHandling.CONTINUE_ON_FAILURE)

            WebUI.verifyEqual(WebUI.getText(CustomKeywords.'utility.Utility.addXpathToTestObject'(errorMessage), FailureHandling.CONTINUE_ON_FAILURE), 
                'This account is not found on the system.')

            WebUI.click(CustomKeywords.'utility.Utility.addXpathToTestObject'(closeErrorPopup), FailureHandling.CONTINUE_ON_FAILURE)

            break
    }
}