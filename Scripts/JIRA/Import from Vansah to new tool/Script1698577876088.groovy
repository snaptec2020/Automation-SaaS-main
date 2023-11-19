import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.nio.file.WatchService as WatchService
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.Variable as Variable
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

TestData td = findTestData('Data Files/APIs/JIRA/ST TC')

def response

for (int i = 1; i <= td.allData.size(); i++) {
    if (!(td.getValue(1, i).toString().isEmpty())) {
        createTestCase(i)
		WS.delay(2)
        createTestStep(i)
		WS.delay(2)
    } else {
        createTestStep(i)
		WS.delay(2)
    }
} //testcaseInfo.get



def createTestCase(int rowNumber) {
    TestData td = findTestData('Data Files/APIs/JIRA/ST TC')
	StringBuilder sb = new StringBuilder();
	StringBuilder sb1 = new StringBuilder();
    def testcaseInfo = [:]

    def response = CustomKeywords.'generalactions.generalStrings.jsonParser'(WS.sendRequest(findTestObject('APIs/JIRA/Postman/Create anonymous ticket')).getResponseText())
	response = CustomKeywords.'generalactions.generalStrings.jsonParser'(WS.sendRequest(findTestObject('APIs/JIRA/Postman/Create ticket',
		[('issuelinks-issues') : td.getValue(13, rowNumber).toString().replace('"', '*'), ('description') : sb.append(td.getValue(
				4, rowNumber)).toString().replaceAll("[^?!a-zA-Z0-9\\s]", "*").replaceAll("\\n", "-->"), ('summary') : sb1.append('MID - '+td.getValue(3, rowNumber)).toString().replaceAll("[^?!a-zA-Z0-9\\s]", "*").replaceAll("\\n", "-->"), ('formToken') : response.formToken, ('atl_token') : response.atl_token])).getResponseText())
   /* response = CustomKeywords.'generalactions.generalStrings.jsonParser'(WS.sendRequest(findTestObject('APIs/JIRA/Postman/Create ticket', 
                [('issuelinks-issues') : td.getValue(13, rowNumber).toString().replace('"', '*'), ('description') : td.getValue(
                        4, rowNumber).toString().replace('"', '*'), ('summary') : 'MID - '+td.getValue(3, rowNumber).toString().replace(
                        '"', '*'), ('formToken') : response.formToken, ('atl_token') : response.atl_token])).getResponseText())*/

    testcaseInfo.put('issue.key', response.issueKey)

    testcaseInfo.put('issue.id', response.issueId.toString())

    testCaseInfo = testcaseInfo
}

def createTestStep(int rowNumber) {
    TestData td = findTestData('Data Files/APIs/JIRA/ST TC')
	StringBuilder sb = new StringBuilder();
	StringBuilder sb1 = new StringBuilder();
    def response = CustomKeywords.'generalactions.generalStrings.jsonParser'(WS.sendRequest(findTestObject('APIs/JIRA/Postman/Token for test step', 
                [('issue.id') : testCaseInfo.get('issue.id'), ('issue.key') : testCaseInfo.get('issue.key')])).getResponseText())

    response = CustomKeywords.'generalactions.generalStrings.jsonParser'(WS.sendRequest(findTestObject('APIs/JIRA/Postman/Add Step', 
                [('testCaseId') : testCaseInfo.get('issue.id'), ('result') : sb.append(td.getValue(20, rowNumber)).toString().replaceAll("[^?!a-zA-Z0-9\\s]", "*").replaceAll("\\n", "-->"), ('step') : sb1.append(td.getValue(
                        18, rowNumber)).toString().replaceAll("[^?!a-zA-Z0-9\\s]", "*").replaceAll("\\n", "-->"), ('jwt') : response.contextJwt])).getResponseText())
	/*response = CustomKeywords.'generalactions.generalStrings.jsonParser'(WS.sendRequest(findTestObject('APIs/JIRA/Postman/Add Step',
		[('testCaseId') : testCaseInfo.get('issue.id'), ('result') : td.getValue(20, rowNumber).toString().replaceAll("\n", " --> "), ('step') : td.getValue(
				18, rowNumber).toString().replaceAll("\n", " --> "), ('jwt') : response.contextJwt])).getResponseText())*/
}

