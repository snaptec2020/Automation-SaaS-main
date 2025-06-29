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
import com.utils.CustomLogger
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
import internal.GlobalVariable as GlobalVariable
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

		CustomLogger.logInfo("=== Getting products ===")
		List inStockProducts = utilityFunctions.findWebElements('Object Repository/Products/Product container in page',30)//WebUI.findWebElements(findTestObject('Object Repository/Products/Product container in page'),30)
		CustomLogger.logInfo("=== products Size is:${inStockProducts.size().toString()}")

		return inStockProducts
	}

	@Keyword
	def getSpecifiedinStockProducts(int elementIndex,List productList ) {



		productList.get(elementIndex).click()
	}

	@Keyword
	def getSpecifiedinStockProductsFromRandomCategory(boolean doNeedClickAddToCart = true) {
		CustomLogger.logInfo("=== Starting Get Specified In-Stock Products From Random Category ===")

		try {
			CustomLogger.logInfo("Step 1: Selecting catalog components")
			selectCatalogComponents()
			CustomLogger.logInfo("Catalog components selection completed")

			CustomLogger.logInfo("Step 2: Scrolling to bottom of page")
			WebUI.callTestCase(findTestCase('FE/Scrolling/scrollingAtTheBottom'), [:], FailureHandling.OPTIONAL)
			CustomLogger.logInfo("Page scrolling completed")

			CustomLogger.logInfo("Step 3: Getting in-stock products list")
			List prod = getinStockProduct()
			CustomLogger.logInfo("Retrieved ${prod.size()} in-stock products")

			if(prod.size() == 0) {
				CustomLogger.logInfo("No in-stock products found, recursively calling getSpecifiedinStockProductsFromRandomCategory()")
				getSpecifiedinStockProductsFromRandomCategory(doNeedClickAddToCart)
			} else {
				CustomLogger.logInfo("Found ${prod.size()} in-stock products, proceeding with selection")

				// Generate random index
				def elementIndexproduct = genaralActions.getRandomNumberBetweenOnetoTarget(prod.size())
				def a = prod.size()
				CustomLogger.logInfo("Generated random index: ${elementIndexproduct} from ${a} products")

				// Ensure index is not 0
				if(elementIndexproduct == 0) {
					elementIndexproduct = 1
					CustomLogger.logInfo("Adjusted index from 0 to 1")
				}
				CustomLogger.logInfo("Final selected product index: ${elementIndexproduct}")

				// Create XPath for product selection
				String productXPath = "("+findTestObject('Products/Product container in page').findPropertyValue('xpath')+")" + "["+elementIndexproduct+"]"
				//"(//div[contains(@class, 'styles_productItem_')][.//span[contains(@class, 'styles_regularPrice') or contains(@class,'styles_oldPrice__')]/span[@dir]/span/span[2][not(text()='0')]]//button[text() = 'أضف إلى السلة' or text() ='Add to Cart']//parent::div//parent::div//parent::div[contains(@class,'productItem')]//button[not(contains(@class, 'wishlistButton'))])[${elementIndexproduct}]"
				CustomLogger.logInfo("Constructed product XPath: ${productXPath}")

				if(doNeedClickAddToCart) {
					CustomLogger.logInfo("Step 4: Clicking on selected product using JavaScript")
					utilityFunctions.clickOnObjectusingJavaScript(utilityFunctions.addXpathToTestObject(productXPath))
					CustomLogger.logInfo("Product click completed")

					// Check for location workflow
					CustomLogger.logInfo("Step 5: Checking location workflow conditions")
					CustomLogger.logInfo("GlobalVariable.normalEcommerce: ${GlobalVariable.normalEcommerce}")
					CustomLogger.logInfo("GlobalVariable.isFirstTime: ${GlobalVariable.isFirstTime}")

					if((GlobalVariable.normalEcommerce == null || !GlobalVariable.normalEcommerce) && GlobalVariable.isFirstTime) {
						CustomLogger.logInfo("Location workflow conditions met - executing location validation")

						CustomLogger.logInfo("Calling location validation test case")
						WebUI.callTestCase(findTestCase('FE/Website launch/Validations/Add locatin New workflow'), [:], FailureHandling.STOP_ON_FAILURE)
						CustomLogger.logInfo("Location validation completed")

						CustomLogger.logInfo("Re-clicking on product after location workflow")
						utilityFunctions.clickOnObjectusingJavaScript(utilityFunctions.addXpathToTestObject(productXPath))
						CustomLogger.logInfo("Product re-click completed after location workflow")
					} else {
						CustomLogger.logInfo("Location workflow conditions not met, skipping location validation")
						if(GlobalVariable.normalEcommerce != null) {
							CustomLogger.logInfo("  - normalEcommerce is not null: ${GlobalVariable.normalEcommerce}")
						} else {
							CustomLogger.logInfo("  - normalEcommerce is null")
						}
						CustomLogger.logInfo("  - isFirstTime: ${GlobalVariable.isFirstTime}")
					}
				} else {
					CustomLogger.logInfo("Step 4: Clicking on selected product Details using JavaScript")
					productXPath = productXPath + "/ancestor::div[contains(@class, 'styles_productItem_')][1]/div[1]//img"
					CustomLogger.logInfo("=== New Xpath is ${productXPath}")
					utilityFunctions.clickOnObjectusingJavaScript(productXPath) //utilityFunctions.addXpathToTestObject(productXPath)
					CustomLogger.logInfo("Product Details click completed")
				}
			}

			CustomLogger.logInfo("=== Successfully Completed Get Specified In-Stock Products From Random Category ===")
		} catch (Exception e) {
			CustomLogger.logInfo("Exception occurred in getSpecifiedinStockProductsFromRandomCategory(): ${e.getMessage()}")
			e.printStackTrace()
			throw e
		}
	}

	@Keyword
	def getSpecifiedinStockProductsText() {
		CustomLogger.logInfo("=== Starting Get Specified In-Stock Products Text ===")

		try {
			CustomLogger.logInfo("Calling getSpecifiedinStockProductsFromRandomCategory() to select a product")
			getSpecifiedinStockProductsFromRandomCategory(false)
			CustomLogger.logInfo("Successfully completed product selection from random category")

			// Get product title
			CustomLogger.logInfo("Retrieving product title using XPath: //h2[@class='product-content__title']")
			GlobalVariable.textSearch[0] = WebUI.getText(utilityFunctions.addXpathToTestObject("//h2[@class='product-content__title'] | //h1[starts-with(@class,styles_nameProduct__)]"))
			CustomLogger.logInfo("Product title retrieved: '${GlobalVariable.textSearch[0]}'")

			// Get product SKU
			CustomLogger.logInfo("Retrieving product SKU using XPath: //span[@class='sku__value']")
			GlobalVariable.textSearch[1] = WebUI.getText(utilityFunctions.addXpathToTestObject("//span[@class='sku__value'] | //div[starts-with(@class,'styles_sku__')]/div[starts-with(@class,'styles_groupValue__')]/span[starts-with(@class,'styles_value__')][1]"))
			CustomLogger.logInfo("Product SKU retrieved: '${GlobalVariable.textSearch[1]}'")

			// Log the retrieved values
			CustomLogger.logInfo("Final stored values:")
			CustomLogger.logInfo("  - GlobalVariable.textSearch[0] (Product Title): '${GlobalVariable.textSearch[0]}'")
			CustomLogger.logInfo("  - GlobalVariable.textSearch[1] (Product SKU): '${GlobalVariable.textSearch[1]}'")

			KeywordUtil.logInfo(GlobalVariable.textSearch[0])
			KeywordUtil.logInfo(GlobalVariable.textSearch[1])

			CustomLogger.logInfo("=== Successfully Completed Get Specified In-Stock Products Text ===")
		} catch (Exception e) {
			CustomLogger.logInfo("Exception occurred in getSpecifiedinStockProductsText(): ${e.getMessage()}")
			e.printStackTrace()
			throw e
		}
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
			KeywordUtil.markWarning("No Products in this Page")
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
		FailureHandling.OPTIONAL)
	}

	def selectCatalogComponents() {
		List Categories = catalogComp.getCategoryElements()
		elementIndex= genaralActions.getRandomNumberBetweenOnetoTarget(Categories.size() - 1)
		catalogComp.getSpecifiedCatalogElement(elementIndex, Categories)
	}
}
