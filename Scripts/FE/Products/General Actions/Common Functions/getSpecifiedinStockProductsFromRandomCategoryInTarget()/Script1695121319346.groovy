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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

//getSpecifiedinStockProductsFromRandomCategoryInTarget()
//def getSpecifiedinStockProductsFromRandomCategoryInTarget() {
float minimum = GlobalVariable.minimum as float
float maximum=GlobalVariable.maximum as float
//selectCatalogComponents()
//WebUI.callTestCase(findTestCase('FE/menu Items/Select random Catalog'), [:], FailureHandling.STOP_ON_FAILURE)
//WebUI.callTestCase(findTestCase('FE/Scrolling/scrollingAtTheBottom'), [:], FailureHandling.CONTINUE_ON_FAILURE)
List <WebElement> prod = WebUI.callTestCase(findTestCase('Test Cases/FE/menu Items/Select Category contains Product'), [:], FailureHandling.STOP_ON_FAILURE)
//if (prod.size()<=0) {
//	//getSpecifiedinStockProductsFromRandomCategoryInTarget()
//	WebUI.callTestCase(findTestCase('FE/Products/General Actions/Common Functions/getSpecifiedinStockProductsFromRandomCategoryInTarget()'),
//		[:], FailureHandling.STOP_ON_FAILURE)
//	return
//}
//KeywordUtil.logInfo("++++++++++++++++++++++++++++"+prod.size().toString()

boolean found=false

//println(prod.size() + ".................Product Size" + CustomKeywords.'generalactions.generalStrings.getRandomNumberBetweenAnytoAny'(prod.size()-1, 0))

WebElement getRandomElement= prod.get(10)


//prod.any ({
	//KeywordUtil.logInfo("++++++++++++++++++++++++++++"+prod.size().toString())
