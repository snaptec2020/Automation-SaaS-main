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
import generalactions.generalStrings
import helpdesk.HelpdeskUtil

import java.util.List

import org.checkerframework.checker.guieffect.qual.UIType
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import internal.GlobalVariable
import utility.Utility
/*------------------------------------------------------------
 * 
 * This class to handle the product functionalities like add to cart
 */

public class productsFromCatalog {
	TestObject tb=new TestObject();
	Random randomNumber = new Random()
	Random randomNumberforProduct = new Random()
	String objText = ""
	def catalogComp = new catlogComponants()
	public def utilityFunctions = new Utility()
	int elementIndex = 0
	def genaralActions= new generalStrings()

	@Keyword
	def getProducts() {
		List Products = utilityFunctions.findWebElements('Object Repository/Products/List of products',30)//WebUI.findWebElements(findTestObject('Object Repository/Products/List of products'),30)


		return Products
	}

	@Keyword
	def getSpecifiedProduct(int elementIndex,List productList) {


		productList.get(elementIndex).click()
	}
	//--------------------------------------------------

	@Keyword
	def getinStockProduct() {


		List inStockProducts = utilityFunctions.findWebElements('Object Repository/Products/Product container in page',30)//WebUI.findWebElements(findTestObject('Object Repository/Products/Product container in page'),30)


		return inStockProducts
	}

	@Keyword
	def getSpecifiedinStockProducts(int elementIndex,List productList ) {



		productList.get(elementIndex).click()
	}

