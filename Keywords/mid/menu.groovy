package mid

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
import groovy.json.JsonSlurper
import internal.GlobalVariable

public class menu {

	private def resourceBundle
	private final String resourceFilePath = "path/to/menus_en.json"

	menu() {
		def jsonSlurper = new JsonSlurper()
		resourceBundle = jsonSlurper.parse(new File(resourceFilePath))
	}

	private def translation(String key) {
		// Implement the translation logic here
		return resourceBundle[key]
	}

	private void openMenuItem(String menuGroupName, String subMenuGroupName, String openMenuItemName) {
		menuGroupName = translation(menuGroupName)
		subMenuGroupName = translation(subMenuGroupName)
		openMenuItemName = translation(openMenuItemName)

		String menuGroup = "//div[text()='" + menuGroupName + "']/parent::div[starts-with(@class,'styles_menuItem__')]"
		String additionalPath = "/following-sibling::div[@class='ReactCollapse--collapse'][1]"
		String menuItemPath

		if (subMenuGroupName == '') {
			menuItemPath = additionalPath + "//div[text()='" + openMenuItemName + "']"
			boolean isItNotOpen = WebUI.verifyElementAttributeValue(findTestObject(menuGroup + additionalPath), 'aria-hidden', 'true', 5)
			if (isItNotOpen) {
				WebUI.click(findTestObject(menuGroup))
			}
			WebUI.click(findTestObject(menuGroup + menuItemPath))
		} else {
			boolean isItNotOpen = WebUI.verifyElementAttributeValue(findTestObject(menuGroup + additionalPath), 'aria-hidden', 'true', 5)
			if (isItNotOpen) {
				WebUI.click(findTestObject(menuGroup))
			}
			String subMenuGroup = "//div[text()='" + menuGroupName + "']/parent::div[starts-with(@class,'styles_menuItem__')]/following::div[starts-with(@class,'styles_textTitle__')][text()='" + subMenuGroupName + "']"
			additionalPath = "/parent::div/following-sibling::div[@class='ReactCollapse--collapse'][1]"
			isItNotOpen = WebUI.verifyElementAttributeValue(findTestObject(subMenuGroup + additionalPath), 'aria-hidden', 'true', 5)
			menuItemPath = additionalPath + "//div[text()='" + openMenuItemName + "']"
			if (isItNotOpen) {
				WebUI.click(findTestObject(subMenuGroup))
			}
			WebUI.click(findTestObject(subMenuGroup + menuItemPath))
		}
		WebUI.waitForElementNotVisible(findTestObject('spinnerObject'), 10)
	}

	// Menu Navigation Methods
	void openOrdersFromSales() {
		openMenuItem(resourceBundle.Sales, '', resourceBundle.Sales_Orders)
		GlobalVariable.menuName = 'order'
	}

	void openInvoicesFromSales() {
		openMenuItem(resourceBundle.Sales, '', resourceBundle.Sales_Invoices)
		GlobalVariable.menuName = 'invoice'
	}

	void openShipmentsFromSales() {
		openMenuItem(resourceBundle.Sales, '', resourceBundle.Sales_Shipments)
		GlobalVariable.menuName = 'shipment'
	}

	void openCreditMemosFromSales() {
		openMenuItem(resourceBundle.Sales, '', resourceBundle.Sales_CreditMemos)
		GlobalVariable.menuName = 'credit_memo'
	}

	void openReturnRequestsFromSales() {
		openMenuItem(resourceBundle.Sales, '', resourceBundle.Sales_ReturnRequests)
	}

	void openReturnRequestSettingsFromSales() {
		openMenuItem(resourceBundle.Sales, '', resourceBundle.Sales_ReturnRequestSettings)
	}

	void openQuotationsFromSales() {
		openMenuItem(resourceBundle.Sales, '', resourceBundle.Sales_Quotations)
		GlobalVariable.menuName = 'quotation_id'
	}

	void openOMSFromSales() {
		openMenuItem(resourceBundle.Sales, '', resourceBundle.OMS)
		GlobalVariable.menuName = 'increment_id'
	}

