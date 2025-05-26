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
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.apache.commons.lang3.StringUtils as StringUtils
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
//def countryResponse = CustomKeywords.'generalactions.generalStrings.jsonParser'(WS.sendRequestAndVerify(findTestObject('APIs/Postman/Get country', 
//            [('URL') : GlobalVariable.URL, ('store') : store])).getResponseText())
//
//def locale = countryResponse.data.countries[0].full_name_english.toString().toLowerCase( //new Locale('en', countryCode)
//    )
if (WebUI.waitForElementClickable(findTestObject('Map Objs/Pick from map btn'), 5) || !isSelectFromMap ) { 
    def locatorZonesResponse = CustomKeywords.'generalactions.generalStrings.jsonParser'(WS.sendRequestAndVerify(findTestObject(
                'APIs/Postman/Get locator Zones', [('URL') : GlobalVariable.URL, ('store') : store])).getResponseText( //WebUI.executeJavaScript(script, null)
            ))

    if (isSelectFromMap) {
        WebUI.click(findTestObject('Map Objs/Pick from map btn'))

        def coordinates = []

        locatorZonesResponse.data.getLocatorZones.each({ def zone ->
                zone.coordinate.each({ def coordinate ->
                        def lat = coordinate.latitude

                        def lon = coordinate.longitude

                        coordinates << [('latitude') : lat, ('longitude') : lon]
                    })
            })

        coordinates.find({ def coord ->
                def latitude = coord.latitude

                def longitude = coord.longitude

                // Format the coordinates with 6 decimal places
                def latLong = "$latitude, $longitude"

                //
                KeywordUtil.logInfo("$latitude,$longitude")

                WebUI.sendKeys(findTestObject('Map Objs/Input search location'), latLong)

                //WebUI.delay(1)
                WebUI.sendKeys(findTestObject('Map Objs/Input search location'), Keys.chord(Keys.SPACE))

                //WebUI.delay(2)
                WebUI.click(findTestObject('Map Objs/Select 1st location'))

                // Condition to stop the loop
                if (WebUI.waitForElementClickable(findTestObject('Map Objs/Continue After select Address'), 5)) {
                    WebUI.click(findTestObject('Map Objs/Continue After select Address'))

                    return true // This stops the loop
                }
                
                return false // Continue the loop
            })
    } else {
		WebUI.click(findTestObject('Map Objs/Change address button'))
       // WebUI.verifyElementPresent(findTestObject('PickUp/Pickup popup'), 5)

        WebUI.click(findTestObject('PickUp/PickupButton'))

        //WebUI.verifyElementPresent(findTestObject('PickUp/Map PopUp'), 5)
		
		

        WebUI.doubleClick(findTestObject('PickUp/first option from adderss list'))

        WebUI.click(findTestObject('PickUp/Select branch button'))
    }
    //WebUI.setText(findTestObject('Map Objs/Input search location'), GlobalVariable.Countries[locale])
} else {
    WebUI.click(findTestObject('Map Objs/Change address button'))

    WebUI.click(findTestObject('Map Objs/Pick from map btn'))

    int numTrails = 0

    WebUI.clickOffset(findTestObject('Map Objs/Map Block'), 0, 0)

    def theCapitalOfCountry = WebUI.getText(findTestObject('Map Objs/Current Location'))

    while (!(WebUI.waitForElementClickable(findTestObject('Map Objs/Continue After select Address'), 5)) && (numTrails < 
    5)) {
        numTrails++

        WebUI.setText(findTestObject('Map Objs/Input search location'), theCapitalOfCountry)

        WebUI.delay(2)

        WebUI.click(findTestObject('Map Objs/Select 1st location'))

        WebUI.doubleClick(findTestObject('Map Objs/Zoom In'))

        WebUI.delay(2)

        WebUI.clickOffset(findTestObject('Map Objs/Map Block'), 0, 0)
    }
    
    WebUI.click(findTestObject('Map Objs/Continue After select Address'))
}

GlobalVariable.isFirstTime = false
WebUI.callTestCase(findTestCase('FE/Scrolling/scrollingAtTheBottom'), [:], FailureHandling.CONTINUE_ON_FAILURE)