	@Keyword
	def getSpecifiedinStockProductsFromRandomCategory() {
		//WebUI.callTestCase(findTestCase('FE/menu Items/Select Catalog'), [:], FailureHandling.STOP_ON_FAILURE)
		selectCatalogComponents()

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
			def elementIndexproduct= genaralActions.getRandomNumberBetweenOnetoTarget(prod.size())//Math.abs((randomNumberforProduct.nextInt(prod.size())))
			//KeywordUtil.logInfo(elementIndexproduct.toString() +prod.get(elementIndexproduct).toString())
			if(elementIndexproduct==0) {
				elementIndexproduct=1
			}
			//tb.addProperty('xpath', ConditionType.EQUALS, "(//button[text() = 'أضف إلى السلة' or text() ='Add to Cart']//parent::div//parent::div[@class='styles_bottomContainer__Fvu6h']//parent::div[@class='styles_productItem__YY5Bs']//p)["+elementIndexproduct+"]")
			utilityFunctions.clickOnObjectusingJavaScript(utilityFunctions.addXpathToTestObject("(//button[text() = 'أضف إلى السلة' or text() ='Add to Cart']//parent::div//parent::div[@class='styles_bottomContainer__Fvu6h']//parent::div[@class='styles_productItem__YY5Bs']//p)["+elementIndexproduct+"]"))
			//WebElement element = WebUiCommonHelper.findWebElement(tb,30)
			//WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(element))

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


		selectCatalogComponents()
		//WebUI.callTestCase(findTestCase('FE/menu Items/Select Catalog'), [:], FailureHandling.STOP_ON_FAILURE)

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
			def elementIndexproduct= genaralActions.getRandomNumberBetweenOnetoTarget(prod.size())//Math.abs((randomNumberforProduct.nextInt(prod.size())))
			//KeywordUtil.logInfo(elementIndexproduct.toString() +prod.get(elementIndexproduct).toString())
			if(elementIndexproduct==0) {
				elementIndexproduct=1
			}
			//KeywordUtil.logInfo("AAAAAAAAAAAAAAAAAAAAAAAAAAAA\t"+elementIndexproduct.toString())
			//tb.addProperty('xpath', ConditionType.EQUALS, "(//button[text() = 'أضف إلى السلة' or text() ='Add to Cart']//parent::div//parent::div[@class='styles_bottomContainer__Fvu6h']//parent::div[@class='styles_productItem__YY5Bs']//p)["+elementIndexproduct+"]")

			objText = WebUI.getText(utilityFunctions.addXpathToTestObject("(//button[text() = 'أضف إلى السلة' or text() ='Add to Cart']//parent::div//parent::div[@class='styles_bottomContainer__Fvu6h']//parent::div[@class='styles_productItem__YY5Bs']//p)["+elementIndexproduct+"]"))
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
	def getSpecifiedinStockProductsFromOnePage(int elementIndex,List productList ) {
		//tb.addProperty('xpath', ConditionType.EQUALS, "//div[@class='styles_productItem__YY5Bs']//button[@class='styles_atcButton__qYfHB styles_atcButton__kaT52'][contains(text(),'Add to Cart') or contains(text(),'أضف إلى السلة')]["+elementIndex+"]")
		tb = utilityFunctions.addXpathToTestObject("//div[@class='styles_productItem__YY5Bs']//button[@class='styles_atcButton__qYfHB styles_atcButton__kaT52'][contains(text(),'Add to Cart') or contains(text(),'أضف إلى السلة')]["+elementIndex+"]")
		//def product = productList.get(elementIndex).cl
		//KeywordUtil.logInfo("***************************\t"+product.toString())
		WebUI.waitForElementClickable(tb, 0)
		utilityFunctions.clickOnObjectusingJavaScript(tb)
		//WebElement element = WebUiCommonHelper.findWebElement(tb,30)
		//WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(element))
		//productList.get(elementIndex).click()
	}

	@Keyword
	def getSpecifiedinStockProductsFromRandomCategoryInTarget(boolean inTarget) {
		if(inTarget) {
			boolean found=false
			while(!found) {
				selectCatalogComponents()
				//WebUI.callTestCase(findTestCase('FE/Scrolling/scrollingAtTheBottom'), [:], FailureHandling.CONTINUE_ON_FAILURE)
				List prod = getinStockProduct()
				if(prod.size()==0){
					getSpecifiedinStockProductsFromRandomCategoryInTarget(inTarget)
				} else{
					def elementIndexproduct= genaralActions.getRandomNumberBetweenOnetoTarget(prod.size())//Math.abs((randomNumberforProduct.nextInt(prod.size())))
					if(elementIndexproduct==0) {
						elementIndexproduct=1
					}
					def SelectedProductXPath="(//div[starts-with(@class,'styles_productItem__')]//button[starts-with(@class,'styles_atcButton__')][contains(text(),'Add to Cart') or contains(text(),'أضف إلى السلة')])["+elementIndexproduct+"]"
					TestObject priceOfSelectedPrudct=new TestObject()
					String priceOfSelectedPrudctXPath=SelectedProductXPath + "/../../../div[contains(@class,'styles_informationContainer')]//span[@dir='rtl']/span/span[2]"
					priceOfSelectedPrudct.addProperty("xpath",ConditionType.EQUALS,priceOfSelectedPrudctXPath)
					WebElement priceOfSelectedPrudctElm = WebUiCommonHelper.findWebElement(priceOfSelectedPrudct, 5)
					float priceOfSelectedPrudctAmount= priceOfSelectedPrudctElm.getText().toFloat()
					if (priceOfSelectedPrudctAmount>1000 & priceOfSelectedPrudctAmount<2500) {
						found=true
						utilityFunctions.clickOnObjectusingJavaScript(utilityFunctions.addXpathToTestObject(SelectedProductXPath))
					}
				}
			}
		}else {
			getSpecifiedinStockProductsFromRandomCategory()
		}
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
	def getSpecifiedinStockProductsFromRandomCategoryInTarget() {
		float minimum = 1000
		float maximum=2500
		selectCatalogComponents()
		WebUI.callTestCase(findTestCase('FE/Scrolling/scrollingAtTheBottom'), [:], FailureHandling.CONTINUE_ON_FAILURE)
		List <WebElement> prod = utilityFunctions.findWebElements('Object Repository/Products/Product container in page in target',30)
		/*	if (prod.size()==0) {
		 getSpecifiedinStockProductsFromRandomCategoryInTarget()
		 }*/
		boolean found=false
		prod.any ({
			WebElement currentPrice= it.findElements(By.xpath("./div[contains(@class,'styles_informationContainer')]/div[contains(@class,'styles_priceContainer')]/span/span/span/span/span[1]")).get(0)
			float priceOfSelectedPrudctAmount= currentPrice.getText().replaceAll(",", "").replaceAll(" ", "").toFloat()
			if(priceOfSelectedPrudctAmount>=minimum & priceOfSelectedPrudctAmount<=maximum) {
				found=true
				WebElement currentAddToCartBtn=it.findElements(By.xpath("./div/div/button[contains(text(),'أضف إلى السلة') or contains(text(),'Add to cart')]")).get(0)
				def currentURL = WebUI.getUrl()
				(new HelpdeskUtil().ScrollToElement(currentAddToCartBtn))
				WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(currentAddToCartBtn))
				//utilityFunctions.clickOnObjectusingJavaScript(currentAddToCartBtn)
				WebUI.delay(1)
				if (currentURL.equals(WebUI.getUrl()) & WebUI.verifyElementNotVisible(findTestObject('Object Repository/Cart/Continue Shopping'),FailureHandling.OPTIONAL)  ) {
					return
				}
				checkOnAddToStoreClickable(currentURL)
				return true
			}else if (priceOfSelectedPrudctAmount < minimum) {
				found=true
				WebElement currentAddToCartBtn=it.findElements(By.xpath("./div/div/button[contains(text(),'أضف إلى السلة') or contains(text(),'Add to cart')]")).get(0)
				def currentURL = WebUI.getUrl()
				(new HelpdeskUtil().ScrollToElement(currentAddToCartBtn))
				WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(currentAddToCartBtn))
				WebUI.delay(1)
				if (currentURL.equals(WebUI.getUrl()) & WebUI.verifyElementNotVisible(findTestObject('Object Repository/Cart/Continue Shopping'),FailureHandling.OPTIONAL)  ) {
					return
				}
				checkOnAddToStoreClickable(currentURL)
				WebUI.callTestCase(findTestCase('FE/Cart/General Actions/View Cart'), [:], FailureHandling.STOP_ON_FAILURE)
				float Total = (((WebUI.getText(findTestObject('Object Repository/Cart/Cart Subtotal (Inc VAT)')).replaceAll(',', '') =~ '\\d+\\.\\d+')[0]) as float)
				int neededQty=Math.ceil(minimum/Total)
				//				for (int i=1; i< neededQty ; i++) {
				//					WebUI.waitForElementClickable(findTestObject('Object Repository/Cart/item plus'), 5)
				//					WebUI.click(findTestObject('Object Repository/Cart/item plus'))
				//					WebUI.delay(1)
				//				}
				WebUI.verifyElementVisible(findTestObject('Object Repository/Cart/insert NeedQTY By text'), FailureHandling.CONTINUE_ON_FAILURE)

				//WebUI.sendKeys(findTestObject(findTestObject('Object Repository/Cart/insert NeedQTY By text')), Keys.chord(Keys.CONTROL, 'a'))
				WebUI.click(findTestObject('Object Repository/Cart/insert NeedQTY By text'))
				WebUI.sendKeys(findTestObject('Object Repository/Cart/insert NeedQTY By text'), Keys.chord(Keys.CONTROL, 'a'))
				WebUI.sendKeys(findTestObject('Object Repository/Cart/insert NeedQTY By text'), Keys.chord(Keys.BACK_SPACE))
				WebUI.sendKeys(findTestObject('Object Repository/Cart/insert NeedQTY By text'), neededQty.toString(), FailureHandling.CONTINUE_ON_FAILURE)

				//WebUI.(findTestObject('Object Repository/Cart/insert NeedQTY By text'), neededQty.toString())
				return true
			}
		})
		if(!found) {
			getSpecifiedinStockProductsFromRandomCategoryInTarget()
		}
	}
	@Keyword
	def getRandominStockProductsFromRandomCategoryInTarget(boolean isInTarget) {
		if (isInTarget) {

		}else {
			getRandominStockProductsFromRandomCategory()
		}
	}

