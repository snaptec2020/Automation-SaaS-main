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

try 
{
	
	WebUI.callTestCase(findTestCase('Test Cases/FE/Cart/General Actions/Continue shopping after adding product to cart'), [:], FailureHandling.STOP_ON_FAILURE);
	
	WebUI.delay(5)
	

List prod = CustomKeywords.'products.productsFromCatalog.getinStockProductFromOnePage'()
if(prod.size()==0)
		{WebUI.closeBrowser(FailureHandling.STOP_ON_FAILURE)}

		else

{
	
//elementIndexproduct= Math.abs((randomNumberforProduct.nextInt(prod.size() - 1)))

for (int i=1;i<=2;i++) {
	CustomKeywords.'products.productsFromCatalog.getSpecifiedinStockProductsFromOnePage'(i, prod)
	
}
	
	WebUI.delay(5)
	
WebUI.click(findTestObject('Object Repository/Cart/View Cart'));


WebUI.scrollToPosition(650, 600);
	
	
	//WebUI.waitForElementClickable(findTestObject('Object Repository/Cart/Close item was added to cart dialog'), 5)
	
	
	//WebUI.click(findTestObject('Object Repository/Cart/Close item was added to cart dialog'), FailureHandling.STOP_ON_FAILURE)
}
}
catch (Exception e)
{
e.printStackTrace();
//WebUI.closeBrowser(FailureHandling.STOP_ON_FAILURE);
}