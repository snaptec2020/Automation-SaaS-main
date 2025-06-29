package mid.framework

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.utils.CustomLogger

import catalog.catlogComponants
import generalactions.generalActions

import java.awt.Robot
import java.nio.charset.StandardCharsets
import java.util.List
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import internal.GlobalVariable as GlobalVariable
import mid.framework.jsonUtility

class menu {

	String filePath = "Resources/menus_en.json"
	def resourceBundle
	def generalActions = new generalActions()
	// Constructor to initialize resourceBundle with logging
	public menu() {
		try {
			CustomLogger.logInfo("Initializing menu class - loading resource bundle from: ${filePath}")
			resourceBundle = jsonUtility.readJsonFile(filePath)
			CustomLogger.logInfo("Resource bundle loaded successfully")
		} catch (Exception e) {
			CustomLogger.logError("Failed to load resource bundle from ${filePath}: ${e.message}")
			throw e
		}
	}

	private def translation(String key) {
		CustomLogger.logInfo("Getting translation for key: ${key}")
		def translation = resourceBundle[key]
		if (translation) {
			CustomLogger.logInfo("Translation found: ${key} -> ${translation}")
		} else {
			CustomLogger.logWarning("No translation found for key: ${key}")
		}
		return translation
	}

	private void openMenuItem(String menuGroupName, String subMenuGroupName, String openMenuItemName) {
		CustomLogger.logInfo("Opening menu item - Group: '${menuGroupName}', SubGroup: '${subMenuGroupName}', Item: '${openMenuItemName}'")

		try {
			String menuGroupXpath = "//div[text()='" + menuGroupName + "']/parent::div[starts-with(@class,'styles_menuItem__')]"
			String additionalPath = "/following-sibling::div[@class='ReactCollapse--collapse'][1]"
			String menuItemPath
			boolean isItNotOpen = isMenuClosed(menuGroupXpath + additionalPath)

			if (subMenuGroupName == '') {
				CustomLogger.logInfo("Opening direct menu item without subgroup")
				menuItemPath = additionalPath + "//div[text()='" + openMenuItemName + "']"

				if (isItNotOpen) {
					CustomLogger.logInfo("Menu group '${menuGroupName}' is closed, clicking to open")
					WebUI.click(createTestObject(menuGroupXpath))
					CustomLogger.logInfo("Successfully clicked menu group '${menuGroupName}'")
				} else {
					CustomLogger.logInfo("Menu group '${menuGroupName}' is already open")
				}

				CustomLogger.logInfo("Clicking menu item '${openMenuItemName}'")
				WebUI.click(createTestObject(menuGroupXpath + menuItemPath))
				CustomLogger.logPassed("Successfully clicked menu item '${openMenuItemName}'")
			} else {
				CustomLogger.logInfo("Opening menu item with subgroup")
				String subMenuGroupXpath = "//div[text()='" + menuGroupName + "']/parent::div[starts-with(@class,'styles_menuItem__')]/following::div[starts-with(@class,'styles_textTitle__')][text()='" + subMenuGroupName + "']"
				additionalPath = "/parent::div/following-sibling::div[@class='ReactCollapse--collapse'][1]"
				isItNotOpen = isMenuClosed(subMenuGroupXpath + additionalPath)
				menuItemPath = additionalPath + "//div[text()='" + openMenuItemName + "']"

				if (isItNotOpen) {
					CustomLogger.logInfo("Submenu group '${subMenuGroupName}' is closed, clicking to open")
					WebUI.click(createTestObject(subMenuGroupXpath))
					CustomLogger.logInfo("Successfully clicked submenu group '${subMenuGroupName}'")
				} else {
					CustomLogger.logInfo("Submenu group '${subMenuGroupName}' is already open")
				}

				CustomLogger.logInfo("Clicking menu item '${openMenuItemName}' in subgroup '${subMenuGroupName}'")
				WebUI.click(createTestObject(subMenuGroupXpath + menuItemPath))
				CustomLogger.logPassed("Successfully clicked menu item '${openMenuItemName}' in subgroup '${subMenuGroupName}'")
			}

			CustomLogger.logInfo("Waiting for spinner to disappear")
			generalActions.waiteSpinnerToHide()
			//WebUI.waitForElementNotVisible(createTestObject('spinnerObject'), 10)
			CustomLogger.logInfo("Spinner disappeared, menu navigation completed successfully")
		} catch (Exception e) {
			CustomLogger.logError("Failed to open menu item - Group: '${menuGroupName}', SubGroup: '${subMenuGroupName}', Item: '${openMenuItemName}'. Error: ${e.message}")
			throw e
		}
	}

