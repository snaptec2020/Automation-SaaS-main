package checkout
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.apache.commons.lang3.StringUtils
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration as RC
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import generalactions.generalActions
import generalactions.generalStrings
import internal.GlobalVariable
import utility.Utility
import com.utils.CustomLogger

public class EnhancedPayments {
	TestObject tb = new TestObject();
	Random randomNumberforProduct = new Random()
	def utilityFunctions = new Utility()
	def generalStrings = new generalStrings()
	def generalActions = new generalActions()
	def executionProfile = RC.getExecutionProfile()

	@Keyword
	def getPaymentMethodsList() {
		CustomLogger.logInfo("=== Getting Payment Methods List ===")
		List Paymentlist = WebUI.findWebElements(findTestObject('Object Repository/Check Out/Payment methods list'), 0)
		CustomLogger.logInfo("Found ${Paymentlist.size()} payment methods")
		return Paymentlist
	}

	@Keyword
	def getRandomPaymentMethod() {
		CustomLogger.logInfo("=== Starting Random Payment Method Selection ===")
		List paymentMethods = getPaymentMethodsList()
		if (paymentMethods.size() == 0) {
			CustomLogger.logInfo("No payment methods found on this page")
			KeywordUtil.markPassed("No Payment Methods in this Page")
		} else {
			int randomIndex = generalStrings.getRandomNumberBetweenOnetoTarget(paymentMethods.size())
			CustomLogger.logInfo("Selected random payment method at index: ${randomIndex} out of ${paymentMethods.size()} methods")
			WebUI.click(utilityFunctions.addXpathToTestObject(utilityFunctions.addXpathToTestObject("("+findTestObject('Object Repository/Check Out/Payment methods list').findPropertyValue('xpath') + ")["+randomIndex+"]")))
			CustomLogger.logInfo("Clicked on random payment method, proceeding to place order")
			placeOrder()
		}
		CustomLogger.logInfo("=== Completed Random Payment Method Selection ===")
	}

