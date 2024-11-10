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
import generalactions.generalStrings
import internal.GlobalVariable
import utility.Utility

public class EnhancedPayments {
	TestObject tb = new TestObject();
	Random randomNumberforProduct = new Random()
	def utilityFunctions = new Utility()
	def generalStrings = new generalStrings()
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
	def paymentMethodToPayBySelectedMethod(int selectedIndex,def paymentMethod = 'Default') {
		if (selectedIndex == 0) {
			selectedIndex = 1
		}
		utilityFunctions.clickOnObjectUsingJavaScript(utilityFunctions.addXpathToTestObject("("+findTestObject('Object Repository/Check Out/Payment methods list').findPropertyValue('xpath') + ")["+selectedIndex+"]"))
		placeOrder(paymentMethod)
	}

	def placeOrder(String expectedPaymentMethod = 'Default') {
	    try {
	        def nonVisa = WebUiCommonHelper.findWebElement(utilityFunctions.addXpathToTestObject("//div[contains(@class,'payment-method v2')]/following-sibling::div"), 30).getAttribute("class")
	        if (nonVisa != 'checkout-com-form-container') {
	            if (WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/payment Method Text'), 0)) {
	                double grandTotal = (WebUI.getText(findTestObject('Object Repository/Check Out/Grand Total')).replaceAll(",", "") =~ /\d+\.\d+/)[0] as double
	                String paymentMethod = WebUI.getText(findTestObject('Object Repository/Check Out/payment Method Text'))
	                if (paymentMethod.equalsIgnoreCase(expectedPaymentMethod) || paymentMethod.equalsIgnoreCase('Default')) {
	                    WebUI.click(findTestObject('Object Repository/Check Out/Place order check out button'))
	                    handlePaymentMethod(paymentMethod, grandTotal)
	                } else {
	                    KeywordUtil.logInfo("Expected payment method: $expectedPaymentMethod, Actual payment method: $paymentMethod")
	                    return
	                }
	            }
	        } else {
	            WebUI.verifyElementVisible(findTestObject('Object Repository/Check Out/Place order check out button'))
	            fillCreditCardDetails()
	            WebUI.click(findTestObject('Object Repository/Check Out/Place order check out button'))
	            WebUI.delay(10)
	        }
	    } catch (Exception e) {
	        e.printStackTrace()
	        WebUI.navigateToUrl(GlobalVariable.URL, FailureHandling.CONTINUE_ON_FAILURE)
	    }
	}

	private void handlePaymentMethod(String paymentMethod, double grandTotal) {
		switch (paymentMethod) {
			case ~('قسّمها على 4 بدون رسوم ولا فوائد'):
			case ~('4 interest-free payments'):
				handleTabbyPayment(grandTotal)
				break
			case ~('مدى/فيزا/ماستر كارد/آبل باي'):
			case ~('Mada/ Visa/Masster Card/Apple pay'):
				handleTelrPayment(grandTotal)
				break
			case ~('الدفع عند الإستلام'):
			case ~('Cash On Delivery'):
				handleCashOnDeliveryPayment()
				break
			case ~('قسم فاتورتك على 3 دفعات بدون فوائد'):
			case ~('Mada/ Visa/Masster Card/Apple pay'):
				handleTamaraPayment()
				break
		}
	}

	private void handleTabbyPayment(double grandTotal) {
		WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/TabbyTotal'), 0)
		double tabbyTotal = (WebUI.getText(findTestObject('Object Repository/Check Out/TabbyTotal')).replaceAll(",", "") =~ /\d+\.\d+/)[0] as double
		WebUI.verifyEqual(grandTotal, tabbyTotal, FailureHandling.CONTINUE_ON_FAILURE)
		closeTabbyPayment()
	}

	private void handleTelrPayment(double grandTotal) {
		WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/Telr Grand Total'), 0)
		double telrTotal = (WebUI.getText(findTestObject('Object Repository/Check Out/Telr Grand Total')).replaceAll(",", "") =~ /\d+\.\d+/)[0] as double
		WebUI.verifyEqual(grandTotal, telrTotal, FailureHandling.CONTINUE_ON_FAILURE)
		closeTelrPayment()
	}

	private void handleCashOnDeliveryPayment() {
		if (StringUtils.indexOfIgnoreCase(executionProfile, "-Live") > 0) {
			return
		} else {
			WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/COD Success'), 0)
			WebUI.takeFullPageScreenshot('./CODOrderResult.png')
		}
	}

	private void handleTamaraPayment() {
		WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/Telr Grand Total'), 0)
		String tamaraURL = WebUI.getUrl()
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

	private void closeTabbyPayment() {
		switch (GlobalVariable.RunningMode) {
			case '1':
				WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/Close payment method/Tabby back to Store'), 0)
				WebUI.click(findTestObject('Object Repository/Check Out/Close payment method/Tabby back to Store'))
				WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/Close payment method/Tabby Cancel'), 0)
				WebUI.click(findTestObject('Object Repository/Check Out/Close payment method/Tabby Cancel'))
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
	}

	private void closeTelrPayment() {
		WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/Close payment method/Cancel Telr'), 0)
		WebUI.click(findTestObject('Object Repository/Check Out/Close payment method/Cancel Telr'))
		WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/Close payment method/Fail order check'), 0)
		WebUI.takeFullPageScreenshot('./TelrOrderResult.png')
	}

	private void closeTamaraPayment() {
		WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/Close payment method/Tamara close icon'), 0)
		WebUI.click(findTestObject('Object Repository/Check Out/Close payment method/Tamara close icon'))
		WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/Close payment method/Tamara cancel button'), 0)
		WebUI.click(findTestObject('Object Repository/Check Out/Close payment method/Tamara cancel button'))
		WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/Close payment method/Fail order check'), 0)
		WebUI.takeFullPageScreenshot('./TamaraOrderResult.png')
	}
}