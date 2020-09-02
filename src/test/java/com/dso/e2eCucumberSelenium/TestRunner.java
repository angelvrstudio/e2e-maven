package com.dso.e2eCucumberSelenium;

import com.dso.e2eCucumberSelenium.enviroment.BrowserCodes;
import com.dso.e2eCucumberSelenium.enviroment.Environment;
import com.dso.e2eCucumberSelenium.enviroment.Hooks;
import com.dso.e2eCucumberSelenium.exception.NotFoundResourceException;
import com.dso.e2eCucumberSelenium.utilsType.EnvPropertiesManagement;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(Cucumber.class)

@CucumberOptions(
		plugin = {
				"pretty",
				"json:target/cucumber.json",
				//"html:target/site/cucumber-pretty",
		},

		//Used only if you want run a specific feature by tag : @login, @search or @filter
		tags = {"@loginUnsuccessful"},

		features = {"src/test/resources/features"},
		glue = {"com.dso.e2eCucumberSelenium.stepDefinition"}
)


public class TestRunner {

	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TestRunner.class);


	@BeforeClass
	public static void startEnvironment() throws Exception {
		String defaultFileName = "defaultEnvironment";
		System.setProperty("environment", defaultFileName);

		if (System.getProperty("environment").equals("defaultEnvironment")) {
			logger.error(" ******************* THE ENVIRONMENT VARIABLE IS OBLIGATED TO SET ***************** ");

			logger.info(" Loading DEFAULT Environment Configuration File --------------------------- : " + System.getProperty("environment") + ".properties");
			logger.info(" Loading the system variable Environment -------------------------- : " + System.getProperty("environment"));
			EnvPropertiesManagement.loadDefaultEnvironmentProperties();
//			throw new Exception("\r\n\r\n******************* THE ENVIRONMENT VARIABLE IS OBLIGATED TO SET *****************\r\n\r\n ");
		} else {

			logger.info(" Loading Environment Configuration File --------------------------- : " + System.getProperty("environment") + ".properties");
			logger.info(" Loading the system variable Environment -------------------------- : " + System.getProperty("environment"));
			EnvPropertiesManagement.loadMvnEnvironmentProperties(System.getProperty("environment"));
		}

		if (EnvPropertiesManagement.getEnvProps().getProperty(BrowserCodes.BROWSER) != null && EnvPropertiesManagement.getEnvProps().getProperty(BrowserCodes.BROWSER) != null) {

			String browser = EnvPropertiesManagement.getEnvProps().getProperty(BrowserCodes.BROWSER);

			String mainURL = "";
			Environment environment = new Environment(browser, mainURL);
			logger.debug("Generating masterthought HTML reports .......");
			Hooks.setEnvironment(environment);

			logger.info("*************************************");

		}

	}

	@AfterClass
	public static void generateHTMLReport() throws InterruptedException, NotFoundResourceException, IOException {
		logger.debug("Stop aplication .......");
		logger.debug("Generating masterthought HTML reports .......");
		File reportOutputDirectory = new File("target");
		List<String> jsonFiles = new ArrayList<>();
		jsonFiles.add("target/cucumber.json");

		String buildNumber = "1";
		String projectName = "cucumberProject";
		boolean runWithJenkins = false;
		boolean parallelTesting = false;

		Configuration configuration = new Configuration(reportOutputDirectory, projectName);
		// optional configuration

		configuration.setBuildNumber(buildNumber);
		// addidtional metadata presented on main page
		configuration.addClassifications("Platform", "Windows");
		configuration.addClassifications("Browser", "Firefox");
		configuration.addClassifications("Branch", "release/1.0");

		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
		Reportable result = reportBuilder.generateReports();
		// and here validate 'result' to decide what to do
		// if report has failed features, undefined steps etc
		logger.debug("Generated Masterthought HTML reports");
	}
}