List <WebElement> a= getRandomElement.findElements(By.xpath("./div[contains(@class,'styles_informationContainer')]/div[contains(@class,'styles_priceContainer')]/span/span[not(contains(@class,'styles_oldPrice__'))]/span/span[1]"))
//	float priceOfSelectedPrudctAmount= currentPrice.getText().replaceAll(",", "").replaceAll(" ", "").toFloat()
println(a.size() + ".......................a.size............")
	WebElement currentPrice = a.get(0)

	String priceText = currentPrice.getText().replaceAll(",", "").replaceAll(" ", "")
	float priceOfSelectedPrudctAmount;
	
	if (!priceText.isEmpty()) {
		priceOfSelectedPrudctAmount = Float.parseFloat(priceText);
	} else {
		WebUI.callTestCase(findTestCase('FE/Products/General Actions/Common Functions/getSpecifiedinStockProductsFromRandomCategoryInTarget()'),
			[:], FailureHandling.STOP_ON_FAILURE)
		return
	}
	
	if(priceOfSelectedPrudctAmount>=minimum & priceOfSelectedPrudctAmount<=maximum) {
		found=true
		//WebElement currentAddToCartBtn=it.findElements(By.xpath("./div/div/button[contains(text(),'أضف إلى السلة') or contains(text(),'Add to Cart')]")).get(0)
		WebElement currentAddToCartBtn=getRandomElement.findElements(By.xpath(".//child::button[contains(text(),'أضف إلى السلة') or contains(text(),'Add to Cart')]")).get(0) //child::button[contains(text(),'أضف إلى السلة') or contains(text(),'Add to Cart')]
		def currentURL = WebUI.getUrl()
		(CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(currentAddToCartBtn))
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(currentAddToCartBtn))
		//utilityFunctions.clickOnObjectusingJavaScript(currentAddToCartBtn)
		WebUI.delay(1)
		if (currentURL.equals(WebUI.getUrl()) & WebUI.verifyElementNotVisible(findTestObject('Object Repository/Cart/Continue Shopping'),FailureHandling.OPTIONAL)  ) {
			return
		}
		//checkOnAddToStoreClickable(currentURL)
		WebUI.callTestCase(findTestCase('FE/Products/General Actions/Common Functions/checkOnAddToStoreClickable(def currentURL)'),
			[('currentURL') : currentURL], FailureHandling.STOP_ON_FAILURE)
		return true
	}else if (priceOfSelectedPrudctAmount < minimum) {
		found=true
		//WebElement currentAddToCartBtn=it.findElements(By.xpath("./div/div/button[contains(text(),'أضف إلى السلة') or contains(text(),'Add to Cart')]")).get(0)
		WebElement currentAddToCartBtn=getRandomElement.findElements(By.xpath(".//child::button[contains(text(),'أضف إلى السلة') or contains(text(),'Add to Cart')]")).get(0)
		def currentURL = WebUI.getUrl()
		(CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(currentAddToCartBtn))
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(currentAddToCartBtn))
		WebUI.delay(1)
		if (currentURL.equals(WebUI.getUrl()) & WebUI.verifyElementNotVisible(findTestObject('Object Repository/Cart/Continue Shopping'),FailureHandling.OPTIONAL)  ) {
			return
		}
		WebUI.callTestCase(findTestCase('FE/Products/General Actions/Common Functions/checkOnAddToStoreClickable(def currentURL)'),
			[('currentURL') : currentURL], FailureHandling.STOP_ON_FAILURE)
		WebUI.callTestCase(findTestCase('FE/Cart/General Actions/View Cart'), [:], FailureHandling.STOP_ON_FAILURE)
		float cartSubTotal = CustomKeywords.'cart.cartItems.getCartSubtotal'()//WebUI.findWebElements(findTestObject('Object Repository/Cart/Cart count'), 10).size()


		if (cartSubTotal == 0) {
			//getSpecifiedinStockProductsFromRandomCategoryInTarget()
			WebUI.callTestCase(findTestCase('FE/Products/General Actions/Common Functions/getSpecifiedinStockProductsFromRandomCategoryInTarget()'),
				[:], FailureHandling.STOP_ON_FAILURE)
			return
		}
		if(WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Cart/insert NeedQTY By text'),30).getAttribute("value") != '1') {
			WebUI.click(findTestObject('Object Repository/Cart/insert NeedQTY By text'))
			WebUI.sendKeys(findTestObject('Object Repository/Cart/insert NeedQTY By text'), Keys.chord(Keys.CONTROL, 'a'))
			WebUI.sendKeys(findTestObject('Object Repository/Cart/insert NeedQTY By text'), Keys.chord(Keys.BACK_SPACE))
			WebUI.sendKeys(findTestObject('Object Repository/Cart/insert NeedQTY By text'), '1', FailureHandling.CONTINUE_ON_FAILURE)
			WebUI.sendKeys(findTestObject('Object Repository/Cart/insert NeedQTY By text'), Keys.chord(Keys.TAB))
		}
		float Total = (((WebUI.getText(findTestObject('Object Repository/Cart/Cart Subtotal (Inc VAT)')).replaceAll(',', '') =~ '\\d+\\.\\d+')[0]) as float)
		
		float lastItemPrice = CustomKeywords.'cart.cartItems.getLastItemPrice'()
		int neededQty=Math.ceil((minimum-Total)/lastItemPrice)+1
		//				for (int i=1; i< neededQty ; i++) {
		//					WebUI.waitForElementClickable(findTestObject('Object Repository/Cart/item plus'), 5)
		//					WebUI.click(findTestObject('Object Repository/Cart/item plus'))
		//					WebUI.delay(1)
		//				}
		KeywordUtil.logInfo(Total.toString()+"\n"+lastItemPrice.toString()+"\n"+neededQty)
		WebUI.verifyElementVisible(findTestObject('Object Repository/Cart/insert NeedQTY By text'), FailureHandling.CONTINUE_ON_FAILURE)
		

		//WebUI.sendKeys(findTestObject(findTestObject('Object Repository/Cart/insert NeedQTY By text')), Keys.chord(Keys.CONTROL, 'a'))
		WebUI.click(findTestObject('Object Repository/Cart/insert NeedQTY By text'))
		WebUI.sendKeys(findTestObject('Object Repository/Cart/insert NeedQTY By text'), Keys.chord(Keys.CONTROL, 'a'))
		//WebUI.sendKeys(findTestObject('Object Repository/Cart/insert NeedQTY By text'), Keys.chord(Keys.BACK_SPACE))
		WebUI.sendKeys(findTestObject('Object Repository/Cart/insert NeedQTY By text'), neededQty.toString(), FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.sendKeys(findTestObject('Object Repository/Cart/insert NeedQTY By text'), Keys.chord(Keys.TAB))
		if(WebUI.waitForElementVisible(findTestObject('Object Repository/Cart/Error-Max qty'), 5, FailureHandling.CONTINUE_ON_FAILURE)) {
			WebUI.click(findTestObject('Object Repository/Cart/insert NeedQTY By text'))
			WebUI.sendKeys(findTestObject('Object Repository/Cart/insert NeedQTY By text'), Keys.chord(Keys.CONTROL, 'a'))
			//WebUI.sendKeys(findTestObject('Object Repository/Cart/insert NeedQTY By text'), Keys.chord(Keys.BACK_SPACE))
			WebUI.sendKeys(findTestObject('Object Repository/Cart/insert NeedQTY By text'), '1', FailureHandling.CONTINUE_ON_FAILURE)
			WebUI.sendKeys(findTestObject('Object Repository/Cart/insert NeedQTY By text'), Keys.chord(Keys.TAB))
			//getSpecifiedinStockProductsFromRandomCategoryInTarget()
		}
		//WebUI.(findTestObject('Object Repository/Cart/insert NeedQTY By text'), neededQty.toString())
		return true
	}else {
		//getSpecifiedinStockProductsFromRandomCategoryInTarget()
		WebUI.callTestCase(findTestCase('FE/Products/General Actions/Common Functions/getSpecifiedinStockProductsFromRandomCategoryInTarget()'),
			[:], FailureHandling.STOP_ON_FAILURE)
		return
	}
//})
/*if(!found) {
	//getSpecifiedinStockProductsFromRandomCategoryInTarget()
	WebUI.callTestCase(findTestCase('FE/Products/General Actions/Common Functions/getSpecifiedinStockProductsFromRandomCategoryInTarget()'),
		[:], FailureHandling.STOP_ON_FAILURE)
}*/
//}