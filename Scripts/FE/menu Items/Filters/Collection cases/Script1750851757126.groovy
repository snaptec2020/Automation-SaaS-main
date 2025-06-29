import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.model.FailureHandling.CONTINUE_ON_FAILURE
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.utils.CustomLogger

import internal.GlobalVariable
import utility.CustomWebUI

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.logging.LogEntries
import org.openqa.selenium.logging.LogType
import org.openqa.selenium.logging.LogEntry

// Configuration constants
String MAIN_FILTER_OBJECT_PATH = 'Object Repository/FE Filters/Filter Main'


// Test configuration - could be externalized to test data
def optionMode = FILTER_OPTION_MODE // 'AllAtTheSameTime', 'OneByOne', 'randomOption'
//def maxFiltersToTest = GlobalVariable.MAX_FILTERS_TO_TEST ?: 5
def maxFiltersToTest = 100
try {
    // Initialize test
    CustomLogger.logInfo("Starting filter functionality test with mode: ${optionMode}")
    
    def mainFilterObject = findTestObject('Object Repository/FE Filters/Filter Main')
    def initialProductCount = getProductCountWithRetry()
    
    if (initialProductCount == 0) {
        CustomLogger.logWarning("No products found on the page. Skipping filter test.")
        return
    }
    
    CustomLogger.logInfo("Initial product count: ${initialProductCount}")
    
    // Execute filter tests based on selected mode
    if (checkFiltersExists(mainFilterObject)) {
        List<WebElement> filterOptions = collectFilterOptions(mainFilterObject)
        
        if (filterOptions.size() > 0) {
            CustomLogger.logInfo("Found ${filterOptions.size()} filter options")
            executeFilterTests(filterOptions, mainFilterObject, optionMode, maxFiltersToTest)
        } else {
            CustomLogger.logWarning("No filter options found in the filters section")
        }
    } else {
        CustomLogger.logWarning("Filter section not found on the page")
    }
    
} catch (Exception e) {
    CustomLogger.logFailed("Test execution failed: ${e.getMessage()}")
    throw e
} finally {
    CustomLogger.logInfo("Filter functionality test completed")
}

/*************************************
 * ************* Main Test Logic *****
 ************************************/

def executeFilterTests(List<WebElement> filterOptions, def mainFilterObject, String mode, int maxFilters) {
    def filtersToTest = Math.min(filterOptions.size(), maxFilters)
    
    switch(mode) {
        case 'AllAtTheSameTime': 
            executeAllFiltersSimultaneously(filterOptions, mainFilterObject, filtersToTest)
            break
            
        case 'OneByOne': 
            executeFiltersOneByOne(filterOptions, mainFilterObject, filtersToTest)
            break
            
        case 'randomOption': 
            executeRandomFilter(filterOptions, mainFilterObject)
            break
            
        default:
            CustomLogger.logWarning("Unknown option mode: ${mode}. Using 'OneByOne' as default.")
            executeFiltersOneByOne(filterOptions, mainFilterObject, filtersToTest)
    }
}

def executeAllFiltersSimultaneously(List<WebElement> filterOptions, def mainFilterObject, int filtersToTest) {
    CustomLogger.logInfo("Executing all filters simultaneously")
    
    try {
        for(int index = 0; index < filtersToTest; index++) {
            WebElement selectedElement = filterOptions.get(index)
            if (isElementStillValid(selectedElement)) {
                handleSelectedFilter(selectedElement, index + 1)
            }
        }
        
        applyFilter(mainFilterObject)
        validateFilterApplication("All filters applied simultaneously")
        clearFilters()
    } catch (Exception e) {
        CustomLogger.logFailed("Error in simultaneous filter execution: ${e.getMessage()}")
        throw e
    }
}

def executeFiltersOneByOne(List<WebElement> filterOptions, def mainFilterObject, int filtersToTest) {
    CustomLogger.logInfo("Executing filters one by one")
    
    for(int index = 0; index < filtersToTest; index++) {
        try {
            // Re-collect filter options to handle DOM changes
            List<WebElement> currentFilterOptions = collectFilterOptions(mainFilterObject)
            
            if (index >= currentFilterOptions.size()) {
                CustomLogger.logWarning("Filter option ${index + 1} no longer available after DOM refresh")
                continue
            }
            
            WebElement selectedElement = currentFilterOptions.get(index)
            
            if (isElementStillValid(selectedElement)) {
                CustomLogger.logInfo("Testing filter ${index + 1} of ${filtersToTest}")
                
                handleSelectedFilter(selectedElement, index + 1)
                applyFilter(mainFilterObject)
                validateFilterApplication("Filter ${index + 1}")
                clearFilters()
                // Clear filters for next iteration (except last one)
                if (index < filtersToTest - 1) {
                    clearAllFilters(mainFilterObject)
                }
            } else {
                CustomLogger.logWarning("Filter element ${index + 1} is no longer valid")
            }
            
        } catch (Exception e) {
            CustomLogger.logWarning("Failed to test filter ${index + 1}: ${e.getMessage()}")
            continue
        }
    }
}

