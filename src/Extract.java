import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class Extract {

    Document document;
    Elements harvester;

    void getData(ArrayList<Item> products, String url) {
        try {
            while(products.size() < 1000) {
                document = Jsoup.connect(url).userAgent("user").get();
                harvester = document.select("span.a-size-base-plus");
                for (Element runner : harvester) {
                    products.add(new Item(runner.text()));
                }
                /*
                int count = 1;
                harvester = document.select("span.a-offscreen");// data-a-size="l" data-a-color="base"><span class="a-offscreen">$6.49</span><span aria-hidden="true"><span class="a-price-symbol">$</span><span class="a-price-whole">6<span class="a-price-decimal">.</span></span><span class="a-price-fraction">49</span></span></span> <span class="a-price a-text-price" data-a-size="b" data-a-strike="true" data-a-color="secondary"><span class="a-offscreen">$6.99</span><span aria-hidden="true">$6.99</span></span>
                for (Element runner : harvester) {
                    //arraylist.sort > 5 then arraylist.get 0,1,2 somewhere else now
                    if (products.size() < count) {
                        products.get(count).setprice(runner.text());
                    } else {
                        break;
                    }
                    count++;

                }
*/
                //Element content = document.getElementById("content");
                //Elements links = content.getElementsByTag("a");
                //for (Element link : links) {
                //    String linkHref = link.attr("href");
                //    String linkText = link.text();
                //}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
