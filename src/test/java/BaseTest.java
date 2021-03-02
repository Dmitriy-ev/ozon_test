import org.junit.After;
import org.junit.Before;
import ru.appline.managers.InitManager;
import ru.appline.managers.ManagerPages;

public class BaseTest {

    protected ManagerPages app = ManagerPages.getManagerPages();

    @Before
    public void beforeEach() {
        InitManager.initFramework();
    }

    @After
    public void afterEach() {
        InitManager.quitFramework();
    }
}
