
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import java.util.List



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


def static "signup.signupVerifications.verificationMessage"(
    	Object expectedMessageAr	
     , 	Object expectedMessageEn	) {
    (new signup.signupVerifications()).verificationMessage(
        	expectedMessageAr
         , 	expectedMessageEn)
}


def static "signup.signupVerifications.verificationElement"(
    	Object message	) {
    (new signup.signupVerifications()).verificationElement(
        	message)
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
