import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.sql.Driver as Driver
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.sun.java.util.jar.pack.Instruction.Switch as Switch
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.remote.server.handler.FindChildElements as FindChildElements
import org.openqa.selenium.remote.server.handler.FindElement as Keys
import org.openqa.selenium.remote.server.handler.FindElements as FindElements

/*
 * WebUI.callTestCase(findTestCase('BE/SignInAction By Nhu/TestCaseSuccess'),
 * [('Username') : GlobalVariable.Username, ('Password') :
 * GlobalVariable.Password], FailureHandling.STOP_ON_FAILURE)
 */
WebUI.callTestCase(findTestCase('BE/Sign In/Validation/Sucess SignIn'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/Page_Middle Dashboard/btn_Sales'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Page_Middle Dashboard/btn_Orders'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Page_Middle Dashboard/btn_MoreFilter'), FailureHandling.CONTINUE_ON_FAILURE)

for (i = 0; i <= 3; i++) {
    switch (i) {
        case 1: // truong hop co ket qua rong
            for (int j = 0; j <= 3; j++) {
                checkVerify(xpath[j], value1[j])
            }
            
            WebUI.click(findTestObject('Object Repository/Page_Middle Dashboard/btn_Search'), FailureHandling.CONTINUE_ON_FAILURE)

            WebUI.verifyEqual(WebUI.getText(findTestObject('Object Repository/Page_Middle Dashboard/text_ResultSearchEmpty')), 
                'There’re no available data to view', FailureHandling.CONTINUE_ON_FAILURE)

            break
        case 2: // truong hop ton tai ket qua khac rong
            for (int j = 0; j <= 3; j++) {
                checkVerify(xpath[j], value[j])
            }
            
            WebUI.click(findTestObject('Object Repository/Page_Middle Dashboard/btn_Search'), FailureHandling.CONTINUE_ON_FAILURE)

            WebUI.verifyEqual(WebUI.getText(findTestObject('Object Repository/Page_Middle Dashboard/search_Success')), '15000000405', 
                FailureHandling.CONTINUE_ON_FAILURE)

            break //		case 3:// search sai
            //		for (int j = 0; j <=3; j++) {
            //			checkVerify(xpath[j],value3[j])
            //			
            //		}
            //		break
    }
}



def checkVerify(def xpath, def value) {
    WebUI.verifyElementVisible(findTestObject(xpath))

    if (xpath.contains('input')) {
        WebUI.setText(findTestObject(xpath), value)
    }
    
    if (xpath.contains('dropdown')) {
        WebUI.verifyElementVisible(findTestObject(xpath))

        WebUI.click(findTestObject(xpath))

        WebUI.verifyElementVisible(findTestObject(value), FailureHandling.CONTINUE_ON_FAILURE)

        WebUI.click(findTestObject(value), FailureHandling.CONTINUE_ON_FAILURE)
    }
}

