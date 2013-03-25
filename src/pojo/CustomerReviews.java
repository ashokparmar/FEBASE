package pojo;

import java.io.Serializable;
import java.util.Date;

public class CustomerReviews implements Serializable {
	private Integer reviewId;
	private String productId;
	private String productTitle;
	private String reviewerName;
	private String reviewText;
	private String reviewTitle;
	private Integer ratingValue;
	private Date reviewCreationDate;
	private String status;
	private Date creationDate;
	private Date lastUpdated;
	
	public CustomerReviews() {}
	
	public CustomerReviews(Integer reviewId, String productId, String productTitle,
			String reviewerName,  String reviewText, String reviewTitle, Integer ratingValue,
			Date reviewCreationDate, String status, Date creationDate,
			Date lastUpdated) {
		this.reviewId = reviewId;
		this.productId = productId;
		this.productTitle = productTitle;
		this.reviewerName = reviewerName;
		this.reviewText = reviewText;
		this.reviewTitle = reviewTitle;
		this.ratingValue = ratingValue;
		this.reviewCreationDate = reviewCreationDate;
		this.status = status;
		this.creationDate = creationDate;
		this.lastUpdated = lastUpdated;
	}
	
	public Integer getReviewId() {
		return reviewId;
	}
	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	public String getReviewTitle() {
		return reviewTitle;
	}
	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}
	public Integer getRatingValue() {
		return ratingValue;
	}
	public void setRatingValue(Integer ratingValue) {
		this.ratingValue = ratingValue;
	}
	public Date getReviewCreationDate() {
		return reviewCreationDate;
	}
	public void setReviewCreationDate(Date reviewCreationDate) {
		this.reviewCreationDate = reviewCreationDate;
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

	public String getReviewerName() {
		return reviewerName;
	}

	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}
}
