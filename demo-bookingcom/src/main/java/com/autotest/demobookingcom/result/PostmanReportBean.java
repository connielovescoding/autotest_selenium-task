package com.autotest.demobookingcom.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostmanReportBean {
    
    private Status status;
    private String reason;
    private int totalTestCases;
    private int finishedTestCases;
    private int numberOfSuccessfulTestCases;
    private int numberOfFailureTestCases;
    private String successRatioInFinishedCases;
    private int unfinishedTestCases;
    private String successRatioInTotal;

    public String calSuccessRatioInFinishedCases() {
        return ((double) numberOfSuccessfulTestCases / finishedTestCases) * 100 + "%";
    }

    public String calSuccessRatioInTotal() {
        return ((double) numberOfSuccessfulTestCases / totalTestCases) * 100 + "%";
    }

    @Getter
    public static enum Status {

        FINISHED("Finished Testing"), //
        FAILED("Failed to Test"), //
        ;

        private String statement;

        private Status(String statement) {
            this.statement = statement;
        }

    }
}