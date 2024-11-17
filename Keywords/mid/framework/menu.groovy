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
import catalog.catlogComponants
import java.awt.Robot
import java.nio.charset.StandardCharsets
import java.util.List
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import internal.GlobalVariable
import mid.framework.jsonUtility

class menu {

	//	String url = "/"
	//	private final Map<String, String> resourceBundle = [
	//		"Sales": "Sales",
	//		"Sales_Orders": "Orders",
	//		"Sales_Invoices": "Invoices",
	//		"Sales_Shipments": "Shipments",
	//		"Sales_CreditMemos": "Credit Memos",
	//		"Sales_ReturnRequests": "Return Requests",
	//		"OMS": "OMS",
	//		"Sales_ReturnRequestSettings": "Return Request Settings",
	//		"Sales_Quotations": "Quotations",
	//		"Catalog": "Catalog",
	//		"Catalog_Products": "Products",
	//		"Catalog_Categories": "Categories",
	//		"Catalog_RelatedProductRules": "Related Product Rules",
	//		"Catalog_ProductLabels": "Product Labels",
	//		"Content": "Content",
	//		"Content_CMSPages": "CMS Pages",
	//		"Content_LandingPages": "Landing Pages",
	//		"Content_Headers": "Headers",
	//		"Content_Footers": "Footers",
	//		"Content_SubscriptionForms": "Subscription Forms",
	//		"Customers": "Customers",
	//		"Customers_AllCustomers": "All Customers",
	//		"Customers_CustomerGroups": "Customer Groups",
	//		"Marketing": "Marketing",
	//		"Marketing_CartPriceRules": "Cart Price Rules",
	//		"Marketing_ProductsReviews": "Products Reviews",
	//		"Reports": "Reports",
	//		"Reports_BasicReports": "Basic Reports",
	//		"System": "System",
	//		"System_Configuration": "Configuration",
	//		"System_Permissions": "Permissions",
	//		"System_LocatorZones": "Locator Zones",
	//		"System_DataTransfer": "Data Transfer",
	//		"DriverApp": "Driver App",
	//		"DriverApp_Drivers": "Drivers",
	//		"DriverApp_AssignOrderToDriver": "Assign Order To Driver",
	//		"Stores": "Stores",
	//		"Stores_Inventory": "Inventory",
	//		"Stores_Inventory_Sources": "Sources",
	//		"Stores_Inventory_Stocks": "Stocks"
	//	]

	
	String filePath = "Resources/menus_en.json"
	def resourceBundle = jsonUtility.readJsonFile(filePath)
	
	private def translation(String key) {
		return resourceBundle[key]
	}

	private void openMenuItem(String menuGroupName, String subMenuGroupName, String openMenuItemName) {
		String menuGroupXpath = "//div[text()='" + menuGroupName + "']/parent::div[starts-with(@class,'styles_menuItem__')]"
		String additionalPath = "/following-sibling::div[@class='ReactCollapse--collapse'][1]"
		String menuItemPath
		boolean isItNotOpen = isMenuClosed(menuGroupXpath + additionalPath)

		if (subMenuGroupName == '') {
			menuItemPath = additionalPath + "//div[text()='" + openMenuItemName + "']"
			if (isItNotOpen) {
				WebUI.click(createTestObject(menuGroupXpath))
			}
			WebUI.click(createTestObject(menuGroupXpath + menuItemPath))
		} else {
			String subMenuGroupXpath = "//div[text()='" + menuGroupName + "']/parent::div[starts-with(@class,'styles_menuItem__')]/following::div[starts-with(@class,'styles_textTitle__')][text()='" + subMenuGroupName + "']"
			additionalPath = "/parent::div/following-sibling::div[@class='ReactCollapse--collapse'][1]"
			isItNotOpen = isMenuClosed(subMenuGroupXpath + additionalPath)
			menuItemPath = additionalPath + "//div[text()='" + openMenuItemName + "']"
			if (isItNotOpen) {
				WebUI.click(createTestObject(subMenuGroupXpath))
			}
			WebUI.click(createTestObject(subMenuGroupXpath + menuItemPath))
		}
		WebUI.waitForElementNotVisible(createTestObject('spinnerObject'), 10)
	}

	private boolean isMenuClosed(String menuXpath) {
		TestObject menuTestObject = createTestObject(menuXpath)

		try {
			WebUI.waitForElementPresent(menuTestObject, 10)

			return WebUI.verifyElementAttributeValue(menuTestObject, 'aria-hidden', 'true', 5)
		} catch (Exception e) {
			println("Error waiting for object: " + e.message)
			return false
		}
	}


	private TestObject createTestObject(String xpath) {
		TestObject testObject = new TestObject("dynamicTestObject_" + xpath.hashCode())
		testObject.addProperty("xpath", ConditionType.EQUALS, xpath)
		return testObject
	}


	@Keyword
	def openOrdersFromSales() {
		openMenuItem(resourceBundle.Sales, '', resourceBundle.Sales_Orders)
		//		GlobalVariable.menuName = 'order'
	}

	@Keyword
	def openInvoicesFromSales() {
		openMenuItem(resourceBundle.Sales, '', resourceBundle.Sales_Invoices)
		//		GlobalVariable.menuName = 'invoice'
	}

	@Keyword
	def openShipmentsFromSales() {
		openMenuItem(resourceBundle.Sales, '', resourceBundle.Sales_Shipments)
		//		GlobalVariable.menuName = 'shipment'
	}