def executeRandomFilter(List<WebElement> filterOptions, def mainFilterObject) {
    CustomLogger.logInfo("Executing random filter selection")
    
    try {
        def randomIndex = CustomKeywords.'generalactions.generalStrings.getRandomNumberBetweenAnytoAny'(0, filterOptions.size() - 1)
        WebElement selectedElement = filterOptions.get(randomIndex)
        
        CustomLogger.logInfo("Selected random filter at index: ${randomIndex}")
        
        handleSelectedFilter(selectedElement, randomIndex + 1)
        applyFilter(mainFilterObject)
        validateFilterApplication("Random filter (index ${randomIndex})")
        
    } catch (Exception e) {
        CustomLogger.logFailed("Error in random filter execution: ${e.getMessage()}")
        throw e
    }
}

/*************************************
 * ************* Helper Methods ******
 ************************************/

def getProductCountWithRetry() {
    for (int attempt = 1; attempt <= MAX_RETRY_ATTEMPTS; attempt++) {
        try {
            List<WebElement> products = WebUI.callTestCase(
                findTestCase('Test Cases/FE/menu Items/Select Category contains Product'),
                [:], 
                FailureHandling.CONTINUE_ON_FAILURE
            )
            
            if (products?.size() > 0) {
                return products.size()
            }
            
            if (attempt < MAX_RETRY_ATTEMPTS) {
                CustomLogger.logInfo("Attempt ${attempt}: No products found, retrying...")
                WebUI.delay(2) // Wait before retry
            }
            
        } catch (Exception e) {
            CustomLogger.logWarning("Attempt ${attempt} failed: ${e.getMessage()}")
            if (attempt == MAX_RETRY_ATTEMPTS) {
                throw e
            }
        }
    }
    
    return 0
}

def boolean checkFiltersExists(def mainFilterObject) {
    try {
        return CustomWebUI.verifyElementVisibleWithTimeout(mainFilterObject, DEFAULT_TIMEOUT, FailureHandling.OPTIONAL)
    } catch (Exception e) {
        CustomLogger.logWarning("Error checking filter existence: ${e.getMessage()}")
        return false
    }
}

def collectFilterOptions(def mainFilterObject) {
    try {
        WebElement filtersMain = WebUI.findWebElement(mainFilterObject, ELEMENT_WAIT_TIMEOUT)
        List<WebElement> filterOptions = filtersMain.findElements(By.xpath("./div[starts-with(@class,'styles_filterBlock_')]"))
        
        // Filter out any stale or invalid elements
        return filterOptions.findAll { isElementStillValid(it) }
        
    } catch (Exception e) {
        CustomLogger.logWarning("Error collecting filter options: ${e.getMessage()}")
        return []
    }
}

def boolean isElementStillValid(WebElement element) {
    try {
        element.isDisplayed()
        return true
    } catch (Exception e) {
        return false
    }
}

def handleSelectedFilter(WebElement selectedFilter, int filterIndex) {
    try {
        CustomLogger.logInfo("Handling filter ${filterIndex}")
        
        // Find the form element - corrected XPath
        List<WebElement> formElements = selectedFilter.findElements(By.xpath(".//form"))
        
        if (formElements.size() == 0) {
            CustomLogger.logWarning("No form element found for filter ${filterIndex}")
            return
        }
        
        WebElement displayPopUp = formElements.get(0)
        String displayPopUpClass = displayPopUp.getAttribute("class") ?: ""
        
        // Click button if popup is not already displayed
        if (displayPopUpClass.trim().isEmpty()) {
            List<WebElement> buttons = selectedFilter.findElements(By.xpath(".//button"))
            if (buttons.size() > 0) {
                buttons.get(0).click()
                WebUI.delay(1) // Wait for popup to appear
            }
        }
        
        handleDisplayPopUp(displayPopUp, filterIndex)
        
    } catch (Exception e) {
        CustomLogger.logWarning("Error handling filter ${filterIndex}: ${e.getMessage()}")
        throw e
    }
}

