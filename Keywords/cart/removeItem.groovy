package cart

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
//import com.sun.corba.se.impl.util.Utility
import utility.Utility



import internal.GlobalVariable

public class removeItem {
	def utilityFunctions = new Utility()
	@Keyword
	def getProductsInCart() {
		List RemoveCartItems = WebUI.findWebElements(findTestObject('Object Repository/Cart/Remove product from cart'),30)


		return RemoveCartItems;
	}

	@Keyword
	def getSpecifiedIteminThecart(int elementIndex,List items) {


		items.get(elementIndex).click()
	}

	@Keyword
	def clearCart() {
		//TestObject removeProductFromCart = new TestObject()
		//removeProductFromCart.addProperty('xpath', ConditionType.EQUALS, '//td[@class="col item"]//a[@title="إزالة منتج"]')
		WebUI.waitForElementVisible(findTestObject('Object Repository/Cart/Remove products from carts buttons'),5)
		List<WebElement> removeProductFromCartElements = WebUiCommonHelper.findWebElements(findTestObject('Object Repository/Cart/Remove products from carts buttons'), 5)

		while (removeProductFromCartElements.size() != 0) {
			if (removeProductFromCartElements.size().equals(1)) {
				//CustomKeywords.'products.productsFromCatalog.clickJS'(removeProductFromCartElements.get(0), 10)
				//CustomKeywords.'utility.Utility.clickOnObjectusingJavaScript'(removeProductFromCartElements.get(0))
				utilityFunctions.clickOnObjectusingJavaScript(removeProductFromCartElements.get(0))
				removeProductFromCartElements.remove(0)
			} else {
				utilityFunctions.clickOnObjectusingJavaScript(removeProductFromCartElements.get(0))
				removeProductFromCartElements = WebUiCommonHelper.findWebElements(findTestObject('Object Repository/Cart/Remove products from carts buttons'), 10)
			}
		}
	}
}









