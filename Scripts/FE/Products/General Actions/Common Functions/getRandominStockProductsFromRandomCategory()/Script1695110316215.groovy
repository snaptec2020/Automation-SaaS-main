import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

//selectCatalogComponents()
WebUI.callTestCase(findTestCase('FE/menu Items/Select random Catalog'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('FE/Scrolling/scrollingAtTheBottom'), [:], FailureHandling.CONTINUE_ON_FAILURE)

//def xPathDef = "(\"//div[@class='styles_productItem__YY5Bs']//button[@class='styles_atcButton__qYfHB styles_atcButton__kaT52'][contains(text(),'Add to Cart') or contains(text(),'أضف إلى السلة')]\")["+elementIndex+"]"
List prod = CustomKeywords.'products.productsFromCatalog.getinStockProductFromOnePage'()

//CustomKeywords.'products.productsFromCatalog.getRandominStockProductsFromOnePage'()
//KeywordUtil.markError(prod.get(1))
if (prod.size() == 0) {
    CustomKeywords.'products.productsFromCatalog.getRandominStockProductsFromRandomCategory'()
} else {
    def elementIndexproduct = CustomKeywords.'generalactions.generalStrings.getRandomNumberBetweenOnetoTarget'(prod.size())

    if (elementIndexproduct == 0) {
        elementIndexproduct = 1
    }
    
   // addProductToCart(prod.size())
	WebUI.callTestCase(findTestCase('FE/Products/General Actions/Common Functions/addProductToCart(int sizeForRandom)'), [('sizeForRandom') : prod.size()],
		FailureHandling.STOP_ON_FAILURE)
}



