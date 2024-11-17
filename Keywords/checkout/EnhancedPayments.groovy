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
		List Paymentlist = WebUI.findWebElements(findTestObject('Object Repository/Check Out/Payment methods list'), 0)
		return Paymentlist
	}

	@Keyword
	def getRandomPaymentMethod() {
		List paymentMethods = getPaymentMethodsList()
		if (paymentMethods.size() == 0) {
			KeywordUtil.markPassed("No Payment Methods in this Page")
		} else {
			int randomIndex = generalStrings.getRandomNumberBetweenOnetoTarget(paymentMethods.size())
			WebUI.click(utilityFunctions.addXpathToTestObject(utilityFunctions.addXpathToTestObject("("+findTestObject('Object Repository/Check Out/Payment methods list').findPropertyValue('xpath') + ")["+randomIndex+"]")))
			placeOrder()
		}
	}

	@Keyword
	def paymentMethodToPayBySelectedMethod(int selectedIndex,def expectedPaymentMethod = 'Default') {

		if (selectedIndex == 0) {
			selectedIndex = 1
		}
		if(expectedPaymentMethod.equalsIgnoreCase('Tabby') && WebUI.waitForElementClickable(findTestObject('Check Out/Tabby Button'), 5)) {
			WebUI.click(findTestObject('Check Out/Tabby Button'));
			def expectedPattern = 'قسّمها على \\d+ بدون رسوم ولا فوائد'
			def languageMode = GlobalVariable.languageMode

			if (languageMode.equalsIgnoreCase("en")) {
				expectedPattern = '\\d+ interest-free payments'
			}
			verifyMessageMatching(expectedPattern, 'Check Out/payment Method Text');
			WebUI.verifyElementVisible(findTestObject('Check Out/Tabby Card'), FailureHandling.CONTINUE_ON_FAILURE)
			placeOrder(expectedPaymentMethod)
		}else if(expectedPaymentMethod.equalsIgnoreCase('Tamara')){
			CustomLogger.logInfo("Tammara Selected")
			WebUI.click(findTestObject('Check Out/Tamara Img'));
			placeOrder(expectedPaymentMethod)
		}else {
			tb = utilityFunctions.addXpathToTestObject("("+findTestObject('Object Repository/Check Out/Payment methods list').findPropertyValue('xpath') + ")["+selectedIndex+"]")
			utilityFunctions.clickOnObjectusingJavaScript(tb)
			if (WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/payment Method Text'), 3)) {
				String paymentMethod = WebUI.getText(findTestObject('Object Repository/Check Out/payment Method Text'))
				if (paymentMethod ==~ expectedPaymentMethod /*paymentMethod.equalsIgnoreCase(expectedPaymentMethod)*/ || expectedPaymentMethod.equalsIgnoreCase('Default')) {
					placeOrder(expectedPaymentMethod)
				} else {
					//KeywordUtil.logInfo("Expected payment method: $expectedPaymentMethod, Actual payment method: $paymentMethod")
					return

				}
			}
		}
	}

	def placeOrder(String expectedPaymentMethod = 'Default') {
		try {
			// def nonVisa = WebUiCommonHelper.findWebElement(utilityFunctions.addXpathToTestObject("//div[contains(@class,'payment-method v2')]/following-sibling::div"), 30).getAttribute("class")
			//if (nonVisa != 'checkout-com-form-container') {
			//if (WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/payment Method Text'), 5)) {

			//if (paymentMethod.equalsIgnoreCase(expectedPaymentMethod) || paymentMethod.equalsIgnoreCase('Default')) {
			double grandTotal = (WebUI.getText(findTestObject('Object Repository/Check Out/Grand Total')).replaceAll(",", "") =~ /\d+\.\d+/)[0] as double
			WebUI.click(findTestObject('Object Repository/Check Out/Place order check out button'))
			if(!expectedPaymentMethod.equalsIgnoreCase('Tabby')) {
				generalActions.waiteSpinnerToHide()
			}
			handlePaymentMethod(expectedPaymentMethod, grandTotal)
			//	                } else {
			//	                    KeywordUtil.logInfo("Expected payment method: $expectedPaymentMethod, Actual payment method: $paymentMethod")
			//	                    return
			//	                }
			//}
			/*} /*else {
			 WebUI.verifyElementVisible(findTestObject('Object Repository/Check Out/Place order check out button'))
			 fillCreditCardDetails()
			 WebUI.click(findTestObject('Object Repository/Check Out/Place order check out button'))
			 WebUI.delay(10)
			 }*/
		} catch (Exception e) {
			e.printStackTrace()
			WebUI.navigateToUrl(GlobalVariable.URL, FailureHandling.CONTINUE_ON_FAILURE)
		}
	}

	private void handlePaymentMethod(String paymentMethod, double grandTotal) {
		KeywordUtil.logInfo(paymentMethod)
		switch (paymentMethod) {

			case ~('قسّمها على 4 بدون رسوم ولا فوائد'):
			case ~('4 interest-free payments'):
			case ~('Tabby'):
				CustomLogger.logInfo("Start handling Tabby")
				handleTabbyPayment(grandTotal)
				break
			case ~('مدى/فيزا/ماستر كارد/آبل باي'):
			case ~('Mada/ Visa/Masster Card/Apple pay'):
				handleTelrPayment(grandTotal)
				break
			case ~('الدفع عند الإستلام'):
			case ~('الدفع عند الاستلام'):
			case ~('Cash On Delivery'):
				CustomLogger.logInfo("Start handling COD")
				handleCashOnDeliveryPayment()
				break
			case ~('قسم فاتورتك على \\d دفعات بدون فوائد'):
			case ~('Split in \\d payments without interest or hidden fees'):
			case ~('Tamara'):
				CustomLogger.logInfo("Start handling Tamara")
				handleTamaraPayment()
				break
		}
	}

	private void handleTabbyPayment(double grandTotal) {
		WebUI.waitForElementVisible(findTestObject('Check Out/Tabby Screen'), 0)
		//double tabbyTotal = (WebUI.getText(findTestObject('Object Repository/Check Out/TabbyTotal')).replaceAll(",", "") =~ /\d+\.\d+/)[0] as double
		//WebUI.verifyEqual(grandTotal, tabbyTotal, FailureHandling.CONTINUE_ON_FAILURE)
		def tabbyURL = WebUI.getUrl()
		WebUI.verifyMatch(tabbyURL, 'https://checkout\\.tabby\\.ai/auth\\?sessionId=.*', true)
		closeTabbyPayment(grandTotal)
	}

	private void handleTelrPayment(double grandTotal) {
		WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/Telr Grand Total'), 0)
		double telrTotal = (WebUI.getText(findTestObject('Object Repository/Check Out/Telr Grand Total')).replaceAll(",", "") =~ /\d+\.\d+/)[0] as double
		WebUI.verifyEqual(grandTotal, telrTotal, FailureHandling.CONTINUE_ON_FAILURE)
		closeTelrPayment()
	}

	private void handleCashOnDeliveryPayment() {
		KeywordUtil.logInfo(executionProfile.toString())
		if (StringUtils.indexOfIgnoreCase(executionProfile, "-Live") > 0) {
			return
		} else {
			WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/COD Success'), 10)
			//WebUI.takeFullPageScreenshot('./CODOrderResult.png')
			verifyOrderConfirmation();
		}
	}

	private void handleTamaraPayment() {
		CustomLogger.logInfo("Waiting Tamar Grand Total to visible")
		WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/Telr Grand Total'), 0)
		String tamaraURL = WebUI.getUrl()
		CustomLogger.logInfo("Tamara URL is ${tamaraURL} --> and veryfying it")
		WebUI.verifyMatch(tamaraURL, '^https://checkout.tamara.co/.*', true, FailureHandling.CONTINUE_ON_FAILURE)
		closeTamaraPayment()
	}

	private void fillCreditCardDetails() {
		utilityFunctions.switchToIframeByXpath("//iframe[@id='cardNumber']")
		WebUI.sendKeys(utilityFunctions.addXpathToTestObject("//input[@name='cardnumber']"), '4440000009900010')
		WebUI.switchToDefaultContent()
		utilityFunctions.switchToIframeByXpath("//iframe[@id='cvv']")
		WebUI.sendKeys(utilityFunctions.addXpathToTestObject("//input[@class='cvv field']"), '123')
		WebUI.switchToDefaultContent()
		utilityFunctions.switchToIframeByXpath("//iframe[@id='expiryDate']")
		WebUI.sendKeys(utilityFunctions.addXpathToTestObject("//input[@class='expiry-date field']"), '0226')
		WebUI.switchToDefaultContent()
	}

	private void closeTabbyPayment(double grandTotal) {
		switch (GlobalVariable.RunningMode) {
			case '1':
				tb = findTestObject('Object Repository/Check Out/Close payment method/Tabby back to Store');
				CustomLogger.logInfo("Waiting this object to visable ${tb.getObjectId()}")
				WebUI.waitForElementVisible(tb, 0)
				CustomLogger.logInfo("Clicking on test object: ${tb.getObjectId()}")
				WebUI.click(tb)
				tb = findTestObject('Object Repository/Check Out/Close payment method/Tabby Cancel')
				CustomLogger.logInfo("Waiting this object to visable ${tb.getObjectId()}")
				WebUI.waitForElementVisible(tb, 0)
				CustomLogger.logInfo("Clicking on test object: ${tb.getObjectId()}")
				WebUI.click(tb)
				CustomLogger.logInfo("Closing Tabby")
				WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/Close payment method/Fail order check'), 0)
				WebUI.takeFullPageScreenshot('./TabbyOrderResult.png')
				break
			case '2':
				WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/Close payment method/Tabby close icon Phone'), 0)
				WebUI.click(findTestObject('Object Repository/Check Out/Close payment method/Tabby close icon Phone'))
				WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/Close payment method/Tabby Cancel'), 0)
				WebUI.click(findTestObject('Object Repository/Check Out/Close payment method/Tabby Cancel'))
				WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/Close payment method/Fail order check'), 0)
				WebUI.takeFullPageScreenshot('./TabbyOrderResult.png')
				break
		}
		CustomLogger.logInfo("Verifying the Cart is not empty")
		WebUI.verifyNotEqual(utilityFunctions.checkIfElementExist('Object Repository/Cart/Items in cart'), 0, FailureHandling.CONTINUE_ON_FAILURE)
		CustomLogger.logInfo("Getting the grand Total and check with Old one")
		double grandTotalAfterClose = (WebUI.getText(findTestObject('Object Repository/Cart/Cart Subtotal (Inc VAT)'),FailureHandling.STOP_ON_FAILURE).replaceAll(",", "") =~ /\d+\.\d+/)[0] as double
		WebUI.verifyEqual(grandTotal, grandTotalAfterClose, FailureHandling.STOP_ON_FAILURE);
	}

	private void closeTelrPayment() {
		WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/Close payment method/Cancel Telr'), 0)
		WebUI.click(findTestObject('Object Repository/Check Out/Close payment method/Cancel Telr'))
		WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/Close payment method/Fail order check'), 0)
		WebUI.takeFullPageScreenshot('./TelrOrderResult.png')
	}

	private void closeTamaraPayment() {
		tb = findTestObject('Object Repository/Check Out/Close payment method/Tamara close icon')
		CustomLogger.logInfo("Waiting close Tamara btn to be visible: ${tb.getObjectId()}")
		WebUI.waitForElementVisible(tb, 0)
		CustomLogger.logInfo("Click to close Tamara: ${tb.getObjectId()}")
		WebUI.click(tb)
		tb = findTestObject('Object Repository/Check Out/Close payment method/Tamara cancel button')
		CustomLogger.logInfo("Waiting Cancel Tamara btn to be visible: ${tb.getObjectId()}")
		WebUI.waitForElementVisible(tb, 0)
		CustomLogger.logInfo("Click to Cancel Tamara: ${tb.getObjectId()}")
		WebUI.click(tb)
		WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/Close payment method/Fail order check'), 0)
		WebUI.takeFullPageScreenshot('./TamaraOrderResult.png')
	}

	def verifyOrderConfirmation() {
		// Get the actual text from the element
		// Replace 'Your_Element_ID' with your actual element identifier
		def actualText = WebUI.getText(findTestObject('Check Out/Success Order message'))

		// Define the expected text pattern using regex
		// This pattern matches the Arabic text with any number between the quotes
		def expectedPattern = 'تم اتمام طلبك رقم "\\d+" بنجاح'
		def languageMode = GlobalVariable.languageMode

		if (languageMode.equalsIgnoreCase("en")) {
			expectedPattern = 'Your order number "\\d+" was completed successfully'
		}

		// Verify if the actual text matches the pattern
		if (actualText =~ expectedPattern) {
			KeywordUtil.markPassed("Message format is correct: ${actualText}")

			// Extract the order number if needed
			def matcher = actualText =~ /"(\d+)"/
			if (matcher.find()) {
				def orderNumber = matcher.group(1)
				KeywordUtil.logInfo("Order number found: ${orderNumber}")
			}
		} else {
			KeywordUtil.markFailed("Message format is incorrect. Expected pattern: ${expectedPattern}, Actual text: ${actualText}")
		}
		WebUI.click(findTestObject('Check Out/continue Shoping Button'))
	}

	def verifyMessageMatching(def expectedPattern, def testObject) {
		// Get the actual text from the element
		// Replace 'Your_Element_ID' with your actual element identifier
		def actualText = WebUI.getText(findTestObject(testObject))

		// Define the expected text pattern using regex
		// This pattern matches the Arabic text with any number between the quotes


		// Verify if the actual text matches the pattern
		if (actualText =~ expectedPattern) {
			KeywordUtil.markPassed("Message format is correct: ${actualText}")

			// Extract the order number if needed
			def matcher = actualText =~ /"(\d+)"/
			if (matcher.find()) {
				def orderNumber = matcher.group(1)
				KeywordUtil.logInfo("Order number found: ${orderNumber}")
			}
		} else {
			KeywordUtil.markFailed("Message format is incorrect. Expected pattern: ${expectedPattern}, Actual text: ${actualText}")
		}
	}
}