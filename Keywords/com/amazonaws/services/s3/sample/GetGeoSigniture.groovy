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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
/********************************************************/
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


import com.amazonaws.services.s3.sample.auth.AWS4SignerBase;
import com.amazonaws.services.s3.sample.auth.AWS4SignerForAuthorizationHeader;
import com.amazonaws.services.s3.sample.util.HttpUtils;
/*******************************************************/

public class GetGeoSigniture extends AWS4SignerBase{
	public static def getGeoSign(String regionName, String awsAccessKey, String awsSecretKey, String securityToken,String theLongitude, String theLatitude) {
		//		System.out.println("*******************************************************");
		//		System.out.println("*  Executing sample 'GetObjectUsingHostedAddressing'  *");
		//		System.out.println("*******************************************************");

		// the region-specific endpoint to the target object expressed in path style
		URL endpointUrl;
		try {
			endpointUrl = new URL("https://places.geo.eu-west-1.amazonaws.com/places/v0/indexes/placeIndex1f250acc-saaspro/search/position");

		} catch (MalformedURLException e) {
			throw new RuntimeException("Unable to parse service endpoint: " + e.getMessage());
		}
		String bodyContnt= "{\"Position\":["+theLongitude+","+theLatitude+"],\"Language\":\"en\",\"MaxResults\":2}";
		InputStream targetStream = new ByteArrayInputStream(bodyContnt.getBytes());
		// for a simple GET, we have no body so supply the precomputed 'empty' hash
		Map<String, String> headers = new HashMap<String, String>();

		String bodyHash = getBodyHash(bodyContnt)
		setBodySHA256(bodyHash)

		AWS4SignerForAuthorizationHeader signer = new AWS4SignerForAuthorizationHeader(
				endpointUrl, "POST", "geo", regionName);
		String authorization = signer.computeSignature(headers,
				null, // no query parameters
				AWS4SignerBase.getBodySHA256(),
				awsAccessKey,
				awsSecretKey,
				securityToken);

		// place the computed signature into a formatted 'Authorization' header
		// and call S3

		headers.put("Authorization", authorization);
		headers.put("content-Length","67");
		//System.out.println("----------------1------------------");
		String response = HttpUtils.invokeHttpRequest(endpointUrl, "POST", headers, bodyContnt);
		//System.out.println("----------------1------------------");
		//		System.out.println("--------- Response content ---------");
		//		System.out.println(bodyContnt)
		//		System.out.println(response);
		//		System.out.println("------------------------------------");
		return response
	}

}