	@Keyword
	def openCreditMemosFromSales() {
		openMenuItem(resourceBundle.Sales, '', resourceBundle.Sales_CreditMemos)
		//		GlobalVariable.menuName = 'credit_memo'
	}

	@Keyword
	def openReturnRequestsFromSales() {
		openMenuItem(resourceBundle.Sales, '', resourceBundle.Sales_ReturnRequests)
	}

	@Keyword
	def openReturnRequestSettingsFromSales() {
		openMenuItem(resourceBundle.Sales, '', resourceBundle.Sales_ReturnRequestSettings)
	}

	@Keyword
	def openQuotationsFromSales() {
		openMenuItem(resourceBundle.Sales, '', resourceBundle.Sales_Quotations)
		//		GlobalVariable.menuName = 'quotation_id'
	}

	@Keyword
	def openOMSFromSales() {
		openMenuItem(resourceBundle.Sales, '', resourceBundle.OMS)
		//		GlobalVariable.menuName = 'increment_id'
	}

	@Keyword
	def openProductsFromCatalog() {
		openMenuItem(resourceBundle.Catalog, '', resourceBundle.Catalog_Products)
	}

	@Keyword
	def openCategoriesFromCatalog() {
		openMenuItem(resourceBundle.Catalog, '', resourceBundle.Catalog_Categories)
	}

	@Keyword
	def openRelatedProductRulesFromCatalog() {
		openMenuItem(resourceBundle.Catalog, '', resourceBundle.Catalog_RelatedProductRules)
		//		GlobalVariable.menuName = 'name'
	}

	@Keyword
	def openProductLabelsFromCatalog() {
		openMenuItem(resourceBundle.Catalog, '', resourceBundle.Catalog_ProductLabels)
		//		GlobalVariable.menuName = 'name'
	}

	@Keyword
	def openCMSPagesFromContent() {
		openMenuItem(resourceBundle.Content, '', resourceBundle.Content_CMSPages)
		//		GlobalVariable.menuName = 'title'
	}

	@Keyword
	def openLandingPagesFromContent() {
		openMenuItem(resourceBundle.Content, '', resourceBundle.Content_LandingPages)
		//		GlobalVariable.menuName = 'title'
	}

	@Keyword
	def openHeadersFromContent() {
		openMenuItem(resourceBundle.Content, '', resourceBundle.Content_Headers)
		//		GlobalVariable.menuName = 'title'
	}

	@Keyword
	def openFootersFromContent() {
		openMenuItem(resourceBundle.Content, '', resourceBundle.Content_Footers)
		//		GlobalVariable.menuName = 'title'
	}

	@Keyword
	def openSubscriptionFormsFromContent() {
		openMenuItem(resourceBundle.Content, '', resourceBundle.Content_SubscriptionForms)
		//		GlobalVariable.menuName = 'title'
	}

	@Keyword
	def openAllCustomersFormsFromCustomers() {
		openMenuItem(resourceBundle.Customers, '', resourceBundle.Customers_AllCustomers)
		//		GlobalVariable.menuName = 'customer_number'
	}

	@Keyword
	def openCustomerGroupsFormsFromCustomers() {
		openMenuItem(resourceBundle.Customers, '', resourceBundle.Customers_CustomerGroups)
		//		GlobalVariable.menuName = 'customer_group_code'
	}

	@Keyword
	def openCartPriceRulesFormsFromMarketing() {
		openMenuItem(resourceBundle.Marketing, '', resourceBundle.Marketing_CartPriceRules)
		//		GlobalVariable.menuName = 'name'
	}

	@Keyword
	def openProductsReviewsFormsFromMarketing() {
		openMenuItem(resourceBundle.Marketing, '', resourceBundle.Marketing_ProductsReviews)
		//		GlobalVariable.menuName = 'detail'
	}

	@Keyword
	def openBasicReportsFormsFromReports() {
		openMenuItem(resourceBundle.Reports, '', resourceBundle.Reports_BasicReports)
	}

	@Keyword
	def openConfigurationFormsFromSystem() {
		openMenuItem(resourceBundle.System, '', resourceBundle.System_Configuration)
	}

	@Keyword
	def openPermissionsFormsFromSystem() {
		openMenuItem(resourceBundle.System, '', resourceBundle.System_Permissions)
	}

	@Keyword
	def openLocatorZonesFormsFromSystem() {
		openMenuItem(resourceBundle.System, '', resourceBundle.System_LocatorZones)
	}

	@Keyword
	def openDataTransferFormsFromSystem() {
		openMenuItem(resourceBundle.System, '', resourceBundle.System_DataTransfer)
	}

	@Keyword
	def openDriversFormsFromDriverApp() {
		openMenuItem(resourceBundle.DriverApp, '', resourceBundle.DriverApp_Drivers)
	}

	@Keyword
	def openAssignOrderToDriverFormsFromDriverApp() {
		openMenuItem(resourceBundle.DriverApp, '', resourceBundle.DriverApp_AssignOrderToDriver)
	}

	@Keyword
	def openSourcesFormsFromStores() {
		openMenuItem(resourceBundle.Stores, resourceBundle.Stores_Inventory, resourceBundle.Stores_Inventory_Sources)
	}

	@Keyword
	def openStocksFormsFromStores() {
		openMenuItem(resourceBundle.Stores, resourceBundle.Stores_Inventory, resourceBundle.Stores_Inventory_Stocks)
	}
}