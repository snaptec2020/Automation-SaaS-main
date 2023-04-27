import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import javax.xml.bind.annotation.XmlElementDecl.GLOBAL as GLOBAL
import com.kms.katalon.core.annotation.Keyword as Keyword
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

WebUI.callTestCase(findTestCase('FE/Sign up TC/General Actions Sign up/Navigate to Sign up page'), [:], FailureHandling.STOP_ON_FAILURE)

TestData td = findTestData('Signup phone TD')

int rowNumber = 0

def messageColumn = 6

def phoneSize

def firstName = ''

def phoneNumber

def lastname = ''

def isCheck = '1'

for (def rowData : td.allData) {
    rowNumber = (rowNumber + 1)

    isCheck = '1'

    //CustomKeywords.'generalactions.generalStrings.generatePhoneWithConditions'(
    //	9, 0)
    phoneSize = findTestData('Signup phone TD').getValue(3, rowNumber).trim().length()

    KeywordUtil.logInfo(findTestData('Signup phone TD').getValue(2, rowNumber).length().toString())

    firstName = findTestData('Signup phone TD').getValue(1, rowNumber)

    lastname = findTestData('Signup phone TD').getValue(2, rowNumber)

    if (phoneSize == 10) {
        phoneNumber = CustomKeywords.'generalactions.generalStrings.generatePhoneWithConditions'(9, 1 //KeywordUtil.logInfo(rowData.size().toString())
            //KeywordUtil.logInfo(rowNumber.toString())
            )
    } else {
        phoneNumber = CustomKeywords.'generalactions.generalStrings.generatePhoneWithConditions'(phoneSize, 0)

        if (findTestData('Signup phone TD').getValue(3, rowNumber).trim() == 'exist') {
            phoneNumber = GlobalVariable.phoneNumber
        }
    }
    
    if (td.getValue(4, rowNumber) != 'checked') {
        isCheck = '0'
    }
    
    WebUI.callTestCase(findTestCase('FE/Sign up TC/Validations/Sign up By phone/SignUp by phone'), [('firstname') : firstName
            , ('lastname') : lastname, ('PhoneNumber') : phoneNumber, ('isCheck') : isCheck], FailureHandling.STOP_ON_FAILURE)

    /*
		 * WebUI.callTestCase(findTestCase('FE/Sign up TC/Validations/Sign up By
		 * phone/SignUp by phone'), [('firstname') : firstName , ('lastname') :
		 * lastname, ('PhoneNumber') : phoneNumber], FailureHandling.STOP_ON_FAILURE)
		 */
    if (td.getValue(5, rowNumber) != 'ButtonDisabled') {
        if (GlobalVariable.languageMode == 'en') {
            messageColumn = 7
        }
        
        CustomKeywords.'signup.signupPhoneVerifications.phoneVerificationMessage'(findTestData('Signup phone TD').getValue(
                messageColumn, rowNumber) //WebUI.delay(3)
            )
    } else {
        CustomKeywords.'signup.signupPhoneVerifications.verificationElementPhoneSignUp'()
    }
}

