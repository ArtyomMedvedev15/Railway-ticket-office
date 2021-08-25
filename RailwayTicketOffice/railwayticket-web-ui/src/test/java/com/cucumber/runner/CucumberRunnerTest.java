package com.cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty","html:target/cucumber-reports",
                            "json:target/cucumber-reports/CucumberTests.json",
                            "json:target/cucumber-reports/CucumberTests.xml"},
                monochrome = true,
                tags = "@home",
                glue = "com.cucumber",
                features = "src/test/resources")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CucumberRunnerTest {
}
