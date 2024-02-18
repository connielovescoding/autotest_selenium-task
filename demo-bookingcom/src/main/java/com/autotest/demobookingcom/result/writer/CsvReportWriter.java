package com.autotest.demobookingcom.result.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.autotest.demobookingcom.result.CsvReportBean;

@Component
public class CsvReportWriter {

    @Value("${auto-test_config.reportPath}")
    private String reportPath;

    public void writeReportToCsv(List<CsvReportBean> csvReportBeans, String fileName) throws IOException {
        if (!fileName.endsWith(".csv")) {
            fileName += ".csv";
        }

        String filePath = reportPath + (reportPath.endsWith("/") ? "" : "/") + fileName;

        try (FileWriter csvWriter = new FileWriter(filePath)) {
            csvWriter.append("TestId,DependencyId,TestTime,Description,Parameter,Detail,ExpectedResult,Result,Evidence\n");
            for (CsvReportBean csvReportBean : csvReportBeans) {
                csvWriter.append(String.join(",", 
                    sanitize(csvReportBean.getTestId()),
                    sanitize(csvReportBean.getDependencyTestId()),
                    sanitize(csvReportBean.getTestTime().toString()),
                    sanitize(csvReportBean.getTestDescription()),
                    sanitize(csvReportBean.getParameter()),
                    sanitize(csvReportBean.getTestDetail()),
                    sanitize(csvReportBean.getExpectedResult()),
                    sanitize(csvReportBean.getTestResult()),
                    sanitize(csvReportBean.getEvidence())
                ));
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
        }
    }

    private static String sanitize(String value) {
        return value == null ? "" : "\"" + value.replace("\"", "\"\"") + "\"";
    }
}
