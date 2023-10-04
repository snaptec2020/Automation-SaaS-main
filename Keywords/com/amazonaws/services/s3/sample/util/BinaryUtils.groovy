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
import java.util.Locale;

public class BinaryUtils {

	/**
	 * Converts byte data to a Hex-encoded string.
	 *
	 * @param data
	 *            data to hex encode.
	 *
	 * @return hex-encoded string.
	 */
	public static String toHex(byte[] data) {
		StringBuilder sb = new StringBuilder(data.length * 2);
		for (int i = 0; i < data.length; i++) {
			String hex = Integer.toHexString(data[i]);
			if (hex.length() == 1) {
				// Append leading zero.
				sb.append("0");
			} else if (hex.length() == 8) {
				// Remove ff prefix from negative numbers.
				hex = hex.substring(6);
			}
			sb.append(hex);
		}
		return sb.toString().toLowerCase(Locale.getDefault());
	}

	/**
	 * Converts a Hex-encoded data string to the original byte data.
	 *
	 * @param hexData
	 *            hex-encoded data to decode.
	 * @return decoded data from the hex string.
	 */
	public static byte[] fromHex(String hexData) {
		byte[] result = new byte[(hexData.length() + 1) / 2];
		String hexNumber = null;
		int stringOffset = 0;
		int byteOffset = 0;
		while (stringOffset < hexData.length()) {
			hexNumber = hexData.substring(stringOffset, stringOffset + 2);
			stringOffset += 2;
			result[byteOffset++] = (byte) Integer.parseInt(hexNumber, 16);
		}
		return result;
	}
}
