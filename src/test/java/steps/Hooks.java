package steps;


import io.qameta.allure.Attachment;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.appline.managers.InitManager;
import ru.appline.managers.TestPropManager;

import static ru.appline.managers.DriverManager.getDriver;
import static ru.appline.utils.PropConst.APP_URL;

public class Hooks {

    TestPropManager props = TestPropManager.getTestPropManager();
    @Before
    public void beforeAll()
    {
        InitManager.initFramework();
        getDriver().get(props.getProperty(APP_URL));
    }

    @After
    public void afterAll()
    {
        InitManager.quitFramework();
    }
    @Attachment(value = "screenshot", type = "image/png", fileExtension = ".png")
    public static byte[] addScreenshot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
