package com.sgcib.kata.bank.acceptancetests.config;

import com.vimalselvam.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.File;

/**
 * @author fahmi
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        glue = {"com.sgcib.kata.bank.acceptancetests.steps"},
        plugin = {"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:target/reports/report.html"})
public class CucumberTest {

    @AfterClass
    public static void teardown() {
        Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
    }
}