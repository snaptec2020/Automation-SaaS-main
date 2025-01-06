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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import org.openqa.selenium.WebElement as Keys

try {
	long lastHeight=((Number) WebUI.executeJavaScript("return document.body.scrollHeight", null)).longValue();
	int  scrollingCount=0

	while (true) {
		WebUI.executeJavaScript("window.scrollTo(0, document.body.scrollHeight);", null);
		CustomKeywords.'generalactions.generalActions.waiteSpinnerToHide'()
		
		long newHeight = ((Number)WebUI.executeJavaScript("return document.body.scrollHeight", null)).longValue();
		if (newHeight == lastHeight || scrollingCount==5) {
			CustomKeywords.'utility.Utility.moveToElement'()
			break;
		}
		lastHeight = newHeight;
		scrollingCount++;
	}
} catch (InterruptedException e) {
	e.printStackTrace();
}
