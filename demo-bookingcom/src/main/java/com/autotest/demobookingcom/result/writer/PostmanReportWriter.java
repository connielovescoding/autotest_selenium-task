package com.autotest.demobookingcom.result.writer;

import com.autotest.demobookingcom.result.PostmanReportBean;

public class PostmanReportWriter {
    
    public static PostmanReportBean writeReportToPostman(boolean isFinished, String reason) {
        PostmanReportBean postmanReportBean = new PostmanReportBean();
        postmanReportBean.setStatus(isFinished ? PostmanReportBean.Status.FINISHED : PostmanReportBean.Status.FAILED);
        postmanReportBean.setReason(reason);
        return postmanReportBean;
    }

}
