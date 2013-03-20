package main;
import helper.DBHelper;
import helper.WordNetHelper;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import pojo.CustomerReviews;
import pojo.ProductFeatures;
import edu.stanford.nlp.dcoref.CorefChain;
import edu.stanford.nlp.dcoref.CorefCoreAnnotations.CorefChainAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.trees.semgraph.SemanticGraph;
import edu.stanford.nlp.trees.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation;
import edu.stanford.nlp.util.CoreMap;

public class FebaseController {
	public static void main(String[] args) throws Exception  {
		DBHelper.init();
		//fetchSimilarWords("Sony-Xperia-Tipo");
		//applyPOSTagging("Sony-Xperia-Tipo");
		applyPOSTaggingUsingStanFordCoreNlp("Sony-Xperia-Tipo");
	}
	
	public static void fetchSimilarWords(String productId) {
		List<ProductFeatures> productFeatures = DBHelper.getProductFeatures(productId);
		if (productFeatures != null && productFeatures.size() > 0) {
			for (ProductFeatures record : productFeatures) {
				String feature = record.getProductFeaturesPK().getFeatureName();
				List<String> similarWordList = WordNetHelper.getSynonyms(feature);
				if (similarWordList != null && similarWordList.size() > 0) {
					StringBuilder similarWords = new StringBuilder();
					for (String sWord : similarWordList) {
						if (feature != null && !feature.equalsIgnoreCase(sWord))
							similarWords.append(sWord +"#");
					}
					if (similarWords.length() > 0) {
						record.setSimilarWords(similarWords.substring(0, similarWords.length()-1).toString());
						DBHelper.storeProductFeatures(record);
					}
				}
			}
		}
	}
	
	public static void applyPOSTagging(String productId) {
		List<CustomerReviews> reviews = DBHelper.getCustomerReviews(productId);
		if (reviews != null && reviews.size() > 0) {
			for (CustomerReviews review : reviews) {
				String reviewText = review.getReviewText();
				if (reviewText != null) {
					String[] sentences = reviewText.split(".");
					System.out.println("Product Id : " + productId + ", Review Id : " + review.getReviewId());
					for (String sentence : sentences) {
						System.out.println(sentence);
					}
					System.out.println("End of review");
				}
			}
		}
	}
	
	public static void applyPOSTaggingUsingStanFordCoreNlp(String productId) {
		List<CustomerReviews> reviews = DBHelper.getCustomerReviews(productId);
		if (reviews != null && reviews.size() > 0) {
			// creates a StanfordCoreNLP object, with POS tagging,
			// lemmatization, NER, parsing, and coreference resolution
			Properties props = new Properties();
			props.put("annotators",
					"tokenize, ssplit, pos, lemma, ner, parse, dcoref");
			StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
			for (CustomerReviews review : reviews) {
				String reviewText = review.getReviewText();
				// create an empty Annotation
				Annotation document = new Annotation(reviewText);
				// run all Annotators on this text
				pipeline.annotate(document);

				// these are all the sentences in this document
				// a CoreMap is essentially a Map that uses class objects as
				// keys and has values with custom types
				List<CoreMap> sentences = document.get(SentencesAnnotation.class);

				for (CoreMap sentence : sentences) {
					System.out.println("Sentence : " + sentence.toString());					
					// traversing the words in the current sentence
					// a CoreLabel is a CoreMap with additional token-specific
					// methods
					for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
						// this is the text of the token
						String word = token.get(TextAnnotation.class);
						System.out.println("Word : " + word + "#");
						// this is the POS tag of the token
						String pos = token.get(PartOfSpeechAnnotation.class);
						System.out.println("POS Tag : " + pos + "#");
						// this is the NER label of the token
						String ne = token.get(NamedEntityTagAnnotation.class);
						System.out.println("NamedEntity : " + ne);
					}

					// this is the parse tree of the current sentence
					Tree tree = sentence.get(TreeAnnotation.class);
					System.out.println("Tree : " + tree);
					// this is the Stanford dependency graph of the current
					// sentence
					SemanticGraph dependencies = sentence
							.get(CollapsedCCProcessedDependenciesAnnotation.class);
					System.out.println("Dependencies : " + dependencies);
				}
				// This is the coreference link graph
				// Each chain stores a set of mentions that link to each other,
				// along with a method for getting the most representative
				// mention
				// Both sentence and token offsets start at 1!
				Map<Integer, CorefChain> graph = document
						.get(CorefChainAnnotation.class);
				System.out.println("Graph : " + graph);
			}
		}
	}
}
