package com.amazonaws.services.s3.sample

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
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.amazonaws.services.s3.sample.GetGeoSigniture
import generalactions.generalStrings
import internal.GlobalVariable

public class getAPIResults {
	def generalStrings = new generalStrings()
	@Keyword
	def getAPIResultsByLatLong(String awsAccessKey,String awsSecretKey,String SecurityToken,String regionName,String theLongitude, String theLatitude) {
		def results = generalStrings.jsonParser(GetGeoSigniture.getGeoSign(regionName, awsAccessKey, awsSecretKey,SecurityToken,theLongitude, theLatitude));

		//KeywordUtil.logInfo(results.Results.Place.Label[1].toString())
		return results.Results.Place.Label
	}
}
