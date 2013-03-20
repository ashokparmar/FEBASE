package pojo;

import java.io.Serializable;

public class ProductFeaturesPK implements Serializable {
	private String productId;
	private String featureName;
	
	public ProductFeaturesPK () {}
	
	public ProductFeaturesPK (String productId, String featureName) {
		this.productId = productId;
		this.featureName = featureName;
	}
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getFeatureName() {
		return featureName;
	}
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
}
