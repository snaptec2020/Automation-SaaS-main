import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase

import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.reader.CsvWriter
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.net.URL;

String token = CustomKeywords.'generalactions.generalStrings.jsonParser'(WS.sendRequest(findTestObject('APIs/MID Azyan Excel/Postman/Get token')).getResponseText())
appendCSV("Main SKU","Child SKU","Expected QTY","Real QTY","Real Salable QTY")
token = token.replaceAll('"', '')
TestData td = findTestData('Data Files/APIs/Azyan/Azyan')
int count = 0
for(def currentId:td.allData ) {
	count++
 def response = CustomKeywords.'generalactions.generalStrings.jsonParser'(WS.sendRequest(findTestObject('APIs/MID Azyan Excel/Postman/Get Configurable', [('token') : token, ('confItemId') : td.getValue(2, count)])).getResponseText())
 response.items.each { 
	 //KeywordUtil.logInfo()
def simpleResp = CustomKeywords.'generalactions.generalStrings.jsonParser'(WS.sendRequest(findTestObject('APIs/MID Azyan Excel/Postman/Search for simple', [('token') : token, ('simpleProductId') : it.sku])).getResponseText())
	appendCSV(td.getValue(2, count).toString(),(it.sku).toString(),td.getValue(8, count).toString(),(simpleResp.items[0].extension_attributes.stock_item.qty).toString(),(simpleResp.items[0].extension_attributes.salable_qty).toString())
 }
}

def appendCSV(String[] record) {
		String fileUrl = "C:/Users/aboel/Downloads/AzyanCSV.csv";
	
		CSVWriter writer = new CSVWriter(new FileWriter(fileUrl, true));
	
		//Create record
		//String[] record = ["3","Rahul","Vaidya","India","35"];
	
		//Write the record to file
		writer.writeNext(record, false);
	
		//close the writer
		writer.close();
}
//WS.sendRequest(findTestObject('APIs/MID Azyan Excel/Postman/Search for simple', [('token') : token]))



