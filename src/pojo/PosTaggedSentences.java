package pojo;

import java.util.Date;

public class PosTaggedSentences {
	private Integer taggedSentenceId;
	private String productId;
	private Integer reviewId;
	private String originalSentence;
	private String posTaggedSentence;
	private String nouns;
	private String adjectives;
	private String adverbs;
	private String relatedFeatureName;
	private Integer semanticScore;
	private String status;
	private Date creationDate;
	private Date lastUpdated;
	
	public PosTaggedSentences () {}
	
	public PosTaggedSentences (Integer taggedSentenceId, String productId,
			Integer reviewId, String originalSentence, String posTaggedSentence,
			String nouns, String adjectives, String adverbs,
			Integer semanticScore, String status, 
			Date creationDate, Date lastUpdated) {
		this.taggedSentenceId = taggedSentenceId;
		this.productId = productId;
		this.reviewId = reviewId;
		this.originalSentence = originalSentence;
		this.posTaggedSentence = posTaggedSentence;
		this.nouns = nouns;
		this.adjectives = adjectives;
		this.adverbs = adverbs;
		this.semanticScore = semanticScore;
		this.status = status;
		this.creationDate = creationDate;
		this.lastUpdated = lastUpdated;
	}
	
	public Integer getTaggedSentenceId() {
		return taggedSentenceId;
	}
	public void setTaggedSentenceId(Integer taggedSentenceId) {
		this.taggedSentenceId = taggedSentenceId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getReviewId() {
		return reviewId;
	}
	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}
	public String getOriginalSentence() {
		return originalSentence;
	}
	public void setOriginalSentence(String originalSentence) {
		this.originalSentence = originalSentence;
	}
	public String getPosTaggedSentence() {
		return posTaggedSentence;
	}
	public void setPosTaggedSentence(String posTaggedSentence) {
		this.posTaggedSentence = posTaggedSentence;
	}
	public Integer getSemanticScore() {
		return semanticScore;
	}
	public void setSemanticScore(Integer semanticScore) {
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
