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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

//WebUI.callTestCase(findTestCase('Test Cases/FE/Search/Verification/Verify elemnts for the search'), [:], FailureHandling.STOP_ON_FAILURE)
/*List Catalogs = WebUI.findWebElements(findTestObject('Object Repository/Mega Menu/Catalog list'),30)



int size = Catalogs.size()

if (size > 0) {
for (int i = 0; i < size; i++) {
Catalogs.get(i).click()
}
}
*/
Random randomNumberforProduct = new Random()

Random randomNumber = new Random()

try {
    List Categories = CustomKeywords.'catalog.catlogComponants.getCategoryElements'()

    // int elementIndex = 0
    //int elementIndex= Math.abs((randomNumber.nextInt(Categories.size() - 1)))
    //= Math.abs((randomNumber.nextInt(Categories.size() - 1)))
    for (int elementIndex = 0; elementIndex <= (Categories.size() - 1); elementIndex++) {
        //elementIndex = elementIndex++
		if(elementIndex==0 & GlobalVariable.RunningMode>1) {
			WebUI.callTestCase(findTestCase('FE/Scrolling/scrollingAtTheBottom'), [:], FailureHandling.CONTINUE_ON_FAILURE)
			CustomKeywords.'products.productsFromCatalog.getRandominStockProductsFromOnePage'()
		}
        CustomKeywords.'catalog.catlogComponants.getSpecifiedCatalogElement'(elementIndex, Categories)
		
		WebUI.callTestCase(findTestCase('FE/Scrolling/scrollingAtTheBottom'), [:], FailureHandling.CONTINUE_ON_FAILURE)

        //CustomKeywords.'generalactions.scrolling.scrollingAtTheBottom'()
        //WebUI.delay(5)
        /*
		 * for (int i = 1; i <= 3; i++) { WebUI.scrollToElement(findTestObject('Headers
		 * and Footers/Footer contents/Web footer'), 30,
		 * FailureHandling.CONTINUE_ON_FAILURE) }
		 * 
		 * WebUI.delay(3)
		 * 
		 * for (int i = 1; i <= 4; i++) { WebUI.scrollToElement(findTestObject('Headers
		 * and Footers/Footer contents/Web footer'), 30,
		 * FailureHandling.CONTINUE_ON_FAILURE) }
		 * 
		 * WebUI.delay(2)
		 */
        /*
	 * List prod =
	 * CustomKeywords.'products.productsFromCatalog.getinStockProductFromOnePage'()
	 * //KeywordUtil.markError(prod.get(1)) if(prod.size()==0){
	 * //WebUI.closeBrowser(FailureHandling.STOP_ON_FAILURE)
	 * KeywordUtil.markError("No Products in this Page")
	 * 
	 * } else{ def elementIndexproduct=
	 * Math.abs((randomNumberforProduct.nextInt(prod.size())))
	 * KeywordUtil.logInfo(elementIndexproduct.toString()
	 * +prod.get(elementIndexproduct).toString()) if(elementIndexproduct==0) {
	 * elementIndexproduct=1 }
	 */
        CustomKeywords.'products.productsFromCatalog.getRandominStockProductsFromOnePage'( //WebUI.click(findTestObject('Object Repository/Cart/Continue Shopping'), FailureHandling.CONTINUE_ON_FAILURE)
            //}
            )

    }
}
catch (Exception e) {
    e.printStackTrace()
} 

