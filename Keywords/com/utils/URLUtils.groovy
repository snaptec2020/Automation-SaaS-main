package com.utils

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class URLUtils {
    
    @Keyword
    def waitForURLChange(String expectedURL, int timeoutSeconds = 30) {
        WebDriver driver = DriverFactory.getWebDriver()
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds)
        
        try {
            wait.until(ExpectedConditions.urlToBe(expectedURL))
            KeywordUtil.markPassed("URL changed to expected URL: ${expectedURL}")
            return true
        } catch (Exception e) {
            KeywordUtil.markFailed("URL did not change to expected URL within ${timeoutSeconds} seconds")
            return false
        }
    }
    
    @Keyword
    def waitForURLContains(String partialURL, int timeoutSeconds = 30) {
        WebDriver driver = DriverFactory.getWebDriver()
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds)
        
        try {
            wait.until(ExpectedConditions.urlContains(partialURL))
            KeywordUtil.markPassed("URL contains expected text: ${partialURL}")
            return true
        } catch (Exception e) {
            KeywordUtil.markFailed("URL did not contain expected text within ${timeoutSeconds} seconds")
            return false
        }
    }
    
    @Keyword
    def waitForURLChange(String currentURL, String expectedURL, int timeoutSeconds = 30) {
        WebDriver driver = DriverFactory.getWebDriver()
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds)
        
        try {
            // First wait for current URL to change
            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentURL)))
            
            // Then wait for expected URL if provided
            if (expectedURL) {
                wait.until(ExpectedConditions.urlToBe(expectedURL))
            }
            
            KeywordUtil.markPassed("URL changed successfully to: ${driver.getCurrentUrl()}")
            return true
        } catch (Exception e) {
            KeywordUtil.markFailed("URL change failed. Current URL: ${driver.getCurrentUrl()}")
            return false
        }
    }
    
    @Keyword
    def waitForURLPattern(String urlPattern, int timeoutSeconds = 30) {
        WebDriver driver = DriverFactory.getWebDriver()
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds)
        
        try {
            wait.until(ExpectedConditions.urlMatches(urlPattern))
            KeywordUtil.markPassed("URL matches pattern: ${urlPattern}")
            return true
        } catch (Exception e) {
            KeywordUtil.markFailed("URL did not match pattern within ${timeoutSeconds} seconds")
            return false
        }
    }
}