	void openProductsFromCatalog() {
		openMenuItem(resourceBundle.Catalog, '', resourceBundle.Catalog_Products)
	}

	void openCategoriesFromCatalog() {
		openMenuItem(resourceBundle.Catalog, '', resourceBundle.Catalog_Categories)
	}

	void openRelatedProductRulesFromCatalog() {
		openMenuItem(resourceBundle.Catalog, '', resourceBundle.Catalog_RelatedProductRules)
		GlobalVariable.menuName = 'name'
	}

	void openProductLabelsFromCatalog() {
		openMenuItem(resourceBundle.Catalog, '', resourceBundle.Catalog_ProductLabels)
		GlobalVariable.menuName = 'name'
	}

	void openCMSPagesFromContent() {
		openMenuItem(resourceBundle.Content, '', resourceBundle.Content_CMSPages)
		GlobalVariable.menuName = 'title'
	}

	void openLandingPagesFromContent() {
		openMenuItem(resourceBundle.Content, '', resourceBundle.Content_LandingPages)
		GlobalVariable.menuName = 'title'
	}

	void openHeadersFromContent() {
		openMenuItem(resourceBundle.Content, '', resourceBundle.Content_Headers)
		GlobalVariable.menuName = 'title'
	}

	void openFootersFromContent() {
		openMenuItem(resourceBundle.Content, '', resourceBundle.Content_Footers)
		GlobalVariable.menuName = 'title'
	}

	void openSubscriptionFormsFromContent() {
		openMenuItem(resourceBundle.Content, '', resourceBundle.Content_SubscriptionForms)
		GlobalVariable.menuName = 'title'
	}

	void openAllCustomersFormsFromCustomers() {
		openMenuItem(resourceBundle.Customers, '', resourceBundle.Customers_AllCustomers)
		GlobalVariable.menuName = 'customer_number'
	}

	void openCustomerGroupsFormsFromCustomers() {
		openMenuItem(resourceBundle.Customers, '', resourceBundle.Customers_CustomerGroups)
		GlobalVariable.menuName = 'customer_group_code'
	}

	void openCartPriceRulesFormsFromMarketing() {
		openMenuItem(resourceBundle.Marketing, '', resourceBundle.Marketing_CartPriceRules)
		GlobalVariable.menuName = 'name'
	}

	void openProductsReviewsFormsFromMarketing() {
		openMenuItem(resourceBundle.Marketing, '', resourceBundle.Marketing_ProductsReviews)
		GlobalVariable.menuName = 'detail'
	}

	void openBasicReportsFormsFromReports() {
		openMenuItem(resourceBundle.Reports, '', resourceBundle.Reports_BasicReports)
	}

	void openConfigurationFormsFromSystem() {
		openMenuItem(resourceBundle.System, '', resourceBundle.System_Configuration)
	}

	void openPermissionsFormsFromSystem() {
		openMenuItem(resourceBundle.System, '', resourceBundle.System_Permissions)
	}

	void openLocatorZonesFormsFromSystem() {
		openMenuItem(resourceBundle.System, '', resourceBundle.System_LocatorZones)
	}

	void openDataTransferFormsFromSystem() {
		openMenuItem(resourceBundle.System, '', resourceBundle.System_DataTransfer)
	}

	void openDriversFormsFromDriverApp() {
		openMenuItem(resourceBundle.DriverApp, '', resourceBundle.DriverApp_Drivers)
	}

	void openAssignOrderToDriverFormsFromDriverApp() {
		openMenuItem(resourceBundle.DriverApp, '', resourceBundle.DriverApp_AssignOrderToDriver)
	}

	void openSourcesFormsFromStores() {
		openMenuItem(resourceBundle.Stores, resourceBundle.Stores_Inventory, resourceBundle.Stores_Inventory_Sources)
	}

	void openStocksFormsFromStores() {
		openMenuItem(resourceBundle.Stores, resourceBundle.Stores_Inventory, resourceBundle.Stores_Inventory_Stocks)
	}
}