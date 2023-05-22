
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import java.util.List

import java.lang.String

import com.kms.katalon.core.testobject.TestObject

import org.openqa.selenium.WebElement

import com.kms.katalon.core.context.TestSuiteContext


 /*** Refresh browser like F5 */ 
def static "customUtils.general.refreshBrowser"() {
    (new customUtils.general()).refreshBrowser()
}


def static "dbconnections.executeQueries.getDataFromDBByQuery"(
    	Object mQuery	) {
    (new dbconnections.executeQueries()).getDataFromDBByQuery(
        	mQuery)
}


def static "dbconnections.executeQueries.getOtp"(
    	Object mobileNumber	) {
    (new dbconnections.executeQueries()).getOtp(
        	mobileNumber)
}


def static "signIn.signInVerifications.verificationElementSignIn"() {
    (new signIn.signInVerifications()).verificationElementSignIn()
}


def static "signIn.signInVerifications.verifyActualMessageWithExpectedSignIn"(
    	Object expectedMessage	) {
    (new signIn.signInVerifications()).verifyActualMessageWithExpectedSignIn(
        	expectedMessage)
}


def static "products.productsFromCatalog.getProducts"() {
    (new products.productsFromCatalog()).getProducts()
}


def static "products.productsFromCatalog.getSpecifiedProduct"(
    	int elementIndex	
     , 	List productList	) {
    (new products.productsFromCatalog()).getSpecifiedProduct(
        	elementIndex
         , 	productList)
}


def static "products.productsFromCatalog.getinStockProduct"() {
    (new products.productsFromCatalog()).getinStockProduct()
}


def static "products.productsFromCatalog.getSpecifiedinStockProducts"(
    	int elementIndex	
     , 	List productList	) {
    (new products.productsFromCatalog()).getSpecifiedinStockProducts(
        	elementIndex
         , 	productList)
}


def static "products.productsFromCatalog.getSpecifiedinStockProductsFromRandomCategory"() {
    (new products.productsFromCatalog()).getSpecifiedinStockProductsFromRandomCategory()
}


def static "products.productsFromCatalog.getSpecifiedinStockProductsText"() {
    (new products.productsFromCatalog()).getSpecifiedinStockProductsText()
}


def static "products.productsFromCatalog.getinStockProductFromOnePage"() {
    (new products.productsFromCatalog()).getinStockProductFromOnePage()
}


def static "products.productsFromCatalog.getSpecifiedinStockProductsFromOnePage"(
    	int elementIndex	
     , 	List productList	) {
    (new products.productsFromCatalog()).getSpecifiedinStockProductsFromOnePage(
        	elementIndex
         , 	productList)
}


def static "products.productsFromCatalog.getRandominStockProductsFromOnePage"() {
    (new products.productsFromCatalog()).getRandominStockProductsFromOnePage()
}


def static "products.productsFromCatalog.getRandominStockProductsFromRandomCategory"() {
    (new products.productsFromCatalog()).getRandominStockProductsFromRandomCategory()
}


def static "products.productsFromCatalog.getOutOfStockProduct"() {
    (new products.productsFromCatalog()).getOutOfStockProduct()
}


def static "products.productsFromCatalog.getSpecifiedOutOfStockProduct"(
    	int elementIndex	
     , 	List productList	) {
    (new products.productsFromCatalog()).getSpecifiedOutOfStockProduct(
        	elementIndex
         , 	productList)
}


def static "products.productsFromCatalog.getProductsTRoWishList"() {
    (new products.productsFromCatalog()).getProductsTRoWishList()
}


def static "products.productsFromCatalog.getSpecifiedProductToBVeAddedinwishlist"(
    	int elementIndex	
     , 	List productList	) {
    (new products.productsFromCatalog()).getSpecifiedProductToBVeAddedinwishlist(
        	elementIndex
         , 	productList)
}


def static "products.productsFromCatalog.getWishListItems"() {
    (new products.productsFromCatalog()).getWishListItems()
}


def static "products.productsFromCatalog.getSpecifieditemfromWishListItems"(
    	int elementIndex	
     , 	List productList	) {
    (new products.productsFromCatalog()).getSpecifieditemfromWishListItems(
        	elementIndex
         , 	productList)
}


