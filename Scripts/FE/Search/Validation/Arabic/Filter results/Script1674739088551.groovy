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
import internal.GlobalVariable

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.remote.server.handler.FindElement
import org.openqa.selenium.remote.server.handler.FindElements

import java.util.List


WebUI.callTestCase(findTestCase('Test Cases/FE/Search/Verification/Arabic/Verify elemnts for the search'), [:], FailureHandling.STOP_ON_FAILURE)


WebUI.setText(findTestObject('Object Repository/Search contents/Search box/Search Test box'), 'Test ')

WebUI.verifyElementVisible(findTestObject('Object Repository/Search contents/Search box/Search results container'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Search contents/Search button'))

WebUI.click(findTestObject('Object Repository/Search contents/Search button'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('Object Repository/Search contents/Search page/Filter button'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Search contents/Search page/Price filter'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Search contents/Search page/Sider button to change price'))

WebUI.click(findTestObject('Object Repository/Search contents/Search page/Sider button to change price'), FailureHandling.STOP_ON_FAILURE)


WebUI.verifyElementVisible(findTestObject('Object Repository/Search contents/Search page/Price Range button'))



WebUI.dragAndDropByOffset(findTestObject('Object Repository/Search contents/Search page/Sider button to change price'), 0, 40)


WebUI.delay(10)


WebUI.waitForElementPresent(findTestObject('Object Repository/Products/List of products'), 10)


List products = WebUI.findWebElements(findTestObject('Object Repository/Products/List of products'),30)



int size = products.size()

if (size > 0) {
for (int i = 0; i < size; i++) {
products.get(i).click()
}
}




