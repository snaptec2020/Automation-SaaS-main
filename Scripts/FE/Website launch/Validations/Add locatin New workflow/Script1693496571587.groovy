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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable

import org.apache.commons.lang3.StringUtils
import org.openqa.selenium.Keys as Keys
import java.text.Collator as Collator
import java.util.*

/*
 * WebUI.click(findTestObject('Check Out/Zoom In (Map)'))

 * 
 * int mapHeight = WebUI.getElementHeight(findTestObject('Object Repository/Map Objs/Map Block')) / 2
 * 
 * int mapWidth = WebUI.getElementWidth(findTestObject('Object Repository/CheckOut/Map Block')) / 2
 */
//
String script = 'return (JSON.parse(localStorage.getItem(\'BROWSER_PERSISTENCE__store_view_code\')).value).replaceAll(\'"\',\'\')'

def store = WebUI.executeJavaScript(script, null)

script = (('return JSON.parse(JSON.parse(localStorage.getItem(\'persist:availableCacheData:' + store) + '\')).appState).storeConfig.default_country_code')

//def countryCode = WebUI.executeJavaScript(script, null)

//KeywordUtil.logInfo(countryCode.toString())
def countryResponse = CustomKeywords.'generalactions.generalStrings.jsonParser'(WS.sendRequestAndVerify(findTestObject('APIs/Postman/Get country', [('URL') : GlobalVariable.URL, ('store') : store])).getResponseText())
def locale = (countryResponse.data.countries[0].full_name_english).toString().toLowerCase()//new Locale('en', countryCode)



//WebUI.click(findTestObject('Map Objs/Pick from map btn'))
//WebUI.delay(50)
//WebUI.setText(findTestObject('Map Objs/Input search location'), GlobalVariable.Countries[locale.getDisplayCountry().toLowerCase()])
//WebUI.click(findTestObject('Map Objs/Select 1st location'))
//WebUI.clickOffset(findTestObject('Map Objs/Map Block'), 0, 0)
/*if (WebUI.waitForElementClickable(findTestObject('Map Objs/Continue After select Address'), 5)) {
    WebUI.click(findTestObject('Map Objs/Continue After select Address')) //def searchText = WebUI.getText(findTestObject('Map Objs/Map Title')).replaceAll('^.*,', '').toString().trim()

} else {*/
//script = (('return JSON.parse(JSON.parse(localStorage.getItem(\'persist:availableCacheData:' + store) + '\')).appState).locatorZones')

String awsAccessKey

String awsSecretKey

String SecurityToken

String regionName = 'eu-west-1'

if (WebUI.waitForElementClickable(findTestObject('Map Objs/Pick from map btn'), 5)) {
	def locatorZonesResponse =CustomKeywords.'generalactions.generalStrings.jsonParser'(WS.sendRequestAndVerify(findTestObject('APIs/Postman/Get locator Zones', [('URL') : GlobalVariable.URL, ('store') : store])).getResponseText())//WebUI.executeJavaScript(script, null)
	def locatorZones = locatorZonesResponse.data.getLocatorZones
	KeywordUtil.logInfo(locatorZones.toString())
    WebUI.click(findTestObject('Map Objs/Pick from map btn'))

    def latLong = []

    for (int i = 0; i <= (locatorZones.size() - 1); i++) {
        (locatorZones[i]).each({ 
			for (int j = 0; j <= (locatorZones.coordinate.size() - 1); j++) {
				locatorZones.coordinate[j].each { 
					latLong.add([it.latitude, it.longitude])
					 }
				}
                
            })
    }
    
    for (int i = 0; i <= (latLong.size() - 1); i++) {
        def resopnse = CustomKeywords.'generalactions.generalStrings.jsonParser'(WS.sendRequest(findTestObject('APIs/Location APIs/Postman/cognito-identity.eu-west-1.amazonaws.com')).getResponseText())

        awsAccessKey = resopnse.Credentials.AccessKeyId

        awsSecretKey = resopnse.Credentials.SecretKey

        SecurityToken = resopnse.Credentials.SessionToken

        def locations = CustomKeywords.'com.amazonaws.services.s3.sample.getAPIResults.getAPIResultsByLatLong'(awsAccessKey, 
            awsSecretKey, SecurityToken, regionName, ((latLong[i])[1]).toString(), ((latLong[i])[0]).toString())

        for (int j = 0; j <= (locations.size() - 1); j++) {
            WebUI.setText(findTestObject('Map Objs/Input search location'), locations[j])

            WebUI.click(findTestObject('Map Objs/Select 1st location'))

            if (WebUI.waitForElementClickable(findTestObject('Map Objs/Continue After select Address'), 5)) {
                WebUI.click(findTestObject('Map Objs/Continue After select Address'))

                return null
            }
        }
    }
} else {
    WebUI.click(findTestObject('Map Objs/Change address button'))

    WebUI.click(findTestObject('Map Objs/Pick from map btn'))

    int numTrails = 0

    WebUI.clickOffset(findTestObject('Map Objs/Map Block'), 0, 0)

    while (!(WebUI.waitForElementClickable(findTestObject('Map Objs/Continue After select Address'), 5)) && (numTrails < 
    5)) {
        numTrails++

        WebUI.setText(findTestObject('Map Objs/Input search location'), GlobalVariable.Countries[locale])

        WebUI.delay(2)

        WebUI.click(findTestObject('Map Objs/Select 1st location'))

        WebUI.doubleClick(findTestObject('Map Objs/Zoom In'))

        WebUI.delay(2)

        WebUI.clickOffset(findTestObject('Map Objs/Map Block'), 0, 0)
    }
    
    WebUI.click(findTestObject('Map Objs/Continue After select Address'))
}



