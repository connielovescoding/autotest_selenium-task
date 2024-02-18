package com.autotest.demobookingcom.result;

import java.time.LocalDateTime;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CsvReportBean {
    
    @CsvBindByName(column = "Test Id")
    private String testId;

    private String dependencyTestId;

    @CsvDate("yyyy-mm-dd HH:mm:ss")
    @CsvBindByName(column = "Test Time")
    private LocalDateTime testTime;

    @CsvBindByName(column = "Description")
    private String testDescription;

    @CsvBindByName(column = "Detail")
    private String testDetail;

    @CsvBindByName(column = "Result")
    private String testResult;

    @CsvBindByName(column = "Expected Result")
    private String expectedResult;

    @CsvBindByName(column = "Evidence")
    private String evidence;

    private String parameter;

}