def static "products.productsFromCatalog.configurableProduct"() {
    (new products.productsFromCatalog()).configurableProduct()
}


def static "products.productsFromCatalog.checkOnAddToStoreClickable"(
    	Object currentURL	) {
    (new products.productsFromCatalog()).checkOnAddToStoreClickable(
        	currentURL)
}


def static "signup.Signupemailverifications.verificationMessage"(
    	Object expectedMessageAr	
     , 	Object expectedMessageEn	) {
    (new signup.Signupemailverifications()).verificationMessage(
        	expectedMessageAr
         , 	expectedMessageEn)
}


def static "signup.Signupemailverifications.verificationElementSignUp"() {
    (new signup.Signupemailverifications()).verificationElementSignUp()
}

 /**
	 * Get Element by XPath
	 * @param xPath
	 *         represent a test object xPath
	 * @return Element
	 */ 
def static "customUtils.Util.getElementByXPath"(
    	String xPath	) {
    (new customUtils.Util()).getElementByXPath(
        	xPath)
}


def static "helpdesk.HelpdeskUtil.OpenRandomProductAJStore"() {
    (new helpdesk.HelpdeskUtil()).OpenRandomProductAJStore()
}


def static "helpdesk.HelpdeskUtil.OpenRandomProductQasr"() {
    (new helpdesk.HelpdeskUtil()).OpenRandomProductQasr()
}


def static "helpdesk.HelpdeskUtil.OpenRandomProductAlAseel"() {
    (new helpdesk.HelpdeskUtil()).OpenRandomProductAlAseel()
}


def static "helpdesk.HelpdeskUtil.OpenRandomProductAlJedaie"() {
    (new helpdesk.HelpdeskUtil()).OpenRandomProductAlJedaie()
}


def static "helpdesk.HelpdeskUtil.OpenRandomProductTBS"() {
    (new helpdesk.HelpdeskUtil()).OpenRandomProductTBS()
}


def static "helpdesk.HelpdeskUtil.OpenRandomProductTheBeautySecrets"() {
    (new helpdesk.HelpdeskUtil()).OpenRandomProductTheBeautySecrets()
}


def static "helpdesk.HelpdeskUtil.OpenRandomProductAlShamasy"() {
    (new helpdesk.HelpdeskUtil()).OpenRandomProductAlShamasy()
}


def static "helpdesk.HelpdeskUtil.decodeEncodedValue"(
    	String encoded	) {
    (new helpdesk.HelpdeskUtil()).decodeEncodedValue(
        	encoded)
}


def static "helpdesk.HelpdeskUtil.encodeValue"(
    	String value	) {
    (new helpdesk.HelpdeskUtil()).encodeValue(
        	value)
}


def static "helpdesk.HelpdeskUtil.scrollToVerifyElementVisiblity"(
    	TestObject testObjectRelativeId	) {
    (new helpdesk.HelpdeskUtil()).scrollToVerifyElementVisiblity(
        	testObjectRelativeId)
}


def static "helpdesk.HelpdeskUtil.OpenRandomProductOrange"() {
    (new helpdesk.HelpdeskUtil()).OpenRandomProductOrange()
}


def static "helpdesk.HelpdeskUtil.ScrollToElement"(
    	TestObject tb	) {
    (new helpdesk.HelpdeskUtil()).ScrollToElement(
        	tb)
}


def static "helpdesk.HelpdeskUtil.ScrollToElement"(
    	WebElement element	) {
    (new helpdesk.HelpdeskUtil()).ScrollToElement(
        	element)
}


def static "helpdesk.HelpdeskUtil.clickJS"(
    	TestObject to	
     , 	int timeout	) {
    (new helpdesk.HelpdeskUtil()).clickJS(
        	to
         , 	timeout)
}


def static "helpdesk.HelpdeskUtil.clickJS"(
    	WebElement element	
     , 	int timeout	) {
    (new helpdesk.HelpdeskUtil()).clickJS(
        	element
         , 	timeout)
}


