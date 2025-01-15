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
import com.utils.CustomLogger

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

if(WebUI.waitForElementVisible(findTestObject('Object Repository/Cart/Out Of Stock Items'),5)) {
	CustomKeywords.'cart.removeItem.deleteOutStockFromCart'()
	//cartSubTotal = CustomKeywords.'cart.cartItems.getCartSubtotal'()
}
//CustomKeywords.'cart.removeItem.clearCart'()
//WebUI.callTestCase(findTestCase('FE/menu Items/Select Catalog - Select All Categories and Scrolling'), [:], FailureHandling.STOP_ON_FAILURE)
float cartSubTotal = CustomKeywords.'cart.cartItems.getCartSubtotal'()//WebUI.findWebElements(findTestObject('Object Repository/Cart/Cart count'), 10).size()


if (cartSubTotal == 0 || !(cartSubTotal>=GlobalVariable.minimum && cartSubTotal<=GlobalVariable.maximum)) {
	//CustomKeywords.'products.productsFromCatalog.getRandominStockProductsFromRandomCategory'()
	while(!(cartSubTotal>=GlobalVariable.minimum && cartSubTotal<=GlobalVariable.maximum))	{
		//switch(cartSubTotal){
		CustomLogger.logInfo(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+cartSubTotal.toString())
			if(cartSubTotal > GlobalVariable.maximum) {
				CustomKeywords.'cart.removeItem.deleteItemFromCart'()
				cartSubTotal = CustomKeywords.'cart.cartItems.getCartSubtotal'()
			}else if (cartSubTotal < GlobalVariable.minimum) {
				CustomKeywords.'products.productsFromCatalog.getSpecifiedinStockProductsFromRandomCategoryInTarget'()
				cartSubTotal = CustomKeywords.'cart.cartItems.getCartSubtotal'()
			}else if(WebUI.waitForElementVisible(findTestObject('Object Repository/Cart/Out Of Stock Items'),5)) {
				CustomKeywords.'cart.removeItem.deleteOutStockFromCart'()
				cartSubTotal = CustomKeywords.'cart.cartItems.getCartSubtotal'()
			}else{
				CustomKeywords.'products.productsFromCatalog.getSpecifiedinStockProductsFromRandomCategoryInTarget'()
				cartSubTotal = CustomKeywords.'cart.cartItems.getCartSubtotal'()
			}
	//}
	}
}