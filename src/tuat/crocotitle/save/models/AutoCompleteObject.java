package tuat.crocotitle.save.models;

/*
 * Author	: Le Vinh - Vietnam
 * Time	 	: 20/6/2013
 * Name		: AutoCompleteModel Class
 * Function	: Model Object for data source of AutoComplete View
 * Others	: This class is used with file AutoCompleteStore.json
 */

public class AutoCompleteObject {
	
	/* +++++++++++++++ Attributes +++++++++++++++ */
	private int    id;
	private String fileName = null;
	
	/* +++++++++++++++ Methods Constructors +++++++++++++++ */
	public AutoCompleteObject(int id, String fileName)
	{
		this.id 	  = id;
		this.fileName = fileName;
	}
	
	/* +++++++++++++++ Methods Controller +++++++++++++++ */
	// Get & Set method
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getFileName()
	{
		return fileName;
	}
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.fileName;
	}
	


}
