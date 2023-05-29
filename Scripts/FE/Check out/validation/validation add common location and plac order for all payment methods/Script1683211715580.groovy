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

def currentUrl=WebUI.getUrl()
WebUI.callTestCase(findTestCase('FE/Check out/verification/Verification Check out components after click on proceed'), [:], 
    FailureHandling.STOP_ON_FAILURE)
List PaymentMethods =CustomKeywords.'checkout.Payments.getPaymentMethodsList'()
for(int i=1;i<=PaymentMethods.size();i++) {
if(i!=1) {	
WebUI.callTestCase(findTestCase('FE/Check out/verification/Verification Check out components after click on proceed'), [:],
		FailureHandling.STOP_ON_FAILURE)
}
WebUI.callTestCase(findTestCase('FE/Check out/validation/Select location common case'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'checkout.Payments.paymentMethodToPayBySelectedMethod'(i)
//WebUI.takeFullPageScreenshot('./paymentResult.png')

//WebUI.navigateToUrl(currentUrl)
}