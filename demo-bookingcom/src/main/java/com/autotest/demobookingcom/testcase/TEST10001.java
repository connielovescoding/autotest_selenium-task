package com.autotest.demobookingcom.testcase;

import org.openqa.selenium.WebDriver;
import lombok.Getter;


@Getter
public class TEST10001 implements TestCase {
    
    private WebDriver webDriver;
    private String dependencyId = "TEST10000 >> TEST10001";
    private String testDescription = "check title";
    private String parameters;
    private String detail = "1.xxx, 2.xxx, 3.xxx";
    private String expectedResult = "title=Booking.com | Official site | The best hotels, flights, car rentals & accommodations";

    public TEST10001(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Override
    public boolean run() throws Exception {
        return webDriver.getTitle().equals("Booking.com | Official site | The best hotels, flights, car rentals & accommodations");
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
