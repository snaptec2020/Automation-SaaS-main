import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

def currentUrl=WebUI.getUrl()
WebUI.callTestCase(findTestCase('FE/Check out/verification/Verification Check out components after click on proceed'), [:],
	FailureHandling.CONTINUE_ON_FAILURE)

WebUI.callTestCase(findTestCase('FE/Scrolling/scrollingAtTheBottom'), [:], FailureHandling.STOP_ON_FAILURE)
//List PaymentMethods =CustomKeywords.'checkout.EnhancedPayments.getPaymentMethodsList'()
//KeywordUtil.logInfo(PaymentMethods.toString())

//if(i!=1) {
//WebUI.callTestCase(findTestCase('FE/Check out/verification/Verification Check out components after click on proceed'), [:],
//		FailureHandling.STOP_ON_FAILURE)
//}
//WebUI.callTestCase(findTestCase('FE/Check out/validation/Select location common case'), [:], FailureHandling.STOP_ON_FAILURE)
//def languageMode = GlobalVariable.languageMode
def paymentMethod = 'Tabby'

CustomKeywords.'checkout.EnhancedPayments.paymentMethodToPayBySelectedMethod'(0,paymentMethod)


