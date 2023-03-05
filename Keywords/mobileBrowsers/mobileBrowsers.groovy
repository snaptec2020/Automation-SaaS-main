package mobileBrowsers

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.github.kklisura.cdt.protocol.commands.Emulation as Emulation
import com.github.kklisura.cdt.protocol.commands.Page as Page
import com.github.kklisura.cdt.protocol.types.page.CaptureScreenshotFormat as CaptureScreenshotFormat
import com.github.kklisura.cdt.services.ChromeDevToolsService as ChromeDevToolsService
import com.katalon.cdp.CdpUtils
import internal.GlobalVariable
import com.github.kklisura.cdt.protocol.commands.Fetch as Fetch


public class mobileBrowsers {




	ChromeDevToolsService cdts = CdpUtils.getService()


	@Keyword
	def captureFullPageScreenshot(String  Width,String Height) {

		double width  = Width as double

		double height = Height as double

		Page page = cdts.getPage()



		page.enable()



		Emulation emulation = cdts.getEmulation()

		emulation.setDeviceMetricsOverride(Double.valueOf(width).intValue(), Double.valueOf(height).intValue(), 1.0, Boolean.FALSE)

		emulation.setScrollbarsHidden(Boolean.TRUE)



		emulation.setScrollbarsHidden(Boolean.FALSE)
	}
}
