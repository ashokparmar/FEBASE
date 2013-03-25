package helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

public class SentiWordNetHelper {

	private static final String pathToSWN = "swn/SentiWordNet_3.0.0_data.txt";
	private HashMap<String, String> _dict;
	private static HashMap<String, String> posMapping = new HashMap<String, String> ();
	static {
		posMapping.put("JJ", "a");
		posMapping.put("JJR", "a");
		posMapping.put("JJS", "a");
		posMapping.put("NN","n");
		posMapping.put("NNP","n");
		posMapping.put("NNPS","n");
		posMapping.put("NNS","n");
		posMapping.put("RB", "r");
		posMapping.put("RBS", "r");
		posMapping.put("RBR", "r");
	}
	
	public static boolean initialized = false;
	public static HashMap<String, Double> wordToScore = new HashMap<String, Double> ();
	
	public SentiWordNetHelper() {
		_dict = new HashMap<String, String>();
		HashMap<String, Vector<Double>> _temp = new HashMap<String, Vector<Double>>();
		try {
			BufferedReader csv = new BufferedReader(new FileReader(pathToSWN));
			String line = "";
			while ((line = csv.readLine()) != null) {
				try {
					String[] data = line.split("\t");
					if ("#".equals(data[0].substring(0, 1)))
						continue;
					Double score = Double.parseDouble(data[2])
							- Double.parseDouble(data[3]);
					String[] words = data[4].split(" ");
					for (String w : words) {
						String[] w_n = w.split("#");
						w_n[0] += "#" + data[0];
						int index = Integer.parseInt(w_n[1]) - 1;
						if (_temp.containsKey(w_n[0])) {
							Vector<Double> v = _temp.get(w_n[0]);
							if (index > v.size())
								for (int i = v.size(); i < index; i++)
									v.add(0.0);
							v.add(index, score);
							_temp.put(w_n[0], v);
						} else {
							Vector<Double> v = new Vector<Double>();
							for (int i = 0; i < index; i++)
								v.add(0.0);
							v.add(index, score);
							_temp.put(w_n[0], v);
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					continue;
				}
			}
			Set<String> temp = _temp.keySet();
			for (Iterator<String> iterator = temp.iterator(); iterator
					.hasNext();) {
				String word = (String) iterator.next();
				Vector<Double> v = _temp.get(word);
				double score = 0.0;
				double sum = 0.0;
				for (int i = 0; i < v.size(); i++)
					score += ((double) 1 / (double) (i + 1)) * v.get(i);
				for (int i = 1; i <= v.size(); i++)
					sum += (double) 1 / (double) i;
				score /= sum;
				String sent = "";
				if (score >= 0.75)
					sent = "strong_positive";
				else if (score > 0.25 && score <= 0.5)
					sent = "positive";
				else if (score > 0 && score >= 0.25)
					sent = "weak_positive";
				else if (score < 0 && score >= -0.25)
					sent = "weak_negative";
				else if (score < -0.25 && score >= -0.5)
					sent = "negative";
				else if (score <= -0.75)
					sent = "strong_negative";
				_dict.put(word, sent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String extract(String word, String pos) {
		if (posMapping.containsKey(pos))
			return _dict.get(word + "#" + posMapping.get(pos));
		else 
			return null;
	}
	
	public static void init() {
		if (!initialized) {
			HashMap<String, Vector<Double>> _temp = new HashMap<String, Vector<Double>>();
			try {
				BufferedReader csv = new BufferedReader(new FileReader(pathToSWN));
				String line = "";
				while ((line = csv.readLine()) != null) {
					try {
						String[] data = line.split("\t");
						if ("#".equals(data[0].substring(0, 1)))
							continue;
						Double score = Double.parseDouble(data[2])
								- Double.parseDouble(data[3]);
						String[] words = data[4].split(" ");
						for (String w : words) {
							String[] w_n = w.split("#");
							w_n[0] += "#" + data[0];
							int index = Integer.parseInt(w_n[1]) - 1;
							if (_temp.containsKey(w_n[0])) {
								Vector<Double> v = _temp.get(w_n[0]);
								if (index > v.size())
									for (int i = v.size(); i < index; i++)
										v.add(0.0);
								v.add(index, score);
								_temp.put(w_n[0], v);
							} else {
								Vector<Double> v = new Vector<Double>();
								for (int i = 0; i < index; i++)
									v.add(0.0);
								v.add(index, score);
								_temp.put(w_n[0], v);
							}
						}
					} catch (Exception ex) {
						ex.printStackTrace();
						continue;
					}
				}
				Set<String> temp = _temp.keySet();
				for (Iterator<String> iterator = temp.iterator(); iterator
						.hasNext();) {
					String word = (String) iterator.next();
					Vector<Double> v = _temp.get(word);
					double score = 0.0;
					double sum = 0.0;
					for (int i = 0; i < v.size(); i++)
						score += ((double) 1 / (double) (i + 1)) * v.get(i);
					for (int i = 1; i <= v.size(); i++)
						sum += (double) 1 / (double) i;
					score /= sum;
					
					wordToScore.put(word, score);
				}
				initialized = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String scoreToString (Double score) {
		String sent = "";
		if (score >= 0.75)
			sent = "strong_positive";
		else if (score > 0.25 && score <= 0.5)
			sent = "positive";
		else if (score > 0 && score >= 0.25)
			sent = "weak_positive";
		else if (score < 0 && score >= -0.25)
			sent = "weak_negative";
		else if (score < -0.25 && score >= -0.5)
			sent = "negative";
		else if (score <= -0.75)
			sent = "strong_negative";
		return sent;
	}
	
	public static Double getSemanticScore(String word, String pos) {
		init();
		if (posMapping.containsKey(pos))
			return wordToScore.containsKey(word + "#" + posMapping.get(pos)) ? wordToScore.get(word + "#" + posMapping.get(pos)) : 0.0;
		else 
			return 0.0;
	}
}
