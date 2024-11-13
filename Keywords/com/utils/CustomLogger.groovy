package com.utils

import com.kms.katalon.core.util.KeywordUtil
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

public class CustomLogger {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

	public static void logInfo(String message) {
		String timestamp = LocalDateTime.now().format(formatter)
		String formattedMessage = "[INFO] ${timestamp} - ${message}"
		KeywordUtil.logInfo(formattedMessage)
	}

	public static void logError(String message) {
		String timestamp = LocalDateTime.now().format(formatter)
		String formattedMessage = "[ERROR] ${timestamp} - ${message}"
		KeywordUtil.markError(formattedMessage)
	}

	public static void logWarning(String message) {
		String timestamp = LocalDateTime.now().format(formatter)
		String formattedMessage = "[WARNING] ${timestamp} - ${message}"
		KeywordUtil.markWarning(formattedMessage)
	}

	public static void logPassed(String message) {
		String timestamp = LocalDateTime.now().format(formatter)
		String formattedMessage = "[PASSED] ${timestamp} - ${message}"
		KeywordUtil.markPassed(formattedMessage)
	}

	public static void logFailed(String message) {
		String timestamp = LocalDateTime.now().format(formatter)
		String formattedMessage = "[FAILED] ${timestamp} - ${message}"
		KeywordUtil.markFailed(formattedMessage)
	}
}