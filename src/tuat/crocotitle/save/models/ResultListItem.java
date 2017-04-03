package tuat.crocotitle.save.models;

/*
 * Author	: Le Vinh - Vietnam
 * Time	 	: 25/6/2013
 * Name		: ResultListItem Class (activity)
 * Function	: Item Model
 * Others	: 
 */
public class ResultListItem {
	private int id;
	private String name;
	private int rating;	
	private String date;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}	

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return Integer.toString(this.rating) + "-" + this.name;
	}

}
