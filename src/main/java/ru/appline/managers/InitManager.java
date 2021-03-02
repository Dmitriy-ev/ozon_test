package ru.appline.managers;

import java.util.concurrent.TimeUnit;

import static ru.appline.managers.DriverManager.getDriver;
import static ru.appline.managers.DriverManager.quitDriver;
import static ru.appline.utils.PropConst.*;

public class InitManager {


    public static TestPropManager props = TestPropManager.getTestPropManager();


    public static void initFramework() {
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(props.getProperty(PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
    }

    public static void quitFramework() {
        quitDriver();
    }
}
