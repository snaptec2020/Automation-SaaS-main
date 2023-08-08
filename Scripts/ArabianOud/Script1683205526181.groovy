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

WebUI.openBrowser('')

WebUI.navigateToUrl('https://sa.blend.arabianoud.com/en')
for(int i = 1;i<=5;i++) {
WebUI.click(findTestObject('Object Repository/ArabianOud/Page_Arabian Blend/a_Account Login  Register'))

WebUI.click(findTestObject('Object Repository/ArabianOud/Page_Account Login/a_Register'))

WebUI.click(findTestObject('Object Repository/ArabianOud/Page_Register Account/input_Create an Account_firstname'))

WebUI.setText(findTestObject('Object Repository/ArabianOud/Page_Register Account/input_Create an Account_firstname'), 'Abc')

WebUI.setText(findTestObject('Object Repository/ArabianOud/Page_Register Account/input_First Name_lastname'), 'Abc')

WebUI.setText(findTestObject('Object Repository/ArabianOud/Page_Register Account/input_Last Name_email'), 'ahmed@snaptec.co')

WebUI.setText(findTestObject('Object Repository/ArabianOud/Page_Register Account/input_land Islands_telephone'), '561613356')

WebUI.setEncryptedText(findTestObject('Object Repository/ArabianOud/Page_Register Account/input_Telephone_password'), 'roZhOZWSFA6qyP32M1p+Ng==')

WebUI.setEncryptedText(findTestObject('Object Repository/ArabianOud/Page_Register Account/input_Password_confirm'), 'roZhOZWSFA6qyP32M1p+Ng==')

WebUI.click(findTestObject('Object Repository/ArabianOud/Page_Register Account/input_I wish to subscribe to the   newslett_4c072b'))

WebUI.click(findTestObject('Object Repository/ArabianOud/Page_Register Account/input_I have read and agree to the_agree'))

WebUI.click(findTestObject('Object Repository/ArabianOud/Page_Register Account/input_Privacy Policy_button-register'))

WebUI.delay(2)
WebUI.deleteAllCookies()
WebUI.click(findTestObject('Object Repository/ArabianOud/image'))
// TEST
//WebUI.refresh()
//WebUI.delay(3)
}