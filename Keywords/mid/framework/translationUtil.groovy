package mid.framework

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
import com.kms.katalon.core.configuration.RunConfiguration
import groovy.json.JsonSlurper
import internal.GlobalVariable
import java.nio.file.Files
import java.nio.file.Paths

public class translationUtil {

	private static Map<String, Map<String, String>> translationCache = [:]


	private static Map<String, String> jsonFileToMap(String filename = "") {
		if (translationCache.containsKey(filename)) {
			return translationCache.get(filename)
		}

		String filePath = (GlobalVariable.MID == '1' ? 'MID/' : 'SaaS/') + filename + '.json'
		String fullPath = RunConfiguration.getProjectDir() + '/Resources/' + filePath
		File file = new File(fullPath)

		if (!file.exists()) {
			println "File not found: ${fullPath}"
			return [:]
		}

		def jsonSlurper = new JsonSlurper()
		Map<String, String> mapData = jsonSlurper.parse(file)
		translationCache[filename] = mapData

		return mapData
	}


	static String gettext(String text) {
		String englishRegex = /^[a-zA-Z@.!?\\-\\/\\\\<>{}'“”‘’&|(),0-9\\s=:._%#"]*$/

		if (text.matches(englishRegex)) {
			if (GlobalVariable.locale == 'en') {
				return text
			} else {
				String locale = GlobalVariable.locale ?: 'en'
				Map<String, String> data = jsonFileToMap(locale)
				String translatedText = data.get(text.toLowerCase())

				if (translatedText == null || translatedText.trim().isEmpty()) {
					return text
				} else {
					return translatedText
				}
			}
		} else {
			return text
		}
	}


	static String translation(String text) {
		return gettext(text)
	}


	static List<String> arrayOfTranslation(List<String> textList) {
		List<String> translatedTextList = []

		for (String text : textList) {
			translatedTextList.add(gettext(text))
		}

		return translatedTextList
	}


	static void convertKeysToLowerCase() {
		Map<String, String> data = jsonFileToMap("ar")
		Map<String, String> lowerCaseData = data.collectEntries { [(it.key.toLowerCase()): it.value] }
		saveToFile(lowerCaseData, "test2.json")
	}


	private static void saveToFile(Map<String, String> data, String filename) {
		String json = groovy.json.JsonOutput.toJson(data)
		String fullPath = RunConfiguration.getProjectDir() + "/Resources/" + filename
		Files.write(Paths.get(fullPath), json.getBytes("UTF-8"))
		println "Dictionary saved to ${fullPath}"
	}
}