	@Keyword
	def getRandominStockProductsFromRandomCategory() {
		//CustomKeywords.'catalog.catlogComponants.getCategoryElements'()
		//WebUI.callTestCase(findTestCase('FE/menu Items/Select Catalog'), [:], FailureHandling.STOP_ON_FAILURE)
		selectCatalogComponents()

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
			def elementIndexproduct= genaralActions.getRandomNumberBetweenOnetoTarget(prod.size())//Math.abs((randomNumberforProduct.nextInt(prod.size())))
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



		List OutOfStockProducts = WebUI.findWebElements(findTestObject('Object Repository/Products/Out of Stock products'),10)
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
		//tb.addProperty('xpath', ConditionType.EQUALS, '//div[contains(@class,\'attributesContainer_attributesContainer\')]//div[contains(@class,\'attributesContainer_optionsList\')]')


		List genralDropDowns = WebUI.findWebElements(utilityFunctions.addXpathToTestObject('//div[contains(@class,\'attributesContainer_attributesContainer\')]//div[contains(@class,\'attributesContainer_optionsList\')]'), 30)
		if (genralDropDowns.size()!=0) {



			def element

			for (int i = 1; i <= genralDropDowns.size(); i++) {
				tb.addProperty('xpath', ConditionType.EQUALS, ('(//div[contains(@class,\'attributesContainer_attributesContainer\')]//div[contains(@class,\'attributesContainer_optionsList\')])[' +
						i) + ']')

				element = WebUiCommonHelper.findWebElement(tb, 30).findElement(By.xpath(('(//div[contains(@class,\'attributesContainer_attributesContainer\')]//div[contains(@class,\'attributesContainer_optionsList\')])[' +
						i) + ']//child::*[contains(@class,\'styles_dropdownOption\') or contains(@class,\'styles_swatchOption\') or contains(@class,\'styles_checkboxOption\')]')).getAttribute(
						'class')


				switch (element) {
					case ~('^styles_dropdownOption.*') :
						tb.addProperty('xpath', ConditionType.EQUALS, ('(//div[contains(@class,\'attributesContainer_attributesContainer\')]//div[contains(@class,\'attributesContainer_optionsList\')])[' +
						i) + ']//following-sibling::*[@class]//*[contains(@class,\'general-dropdown__button\')]')
					//WebUI.scrollToElement(tb, 10)

						WebUI.click(tb)


					/*						tb.addProperty('xpath', ConditionType.EQUALS, ('(//div[contains(@class,\'attributesContainer_attributesContainer\')]//div[contains(@class,\'attributesContainer_optionsList\')])[' +
					 i) + ']//following-sibling::*[@class]//*[contains(@class,\'general-dropdown__button\')]//following-sibling::*[contains(@class,\'general-dropdown__menu\')]//li[1]')
					 */
						tb.addProperty('xpath', ConditionType.EQUALS, ('((//div[contains(@class,\'attributesContainer_attributesContainer\')]//div[contains(@class,\'attributesContainer_optionsList\')])[' +
								i) + ']//following-sibling::*[@class]//*[contains(@class,\'general-dropdown__button\')]//following-sibling::*[contains(@class,\'general-dropdown__menu\')]//li[@class=\'menu__item\']//button[not(@disabled)])[1]')
						WebUI.click(tb)

						break
					case ~('^styles_checkboxOption.*') :
						tb.addProperty('xpath', ConditionType.EQUALS, ('(//div[contains(@class,\'attributesContainer_attributesContainer\')]//div[contains(@class,\'attributesContainer_optionsList\')])[' +
						i) + ']//span[contains(@class,\'styles_checkboxOption\')][1]//input')

						WebUI.check(tb)

						break
					case ~('^styles_swatchOption.*') :
						tb.addProperty('xpath', ConditionType.EQUALS, ('(//div[contains(@class,\'attributesContainer_attributesContainer\')]//div[contains(@class,\'attributesContainer_optionsList\')])[' +
						i) + ']//span[contains(@class,\'styles_swatchOption\')][1]')

						WebUI.check(tb)
				}
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
			WebUI.scrollToElement(findTestObject('Object Repository/Products/Product content'), 0, FailureHandling.STOP_ON_FAILURE)
			configurableProduct()
			tb = utilityFunctions.addXpathToTestObject("//*[@class='product-content__button-wrapper']//button[@class='product-content__cart']")
			//tb.addProperty('xpath', ConditionType.EQUALS, "//*[@class='product-content__button-wrapper']//button[@class='product-content__cart']")
			if(WebUI.verifyElementClickable(tb)) {
				WebUI.click(tb)
				WebUI.waitForElementVisible(findTestObject('Object Repository/Cart/Continue Shopping'),5, FailureHandling.CONTINUE_ON_FAILURE)
				WebUI.click(findTestObject('Object Repository/Cart/Continue Shopping'), FailureHandling.CONTINUE_ON_FAILURE)
			} else {
				KeywordUtil.markPassed("Trying to Get a new product")
				getRandominStockProductsFromRandomCategory()
			}
		}

	}
	def addProductToCart(int sizeForRandom) {
		def elementIndexproduct= genaralActions.getRandomNumberBetweenOnetoTarget(sizeForRandom)//Math.abs((randomNumberforProduct.nextInt(sizeForRandom)))
		//KeywordUtil.logInfo(elementIndexproduct.toString() +prod.get(elementIndexproduct).toString())
		if(elementIndexproduct==0) {
			elementIndexproduct=1
		}
		def currentURL = WebUI.getUrl()
		//tb.addProperty('xpath', ConditionType.EQUALS, "(//div[@class='styles_productItem__YY5Bs']//button[@class='styles_atcButton__qYfHB styles_atcButton__kaT52'][contains(text(),'Add to Cart') or contains(text(),'أضف إلى السلة')])["+elementIndexproduct+"]")

		utilityFunctions.clickOnObjectusingJavaScript(utilityFunctions.addXpathToTestObject("(//div[starts-with(@class,'styles_productItem__')]//button[starts-with(@class,'styles_atcButton__')][contains(text(),'Add to Cart') or contains(text(),'أضف إلى السلة')])["+elementIndexproduct+"]"))
		//WebElement element = WebUiCommonHelper.findWebElement(tb,30)
		//WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(element))
		WebUI.delay(5)
		checkOnAddToStoreClickable(currentURL)
	}
	def selectCatalogComponents() {
		List Categories = catalogComp.getCategoryElements()


		elementIndex= genaralActions.getRandomNumberBetweenOnetoTarget(Categories.size() - 1)//Math.abs((randomNumber.nextInt(Categories.size() - 1)))

		//CustomKeywords.'catalog.catlogComponants.getSpecifiedCatalogElement'(elementIndex, Categories)
		catalogComp.getSpecifiedCatalogElement(elementIndex, Categories)
	}


}
