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
    public static Object Running_mode
     
    /**
     * <p></p>
     */
    public static Object URL_AR
     
    /**
     * <p></p>
     */
    public static Object URL_EN
     
    /**
     * <p></p>
     */
    public static Object store
     

    static {
        try {
            def selectedVariables = TestCaseMain.getGlobalVariables("default")
			selectedVariables += TestCaseMain.getGlobalVariables(RunConfiguration.getExecutionProfile())
            selectedVariables += TestCaseMain.getParsedValues(RunConfiguration.getOverridingParameters())
    
            URL = selectedVariables['URL']
            Running_mode = selectedVariables['Running_mode']
            URL_AR = selectedVariables['URL_AR']
            URL_EN = selectedVariables['URL_EN']
            store = selectedVariables['store']
            
        } catch (Exception e) {
            TestCaseMain.logGlobalVariableError(e)
        }
    }
}
