package com.autotest.demobookingcom.testcase;

import org.openqa.selenium.WebDriver;
import lombok.Getter;

@Getter
public class TEST19000 implements TestCase {
    
    private WebDriver webDriver;
    private String dependencyId;
    private String testDescription = "go to base url";
    private String parameters = "https://wrongurl.com";
    private String detail = "1.xxx, 2.xxx";
    private String expectedResult = "in booking.com";

    public TEST19000(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Override
    public boolean run() throws Exception {
        return webDriver.getCurrentUrl().contains("wrongurl.com");
    }

    @Override
    public String getDependencyTestId() {
        return this.dependencyId;
    }

    @Override
    public String getTestDescription() {
        return this.testDescription;
    }

    @Override
    public String getParameter() {
        return this.parameters;
    }

    @Override
    public String getTestDetail() {
        return this.detail;
    }

    @Override
    public String getExpectedResult() {
        return this.expectedResult;
    }
    
}
