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

def randomString = CustomKeywords.'mid.framework.generalAction.generateRandomString'()
def randomPhoneNumber = ('5' + (Math.random() * 90000000 + 10000000).toLong())


WebUI.callTestCase(findTestCase('BE/MID/Customers/All Customers/Open Customer Page'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'mid.framework.generalAction.clickOnDependOnName'('Add New Customer')

CustomKeywords.'mid.framework.generalAction.verifyHeaderText'('Add New Customer')

//CustomKeywords.'mid.framework.generalAction.setTextToInputFieldDependOnName'('first name', randomString )
//
//CustomKeywords.'mid.framework.generalAction.setTextToInputFieldDependOnName'('email', randomString+"@example.com")
//
//CustomKeywords.'mid.framework.generalAction.setTextToInputFieldDependOnName'('last name', randomString)
//
//CustomKeywords.'mid.framework.generalAction.setTextToInputFieldDependOnName'('Mobile Number', randomPhoneNumber)

CustomKeywords.'mid.framework.generalAction.setDate'('18-12-2024')

CustomKeywords.'mid.framework.generalAction.unableFieldDependOnName'('Allow remote shopping assistance')

CustomKeywords.'mid.framework.generalAction.unableFieldDependOnName'('Sales Notification')

CustomKeywords.'mid.framework.generalAction.unableFieldDependOnName'('New Arrivals Notification')

CustomKeywords.'mid.framework.generalAction.unableFieldDependOnName'('Delivery Status Changes Notification')

CustomKeywords.'mid.framework.generalAction.selectOptionDependOnName'('Associate to Website','Snaptec')

CustomKeywords.'mid.framework.generalAction.selectOptionDependOnName'('Group','New Group')

CustomKeywords.'mid.framework.generalAction.selectOptionDependOnName'('Send Welcome Email From','Select store view')

CustomKeywords.'mid.framework.generalAction.selectOptionDependOnName'('Gender','Male')

CustomKeywords.'mid.framework.generalAction.clickOnDependOnName'('Save Customer')

