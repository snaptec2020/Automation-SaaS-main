import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.amazonaws.ClientConfiguration as ClientConfiguration
import com.amazonaws.DefaultRequest as DefaultRequest
import com.amazonaws.Request as Request
import com.amazonaws.Response as Response
import com.amazonaws.auth.AWS4Signer as AWS4Signer
import com.amazonaws.auth.AWSCredentials as AWSCredentials
import com.amazonaws.auth.Signer as Signer
import com.amazonaws.http.AmazonHttpClient as AmazonHttpClient
import com.amazonaws.http.ExecutionContext as ExecutionContext
import com.amazonaws.http.HttpMethodName as HttpMethodName
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.main.CustomKeywordDelegatingMetaClass as CustomKeywordDelegatingMetaClass
import com.kms.katalon.core.main.TestCaseMain as TestCaseMain
import com.kms.katalon.core.main.TestSuiteExecutor as TestSuiteExecutor
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.common.internal.SmartWait as SmartWait
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword as WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebElement as WebElement
import com.thoughtworks.selenium.webdriven.Windows as Windows
import org.openqa.selenium.Point as Point
import com.kms.katalon.core.configuration.RunConfiguration as RC
import org.apache.commons.codec.binary.Hex as Hex
import org.apache.commons.lang3.StringUtils as StringUtils
import java.nio.file.Files as Files
import java.text.Collator as Collator
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.*
import javax.crypto.Mac as Mac
import javax.crypto.spec.SecretKeySpec as SecretKeySpec

SmartWait smw = new SmartWait()

