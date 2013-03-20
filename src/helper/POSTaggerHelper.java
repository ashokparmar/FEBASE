package helper;
import java.io.IOException;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class POSTaggerHelper {
	public static void main(String[] args) throws IOException, ClassNotFoundException {

        // Initialize the tagger
        MaxentTagger tagger = new MaxentTagger(
                "taggers/wsj-0-18-left3words.tagger");
 
        // The sample string
        String sample = "My dog eats quickly. When he is very hungry, he eats really quickly.";
 
        // The tagged string
        String tagged = tagger.tagString(sample);

        // Output the result
        System.out.println(tagged);
	}
}
