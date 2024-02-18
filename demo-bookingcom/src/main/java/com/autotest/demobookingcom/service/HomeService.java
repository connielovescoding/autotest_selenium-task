package com.autotest.demobookingcom.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.autotest.demobookingcom.result.CsvReportBean;
import com.autotest.demobookingcom.result.PostmanReportBean;
import com.autotest.demobookingcom.result.writer.CsvReportWriter;
import com.autotest.demobookingcom.testcase.TEST10000;
import com.autotest.demobookingcom.testcase.TEST10001;
import com.autotest.demobookingcom.testcase.TEST19000;
import com.autotest.demobookingcom.testcase.TEST19001;
import com.autotest.demobookingcom.testcase.TestCase;
import com.autotest.demobookingcom.util.DateTimeUtil;
import com.autotest.demobookingcom.util.ScreenShotUtil;


@Service
public class HomeService {
    
    @Autowired
    @Qualifier(value = "webDriverBean")
    WebDriver webDriver;

    @Autowired
    CsvReportWriter csvReportWriter;

    public PostmanReportBean runAllTests() throws IOException {
        PostmanReportBean postmanReportBean = new PostmanReportBean();
        List<CsvReportBean> csvReportBeans = new ArrayList<>();

        List<TestCase> testCases = new ArrayList<>();
        testCases.add(new TEST10000(webDriver));
        testCases.add(new TEST10001(webDriver));
        testCases.add(new TEST19000(webDriver));
        testCases.add(new TEST19001(webDriver));

        for (TestCase testCase : testCases) {
            try {
                boolean isPassed = testCase.run();
                String screenshotpath = ScreenShotUtil.captureAndSave(webDriver, testCase.getClass().getSimpleName());

                CsvReportBean csvReportBean = CsvReportBean.builder()
                    .testId(testCase.getClass().getSimpleName())
                    .dependencyTestId(testCase.getDependencyTestId())
                    .testTime(LocalDateTime.now())
                    .testDescription(testCase.getTestDescription())
                    .parameter(testCase.getParameter())
                    .testDetail(testCase.getTestDetail())
                    .expectedResult(testCase.getExpectedResult())
                    .testResult(isPassed ? "PASSED" : "FAILED")
                    .evidence(screenshotpath)
                    .build();

                csvReportBeans.add(csvReportBean);

                if (isPassed) {
                    postmanReportBean.setNumberOfSuccessfulTestCases(postmanReportBean.getNumberOfSuccessfulTestCases() + 1);
                } else {
                    postmanReportBean.setNumberOfFailureTestCases(postmanReportBean.getNumberOfFailureTestCases() + 1);
                }
                
                postmanReportBean.setFinishedTestCases(postmanReportBean.getFinishedTestCases() + 1);
                postmanReportBean.setStatus(PostmanReportBean.Status.FINISHED);
                
            } catch (Exception e) {
                CsvReportBean csvReportBean = CsvReportBean.builder()
                    .testId(testCase.getClass().getSimpleName())
                    .dependencyTestId(testCase.getDependencyTestId())
                    .testTime(LocalDateTime.now())
                    .testDescription(testCase.getTestDescription())
                    .parameter(testCase.getParameter())
                    .testDetail(testCase.getTestDetail())
                    .expectedResult(testCase.getExpectedResult())
                    .testResult("ERROR")
                    .evidence(null)
                    .build();
                
                csvReportBeans.add(csvReportBean);
                postmanReportBean.setUnfinishedTestCases(postmanReportBean.getUnfinishedTestCases() + 1);
                postmanReportBean.setStatus(PostmanReportBean.Status.FAILED);
                postmanReportBean.setReason(e.getMessage());
            }
        }
        postmanReportBean.setTotalTestCases(testCases.size());
        postmanReportBean.setSuccessRatioInFinishedCases(postmanReportBean.calSuccessRatioInFinishedCases());
        postmanReportBean.setSuccessRatioInTotal(postmanReportBean.calSuccessRatioInTotal());
        csvReportWriter.writeReportToCsv(csvReportBeans, DateTimeUtil.getCurrentDate() + "_home");
        return postmanReportBean;
    }
}