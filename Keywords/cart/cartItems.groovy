package cart

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import utility.Utility

public class cartItems {
	def utility = new Utility()
	@Keyword
	def getProductsInCart() {
		List CartItems = WebUI.findWebElements(findTestObject('Object Repository/Cart/Items in cart'),30)


		return CartItems;
	}

	@Keyword
	def getSpecifiedIteminThecart(int elementIndex,List items) {


		items.get(elementIndex).click()
	}
	@Keyword
	def getSumOfProductsPriceInCart() {
		double sumOfOrder
		def numOfElementsInCard = utility.checkIfElementExist(findTestObject('Object Repository/Cart/Product price'))
		if(numOfElementsInCard!=0) {
			for(int i=1;i<=numOfElementsInCard;i++) {
				double productPrice = WebUI.getText(utility.addXpathToTestObject("("+findTestObject('Object Repository/Cart/Product price').findPropertyValue('xpath') + ")["+i+"]")).replaceAll(",", "") as double
				sumOfOrder =sumOfOrder + productPrice
				//KeywordUtil.logInfo(WebUI.getText(CustomKeywords.'utility.Utility.addXpathToTestObject'("("+findTestObject('Object Repository/Cart/Product price').findPropertyValue('xpath') + ")["+i+"]")))
				KeywordUtil.logInfo(sumOfOrder.round(2).toString())
			}
			return sumOfOrder.round(2)
		} else {
			return 0
		}
	}
	@Keyword
	float getCartSubtotal() {
		if (WebUI.waitForElementVisible(findTestObject('Object Repository/Cart/Empty cart'), 5, FailureHandling.CONTINUE_ON_FAILURE)) {
			return 0
		}else {
			def  totalPrice = WebUI.getText(findTestObject('Object Repository/Cart/Cart Subtotal (Inc VAT)')).replaceAll(",", "") =~/\d+\.\d+/
			return totalPrice[0] as float
		}
	}
	//	@Keyword
	//	def checkTheTarget() {
	//		double minimum=1000
	//		double maximum=2500
	//		double Total =(WebUI.getText(findTestObject('Object Repository/Cart/Cart Subtotal (Inc VAT)')).replaceAll(",", "") =~/\d+\.\d+/)[0] as double
	//		if (minimum >= Total && Total >= maximum) {
	//			plac
	//
	//		}
	//	}
}
