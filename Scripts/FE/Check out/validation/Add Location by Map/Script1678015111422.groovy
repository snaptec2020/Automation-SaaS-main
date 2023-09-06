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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import java.text.Collator;
import java.util.*;
/*
 * WebUI.click(findTestObject('Check Out/Zoom In (Map)'))

 * 
 * int mapHeight = WebUI.getElementHeight(findTestObject('Object Repository/Map Objs/Map Block')) / 2
 * 
 * int mapWidth = WebUI.getElementWidth(findTestObject('Object Repository/CheckOut/Map Block')) / 2
 */

String script ="return (JSON.parse(localStorage.getItem('BROWSER_PERSISTENCE__store_view_code')).value).replaceAll('\"','')"
def store = WebUI.executeJavaScript(script, null)
script = "return JSON.parse(JSON.parse(localStorage.getItem('persist:availableCacheData:"+store+"')).appState).storeConfig.default_country_code"
def countryCode = WebUI.executeJavaScript(script, null)
//KeywordUtil.logInfo(countryCode.toString())
Locale locale = new Locale("en", countryCode);

WebUI.waitForElementClickable(findTestObject('Map Objs/Pick from map btn'), 10)
WebUI.click(findTestObject('Map Objs/Pick from map btn'))
//WebUI.delay(50)
WebUI.setText(findTestObject('Map Objs/Input search location'), GlobalVariable.Countries[locale.getDisplayCountry().toLowerCase()])
WebUI.click(findTestObject('Map Objs/Select 1st location'))
WebUI.clickOffset(findTestObject('Map Objs/Map Block'), 0, 0)
//WebUI.doubleClick(findTestObject('Map Objs/Map Block'), FailureHandling.CONTINUE_ON_FAILURE)
//WebUI.verifyElementVisible(findTestObject('Map Objs/Map postion after click on maker'), FailureHandling.CONTINUE_ON_FAILURE)

if (WebUI.waitForElementClickable(findTestObject('Map Objs/Confirim location'), 5)) {
    WebUI.click(findTestObject('Map Objs/Confirim location'))
	
} else {
	//def searchText = WebUI.getText(findTestObject('Map Objs/Map Title')).replaceAll('^.*,', '').toString().trim()
	
	//KeywordUtil.logInfo(searchText.toString())
	script = "return JSON.parse(JSON.parse(localStorage.getItem('persist:availableCacheData:"+store+"')).appState).locatorZones"
	def locatorZones = WebUI.executeJavaScript(script, null)
	String awsAccessKey
	//
	///** Put your secret key here **/
	String awsSecretKey
	//
	///** Put your bucket name here **/
	String SecurityToken
	//
	///** The name of the region where the bucket is created. (e.g. us-west-1) **/
	String regionName = 'eu-west-1'
	if(locatorZones!=null) {
		def latLong = []
		for(int i =0;i<=locatorZones.coordinate.size()-1;i++) {
			locatorZones.coordinate[i].each {
				latLong.add([it.latitude,it.longitude])
			}
			
		}
		first:
		for(int i =0;i<= latLong.size()-1;i++) {
			//KeywordUtil.logInfo(latLong[i][0].toString())
			//KeywordUtil.logInfo(latLong[i][1].toString())
			def resopnse = CustomKeywords.'generalactions.generalStrings.jsonParser'(WS.sendRequest(findTestObject('APIs/Location APIs/Postman/cognito-identity.eu-west-1.amazonaws.com')).getResponseText())

			//KeywordUtil.logInfo(resopnse.toString())
			 awsAccessKey = resopnse.Credentials.AccessKeyId
			
			 awsSecretKey = resopnse.Credentials.SecretKey
			
		
			 SecurityToken = resopnse.Credentials.SessionToken
//			 KeywordUtil.logInfo(awsAccessKey)
//			 KeywordUtil.logInfo(awsSecretKey)
//			 KeywordUtil.logInfo(SecurityToken)
//			 KeywordUtil.logInfo(latLong[i][1].toString())
//			 KeywordUtil.logInfo(latLong[i][0].toString())
			
			def locations = CustomKeywords.'com.amazonaws.services.s3.sample.getAPIResults.getAPIResultsByLatLong'(awsAccessKey, awsSecretKey, SecurityToken,
				regionName, latLong[i][1].toString(), latLong[i][0].toString())
			second:
			for(int j =0;j<= locations.size()-1;j++){
				WebUI.setText(findTestObject('Map Objs/Input search location'), locations[j])
				WebUI.click(findTestObject('Map Objs/Select 1st location'))
				if (WebUI.waitForElementClickable(findTestObject('Map Objs/Confirim location'), 5)) {
					WebUI.click(findTestObject('Map Objs/Confirim location'))
					break first
					}
			}
		}
	}else {
	int numTrails = 0
	while(!WebUI.waitForElementClickable(findTestObject('Map Objs/Confirim location'), 5)&& numTrails<5){

		numTrails++
		WebUI.setText(findTestObject('Map Objs/Input search location'), GlobalVariable.Countries[locale.getDisplayCountry().toLowerCase()])
		WebUI.click(findTestObject('Map Objs/Select 1st location'))
		//WebUI.clickOffset(findTestObject('Map Objs/Map Block'), 0, 0)
		WebUI.doubleClick(findTestObject('Map Objs/Zoom In'))

		WebUI.delay(2)

		WebUI.clickOffset(findTestObject('Map Objs/Map Block'), 0, 0)
	}
	WebUI.click(findTestObject('Map Objs/Confirim location'))
}
}


//WebUI.doubleClick(findTestObject('Check Out/map ic marker'), FailureHandling.CONTINUE_ON_FAILURE)



//WebUI.verifyElementVisible(findTestObject('Check Out/Save location Map Button'), FailureHandling.CONTINUE_ON_FAILURE)

//WebUI.click(findTestObject('Check Out/Save location Map Button'), FailureHandling.CONTINUE_ON_FAILURE)