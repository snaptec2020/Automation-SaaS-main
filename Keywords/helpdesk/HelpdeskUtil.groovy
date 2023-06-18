package helpdesk
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


public class HelpdeskUtil {

	@Keyword
	def OpenRandomProductAJStore(){


		//		List prod = getinStockProductFromOnePage()
		List prod = WebUI.findWebElements(findTestObject('Object Repository/Helpdesk/AjStore/FE/Product/Add to cart enabled button'),30)

		Random randomNumberforProduct = new Random()

		def elementIndexproduct = (Math.abs(randomNumberforProduct.nextInt(prod.size()))) +1

		def currentURL = WebUI.getUrl()

		TestObject tb = new TestObject()

		tb.addProperty('xpath', ConditionType.EQUALS, '(//button[contains(text(),\'Add to Cart\') or contains(text(),\'أضف إلى السلة\')])[' +
				elementIndexproduct + ']')

		//WebUI.waitForElementClickable(tb, 5)
		//		WebElement element = WebUiCommonHelper.findWebElement(tb, 30)
		ScrollToElement(tb)
		clickJS(tb,5)

		//		WebUI.executeJavaScript('arguments[0].click()', Arrays.asList(element))
		if (WebUI.getUrl() == currentURL) {
			ScrollToElement(findTestObject('Object Repository/Helpdesk/AjStore/FE/Cart/Continue Shopping'))
			WebUI.click(findTestObject('Object Repository/Helpdesk/AjStore/FE/Cart/Continue Shopping'), FailureHandling.CONTINUE_ON_FAILURE)
		} else {
			KeywordUtil.markPassed('Trying to Get Configurable product')

			tb.addProperty('xpath', ConditionType.EQUALS, '//section[starts-with(@class,\'productFullDetail-groupOption-\')]//div[starts-with(@class,\'option-root-\')]')

			List genralDropDowns = WebUI.findWebElements(tb, 30)
			if (genralDropDowns.size() != 0) {
				for (int i = 1; i <= genralDropDowns.size(); i++) {
					tb.addProperty('xpath', ConditionType.EQUALS, ('(//section[starts-with(@class,\'productFullDetail-groupOption-\')]//div[starts-with(@class,\'option-root-\')])[' +
							i) + ']//div//button[not( @disabled)]')
					ScrollToElement(tb)
					clickJS(tb,2)
				}
			}
		}


	}


	@Keyword
	def OpenRandomProductQasr(){


		//		List prod = getinStockProductFromOnePage()
		TestObject items = new TestObject()
		items.addProperty("xpath",ConditionType.EQUALS,'//a[contains(@class,"item-nameProduct-")]')
		List prod = WebUI.findWebElements(items,30)

		Random randomNumberforProduct = new Random()

		def elementIndexproduct = Math.abs(randomNumberforProduct.nextInt(prod.size() - 1)) + 1

		TestObject tb = new TestObject()

		tb.addProperty('xpath', ConditionType.EQUALS, ('('+ items.findPropertyValue("xpath") + ')[' +
				elementIndexproduct) + ']')

		WebElement element = WebUiCommonHelper.findWebElement(tb, 30)
		ScrollToElement(tb)
		clickJS(tb, 5)
	}

