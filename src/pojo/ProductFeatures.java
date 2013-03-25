package pojo;

import java.util.Date;
import pojo.ProductFeaturesPK;

public class ProductFeatures {
	
	private ProductFeaturesPK productFeaturesPK;
	private String similarWords;
	private Integer mentionedInReviews;
	private Integer positiveInReviews;
	private Integer negativeInReviews;
	private Integer neutralInReviews;
	private String status;
	private Date creationDate;
	private Date lastUpdated;
	
	public ProductFeatures () {}
	
	public ProductFeatures (ProductFeaturesPK productFeaturesPK,
			String productTitle, String similarWords,
			Integer mentionedInReviews, Integer positiveInReviews,
			Integer negativeInReviews, Integer neutralInReviews,
			String status, Date creationDate, Date lastUpdated) {
		this.productFeaturesPK = productFeaturesPK;
		this.similarWords = similarWords;
		this.mentionedInReviews = mentionedInReviews;
		this.positiveInReviews = positiveInReviews;
		this.negativeInReviews = negativeInReviews;
		this.neutralInReviews = neutralInReviews;
		this.status = status;
		this.creationDate = creationDate;
		this.lastUpdated = lastUpdated;
	}
	
	public String getSimilarWords() {
		return similarWords;
	}
	public void setSimilarWords(String similarWords) {
		this.similarWords = similarWords;
	}
	public Integer getMentionedInReviews() {
		return mentionedInReviews;
	}
	public void setMentionedInReviews(Integer mentionedInReviews) {
		this.mentionedInReviews = mentionedInReviews;
	}
	public Integer getPositiveInReviews() {
		return positiveInReviews;
	}
	public void setPositiveInReviews(Integer positiveInReviews) {
		this.positiveInReviews = positiveInReviews;
	}
	public Integer getNegativeInReviews() {
		return negativeInReviews;
	}
	public void setNegativeInReviews(Integer negativeInReviews) {
		this.negativeInReviews = negativeInReviews;
	}
	public Integer getNeutralInReviews() {
		return neutralInReviews;
	}
	public void setNeutralInReviews(Integer neutralInReviews) {
		this.neutralInReviews = neutralInReviews;
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

	public ProductFeaturesPK getProductFeaturesPK() {
		return productFeaturesPK;
	}

	public void setProductFeaturesPK(ProductFeaturesPK productFeaturesPK) {
		this.productFeaturesPK = productFeaturesPK;
	}
	
	
	
}
