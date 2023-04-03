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

/*
 * WebUI.callTestCase(findTestCase('FE/Sign up TC/General Actions Sign
 * up/Navigate to Sign up page'), [:], FailureHandling.STOP_ON_FAILURE)
 * 
 * WebUI.setText(findTestObject('Sign up Page/Sign up By email/First Name
 * field'), 'Abedalziz')
 * 
 * WebUI.setText(findTestObject('Sign up Page/Sign up By email/Last Name
 * field'), 'saleh')
 * 
 * WebUI.setText(findTestObject('Sign up Page/Sign up By email/Email field'),
 * 'AAA@AAa.com')
 * 
 * WebUI.setEncryptedText(findTestObject('Sign up Page/Sign up By email/Password
 * feild'), 'CxznB8Z9LyBr+LAX0Ab/lQ==')
 * 
 * WebUI.click(findTestObject('Sign up Page/Sign up By email/Services and
 * Privacy Policy Check box'))
 * 
 * WebUI.click(findTestObject('Sign up Page/Sign up By email/Sign Up Button'))
 */
TestData td = findTestData('Signup TD')
int rowNumber=0;
def messageColumn=5
def emailForCheck
for(def rowData:td.allData) {
	rowNumber=rowNumber+1
	if(rowNumber==2) {
		emailForCheck=GlobalVariable.Vaild_email
	}
	else {
		emailForCheck=findTestData('Signup TD').getValue(3, rowNumber)
	}
WebUI.callTestCase(findTestCase('FE/Sign up TC/Validations/Sgin up By email/SignUp by Email'), [('firstName') : findTestData(
            'Signup TD').getValue(1, rowNumber), ('lastName') : findTestData('Signup TD').getValue(2, rowNumber), ('email') : emailForCheck, ('password') : findTestData('Signup TD').getValue(4, rowNumber)], FailureHandling.STOP_ON_FAILURE)

if (td.getValue(7, rowNumber) != 'ButtonDisabled') {
	if (GlobalVariable.languageMode=='en') {
		messageColumn=6
		
	}
    CustomKeywords.'signup.Signupemailverifications.verificationMessage'(td.getValue(messageColumn, rowNumber),td.getValue(messageColumn, rowNumber))
}
 else {
	CustomKeywords.'signup.Signupemailverifications.verificationElementSignUp'()
}
}


