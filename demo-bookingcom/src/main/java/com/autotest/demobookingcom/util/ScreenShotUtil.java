package com.autotest.demobookingcom.util;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScreenShotUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(ScreenShotUtil.class);

    public static String captureAndSave(WebDriver webDriver, String testId) {
        File screenshotFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        File screenshotLocation = new File("screenshot/" + DateTimeUtil.getCurrentDate() + testId + ".png");
        try {
            FileUtils.copyFile(screenshotFile, screenshotLocation);
            logger.info("The screenshot has been saved to:" + screenshotLocation);
            return screenshotLocation.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