	private boolean isMenuClosed(String menuXpath) {
		CustomLogger.logInfo("Checking if menu is closed for xpath: ${menuXpath}")
		TestObject menuTestObject = createTestObject(menuXpath)

		try {
			CustomLogger.logInfo("Waiting for menu element to be present")
			WebUI.waitForElementPresent(menuTestObject, 5)

			boolean isClosed = WebUI.verifyElementAttributeValue(menuTestObject, 'aria-hidden', 'true', 5)
			CustomLogger.logInfo("Menu closed status: ${isClosed}")
			return isClosed
		} catch (Exception e) {
			CustomLogger.logError("Error checking menu status for xpath '${menuXpath}': ${e.message}")
			return false
		}
	}

	private TestObject createTestObject(String xpath) {
		CustomLogger.logInfo("Creating test object for xpath: ${xpath}")
		TestObject testObject = new TestObject("dynamicTestObject_" + xpath.hashCode())
		testObject.addProperty("xpath", ConditionType.EQUALS, xpath)
		CustomLogger.logInfo("Test object created successfully with ID: dynamicTestObject_${xpath.hashCode()}")
		return testObject
	}

	@Keyword
	def openOrdersFromSales() {
		CustomLogger.logInfo("Opening Orders from Sales menu")
		try {
			openMenuItem(resourceBundle.Sales, '', resourceBundle.Sales_Orders)
			CustomLogger.logPassed("Successfully opened Orders from Sales")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open Orders from Sales: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openInvoicesFromSales() {
		CustomLogger.logInfo("Opening Invoices from Sales menu")
		try {
			openMenuItem(resourceBundle.Sales, '', resourceBundle.Sales_Invoices)
			CustomLogger.logPassed("Successfully opened Invoices from Sales")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open Invoices from Sales: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openShipmentsFromSales() {
		CustomLogger.logInfo("Opening Shipments from Sales menu")
		try {
			openMenuItem(resourceBundle.Sales, '', resourceBundle.Sales_Shipments)
			CustomLogger.logPassed("Successfully opened Shipments from Sales")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open Shipments from Sales: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openCreditMemosFromSales() {
		CustomLogger.logInfo("Opening Credit Memos from Sales menu")
		try {
			openMenuItem(resourceBundle.Sales, '', resourceBundle.Sales_CreditMemos)
			CustomLogger.logPassed("Successfully opened Credit Memos from Sales")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open Credit Memos from Sales: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openReturnRequestsFromSales() {
		CustomLogger.logInfo("Opening Return Requests from Sales menu")
		try {
			openMenuItem(resourceBundle.Sales, '', resourceBundle.Sales_ReturnRequests)
			CustomLogger.logPassed("Successfully opened Return Requests from Sales")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open Return Requests from Sales: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openReturnRequestSettingsFromSales() {
		CustomLogger.logInfo("Opening Return Request Settings from Sales menu")
		try {
			openMenuItem(resourceBundle.Sales, '', resourceBundle.Sales_ReturnRequestSettings)
			CustomLogger.logPassed("Successfully opened Return Request Settings from Sales")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open Return Request Settings from Sales: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openQuotationsFromSales() {
		CustomLogger.logInfo("Opening Quotations from Sales menu")
		try {
			openMenuItem(resourceBundle.Sales, '', resourceBundle.Sales_Quotations)
			CustomLogger.logPassed("Successfully opened Quotations from Sales")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open Quotations from Sales: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openOMSFromSales() {
		CustomLogger.logInfo("Opening OMS from Sales menu")
		try {
			openMenuItem(resourceBundle.Sales, '', resourceBundle.OMS)
			CustomLogger.logPassed("Successfully opened OMS from Sales")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open OMS from Sales: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openProductsFromCatalog() {
		CustomLogger.logInfo("Opening Products from Catalog menu")
		try {
			openMenuItem(resourceBundle.Catalog, '', resourceBundle.Catalog_Products)
			CustomLogger.logPassed("Successfully opened Products from Catalog")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open Products from Catalog: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openCategoriesFromCatalog() {
		CustomLogger.logInfo("Opening Categories from Catalog menu")
		try {
			openMenuItem(resourceBundle.Catalog, '', resourceBundle.Catalog_Categories)
			CustomLogger.logPassed("Successfully opened Categories from Catalog")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open Categories from Catalog: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openRelatedProductRulesFromCatalog() {
		CustomLogger.logInfo("Opening Related Product Rules from Catalog menu")
		try {
			openMenuItem(resourceBundle.Catalog, '', resourceBundle.Catalog_RelatedProductRules)
			CustomLogger.logPassed("Successfully opened Related Product Rules from Catalog")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open Related Product Rules from Catalog: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openProductLabelsFromCatalog() {
		CustomLogger.logInfo("Opening Product Labels from Catalog menu")
		try {
			openMenuItem(resourceBundle.Catalog, '', resourceBundle.Catalog_ProductLabels)
			CustomLogger.logPassed("Successfully opened Product Labels from Catalog")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open Product Labels from Catalog: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openCMSPagesFromContent() {
		CustomLogger.logInfo("Opening CMS Pages from Content menu")
		try {
			openMenuItem(resourceBundle.Content, '', resourceBundle.Content_CMSPages)
			CustomLogger.logPassed("Successfully opened CMS Pages from Content")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open CMS Pages from Content: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openLandingPagesFromContent() {
		CustomLogger.logInfo("Opening Landing Pages from Content menu")
		try {
			openMenuItem(resourceBundle.Content, '', resourceBundle.Content_LandingPages)
			CustomLogger.logPassed("Successfully opened Landing Pages from Content")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open Landing Pages from Content: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openHeadersFromContent() {
		CustomLogger.logInfo("Opening Headers from Content menu")
		try {
			openMenuItem(resourceBundle.Content, '', resourceBundle.Content_Headers)
			CustomLogger.logPassed("Successfully opened Headers from Content")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open Headers from Content: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openFootersFromContent() {
		CustomLogger.logInfo("Opening Footers from Content menu")
		try {
			openMenuItem(resourceBundle.Content, '', resourceBundle.Content_Footers)
			CustomLogger.logPassed("Successfully opened Footers from Content")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open Footers from Content: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openSubscriptionFormsFromContent() {
		CustomLogger.logInfo("Opening Subscription Forms from Content menu")
		try {
			openMenuItem(resourceBundle.Content, '', resourceBundle.Content_SubscriptionForms)
			CustomLogger.logPassed("Successfully opened Subscription Forms from Content")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open Subscription Forms from Content: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openAllCustomersFormsFromCustomers() {
		CustomLogger.logInfo("Opening All Customers from Customers menu")
		try {
			openMenuItem(resourceBundle.Customers, '', resourceBundle.Customers_AllCustomers)
			CustomLogger.logPassed("Successfully opened All Customers from Customers")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open All Customers from Customers: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openCustomerGroupsFormsFromCustomers() {
		CustomLogger.logInfo("Opening Customer Groups from Customers menu")
		try {
			openMenuItem(resourceBundle.Customers, '', resourceBundle.Customers_CustomerGroups)
			CustomLogger.logPassed("Successfully opened Customer Groups from Customers")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open Customer Groups from Customers: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openCartPriceRulesFormsFromMarketing() {
		CustomLogger.logInfo("Opening Cart Price Rules from Marketing menu")
		try {
			openMenuItem(resourceBundle.Marketing, '', resourceBundle.Marketing_CartPriceRules)
			CustomLogger.logPassed("Successfully opened Cart Price Rules from Marketing")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open Cart Price Rules from Marketing: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openProductsReviewsFormsFromMarketing() {
		CustomLogger.logInfo("Opening Products Reviews from Marketing menu")
		try {
			openMenuItem(resourceBundle.Marketing, '', resourceBundle.Marketing_ProductsReviews)
			CustomLogger.logPassed("Successfully opened Products Reviews from Marketing")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open Products Reviews from Marketing: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openBasicReportsFormsFromReports() {
		CustomLogger.logInfo("Opening Basic Reports from Reports menu")
		try {
			openMenuItem(resourceBundle.Reports, '', resourceBundle.Reports_BasicReports)
			CustomLogger.logPassed("Successfully opened Basic Reports from Reports")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open Basic Reports from Reports: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openConfigurationFormsFromSystem() {
		CustomLogger.logInfo("Opening Configuration from System menu")
		try {
			openMenuItem(resourceBundle.System, '', resourceBundle.System_Configuration)
			CustomLogger.logPassed("Successfully opened Configuration from System")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open Configuration from System: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openPermissionsFormsFromSystem() {
		CustomLogger.logInfo("Opening Permissions from System menu")
		try {
			openMenuItem(resourceBundle.System, '', resourceBundle.System_Permissions)
			CustomLogger.logPassed("Successfully opened Permissions from System")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open Permissions from System: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openLocatorZonesFormsFromSystem() {
		CustomLogger.logInfo("Opening Locator Zones from System menu")
		try {
			openMenuItem(resourceBundle.System, '', resourceBundle.System_LocatorZones)
			CustomLogger.logPassed("Successfully opened Locator Zones from System")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open Locator Zones from System: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openDataTransferFormsFromSystem() {
		CustomLogger.logInfo("Opening Data Transfer from System menu")
		try {
			openMenuItem(resourceBundle.System, '', resourceBundle.System_DataTransfer)
			CustomLogger.logPassed("Successfully opened Data Transfer from System")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open Data Transfer from System: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openDriversFormsFromDriverApp() {
		CustomLogger.logInfo("Opening Drivers from Driver App menu")
		try {
			openMenuItem(resourceBundle.DriverApp, '', resourceBundle.DriverApp_Drivers)
			CustomLogger.logPassed("Successfully opened Drivers from Driver App")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open Drivers from Driver App: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openAssignOrderToDriverFormsFromDriverApp() {
		CustomLogger.logInfo("Opening Assign Order To Driver from Driver App menu")
		try {
			openMenuItem(resourceBundle.DriverApp, '', resourceBundle.DriverApp_AssignOrderToDriver)
			CustomLogger.logPassed("Successfully opened Assign Order To Driver from Driver App")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open Assign Order To Driver from Driver App: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openSourcesFormsFromStores() {
		CustomLogger.logInfo("Opening Sources from Stores menu")
		try {
			openMenuItem(resourceBundle.Stores, resourceBundle.Stores_Inventory, resourceBundle.Stores_Inventory_Sources)
			CustomLogger.logPassed("Successfully opened Sources from Stores")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open Sources from Stores: ${e.message}")
			throw e
		}
	}

	@Keyword
	def openStocksFormsFromStores() {
		CustomLogger.logInfo("Opening Stocks from Stores menu")
		try {
			openMenuItem(resourceBundle.Stores, resourceBundle.Stores_Inventory, resourceBundle.Stores_Inventory_Stocks)
			CustomLogger.logPassed("Successfully opened Stocks from Stores")
		} catch (Exception e) {
			CustomLogger.logFailed("Failed to open Stocks from Stores: ${e.message}")
			throw e
		}
	}
}