package generalactions

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.utils.CustomLogger

public class generalActions {
	private static final int MAX_ATTEMPTS = 10
	private static final int WAIT_TIMEOUT = 1

	@Keyword
	def waiteSpinnerToHide() {
		def spinnerObject = findTestObject('Spinner')
		WebDriver driver = DriverFactory.getWebDriver()
		int attemptCount = 0
		// Record start time
		long startTime = System.currentTimeMillis()
		while (attemptCount < MAX_ATTEMPTS) {
			try {

				//				boolean spinnerExists = WebUI.waitForElementPresent(spinnerObject, WAIT_TIMEOUT, FailureHandling.OPTIONAL)
				//
				//				if (!spinnerExists) {
				//					CustomLogger.logInfo("Spinner is not present in DOM")
				//					return
				//				}

				// Check if spinner is visible
				boolean isVisible = WebUI.waitForElementVisible(spinnerObject, WAIT_TIMEOUT, FailureHandling.OPTIONAL)

				if (!isVisible) {
                // Calculate elapsed time and log
                long endTime = System.currentTimeMillis()
                double elapsedSeconds = (endTime - startTime) / 1000.0
                CustomLogger.logInfo("Spinner is not visible. Total wait time: ${String.format('%.2f', elapsedSeconds)} seconds")
                return
				}

				// If still visible, wait a bit before next attempt
				//				WebUI.delay(WAIT_TIMEOUT)
				attemptCount++
			} catch (StaleElementReferenceException e) {
				// If we get a stale element exception, it likely means the spinner has been removed
	            long endTime = System.currentTimeMillis()
	            double elapsedSeconds = (endTime - startTime) / 1000.0
	            CustomLogger.logInfo("Spinner appears to be gone (StaleElementReferenceException). Total wait time: ${String.format('%.2f', elapsedSeconds)} seconds")
	            return
			} catch (NoSuchElementException e) {
	            long endTime = System.currentTimeMillis()
	            double elapsedSeconds = (endTime - startTime) / 1000.0
	            CustomLogger.logInfo("Spinner not found in DOM. Total wait time: ${String.format('%.2f', elapsedSeconds)} seconds")
	            return
			}catch (Exception e) {
            // For any other exception, log it and consider the spinner gone
	            long endTime = System.currentTimeMillis()
	            double elapsedSeconds = (endTime - startTime) / 1000.0
	            CustomLogger.logInfo("Exception while checking spinner: ${e.class.simpleName}. Assuming spinner is gone. Total wait time: ${String.format('%.2f', elapsedSeconds)} seconds")
	            return
			}
		}

    long endTime = System.currentTimeMillis()
    double elapsedSeconds = (endTime - startTime) / 1000.0
    CustomLogger.logWarning("Spinner remained visible after ${MAX_ATTEMPTS} attempts. Total wait time: ${String.format('%.2f', elapsedSeconds)} seconds")

	}

	@Keyword
	def waitForSpinnerWithRetry() {
		def spinnerObject = findTestObject('Spinner')
		WebDriver driver = DriverFactory.getWebDriver()
		WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT)
		int attemptCount = 0

		while (attemptCount < MAX_ATTEMPTS) {
			try {
				// Wait for spinner to appear (if it's going to)
				boolean spinnerAppeared = WebUI.verifyElementPresent(spinnerObject, 1, FailureHandling.OPTIONAL)

				if (spinnerAppeared) {
					// Wait for it to disappear
					wait.until(ExpectedConditions.invisibilityOfElementLocated(WebUI.convertTestObjectToLocator(spinnerObject)))
				}

				return // Success - either spinner never appeared or it disappeared

			} catch (StaleElementReferenceException e) {
				// If we get a stale reference, the element is already gone
				return

			} catch (Exception e) {
				attemptCount++
				if (attemptCount >= MAX_ATTEMPTS) {
					CustomLogger.logWarning("Failed to handle spinner after ${MAX_ATTEMPTS} attempts: ${e.message}")
					return
				}
				WebUI.delay(WAIT_TIMEOUT)
			}
		}
	}
}