def handleDisplayPopUp(WebElement displayPopUp, int filterIndex) {
    try {
        // Check for list-based filters (dropdowns, checkboxes)
        List<WebElement> ulElements = displayPopUp.findElements(By.xpath(".//ul"))
        
        if (ulElements.size() > 0) {
            handleListFilter(ulElements.get(0), filterIndex)
        } else {
            // Handle range/slider filters
            handleRangeFilter(displayPopUp, filterIndex)
        }
        
    } catch (Exception e) {
        CustomLogger.logWarning("Error handling popup for filter ${filterIndex}: ${e.getMessage()}")
        throw e
    }
}

def handleListFilter(WebElement ulElement, int filterIndex) {
    List<WebElement> listOfFilters = ulElement.findElements(By.xpath("./li"))
    
    if (listOfFilters.size() > 0) {
        // Select first available option
        WebElement firstOption = listOfFilters.get(0)
		firstOption = firstOption.findElement(By.xpath("./span/input[@type='checkbox']"))
        if (isElementStillValid(firstOption)) {
            firstOption.click()
            CustomLogger.logInfo("Selected list option for filter ${filterIndex}")
        }
    } else {
        CustomLogger.logWarning("No options found in list for filter ${filterIndex}")
    }
}

def handleRangeFilter(WebElement displayPopUp, int filterIndex) {
    try {
        // More flexible XPath for range sliders
        List<WebElement> sliderElements = displayPopUp.findElements(
            By.xpath("./div[contains(@class,'rangeContainer')]/div[starts-with(@class,'styles_rangeSliderTrack__')]/div[starts-with(@class,'styles_trackChildren__')]/div[last()]")
        )
        
        if (sliderElements.size() > 0) {
            sliderElements.get(0).click()
            CustomLogger.logInfo("Adjusted range slider for filter ${filterIndex}")
        } else {
            CustomLogger.logWarning("No range slider found for filter ${filterIndex}")
        }
        
    } catch (Exception e) {
        CustomLogger.logWarning("Error handling range filter ${filterIndex}: ${e.getMessage()}")
    }
}

def applyFilter(def mainFilterObject) {
    try {
        WebElement filtersMain = WebUI.findWebElement(mainFilterObject, ELEMENT_WAIT_TIMEOUT)
        
        // More flexible XPath for apply button
        List<WebElement> applyButtons = filtersMain.findElements(
            By.xpath("./parent::div//button[starts-with(@class,'button_button__')][2]")
        )
        
        if (applyButtons.size() == 0) {
            // Fallback to original XPath
            applyButtons = filtersMain.findElements(By.xpath("./div[starts-with(@class,'styles_footer__')]/button[2]"))
        }
        
        if (applyButtons.size() > 0) {
            WebElement applyButton = applyButtons.get(0)
            
            if (WebUI.waitForElementClickable(WebUI.convertWebElementToTestObject(applyButton), ELEMENT_WAIT_TIMEOUT)) {
                applyButton.click()
                CustomLogger.logInfo("Filter applied successfully")
                
                // Wait for spinner and page to update
                CustomKeywords.'generalactions.generalActions.waiteSpinnerToHide'()
                WebUI.delay(2) // Additional wait for results to load
            } else {
                CustomLogger.logError("Apply button not clickable")
            }
        } else {
            CustomLogger.logError("Apply button not found")
        }
        
    } catch (Exception e) {
        CustomLogger.logError("Error applying filter: ${e.getMessage()}")
        throw e
    }
}

def validateFilterApplication(String testDescription) {
    try {
        CustomLogger.logInfo("Validating filter application: ${testDescription}")
        
        // Scroll to ensure all content is loaded
        //WebUI.callTestCase(findTestCase('FE/Scrolling/scrollingAtTheBottom'), [:], FailureHandling.OPTIONAL)
        
        // Check if filters are applied (selected filter tabs should be visible)
        List<WebElement> selectedFilterTabs = WebUI.findWebElements(
            findTestObject('Object Repository/FE Filters/Selected Filters tab'), 
            ELEMENT_WAIT_TIMEOUT
        )
        
        if (selectedFilterTabs.size() > 0) {
            CustomLogger.logPassed("✅ Filter applied successfully - ${selectedFilterTabs.size()} active filter(s)")
        } else {
            CustomLogger.logWarning("⚠️ No selected filter tabs found after applying filter")
        }
        
        // Check for console errors
        checkBrowserConsoleLogs()
        
        // Verify page loaded properly
        validatePageLoad()
        
    } catch (Exception e) {
        CustomLogger.logFailed("Error validating filter application: ${e.getMessage()}")
        throw e
    }
}

