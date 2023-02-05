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

Random randomNumberforProduct = new Random()

int elementIndex;

try {
    WebUI.callTestCase(findTestCase('Test Cases/FE/Cart/Verifications/Cart is filled'), [:], FailureHandling.STOP_ON_FAILURE)

    List Items = CustomKeywords.'cart.cartItems.getProductsInCart'()

    if (Items.size() == 0) {
        WebUI.closeBrowser(FailureHandling.STOP_ON_FAILURE)
    } 
else {
elementIndex = Math.abs(randomNumberforProduct.nextInt(Items.size() - 1))

CustomKeywords.'cart.cartItems.getSpecifiedIteminThecart'(elementIndex, Items)
    }
}



catch (Exception e) {
    e.printStackTrace()

    WebUI.closeBrowser(FailureHandling.STOP_ON_FAILURE) /*Random randomNumberforCatalog = new Random();

Random randomNumberforProduct = new Random();
int elementIndex;
int elementIndexProduct;


try {
	
List Categories = CustomKeywords.'catalog.catlogComponants.getCategoryElements'();
if (Categories.size()==0)
{WebUI.closeBrowser(FailureHandling.STOP_ON_FAILURE)}
else
{
elementIndex= Math.abs((randomNumberforCatalog.nextInt(Categories.size() - 1)))
	
CustomKeywords.'catalog.catlogComponants.getSpecifiedCatalogElement'(elementIndex, Categories)
}
} 
catch (Exception e) {
	e.printStackTrace();
	WebUI.closeBrowser(FailureHandling.STOP_ON_FAILURE);
}

*/
} 

