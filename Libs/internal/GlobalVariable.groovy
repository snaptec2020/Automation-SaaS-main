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
    public static Object languageMode
     
    /**
     * <p></p>
     */
    public static Object gDBUrl
     
    /**
     * <p></p>
     */
    public static Object gDBUserName
     
    /**
     * <p></p>
     */
    public static Object gDBPassword
     
    /**
     * <p></p>
     */
    public static Object gJDBCClass
     
    /**
     * <p></p>
     */
    public static Object testSuiteStatus
     
    /**
     * <p></p>
     */
    public static Object textSearch
     
    /**
     * <p></p>
     */
    public static Object ValidPassword
     
    /**
     * <p></p>
     */
    public static Object FixedOTP
     
    /**
     * <p></p>
     */
    public static Object phoneNumber
     

    static {
        try {
            def selectedVariables = TestCaseMain.getGlobalVariables("default")
			selectedVariables += TestCaseMain.getGlobalVariables(RunConfiguration.getExecutionProfile())
            selectedVariables += TestCaseMain.getParsedValues(RunConfiguration.getOverridingParameters())
    
            URL = selectedVariables['URL']
            Vaild_email = selectedVariables['Vaild_email']
            Wrong_email = selectedVariables['Wrong_email']
            languageMode = selectedVariables['languageMode']
            gDBUrl = selectedVariables['gDBUrl']
            gDBUserName = selectedVariables['gDBUserName']
            gDBPassword = selectedVariables['gDBPassword']
            gJDBCClass = selectedVariables['gJDBCClass']
            testSuiteStatus = selectedVariables['testSuiteStatus']
            textSearch = selectedVariables['textSearch']
            ValidPassword = selectedVariables['ValidPassword']
            FixedOTP = selectedVariables['FixedOTP']
            phoneNumber = selectedVariables['phoneNumber']
            
        } catch (Exception e) {
            TestCaseMain.logGlobalVariableError(e)
        }
    }
}
