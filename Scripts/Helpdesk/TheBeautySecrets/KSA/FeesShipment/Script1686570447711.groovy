import com.kms.katalon.core.model.FailureHandling

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.keyword.builtin.CallTestCaseKeyword
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
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword as Keyword
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword as WebUIAbstractKeyword
import catalog.catlogComponants as catlogComponants
import java.util.List as List
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/TheBeautySecrets/KSA/SharedScripts/LaunchFE'), [:],	FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/TheBeautySecrets/KSA/SharedScripts/Login'), [:],	FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/TheBeautySecrets/KSA/SharedScripts/ClearProductsFromCartPage'), [:],	FailureHandling.STOP_ON_FAILURE)

if(WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/Logo'),5,FailureHandling.OPTIONAL)){
	WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/Logo'),FailureHandling.OPTIONAL)
}

WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/TheBeautySecrets/KSA/SharedScripts/OpenAndAddProductToCart'), [:],	FailureHandling.STOP_ON_FAILURE)
/////////////////////////
//WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Product/MoreDetailsPlus'))
def ProductTitle = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Product/productFullDetail-Name'), 5).getText().split("\n")[0]
def ProductSKU = WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Product/productFullDetail-sku')).replace(":", "").replace(" ", "")
def ProductURL = CustomKeywords.'helpdesk.HelpdeskUtil.decodeEncodedValue'(WebUI.getUrl()) //.replace(GlobalVariable.FE_URL, "")
def ProductPrice = ""

if(WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Product/SpecialPrice'), 2,FailureHandling.OPTIONAL)) {
	ProductPrice = WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Product/SpecialPrice')).replace("٫", ".").replace("ر.س.", "")
}else {
	ProductPrice = WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Product/Product_Price')).replace("٫", ".").replace("ر.س.", "")
}

println ProductTitle
println ProductSKU
println ProductPrice
println ProductURL

WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/Cart'),10)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/Cart'),10)
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/Cart'), 10)
if(WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/ViewCartMainPageBtn'), 5,FailureHandling.OPTIONAL)) {
	WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/ViewCartMainPageBtn'),10)
}else {
	WebUI.refresh()
	WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/Cart'),10)
	WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/Cart'),10)
	CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/Cart'), 10)
}
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/ViewCartMainPageBtn'),10)
CustomKeywords.'helpdesk.HelpdeskUtil.clickJS'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/ViewCartMainPageBtn'),10)


//Check Total Paid for Tabby and Tamara
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/totalNeededPay'), 10)
String totalText=WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/totalNeededPay'))
println(totalText)
totalText =totalText.replace(" ر.س.", "").replace(" ", "").replace("٫", ".")
println(totalText)
Float totalValue = totalText.toFloat()
println(totalValue)

