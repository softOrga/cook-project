package com.MyCooking.runner;



import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",  
    glue = "com.MyCooking.steps",            
    plugin = { "pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json" },
    monochrome = true
)
public class TestRunner {
	
}