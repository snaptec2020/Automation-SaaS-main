package checkout

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.WebElement

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
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import generalactions.generalStrings
import internal.GlobalVariable
import utility.Utility

public class Payments {
	TestObject tb=new TestObject();
	Random randomNumberforProduct = new Random()
	def utilityFunctions = new Utility()
	def generalStrings = new generalStrings()
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
			def elementsIndexPayments=generalStrings.getRandomNumberBetweenOnetoTarget(PaymentMethods.size())//Math.abs((randomNumberforProduct.nextInt(PaymentMethods.size())))
			//KeywordUtil.logInfo(elementIndexproduct.toString() +prod.get(elementIndexproduct).toString())
			if(elementsIndexPayments==0) {
				elementsIndexPayments=1
			}
			//div[@class='payment-method-container__list__item']
			//tb.addProperty('xpath', ConditionType.EQUALS, "//div[@class='payment-method-container__list__item']["+elementsIndexPayments+"]")
			//utilityFunctions.addXpathToTestObject(findTestObject('Object Repository/Check Out/Payment methods list').findPropertyValue('xpath') + "["+elementsIndexPayments+"]")
			WebUI.click(utilityFunctions.addXpathToTestObject("("+findTestObject('Object Repository/Check Out/Payment methods list').findPropertyValue('xpath') + ")["+elementsIndexPayments+"]"))
			placeOrder()
		}
	}

	@Keyword
	def paymentMethodToPayBySelectedMethod(int elementsIndexPayments) {

		//KeywordUtil.markError(prod.get(1))
		if(elementsIndexPayments==0) {
			elementsIndexPayments=1
		}
		//div[@class='payment-method-container__list__item']
		//tb.addProperty('xpath', ConditionType.EQUALS, "//div[@class='payment-method-container__list__item']["+elementsIndexPayments+"]")
		//utilityFunctions.addXpathToTestObject(findTestObject('Object Repository/Check Out/Payment methods list').findPropertyValue('xpath') + "["+elementsIndexPayments+"]")
		WebUI.click(utilityFunctions.addXpathToTestObject("("+findTestObject('Object Repository/Check Out/Payment methods list').findPropertyValue('xpath') + ")["+elementsIndexPayments+"]"))
		//WebUI.click(utilityFunctions.addXpathToTestObject(findTestObject('Object Repository/Check Out/Payment methods list').findPropertyValue('xpath') + "["+elementsIndexPayments.toString()+"]"))
		placeOrder()
	}
	def placeOrder()
	{

		//tb.addProperty('xpath', ConditionType.EQUALS, "//div[contains(@class,'payment-method v2')]/following-sibling::div")

		def nonVisa = WebUiCommonHelper.findWebElement(utilityFunctions.addXpathToTestObject("//div[contains(@class,'payment-method v2')]/following-sibling::div"),30).getAttribute("class")
		//KeywordUtil.logInfo(nonVisa.toString())
		//int visa = WebUI.findWebElements(findTestObject('Object Repository/Check Out/Check Out insert Card'),30).size()
		if(nonVisa!='checkout-com-form-container')
		{
			WebUI.click(findTestObject('Object Repository/Check Out/Place order check out button'))
			WebUI.delay(10)

		}else //if(visa!=0)
		{
			//WebUI.scrollToElement(findTestObject('Object Repository/Check Out/Card Number Field'), 30)
			KeywordUtil.logInfo(nonVisa.toString())
			//tb.addProperty('xpath', ConditionType.EQUALS, "//iframe[@id='cardNumber']")
			utilityFunctions.switchToIframeByXpath("//iframe[@id='cardNumber']")
			//WebUI.switchToFrame(tb, 0)
			//tb.addProperty('xpath', ConditionType.EQUALS, "//input[@name='cardnumber']")
			WebUI.sendKeys(utilityFunctions.addXpathToTestObject("//input[@name='cardnumber']"), '4440000009900010')
			WebUI.switchToDefaultContent()
			utilityFunctions.switchToIframeByXpath("//iframe[@id='cvv']")
			//tb.addProperty('xpath', ConditionType.EQUALS, "//iframe[@id='cvv']")
			//WebUI.switchToFrame(tb, 0)
			//tb.addProperty('xpath', ConditionType.EQUALS, "//input[@class='cvv field']")
			WebUI.sendKeys(utilityFunctions.addXpathToTestObject("//input[@class='cvv field']"), '123')
			WebUI.switchToDefaultContent()
			utilityFunctions.switchToIframeByXpath("//iframe[@id='expiryDate']")
			//tb.addProperty('xpath', ConditionType.EQUALS, "//iframe[@id='expiryDate']")
			//WebUI.switchToFrame(tb, 0)
			//tb.addProperty('xpath', ConditionType.EQUALS, "//input[@class='expiry-date field']")
			WebUI.sendKeys(utilityFunctions.addXpathToTestObject("//input[@class='expiry-date field']"), '0226')
			WebUI.switchToDefaultContent()
			//WebUI.sendKeys(findTestObject('Object Repository/Check Out/Expire Data Card Field'), '0226')


			//WebElement element = WebUiCommonHelper.findWebElement(tb,30)
			//WebUI.executeJavaScript("arguments[0].value='4440000009900010'", Arrays.asList(element))
			//WebUI.waitForElementVisible(findTestObject('Object Repository/Check Out/Card Number Field'), 10)
			//KeywordUtil.logInfo(nonVisa.toString())
			//WebUI.sendKeys(findTestObject('Object Repository/Check Out/Card Number Field'), '4440000009900010')

			WebUI.click(findTestObject('Object Repository/Check Out/Place order check out button'))
			WebUI.delay(10)
		}
	}
}
