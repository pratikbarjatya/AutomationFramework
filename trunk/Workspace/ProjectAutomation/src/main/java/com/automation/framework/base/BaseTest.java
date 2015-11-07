package com.automation.framework.base;

import com.automation.framework.core.Driver;
import com.automation.framework.core.Reporting;
import com.automation.framework.core.Wrappers;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by gadrea on 11/6/2015.
 */
public class BaseTest {

    //Variables
    public String className;
    public String env;
    public String browser;
    public String browserVersion;
    public String OS;
    public Driver asapDriver;
    public WebDriver driver = null;
    public Reporting Reporter;
    public Wrappers doAction;
    public HashMap<String, String> Environment = new HashMap<String, String>();
    public HashMap<String, String> Dictionary = new HashMap<String, String>();

    public void beforeClass() throws IOException {
        //Print class name to console
        System.out.println(className);

        //get env
        env = System.getProperty("envName");
        Assert.assertNotNull(env, "No Environment Parameter value received");

        //Add to envs
        Environment.put("CLASSNAME", className);
        Environment.put("BROWSER", browser);
        Environment.put("ENV_CODE", env);

        //Initiating asapDriver
        asapDriver = new Driver(Dictionary, Environment);
        asapDriver.createExecutionFolders();
        asapDriver.fetchEnvironmentDetailsFromConfigXML();

        //Instantiate reporter
        Reporter = new Reporting(Dictionary, Environment);
        Reporter.createSummaryReport();
        Reporter.createJenkinsReport();
    }

    public void beforeMethod(Method method){
        String testName = method.getName();
        System.out.println("Before Method for test " + testName);
        asapDriver.getDataForTest(testName);
        Reporter.createTestLevelReport(testName);
    }

    public void afterMethod(Method method) {
        String testName = method.getName();
        System.out.println("After Method" + testName);
        asapDriver.setReferenceData();
        Reporter.closeTestLevelReport(testName);
    }

    public void afterClass(){
        System.out.println("After Class " + className);
        Reporter.closeTestSummaryReport();
        if(driver != null)
            driver.quit();
    }

}
