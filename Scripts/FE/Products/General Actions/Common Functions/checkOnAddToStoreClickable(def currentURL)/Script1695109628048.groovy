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

KeywordUtil.logInfo(WebUI.getUrl())

KeywordUtil.logInfo(currentURL)

//if(WebUI.verifyElementVisible(findTestObject('Object Repository/Cart/Continue Shopping'), FailureHandling.CONTINUE_ON_FAILURE)) {
if (WebUI.getUrl() == currentURL) {
    WebUI.click(findTestObject('Object Repository/Cart/Continue Shopping'), FailureHandling.CONTINUE_ON_FAILURE) //WebUI.scrollToElement(findTestObject('Object Repository/Products/Product content'), 0, FailureHandling.STOP_ON_FAILURE)
    //KeywordUtil.logInfo(">>>>>>>>>>>>>>>>>>>>>>>>>")
    //WebUI.executeJavaScript("window.scrollTo(0, document.body.scrollHeight/2);", null);
    //WebUI.click(findTestObject(testObjectPath))
    //KeywordUtil.logInfo(">>>>>>>>>>>>>>>>>>>>>> end configurable")
    //tb.addProperty('xpath', ConditionType.EQUALS, "//*[@class='product-content__button-wrapper']//button[@class='product-content__cart']")
    //KeywordUtil.logInfo(WebUI.waitForElementClickable(tb, 10, FailureHandling.CONTINUE_ON_FAILURE).toString())
    //KeywordUtil.logInfo(">>>>>>>>>>>>>>>>>>>>>> 1")
    //scrollingActions.scrollToClick(tb)
    //configurableProduct()
} else {
    KeywordUtil.markPassed('Trying to Get Configurable product')

    tb = CustomKeywords.'utility.Utility.addXpathToTestObject'('//*[@class=\'product-content__button-wrapper\']//button[@class=\'product-content__cart\'] | //button[starts-with(@class,\'styles_cartWrapper__\')]')

    for (int i = 5; i > 0; i--) {
        if (WebUI.waitForElementVisible(tb, 5, FailureHandling.CONTINUE_ON_FAILURE)) {
            break
        }
        
        WebUI.executeJavaScript(('window.scrollTo(0, document.body.scrollHeight/' + i.toString()) + ');', null)
    }
    
    WebUI.callTestCase(findTestCase('FE/Products/General Actions/Common Functions/configurableProduct()'), [:], FailureHandling.STOP_ON_FAILURE)

    try {
        WebUI.click(tb)

        if (WebUI.waitForElementVisible(findTestObject('Object Repository/Cart/Continue Shopping'), 5, FailureHandling.CONTINUE_ON_FAILURE)) {
            WebUI.click(findTestObject('Object Repository/Cart/Continue Shopping'), FailureHandling.CONTINUE_ON_FAILURE)
        }
    }
    catch (Exception e) {
        for (int i = 5; i > 0; i--) {
            if (WebUI.waitForElementVisible(findTestObject('Object Repository/Cart/Continue Shopping'), 2, FailureHandling.CONTINUE_ON_FAILURE) && 
            (i != 5)) {
                WebUI.click(findTestObject('Object Repository/Cart/Continue Shopping'), FailureHandling.CONTINUE_ON_FAILURE)

                break
            }
            
            WebUI.executeJavaScript(('window.scrollTo(0, document.body.scrollHeight/' + i.toString()) + ');', null)
			
            WebUI.click(tb, FailureHandling.OPTIONAL)
        }
        
        e.printStackTrace()

        //getRandominStockProductsFromRandomCategory()

        WebUI.callTestCase(findTestCase('FE/Products/General Actions/Common Functions/getRandominStockProductsFromRandomCategory()'), 
            [:], FailureHandling.STOP_ON_FAILURE)
    } 
}

