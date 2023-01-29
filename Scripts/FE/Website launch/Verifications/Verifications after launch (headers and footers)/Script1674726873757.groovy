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

//We want to add all check after Launch here like Header, footer and header contents (signin, favorit, logo)
//WebUI.callTestCase(findTestCase('FE/Website launch/Validations/Website launch'), [:], FailureHandling.STOP_ON_FAILURE)

'Verfy header visible'
if(WebUI.verifyElementVisible(findTestObject('Headers and Footers/Header contents/Web Header'), FailureHandling.CONTINUE_ON_FAILURE)) {
	'Verify the tob bar visible'
	WebUI.verifyElementVisible(findTestObject('Headers and Footers/Header contents/Top bar'), FailureHandling.CONTINUE_ON_FAILURE)
	
	'Verify Language bar visible'
	WebUI.verifyElementVisible(findTestObject('Headers and Footers/Header contents/Language'), FailureHandling.CONTINUE_ON_FAILURE)
	
	'Verfy Logo visible'
	WebUI.verifyElementVisible(findTestObject('Headers and Footers/Header contents/Logo'), FailureHandling.CONTINUE_ON_FAILURE)
	
	'Verfy login part visible'
	WebUI.verifyElementVisible(findTestObject('Headers and Footers/Header contents/Login'), FailureHandling.CONTINUE_ON_FAILURE)
	
	'Verfy wish list part visible'
	WebUI.verifyElementVisible(findTestObject('Headers and Footers/Header contents/Fav'), FailureHandling.CONTINUE_ON_FAILURE)
	
	'Verfy search bar visible'
	WebUI.verifyElementVisible(findTestObject('Headers and Footers/Header contents/Search'), FailureHandling.CONTINUE_ON_FAILURE)
	
	'Verify Cart visible'
	WebUI.verifyElementVisible(findTestObject('Headers and Footers/Header contents/Cart'), FailureHandling.CONTINUE_ON_FAILURE)
}

'Verify mega menu visible'
WebUI.verifyElementVisible(findTestObject('Mega Menu/Mega menu'), FailureHandling.CONTINUE_ON_FAILURE)

'Verify footer block visible'
if(WebUI.verifyElementVisible(findTestObject('Headers and Footers/Footer contents/Web footer'), FailureHandling.CONTINUE_ON_FAILURE)) {
	'Verify subscribe block visible'
	for(int j=1;j<=3;j++) {
	if(!WebUI.verifyElementVisible(findTestObject('Subscribe/Subscribe block'), FailureHandling.CONTINUE_ON_FAILURE)) {
		for(int i; i<=5;i++) {
		WebUI.scrollToElement(findTestObject('Headers and Footers/Footer contents/Web footer'), 0,FailureHandling.CONTINUE_ON_FAILURE)
		}
	}else {
		WebUI.verifyElementVisible(findTestObject('Subscribe/Subscribe block'), FailureHandling.CONTINUE_ON_FAILURE)
		break;
	}
	}
}
