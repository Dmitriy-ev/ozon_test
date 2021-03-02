import org.junit.Test;
import ru.appline.utils.Product;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OzonTest extends BaseTest {

    @Test
    public void startTest() {
        List<Product> list = new LinkedList<>();

        app.getStartPage()
                .searchOzon("iphone")
                //.searchOzon("беспроводные наушники")
                .fillFilterFields("Цена", "до", "100000")
                //.fillMultiFilterCheckBoxBrand("Бренды", "Beats", "Samsung", "Xiaomi")
                .fillFilterCheckBox("Высокий рейтинг")
                .fillMultiFilterCheckBox("Беспроводные интерфейсы", "NFC")
                .addToBasket(list, 8)
                .checkProductNameBasket(list)
                .removeAllAndcheckBasket(list);
                //.slep();
    }

}
