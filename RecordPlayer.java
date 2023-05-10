 

import java.io.Serializable;

// Jack Sheehy C22468732
// Semester 2 Assignment 2
public class RecordPlayer extends MusicProducts implements Serializable{

	private String brand;
	private String model;
	private String colour;
	public int toString;
	

	public RecordPlayer(String productCode, int stockLevel, double productPrice, String brand, String model, String colour) {
		super(productCode, stockLevel, productPrice);

		this.brand = brand;
		this.model = model;
		this.colour = colour;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}
	
	public String getColour() {
		return colour;
	}
  

   public String toString() { // converts event object into a string.
	return 

		   "    '" + brand + " " + model + "' Record Player" +
		   "\n ---------------------" +
	       "\n  BRAND: " + brand +
	       "\n  MODEL: " + model + 
	       "\n  COLOUR: " + colour + 
	       "\n  PRICE: €‎ " + String.format("%.2f", productPrice) + // ensures the price only ever has 2 decimal places after it.
	       "\n";		
}
   
   public String toStringStock() { // converts event object into a string.
		return 

			   "    '" + brand + " " + model + "' Record Player" +
			   "\n ---------------------" +
		       "\n  BRAND: " + brand +
		       "\n  MODEL: " + model + 
		       "\n  COLOUR: " + colour + 
		       "\n  PRICE: €‎ " + String.format("%.2f", productPrice) + // ensures the price only ever has 2 decimal places after it.
		       "\n  STOCK: " + stockLevel +"\n";		
	}
   }
