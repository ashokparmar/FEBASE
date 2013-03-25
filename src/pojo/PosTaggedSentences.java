package pojo;

import java.util.Date;

public class PosTaggedSentences {
	private Integer sentenceId;
	private String posTaggedSentence;
	private String nouns;
	private String adjectives;
	private String adverbs;
	private String relatedFeatureName;
	private Double semanticScore;
	private String status;
	private Date creationDate;
	private Date lastUpdated;
	
	public PosTaggedSentences () {}
	
	public PosTaggedSentences (Integer sentenceId, 
			String posTaggedSentence, String nouns, 
			String adjectives, String adverbs,
			Double semanticScore, String status, 
			Date creationDate, Date lastUpdated) {
		this.sentenceId = sentenceId;
		this.posTaggedSentence = posTaggedSentence;
		this.nouns = nouns;
		this.adjectives = adjectives;
		this.adverbs = adverbs;
		this.semanticScore = semanticScore;
		this.status = status;
		this.creationDate = creationDate;
		this.lastUpdated = lastUpdated;
	}
	
	public Integer getSentenceId() {
		return sentenceId;
	}
	public void setSentenceId(Integer sentenceId) {
		this.sentenceId = sentenceId;
	}
	public String getPosTaggedSentence() {
		return posTaggedSentence;
	}
	public void setPosTaggedSentence(String posTaggedSentence) {
		this.posTaggedSentence = posTaggedSentence;
	}
	public Double getSemanticScore() {
		return semanticScore;
	}
	public void setSemanticScore(Double semanticScore) {
		this.semanticScore = semanticScore;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getRelatedFeatureName() {
		return relatedFeatureName;
	}

	public void setRelatedFeatureName(String relatedFeatureName) {
		this.relatedFeatureName = relatedFeatureName;
	}

	public String getNouns() {
		return nouns;
	}

	public void setNouns(String nouns) {
		this.nouns = nouns;
	}

	public String getAdjectives() {
		return adjectives;
	}

	public void setAdjectives(String adjectives) {
		this.adjectives = adjectives;
	}

	public String getAdverbs() {
		return adverbs;
	}

	public void setAdverbs(String adverbs) {
		this.adverbs = adverbs;
	}
}
