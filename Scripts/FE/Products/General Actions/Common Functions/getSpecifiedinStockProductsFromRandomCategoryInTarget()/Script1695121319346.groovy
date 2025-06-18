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
import com.utils.CustomLogger

import internal.GlobalVariable

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import org.openqa.selenium.StaleElementReferenceException

//getSpecifiedinStockProductsFromRandomCategoryInTarget()
//def getSpecifiedinStockProductsFromRandomCategoryInTarget() {
float minimum = GlobalVariable.minimum as float
float maximum=GlobalVariable.maximum as float
//selectCatalogComponents()
//WebUI.callTestCase(findTestCase('FE/menu Items/Select random Catalog'), [:], FailureHandling.STOP_ON_FAILURE)
//WebUI.callTestCase(findTestCase('FE/Scrolling/scrollingAtTheBottom'), [:], FailureHandling.CONTINUE_ON_FAILURE)
List <WebElement> prod = WebUI.callTestCase(findTestCase('Test Cases/FE/menu Items/Select Category contains Product'), [:], FailureHandling.STOP_ON_FAILURE)
/*
 * //if (prod.size()<=0) { //
 * //getSpecifiedinStockProductsFromRandomCategoryInTarget() //
 * WebUI.callTestCase(findTestCase('FE/Products/General Actions/Common
 * Functions/getSpecifiedinStockProductsFromRandomCategoryInTarget()'), // [:],
 * FailureHandling.STOP_ON_FAILURE) // return //}
 *///KeywordUtil.logInfo("++++++++++++++++++++++++++++"+prod.size().toString()
if (prod.size() <= 0 || prod.isEmpty()) {
	//CustomKeywords.'reporting.takeScreenshot'()
    KeywordUtil.markWarning("Empty product list in category")
    return
}
boolean found=false
int maxRetries = 5

for (int retry = 0; retry < maxRetries; retry++) {
	try {
		// Replace hardcoded index with random selection
		int randomIndex = CustomKeywords.'generalactions.generalStrings.getRandomNumberBetweenAnytoAny'(0, prod.size()-1)
		//WebElement productContainer = prod.get(randomIndex)

		// Re-find the product element to avoid stale reference
		WebElement productContainer = refindProductContainer(randomIndex, prod)
		if (productContainer == null) {
			KeywordUtil.logInfo("Product container not found, retrying...")
			continue
		}
		List <WebElement> priceElements= productContainer.findElements(By.xpath("./div[contains(@class,'styles_informationContainer')]/div[contains(@class,'styles_priceContainer')]/span/span[not(contains(@class,'styles_oldPrice__'))]/span/span[1]"))
		
		CustomLogger.logInfo(priceElements.size() + ".......................priceElements.size............")
		
		if (priceElements.isEmpty()) {
			KeywordUtil.logInfo("No price elements found, retrying...")
			continue
		}
		
		WebElement currentPrice = priceElements.get(0)
		String priceText = currentPrice.getText().replaceAll(",", "").replaceAll(" ", "")
		float priceOfSelectedPrudctAmount
		if (!priceText.isEmpty()) {
			priceOfSelectedPrudctAmount = Float.parseFloat(priceText)
		} else {
			KeywordUtil.logInfo("Empty price text, retrying...")
			continue
		}
		
		if(priceOfSelectedPrudctAmount>=minimum & priceOfSelectedPrudctAmount<=maximum) {
			found=true
			WebElement currentAddToCartBtn = findAddToCartButton(productContainer)
			
			if (currentAddToCartBtn == null) {
				KeywordUtil.logInfo("Add to cart button not found, retrying...")
				continue
			}			
			
			// Call add new location with improved error handling
			if (checkNewLocationSafely(currentAddToCartBtn)) {
				def currentURL = GlobalVariable.currentURL
				if (currentURL.equals(WebUI.getUrl()) && WebUI.waitForElementNotVisible(findTestObject('Object Repository/Cart/Continue Shopping'),3,FailureHandling.OPTIONAL)) {
					return
				}
				
				WebUI.callTestCase(findTestCase('FE/Products/General Actions/Common Functions/checkOnAddToStoreClickable(def currentURL)'),
					[('currentURL'): currentURL], FailureHandling.STOP_ON_FAILURE)
				return true
			}
			} else if (priceOfSelectedPrudctAmount < minimum) {
				found = true
				WebElement currentAddToCartBtn = findAddToCartButton(productContainer)
				
				if (currentAddToCartBtn == null) {
					KeywordUtil.logInfo("Add to cart button not found, retrying...")
					continue
				}
				//GlobalVariable.currentURL = WebUI.getUrl()
				
				
				// Call add new location with improved error handling
				if (checkNewLocationSafely(currentAddToCartBtn)) {
					def currentURL = GlobalVariable.currentURL
					if (currentURL.equals(WebUI.getUrl()) & WebUI.waitForElementNotVisible(findTestObject('Object Repository/Cart/Continue Shopping'),3,FailureHandling.OPTIONAL)) {
						return
					}
					
					WebUI.callTestCase(findTestCase('FE/Products/General Actions/Common Functions/checkOnAddToStoreClickable(def currentURL)'),
						[('currentURL') : currentURL], FailureHandling.STOP_ON_FAILURE)
					WebUI.callTestCase(findTestCase('FE/Cart/General Actions/View Cart'), [:], FailureHandling.STOP_ON_FAILURE)
					
					float cartSubTotal = CustomKeywords.'cart.cartItems.getCartSubtotal'()
					
					if (cartSubTotal == 0) {
						//getSpecifiedinStockProductsFromRandomCategoryInTarget()
						WebUI.callTestCase(findTestCase('FE/Products/General Actions/Common Functions/getSpecifiedinStockProductsFromRandomCategoryInTarget()'),
							[:], FailureHandling.STOP_ON_FAILURE)
						return
					}
					// Handle quantity adjustment
					handleQuantityAdjustment(minimum)
					return true
				}
			} else {
            WebUI.callTestCase(findTestCase('FE/Products/General Actions/Common Functions/getSpecifiedinStockProductsFromRandomCategoryInTarget()'),
                [:], FailureHandling.STOP_ON_FAILURE)
            return
        }
        
        // If we reach here, we successfully processed an element, break the retry loop
        break
			

	} catch (StaleElementReferenceException e) {
        KeywordUtil.logInfo("Stale element reference encountered, retry ${retry + 1}/${maxRetries}")
        if (retry == maxRetries - 1) {
            KeywordUtil.markWarning("Max retries reached for stale element handling")
            WebUI.callTestCase(findTestCase('FE/Products/General Actions/Common Functions/getSpecifiedinStockProductsFromRandomCategoryInTarget()'),
                [:], FailureHandling.STOP_ON_FAILURE)
            return
        }
        WebUI.delay(1) // Wait before retry
    } catch (Exception e) {
        KeywordUtil.logInfo("Unexpected error: ${e.getMessage()}")
        if (retry == maxRetries - 1) {
            KeywordUtil.markWarning("Max retries reached due to unexpected error")
            WebUI.callTestCase(findTestCase('FE/Products/General Actions/Common Functions/getSpecifiedinStockProductsFromRandomCategoryInTarget()'),
                [:], FailureHandling.STOP_ON_FAILURE)
            return
        }
    }
}

