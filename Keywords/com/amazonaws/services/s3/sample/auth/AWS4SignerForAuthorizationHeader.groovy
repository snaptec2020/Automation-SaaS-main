package com.amazonaws.services.s3.sample.auth

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
/*************************************/
import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import com.amazonaws.services.s3.sample.util.BinaryUtils;
/************************************/

public class AWS4SignerForAuthorizationHeader extends AWS4SignerBase{

	public AWS4SignerForAuthorizationHeader(URL endpointUrl, String httpMethod,
	String serviceName, String regionName) {
		super(endpointUrl, httpMethod, serviceName, regionName);
	}

	/**
	 * Computes an AWS4 signature for a request, ready for inclusion as an
	 * 'Authorization' header.
	 *
	 * @param headers
	 *            The request headers; 'Host' and 'X-Amz-Date' will be added to
	 *            this set.
	 * @param queryParameters
	 *            Any query parameters that will be added to the endpoint. The
	 *            parameters should be specified in canonical format.
	 * @param bodyHash
	 *            Precomputed SHA256 hash of the request body content; this
	 *            value should also be set as the header 'X-Amz-Content-SHA256'
	 *            for non-streaming uploads.
	 * @param awsAccessKey
	 *            The user's AWS Access Key.
	 * @param awsSecretKey
	 *            The user's AWS Secret Key.
	 * @return The computed authorization string for the request. This value
	 *         needs to be set as the header 'Authorization' on the subsequent
	 *         HTTP request.
	 */
	public String computeSignature(Map<String, String> headers,
			Map<String, String> queryParameters,
			String bodyHash,
			String awsAccessKey,
			String awsSecretKey,
			String securityToken) {
		// first get the date and time for the subsequent request, and convert
		// to ISO 8601 format for use in signature generation
		Date now = new Date();
		String dateTimeStamp = dateTimeFormat.format(now);

		// update the headers with required 'x-amz-date' and 'host' values
		headers.put("x-amz-date", dateTimeStamp);

		String hostHeader = endpointUrl.getHost();
		int port = endpointUrl.getPort();
		if ( port > -1 ) {
			hostHeader.concat(":" + Integer.toString(port));
		}
		headers.put("Host", hostHeader);
		headers.put("x-amz-content-sha256", AWS4SignerBase.getBodySHA256());
		// canonicalize the headers; we need the set of header names as well as the
		// names and values to go into the signature process
		String canonicalizedHeaderNames = getCanonicalizeHeaderNames(headers);
		String canonicalizedHeaders = getCanonicalizedHeaderString(headers);

		// if any query string parameters have been supplied, canonicalize them
		String canonicalizedQueryParameters = getCanonicalizedQueryString(queryParameters);

		// canonicalize the various components of the request
		String canonicalRequest = getCanonicalRequest(endpointUrl, httpMethod,
				canonicalizedQueryParameters, canonicalizedHeaderNames,
				canonicalizedHeaders, bodyHash);
		//		System.out.println("--------- Canonical request --------");
		//		System.out.println(canonicalRequest);
		//		System.out.println("------------------------------------");

		// construct the string to be signed
		String dateStamp = dateStampFormat.format(now);
		String scope =  dateStamp + "/" + regionName + "/" + serviceName + "/" + TERMINATOR;
		String stringToSign = getStringToSign(SCHEME, ALGORITHM, dateTimeStamp, scope, canonicalRequest);
		//		System.out.println("--------- String to sign -----------");
		//		System.out.println(stringToSign);
		//		System.out.println("------------------------------------");
		headers.put("accept"," */*");
		headers.put("accept-language"," en-US,en;q=0.9");
		headers.put("authority","places.geo.eu-west-1.amazonaws.com");
		headers.put("content-type","application/json");
		//headers.put("host","places.geo.eu-west-1.amazonaws.com");
		headers.put("origin","https://release-tanmiah.snaptec.co");
		headers.put("referer","https://release-tanmiah.snaptec.co/");
		headers.put("sec-ch-ua","\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"");
		headers.put("sec-ch-ua-mobile","?0");
		headers.put("sec-ch-ua-platform","\"Windows\"");
		headers.put("sec-fetch-dest","empty");
		headers.put("sec-fetch-mode","cors");
		headers.put("sec-fetch-site","cross-site");
		//headers.put("x-amz-content-sha256", AWS4SignerBase.EMPTY_BODY_SHA256);
		headers.put("x-amz-security-token",securityToken);
		headers.put("x-amz-user-agent","aws-sdk-js/2.1419.0 callback");
		// compute the signing key

		byte[] kSecret = (SCHEME + awsSecretKey).getBytes();
		//System.out.println("---------------2-------------------");
		byte[] kDate = sign(dateStamp, kSecret, "HmacSHA256");
		//System.out.println("---------------3-------------------");
		byte[] kRegion = sign(regionName, kDate, "HmacSHA256");
		//System.out.println("--------------4--------------------");
		byte[] kService = sign(serviceName, kRegion, "HmacSHA256");
		//System.out.println("--------------5--------------------");
		byte[] kSigning = sign(TERMINATOR, kService, "HmacSHA256");
		//System.out.println("-------------6---------------------");
		byte[] signature = sign(stringToSign, kSigning, "HmacSHA256");
		//System.out.println("-------------7---------------------");

		String credentialsAuthorizationHeader =
				"Credential=" + awsAccessKey + "/" + scope;
		String signedHeadersAuthorizationHeader =
				"SignedHeaders=" + canonicalizedHeaderNames;
		String signatureAuthorizationHeader =
				"Signature=" + BinaryUtils.toHex(signature);
		//System.out.println("-------------9---------------------");
		//				System.out.println(credentialsAuthorizationHeader);
		//				System.out.println(signedHeadersAuthorizationHeader);
		//				System.out.println(signatureAuthorizationHeader);
		/*String authorizationHeader = SCHEME + "-" + ALGORITHM + " "
		 + credentialsAuthorizationHeader + ", "
		 + signedHeadersAuthorizationHeader + ", "
		 + signatureAuthorizationHeader;*/
		//System.out.println(authorizationHeader);
		String authorizationHeader = """${SCHEME}-${ALGORITHM} ${credentialsAuthorizationHeader}, ${signedHeadersAuthorizationHeader}, ${signatureAuthorizationHeader}""";
		//System.out.println(authorizationHeader);
		return authorizationHeader;
	}
}
