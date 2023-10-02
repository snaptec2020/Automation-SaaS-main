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
import internal.GlobalVariable

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import org.openqa.selenium.WebElement as Keys

List <WebElement> menuGroup = WebUI.findWebElements(findTestObject('BE/MID/Menus/Menu Groups', [('menuName') : menuGroupName]),30)
menuGroup.any { 
	def isItNotOpen = it.findElement(By.xpath("./following-sibling::div[@class='ReactCollapse--collapse'][1]")).getAttribute("aria-hidden")
	def menuItem = "./following-sibling::div[@class='ReactCollapse--collapse'][1]//div[text()='"+menuItemName+"']"
	//KeywordUtil.logInfo(isItNotOpen.toString())
	if(isItNotOpen.equalsIgnoreCase('true')) {
		//it.findElement(By.xpath("./div[text()='"+menuGroupName+"']")).click()
		it.click()
		it.findElement(By.xpath(menuItem)).click()
	}else {
		//WebUI.click(findTestObject('BE/MID/Menus/Menu Groups', [('menuName') : menuGroupName]))
		it.findElement(By.xpath(menuItem)).click()
	}
	CustomKeywords.'generalactions.generalActions.waiteSpinnerToHide'()
	
}