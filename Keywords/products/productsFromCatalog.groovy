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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import java.util.List
import internal.GlobalVariable



public class productsFromCatalog {
 TestObject tb = new TestObject()
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
	//-------------------------------------------------
	@Keyword
	def getinStockProductFromOnePage() {


		List listOfInStockProducts = WebUI.findWebElements(findTestObject('Object Repository/Products/Add to cart enabled button'),30)


		return listOfInStockProducts
	}

	@Keyword
	def getSpecifiedinStockProductsFromOnePage(int elementIndex,List productList ) {

		tb.addProperty('xpath', ConditionType.EQUALS, "//div[@class='styles_productItem__YY5Bs']//button[@class='styles_atcButton__qYfHB styles_atcButton__kaT52'][contains(text(),'Add to Cart') or contains(text(),'أضف إلى السلة')]["+elementIndex+"]")

		//def product = productList.get(elementIndex).cl
		//KeywordUtil.logInfo("***************************\t"+product.toString())
		WebUI.waitForElementClickable(tb, 0)
		WebUI.click(tb)
		//productList.get(elementIndex).click()
	}
	//-------------------------------------------------


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