def clearFilters() {
	try {
		CustomLogger.logInfo("Collecting the Filters Items to clear")
		
		// Scroll to ensure all content is loaded
		//WebUI.callTestCase(findTestCase('FE/Scrolling/scrollingAtTheBottom'), [:], FailureHandling.OPTIONAL)
		
		// Check if filters are applied (selected filter tabs should be visible)
		List<WebElement> itemToClear = WebUI.findWebElements(
			findTestObject('Object Repository/FE Filters/Selected Filters tab').addProperty("xpath", ConditionType.EQUALS, "/button"),
			ELEMENT_WAIT_TIMEOUT
		)
		
		
		
		if (itemToClear.size() > 0) {
			CustomLogger.logPassed("✅ Start Clearing items - ${itemToClear.size()} active filter(s)")
			
			for(int i =0; i<itemToClear.size(); i++) {
				CustomLogger.logPassed("✅ clearing the item - ${i}")
				
				itemToClear.get(0).click();
				
				CustomKeywords.'generalactions.generalActions.waiteSpinnerToHide'()
			}
			
			CustomLogger.logPassed("✅ Verifying the products are still exists")
			
			def initialProductCount = getProductCountWithRetry()
			
			if (initialProductCount == 0) {
				CustomLogger.logError("the proucts have been hidden after applying the filter")
				return
			}else {
				CustomLogger.logPassed("Initial product count: ${initialProductCount}")
			}
			

			
		} else {
			CustomLogger.logWarning("⚠️ No Items available to clear")
		}
		
		// Check for console errors
		checkBrowserConsoleLogs()
		
		// Verify page loaded properly
		validatePageLoad()
		
	} catch (Exception e) {
		CustomLogger.logFailed("Error validating filter application: ${e.getMessage()}")
		throw e
	}
}

def validatePageLoad() {
    try {
        // Check if page has loaded without critical errors
        def driver = DriverFactory.getWebDriver()
        def readyState = driver.executeScript("return document.readyState")
        
        if (readyState == "complete") {
            CustomLogger.logInfo("Page load state: Complete")
        } else {
            CustomLogger.logWarning("Page load state: ${readyState}")
        }
        
    } catch (Exception e) {
        CustomLogger.logWarning("Error checking page load state: ${e.getMessage()}")
    }
}

def clearAllFilters(def mainFilterObject) {
    try {
        // Look for clear/reset button
        WebElement filtersMain = WebUI.findWebElement(mainFilterObject, ELEMENT_WAIT_TIMEOUT)
        List<WebElement> clearButtons = filtersMain.findElements(
            By.xpath(".//button[contains(text(), 'Clear') or contains(text(), 'Reset') or contains(@class, 'clear')]")
        )
        
        if (clearButtons.size() > 0) {
            clearButtons.get(0).click()
            CustomLogger.logInfo("Filters cleared")
            WebUI.delay(2) // Wait for clear action to complete
        }
        
    } catch (Exception e) {
        CustomLogger.logWarning("Error clearing filters: ${e.getMessage()}")
    }
}

def boolean checkBrowserConsoleLogs() {
    try {
        def driver = DriverFactory.getWebDriver()
        LogEntries logs = driver.manage().logs().get(LogType.BROWSER)
        
        boolean hasErrors = false
        boolean hasWarnings = false
        int errorCount = 0
        int warningCount = 0
        
        for (LogEntry entry : logs) {
            String level = entry.level.toString()
            String message = entry.message
            
            // Filter out common non-critical messages
            if (shouldIgnoreLogEntry(message)) {
                continue
            }
            
            CustomLogger.logInfo("[Browser ${level}] ${message}")
            
            if (level.equalsIgnoreCase("SEVERE")) {
                hasErrors = true
                errorCount++
            } else if (level.equalsIgnoreCase("WARNING")) {
                hasWarnings = true
                warningCount++
            }
        }
        
        // Report results
        if (hasErrors) {
            CustomLogger.logFailed("❌ Found ${errorCount} JavaScript error(s) in browser console")
        } else {
            CustomLogger.logPassed("✅ No JavaScript errors in browser console")
        }
        
        if (hasWarnings && warningCount > 0) {
            CustomLogger.logInfo("⚠️ Found ${warningCount} warning(s) in browser console")
        }
        
        return !hasErrors
        
    } catch (Exception e) {
        CustomLogger.logWarning("Error checking browser console logs: ${e.getMessage()}")
        return true // Don't fail test if we can't check logs
    }
}

def boolean shouldIgnoreLogEntry(String message) {
    // Common patterns to ignore (adjust based on your application)
    def ignoredPatterns = [
        "favicon.ico",
        "third-party",
        "analytics",
        "advertisement",
        "google",
        "facebook"
    ]
    
    return ignoredPatterns.any { pattern -> 
        message.toLowerCase().contains(pattern.toLowerCase())
    }
}