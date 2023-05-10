import java.io.Serializable;

// Jack Sheehy C22468732
// Semester 2 Assignment 2
public class MusicProducts implements Serializable{

	String productCode;
	double productPrice;
	int stockLevel;

	public MusicProducts(String productCode, int stockLevel, double productPrice) {

		this.productCode = productCode;
		this.productPrice = productPrice;
		this.stockLevel = stockLevel;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductName(String productCode) {
		this.productCode = productCode;
	}

	public int getStockLevel() {	
		return stockLevel;
	}

	public void setStockLevel(int stockLevel) {
		this.stockLevel = stockLevel;
	}
	
	public double getPrice() {
		return productPrice;
	}
	
	public void setPrice (int productPrice) {
		this.productPrice = productPrice;
	}

}
