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
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys


WebUI.callTestCase(findTestCase('Test Cases/FE/Wishlist/Validations/Wishlist after sign in/Wishlist is not empty'), [:], FailureHandling.STOP_ON_FAILURE)



WebUI.click(findTestObject('Object Repository/WishList/Share WishList'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Object Repository/WishList/Text area for email'), 5, FailureHandling.STOP_ON_FAILURE)
//WebUI.click(findTestObject('Object Repository/WishList/Empty WishList'));

WebUI.setText(findTestObject('Object Repository/WishList/Text area for email'), Emails)

WebUI.setText(findTestObject('Object Repository/WishList/Mesage in the share'), Message)



WebUI.click(findTestObject('Object Repository/WishList/Share button'), FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'generalactions.notificationsObject.verifyNotificationVisble'('لقد تم مشاركة قائمة رغباتكم.', 'Your wish list has been shared.')