	@Keyword
	def paymentMethodToPayBySelectedMethod(int selectedIndex, def expectedPaymentMethods /*= 'Default'*/) {
		CustomLogger.logInfo("=== Starting Payment Method Selection ===")
		CustomLogger.logInfo("Input parameters - selectedIndex: ${selectedIndex}, expectedPaymentMethods: ${expectedPaymentMethods}")
		
		if (selectedIndex == 0) {
			selectedIndex = 1
			CustomLogger.logInfo("Adjusted selectedIndex from 0 to 1")
		}
		
		// Ensure expectedPaymentMethods is always a list
		def paymentMethodList = expectedPaymentMethods instanceof List ?
				expectedPaymentMethods : [expectedPaymentMethods]
		CustomLogger.logInfo("Payment method list: ${paymentMethodList}")

		// Create a combined regex pattern
		def combinedPattern = paymentMethodList.collect { it.toString() }.join('|')
		def paymentMethodPattern = ~/${combinedPattern}/
		CustomLogger.logInfo("Created regex pattern: ${combinedPattern}")

		// Check payment method type and handle accordingly
		CustomLogger.logInfo("Checking payment method type for: ${expectedPaymentMethods.toString()}")

		if(expectedPaymentMethods.toString() =~ /Tabby/) {
			CustomLogger.logInfo("=== TABBY Payment Method Detected ===")
			if(WebUI.waitForElementClickable(findTestObject('Check Out/Tabby Button'), 5)) {
				CustomLogger.logInfo("Tabby button is clickable, proceeding to click")
				utilityFunctions.clickOnObjectusingJavaScript(findTestObject('Check Out/Tabby Button'))
				//WebUI.click(findTestObject('Check Out/Tabby Button'));
				
				def expectedPattern
				def languageMode = GlobalVariable.languageMode
				CustomLogger.logInfo("Language mode: ${languageMode}")

				if (languageMode.equalsIgnoreCase("en")) {
					expectedPattern = ~/\d+ interest-free payments/
					CustomLogger.logInfo("Using English pattern for Tabby")
				} else {
					expectedPattern = ~/قسّمها على \d+ بدون رسوم ولا فوائد/
					CustomLogger.logInfo("Using Arabic pattern for Tabby")
				}
				
				CustomLogger.logInfo("Verifying Tabby message with pattern: ${expectedPattern}")
				verifyMessageMatching(expectedPattern, 'Check Out/payment Method Text');
				WebUI.verifyElementVisible(findTestObject('Check Out/Tabby Card'), FailureHandling.CONTINUE_ON_FAILURE)
				CustomLogger.logInfo("Tabby setup complete, proceeding to place order")
				placeOrder(expectedPaymentMethods)
			} else {
				CustomLogger.logInfo("Tabby button not clickable within 5 seconds")
			}
		}else if(expectedPaymentMethods.toString() =~ /Tamara/){
			CustomLogger.logInfo("=== TAMARA Payment Method Detected ===")
			utilityFunctions.clickOnObjectusingJavaScript(findTestObject('Check Out/Tamara Img'))
			//WebUI.click(findTestObject('Check Out/Tamara Img'));
			CustomLogger.logInfo("Clicked on Tamara image, proceeding to place order")
			placeOrder(expectedPaymentMethods)
		}else if(expectedPaymentMethods.toString() =~ /COD/){
			CustomLogger.logInfo("=== COD (Cash on Delivery) Payment Method Detected ===")
			// Fix: Changed log message and element click
			utilityFunctions.clickOnObjectusingJavaScript(findTestObject('Check Out/COD img')) // Assuming COD has its own button
			//WebUI.click(findTestObject('Check Out/COD Button')); // Assuming COD has its own button
			CustomLogger.logInfo("Clicked on COD button, proceeding to place order")
			placeOrder(expectedPaymentMethods)
		}else {
			CustomLogger.logInfo("=== Generic Payment Method Selection ===")
			tb = utilityFunctions.addXpathToTestObject("("+findTestObject('Object Repository/Check Out/Payment methods list').findPropertyValue('xpath') + ")["+selectedIndex+"]")
			CustomLogger.logInfo("Created test object for payment method at index: ${selectedIndex}")
			
			utilityFunctions.clickOnObjectusingJavaScript(tb)
			CustomLogger.logInfo("Clicked on payment method using JavaScript")
			
			if (WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/payment Method Text'), 3)) {
				String paymentMethod = WebUI.getText(findTestObject('Object Repository/Check Out/payment Method Text'))
				CustomLogger.logInfo("Retrieved payment method text: '${paymentMethod}'")
				CustomLogger.logInfo("Checking against pattern: ${paymentMethodPattern}")
				
				if (paymentMethod ==~ paymentMethodPattern) {
					CustomLogger.logInfo("Payment method matches expected pattern, proceeding to place order")
					placeOrder(paymentMethod)
				} else {
					CustomLogger.logInfo("Payment method does not match expected pattern")
					CustomLogger.logInfo("Expected pattern: ${combinedPattern}, Actual: ${paymentMethod}")
					return
				}
			} else {
				CustomLogger.logInfo("Payment method text not visible within 3 seconds")
			}
		}
		CustomLogger.logInfo("=== Completed Payment Method Selection ===")
	}

