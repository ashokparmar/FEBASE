package pojo;

import java.util.Date;

public class CustomerReviewSentences {
	private Integer sentenceId;
	private Integer reviewId;
	private String originalSentence;
	private String status;
	private Date creationDate;
	private Date lastUpdated;

	public CustomerReviewSentences () {}
	
	public CustomerReviewSentences (Integer sentenceId,
			Integer reviewId, String originalSentence, 
			String status, Date creationDate, Date lastUpdated) {
		this.sentenceId = sentenceId;
		this.reviewId = reviewId;
		this.originalSentence = originalSentence;
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
}
