package products
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
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows


import catalog.catlogComponants


import java.util.List

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import internal.GlobalVariable



public class productsFromCatalog {
	TestObject tb=new TestObject();

	Random randomNumberforProduct = new Random()
	String objText = ""
	@Keyword
	def getProducts() {
		List Products = WebUI.findWebElements(findTestObject('Object Repository/Products/List of products'),30)


		return Products
	}

	@Keyword
	def getSpecifiedProduct(int elementIndex,List productList) {


		productList.get(elementIndex).click()
	}
	//--------------------------------------------------

	@Keyword
	def getinStockProduct() {


		List inStockProducts = WebUI.findWebElements(findTestObject('Object Repository/Products/Product container in page'),30)


		return inStockProducts
	}

	@Keyword
	def getSpecifiedinStockProducts(int elementIndex,List productList ) {



		productList.get(elementIndex).click()
	}

	@Keyword
	def getSpecifiedinStockProductsFromRandomCategory() {
		WebUI.callTestCase(findTestCase('FE/menu Items/Select Catalog'), [:], FailureHandling.STOP_ON_FAILURE)

		//		for (int i = 1; i <= 4; i++) {
		//			WebUI.scrollToElement(findTestObject('Headers and Footers/Footer contents/Web footer'), 0, FailureHandling.CONTINUE_ON_FAILURE)
		//		}
		WebUI.callTestCase(findTestCase('FE/Scrolling/scrollingAtTheBottom'), [:], FailureHandling.CONTINUE_ON_FAILURE)


		//WebUI.delay(3)

		//def xPathDef = "(\"//div[@class='styles_productItem__YY5Bs']//button[@class='styles_atcButton__qYfHB styles_atcButton__kaT52'][contains(text(),'Add to Cart') or contains(text(),'أضف إلى السلة')]\")["+elementIndex+"]"
		List prod = getinStockProduct()
		//KeywordUtil.markError(prod.get(1))
		if(prod.size()==0){
			//WebUI.closeBrowser(FailureHandling.STOP_ON_FAILURE)
			getSpecifiedinStockProductsFromRandomCategory()

		} else{
			def elementIndexproduct= Math.abs((randomNumberforProduct.nextInt(prod.size())))
			//KeywordUtil.logInfo(elementIndexproduct.toString() +prod.get(elementIndexproduct).toString())
			if(elementIndexproduct==0) {
				elementIndexproduct=1
			}
			tb.addProperty('xpath', ConditionType.EQUALS, "(//button[text() = 'أضف إلى السلة' or text() ='Add to Cart']//parent::div//parent::div[@class='styles_bottomContainer__Fvu6h']//parent::div[@class='styles_productItem__YY5Bs']//p)["+elementIndexproduct+"]")

			WebElement element = WebUiCommonHelper.findWebElement(tb,30)
			WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(element))

		}


		/*		String javaScript = '$x'+xPathDef+'.click()'
		 KeywordUtil.logInfo("**************** " + javaScript)
		 WebUI.executeJavaScript(javaScript, "")*/
		/*if(WebUI.waitForElementVisible(tb, 0)) {
		 WebUI.click(tb,FailureHandling.CONTINUE_ON_FAILURE)
		 KeywordUtil.logInfo("4")
		 }*/