	def placeOrder(def expectedPaymentMethod /*= 'Default'*/) {
		CustomLogger.logInfo("=== Starting Place Order Process ===")
		CustomLogger.logInfo("Expected payment method: ${expectedPaymentMethod}")
		
		try {
			// Get grand total before placing order
			String grandTotalText = WebUI.getText(findTestObject('Object Repository/Check Out/Grand Total'))
			CustomLogger.logInfo("Grand total text retrieved: '${grandTotalText}'")
			
			double grandTotal = (grandTotalText.replaceAll(",", "") =~ /\d+\.\d+/)[0] as double
			CustomLogger.logInfo("Parsed grand total: ${grandTotal}")
			
			CustomLogger.logInfo("Clicking 'Place Order' button")
			WebUI.click(findTestObject('Object Repository/Check Out/Place order check out button'))
			
			if(!expectedPaymentMethod.toString() =~ /Tabby/) {
				CustomLogger.logInfo("Non-Tabby payment, waiting for spinner to hide")
				generalActions.waiteSpinnerToHide()
			} else {
				CustomLogger.logInfo("Tabby payment detected, skipping spinner wait")
			}
			
			CustomLogger.logInfo("Proceeding to handle payment method: ${expectedPaymentMethod}")
			handlePaymentMethod(expectedPaymentMethod, grandTotal)
			
		} catch (Exception e) {
			CustomLogger.logInfo("Exception occurred in placeOrder: ${e.getMessage()}")
			e.printStackTrace()
			CustomLogger.logInfo("Navigating back to main URL due to exception")
			WebUI.navigateToUrl(GlobalVariable.URL, FailureHandling.CONTINUE_ON_FAILURE)
		}
		CustomLogger.logInfo("=== Completed Place Order Process ===")
	}

	private void handlePaymentMethod(String paymentMethod, double grandTotal) {
		CustomLogger.logInfo("=== Handling Payment Method ===")
		CustomLogger.logInfo("Payment method: '${paymentMethod}', Grand total: ${grandTotal}")
		
		switch (paymentMethod) {
			case ~('قسّمها على 4 بدون رسوم ولا فوائد'):
			case ~('4 interest-free payments'):
			case ~('Tabby'):
				CustomLogger.logInfo("Matched Tabby payment pattern")
				handleTabbyPayment(grandTotal)
				break
			case ~('مدى/فيزا/ماستر كارد/آبل باي'):
			case ~('Mada/ Visa/Masster Card/Apple pay'):
				CustomLogger.logInfo("Matched Telr payment pattern")
				handleTelrPayment(grandTotal)
				break
			case ~('الدفع عند الإستلام'):
			case ~('Cash On Delivery'):
			case ~('COD'):
				CustomLogger.logInfo("Matched COD payment pattern")
				handleCashOnDeliveryPayment()
				break
			case ~('قسم فاتورتك على \\d دفعات بدون فوائد'):
			case ~('Split in \\d payments without interest or hidden fees'):
			case ~('Tamara'):
				CustomLogger.logInfo("Matched Tamara payment pattern")
				handleTamaraPayment()
				break
			default:
				CustomLogger.logInfo("No specific payment pattern matched, using default handling")
				break
		}
		CustomLogger.logInfo("=== Completed Payment Method Handling ===")
	}

	private void handleTabbyPayment(double grandTotal) {
		CustomLogger.logInfo("=== Starting Tabby Payment Handling ===")
		CustomLogger.logInfo("Expected grand total: ${grandTotal}")
		
		WebUI.waitForElementVisible(findTestObject('Check Out/Tabby Screen'), 0)
		CustomLogger.logInfo("Tabby screen is now visible")
		
		def tabbyURL = WebUI.getUrl()
		CustomLogger.logInfo("Current URL: ${tabbyURL}")
		
		WebUI.verifyMatch(tabbyURL, 'https://checkout\\.tabby\\.ai/auth\\?sessionId=.*', true)
		CustomLogger.logInfo("URL verification passed for Tabby checkout")
		
		CustomLogger.logInfo("Proceeding to close Tabby payment")
		closeTabbyPayment(grandTotal)
		CustomLogger.logInfo("=== Completed Tabby Payment Handling ===")
	}

