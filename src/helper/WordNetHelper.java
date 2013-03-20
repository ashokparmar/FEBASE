package helper;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;

public class WordNetHelper {

	public static String WNHOME = "/home/ashok/Desktop/FeaturedBasedSentimentAnalysis" +
			"/dependency/wordnet/WordNet-3.0/dict";
	public static void main(String[] args) {
		try {
			testDictionary("picture");
			getSynonyms("picture");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public static void init() {
		
	}
	
	public static void testDictionary (String wordStr) throws IOException {
		// construct the URL to the Wordnet dictionary directory
		URL url = new URL ("file", null, WNHOME) ;
		// construct the dictionary object and open it
		IDictionary dict = new Dictionary(url);
		dict.open () ;
		// look up first sense of the word " dog "
		IIndexWord idxWord = dict.getIndexWord(wordStr, POS.NOUN) ;
		IWordID wordID = idxWord.getWordIDs().get(0);
		IWord word = dict. getWord (wordID) ;
		System.out.println("Id = " + wordID) ;
		System.out.println("Lemma = " + word.getLemma()) ;
		System.out.println("Gloss = " + word.getSynset().getGloss ()) ;
	}
	
	public static ArrayList<String> getSynonyms(String wordStr) {
		if (wordStr == null || "".equals(wordStr))
			return null;
		ArrayList<String> synonyms = new ArrayList<String>();
		//StringBuilder synonyms = new StringBuilder();
		try {
			// construct the URL to the Wordnet dictionary directory
			URL url = new URL ("file", null, WNHOME) ;
			
			// construct the dictionary object and open it
			IDictionary dict = new Dictionary(url);
			dict.open () ;
			
			// look up first sense of the word
			IIndexWord idxWord = dict.getIndexWord(wordStr, POS.NOUN);
			IWordID wordID = idxWord.getWordIDs().get(0); // 1 st meaning
			IWord word = dict.getWord(wordID);
			ISynset synset = word.getSynset();
			
			// iterate over words associated with the synset
			for(IWord w : synset.getWords())
				synonyms.add(w.getLemma().replace("_", " "));
			
		} catch (IOException io) {
			io.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return synonyms;
	}
}
