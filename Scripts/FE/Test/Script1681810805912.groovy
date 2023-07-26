import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.main.CustomKeywordDelegatingMetaClass as CustomKeywordDelegatingMetaClass
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
import com.kms.katalon.core.configuration.RunConfiguration as RC
import org.apache.commons.lang3.StringUtils
def executionProfile = RC.getExecutionProfile()
//def x = findTestObject('Object Repository/Mega Menu/Catalog list').findPropertyValue('xpath') + '[3]'

//def xpathval = x.findPropertyValue('xpath') + '[3]'

//x.findProperty('xpath').setValue(xpathval)

//KeywordUtil.logInfo(xpathval)

//x.addProperty("xpath", ConditionType.EQUALS, xpathval,true)
//("", ConditionType.EQUALS, "[2]",true)
/*
 * double sumOfOrder def numOfElementsInCard =
 * CustomKeywords.'utility.Utility.checkIfElementExist'('Object
 * Repository/Cart/Product price') if(numOfElementsInCard!=0) { for(int
 * i=1;i<=numOfElementsInCard;i++) { productPrice =
 * WebUI.getText(CustomKeywords.'utility.Utility.addXpathToTestObject'("("+
 * findTestObject('Object Repository/Cart/Product
 * price').findPropertyValue('xpath') + ")["+i+"]")) as double sumOfOrder
 * =sumOfOrder + productPrice
 * KeywordUtil.logInfo(WebUI.getText(CustomKeywords.'utility.Utility.
 * addXpathToTestObject'("("+findTestObject('Object Repository/Cart/Product
 * price').findPropertyValue('xpath') + ")["+i+"]")))
 * KeywordUtil.logInfo(sumOfOrder.round(2).toString()) } }
 */
//WebUI.click(CustomKeywords.'utility.Utility.addXpathToTestObject'(findTestObject('Object Repository/Mega Menu/Catalog list').findPropertyValue('xpath') + '[3]'))
//WebUI.delay(5)
if(StringUtils.indexOfIgnoreCase(executionProfile, "-Live")>0) {
KeywordUtil.logInfo(GlobalVariable.productionPhones.get(CustomKeywords.'generalactions.generalStrings.getRandomNumberBetweenAnytoAny'(GlobalVariable.productionPhones.size()-1,0)).toString())
}
KeywordUtil.logInfo(GlobalVariable.productionPhones.size().toString())

KeywordUtil.logInfo(CustomKeywords.'generalactions.generalStrings.getRandomNumberBetweenOnetoTarget'(GlobalVariable.productionPhones.size()).toString())
KeywordUtil.logInfo(CustomKeywords.'generalactions.generalStrings.getRandomNumberBetweenAnytoAny'(GlobalVariable.productionPhones.size()-1,0).toString())
KeywordUtil.logInfo(GlobalVariable.productionPhones.get(0).toString())
//def totalPrice = WebUI.getText(findTestObject('Object Repository/Cart/Cart Subtotal (Inc VAT)')) =~/\d+\.\d+/
//KeywordUtil.logInfo(totalPrice[0].toString())
//WebUI.verifyEqual(CustomKeywords.'cart.cartItems.getCartSubtotal'(), CustomKeywords.'cart.cartItems.getSumOfProductsPriceInCart'())
