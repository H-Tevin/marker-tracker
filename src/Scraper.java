import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

public class Scraper {
    public Scraper() {
    }

    public static void main(String[] args) throws Exception {
        System.out.println("running");
        ArrayList<Item> product = new ArrayList();
        String url = "https://www.amazon.com/dry-erase-markers/s?k=dry+erase+markers";
        (new Extract()).getData(product, url);
        Iterator var4 = product.iterator();

        while(var4.hasNext()) {
            Item runner = (Item)var4.next();
            System.out.println(runner.toString());
        }

        new StoreData();
        Connection conn = StoreData.getConnection();
        new StoreData();
        StoreData.createtable(conn);
        new StoreData();
        StoreData.insert(product, conn);
        System.out.println("done ");
    }
}