def static "helpdesk.HelpdeskUtil.checkUsingJS"(
    	TestObject to3	
     , 	int timeout3	) {
    (new helpdesk.HelpdeskUtil()).checkUsingJS(
        	to3
         , 	timeout3)
}


def static "helpdesk.HelpdeskUtil.uncheckUsingJS"(
    	TestObject to3	
     , 	int timeout3	) {
    (new helpdesk.HelpdeskUtil()).uncheckUsingJS(
        	to3
         , 	timeout3)
}


def static "helpdesk.HelpdeskUtil.checkUsingJS"(
    	WebElement element	) {
    (new helpdesk.HelpdeskUtil()).checkUsingJS(
        	element)
}


def static "helpdesk.HelpdeskUtil.uncheckUsingJS"(
    	WebElement element	) {
    (new helpdesk.HelpdeskUtil()).uncheckUsingJS(
        	element)
}


def static "cart.removeItem.getProductsInCart"() {
    (new cart.removeItem()).getProductsInCart()
}


def static "cart.removeItem.getSpecifiedIteminThecart"(
    	int elementIndex	
     , 	List items	) {
    (new cart.removeItem()).getSpecifiedIteminThecart(
        	elementIndex
         , 	items)
}


def static "generalactions.notificationsObject.verifyNotificationVisble"(
    	Object arText	
     , 	Object enText	) {
    (new generalactions.notificationsObject()).verifyNotificationVisble(
        	arText
         , 	enText)
}


def static "generalactions.notificationsObject.verifyNotificationNotVisble"(
    	Object arText	
     , 	Object enText	) {
    (new generalactions.notificationsObject()).verifyNotificationNotVisble(
        	arText
         , 	enText)
}


def static "generalactions.notificationsObject.getMessageText"() {
    (new generalactions.notificationsObject()).getMessageText()
}


def static "cart.cartItems.getProductsInCart"() {
    (new cart.cartItems()).getProductsInCart()
}


def static "cart.cartItems.getSpecifiedIteminThecart"(
    	int elementIndex	
     , 	List items	) {
    (new cart.cartItems()).getSpecifiedIteminThecart(
        	elementIndex
         , 	items)
}


def static "checkout.Payments.getPaymentMethodsList"() {
    (new checkout.Payments()).getPaymentMethodsList()
}


def static "checkout.Payments.getRandomPaymentMethods"() {
    (new checkout.Payments()).getRandomPaymentMethods()
}


def static "generalactions.reporting.exportKatalonReports"(
    	TestSuiteContext testSuiteContext	) {
    (new generalactions.reporting()).exportKatalonReports(
        	testSuiteContext)
}


def static "catalog.catlogComponants.getCategoryElements"() {
    (new catalog.catlogComponants()).getCategoryElements()
}


def static "catalog.catlogComponants.getSpecifiedCatalogElement"(
    	int elementIndex	
     , 	List catalogList	) {
    (new catalog.catlogComponants()).getSpecifiedCatalogElement(
        	elementIndex
         , 	catalogList)
}


def static "generalactions.scrolling.scrollingAtTheBottom"() {
    (new generalactions.scrolling()).scrollingAtTheBottom()
}


def static "generalactions.generalStrings.generatRandomEmail"() {
    (new generalactions.generalStrings()).generatRandomEmail()
}


def static "generalactions.generalStrings.generateRandomPhoneNumber"() {
    (new generalactions.generalStrings()).generateRandomPhoneNumber()
}


def static "generalactions.generalStrings.generatePhoneWithConditions"(
    	int expectedPhoneSize	
     , 	int isPhoneKeyReplaced	) {
    (new generalactions.generalStrings()).generatePhoneWithConditions(
        	expectedPhoneSize
         , 	isPhoneKeyReplaced)
}


def static "signup.signupPhoneVerifications.phoneVerificationMessage"(
    	Object expectedMessage	) {
    (new signup.signupPhoneVerifications()).phoneVerificationMessage(
        	expectedMessage)
}


def static "signup.signupPhoneVerifications.verificationElementPhoneSignUp"() {
    (new signup.signupPhoneVerifications()).verificationElementPhoneSignUp()
}
