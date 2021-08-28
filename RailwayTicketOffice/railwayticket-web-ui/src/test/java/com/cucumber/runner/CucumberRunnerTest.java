package com.cucumber.runner;

import com.cucumber.pages.HomePage;
import com.railwayticket.AppMain;
import io.cucumber.java.en.Given;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

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