def refindProductContainer(int randomIndex, List<WebElement> originalProd = null) {
		try {

			if ((originalProd == null || originalProd.isEmpty()) || randomIndex <= 0) {
				// Re-fetch the product list to get fresh elements
				CustomLogger.logInfo("Trying to re-find a product")
				List<WebElement> freshProd = WebUI.callTestCase(findTestCase('Test Cases/FE/menu Items/Select Category contains Product'), [:], FailureHandling.OPTIONAL)
				return freshProd[randomIndex].findElement(By.xpath("./ancestor::div[contains(@class, 'styles_productItem_')][1]"))
			} else {
				// Fallback to original approach if fresh list is not available
				CustomLogger.logInfo("Current product is returned")
				return originalProd[randomIndex].findElement(By.xpath("./ancestor::div[contains(@class, 'styles_productItem_')][1]"))
			}
		} catch (Exception e) {
			KeywordUtil.logInfo("Error refinding product container: ${e.getMessage()}")
			return null
		}
	}
	// Helper method to safely find add to cart button
	def findAddToCartButton(WebElement productContainer) {
		try {
			List<WebElement> addToCartButtons = productContainer.findElements(By.xpath(".//child::button[contains(text(),'أضف إلى السلة') or contains(text(),'Add to Cart')]"))
			return addToCartButtons.isEmpty() ? null : addToCartButtons.get(0)
		} catch (Exception e) {
			KeywordUtil.logInfo("Error finding add to cart button: ${e.getMessage()}")
			return null
		}
	}
	// Improved checkNewLocation method with better error handling
	def checkNewLocationSafely(WebElement element) {
		try {
			// Scroll to element first
			CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(element)
			WebUI.delay(0.5) // Small delay after scrolling
			
			// Try multiple click approaches
			if (clickElementSafely(element)) {
				WebUI.delay(1)
				
				// Handle location workflow if needed
				if ((GlobalVariable.normalEcommerce == null || !GlobalVariable.normalEcommerce) && GlobalVariable.isFirstTime) {
					WebUI.callTestCase(findTestCase('FE/Website launch/Validations/Add locatin New workflow'), [:], FailureHandling.STOP_ON_FAILURE)
					
					// Try clicking again after location workflow
					clickElementSafely(element)
					WebUI.delay(1)
				}
				return true
			}
			return false
			
		} catch (Exception e) {
			KeywordUtil.logInfo("Error in checkNewLocationSafely: ${e.getMessage()}")
			return false
		}
	}
	
	// Helper method to handle quantity adjustment
	def handleQuantityAdjustment(float minimum) {
		try {
			if (WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Cart/insert NeedQTY By text'), 30).getAttribute("value") != '1') {
				WebUI.click(findTestObject('Object Repository/Cart/insert NeedQTY By text'))
				WebUI.sendKeys(findTestObject('Object Repository/Cart/insert NeedQTY By text'), Keys.chord(Keys.CONTROL, 'a'))
				WebUI.sendKeys(findTestObject('Object Repository/Cart/insert NeedQTY By text'), Keys.chord(Keys.BACK_SPACE))
				WebUI.sendKeys(findTestObject('Object Repository/Cart/insert NeedQTY By text'), '1', FailureHandling.CONTINUE_ON_FAILURE)
				WebUI.sendKeys(findTestObject('Object Repository/Cart/insert NeedQTY By text'), Keys.chord(Keys.TAB))
			}
			
			float Total = (((WebUI.getText(findTestObject('Object Repository/Cart/Cart Subtotal (Inc VAT)')).replaceAll(',', '') =~ '\\d+\\.\\d+')[0]) as float)
			float lastItemPrice = CustomKeywords.'cart.cartItems.getLastItemPrice'()
			int neededQty = Math.ceil((minimum - Total) / lastItemPrice) + 1
			
			KeywordUtil.logInfo(Total.toString() + "\n" + lastItemPrice.toString() + "\n" + neededQty)
			WebUI.verifyElementVisible(findTestObject('Object Repository/Cart/insert NeedQTY By text'), FailureHandling.CONTINUE_ON_FAILURE)
			
			WebUI.click(findTestObject('Object Repository/Cart/insert NeedQTY By text'))
			WebUI.sendKeys(findTestObject('Object Repository/Cart/insert NeedQTY By text'), Keys.chord(Keys.CONTROL, 'a'))
			WebUI.sendKeys(findTestObject('Object Repository/Cart/insert NeedQTY By text'), neededQty.toString(), FailureHandling.CONTINUE_ON_FAILURE)
			WebUI.sendKeys(findTestObject('Object Repository/Cart/insert NeedQTY By text'), Keys.chord(Keys.TAB))
			
			if (WebUI.waitForElementVisible(findTestObject('Object Repository/Cart/Error-Max qty'), 5, FailureHandling.CONTINUE_ON_FAILURE)) {
				WebUI.click(findTestObject('Object Repository/Cart/insert NeedQTY By text'))
				WebUI.sendKeys(findTestObject('Object Repository/Cart/insert NeedQTY By text'), Keys.chord(Keys.CONTROL, 'a'))
				WebUI.sendKeys(findTestObject('Object Repository/Cart/insert NeedQTY By text'), '1', FailureHandling.CONTINUE_ON_FAILURE)
				WebUI.sendKeys(findTestObject('Object Repository/Cart/insert NeedQTY By text'), Keys.chord(Keys.TAB))
			}
			
		} catch (Exception e) {
			KeywordUtil.logInfo("Error in quantity adjustment: ${e.getMessage()}")
		}
	}

	// Helper method to click element with multiple fallback approaches
	def clickElementSafely(WebElement element) {
		int maxClickRetries = 2
		
		for (int i = 0; i < maxClickRetries; i++) {
			try {
				// Approach 1: JavaScript click
				GlobalVariable.currentURL = WebUI.getUrl()
				WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(element))
				return true
				
			} catch (StaleElementReferenceException e) {
				KeywordUtil.logInfo("Stale element on click attempt ${i + 1}, trying to re-find...")
				
				// Try to re-find the element using a more stable locator
				try {
					// Re-find the product element to avoid stale reference
					WebElement productContainer = refindProductContainer(0)
					if (productContainer == null) {
						KeywordUtil.logInfo("Product container not found, retrying...")
						continue
					}
					GlobalVariable.currentURL = WebUI.getUrl()
					WebElement freshElement = findAddToCartButton(productContainer)
					WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(freshElement))
					return true
				} catch (Exception refreshError) {
					KeywordUtil.logInfo("Could not re-find element: ${refreshError.getMessage()}")
				}
				
			} catch (Exception e) {
				KeywordUtil.logInfo("Click attempt ${i + 1} failed: ${e.getMessage()}")
				
				if (i == maxClickRetries - 1) {
					// Last attempt: try standard WebUI click
					try {
					WebElement productContainer = refindProductContainer(0)
					if (productContainer == null) {
						KeywordUtil.logInfo("Product container not found, retrying...")
						continue
					}
					GlobalVariable.currentURL = WebUI.getUrl()
					WebElement freshElement = findAddToCartButton(productContainer)
					WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(freshElement))
					return true
					} catch (Exception finalError) {
						KeywordUtil.logInfo("All click attempts failed: ${finalError.getMessage()}")
						return false
					}
				}
			}
			
			WebUI.delay(0.5) // Wait before retry
		}
		
		return false
	}