	private void handleTelrPayment(double grandTotal) {
		CustomLogger.logInfo("=== Starting Telr Payment Handling ===")
		CustomLogger.logInfo("Expected grand total: ${grandTotal}")
		
		WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/Telr Grand Total'), 0)
		CustomLogger.logInfo("Telr grand total element is visible")
		
		String telrTotalText = WebUI.getText(findTestObject('Object Repository/Check Out/Telr Grand Total'))
		CustomLogger.logInfo("Telr total text: '${telrTotalText}'")
		
		double telrTotal = (telrTotalText.replaceAll(",", "") =~ /\d+\.\d+/)[0] as double
		CustomLogger.logInfo("Parsed Telr total: ${telrTotal}")
		
		WebUI.verifyEqual(grandTotal, telrTotal, FailureHandling.CONTINUE_ON_FAILURE)
		CustomLogger.logInfo("Grand total verification: Expected=${grandTotal}, Actual=${telrTotal}")
		
		CustomLogger.logInfo("Proceeding to close Telr payment")
		closeTelrPayment()
		CustomLogger.logInfo("=== Completed Telr Payment Handling ===")
	}

	private void handleCashOnDeliveryPayment() {
		CustomLogger.logInfo("=== Starting COD Payment Handling ===")
		CustomLogger.logInfo("Execution profile: ${executionProfile}")
		
		if (StringUtils.indexOfIgnoreCase(executionProfile, "-Live") > 0) {
			CustomLogger.logInfo("Live environment detected, skipping COD verification")
			return
		} else {
			CustomLogger.logInfo("Non-live environment, proceeding with COD verification")
			WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/COD Success'), 10)
			CustomLogger.logInfo("COD success element is visible")
			
			CustomLogger.logInfo("Verifying order confirmation")
			verifyOrderConfirmation();
		}
		CustomLogger.logInfo("=== Completed COD Payment Handling ===")
	}

	private void handleTamaraPayment() {
		CustomLogger.logInfo("=== Starting Tamara Payment Handling ===")
		
		WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/Telr Grand Total'), 0)
		CustomLogger.logInfo("Tamara grand total element is visible")
		
		String tamaraURL = WebUI.getUrl()
		CustomLogger.logInfo("Current Tamara URL: ${tamaraURL}")
		
		WebUI.verifyMatch(tamaraURL, '^https://checkout.tamara.co/.*', true, FailureHandling.CONTINUE_ON_FAILURE)
		CustomLogger.logInfo("Tamara URL verification completed")
		
		CustomLogger.logInfo("Proceeding to close Tamara payment")
		closeTamaraPayment()
		CustomLogger.logInfo("=== Completed Tamara Payment Handling ===")
	}

	private void fillCreditCardDetails() {
		CustomLogger.logInfo("=== Filling Credit Card Details ===")
		
		CustomLogger.logInfo("Switching to card number iframe")
		utilityFunctions.switchToIframeByXpath("//iframe[@id='cardNumber']")
		WebUI.sendKeys(utilityFunctions.addXpathToTestObject("//input[@name='cardnumber']"), '4440000009900010')
		CustomLogger.logInfo("Card number entered")
		WebUI.switchToDefaultContent()
		
		CustomLogger.logInfo("Switching to CVV iframe")
		utilityFunctions.switchToIframeByXpath("//iframe[@id='cvv']")
		WebUI.sendKeys(utilityFunctions.addXpathToTestObject("//input[@class='cvv field']"), '123')
		CustomLogger.logInfo("CVV entered")
		WebUI.switchToDefaultContent()
		
		CustomLogger.logInfo("Switching to expiry date iframe")
		utilityFunctions.switchToIframeByXpath("//iframe[@id='expiryDate']")
		WebUI.sendKeys(utilityFunctions.addXpathToTestObject("//input[@class='expiry-date field']"), '0226')
		CustomLogger.logInfo("Expiry date entered")
		WebUI.switchToDefaultContent()
		
		CustomLogger.logInfo("=== Credit Card Details Filled ===")
	}

