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

import cart.cartItems
import catalog.catlogComponants
import generalactions.generalStrings
import generalactions.scrolling
import helpdesk.HelpdeskUtil

import java.util.List

import org.checkerframework.checker.guieffect.qual.UIType
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import internal.GlobalVariable
import utility.Utility

public class productsFromCatalog {
	TestObject tb=new TestObject()
	Random randomNumber = new Random()
	Random randomNumberforProduct = new Random()
	String objText = ""
	def catalogComp = new catlogComponants()
	public def utilityFunctions = new Utility()
	int elementIndex = 0
	def genaralActions= new generalStrings()
	def scrollingActions = new scrolling()
	def cartIm = new cartItems()

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
		selectCatalogComponents()
		WebUI.callTestCase(findTestCase('FE/Scrolling/scrollingAtTheBottom'), [:], FailureHandling.CONTINUE_ON_FAILURE)
		List prod = getinStockProduct()
		if(prod.size()==0){
			getSpecifiedinStockProductsFromRandomCategory()
		} else{
			def elementIndexproduct= genaralActions.getRandomNumberBetweenOnetoTarget(prod.size())
			def a = prod.size()
			if(elementIndexproduct==0) {
				elementIndexproduct=1
			}
			utilityFunctions.clickOnObjectusingJavaScript(utilityFunctions.addXpathToTestObject("(//button[text() = 'أضف إلى السلة' or text() ='Add to Cart']//parent::div//parent::div[@class='styles_bottomContainer__Fvu6h']//parent::div[@class='styles_productItem__YY5Bs']//button)["+elementIndexproduct+"]"))
		}
	}

	@Keyword
	def getSpecifiedinStockProductsText() {

		getSpecifiedinStockProductsFromRandomCategory()
		GlobalVariable.textSearch[0] = WebUI.getText(utilityFunctions.addXpathToTestObject("//h2[@class='product-content__title']"))
		GlobalVariable.textSearch[1] = WebUI.getText(utilityFunctions.addXpathToTestObject("//span[@class='sku__value']"))

		KeywordUtil.logInfo(GlobalVariable.textSearch[0])
		KeywordUtil.logInfo(GlobalVariable.textSearch[1])
	}
	//-------------------------------------------------
	@Keyword
	def getinStockProductFromOnePage() {
		List listOfInStockProducts = WebUI.findWebElements(findTestObject('Object Repository/Products/Add to cart enabled button'),30)
		return listOfInStockProducts
	}

	@Keyword
	def getSpecifiedinStockProductsFromOnePage(int elementIndex,List productList ) {
		tb = utilityFunctions.addXpathToTestObject("//div[@class='styles_productItem__YY5Bs']//button[@class='styles_atcButton__qYfHB styles_atcButton__kaT52'][contains(text(),'Add to Cart') or contains(text(),'أضف إلى السلة')]["+elementIndex+"]")
		WebUI.waitForElementClickable(tb, 0)
	}

	@Keyword
	def getSpecifiedinStockProductsFromRandomCategoryInTarget(boolean inTarget) {
		if(inTarget) {
			boolean found=false
			while(!found) {
				selectCatalogComponents()
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
		List prod = getinStockProductFromOnePage()
		if(prod.size()==0){
			KeywordUtil.markPassed("No Products in this Page")
		} else{
			addProductToCart(prod.size())
		}
	}

	@Keyword
	def getSpecifiedinStockProductsFromRandomCategoryInTarget() {
		WebUI.callTestCase(findTestCase('FE/Products/General Actions/Common Functions/getSpecifiedinStockProductsFromRandomCategoryInTarget()'),
				[:], FailureHandling.STOP_ON_FAILURE)
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
		WebUI.callTestCase(findTestCase('FE/Products/General Actions/Common Functions/getRandominStockProductsFromRandomCategory()'),
				[:], FailureHandling.STOP_ON_FAILURE)
	}



	@Keyword
	def getOutOfStockProduct() {



		List OutOfStockProducts = WebUI.findWebElements(findTestObject('Object Repository/Products/Out of Stock products'),10)
		return OutOfStockProducts
	}


	@Keyword
	def getSpecifiedOutOfStockProduct(int elementIndex,List productList) {
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
		WebUI.callTestCase(findTestCase('FE/Products/General Actions/Common Functions/configurableProduct()'), [:], FailureHandling.STOP_ON_FAILURE)
	}


	@Keyword
	def checkOnAddToStoreClickable(def currentURL) {

		WebUI.callTestCase(findTestCase('FE/Products/General Actions/Common Functions/checkOnAddToStoreClickable(def currentURL)'),[('currentURL') : currentURL], FailureHandling.STOP_ON_FAILURE)
	}

	def addProductToCart(int sizeForRandom) {
		WebUI.callTestCase(findTestCase('FE/Products/General Actions/Common Functions/addProductToCart(int sizeForRandom)'), [('sizeForRandom') : sizeForRandom],
		FailureHandling.STOP_ON_FAILURE)
	}

	def selectCatalogComponents() {
		List Categories = catalogComp.getCategoryElements()
		elementIndex= genaralActions.getRandomNumberBetweenOnetoTarget(Categories.size() - 1)
		catalogComp.getSpecifiedCatalogElement(elementIndex, Categories)
	}
}
