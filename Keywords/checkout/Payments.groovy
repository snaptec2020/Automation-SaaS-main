package checkout

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class Payments {
	TestObject tb=new TestObject();
	Random randomNumberforProduct = new Random()
	@Keyword
	def getPaymentMethodsList() {
		List Paymentlist = WebUI.findWebElements(findTestObject('Object Repository/Check Out/Payment methods list'),0)


		return Paymentlist
	}

	@Keyword
	def getRandomPaymentMethods() {
		List PaymentMethods = getPaymentMethodsList()
		//KeywordUtil.markError(prod.get(1))
		if(PaymentMethods.size()==0){
			//WebUI.closeBrowser(FailureHandling.STOP_ON_FAILURE)
			KeywordUtil.markPassed("No Payments Methods in this Page")

		} else{
			def elementsIndexPayments= Math.abs((randomNumberforProduct.nextInt(PaymentMethods.size())))
			//KeywordUtil.logInfo(elementIndexproduct.toString() +prod.get(elementIndexproduct).toString())
			if(elementsIndexPayments==0) {
				elementsIndexPayments=1
			}

			tb.addProperty('xpath', ConditionType.EQUALS, "//div[@class='payment-method-container__list__item']["+elementsIndexPayments+"]")
			WebUI.click(tb)
			placeOrder()
		}
	}
	def placeOrder()
	{
		if(WebUI.verifyElementVisible(findTestObject('Object Repository/Check Out/CheckOut Payment Method interdouce'), FailureHandling.CONTINUE_ON_FAILURE))
		{
			WebUI.click(findTestObject('Object Repository/Check Out/Place order check out button'))
			WebUI.delay(10)

		}else if(WebUI.verifyElementVisible(findTestObject('Object Repository/Check Out/Check Out insert Card'), FailureHandling.CONTINUE_ON_FAILURE))
		{
			WebUI.sendKeys(findTestObject('Object Repository/Check Out/Card Number Field'), '4440000009900010')
			WebUI.sendKeys(findTestObject('Object Repository/Check Out/Expire Data Card Field'), '0226')
			WebUI.sendKeys(findTestObject('Object Repository/Check Out/CVV Field'), '123')
			WebUI.click(findTestObject('Object Repository/Check Out/Place order check out button'))
			WebUI.delay(10)
		}
	}
}
