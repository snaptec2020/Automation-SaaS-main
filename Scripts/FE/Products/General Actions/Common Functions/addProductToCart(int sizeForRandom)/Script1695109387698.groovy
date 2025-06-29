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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

def elementIndexproduct = CustomKeywords.'generalactions.generalStrings.getRandomNumberBetweenOnetoTarget'(sizeForRandom //Math.abs((randomNumberforProduct.nextInt(sizeForRandom)))
    )

//KeywordUtil.logInfo(elementIndexproduct.toString() +prod.get(elementIndexproduct).toString())
if (elementIndexproduct == 0) {
    elementIndexproduct = 1
}

def currentURL = WebUI.getUrl()

//tb.addProperty('xpath', ConditionType.EQUALS, "(//div[@class='styles_productItem__YY5Bs']//button[@class='styles_atcButton__qYfHB styles_atcButton__kaT52'][contains(text(),'Add to Cart') or contains(text(),'أضف إلى السلة')])["+elementIndexproduct+"]")
CustomKeywords.'utility.Utility.clickOnObjectusingJavaScript'(findTestObject('Object Repository/Products/Add InStock ToCart', 
        [('index') : elementIndexproduct]))

if((GlobalVariable.normalEcommerce == null || !GlobalVariable.normalEcommerce) && GlobalVariable.isFirstTime) {
	WebUI.callTestCase(findTestCase('FE/Website launch/Validations/Add locatin New workflow'), [:], FailureHandling.STOP_ON_FAILURE) 
	CustomKeywords.'utility.Utility.clickOnObjectusingJavaScript'(findTestObject('Object Repository/Products/Add InStock ToCart',
		[('index') : elementIndexproduct]))
}
//WebElement element = WebUiCommonHelper.findWebElement(tb,30)
//WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(element))
WebUI.delay(2)

//checkOnAddToStoreClickable(currentURL)

WebUI.callTestCase(findTestCase('FE/Products/General Actions/Common Functions/checkOnAddToStoreClickable(def currentURL)'), 
    [('currentURL') : currentURL], FailureHandling.STOP_ON_FAILURE)

