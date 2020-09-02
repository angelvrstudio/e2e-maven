package com.dso.e2eCucumberSelenium.stepDefinition.login;

import com.dso.e2eCucumberSelenium.enviroment.Hooks;
import com.dso.e2eCucumberSelenium.pom.LoginPagePOM;
import cucumber.api.java.en.Then;
import org.junit.Assert;


public class LoginValidations {
    LoginPagePOM loginPage;
    /**
     * The Logger
     */
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(LoginValidations.class);

    @Then("^Message displayed Login Successfully for the user (.+)$")
    public void message_displayed_login_successfully_for_the_user(String userName) throws Throwable {
        loginPage = new LoginPagePOM(Hooks.getWebDriver());
    }

    @Then("^Message displayed Login unsuccessfully$")
    public void message_displayed_login_unsuccessfully() throws Throwable {
        String msg="email duplicated email";
        loginPage = new LoginPagePOM(Hooks.getWebDriver());
        String label=loginPage.loginUnsuccesfully();
        Assert.assertEquals(label,msg);
    }
}

