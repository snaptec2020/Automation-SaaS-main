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
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

//WebUI.callTestCase(findTestCase('FE/Check out/verification/Verification Check out components after click on proceed'), [:], 
// FailureHandling.STOP_ON_FAILURE)
//CustomKeywords.'generalactions.notificationsObject.verifyNotificationVisble'('عنوانك ينقصه بعض المعلومات.', 'Your address are missing some informations.')
WebUI.callTestCase(findTestCase('FE/Check out/validation/Add Location by Map'), [:], FailureHandling.STOP_ON_FAILURE)
//Country is not available
//Your address are missing some informations.
def locationSuccessMessage=CustomKeywords.'generalactions.notificationsObject.getMessageText'()
KeywordUtil.logInfo(locationSuccessMessage)
switch (locationSuccessMessage) {
	case 'عنوانك ينقصه بعض المعلومات.' :
	case 'Your address are missing some informations.': 
		for (int i=1 ; i<=3; i++) {
			tryToGetLocation()
			locationSuccessMessage=CustomKeywords.'generalactions.notificationsObject.getMessageText'()
			switch (locationSuccessMessage) {
				case 'Save Shipping Address successfully':
				case 'حفظ عنوان الشحن بنجاح':
				break;
			}
			
			
			/*if (!(CustomKeywords.'generalactions.notificationsObject.verifyNotificationVisble'('عنوانك ينقصه بعض المعلومات',
				'Save Shipping Address successfully'))) {
				break
			}*/
			
		}
		WebUI.callTestCase(findTestCase('FE/Check out/validation/Add location Manually'), [:], FailureHandling.STOP_ON_FAILURE)
		break;
		case 'Save Shipping Address successfully':
		case 'حفظ عنوان الشحن بنجاح':
		break; 
		//case 'Country is not available':
		//case 'الدولة غير متاحة':
		//WebUI.callTestCase(findTestCase('FE/Check out/validation/Add location Manually'), [:], FailureHandling.STOP_ON_FAILURE)
		//break;
    
	
        
}




/*if (CustomKeywords.'generalactions.notificationsObject.verifyNotificationVisble'('عنوانك ينقصه بعض المعلومات.', 'Your address are missing some informations.')) {
    KeywordUtil.markPassed('Try to get another location')

    for (def index : (0..2)) {
        tryToGetLocation()

        if (!(CustomKeywords.'generalactions.notificationsObject.verifyNotificationVisble'('عنوانك ينقصه بعض المعلومات.', 
            'Your address are missing some informations.'))) {
            break
        }
    }
} else {
    
}*/

def tryToGetLocation() {
    WebUI.click(findTestObject('Check Out/Zoom In (Map)'))

   // int mapHeight = WebUI.getElementHeight(findTestObject('Object Repository/Check Out/Map Block')) / 2

    //int mapWidth = WebUI.getElementWidth(findTestObject('Object Repository/Check Out/Map Block')) / 2

    WebUI.clickOffset(findTestObject('Check Out/Map Block'), 0, 0)

    WebUI.click(findTestObject('Check Out/Save location Map Button'))
}

