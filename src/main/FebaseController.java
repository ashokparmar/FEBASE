package main;
import helper.DBHelper;
import helper.SentiWordNetHelper;
import helper.WordNetHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import pojo.CustomerReviewSentences;
import pojo.CustomerReviews;
import pojo.PosTaggedSentences;
import pojo.ProductFeatures;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class FebaseController {
	
	public static final String NOUN_TAG = "NN";
	public static final String ADJECTIVE_TAG = "JJ";
	public static final String ADVERB_TAG = "RB";
	public static final String VERB_TAG = "VB";
	
	public static void main(String[] args) throws Exception  {
		long start = Calendar.getInstance().getTimeInMillis();
		System.out.println("Starting FebaseController ...");
		//fetchSimilarWords("Sony-Xperia-Tipo");
		//storeCustomerReviewSentences("Sony-Xperia-Tipo");
		//applyPOSTag("Sony-Xperia-Tipo");
		//findRelatedFeatures("Sony-Xperia-Tipo");
		
		//fetchSimilarWords("sony-xperia-u");
		//storeCustomerReviewSentences("sony-xperia-u");
		//applyPOSTag("sony-xperia-u");
		//findRelatedFeatures("sony-xperia-u");
		
		fetchSimilarWords("blackberry-curve-9220");
		storeCustomerReviewSentences("blackberry-curve-9220");
		applyPOSTag("blackberry-curve-9220");
		findRelatedFeatures("blackberry-curve-9220");
		System.out.println("FebaseController end ... done in " + ((Calendar.getInstance().getTimeInMillis() - start) / 1000) + " secs");
		
		

	}
	
	public static void fetchSimilarWords(String productId) {
		System.out.println("In fetchSimilarWords ....");
		long dbStart = Calendar.getInstance().getTimeInMillis();
		List<ProductFeatures> productFeatures = DBHelper.getProductFeatures(productId);
		System.out.println("DB call completed in " + ((Calendar.getInstance().getTimeInMillis() - dbStart) / 1000) + " secs");
		if (productFeatures != null && productFeatures.size() > 0) {
			System.out.println("Total product features : " + productFeatures.size());
			long wnStart = Calendar.getInstance().getTimeInMillis();
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
			System.out.println("Completed similar word storing ... done in " + ((Calendar.getInstance().getTimeInMillis() - wnStart) / 1000) + " secs");
		}
	}
	
	public static void storeCustomerReviewSentences(String productId) {
		System.out.println("In storeCustomerReviewSentences ....");
		long dbStart = Calendar.getInstance().getTimeInMillis();
		List<CustomerReviews> reviews = DBHelper.getCustomerReviews(productId);
		System.out.println("DB call completed in " + ((Calendar.getInstance().getTimeInMillis() - dbStart) / 1000) + " secs");
		if (reviews != null && reviews.size() > 0) {
			System.out.println("Total reviews : " + reviews.size());
			Properties props = new Properties();
			props.put("annotators",	"tokenize, ssplit");
			StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
			long wnStart = Calendar.getInstance().getTimeInMillis();
			for (CustomerReviews review : reviews) {
				String reviewText = review.getReviewText();
				Annotation document = new Annotation(reviewText);
				pipeline.annotate(document);
				List<CoreMap> sentences = document.get(SentencesAnnotation.class);
				for (CoreMap sentence : sentences) {
					CustomerReviewSentences sentenceObj = new CustomerReviewSentences();
					sentenceObj.setReviewId(review.getReviewId());
					sentenceObj.setOriginalSentence(sentence.toString());
					sentenceObj.setStatus("Active");
					sentenceObj.setCreationDate(Calendar.getInstance().getTime());
					sentenceObj.setLastUpdated(Calendar.getInstance().getTime());
					DBHelper.saveCustomerReviewSentences(sentenceObj);
				}
			}
			System.out.println("storeCustomerReviewSentences end ... done in " + ((Calendar.getInstance().getTimeInMillis() - wnStart) / 1000) + " secs");
		}
	}
	
	public static void applyPOSTag(String productId) {
		System.out.println("In applyPOSTag ....");
		long dbStart = Calendar.getInstance().getTimeInMillis();
		List<CustomerReviewSentences> reviewSentences = DBHelper.getCustomerReviewSentences(productId);
		System.out.println("DB call completed in " + ((Calendar.getInstance().getTimeInMillis() - dbStart) / 1000) + " secs");
		if (reviewSentences != null && reviewSentences.size() > 0) {
			System.out.println("Total review sentences : " + reviewSentences.size());
			Properties props = new Properties();
			props.put("annotators",	"tokenize, ssplit, pos");
			StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
			long wnStart = Calendar.getInstance().getTimeInMillis();
			ArrayList<PosTaggedSentences> posSentences = new ArrayList<PosTaggedSentences> ();
			for (CustomerReviewSentences reviewSentence : reviewSentences) {
				long loopStart = Calendar.getInstance().getTimeInMillis();
				String reviewSentenceText = reviewSentence.getOriginalSentence();
				Annotation document = new Annotation(reviewSentenceText);
				pipeline.annotate(document);				
				//List<CoreMap> annotatedSentence = document.get(SentencesAnnotation.class);
				CoreMap sentence = document.get(SentencesAnnotation.class).get(0);	
				StringBuilder posTaggedSentence = new StringBuilder();
				
				ArrayList<String> nounList = new ArrayList();
				ArrayList<String> adjectiveList = new ArrayList();
				ArrayList<String> adverbList = new ArrayList();
				Double semanticScore = 0.0;
				
				for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
					// this is the text of the token
					String word = token.get(TextAnnotation.class);
					// this is the POS tag of the token
					String pos = token.get(PartOfSpeechAnnotation.class);
					posTaggedSentence.append(word+"#"+pos+" ");
					if (pos.startsWith(NOUN_TAG) && !nounList.contains(word)) {
						nounList.add(word);
					} else if (pos.startsWith(ADJECTIVE_TAG) && !adjectiveList.contains(word)) {
						adjectiveList.add(word);
					} else if (pos.startsWith(ADVERB_TAG) && !adverbList.contains(word)) {
						adverbList.add(word);
					}
					semanticScore += SentiWordNetHelper.getSemanticScore(word, pos);						
				}
				System.out.println("Pos tagging done for sentenceId " + reviewSentence.getSentenceId() + " in " + (Calendar.getInstance().getTimeInMillis() - loopStart) + " ms");
				PosTaggedSentences posTagObj = new PosTaggedSentences();
				posTagObj.setSentenceId(reviewSentence.getSentenceId());
				posTagObj.setPosTaggedSentence(posTaggedSentence.toString());
				posTagObj.setNouns(nounList.size() > 0 ? Arrays.toString(nounList.toArray()).replace("[", "").replace("]", "").replace(" ", "") : "");
				posTagObj.setAdjectives(adjectiveList.size() > 0 ? Arrays.toString(adjectiveList.toArray()).replace("[", "").replace("]", "").replace(" ", "") : "");
				posTagObj.setAdverbs(adverbList.size() > 0 ? Arrays.toString(adverbList.toArray()).replace("[", "").replace("]", "").replace(" ", "") : "");
				posTagObj.setSemanticScore(semanticScore);
				posTagObj.setStatus("Active");
				posTagObj.setCreationDate(Calendar.getInstance().getTime());
				posTagObj.setLastUpdated(Calendar.getInstance().getTime());
				posSentences.add(posTagObj);
				//long dbStore = Calendar.getInstance().getTimeInMillis();
				//DBHelper.savePosTaggedSentences(posTagObj);
				//System.out.println("Pos tagged sentece stored in db.. done in " + (Calendar.getInstance().getTimeInMillis() - dbStore) + " ms");
			}
			long dbStore = Calendar.getInstance().getTimeInMillis();
			DBHelper.savePosTaggedSentencesList(posSentences);
			System.out.println("Pos tagged sentece stored in db.. done in " + (Calendar.getInstance().getTimeInMillis() - dbStore) + " ms");
			System.out.println("applyPOSTag end ... done in " + ((Calendar.getInstance().getTimeInMillis() - wnStart) / 1000) + " secs");
		}
	}
	
	public static void findRelatedFeatures(String productId) {
		System.out.println("In findRelatedFeatures ....");
		long dbStart = Calendar.getInstance().getTimeInMillis();
		List<ProductFeatures> features = DBHelper.getProductFeatures(productId);
		System.out.println("DB call for features completed in " + ((Calendar.getInstance().getTimeInMillis() - dbStart) / 1000) + " secs");
		dbStart = Calendar.getInstance().getTimeInMillis();
		List<PosTaggedSentences> posTagged = DBHelper.getPosTaggedSentences(productId);
		System.out.println("DB call for review sentences completed in " + ((Calendar.getInstance().getTimeInMillis() - dbStart) / 1000) + " secs");
		if (features != null && features.size() > 0 
				&& posTagged != null && posTagged.size() > 0) {
			try {
				long wnStart = Calendar.getInstance().getTimeInMillis();
				for (PosTaggedSentences taggedSentence : posTagged) {
					String matchedFeature = null;
					if (taggedSentence.getNouns() != null && !"".equals(taggedSentence.getNouns())) {
						String[] nounList = taggedSentence.getNouns().split(",");
						if (nounList.length > 0) {
						nounLoop: for (String noun : nounList) {
							for (ProductFeatures feature : features) {
								if (noun.equalsIgnoreCase(feature.getProductFeaturesPK().getFeatureName())) {
									matchedFeature = feature.getProductFeaturesPK().getFeatureName();
									break nounLoop;
								} else {
									if (feature.getSimilarWords() != null && !"".equals(feature.getSimilarWords())) {
										String[] similarWordList = feature.getSimilarWords().split("#");
										if (similarWordList.length > 0) {
											for (String similarWord : similarWordList) {
												if (noun.equalsIgnoreCase(similarWord)) {
													matchedFeature = feature.getProductFeaturesPK().getFeatureName();
													break nounLoop;
												}
											}
										}
									}	
								}
							}
							}
						}	
					}
					if (matchedFeature != null && !"".equals(matchedFeature)) {
						taggedSentence.setRelatedFeatureName(matchedFeature);
						DBHelper.savePosTaggedSentences(taggedSentence);
					}
				}
				System.out.println("findRelatedFeatures end ... done in " + ((Calendar.getInstance().getTimeInMillis() - wnStart) / 1000) + " secs");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}	
}
