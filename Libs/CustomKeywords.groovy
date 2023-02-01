
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import java.util.List



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