WebUI.callTestCase(findTestCase('FE/Products/General Actions/Common Functions/getSpecifiedinStockProductsFromRandomCategoryInTarget()'), 
    [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('FE/Products/General Actions/Common Functions/getRandominStockProductsFromRandomCategory()'), 
    [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('FE/Products/General Actions/Common Functions/configurableProduct()'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('FE/Products/General Actions/Common Functions/checkOnAddToStoreClickable(def currentURL)'), 
    [('currentURL') : ''], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject //cartSubTotal = CustomKeywords.'cart.cartItems.getCartSubtotal'()
    //CustomKeywords.'cart.removeItem.clearCart'()
    ) //WebUI.callTestCase(findTestCase('FE/menu Items/Select Catalog - Select All Categories and Scrolling'), [:], FailureHandling.STOP_ON_FAILURE)
//WebUI.findWebElements(findTestObject('Object Repository/Cart/Cart count'), 10).size()
//KeywordUtil.logInfo(cartSubTotal.toString())
//CustomKeywords.'products.productsFromCatalog.getRandominStockProductsFromRandomCategory'()
//switch(cartSubTotal){
//}

static void main(String[] args) {
    WebUI.callTestCase(findTestCase('FE/Cart/General Actions/View Cart'), [:], FailureHandling.STOP_ON_FAILURE)

    if (WebUI.waitForElementVisible(findTestObject('Object Repository/Cart/Out Of Stock Items'), 5)) {
        CustomKeywords.'cart.removeItem.deleteOutStockFromCart'()
    }
    
    float cartSubTotal = CustomKeywords.'cart.cartItems.getCartSubtotal'()

    if ((cartSubTotal == 0) || !((cartSubTotal >= GlobalVariable.minimum) && (cartSubTotal <= GlobalVariable.maximum))) {
        while (!((cartSubTotal >= GlobalVariable.minimum) && (cartSubTotal <= GlobalVariable.maximum))) {
            if (cartSubTotal > GlobalVariable.maximum) {
                CustomKeywords.'cart.removeItem.deleteItemFromCart'()

                cartSubTotal = CustomKeywords.'cart.cartItems.getCartSubtotal'()
            } else if (cartSubTotal < GlobalVariable.minimum) {
                CustomKeywords.'products.productsFromCatalog.getSpecifiedinStockProductsFromRandomCategoryInTarget'()

                cartSubTotal = CustomKeywords.'cart.cartItems.getCartSubtotal'()
            } else if (WebUI.waitForElementVisible(findTestObject('Object Repository/Cart/Out Of Stock Items'), 5)) {
                CustomKeywords.'cart.removeItem.deleteOutStockFromCart'()

                cartSubTotal = CustomKeywords.'cart.cartItems.getCartSubtotal'()
            } else {
                CustomKeywords.'products.productsFromCatalog.getSpecifiedinStockProductsFromRandomCategoryInTarget'()

                cartSubTotal = CustomKeywords.'cart.cartItems.getCartSubtotal'()
            }
        }
    }
    
    WebUI.callTestCase(findTestCase('FE/Cart/General Actions/View Cart'), [:], FailureHandling.STOP_ON_FAILURE)

    WebUI.verifyElementVisible(findTestObject('Check Out/Apply Discount Button'))

    WebUI.verifyElementVisible(findTestObject('Check Out/Cart Calculation'))

    WebUI.verifyElementVisible(findTestObject('Check Out/Proceed To Checkout Button'))

    WebUI.verifyElementVisible(findTestObject('Check Out/Shopping Cart head'))
}

//Files.copy(new File("C:/Users/aboel/Desktop/GitHub/Automation-SaaS-main/Reports/20230911_111657/Test Suites/Regression Suites/Checkout All methods/20230911_111657/20230911_111657.html").getPath(), new File("C:/Users/aboel/Desktop/GitHub/Automation-SaaS-main/Reports/20230911_111657/Test Suites/Regression Suites/Checkout All methods/20230911_111657/index.html").getPath())
/*
Map suiteProperties =[:]
suiteProperties.put("retryCount", "0")
//KeywordUtil.logInfo(RunConfiguration.getExecutionProperties().toString())
TestCaseMain.startTestSuite(GlobalVariable.testSuiteStatus, suiteProperties, new File(GlobalVariable.testSuiteReportFolder+"/testCaseBinding"))
*/ //String awsAccessKey
////
/////** Put your secret key here **/
//String awsSecretKey
////
/////** Put your bucket name here **/
//String SecurityToken
////
/////** The name of the region where the bucket is created. (e.g. us-west-1) **/
//String regionName = 'eu-west-1'
////String latitude
////String longitude
//def script = "return JSON.parse(JSON.parse(localStorage.getItem('persist:availableCacheData:tanmiah_ar')).appState).locatorZones"
//
//def locatorZones = WebUI.executeJavaScript(script, null)
//KeywordUtil.logInfo(locatorZones.coordinate.toString())
//def latLong = []
//for(int i =0;i<=locatorZones.coordinate.size()-1;i++) {
//	locatorZones.coordinate[i].each { 
//		latLong.add([it.latitude,it.longitude])
//	}
//	
//}
//KeywordUtil.logInfo(latLong.toString())
//first:
//for(int i =0;i<= latLong.size()-1;i++) {
//	KeywordUtil.logInfo(latLong[i][0].toString())
//	KeywordUtil.logInfo(latLong[i][1].toString())
//	def resopnse = CustomKeywords.'generalactions.generalStrings.jsonParser'(WS.sendRequest(findTestObject('APIs/Location APIs/Postman/cognito-identity.eu-west-1.amazonaws.com')).getResponseText())
//	/*
//	 def resopnse = CustomKeywords.'generalactions.generalStrings.jsonParser'(WS.sendRequest(findTestObject('APIs/Location APIs/Postman/cognito-identity.eu-west-1.amazonaws.com')).getResponseText())
//	
//	KeywordUtil.logInfo(resopnse.toString())
//	String awsAccessKey = resopnse.Credentials.AccessKeyId
//	
//	
//	String awsSecretKey = resopnse.Credentials.SecretKey
//	
//	
//	String SecurityToken = resopnse.Credentials.SessionToken
//	
//	
//	String regionName = 'eu-west-1'
//	
//	CustomKeywords.'com.amazonaws.services.s3.sample.getAPIResults.getAPIResultsByLatLong'(awsAccessKey, awsSecretKey, SecurityToken,
//		regionName, '39.1291145', '21.3369007')
//	*/
//	KeywordUtil.logInfo(resopnse.toString())
//	 awsAccessKey = resopnse.Credentials.AccessKeyId
//	
//	 awsSecretKey = resopnse.Credentials.SecretKey
//	
//
//	 SecurityToken = resopnse.Credentials.SessionToken
//	 KeywordUtil.logInfo(awsAccessKey)
//	 KeywordUtil.logInfo(awsSecretKey)
//	 KeywordUtil.logInfo(SecurityToken)
//	 KeywordUtil.logInfo(latLong[i][1].toString())
//	 KeywordUtil.logInfo(latLong[i][0].toString())
//	
//	def locations = CustomKeywords.'com.amazonaws.services.s3.sample.getAPIResults.getAPIResultsByLatLong'(awsAccessKey, awsSecretKey, SecurityToken,
//		regionName, latLong[i][1].toString(), latLong[i][0].toString())
//	second:
//	for(int j =0;j<= locations.size()-1;j++){ 
//		WebUI.setText(findTestObject('Map Objs/Input search location'), locations[j])
//		WebUI.click(findTestObject('Map Objs/Select 1st location'))
//		if (WebUI.waitForElementClickable(findTestObject('Map Objs/Confirim location'), 5)) {
//			WebUI.click(findTestObject('Map Objs/Confirim location'))
//			break first
//			}
//	}
//}
//KeywordUtil.markErrorAndStop("for testing")
/*
 def resopnse = CustomKeywords.'generalactions.generalStrings.jsonParser'(WS.sendRequest(findTestObject('APIs/Location APIs/Postman/cognito-identity.eu-west-1.amazonaws.com')).getResponseText())

KeywordUtil.logInfo(resopnse.toString())
String awsAccessKey = resopnse.Credentials.AccessKeyId


String awsSecretKey = resopnse.Credentials.SecretKey


String SecurityToken = resopnse.Credentials.SessionToken


String regionName = 'eu-west-1'

CustomKeywords.'com.amazonaws.services.s3.sample.getAPIResults.getAPIResultsByLatLong'(awsAccessKey, awsSecretKey, SecurityToken, 
    regionName, '39.1291145', '21.3369007')
*/ 