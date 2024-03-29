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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

int cartContentsCount=WebUI.findWebElements(findTestObject('Object Repository/Cart/Cart count'),10).size()
if(cartContentsCount!=0) {
	WebUI.click(findTestObject('Object Repository/Cart/Cart'), FailureHandling.CONTINUE_ON_FAILURE)
}else {
	KeywordUtil.markPassed("Add to cart now")

WebUI.callTestCase(findTestCase('Test Cases/FE/Cart/General Actions/View cart after adding products to cart'), [:], FailureHandling.CONTINUE_ON_FAILURE);
}


//WebUI.delay(10)

//WebUI.scrollToElement(findTestObject('Object Repository/Cart/Filled cart'), 5, FailureHandling.STOP_ON_FAILURE)
//li[contains(@class,'styles_product')]
WebUI.verifyNotEqual(CustomKeywords.'utility.Utility.checkIfElementExist'('Object Repository/Cart/Items in cart'), 0, FailureHandling.CONTINUE_ON_FAILURE)

//WebUI.verifyElementPresent(findTestObject('Object Repository/Cart/Filled cart'), 10)	