		//productList.get(elementIndex).click()


	}
	@Keyword
	def getSpecifiedinStockProductsText() {
		WebUI.callTestCase(findTestCase('FE/menu Items/Select Catalog'), [:], FailureHandling.STOP_ON_FAILURE)

		//		for (int i = 1; i <= 4; i++) {
		//			WebUI.scrollToElement(findTestObject('Headers and Footers/Footer contents/Web footer'), 0, FailureHandling.CONTINUE_ON_FAILURE)
		//		}
		//
		//		WebUI.delay(3)
		WebUI.callTestCase(findTestCase('FE/Scrolling/scrollingAtTheBottom'), [:], FailureHandling.CONTINUE_ON_FAILURE)


		//def xPathDef = "(\"//div[@class='styles_productItem__YY5Bs']//button[@class='styles_atcButton__qYfHB styles_atcButton__kaT52'][contains(text(),'Add to Cart') or contains(text(),'أضف إلى السلة')]\")["+elementIndex+"]"
		List prod = getinStockProduct()
		//KeywordUtil.markError(prod.get(1))
		if(prod.size()==0){
			//WebUI.closeBrowser(FailureHandling.STOP_ON_FAILURE)
			getSpecifiedinStockProductsText()

		} else{
			def elementIndexproduct= Math.abs((randomNumberforProduct.nextInt(prod.size())))
			//KeywordUtil.logInfo(elementIndexproduct.toString() +prod.get(elementIndexproduct).toString())
			if(elementIndexproduct==0) {
				elementIndexproduct=1
			}
			//KeywordUtil.logInfo("AAAAAAAAAAAAAAAAAAAAAAAAAAAA\t"+elementIndexproduct.toString())
			tb.addProperty('xpath', ConditionType.EQUALS, "(//button[contains(text(),'Add to Cart') or contains(text(),'أضف إلى السلة')])["+elementIndexproduct+"]")

			objText = WebUI.getText(tb)
			if(objText.length()<=1) {
				//KeywordUtil.logInfo("BBBBBBBBBBBBBBBBBBBBBBBBBB\t"+objText)
				getSpecifiedinStockProductsText()
			}
			//println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA\t"+objText)
			GlobalVariable.textSearch = objText

		}
	}
	//-------------------------------------------------
	@Keyword
	def getinStockProductFromOnePage() {

		//WebUI.waitForElementVisible(findTestObject('Object Repository/Products/Add to cart enabled button'), 0)
		List listOfInStockProducts = WebUI.findWebElements(findTestObject('Object Repository/Products/Add to cart enabled button'),30)
		return listOfInStockProducts
	}
	
	@Keyword
	def OpenRandomProductAJStore(){
		
		
//		List prod = getinStockProductFromOnePage()
		List prod = WebUI.findWebElements(findTestObject('Object Repository/Products/Add to cart enabled button'),30)
		
		Random randomNumberforProduct = new Random()
		
		def elementIndexproduct = Math.abs(randomNumberforProduct.nextInt(prod.size()))
		
		def currentURL = WebUI.getUrl()
		
		TestObject tb = new TestObject()
		
		tb.addProperty('xpath', ConditionType.EQUALS, ('(//button[contains(text(),\'Add to Cart\') or contains(text(),\'أضف إلى السلة\')])[' +
			elementIndexproduct) + ']')
		
		WebElement element = WebUiCommonHelper.findWebElement(tb, 30)
		
		WebUI.executeJavaScript('arguments[0].click()', Arrays.asList(element))
		if (WebUI.getUrl() == currentURL) {
			WebUI.click(findTestObject('Object Repository/Cart/Continue Shopping'), FailureHandling.CONTINUE_ON_FAILURE)
		} else {
			KeywordUtil.markPassed('Trying to Get Configurable product')
		
			tb.addProperty('xpath', ConditionType.EQUALS, '//section[starts-with(@class,\'productFullDetail-groupOption-\')]//div[starts-with(@class,\'option-root-\')]')
		
			List genralDropDowns = WebUI.findWebElements(tb, 30)
		
			if (genralDropDowns.size() != 0) {
				for (int i = 1; i <= genralDropDowns.size(); i++) {
					tb.addProperty('xpath', ConditionType.EQUALS, ('(//section[starts-with(@class,\'productFullDetail-groupOption-\')]//div[starts-with(@class,\'option-root-\')])[' +
						i) + ']//div//button[not( @disabled)]')
		
					WebUI.click(tb)
				}
			}
		}


	}
	
	
	@Keyword
	def OpenRandomProductQasr(){
		
		
//		List prod = getinStockProductFromOnePage()
		TestObject items = new TestObject()
		items.addProperty("xpath",ConditionType.EQUALS,'//a[contains(@class,"item-nameProduct-")]')
		List prod = WebUI.findWebElements(items,30)
		
		Random randomNumberforProduct = new Random()
		
		def elementIndexproduct = Math.abs(randomNumberforProduct.nextInt(prod.size() - 1)) + 1 
		
		TestObject tb = new TestObject()
		
		tb.addProperty('xpath', ConditionType.EQUALS, ('('+ items.findPropertyValue("xpath") + ')[' +
			elementIndexproduct) + ']')
		
		WebElement element = WebUiCommonHelper.findWebElement(tb, 30)
		
		WebUI.executeJavaScript('arguments[0].click()', Arrays.asList(element))

	}

	@Keyword
	def getSpecifiedinStockProductsFromOnePage(int elementIndex,List productList ) {
		tb.addProperty('xpath', ConditionType.EQUALS, "//div[@class='styles_productItem__YY5Bs']//button[@class='styles_atcButton__qYfHB styles_atcButton__kaT52'][contains(text(),'Add to Cart') or contains(text(),'أضف إلى السلة')]["+elementIndex+"]")

		//def product = productList.get(elementIndex).cl
		//KeywordUtil.logInfo("***************************\t"+product.toString())
		WebUI.waitForElementClickable(tb, 0)
		WebElement element = WebUiCommonHelper.findWebElement(tb,30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(element))
		//productList.get(elementIndex).click()
	}

	@Keyword
	def getRandominStockProductsFromOnePage() {
		//def xPathDef = "(\"//div[@class='styles_productItem__YY5Bs']//button[@class='styles_atcButton__qYfHB styles_atcButton__kaT52'][contains(text(),'Add to Cart') or contains(text(),'أضف إلى السلة')]\")["+elementIndex+"]"
		List prod = getinStockProductFromOnePage()
		//KeywordUtil.markError(prod.get(1))
		if(prod.size()==0){
			//WebUI.closeBrowser(FailureHandling.STOP_ON_FAILURE)
			KeywordUtil.markPassed("No Products in this Page")

		} else{
			addProductToCart(prod.size())
			//Updated By Ahmed 14 Mar, 2023

			/*if(WebUI.verifyElementVisible(findTestObject('Object Repository/Cart/Continue Shopping'), FailureHandling.CONTINUE_ON_FAILURE)) {
			 WebUI.click(findTestObject('Object Repository/Cart/Continue Shopping'), FailureHandling.CONTINUE_ON_FAILURE)
			 } else {
			 //WebUI.takeScreenshot(FailureHandling.CONTINUE_ON_FAILURE)
			 if(WebUI.verifyElementPresent(findTestObject('Object Repository/Products/Add To Cart'), 10,FailureHandling.CONTINUE_ON_FAILURE)) {
			 WebUI.verifyElementNotClickable(findTestObject('Object Repository/Products/Add To Cart'), FailureHandling.STOP_ON_FAILURE)
			 } else {
			 //WebUI.takeScreenshot(FailureHandling.CONTINUE_ON_FAILURE)
			 KeywordUtil.markError("The product you selected is not found")
			 }
			 }*/
		}
	}


	@Keyword
	def getRandominStockProductsFromRandomCategory() {
		//CustomKeywords.'catalog.catlogComponants.getCategoryElements'()
		//WebUI.callTestCase(findTestCase('FE/menu Items/Select Catalog'), [:], FailureHandling.STOP_ON_FAILURE)

		//		for (int i = 1; i <= 4; i++) {
		//			WebUI.scrollToElement(findTestObject('Headers and Footers/Footer contents/Web footer'), 0, FailureHandling.CONTINUE_ON_FAILURE)
		//		}
		//
		//		WebUI.delay(3)
		WebUI.callTestCase(findTestCase('FE/Scrolling/scrollingAtTheBottom'), [:], FailureHandling.CONTINUE_ON_FAILURE)

		//def xPathDef = "(\"//div[@class='styles_productItem__YY5Bs']//button[@class='styles_atcButton__qYfHB styles_atcButton__kaT52'][contains(text(),'Add to Cart') or contains(text(),'أضف إلى السلة')]\")["+elementIndex+"]"
		List prod = getinStockProductFromOnePage()
		//KeywordUtil.markError(prod.get(1))
		if(prod.size()==0){
			//WebUI.closeBrowser(FailureHandling.STOP_ON_FAILURE)
			getRandominStockProductsFromRandomCategory()

		} else{
			def elementIndexproduct= Math.abs((randomNumberforProduct.nextInt(prod.size())))
			//KeywordUtil.logInfo(elementIndexproduct.toString() +prod.get(elementIndexproduct).toString())
			if(elementIndexproduct==0) {
				elementIndexproduct=1
			}
			//tb.addProperty('xpath', ConditionType.EQUALS, "(//div[@class='styles_productItem__YY5Bs']//button[@class='styles_atcButton__qYfHB styles_atcButton__kaT52'][contains(text(),'Add to Cart') or contains(text(),'أضف إلى السلة')])["+elementIndexproduct+"]")

			//WebElement element = WebUiCommonHelper.findWebElement(tb,30)
			//WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(element))
			//Updated By Ahmed 14 Mar, 2023
			addProductToCart(prod.size())
			//checkOnAddToStoreClickable()
			/*if(WebUI.verifyElementVisible(findTestObject('Object Repository/Cart/Continue Shopping'), FailureHandling.CONTINUE_ON_FAILURE)) {
			 WebUI.click(findTestObject('Object Repository/Cart/Continue Shopping'), FailureHandling.CONTINUE_ON_FAILURE)
			 } else {
			 //WebUI.takeScreenshot(FailureHandling.CONTINUE_ON_FAILURE)
			 KeywordUtil.markPassed("try to select product again")
			 getRandominStockProductsFromRandomCategory()
			 /*if(WebUI.verifyElementPresent(findTestObject('Object Repository/Products/Add To Cart'), 10,FailureHandling.CONTINUE_ON_FAILURE)) {
			 WebUI.verifyElementNotClickable(findTestObject('Object Repository/Products/Add To Cart'), FailureHandling.STOP_ON_FAILURE)
			 } else {
			 //WebUI.takeScreenshot(FailureHandling.CONTINUE_ON_FAILURE)
			 KeywordUtil.markError("The product you selected is not found")
			 }
			 }*/
		}

		/*		String javaScript = '$x'+xPathDef+'.click()'
		 KeywordUtil.logInfo("**************** " + javaScript)
		 WebUI.executeJavaScript(javaScript, "")*/
		/*if(WebUI.waitForElementVisible(tb, 0)) {
		 WebUI.click(tb,FailureHandling.CONTINUE_ON_FAILURE)
		 KeywordUtil.logInfo("4")
		 }*/


		//productList.get(elementIndex).click()
	}



	@Keyword
	def getOutOfStockProduct() {



		List OutOfStockProducts = WebUI.findWebElements(findTestObject('Object Repository/Products/Out of Stock products'),30)
		return OutOfStockProducts;
	}


	@Keyword
	def getSpecifiedOutOfStockProduct(int elementIndex,List productList) {

		//	WebUI.verifyElementNotClickable(findTestObject('Object Repository/Products/Out of Stock products'), FailureHandling.STOP_ON_FAILURE)

		productList.get(elementIndex).click()
	}


	//------------------------------------------------


	@Keyword
	def getProductsTRoWishList() {
		List Products = WebUI.findWebElements(findTestObject('Object Repository/WishList/Wish list button'),30)


		return Products
	}

	@Keyword
	def getSpecifiedProductToBVeAddedinwishlist(int elementIndex,List productList) {


		productList.get(elementIndex).click()
	}
	//--------------------------------------------------------------
	@Keyword
	def getWishListItems() {
		List Products = WebUI.findWebElements(findTestObject('Object Repository/WishList/Item added to wishlist'),30)


		return Products
	}

	@Keyword
	def getSpecifieditemfromWishListItems(int elementIndex,List productList) {


		productList.get(elementIndex).click()
	}
	@Keyword
	def configurableProduct() {
		//TO DO: Search for another key types of configurations
		tb.addProperty('xpath', ConditionType.EQUALS, "//section[starts-with(@class,'productFullDetail-groupOption-')]//div[starts-with(@class,'option-root-')]")

		List genralDropDowns = WebUI.findWebElements(tb, 30)
		if (genralDropDowns.size()!=0) {
			//    //div[starts-with(@class,'productFullDetail-groupOption-')]//div[starts-with(@class,'option-root-')]


			def element

			for (int i = 1; i <= genralDropDowns.size(); i++) {
				tb.addProperty('xpath', ConditionType.EQUALS, ("(//section[starts-with(@class,'productFullDetail-groupOption-')]//div[starts-with(@class,'option-root-')])[" +
						i) + "]//div//button[not( @disabled)]")

				WebUI.click(tb)

				//				element = WebUiCommonHelper.findWebElement(tb, 30).findElement(By.xpath(("(//section[starts-with(@class,'productFullDetail-groupOption-')]//div[starts-with(@class,'option-root-')])[" +
				//						i) + ']//child::*[contains(@class,\'styles_dropdownOption\') or contains(@class,\'styles_swatchOption\') or contains(@class,\'styles_checkboxOption\')]')).getAttribute(
				//						'class')
				//
				//
				//				switch (element) {
				//					case ~('^styles_dropdownOption.*') :
				//						tb.addProperty('xpath', ConditionType.EQUALS, ('(//div[contains(@class,\'attributesContainer_attributesContainer\')]//div[contains(@class,\'attributesContainer_optionsList\')])[' +
				//						i) + ']//following-sibling::*[@class]//*[contains(@class,\'general-dropdown__button\')]')

				//						WebUI.click(tb)
				//
				//					/*						tb.addProperty('xpath', ConditionType.EQUALS, ('(//div[contains(@class,\'attributesContainer_attributesContainer\')]//div[contains(@class,\'attributesContainer_optionsList\')])[' +
				//					 i) + ']//following-sibling::*[@class]//*[contains(@class,\'general-dropdown__button\')]//following-sibling::*[contains(@class,\'general-dropdown__menu\')]//li[1]')
				//					 */
				//						tb.addProperty('xpath', ConditionType.EQUALS, ('((//div[contains(@class,\'attributesContainer_attributesContainer\')]//div[contains(@class,\'attributesContainer_optionsList\')])[' +
				//								i) + ']//following-sibling::*[@class]//*[contains(@class,\'general-dropdown__button\')]//following-sibling::*[contains(@class,\'general-dropdown__menu\')]//li[@class=\'menu__item\']//button[not(@disabled)])[1]')
				//						WebUI.click(tb)
				//
				//						break
				//					case ~('^styles_checkboxOption.*') :
				//						tb.addProperty('xpath', ConditionType.EQUALS, ('(//div[contains(@class,\'attributesContainer_attributesContainer\')]//div[contains(@class,\'attributesContainer_optionsList\')])[' +
				//						i) + ']//span[contains(@class,\'styles_checkboxOption\')][1]//input')
				//
				//						WebUI.check(tb)
				//
				//						break
				//					case ~('^styles_swatchOption.*') :
				//						tb.addProperty('xpath', ConditionType.EQUALS, ('(//div[contains(@class,\'attributesContainer_attributesContainer\')]//div[contains(@class,\'attributesContainer_optionsList\')])[' +
				//						i) + ']//span[contains(@class,\'styles_swatchOption\')][1]')
				//
				//						WebUI.check(tb)
				//				}
			}
		}
	}


	@Keyword
	def checkOnAddToStoreClickable(def currentURL) {
		KeywordUtil.logInfo(WebUI.getUrl())
		KeywordUtil.logInfo(currentURL)

		//if(WebUI.verifyElementVisible(findTestObject('Object Repository/Cart/Continue Shopping'), FailureHandling.CONTINUE_ON_FAILURE)) {
		if(WebUI.getUrl()==currentURL){


			WebUI.click(findTestObject('Object Repository/Cart/Continue Shopping'), FailureHandling.CONTINUE_ON_FAILURE)

		} else {
			KeywordUtil.markPassed("Trying to Get Configurable product")
			configurableProduct()
			tb.addProperty('xpath', ConditionType.EQUALS, "//button//span[text()='أضف إلى السلة']")
			if(WebUI.verifyElementClickable(tb)) {
				WebUI.click(tb)
				WebUI.click(findTestObject('Object Repository/Cart/Continue Shopping'), FailureHandling.CONTINUE_ON_FAILURE)
			} else {
				KeywordUtil.markPassed("Trying to Get a new product")
				getRandominStockProductsFromRandomCategory()
			}
		}

	}
	def addProductToCart(int sizeForRandom) {
		def elementIndexproduct= Math.abs((randomNumberforProduct.nextInt(sizeForRandom)))
		//KeywordUtil.logInfo(elementIndexproduct.toString() +prod.get(elementIndexproduct).toString())
		if(elementIndexproduct==0) {
			elementIndexproduct=1
		}
		def currentURL = WebUI.getUrl()
		tb.addProperty('xpath', ConditionType.EQUALS, "(//button[contains(text(),'Add to Cart') or contains(text(),'أضف إلى السلة')])["+elementIndexproduct+"]")

		WebElement element = WebUiCommonHelper.findWebElement(tb,30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(element))
		WebUI.delay(5)
		checkOnAddToStoreClickable(currentURL)
	}



}
