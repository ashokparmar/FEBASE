package helper;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {

	public static final String DIRECTORY_PATH = "/runtime/FeaturedBasedSentimentAnalysis/inputs/Flipkart";
	public static final String CSV_FILE_EXTN = ".csv";

	public static void main(String args[]) throws IOException {
		String[] reviewHeader = { "Product Id", "Product Title", "Reviewer Name", "Review Title", "Review Text", "Rating", "Review Date"};

/*		String productId = "Samsung-Grand-Duos";
		String reviewURL = "http://www.flipkart.com/samsung-galaxy-grand-duos-i9082/product-reviews/ITMDHDVTAF5UQAAF?pid=MOBDHDVRPR8V85KS";*/
		ArrayList<String[]> productsList = new ArrayList<String[]>();
/*		productsList.add(new String[] {"Sony-Xperia-Tipo","http://www.flipkart.com/sony-xperia-tipo/product-reviews/ITMDCWUDZYJFHPH5?pid=MOBDDB8HYXJDA7GK"});
		productsList.add(new String[] {"Samsung-Galaxy-S2","http://www.flipkart.com/samsung-galaxy-s-2-i9100/product-reviews/ITMCZBRRG2PDBVNP?pid=MOBD43T8HNNRUZFQ"});
		productsList.add(new String[] {"Samsung-Galaxy-S3","http://www.flipkart.com/samsung-galaxy-s3/product-reviews/ITMDBWUTHFDZ85CC?pid=MOBD9TPHTT8FHJTP"});
		productsList.add(new String[] {"Samsung-Galaxy-Ace","http://www.flipkart.com/samsung-galaxy-ace-s5830i/product-reviews/ITMDFNDPGZ4NBUFT?pid=MOBDF83QZMH89ARU"});
		productsList.add(new String[] {"Samsung-Galaxy-Y","http://www.flipkart.com/samsung-galaxy-y-duos-s6102/product-reviews/ITMD6VVTRJWBGWGM?pid=MOBD6VVSP8JZRYDN"});
		productsList.add(new String[] {"Nokia-Lumia-620","http://www.flipkart.com/nokia-lumia-620/product-reviews/ITMDGKWYWKMAA2W4?pid=MOBDGH6AKH9ERJAF"});
		productsList.add(new String[] {"Nokia-Asha-311","http://www.flipkart.com/nokia-asha-311/product-reviews/ITMDBGAYMUHRU8YJ?pid=MOBDCBES8Y3RZUHT"});
		productsList.add(new String[] {"nokia-lumia-510","http://www.flipkart.com/nokia-lumia-510/product-reviews/ITMDEUNCGEW8CRGY?pid=MOBDEUNYTUHYPMMT"});
		productsList.add(new String[] {"sony-xperia-j","http://www.flipkart.com/sony-xperia-j/product-reviews/ITMDEGBE4QXM9FJY?pid=MOBDEG3ZVPPZMWTT"});
		productsList.add(new String[] {"sony-xperia-u","http://www.flipkart.com/sony-xperia-u/product-reviews/ITMD9ANRHVQEFVR5?pid=MOBD9AN7PBMXV3GG"});
		productsList.add(new String[] {"motorola-ex226","http://www.flipkart.com/motorola-ex226/product-reviews/ITMD9F9H2GNN94CZ?pid=MOBD9F9EDQGDSGQZ"});
		productsList.add(new String[] {"motorola-atrix-2","http://www.flipkart.com/motorola-atrix-2/product-reviews/ITMD7BNAGFD4YWCD?pid=MOBD9EY7CXXGYNGH"});
		productsList.add(new String[] {"htc-one-x","http://www.flipkart.com/htc-one-x/product-reviews/ITMD85N2VBZATXTB?pid=MOBD85N2DHCYDAXV"});
		productsList.add(new String[] {"htc-explorer","http://www.flipkart.com/htc-explorer/product-reviews/ITMD2KRPPKKEDS2H?pid=MOBD2KRKDNMXGHMJ"});
		productsList.add(new String[] {"htc-8x","http://www.flipkart.com/htc-8x/product-reviews/ITMDF2HYYBV6QH2N?pid=MOBDF2DTGFPZXJ3R"});
		productsList.add(new String[] {"htc-wildfire-s","http://www.flipkart.com/htc-wildfire-s/product-reviews/ITMCZ2PZ4ZQ7HMZQ?pid=MOBCZ2PZXPEN4Z76"});
		productsList.add(new String[] {"htc-desire-x","http://www.flipkart.com/htc-desire-x/product-reviews/ITMDEQDXM2KEWDY2?pid=MOBDE7H2YJTNRMZX"});
		productsList.add(new String[] {"htc-one-v","http://www.flipkart.com/htc-one-v/product-reviews/ITMDEQDWGUREMQJA?pid=MOBD85N2VAETHGYR"});
		productsList.add(new String[] {"blackberry-curve-9220","http://www.flipkart.com/blackberry-curve-9220/product-reviews/ITMD8354Y3YGDQST?pid=MOBD835YGEGFBGGN"});
		productsList.add(new String[] {"blackberry-curve-9320","http://www.flipkart.com/blackberry-curve-9320/product-reviews/ITMD9YDYTBJ6GJF5?pid=MOBD9Y77UJAWNZRH"});
		productsList.add(new String[] {"blackberry-torch-9860","http://www.flipkart.com/blackberry-torch-9860/product-reviews/ITMD2CWRPE6DWWGP?pid=MOBD2CWHJAHGGS2S"});*/
		
		/*for (String[] product : productsList) 
			CSVHelper.writeIntoCSV(	DIRECTORY_PATH + "/Reviews/" + product[0] + CSV_FILE_EXTN, 
					header,	getReviewsFK(product[0], product[1]));*/
		String[] featureHeader = { "Product Id", "Feature Name"};
		productsList.add(new String[] {"Sony-Xperia-Tipo","http://www.flipkart.com/sony-xperia-tipo/p/ITMDCWUDZYJFHPH5?pid=MOBDDB8HYXJDA7GK"});
		productsList.add(new String[] {"Samsung-Galaxy-S2","http://www.flipkart.com/samsung-galaxy-s-2-i9100/p/ITMCZBRRG2PDBVNP?pid=MOBD43T8HNNRUZFQ"});
		productsList.add(new String[] {"Samsung-Galaxy-S3","http://www.flipkart.com/samsung-galaxy-s3/p/ITMDBWUTHFDZ85CC?pid=MOBD9TPHTT8FHJTP"});
		productsList.add(new String[] {"Samsung-Galaxy-Ace","http://www.flipkart.com/samsung-galaxy-ace-s5830i/p/ITMDFNDPGZ4NBUFT?pid=MOBDF83QZMH89ARU"});
		productsList.add(new String[] {"Samsung-Galaxy-Y","http://www.flipkart.com/samsung-galaxy-y-duos-s6102/p/ITMD6VVTRJWBGWGM?pid=MOBD6VVSP8JZRYDN"});
		productsList.add(new String[] {"Nokia-Lumia-620","http://www.flipkart.com/nokia-lumia-620/p/ITMDGKWYWKMAA2W4?pid=MOBDGH6AKH9ERJAF"});
		productsList.add(new String[] {"Nokia-Asha-311","http://www.flipkart.com/nokia-asha-311/p/ITMDBGAYMUHRU8YJ?pid=MOBDCBES8Y3RZUHT"});
		productsList.add(new String[] {"nokia-lumia-510","http://www.flipkart.com/nokia-lumia-510/p/ITMDEUNCGEW8CRGY?pid=MOBDEUNYTUHYPMMT"});
		productsList.add(new String[] {"sony-xperia-j","http://www.flipkart.com/sony-xperia-j/p/ITMDEGBE4QXM9FJY?pid=MOBDEG3ZVPPZMWTT"});
		productsList.add(new String[] {"sony-xperia-u","http://www.flipkart.com/sony-xperia-u/p/ITMD9ANRHVQEFVR5?pid=MOBD9AN7PBMXV3GG"});
		productsList.add(new String[] {"motorola-ex226","http://www.flipkart.com/motorola-ex226/p/ITMD9F9H2GNN94CZ?pid=MOBD9F9EDQGDSGQZ"});
		productsList.add(new String[] {"motorola-atrix-2","http://www.flipkart.com/motorola-atrix-2/p/ITMD7BNAGFD4YWCD?pid=MOBD9EY7CXXGYNGH"});
		productsList.add(new String[] {"htc-one-x","http://www.flipkart.com/htc-one-x/p/ITMD85N2VBZATXTB?pid=MOBD85N2DHCYDAXV"});
		productsList.add(new String[] {"htc-explorer","http://www.flipkart.com/htc-explorer/p/ITMD2KRPPKKEDS2H?pid=MOBD2KRKDNMXGHMJ"});
		productsList.add(new String[] {"htc-8x","http://www.flipkart.com/htc-8x/p/ITMDF2HYYBV6QH2N?pid=MOBDF2DTGFPZXJ3R"});
		productsList.add(new String[] {"htc-wildfire-s","http://www.flipkart.com/htc-wildfire-s/p/ITMCZ2PZ4ZQ7HMZQ?pid=MOBCZ2PZXPEN4Z76"});
		productsList.add(new String[] {"htc-desire-x","http://www.flipkart.com/htc-desire-x/p/ITMDEQDXM2KEWDY2?pid=MOBDE7H2YJTNRMZX"});
		productsList.add(new String[] {"htc-one-v","http://www.flipkart.com/htc-one-v/p/ITMDEQDWGUREMQJA?pid=MOBD85N2VAETHGYR"});
		productsList.add(new String[] {"blackberry-curve-9220","http://www.flipkart.com/blackberry-curve-9220/p/ITMD8354Y3YGDQST?pid=MOBD835YGEGFBGGN"});
		productsList.add(new String[] {"blackberry-curve-9320","http://www.flipkart.com/blackberry-curve-9320/p/ITMD9YDYTBJ6GJF5?pid=MOBD9Y77UJAWNZRH"});
		productsList.add(new String[] {"blackberry-torch-9860","http://www.flipkart.com/blackberry-torch-9860/p/ITMD2CWRPE6DWWGP?pid=MOBD2CWHJAHGGS2S"});
		
		
		//getFeaturesFK("Sony-Xperia-Tipo","http://www.flipkart.com/sony-xperia-tipo/p/itmdcwudzyjfhph5?pid=MOBDDB8HYXJDA7GK");
		for (String[] product : productsList) 
			CSVHelper.writeIntoCSV(	DIRECTORY_PATH + "/ProductFeatures/" + product[0] + CSV_FILE_EXTN, 
					featureHeader,	getFeaturesFK(product[0], product[1]));
		
		//getFeaturesFK("blackberry-torch-9860","http://www.flipkart.com/blackberry-torch-9860/p/ITMD2CWRPE6DWWGP?pid=MOBD2CWHJAHGGS2S");
		
		
		
	}

	public static ArrayList<String[]> getReviewsFK(String productId, String url) {
		ArrayList<String[]> reviews = new ArrayList<String[]>();
		System.out.println("Fetching : " + url);
		int i = 0;
		String productTitle = productId;
		while (true) {
			try {
				String pageUrl;
				pageUrl = url + "&start=" + i;
				//pageUrl = url;
				Document doc = Jsoup.connect(pageUrl).timeout(10000).get();
				if (i == 0) {
					Elements links = doc.select("h1");
					Elements title = links.select("a");
					productTitle = title.text();
					// System.out.println(productTitle);
				}

				Elements allReviews = doc.select("div.fclear.fk-review.fk-position-relative.line");

				if (allReviews.isEmpty()) {
					System.out.println(">>>>>>>>>>>>>>>>>End<<<<<<<<<<<<<<<");
					break;
				}

				for (Element review : allReviews) {
					String[] record = new String[7];
					Elements nameBlock = review.select("div.unit.size1of5.section1");
					// System.out.println(nameBlock.select("a.fk-font-bold.load-user-widget").text());
					Elements ratingBlock = nameBlock.select("div.fk-stars-small");
					//System.out.println(ratingBlock.attr("title"));
					Elements dateBlock = nameBlock.select("div.date.line.fk-font-small");
					//System.out.println(dateBlock.text());
					Elements reviewBlock = review.select("div.lastUnit.size4of5.section2");
					// System.out.println(reviewBlock.select("div.line.fk-font-big.bmargin5").text());
					// System.out.println(reviewBlock.select("p.line.bmargin10").text());
					record[0] = productId;
					record[1] = productTitle;
					record[2] = nameBlock.select("a.fk-font-bold.load-user-widget").text();
					record[3] = reviewBlock.select("div.line.fk-font-big.bmargin5").text();
					record[4] = reviewBlock.select("p.line.bmargin10").text();
					record[5] = ratingBlock.attr("title");
					record[6] = dateBlock.text();
					reviews.add(record);
				}
				i += 10;
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

		}
		return reviews;
	}
	
	public static ArrayList<String[]> getFeaturesFK(String productId, String url) {
		ArrayList<String[]> features = new ArrayList<String[]> ();
		System.out.println("Fetching : " + url);
		try {
		Document doc = Jsoup.connect(url).timeout(10000).get();
		Elements allFeatures = doc.select("td.specs-key");
		
		if (!allFeatures.isEmpty()) {
			for (Element fe : allFeatures) {
				String[] record = new String[2];
				record[0] = productId;
				record[1] = fe.text();
				features.add(record);
			}
		}
			
		} catch (IOException iex) {
			iex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return features;
	}
}
