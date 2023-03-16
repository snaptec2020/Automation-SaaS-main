
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */



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


def static "products.productsFromCatalog.configurableProduct"() {
    (new products.productsFromCatalog()).configurableProduct()
}


def static "products.productsFromCatalog.checkOnAddToStoreClickable"(
    	Object currentURL	) {
    (new products.productsFromCatalog()).checkOnAddToStoreClickable(
        	currentURL)
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
