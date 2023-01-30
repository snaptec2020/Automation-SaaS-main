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

Random randomNumberforCatalog = new Random()
Random randomNumberforProduct= new Random()


List Categories = CustomKeywords.'catalog.catlogComponants.getCategoryElements'()

       int elementIndexCatalog= Math.abs((randomNumberforCatalog.nextInt(Categories.size() - 1)))

CustomKeywords.'catalog.catlogComponants.getSpecifiedCatalogElement'(elementIndexCatalog
	, Categories)
//-------->

List Products = CustomKeywords.'products.productsFromCatalog.getProducts'()
int elementIndexProduct= Math.abs((randomNumberforProduct.nextInt(Products.size() - 1)))

//int elementIndex = Math.abs((randomNumberforProduct.nextInt(Products.size() - 1)))

CustomKeywords.'products.productsFromCatalog.getSpecifiedProduct'(elementIndexProduct, Products)














/*List products = WebUI.findWebElements(findTestObject('Object Repository/Products/List of products'), 30)

int size = products.size()

if (size > 0) {
    for (int i = 0; i < size; i++) {
        products.get(1).click()
    }
}*/


