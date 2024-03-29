import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.nio.file.Files

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class test {
	static void main(String[] args) {
WebUI.callTestCase(findTestCase('FE/Cart/General Actions/View Cart'), [:], FailureHandling.STOP_ON_FAILURE)
if(WebUI.waitForElementVisible(findTestObject('Object Repository/Cart/Out Of Stock Items'),5)) {
	CustomKeywords.'cart.removeItem.deleteOutStockFromCart'()
	//cartSubTotal = CustomKeywords.'cart.cartItems.getCartSubtotal'()
}
//CustomKeywords.'cart.removeItem.clearCart'()
//WebUI.callTestCase(findTestCase('FE/menu Items/Select Catalog - Select All Categories and Scrolling'), [:], FailureHandling.STOP_ON_FAILURE)
float cartSubTotal = CustomKeywords.'cart.cartItems.getCartSubtotal'()//WebUI.findWebElements(findTestObject('Object Repository/Cart/Cart count'), 10).size()

//KeywordUtil.logInfo(cartSubTotal.toString())
if (cartSubTotal == 0 || !(cartSubTotal>=GlobalVariable.minimum && cartSubTotal<=GlobalVariable.maximum)) {
    //CustomKeywords.'products.productsFromCatalog.getRandominStockProductsFromRandomCategory'()
	while(!(cartSubTotal>=GlobalVariable.minimum && cartSubTotal<=GlobalVariable.maximum))	{
		//switch(cartSubTotal){
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

WebUI.callTestCase(findTestCase('FE/Cart/General Actions/View Cart'), [:], FailureHandling.STOP_ON_FAILURE)



WebUI.verifyElementVisible(findTestObject('Check Out/Apply Discount Button'))

WebUI.verifyElementVisible(findTestObject('Check Out/Cart Calculation'))

WebUI.verifyElementVisible(findTestObject('Check Out/Proceed To Checkout Button'))

WebUI.verifyElementVisible(findTestObject('Check Out/Shopping Cart head'))
		}
}
