package tuat.crocotitle.save.models;

import java.util.ArrayList;

/*
 * Author	: Le Vinh - Vietnam
 * Time	 	: 20/6/2013
 * Name		: AutoCompleteArray Class
 * Function	: Model Array for data source of AutoComplete View
 * Others	: This class is used with file AutoCompleteStore.json
 */

public class AutoCompleteList {
	private ArrayList<AutoCompleteObject> dataList;

	/* +++++++++++++++ Methods Constructors +++++++++++++++ */
	public AutoCompleteList() {
		super();
		this.dataList = new ArrayList<AutoCompleteObject>();
	}
	
	/* +++++++++++++++ Methods Controller +++++++++++++++ */
	// ----- List
	public ArrayList<AutoCompleteObject> getList() {
		return dataList;
	}
	public void setList(ArrayList<AutoCompleteObject> dataList) {
		this.dataList = dataList;
	}
	
	// ----- Object: get, add, remove, update, clear
	public AutoCompleteObject getObject(int id)
	{
		int index = ConvertIdToIndex(id);
		return dataList.get(index);
	}
	
	public void addObject(AutoCompleteObject autoObject)
	{
		dataList.add(autoObject);
	}
	
	public void removeObject(AutoCompleteObject autoObject)
	{
		dataList.remove(autoObject);
	}
	
	public void updateObject(int id, String fileName)
	{
		int index = ConvertIdToIndex(id);
		AutoCompleteObject tmp = dataList.get(index);
		tmp.setFileName(fileName);
		dataList.set(index, tmp);
	}
	
	public void clearObject()
	{
		dataList.clear();
	}
	
	/* +++++++++++++++ Methods Utilities +++++++++++++++ */
	public int ConvertIdToIndex(int id) // Use binary search de co the chuyen tu id sang index cua doi tuong
	{
		int mid, low, high;
		low = 0; high = dataList.size();
		while(low <= high)
		{
			mid = (low + high)/2;
			int objId = dataList.get(mid).getId();
		    if(objId == id) return mid;
		    	else if(id < objId) high = mid - 1;
		           else low = mid + 1;
		 }
		return -1; // Error Out of index
	}
	public String test() {
		String tmp = "";
		if(dataList != null)
		{
			for (int i=0; i < dataList.size(); i++)
			{
				   AutoCompleteObject item = dataList.get(i);
				   tmp += Integer.toString(item.getId()) + item.getFileName();
			}
			return  tmp + "so ph/t:" + Integer.toString(dataList.size());
		}
		return "null";
	}
}
