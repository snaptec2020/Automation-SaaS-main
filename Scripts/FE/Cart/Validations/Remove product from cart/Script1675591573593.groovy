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


//Random randomNumberforProduct = new Random();

//int elementIndex;


	WebUI.callTestCase(findTestCase('Test Cases/FE/Cart/Verifications/Cart is filled'), [:], FailureHandling.STOP_ON_FAILURE)
	
	
	//WebUI.scrollToElement(findTestObject('Object Repository/Cart/Product in item container'), 0)
	
	
	//WebUI.scrollToPosition(400, 450);
	
	//WebUI.scrollToElement(findTestObject('Object Repository/Cart/Remove product from cart'), 0)
	WebUI.click(findTestObject('Object Repository/Cart/Remove product from cart'))
	/*List Items = CustomKeywords.'cart.removeItem.getProductsInCart'()


	if (Items.size() == 0) {
		WebUI.closeBrowser(FailureHandling.STOP_ON_FAILURE)
	}
else {
elementIndex = Math.abs(randomNumberforProduct.nextInt(Items.size() - 1))

CustomKeywords.'cart.removeItem.getSpecifiedIteminThecart'(1, Items)*/











