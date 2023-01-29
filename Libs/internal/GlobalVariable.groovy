package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.main.TestCaseMain


/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p></p>
     */
    public static Object URL
     
    /**
     * <p></p>
     */
    public static Object Vaild_email
     
    /**
     * <p></p>
     */
    public static Object Wrong_email
     
    /**
     * <p></p>
     */
    public static Object store
     
    /**
     * <p></p>
     */
    public static Object languageMode
     
    /**
     * <p></p>
     */
    public static Object URL_AR
     
    /**
     * <p></p>
     */
    public static Object URL_EN
     

    static {
        try {
            def selectedVariables = TestCaseMain.getGlobalVariables("default")
			selectedVariables += TestCaseMain.getGlobalVariables(RunConfiguration.getExecutionProfile())
            selectedVariables += TestCaseMain.getParsedValues(RunConfiguration.getOverridingParameters())
    
            URL = selectedVariables['URL']
            Vaild_email = selectedVariables['Vaild_email']
            Wrong_email = selectedVariables['Wrong_email']
            store = selectedVariables['store']
            languageMode = selectedVariables['languageMode']
            URL_AR = selectedVariables['URL_AR']
            URL_EN = selectedVariables['URL_EN']
            
        } catch (Exception e) {
            TestCaseMain.logGlobalVariableError(e)
        }
    }
}
