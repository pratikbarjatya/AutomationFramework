package com.autodesk.accounts;

import com.automation.framework.core.Reporting;
import com.automation.framework.core.Wrappers;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

/**
 * Created by gadrea on 5/5/2015.
 */
public class ProfilePage {

    private Reporting Reporter;
    private WebDriver driver;
    private HashMap<String, String> Dictionary;
    private HashMap<String, String> Environment;
    private Wrappers objWrapper;

    public static final String pageTitle = "Autodesk - User Profile";
    String tbProfile = "classname:=profile";

    //Define the constructor
    public ProfilePage(WebDriver GDriver, HashMap<String, String> GDictionary, HashMap<String, String> GEnvironment, Reporting GReporter)
    {
        Reporter = GReporter;
        driver = GDriver;
        Dictionary = GDictionary;
        Environment = GEnvironment;
        objWrapper = new Wrappers(driver, Reporter);
    }

    public boolean shouldHaveMyProfileTab(){
        boolean isProfileTabPresent = objWrapper.isWebElementPresent(tbProfile);
        if(isProfileTabPresent){
            Reporter.writeToTestLevelReport("Check for Profile Tab", "Profile tab should be present", "Profile tab is present", "Pass");
            return true;
        }

        Reporter.writeToTestLevelReport("Check for Profile Tab", "Profile tab should be present", "Profile tab is not present", "Fail");
        return false;
    }
}
