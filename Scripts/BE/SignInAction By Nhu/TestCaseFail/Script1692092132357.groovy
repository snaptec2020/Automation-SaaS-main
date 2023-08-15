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

for (int i = 0; i <= 3; i++) {
    switch (i) {
        case 1://check username and password is empty
           WebUI.callTestCase(findTestCase('BE/SignInAction By Nhu/TestCaseSignIn'), [('Username') : '', ('Password') : ''], FailureHandling.CONTINUE_ON_FAILURE)
		   WebUI.verifyElementVisible(findTestObject('Object Repository/Page_Middle Dashboard/RequiredUsername'), FailureHandling.CONTINUE_ON_FAILURE)
		   WebUI.verifyElementVisible(findTestObject('Object Repository/Page_Middle Dashboard/RequiredPassword'), FailureHandling.CONTINUE_ON_FAILURE)
            break
        case 2://check wrong password
             WebUI.callTestCase(findTestCase('BE/SignInAction By Nhu/TestCaseSignIn'), [('Username') :  GlobalVariable.Username , ('Password') : 'hfghfghfgjhj'], FailureHandling.CONTINUE_ON_FAILURE)
			 checkMessage('Object Repository/Page_Middle Dashboard/ErrorPassword', 'The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.')
       		// WebUI.verifyEqual(WebUI.getText(findTestObject('Object Repository/Page_Middle Dashboard/ErrorPassword')), 'The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.', FailureHandling.CONTINUE_ON_FAILURE)
			// WebUI.click(findTestObject('Object Repository/Page_Middle Dashboard/ClosePopup'))
            break
        case 3://check wrong username and password
		WebUI.callTestCase(findTestCase('BE/SignInAction By Nhu/TestCaseSignIn'), [('Username') : 'test' , ('Password') : 'test'], FailureHandling.CONTINUE_ON_FAILURE)
		//checkMessage('Object Repository/Page_Middle Dashboard/ErrorPassword', 'The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.')
            break
    }
}

def checkMessage(def testObjectPath, def messageText) {
	WebUI.verifyEqual(WebUI.getText(findTestObject(testObjectPath)), messageText, FailureHandling.CONTINUE_ON_FAILURE)
	WebUI.click(findTestObject('Object Repository/Page_Middle Dashboard/ClosePopup'))
}