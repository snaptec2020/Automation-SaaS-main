import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

Mobile.startApplication('C:\\Users\\aboel\\Downloads\\TanmiahProd.apk', true)

Mobile.tap(findTestObject('Object Repository/android.widget.FrameLayout'), 0)

Mobile.tap(findTestObject('Object Repository/android.widget.TextView - Pick Address on Map'), 0)

Mobile.sendKeys(findTestObject('Object Repository/android.widget.EditText - Search a city'), 'Makka')

Mobile.tap(findTestObject('Object Repository/android.widget.ImageView'), 0)

Mobile.tap(findTestObject('Object Repository/android.widget.TextView - Makkah, Saudi Arabia'), 0)

Mobile.tap(findTestObject('Object Repository/android.widget.TextView - Continue'), 0)

Mobile.closeApplication()

Mobile.setText(findTestObject('Object Repository/android.widget.EditText - 500-000-000'), '581492572', 0)