	private void closeTabbyPayment(double grandTotal) {
		CustomLogger.logInfo("=== Starting Tabby Payment Closure ===")
		CustomLogger.logInfo("Running mode: ${GlobalVariable.RunningMode}")
		
		switch (GlobalVariable.RunningMode) {
			case '1':
				CustomLogger.logInfo("Running mode 1 - Desktop flow")
				tb = findTestObject('Object Repository/Check Out/Close payment method/Tabby back to Store');
				CustomLogger.logInfo("Waiting for 'Back to Store' button: ${tb.getObjectId()}")
				WebUI.waitForElementVisible(tb, 0)
				CustomLogger.logInfo("Clicking 'Back to Store' button")
				WebUI.click(tb)
				
				tb = findTestObject('Object Repository/Check Out/Close payment method/Tabby Cancel')
				CustomLogger.logInfo("Waiting for Tabby Cancel button: ${tb.getObjectId()}")
				WebUI.waitForElementVisible(tb, 0)
				CustomLogger.logInfo("Clicking Tabby Cancel button")
				WebUI.click(tb)
				break
				
			case '2':
				CustomLogger.logInfo("Running mode 2 - Mobile flow")
				WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/Close payment method/Tabby close icon Phone'), 0)
				CustomLogger.logInfo("Tabby close icon (phone) is visible, clicking")
				WebUI.click(findTestObject('Object Repository/Check Out/Close payment method/Tabby close icon Phone'))
				
				WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/Close payment method/Tabby Cancel'), 0)
				CustomLogger.logInfo("Tabby Cancel button is visible, clicking")
				WebUI.click(findTestObject('Object Repository/Check Out/Close payment method/Tabby Cancel'))
				break
				
			default:
				CustomLogger.logInfo("Unknown running mode: ${GlobalVariable.RunningMode}")
				break
		}
		
		CustomLogger.logInfo("Waiting for failed order check element")
		WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/Close payment method/Fail order check'), 0)
		CustomLogger.logInfo("Taking screenshot of Tabby result")
		WebUI.takeFullPageScreenshot('./TabbyOrderResult.png')
		
		CustomLogger.logInfo("Verifying cart is not empty after Tabby cancellation")
		int cartItems = utilityFunctions.checkIfElementExist('Object Repository/Cart/Items in cart')
		WebUI.verifyNotEqual(cartItems, 0, FailureHandling.CONTINUE_ON_FAILURE)
		CustomLogger.logInfo("Cart items count: ${cartItems}")
		
		CustomLogger.logInfo("Getting grand total after Tabby closure")
		String grandTotalAfterCloseText = WebUI.getText(findTestObject('Object Repository/Cart/Cart Subtotal (Inc VAT)'), FailureHandling.STOP_ON_FAILURE)
		double grandTotalAfterClose = (grandTotalAfterCloseText.replaceAll(",", "") =~ /\d+\.\d+/)[0] as double
		CustomLogger.logInfo("Grand total after close: ${grandTotalAfterClose}, Original: ${grandTotal}")
		WebUI.verifyEqual(grandTotal, grandTotalAfterClose, FailureHandling.STOP_ON_FAILURE);
		
		CustomLogger.logInfo("=== Completed Tabby Payment Closure ===")
	}

	private void closeTelrPayment() {
		CustomLogger.logInfo("=== Starting Telr Payment Closure ===")
		
		WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/Close payment method/Cancel Telr'), 0)
		CustomLogger.logInfo("Telr cancel button is visible, clicking")
		WebUI.click(findTestObject('Object Repository/Check Out/Close payment method/Cancel Telr'))
		
		WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/Close payment method/Fail order check'), 0)
		CustomLogger.logInfo("Failed order check is visible, taking screenshot")
		WebUI.takeFullPageScreenshot('./TelrOrderResult.png')
		
		CustomLogger.logInfo("=== Completed Telr Payment Closure ===")
	}

