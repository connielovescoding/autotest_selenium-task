package com.autotest.demobookingcom.testcase;

public interface TestCase {
    
    boolean run() throws Exception;
    
    String getDependencyTestId();
    String getTestDescription();
    String getParameter();
    String getTestDetail();
    String getExpectedResult();
}
