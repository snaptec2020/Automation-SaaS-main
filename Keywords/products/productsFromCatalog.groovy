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


import catalog.catlogComponants as catlogComponants


import java.util.List
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

		for (int i = 1; i <= 4; i++) {
			WebUI.scrollToElement(findTestObject('Headers and Footers/Footer contents/Web footer'), 0, FailureHandling.CONTINUE_ON_FAILURE)
		}

		WebUI.delay(3)

		//def xPathDef = "(\"//div[@class='styles_productItem__YY5Bs']//button[@class='styles_atcButton__qYfHB styles_atcButton__kaT52'][contains(text(),'Add to Cart') or contains(text(),'?????? ?????? ??????????')]\")["+elementIndex+"]"
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
			tb.addProperty('xpath', ConditionType.EQUALS, "(//button[text() = '?????? ?????? ??????????' or text() ='Add to Cart']//parent::div//parent::div[@class='styles_bottomContainer__Fvu6h']//parent::div[@class='styles_productItem__YY5Bs']//p)["+elementIndexproduct+"]")

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

		for (int i = 1; i <= 4; i++) {
			WebUI.scrollToElement(findTestObject('Headers and Footers/Footer contents/Web footer'), 0, FailureHandling.CONTINUE_ON_FAILURE)
		}

		WebUI.delay(3)

		//def xPathDef = "(\"//div[@class='styles_productItem__YY5Bs']//button[@class='styles_atcButton__qYfHB styles_atcButton__kaT52'][contains(text(),'Add to Cart') or contains(text(),'?????? ?????? ??????????')]\")["+elementIndex+"]"
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
			tb.addProperty('xpath', ConditionType.EQUALS, "(//button[text() = '?????? ?????? ??????????' or text() ='Add to Cart']//parent::div//parent::div[@class='styles_bottomContainer__Fvu6h']//parent::div[@class='styles_productItem__YY5Bs']//p)["+elementIndexproduct+"]")

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
	def getSpecifiedinStockProductsFromOnePage(int elementIndex,List productList ) {
		tb.addProperty('xpath', ConditionType.EQUALS, "//div[@class='styles_productItem__YY5Bs']//button[@class='styles_atcButton__qYfHB styles_atcButton__kaT52'][contains(text(),'Add to Cart') or contains(text(),'?????? ?????? ??????????')]["+elementIndex+"]")

		//def product = productList.get(elementIndex).cl
		//KeywordUtil.logInfo("***************************\t"+product.toString())
		WebUI.waitForElementClickable(tb, 0)
		WebElement element = WebUiCommonHelper.findWebElement(tb,30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(element))
		//productList.get(elementIndex).click()
	}

	@Keyword
	def getRandominStockProductsFromOnePage() {
		//def xPathDef = "(\"//div[@class='styles_productItem__YY5Bs']//button[@class='styles_atcButton__qYfHB styles_atcButton__kaT52'][contains(text(),'Add to Cart') or contains(text(),'?????? ?????? ??????????')]\")["+elementIndex+"]"
		List prod = getinStockProductFromOnePage()
		//KeywordUtil.markError(prod.get(1))
		if(prod.size()==0){
			//WebUI.closeBrowser(FailureHandling.STOP_ON_FAILURE)
			KeywordUtil.markPassed("No Products in this Page")

		} else{
			def elementIndexproduct= Math.abs((randomNumberforProduct.nextInt(prod.size())))
			//KeywordUtil.logInfo(elementIndexproduct.toString() +prod.get(elementIndexproduct).toString())
			if(elementIndexproduct==0) {
				elementIndexproduct=1
			}
			tb.addProperty('xpath', ConditionType.EQUALS, "(//div[@class='styles_productItem__YY5Bs']//button[@class='styles_atcButton__qYfHB styles_atcButton__kaT52'][contains(text(),'Add to Cart') or contains(text(),'?????? ?????? ??????????')])["+elementIndexproduct+"]")

			WebElement element = WebUiCommonHelper.findWebElement(tb,30)
			WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(element))
			if(WebUI.verifyElementVisible(findTestObject('Object Repository/Cart/Continue Shopping'), FailureHandling.CONTINUE_ON_FAILURE)) {
				WebUI.click(findTestObject('Object Repository/Cart/Continue Shopping'), FailureHandling.CONTINUE_ON_FAILURE)

			} else {
				//WebUI.takeScreenshot(FailureHandling.CONTINUE_ON_FAILURE)
				if(WebUI.verifyElementPresent(findTestObject('Object Repository/Products/Add To Cart'), 10,FailureHandling.CONTINUE_ON_FAILURE)) {
					WebUI.verifyElementNotClickable(findTestObject('Object Repository/Products/Add To Cart'), FailureHandling.STOP_ON_FAILURE)
				} else {
					//WebUI.takeScreenshot(FailureHandling.CONTINUE_ON_FAILURE)
					KeywordUtil.markError("The product you selected is not found")
				}
			}
		}
	}


	@Keyword
	def getRandominStockProductsFromRandomCategory() {
		//CustomKeywords.'catalog.catlogComponants.getCategoryElements'()
		WebUI.callTestCase(findTestCase('FE/menu Items/Select Catalog'), [:], FailureHandling.STOP_ON_FAILURE)

		for (int i = 1; i <= 4; i++) {
			WebUI.scrollToElement(findTestObject('Headers and Footers/Footer contents/Web footer'), 0, FailureHandling.CONTINUE_ON_FAILURE)
		}

		WebUI.delay(3)

		//def xPathDef = "(\"//div[@class='styles_productItem__YY5Bs']//button[@class='styles_atcButton__qYfHB styles_atcButton__kaT52'][contains(text(),'Add to Cart') or contains(text(),'?????? ?????? ??????????')]\")["+elementIndex+"]"
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
			tb.addProperty('xpath', ConditionType.EQUALS, "(//div[@class='styles_productItem__YY5Bs']//button[@class='styles_atcButton__qYfHB styles_atcButton__kaT52'][contains(text(),'Add to Cart') or contains(text(),'?????? ?????? ??????????')])["+elementIndexproduct+"]")

			WebElement element = WebUiCommonHelper.findWebElement(tb,30)
			WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(element))
			if(WebUI.verifyElementVisible(findTestObject('Object Repository/Cart/Continue Shopping'), FailureHandling.CONTINUE_ON_FAILURE)) {
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
				}*/
			}
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
}