	private void closeTamaraPayment() {
		CustomLogger.logInfo("=== Starting Tamara Payment Closure ===")
		
		tb = findTestObject('Object Repository/Check Out/Close payment method/Tamara close icon')
		CustomLogger.logInfo("Waiting for Tamara close icon: ${tb.getObjectId()}")
		WebUI.waitForElementVisible(tb, 0)
		CustomLogger.logInfo("Clicking Tamara close icon")
		WebUI.click(tb)
		
		tb = findTestObject('Object Repository/Check Out/Close payment method/Tamara cancel button')
		CustomLogger.logInfo("Waiting for Tamara cancel button: ${tb.getObjectId()}")
		WebUI.waitForElementVisible(tb, 0)
		CustomLogger.logInfo("Clicking Tamara cancel button")
		WebUI.click(tb)
		
		WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/Close payment method/Fail order check'), 0)
		CustomLogger.logInfo("Failed order check is visible, taking screenshot")
		WebUI.takeFullPageScreenshot('./TamaraOrderResult.png')
		
		CustomLogger.logInfo("=== Completed Tamara Payment Closure ===")
	}

	def verifyOrderConfirmation() {
		CustomLogger.logInfo("=== Starting Order Confirmation Verification ===")
		
		def actualText = WebUI.getText(findTestObject('Check Out/Success Order message'))
		CustomLogger.logInfo("Retrieved success message: '${actualText}'")

		def expectedPattern = 'تم اتمام طلبك رقم "\\d+" بنجاح'
		def languageMode = GlobalVariable.languageMode
		CustomLogger.logInfo("Language mode: ${languageMode}")

		if (languageMode.equalsIgnoreCase("en")) {
			expectedPattern = 'Your order number "\\d+" was completed successfully'
			CustomLogger.logInfo("Using English pattern for order confirmation")
		} else {
			CustomLogger.logInfo("Using Arabic pattern for order confirmation")
		}
		CustomLogger.logInfo("Expected pattern: ${expectedPattern}")

		if (actualText =~ expectedPattern) {
			CustomLogger.logInfo("Order confirmation message format is correct")
			KeywordUtil.markPassed("Message format is correct: ${actualText}")

			def matcher = actualText =~ /"(\d+)"/
			if (matcher.find()) {
				def orderNumber = matcher.group(1)
				CustomLogger.logInfo("Extracted order number: ${orderNumber}")
				KeywordUtil.logInfo("Order number found: ${orderNumber}")
			} else {
				CustomLogger.logInfo("No order number found in the message")
			}
		} else {
			CustomLogger.logInfo("Order confirmation message format is incorrect")
			KeywordUtil.markFailed("Message format is incorrect. Expected pattern: ${expectedPattern}, Actual text: ${actualText}")
		}
		
		CustomLogger.logInfo("Clicking 'Continue Shopping' button")
		WebUI.click(findTestObject('Check Out/continue Shoping Button'))
		
		CustomLogger.logInfo("=== Completed Order Confirmation Verification ===")
	}

	def verifyMessageMatching(def expectedPattern, def testObject) {
		CustomLogger.logInfo("=== Starting Message Verification ===")
		CustomLogger.logInfo("Expected pattern: ${expectedPattern}")
		CustomLogger.logInfo("Test object: ${testObject}")
		
		def actualText = WebUI.getText(findTestObject(testObject))
		CustomLogger.logInfo("Retrieved actual text: '${actualText}'")

		if (actualText =~ expectedPattern) {
			CustomLogger.logInfo("Message format verification passed")
			KeywordUtil.markPassed("Message format is correct: ${actualText}")

			def matcher = actualText =~ /"(\d+)"/
			if (matcher.find()) {
				def orderNumber = matcher.group(1)
				CustomLogger.logInfo("Found number in message: ${orderNumber}")
				KeywordUtil.logInfo("Order number found: ${orderNumber}")
			}
		} else {
			CustomLogger.logInfo("Message format verification failed")
			KeywordUtil.markFailed("Message format is incorrect. Expected pattern: ${expectedPattern}, Actual text: ${actualText}")
		}
		
		CustomLogger.logInfo("=== Completed Message Verification ===")
	}
}