int neededQty=1
if (totalValue < 150) {
    //increase the products
    neededQty = ((Math.ceil(150 / totalValue)) as int)
	CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/QTYProduct'))
	WebUI.focus(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/QTYProduct'))
	WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/QTYProduct'), Keys.chord(Keys.BACK_SPACE))
	WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/QTYProduct'), Keys.chord(Keys.DELETE))
	WebUI.sendKeys(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/QTYProduct'), neededQty.toString())
	WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/RefreshCart'))
	CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/RefreshCart'))
	WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/RefreshCart'))
    //remove and select another product (need to remove and seach again)
} else if (totalValue > 2500) {
	// To-Do Remove and add another product with less amount
}
neededQty=WebUI.getAttribute(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/QTYProduct'),'value').toInteger()
Float UnitPrice=WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/UnitPrice')).replace(" ر.س.", "").replace(" ", "").replace("٫", ".").toFloat()
WebUI.verifyMatch(UnitPrice.toString(), ProductPrice.toFloat().toString(), false)
totalValue=WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/totalNeededPay')).replace(" ر.س.", "").replace(" ", "").replace("٫", ".").toFloat()
WebUI.verifyMatch(totalValue.toString(), (UnitPrice*neededQty).toString(), false)

WebUI.delay(3)
WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/PriceSummaryButton'),10)
WebUI.waitForElementClickable(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/PriceSummaryButton'),10)
CustomKeywords.'helpdesk.HelpdeskUtil.ScrollToElement'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/PriceSummaryButton'))
WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/PriceSummaryButton'))

if(!WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/Step_4_PaymentMethods'),FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/PriceSummaryButton'))
}




//Steps
WebUI.waitForPageLoad(20)
WebUI.waitForElementNotVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/LoadingImg'), 20)
//WebUI.verifyElementNotVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/MapErrorCannotLoad'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/Step_4_PaymentMethods'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/Step_3_ShipmentMethod'))
//WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/Step_2_ShipmentLocation'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/Step_1_Login'))

Float TotalSub=0
Float ShipmentFees=0
Float TaxAmount=0
Float GrandTotal=0
Float GrandTotalExc=0
Float AdditionalFees=0
Float DiscountAmount=0




/////// KSA
if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/AddNewAddress'), 10)) {
	WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Cart/AddNewAddress'))
}
WebElement FullName = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShippedToForm-FullName'),5)
WebUI.verifyMatch(FullName.getAttribute('value'), GlobalVariable.CustomerName, false)

WebElement Phone = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShippedToForm-Phone'),5)
WebUI.verifyMatch(Phone.getAttribute('value'), GlobalVariable.FE_Tel, false)

WebUI.verifyOptionsPresent(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShippedToForm-Country')
	,['الإمارات العربية المتحدة', 'الكويت', 'المملكة العربية السعودية'])

WebUI.selectOptionByLabel(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShippedToForm-Country'), 'المملكة العربية السعودية', false)
WebUI.waitForElementNotVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/LoadingImg'), 3)

WebUI.selectOptionByLabel(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShippedToForm-City'), 'الرياض', false)
WebUI.waitForElementNotVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/LoadingImg'), 3)

WebUI.setText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShippedToForm-Street'), 'شارع العليا، العليا، الرياض 12214، المملكة العربية السعودية')
WebUI.waitForElementNotVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/LoadingImg'), 3)

WebUI.setText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShippedToForm-Neighbourhood'), 'العليا')
WebUI.waitForElementNotVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/LoadingImg'), 3)

WebUI.setText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShippedToForm-HomePhone'),GlobalVariable.FE_Tel)
WebUI.waitForElementNotVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/LoadingImg'), 3)

CustomKeywords.'helpdesk.HelpdeskUtil.uncheckUsingJS'(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShippedToForm-SaveAddressCheckbox'),3)
WebUI.waitForElementNotVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/LoadingImg'), 3)

TotalSub=WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/TotalSub')).replace(" ر.س.", "").replace(" ", "").replace("٫", ".").toFloat()
ShipmentFees=WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShipmentFees')).replace(" ر.س.", "").replace(" ", "").replace("٫", ".").toFloat()
if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/AdditionalFees'), 5)) {
	AdditionalFees=WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/AdditionalFees')).replace(" ر.س.", "").replace(" ", "").replace("٫", ".").toFloat()
}else {
	AdditionalFees=0
}
if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/DiscountAmount'), 5)) {
	DiscountAmount=WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/DiscountAmount')).replace(" ر.س.", "").replace(" ", "").replace("٫", ".").toFloat()
}else {
	DiscountAmount=0
}
TaxAmount=WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/TaxAmount')).replace(" ر.س.", "").replace(" ", "").replace("٫", ".").toFloat()
GrandTotal=WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/GrandTotal')).replace(" ر.س.", "").replace(" ", "").replace("٫", ".").toFloat()
GrandTotalExc=WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/GrandTotalExc')).replace(" ر.س.", "").replace(" ", "").replace("٫", ".").toFloat()

WebUI.verifyMatch(TotalSub.toString(), totalValue.toString(), false)
WebUI.verifyMatch(ShipmentFees.toString(), '25.0', false)
WebUI.verifyMatch(TaxAmount.toString(), (((TotalSub+ShipmentFees+DiscountAmount)/1.15)*0.15).round(2).toString(), false)
WebUI.verifyMatch(GrandTotal.toString(), ((TotalSub+ShipmentFees+DiscountAmount).round(2)+AdditionalFees).toString(), false)
WebUI.verifyMatch(GrandTotalExc.toString(), (((TotalSub+ShipmentFees+DiscountAmount)/1.15).round(2)+AdditionalFees).toString(), false)

/////// Kuwait
WebUI.selectOptionByLabel(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShippedToForm-Country'), 'الكويت', false)
WebUI.waitForElementNotVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/LoadingImg'), 3)

WebUI.selectOptionByLabel(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShippedToForm-City'), 'السالمية', false)
WebUI.waitForElementNotVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/LoadingImg'), 3)

WebUI.setText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShippedToForm-Street'), 'شارع الواجهة')
WebUI.waitForElementNotVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/LoadingImg'), 3)

WebUI.setText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShippedToForm-Neighbourhood'), 'الواجهة')
WebUI.waitForElementNotVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/LoadingImg'), 3)

TotalSub=WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/TotalSub')).replace(" ر.س.", "").replace(" ", "").replace("٫", ".").toFloat()
ShipmentFees=WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShipmentFees')).replace(" ر.س.", "").replace(" ", "").replace("٫", ".").toFloat()
if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/AdditionalFees'), 5)) {
	AdditionalFees=WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/AdditionalFees')).replace(" ر.س.", "").replace(" ", "").replace("٫", ".").toFloat()
}else {
	AdditionalFees=0
}
if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/DiscountAmount'), 5)) {
	DiscountAmount=WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/DiscountAmount')).replace(" ر.س.", "").replace(" ", "").replace("٫", ".").toFloat()
}else {
	DiscountAmount=0
}
TaxAmount=WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/TaxAmount')).replace(" ر.س.", "").replace(" ", "").replace("٫", ".").toFloat()
GrandTotal=WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/GrandTotal')).replace(" ر.س.", "").replace(" ", "").replace("٫", ".").toFloat()
GrandTotalExc=WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/GrandTotalExc')).replace(" ر.س.", "").replace(" ", "").replace("٫", ".").toFloat()

WebUI.verifyMatch(TotalSub.toString(), totalValue.toString(), false)
WebUI.verifyNotMatch(ShipmentFees.toString(), '25.0', false)
WebUI.verifyMatch(TaxAmount.toString(), (((TotalSub+ShipmentFees+DiscountAmount)/1.15)*0.15).round(2).toString(), false)
WebUI.verifyMatch(GrandTotal.toString(), ((TotalSub+ShipmentFees+DiscountAmount).round(2)+AdditionalFees).toString(), false)
WebUI.verifyMatch(GrandTotalExc.toString(), (((TotalSub+ShipmentFees+DiscountAmount)/1.15).round(2)+AdditionalFees).toString(), false)


/////// UAE
WebUI.selectOptionByLabel(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShippedToForm-Country'), 'الإمارات العربية المتحدة', false)
WebUI.waitForElementNotVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/LoadingImg'), 3)

WebUI.selectOptionByLabel(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShippedToForm-City'), 'أبو ظبي', false)
WebUI.waitForElementNotVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/LoadingImg'), 3)

WebUI.setText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShippedToForm-Street'), 'شارع أبو ظبي')
WebUI.waitForElementNotVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/LoadingImg'), 3)

WebUI.setText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShippedToForm-Neighbourhood'), 'المينا')
WebUI.waitForElementNotVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/LoadingImg'), 3)

TotalSub=WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/TotalSub')).replace(" ر.س.", "").replace(" ", "").replace("٫", ".").toFloat()
ShipmentFees=WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/ShipmentFees')).replace(" ر.س.", "").replace(" ", "").replace("٫", ".").toFloat()
if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/AdditionalFees'), 5)) {
	AdditionalFees=WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/AdditionalFees')).replace(" ر.س.", "").replace(" ", "").replace("٫", ".").toFloat()
}else {
	AdditionalFees=0
}
if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/DiscountAmount'), 5)) {
	DiscountAmount=WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/DiscountAmount')).replace(" ر.س.", "").replace(" ", "").replace("٫", ".").toFloat()
}else {
	DiscountAmount=0
}
TaxAmount=WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/TaxAmount')).replace(" ر.س.", "").replace(" ", "").replace("٫", ".").toFloat()
GrandTotal=WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/GrandTotal')).replace(" ر.س.", "").replace(" ", "").replace("٫", ".").toFloat()
GrandTotalExc=WebUI.getText(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Checkout/GrandTotalExc')).replace(" ر.س.", "").replace(" ", "").replace("٫", ".").toFloat()

WebUI.verifyMatch(TotalSub.toString(), totalValue.toString(), false)
WebUI.verifyNotMatch(ShipmentFees.toString(), '25.0', false)
WebUI.verifyMatch(TaxAmount.toString(), (((TotalSub+ShipmentFees+DiscountAmount)/1.15)*0.15).round(2).toString(), false)
WebUI.verifyMatch(GrandTotal.toString(), ((TotalSub+ShipmentFees+DiscountAmount).round(2)+AdditionalFees).toString(), false)
WebUI.verifyMatch(GrandTotalExc.toString(), (((TotalSub+ShipmentFees+DiscountAmount)/1.15).round(2)+AdditionalFees).toString(), false)


WebUI.delay(5)
WebUI.closeBrowser()