	@Keyword
	def OpenRandomProductAlAseel(){

		//		int spotY =WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/ProductsSection'), 10).getLocation().getY()
		//		WebUI.scrollToPosition(0,spotY)
		//		WebUI.scrollToElement(null, 10)
		//		WebUI.scrollToPosition(0, findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/ProductsSection').get)(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/ProductsSection'), 10)


		//		try {
		//			long lastHeight=((Number) WebUI.executeJavaScript("return document.body.scrollHeight", null)).longValue();
		//
		//			while (true) {
		//			  WebUI.executeJavaScript("window.scrollTo(0, document.body.scrollHeight);", null);
		//			  Thread.sleep(2000);
		//			  long newHeight = ((Number)WebUI.executeJavaScript("return document.body.scrollHeight", null)).longValue();
		//			  if (newHeight == lastHeight) {
		//				break;
		//			  }
		//			  lastHeight = newHeight;
		//			}
		//		  } catch (InterruptedException e) {
		//		   e.printStackTrace();
		//		  }

		//		scrollToVerifyElementVisiblity(findTestObject("Object Repository/Helpdesk/AlAseel/FE/Shared/ProductsSection"))
		//		WebElement el= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/ProductsSection'), 10)
		//		WebUI.executeJavaScript("arguments[0].scrollIntoView(true);", Arrays.asList(el));


		//		WebDriver driver = DriverFactory.getWebDriver()
		//		WebElement elem = driver.findElement(By.xpath('//p[@class="b-title h2"]'))
		//		WebUI.executeJavaScript('arguments[0].scrollIntoView(true)', Arrays.asList(elem))

		//		WebElement elem = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/ProductsSection'), 10)
		//		WebUI.executeJavaScript('arguments[0].scrollIntoView()', Arrays.asList(elem))

		//		Actions act = new Actions(driver)
		//		act.moveToElement(elem).build().perform()


		//		scrollToVerifyElementVisiblityAlAseel(findTestObject("Object Repository/Helpdesk/AlAseel/FE/Shared/ProductsSection"))
		if(WebUI.verifyElementPresent(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/Logo'),5,FailureHandling.OPTIONAL)){
			WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/Logo'),FailureHandling.OPTIONAL)
		}

		//		((JavascriptExecutor) DriverFactory.getWebDriver()).executeScript("arguments[0].scrollIntoView();", )
		WebUI.waitForPageLoad(20)
		ScrollToElement(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/ProductsSection'))
		//		WebUI.executeJavaScript("arguments[0].scrollIntoView(true);", WebUiCommonHelper.findWebElements(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/ProductsSection'), 10))
		WebUI.delay(2)


		List<WebElement> productsCategoriesList = WebUiCommonHelper.findWebElements(findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/ProductsCategoriesSection'), 5)
		for(int i=1;i<=productsCategoriesList.size();i++) {
			TestObject randomNumberCatProductTO = new TestObject()
					.addProperty('xpath', ConditionType.EQUALS, '('
					+ findTestObject('Object Repository/Helpdesk/AlAseel/FE/Shared/ProductsCategoriesSection').findPropertyValue("xpath")
					+ ')[' + i.toString() + ']/a')
			WebElement randomNumberCatProductElm = WebUiCommonHelper.findWebElement(randomNumberCatProductTO, 5)
			clickJS(randomNumberCatProductElm,3)
			WebUI.delay(1)
		}


		TestObject items = new TestObject()
		items.addProperty("xpath",ConditionType.EQUALS,"//button[contains(@title,'Add to Cart') or contains(@title,'إضافة إلى السلة')]")
		WebUI.waitForElementVisible(items, 10)
		List<WebElement> prod = WebUI.findWebElements(items,3)

		Random randomNumberforProduct = new Random()

		def elementIndexproduct = Math.abs(randomNumberforProduct.nextInt(prod.size() - 1)) + 1

		TestObject tb = new TestObject()

		tb.addProperty('xpath', ConditionType.EQUALS, ('('+ items.findPropertyValue("xpath") + ')[' +
				elementIndexproduct) + ']')

		WebElement element = WebUiCommonHelper.findWebElement(tb, 5)
		String currentURL = WebUI.getUrl()
		clickJS(element, 5)
		WebUI.delay(2)
		WebUI.waitForPageLoad(20,FailureHandling.OPTIONAL)
		if (WebUI.getUrl().equals(currentURL)) {
			WebUI.click(findTestObject('Object Repository/Helpdesk/AlAseel/FE/MiniCart/ContinueShoping'), FailureHandling.CONTINUE_ON_FAILURE)
		} else {
			//			KeywordUtil.markPassed('Trying to Get Configurable product')
			String MainXPath = '//div[starts-with(@class,"product-options-wrapper")]//div[starts-with(@class,"swatch-attribute") and @aria-required="true"]'
			TestObject Maintb = new TestObject()
			Maintb.addProperty('xpath', ConditionType.EQUALS, MainXPath)
			WebUI.waitForElementVisible(Maintb, 10,FailureHandling.OPTIONAL)
			List genralDropDowns = WebUI.findWebElements(Maintb, 30)

			if (genralDropDowns.size() != 0) {
				for (int i = 1; i <= genralDropDowns.size(); i++) {
					String SubxPath= '(' + MainXPath + ')' +'[' + i + ']//div[not( @disabled)]'
					TestObject Subtb = new TestObject()
					Subtb.addProperty('xpath', ConditionType.EQUALS, SubxPath)
					WebUI.waitForElementVisible(Subtb, 10)
					List<WebElement> SubElem=WebUiCommonHelper.findWebElements(Subtb, 30)
					//					SubElem.get(0).click()
					ScrollToElement(SubElem.get(0))
					clickJS(SubElem.get(0), 5)
				}
			}

		}
	}


	@Keyword
	def OpenRandomProductAlJedaie(){
		List<WebElement> productsCategoriesList = WebUiCommonHelper.findWebElements(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Shared/ProductsSection'), 5)
		Random randomCategory = new Random()
		int randomCategorySelected = randomCategory.nextInt(productsCategoriesList.size()) + 1
		String randomNumberCatProductXPath='('+ findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Shared/ProductsSection').findPropertyValue("xpath")	+ ')[' + randomCategorySelected.toString() + ']'
		TestObject randomNumberCatProductTO = new TestObject()
		randomNumberCatProductTO.addProperty('xpath', ConditionType.EQUALS, randomNumberCatProductXPath)
		WebElement randomNumberCatProductElm = WebUiCommonHelper.findWebElement(randomNumberCatProductTO, 5)
		print "select cat: "
		println randomCategorySelected
		println randomNumberCatProductXPath
		ScrollToElement(randomNumberCatProductTO)
		WebUI.waitForElementClickable(randomNumberCatProductTO, 5)
		WebUI.doubleClick(randomNumberCatProductTO)
		//		WebUI.click(randomNumberCatProductTO)
		//		WebUI.clickImage(randomNumberCatProductTO, FailureHandling.STOP_ON_FAILURE)
		//		clickJS(randomNumberCatProductElm, 5)
		//println "inside the cat"
		WebUI.scrollToElement(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/Shared/FooterAlJedaei'), 15)
		WebUI.delay(5)
		//println "scrolled to end"
		TestObject items = new TestObject()
		items.addProperty("xpath",ConditionType.EQUALS,'//ol[@class="products  list items product-items row row-col-lg-3"]/li/div/a')
		WebUI.waitForElementVisible(items, 10)
		List<WebElement> prod = WebUI.findWebElements(items,3)

		Random randomNumberforProduct = new Random()
		int elementIndexproduct = Math.abs(randomNumberforProduct.nextInt(prod.size() - 1)) + 1
		TestObject tb = new TestObject()

		tb.addProperty('xpath', ConditionType.EQUALS, ('('+ items.findPropertyValue("xpath") + ')[' +
				elementIndexproduct) + ']')
		String currentURL = WebUI.getUrl()
		WebUI.doubleClick(tb)
		WebUI.delay(2)
		WebUI.waitForPageLoad(20,FailureHandling.OPTIONAL)

		//println("Opened Product")
		if (WebUI.getUrl().equals(currentURL)) {
			WebUI.click(findTestObject('Object Repository/Helpdesk/AlJedaie/FE/MiniCart/ContinueShoping'), FailureHandling.CONTINUE_ON_FAILURE)
		} else {
			//			KeywordUtil.markPassed('Trying to Get Configurable product')
			String MainXPath = '//div[starts-with(@class,"product-options-wrapper")]/div[@class="fieldset"]'
			TestObject Maintb = new TestObject()
			Maintb.addProperty('xpath', ConditionType.EQUALS, MainXPath)
			WebUI.waitForElementVisible(Maintb, 10,FailureHandling.OPTIONAL)
			List<WebElement> genralDropDowns = WebUI.findWebElements(Maintb, 5)
			//print "Number of options:"
			//println(genralDropDowns.size())
			if (genralDropDowns.size() != 0) {
				for (int i = 1; i <= genralDropDowns.size(); i++) {
					String SubColorxPath= '(' + MainXPath + ')' +'[' + i + ']//div[contains(@class,"swatch-attribute-options")]/div'
					TestObject SubColortb = new TestObject()
					SubColortb.addProperty('xpath', ConditionType.EQUALS, SubColorxPath)
					//WebUI.waitForElementVisible(SubColortb, 10)
					List<WebElement> SubColorElem=WebUiCommonHelper.findWebElements(SubColortb, 5)
					//print "Number of colors:"
					//println(SubColorElem.size())
					if(SubColorElem.size()>0) {
						ScrollToElement(SubColorElem.get(0))
						clickJS(SubColorElem.get(0), 5)
					}


					String SubxPath= '(' + MainXPath + ')' +'[' + i + ']//select'
					TestObject Subtb = new TestObject()
					Subtb.addProperty('xpath', ConditionType.EQUALS, SubxPath)
					//					WebUI.waitForElementVisible(Subtb, 10)
					List<WebElement> SubElem=WebUiCommonHelper.findWebElements(Subtb, 5)
					//println(SubElem.size())
					if(SubElem.size()>0) {
						ScrollToElement(Subtb)
						WebUI.selectOptionByIndex(Subtb, 1)
					}


				}
			}

		}
	}

	@Keyword
	def OpenRandomProductTBS(){

		boolean isMobile=false
		if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Shared/RightNav-Mobile'), 3)) {
			isMobile=true
		}else {
			isMobile=false
		}

		String Categories=""
		if(!isMobile) {
			Categories='Object Repository/Helpdesk/TheBodyShop/FE/Shared/Categories'
		}else {
			clickJS(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Shared/RightNav-Mobile'), 5)
			Categories='Object Repository/Helpdesk/TheBodyShop/FE/Shared/Categories-Mobile'
		}


		List<WebElement> productsCategoriesList = WebUiCommonHelper.findWebElements(findTestObject(Categories), 5)
		//		println "Main Cat: "
		//		println  productsCategoriesList.size()
		Random randomCategory = new Random()
		int randomCategorySelected = randomCategory.nextInt(productsCategoriesList.size()-3) + 1
		TestObject randomNumberCatProductTO = new TestObject()
				.addProperty('xpath', ConditionType.EQUALS, '('
				+ findTestObject(Categories).findPropertyValue("xpath")
				+ ')[' + randomCategorySelected.toString() + ']')
		WebElement randomNumberCatProductElm = WebUiCommonHelper.findWebElement(randomNumberCatProductTO, 5)
		ScrollToElement(randomNumberCatProductTO)
		clickJS(randomNumberCatProductTO, 3)

		WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Shared/ProductsScrollerMainItem'), 10)
		List<WebElement> prod = WebUI.findWebElements(findTestObject('Object Repository/Helpdesk/TheBodyShop/FE/Shared/ProductsScrollerMainItem'),3)
		if(prod.size()>0) {
			Random randomNumberforProduct = new Random()
			int elementIndexproduct = Math.abs(randomNumberforProduct.nextInt(prod.size() ))
			String currentURL = WebUI.getUrl()
			WebElement selectedProduct = prod.get(elementIndexproduct)
			WebElement LinkSelectedElement = selectedProduct.findElement(By.xpath('.//a[contains(@class,"product-item-link")]'))
			ScrollToElement(LinkSelectedElement)
			clickJS(LinkSelectedElement, 5)
			//			WebUI.navigateToUrl("https://www.thebodyshop.com.sa/east/ar/fragrance/view-all-fragrance/black-musk-eau-de-toilette-p-p053023")
			WebUI.delay(2)
			String MainXPath = '//div[@id="product-options-wrapper"]/div[@class="fieldset"]/div[@class="attr-mask require"]'
			TestObject Maintb = new TestObject()
			Maintb.addProperty('xpath', ConditionType.EQUALS, MainXPath)
			WebUI.waitForElementVisible(Maintb, 5,FailureHandling.OPTIONAL)
			List<WebElement> genralDropDowns = WebUI.findWebElements(Maintb, 5)
			if (genralDropDowns.size() != 0) {
				for (int i = 1; i <= genralDropDowns.size(); i++) {
					String SubOption= '(' + MainXPath + ')' +'[' + i + ']/div/div[@option-id]'
					TestObject SubOptiontb = new TestObject()
					SubOptiontb.addProperty('xpath', ConditionType.EQUALS, SubOption)
					List<WebElement> SubOptionElm=WebUiCommonHelper.findWebElements(SubOptiontb, 5)
					if(SubOptionElm.size()>0) {
						ScrollToElement(SubOptionElm.get(0))
						clickJS(SubOptionElm.get(0), 5)
					}
				}
			}
		}else {
			OpenRandomProductTBS()
		}

	}

	@Keyword
	def OpenRandomProductTheBeautySecrets(){
		boolean isMobile=false
		if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/RightNav-Mobile'), 3)) {
			isMobile=true
		}else {
			isMobile=false
		}
		if(isMobile) {
			WebUI.click(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/RightNav-Mobile'))
		}
		List<WebElement> productsCategoriesList = WebUiCommonHelper.findWebElements(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/Categories'), 5)
		println "Main Cat: "
		println  productsCategoriesList.size()
		Random randomCategory = new Random()
		int randomCategorySelected = randomCategory.nextInt(productsCategoriesList.size()-1) + 1
		//		randomCategorySelected =2
		TestObject randomNumberCatProductTO = new TestObject()
				.addProperty('xpath', ConditionType.EQUALS, '('
				+ findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/Categories').findPropertyValue("xpath")
				+ ')[' + randomCategorySelected.toString() + ']')
		WebElement randomNumberCatProductElm = WebUiCommonHelper.findWebElement(randomNumberCatProductTO, 5)
		ScrollToElement(randomNumberCatProductTO)
		clickJS(randomNumberCatProductTO, 3)


		List<WebElement> productsCategoriesListL2 = WebUiCommonHelper.findWebElements(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/CategoriesL2'), 5)
		println "Sub Cat: "
		println  productsCategoriesListL2.size()
		if(productsCategoriesListL2.size()>0) {
			Random randomCategoryL2 = new Random()
			int randomCategorySelectedL2 = randomCategoryL2.nextInt(productsCategoriesListL2.size()) + 1
			String randomNumberCatProductTOXPathL2='('+ findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/CategoriesL2').findPropertyValue("xpath")+ ')[' + randomCategorySelectedL2.toString() + ']/a'
			println randomNumberCatProductTOXPathL2
			TestObject randomNumberCatProductTOL2 = new TestObject()
			randomNumberCatProductTOL2.addProperty('xpath', ConditionType.EQUALS, randomNumberCatProductTOXPathL2)
			WebElement randomNumberCatProductElmL2 = WebUiCommonHelper.findWebElement(randomNumberCatProductTOL2, 5)
			ScrollToElement(randomNumberCatProductElmL2)
			clickJS(randomNumberCatProductTOL2, 3)
		}

		WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/ProductsScrollerMainItem'), 10)
		List<WebElement> prod = WebUI.findWebElements(findTestObject('Object Repository/Helpdesk/TheBeautySecrets/KSA/FE/Shared/ProductsScrollerMainItem'),3)
		println "Products: "
		println  prod.size()
		if(prod.size()>0) {
			Random randomNumberforProduct = new Random()
			int elementIndexproduct = Math.abs(randomNumberforProduct.nextInt(prod.size() ))
			String currentURL = WebUI.getUrl()
			WebElement selectedProduct = prod.get(elementIndexproduct)
			WebElement LinkSelectedElement = selectedProduct.findElement(By.xpath('.//a[@class="product-item-link"]'))
			ScrollToElement(LinkSelectedElement)
			clickJS(LinkSelectedElement, 5)
			//			WebUI.navigateToUrl("https://www.thebodyshop.com.sa/east/ar/fragrance/view-all-fragrance/black-musk-eau-de-toilette-p-p053023")
			WebUI.delay(2)
			//TBES does not have configurable products
			//			String MainXPath = '//div[@id="product-options-wrapper"]/div[@class="fieldset"]/div[@class="attr-mask require"]'
			//			TestObject Maintb = new TestObject()
			//			Maintb.addProperty('xpath', ConditionType.EQUALS, MainXPath)
			//			WebUI.waitForElementVisible(Maintb, 5,FailureHandling.OPTIONAL)
			//			List<WebElement> genralDropDowns = WebUI.findWebElements(Maintb, 5)
			//			if (genralDropDowns.size() != 0) {
			//				for (int i = 1; i <= genralDropDowns.size(); i++) {
			//					String SubOption= '(' + MainXPath + ')' +'[' + i + ']/div/div[@option-id]'
			//					TestObject SubOptiontb = new TestObject()
			//					SubOptiontb.addProperty('xpath', ConditionType.EQUALS, SubOption)
			//					List<WebElement> SubOptionElm=WebUiCommonHelper.findWebElements(SubOptiontb, 5)
			//					if(SubOptionElm.size()>0) {
			//						clickJS(SubOptionElm.get(0), 5)
			//					}
			//				}
			//			}
		}else {
			OpenRandomProductTheBeautySecrets()
		}

	}

	@Keyword
	def OpenRandomProductAlShamasy(){
		boolean isMobile=false
		if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Shared/RightNav-Mobile'), 3)) {
			isMobile=true
		}else {
			isMobile=false
		}

		if(isMobile) {
			ScrollToElement(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Shared/RightNav-Mobile'))
			clickJS(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Shared/RightNav-Mobile'), 5)
		}
		List<WebElement> productsCategoriesList = WebUiCommonHelper.findWebElements(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Shared/Categories'), 5)
		println "Main Cat: "
		println  productsCategoriesList.size()
		Random randomCategory = new Random()
		int randomCategorySelected = randomCategory.nextInt(productsCategoriesList.size()-1) + 1
		//		randomCategorySelected =2
		TestObject randomNumberCatProductTO = new TestObject()
				.addProperty('xpath', ConditionType.EQUALS, '('
				+ findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Shared/Categories').findPropertyValue("xpath")
				+ ')[' + randomCategorySelected.toString() + ']')
		WebElement randomNumberCatProductElm = WebUiCommonHelper.findWebElement(randomNumberCatProductTO, 5)
		ScrollToElement(randomNumberCatProductTO)
		clickJS(randomNumberCatProductTO, 3)

		WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Shared/ProductsScrollerMainItem'), 10)
		List<WebElement> prod = WebUI.findWebElements(findTestObject('Object Repository/Helpdesk/AlShamasy/FE/Shared/ProductsScrollerMainItem'),3)
		println "Products: "
		println  prod.size()
		if(prod.size()>0) {
			Random randomNumberforProduct = new Random()
			int elementIndexproduct = Math.abs(randomNumberforProduct.nextInt(prod.size() ))
			String currentURL = WebUI.getUrl()
			WebElement selectedProduct = prod.get(elementIndexproduct)
			WebElement LinkSelectedElement = selectedProduct.findElement(By.xpath('.//a[@class="product-item-link"]'))
			ScrollToElement(LinkSelectedElement)
			clickJS(LinkSelectedElement, 5)
			//			WebUI.navigateToUrl("https://www.thebodyshop.com.sa/east/ar/fragrance/view-all-fragrance/black-musk-eau-de-toilette-p-p053023")
			WebUI.delay(2)
			//TBES does not have configurable products
			//			String MainXPath = '//div[@id="product-options-wrapper"]/div[@class="fieldset"]/div[@class="attr-mask require"]'
			//			TestObject Maintb = new TestObject()
			//			Maintb.addProperty('xpath', ConditionType.EQUALS, MainXPath)
			//			WebUI.waitForElementVisible(Maintb, 5,FailureHandling.OPTIONAL)
			//			List<WebElement> genralDropDowns = WebUI.findWebElements(Maintb, 5)
			//			if (genralDropDowns.size() != 0) {
			//				for (int i = 1; i <= genralDropDowns.size(); i++) {
			//					String SubOption= '(' + MainXPath + ')' +'[' + i + ']/div/div[@option-id]'
			//					TestObject SubOptiontb = new TestObject()
			//					SubOptiontb.addProperty('xpath', ConditionType.EQUALS, SubOption)
			//					List<WebElement> SubOptionElm=WebUiCommonHelper.findWebElements(SubOptiontb, 5)
			//					if(SubOptionElm.size()>0) {
			//						clickJS(SubOptionElm.get(0), 5)
			//					}
			//				}
			//			}
		}else {
			OpenRandomProductAlShamasy()
		}

	}


	@Keyword
	def OpenRandomProductkarazlinenKSA(){
		boolean isMobile=WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/karazlinen/karazlinen-KSA/SharedScripts/isMobile'), [:],	FailureHandling.CONTINUE_ON_FAILURE)
		//		if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/BottomMenu-Mobile'),2)) {
		//			isMobile=true
		//		}

		WebUI.callTestCase(findTestCase('Test Cases/Helpdesk/karazlinen/karazlinen-KSA/SharedScripts/ClickLogo'), [:],	FailureHandling.STOP_ON_FAILURE)

		WebUI.click(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/RightNavigator'))

		List<WebElement> productsCategoriesList = WebUiCommonHelper.findWebElements(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/Categories'), 5)
		print "Main Cat: "
		println  productsCategoriesList.size()
		Random randomCategory = new Random()
		int randomCategorySelected = randomCategory.nextInt(productsCategoriesList.size()) + 1
		//		randomCategorySelected =2
		String selectedMainCatXPath = '(' + findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/Categories').findPropertyValue("xpath")+ ')[' + randomCategorySelected.toString() + ']/a'
		println selectedMainCatXPath
		TestObject randomNumberCatProductTO = new TestObject()
		randomNumberCatProductTO.addProperty('xpath', ConditionType.EQUALS, selectedMainCatXPath)
		WebElement randomNumberCatProductElm = WebUiCommonHelper.findWebElement(randomNumberCatProductTO, 5)
		ScrollToElement(randomNumberCatProductTO)
		clickJS(randomNumberCatProductTO, 3)

		int trialSubs=0
		while(trialSubs<5) {
			trialSubs++
			List<WebElement> productsCategoriesListL2_1=null
			List<WebElement> productsCategoriesListL2_2=null

			productsCategoriesListL2_1 = WebUiCommonHelper.findWebElements(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/CategoriesL2_1'), 3)
			productsCategoriesListL2_2 = WebUiCommonHelper.findWebElements(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/CategoriesL2_2'), 3)
			println "Sub Cat: "
			println  productsCategoriesListL2_1.size()
			println  productsCategoriesListL2_2.size()
			if(!WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/ProductsGallery'), 2)
			& productsCategoriesListL2_1.size()>0) {
				Random randomCategoryL2 = new Random()
				int randomCategorySelectedL2 = randomCategoryL2.nextInt(productsCategoriesListL2_1.size()) + 1
				//				randomCategorySelectedL2=6
				String randomNumberCatProductTOXPathL2='('+ findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/CategoriesL2_1').findPropertyValue("xpath")+ ')[' + randomCategorySelectedL2.toString() + ']'
				println randomNumberCatProductTOXPathL2
				TestObject randomNumberCatProductTOL2 = new TestObject()
				randomNumberCatProductTOL2.addProperty('xpath', ConditionType.EQUALS, randomNumberCatProductTOXPathL2)
				WebElement randomNumberCatProductElmL2 = WebUiCommonHelper.findWebElement(randomNumberCatProductTOL2, 5)
				ScrollToElement(randomNumberCatProductElmL2)
				clickJS(randomNumberCatProductTOL2, 3)
			}else if(!WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/ProductsGallery'), 2)
			& productsCategoriesListL2_2.size()>0) {
				Random randomCategoryL2 = new Random()
				int randomCategorySelectedL2 = randomCategoryL2.nextInt(productsCategoriesListL2_2.size()) + 1
				//randomCategorySelectedL2=6
				String randomNumberCatProductTOXPathL2='('+ findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Shared/CategoriesL2_2').findPropertyValue("xpath")+ ')[' + randomCategorySelectedL2.toString() + ']'
				println randomNumberCatProductTOXPathL2
				TestObject randomNumberCatProductTOL2 = new TestObject()
				randomNumberCatProductTOL2.addProperty('xpath', ConditionType.EQUALS, randomNumberCatProductTOXPathL2)
				WebElement randomNumberCatProductElmL2 = WebUiCommonHelper.findWebElement(randomNumberCatProductTOL2, 5)
				ScrollToElement(randomNumberCatProductElmL2)
				clickJS(randomNumberCatProductTOL2, 3)
			}else {
				break
			}
		}


		ScrollToBottom()
		WebUI.delay(3)
		WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/ProductsGallery'), 10)
		List<WebElement> prod = WebUI.findWebElements(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/ProductsGallery'),3)
		println "Products: "
		println  prod.size()
		if(prod.size()>0) {
			Random randomNumberforProduct = new Random()
			int elementIndexproduct = Math.abs(randomNumberforProduct.nextInt(prod.size() ))
			String currentURL = WebUI.getUrl()
			WebElement selectedProduct = prod.get(elementIndexproduct)
			WebElement LinkSelectedElement = selectedProduct.findElement(By.xpath('./div[contains(@class,"item-title-")]/a'))
			ScrollToElement(LinkSelectedElement)
			clickJS(LinkSelectedElement, 5)
			//			WebUI.navigateToUrl("https://www.thebodyshop.com.sa/east/ar/fragrance/view-all-fragrance/black-musk-eau-de-toilette-p-p053023")
			//			WebUI.navigateToUrl("https://karazlinen.com/ar-sa/sculpted-calvin-klein-towel.html")
			//			WebUI.navigateToUrl("https://karazlinen.com/ar-sa/printed-silk-duvet-set-4pcs-2.html")
			//			WebUI.navigateToUrl("https://karazlinen.com/ar-sa/victoria-mattress.html")
			WebUI.delay(2)

			//Karaz configurable products
			String MainXPath = '//h2[contains(@class,"option-titleOption")]/parent::div'
			String OptionsChoices = ".//button[not(@disabled)]"
			if(isMobile) {
				MainXPath = '//div[contains(@class,"option-root-")]'
				OptionsChoices = ".//button[not(@disabled)]"
			}
			TestObject Maintb = new TestObject()
			Maintb.addProperty('xpath', ConditionType.EQUALS, MainXPath)
			ScrollToElement(Maintb)
			WebUI.waitForElementVisible(Maintb, 5,FailureHandling.OPTIONAL)
			List<WebElement> RequiredOptions = WebUI.findWebElements(Maintb, 5)
			RequiredOptions.each {
				if(!isMobile) {
					List<WebElement> RequiredOptionsChoices =it.findElements(By.xpath(OptionsChoices))
					if(RequiredOptionsChoices.size()>0) {
						ScrollToElement(RequiredOptionsChoices.get(0))
						RequiredOptionsChoices.get(0).click()
					}
				}else {
					List<WebElement> RequiredOptionsChoices =it.findElements(By.xpath(OptionsChoices))
					if(RequiredOptionsChoices.size()>0) {
						ScrollToElement(RequiredOptionsChoices.get(0))
						RequiredOptionsChoices.get(0).click()
						if(WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/OptionDialogSelection'), 3)) {
							ScrollToElement(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/OptionDialogSelection'))
							clickJS(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/OptionDialogSelection'), 3)
						}
					}
				}

			}
		}else {
			OpenRandomProductkarazlinenKSA()
		}

		String PriceElement = 'Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/Product_Price'
		if(!WebUI.waitForElementVisible(findTestObject('Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/Product_Price'), 3)) {
			PriceElement = 'Object Repository/Helpdesk/karazlinen/karazlinen-KSA/FE/Product/Product_SpecialPrice'
		}

		def ProductPrice = WebUI.getText(findTestObject(PriceElement))
		Float ProductPriceNum=ProductPrice.replace("ر.س", "").replace(" ", "").replace(",", "").toFloat()
		if(ProductPriceNum>4000 ) {
			OpenRandomProductkarazlinenKSA()
		}
	}

	@Keyword
	String decodeEncodedValue(String encoded) {
		return URLDecoder.decode(encoded, StandardCharsets.UTF_8.toString())
	}

	@Keyword
	String encodeValue(String value) {
		return URLEncoder.encode(value, StandardCharsets.UTF_8.toString())
	}

	@Keyword
	def scrollToVerifyElementVisiblity(TestObject testObjectRelativeId) {
		try {
			for(int i=4;i<=0;i--) {
				if (WebUI.verifyElementVisible(testObjectRelativeId,FailureHandling.CONTINUE_ON_FAILURE)) {
					//WebUI.executeJavaScript("window.scrollTo(0, document.body.scrollHeight/2);", null);
					break;
				}
				KeywordUtil.markPassed("try to scroll again")
				WebUI.executeJavaScript("window.scrollTo(0, document.body.scrollHeight/"+i.toString()+");", null);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	@Keyword
	def OpenRandomProductOrange(){


		boolean found=false

		while(!found) {
			WebUI.click(findTestObject('Object Repository/Helpdesk/Orange/FE/Shared/Logo'))
			TestObject items = new TestObject()
			items.addProperty("xpath",ConditionType.EQUALS,'//a[contains(@class,"baseProduct-productName-")]')
			List prod = WebUI.findWebElements(items,30)

			Random randomNumberforProduct = new Random()

			def elementIndexproduct = Math.abs(randomNumberforProduct.nextInt(prod.size() - 1)) + 1

			TestObject tb = new TestObject()

			tb.addProperty('xpath', ConditionType.EQUALS, ('('+ items.findPropertyValue("xpath") + ')[' +
					elementIndexproduct) + ']')

			WebElement element = WebUiCommonHelper.findWebElement(tb, 30)

			clickJS(element, 5)

			if(WebUI.verifyElementVisible(findTestObject('Object Repository/Helpdesk/Orange/FE/Cart/Add to cart - Active'),FailureHandling.OPTIONAL)) {
				found =true
			}
		}
	}

	@Keyword
	def ScrollToElement(TestObject tb) {
		try {
			List<WebElement> element = WebUiCommonHelper.findWebElements(tb, 10)
			//		WebUI.executeJavaScript('arguments[0].scrollIntoView();window.scrollBy(0, -10);', element)
			WebUI.executeJavaScript("arguments[0].scrollIntoView({ block: 'center' });", element)
			WebUI.delay(1)
		}catch(Exception ex) {
			println ex.message
			println ex.cause
			ex.printStackTrace()

		}
	}

	@Keyword
	def ScrollToElement(WebElement element) {
		try {
			WebUI.executeJavaScript("arguments[0].scrollIntoView({ block: 'center' });", Arrays.asList(element))
			WebUI.delay(1)
		}catch(Exception ex) {
			println ex.message
			println ex.cause
			ex.printStackTrace()

		}
	}

	@Keyword
	def ScrollToBottom() {
		try {
			WebUI.executeJavaScript("window.scrollTo(0, document.body.scrollHeight);", null)
			WebUI.delay(1)
		}catch(Exception ex) {
			println ex.message
			println ex.cause
			ex.printStackTrace()

		}
	}

	@Keyword
	def clickJS(TestObject to, int timeout){
		int attempts = 0;
		int trials=5
		while(attempts < trials) {
			attempts++
			try {
				List<WebElement> element = WebUiCommonHelper.findWebElements(to, timeout)
				WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(element.get(0)))
				break;
			}
			catch(Exception ex)
			{
				println ex.getMessage()
				WebUI.delay(1)
				if( attempts.equals(trials)){
					List<WebElement> element = WebUiCommonHelper.findWebElements(to, timeout)
					WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(element.get(0)))
				}
			}
		}
	}

	@Keyword
	def clickJS(WebElement element, int timeout){
		int attempts = 0;
		int trials=5
		while(attempts < trials) {
			attempts++
			try {
				WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(element))
				break;
			}
			catch(org.openqa.selenium.StaleElementReferenceException ex)
			{
				println ex.getMessage()
				WebUI.delay(1)
				if( attempts.equals(trials)){
					WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(element))
				}
			}
		}
	}

	@Keyword
	def checkUsingJS(TestObject to3, int timeout3) {
		try {
			List<WebElement> element = WebUiCommonHelper.findWebElements(to3, timeout3)
			WebUI.executeJavaScript("arguments[0].checked = true", element)
		}catch(Exception ex) {
			println ex.message
			println ex.cause
			ex.printStackTrace()
		}
	}

	@Keyword
	def uncheckUsingJS(TestObject to3, int timeout3) {
		try {
			List<WebElement> element = WebUiCommonHelper.findWebElements(to3, timeout3)
			WebUI.executeJavaScript("arguments[0].checked = false", element)
		}catch(Exception ex) {
			println ex.message
			println ex.cause
			ex.printStackTrace()
		}
	}
	@Keyword
	def checkUsingJS(WebElement element) {
		try {
			WebUI.executeJavaScript("arguments[0].checked = true", element)
		}catch(Exception ex) {
			println ex.message
			println ex.cause
			ex.printStackTrace()
		}
	}

	@Keyword
	def uncheckUsingJS(WebElement element) {
		try {
			WebUI.executeJavaScript("arguments[0].checked = false", element)
		}catch(Exception ex) {
			println ex.message
			println ex.cause
			ex.printStackTrace()
		}
	}

	@Keyword
	String getSecondURLPart(String url) {
		List<String> URLParts = url.findAll("/.*?/")
		return URLParts.get(URLParts.size()-1)
	}

	@Keyword
	String getSecondURLPart() {
		getSecondURLPart(WebUI.getUrl())
	}

	@Keyword
	String getCountryNamekarazlinen() {
		String LastURLPart = getSecondURLPart(GlobalVariable.FE_URL)
		switch(LastURLPart) {
			case '/ar-sa/':
				return 'السعودية'
				break
			case '/ar-ae/':
				return 'الإمارات'
				break
			case '/ar-kw/':
				return 'الكويت'
				break
			case '/ar-om/':
				return 'سلطنة عمان'
				break
			case '/ar-bh/':
				return 'البحرين'
				break
			case '/ar-qa/':
				return 'قطر'
				break
		}
	}
}
