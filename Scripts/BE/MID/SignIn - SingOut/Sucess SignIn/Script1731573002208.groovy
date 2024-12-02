import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
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

////KeywordUtil.logInfo(GlobalVariable.MIDUserName)
//TestData td = findTestData('null')
//def userName
//def userPassword
//for(int i=1;i<=td.allData.size();i++) {
//	if(td.getValue(1, i) == RunConfiguration.executionProfile) {
//		userName=td.getValue(2, i)
//		userPassword = td.getValue(3, i)
//		break
//	}
//}
def credentials = CustomKeywords.'mid.framework.UserCredentialKeywords.getUserCredentials'()

// Access the username and password from the returned Map
String userName = credentials.username

String userPassword = credentials.password



WebUI.callTestCase(findTestCase('BE/MID/General Test Cases/Sign In General Steps'), [('userName') : userName, ('password') : userPassword], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('Object Repository/BE/MID/Landing Page/Logo'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('Object Repository/BE/MID/Landing Page/Notifications Icon'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('Object Repository/BE/MID/Landing Page/Account Info Button'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyNotEqual(CustomKeywords.'utility.Utility.checkIfElementExist'('Object Repository/BE/MID/Landing Page/Side Bar contents'), 
    '0', FailureHandling.CONTINUE_ON_FAILURE)




