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
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows


import catalog.catlogComponants

import java.awt.Robot
import java.util.List

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

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

		tb.addProperty('xpath', ConditionType.EQUALS, '(//button[contains(text(),\'Add to Cart\') or contains(text(),\'أضف إلى السلة\')])[' +
				elementIndexproduct + ']')

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
	def OpenRandomProductAlAseel(){

		//		int spotY =WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/ProductsSection'), 10).getLocation().getY()
		//		WebUI.scrollToPosition(0,spotY)
		//		WebUI.scrollToElement(null, 10)
		//		WebUI.scrollToPosition(0, findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/ProductsSection').get)(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/ProductsSection'), 10)


		//		try {
		//			long lastHeight=((Number) WebUI.executeJavaScript("return document.body.scrollHeight", null)).longValue();
		//
		//			while (true) {
		//			  WebUI.executeJavaScript("window.scrollTo(0, document.body.scrollHeight);", null);
		//			  Thread.sleep(2000);
		//			  long newHeight = ((Number)WebUI.executeJavaScript("return document.body.scrollHeight", null)).longValue();
		//			  if (newHeight == lastHeight) {
		//				break;
		//			  }
		//			  lastHeight = newHeight;
		//			}
		//		  } catch (InterruptedException e) {
		//		   e.printStackTrace();
		//		  }

		//		scrollToVerifyElementVisiblity(findTestObject("Object Repository/Helpdesk/AlAseel/FE/Shared/ProductsSection"))
		//		WebElement el= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/ProductsSection'), 10)
		//		WebUI.executeJavaScript("arguments[0].scrollIntoView(true);", Arrays.asList(el));


		//		WebDriver driver = DriverFactory.getWebDriver()
		//		WebElement elem = driver.findElement(By.xpath('//p[@class="b-title h2"]'))
		//		WebUI.executeJavaScript('arguments[0].scrollIntoView(true)', Arrays.asList(elem))

		//		WebElement elem = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/ProductsSection'), 10)
		//		WebUI.executeJavaScript('arguments[0].scrollIntoView()', Arrays.asList(elem))

		//		Actions act = new Actions(driver)
		//		act.moveToElement(elem).build().perform()


		//		scrollToVerifyElementVisiblityAlAseel(findTestObject("Object Repository/Helpdesk/AlAseel/FE/Shared/ProductsSection"))
		if(WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/Logo'),5,FailureHandling.OPTIONAL)){
			WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/Logo'),FailureHandling.OPTIONAL)
		}

		//		((JavascriptExecutor) DriverFactory.getWebDriver()).executeScript("arguments[0].scrollIntoView();", )
		WebUI.waitForPageLoad(20)
		WebUI.executeJavaScript("arguments[0].scrollIntoView(true);", WebUiCommonHelper.findWebElements(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/ProductsSection'), 10))
		WebUI.delay(2)
		
		
		List<WebElement> productsCategoriesList = WebUiCommonHelper.findWebElements(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/ProductsCategoriesSection'), 5)
		for(int i=1;i<=productsCategoriesList.size();i++) {
			TestObject randomNumberCatProductTO = new TestObject()
			.addProperty('xpath', ConditionType.EQUALS, '('
				+ findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/ProductsCategoriesSection').findPropertyValue("xpath")
				+ ')[' + i.toString() + ']/a')
			WebElement randomNumberCatProductElm = WebUiCommonHelper.findWebElement(randomNumberCatProductTO, 5)
			clickJS(randomNumberCatProductElm,3)
			WebUI.delay(1)
		}
		
		
		TestObject items = new TestObject()
		items.addProperty("xpath",ConditionType.EQUALS,"//button[contains(@title,'Add to Cart') or contains(@title,'إضافة إلى السلة')]")
		WebUI.waitForElementVisible(items, 10)
		List<WebElement> prod = WebUI.findWebElements(items,3)
		
		Random randomNumberforProduct = new Random()

		def elementIndexproduct = Math.abs(randomNumberforProduct.nextInt(prod.size() - 1)) + 1

		TestObject tb = new TestObject()

		tb.addProperty('xpath', ConditionType.EQUALS, ('('+ items.findPropertyValue("xpath") + ')[' +
				elementIndexproduct) + ']')

		WebElement element = WebUiCommonHelper.findWebElement(tb, 5)
		String currentURL = WebUI.getUrl()
		clickJS(element, 5)
		WebUI.delay(2)
		WebUI.waitForPageLoad(20,FailureHandling.OPTIONAL)
		if (WebUI.getUrl().equals(currentURL)) {
			WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/MiniCart/ContinueShoping'), FailureHandling.CONTINUE_ON_FAILURE)
		} else {
//			KeywordUtil.markPassed('Trying to Get Configurable product')
			String MainXPath = '//div[starts-with(@class,"product-options-wrapper")]//div[starts-with(@class,"swatch-attribute") and @aria-required="true"]'
			TestObject Maintb = new TestObject()
			Maintb.addProperty('xpath', ConditionType.EQUALS, MainXPath)
			WebUI.waitForElementVisible(Maintb, 10,FailureHandling.OPTIONAL)
			List genralDropDowns = WebUI.findWebElements(Maintb, 30)

			if (genralDropDowns.size() != 0) {
				for (int i = 1; i <= genralDropDowns.size(); i++) {
					String SubxPath= '(' + MainXPath + ')' +'[' + i + ']//div[not( @disabled)]'
					TestObject Subtb = new TestObject()
					Subtb.addProperty('xpath', ConditionType.EQUALS, SubxPath)
					WebUI.waitForElementVisible(Subtb, 10)
					List<WebElement> SubElem=WebUiCommonHelper.findWebElements(Subtb, 30)
//					SubElem.get(0).click()
					clickJS(SubElem.get(0), 5)
				}
			}

		}
	}

	@Keyword
	def scrollToVerifyElementVisiblity(TestObject testObjectRelativeId) {
		try {
			for(int i=4;i<=0;i--) {
				if (WebUI.verifyElementVisible(testObjectRelativeId,FailureHandling.CONTINUE_ON_FAILURE)) {
					//WebUI.executeJavaScript("window.scrollTo(0, document.body.scrollHeight/2);", null);
					break;
				}
				KeywordUtil.markPassed("try to scroll again")
				WebUI.executeJavaScript("window.scrollTo(0, document.body.scrollHeight/"+i.toString()+");", null);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Keyword
	def scrollToVerifyElementVisiblityAlAseel(TestObject testObjectRelativeId) {
		try {
			Robot robot = new Robot()
			int trial=0
			while(trial<10) {
				Thread.sleep(1000)
				trial+=1
				robot.mouseWheel(5)
				if (WebUI.verifyElementVisible(testObjectRelativeId,FailureHandling.CONTINUE_ON_FAILURE)) {
					//WebUI.executeJavaScript("window.scrollTo(0, document.body.scrollHeight/2);", null);
					robot.mouseWheel(5)
					break;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	@Keyword
	def OpenRandomProductOrange(){


		boolean found=false

		while(!found) {
			WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/Shared/Logo'))
			TestObject items = new TestObject()
			items.addProperty("xpath",ConditionType.EQUALS,'//a[contains(@class,"baseProduct-productName-")]')
			List prod = WebUI.findWebElements(items,30)

			Random randomNumberforProduct = new Random()

			def elementIndexproduct = Math.abs(randomNumberforProduct.nextInt(prod.size() - 1)) + 1

			TestObject tb = new TestObject()

			tb.addProperty('xpath', ConditionType.EQUALS, ('('+ items.findPropertyValue("xpath") + ')[' +
					elementIndexproduct) + ']')

			WebElement element = WebUiCommonHelper.findWebElement(tb, 30)

			WebUI.executeJavaScript('arguments[0].click()', Arrays.asList(element))

			if(WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Cart/Add to cart - Active'),FailureHandling.OPTIONAL)) {
				found =true
			}
		}
	}

	@Keyword
	def ScrollToElement(TestObject tb) {
		List<WebElement> element = WebUiCommonHelper.findWebElements(tb, 30)
		WebUI.executeJavaScript('arguments[0].click()', element)
	}

	@Keyword
	def clickJS(TestObject to, int timeout){
		WebElement element = WebUiCommonHelper.findWebElement(to, timeout)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(element))
	}
	
	@Keyword
	def clickJS(WebElement element, int timeout){
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(element))
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
