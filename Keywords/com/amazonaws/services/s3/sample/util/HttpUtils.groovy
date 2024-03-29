package com.amazonaws.services.s3.sample.util

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
/***************************************/
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
/**************************************/
public class HttpUtils {

	/**
	 * Makes a http request to the specified endpoint
	 */
	public static String invokeHttpRequest(URL endpointUrl,
			String httpMethod,
			Map<String, String> headers,
			String requestBody) {
		HttpURLConnection connection = createHttpConnection(endpointUrl, httpMethod, headers);
		try {
			if ( requestBody != null ) {
				DataOutputStream wr = new DataOutputStream(
						connection.getOutputStream());
				wr.writeBytes(requestBody);
				wr.flush();
				wr.close();
			}
		} catch (Exception e) {
			throw new RuntimeException("Request failed. " + e.getMessage(), e);
		}
		return executeHttpRequest(connection);
	}

	public static String executeHttpRequest(HttpURLConnection connection) {
		try {
			// Get Response
			InputStream is;
			try {
				is = connection.getInputStream();
			} catch (IOException e) {
				is = connection.getErrorStream();
			}

			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();
		} catch (Exception e) {
			throw new RuntimeException("Request failed. " + e.getMessage(), e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public static HttpURLConnection createHttpConnection(URL endpointUrl,
			String httpMethod,
			Map<String, String> headers) {
		try {
			HttpURLConnection connection = (HttpURLConnection) endpointUrl.openConnection();
			connection.setRequestMethod(httpMethod);

			if ( headers != null ) {
				//System.out.println("--------- Request headers ---------");
				for ( String headerKey : headers.keySet() ) {
					//System.out.println(headerKey + ": " + headers.get(headerKey));
					connection.setRequestProperty(headerKey, headers.get(headerKey));
				}
			}

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			return connection;
		} catch (Exception e) {
			throw new RuntimeException("Cannot create connection. " + e.getMessage(), e);
		}
	}

	public static String urlEncode(String url, boolean keepPathSlash) {
		String encoded;
		try {
			encoded = URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("UTF-8 encoding is not supported.", e);
		}
		if ( keepPathSlash ) {
			encoded = encoded.replace("%2F", "/");
		}
		return encoded;
	}
}
