import java.io.Serializable;

// Jack Sheehy C22468732
// Semester 2 Assignment 2
public class Vinyl extends MusicProducts implements Serializable{

	public String artistName;
	private String albumName;
	private String genre;
	private int albumYear;

	public Vinyl(String productCode, int stockLevel, double productPrice, String artistName, String albumName, String genre, int albumYear) {

		super(productCode, stockLevel, productPrice);
		this.artistName = artistName;
		this.albumName = albumName;
		this.genre = genre;
		this.albumYear = albumYear;
	}

	public String getArtistName() {
		return artistName;
	}

	public String getAlbumName() {
		return albumName;
	}

	public String getGenre() {
		return genre;
	}

	public int getAlbumYear() {
		return albumYear;

	}
	
	public String toString() { // converts event object into a string.
		return 
	
			   "    '" + albumName + "' Vinyl" +
			   "\n ---------------------" +
		       "\n  ARTIST: " + artistName +
		       "\n  GENRE: " + genre +
		       "\n  RELEASED: " + albumYear +
		       "\n  PRICE: €‎ " + String.format("%.2f", productPrice) + // ensures the price only ever has 2 decimal places after it.
		       "\n";		
	}
	
	public String toStringStock() { // converts event object into a string.
		return 
	
			   "    '" + albumName + "' Vinyl" +
			   "\n ---------------------" +
		       "\n  ARTIST: " + artistName +
		       "\n  GENRE: " + genre +
		       "\n  RELEASED: " + albumYear +
		       "\n  PRICE: €‎ " + String.format("%.2f", productPrice) + // ensures the price only ever has 2 decimal places after it.
		       "\n  STOCK: " + stockLevel + "\n";		
	}
	
	
	
}