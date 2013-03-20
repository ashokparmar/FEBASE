/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class Parser {

    public static void main(String args[]) throws IOException {
//        Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        int i = 0;
        int count=0;
        String url = "http://www.flipkart.com/samsung-galaxy-grand-duos-i9082/product-reviews/ITMDHDVTAF5UQAAF?pid=MOBDHDVRPR8V85KS";
        System.out.println("Fetching %s " + url);

        while (true) {
            try{
            String pageUrl;
            pageUrl = url+"&start="+Integer.toString(i);
            Document doc = Jsoup.connect(pageUrl).timeout(10000).get();
            if(i == 0){
            Elements links = doc.select("h1");
            Elements title = links.select("a");
            System.out.println(title.text());
            }

            Elements allReviews = doc.select("div.fclear.fk-review.fk-position-relative.line");
            
            if(allReviews.isEmpty()){
                System.out.println(">>>>>>>>>>>>>>>>>"+allReviews+"<<<<<<<<<<<<<<<");
                break;
            }

            for (Element review : allReviews) {
                count++;
                Elements nameBlock = review.select("div.unit.size1of5.section1");
                System.out.println("\n");
                System.out.println(nameBlock.select("a.fk-font-bold.load-user-widget").text());

                Elements reviewBlock = review.select("div.lastUnit.size4of5.section2");
                System.out.println(reviewBlock.select("div.line.fk-font-big.bmargin5").text());
                System.out.println(reviewBlock.select("p.line.bmargin10").text());
            }
            i += 10;
            }catch(Exception e){
                e.printStackTrace();
                continue;
            }
            
        }
    }
}
