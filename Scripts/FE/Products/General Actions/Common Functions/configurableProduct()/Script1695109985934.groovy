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
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable

import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys

//#Step 3
//TO DO: Search for another key types of configurations
//tb.addProperty('xpath', ConditionType.EQUALS, '//div[contains(@class,\'attributesContainer_attributesContainer\')]//div[contains(@class,\'attributesContainer_optionsList\')]')

TestObject tb = new TestObject()
List genralDropDowns = WebUI.findWebElements(CustomKeywords.'utility.Utility.addXpathToTestObject'('//div[contains(@class,\'attributesContainer_attributesContainer\')]//div[contains(@class,\'attributesContainer_optionsList\')]'), 30)
if (genralDropDowns.size()!=0) {



	def element

	for (int i = 1; i <= genralDropDowns.size(); i++) {
		tb.addProperty('xpath', ConditionType.EQUALS, ('(//div[contains(@class,\'attributesContainer_attributesContainer\')]//div[contains(@class,\'attributesContainer_optionsList\')])[' +
				i) + ']')

		element = WebUiCommonHelper.findWebElement(tb, 30).findElement(By.xpath(('(//div[contains(@class,\'attributesContainer_attributesContainer\')]//div[contains(@class,\'attributesContainer_optionsList\')])[' +
				i) + ']//child::*[contains(@class,\'styles_dropdownOption\') or contains(@class,\'styles_swatchOption\') or contains(@class,\'styles_checkboxOption\')  or contains(@class,\'styles_badgeOption\')  or (@src and @alt)]')).getAttribute(
				'class')

		//println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +element.toString())
		switch (element) {
			case ~('.*styles_dropdownOption.*') :
				tb.addProperty('xpath', ConditionType.EQUALS, ('(//div[contains(@class,\'attributesContainer_attributesContainer\')]//div[contains(@class,\'attributesContainer_optionsList\')])[' +
				i) + ']//following-sibling::*[@class]//*[contains(@class,\'general-dropdown__button\')]')
			//WebUI.scrollToElement(tb, 10)

				WebUI.click(tb)


			/*						tb.addProperty('xpath', ConditionType.EQUALS, ('(//div[contains(@class,\'attributesContainer_attributesContainer\')]//div[contains(@class,\'attributesContainer_optionsList\')])[' +
			 i) + ']//following-sibling::*[@class]//*[contains(@class,\'general-dropdown__button\')]//following-sibling::*[contains(@class,\'general-dropdown__menu\')]//li[1]')
			 */
				tb.addProperty('xpath', ConditionType.EQUALS, ('((//div[contains(@class,\'attributesContainer_attributesContainer\')]//div[contains(@class,\'attributesContainer_optionsList\')])[' +
						i) + ']//following-sibling::*[@class]//*[contains(@class,\'general-dropdown__button\')]//following-sibling::*[contains(@class,\'general-dropdown__menu\')]//li[@class=\'menu__item\']//button[not(@disabled)])[1]')
				WebUI.click(tb)

				break
			case ~('.*styles_checkboxOption.*') :
				tb.addProperty('xpath', ConditionType.EQUALS, ('(//div[contains(@class,\'attributesContainer_attributesContainer\')]//div[contains(@class,\'attributesContainer_optionsList\')])[' +
				i) + ']//span[contains(@class,\'styles_checkboxOption\')][1]//input')

				WebUI.check(tb)

				break
			case ~('.*styles_swatchOption.*') :
				tb.addProperty('xpath', ConditionType.EQUALS, ('(//div[contains(@class,\'attributesContainer_attributesContainer\')]//div[contains(@class,\'attributesContainer_optionsList\')])[' +
				i) + ']//span[contains(@class,\'styles_swatchOption\')][1]')

				WebUI.check(tb)
				break
			case ~('.*styles_badgeOption.*') :
				tb.addProperty('xpath', ConditionType.EQUALS, ('(//div[contains(@class,\'attributesContainer_attributesContainer\')]//div[contains(@class,\'attributesContainer_optionsList\')])[' +
				i) + ']//button[contains(@class,\'styles_badgeOption\')][1]')

				WebUI.check(tb)
				break
			case '' :
				tb.addProperty('xpath', ConditionType.EQUALS, ('(//div[contains(@class,\'attributesContainer_attributesContainer\')]//div[contains(@class,\'attributesContainer_optionsList\')])[' +
				i) + ']//img[@src][1]')

				WebUI.check(tb)
				break
		}
	}
}