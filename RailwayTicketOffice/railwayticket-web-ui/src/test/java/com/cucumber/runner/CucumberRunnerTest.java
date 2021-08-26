package com.cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty","html:target/cucumber-reports",
                            "json:target/cucumber-reports/CucumberTests.json",
                            "json:target/cucumber-reports/CucumberTests.xml"},
                monochrome = true,
                tags = {"@listtrain or @home or @listclients"},
                glue = "com.cucumber",
                features = "src/test/resources",
        strict = true)
public class CucumberRunnerTest {
}
