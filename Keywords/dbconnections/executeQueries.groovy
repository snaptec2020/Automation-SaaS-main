package dbconnections

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.db.DatabaseConnection
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.DBData
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.entity.global.GlobalVariableEntity
//import com.sun.org.apache.bcel.internal.generic.RETURN

import internal.GlobalVariable

public class executeQueries {

	DatabaseConnection conn = new DatabaseConnection(GlobalVariable.gDBUrl,GlobalVariable.gDBUserName,GlobalVariable.gDBPassword)
	@Keyword
	def getDataFromDBByQuery(def mQuery) {
		DBData dBag = new DBData(conn, mQuery)
		return dBag
	}

	@Keyword
	def getOtp(def mobileNumber) {
		def mQuery = "SELECT otp_code FROM release_clone.magedelight_smsprofileotp where customer_mobile like'%"+mobileNumber+"' order by updated_at desc LIMIT 1;"
		DBData dBag = getDataFromDBByQuery(mQuery)

		if(!dBag.allData.empty){
			return dBag.getValue('otp_code',1)//allData.first().toString()
		} else {
			return 'No OTP please re-send it again'
		}
	}
}
