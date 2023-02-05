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
int elementIndexCatalog;
int elementIndexProduct;

try 
{
List Categories = CustomKeywords.'catalog.catlogComponants.getCategoryElements'()

if (Categories.size()==0)

	{WebUI.closeBrowser(FailureHandling.STOP_ON_FAILURE);}
	
else
		
{elementIndexCatalog= Math.abs((randomNumberforCatalog.nextInt(Categories.size() - 1)))
	
CustomKeywords.'catalog.catlogComponants.getSpecifiedCatalogElement'(elementIndexCatalog, Categories)}
}

catch (Exception e)
{
e.printStackTrace();
WebUI.closeBrowser(FailureHandling.STOP_ON_FAILURE);
}

//-------->

WebUI.delay(5)



try 
{
List Products = CustomKeywords.'products.productsFromCatalog.getProducts'();
if (Products.size()==0) 
{WebUI.closeBrowser(FailureHandling.STOP_ON_FAILURE);}

else {	
elementIndexProduct= Math.abs((randomNumberforProduct.nextInt(Products.size() - 1)))
CustomKeywords.'products.productsFromCatalog.getSpecifiedProduct'(elementIndexProduct, Products)
WebUI.delay(10)
WebUI.waitForElementVisible(findTestObject('Object Repository/Products/Product content'), 10)
WebUI.verifyElementPresent(findTestObject('Object Repository/Products/Product content'), 20, FailureHandling.STOP_ON_FAILURE)
}
}


catch (Exception e)
{
e.printStackTrace();
WebUI.closeBrowser(FailureHandling.STOP_ON_FAILURE);
}





