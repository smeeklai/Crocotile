package tuat.crocotitle.save.models;

import java.util.ArrayList;

import tuat.crocotitle.program.models.TileData;

import android.app.Activity;

/*
 * Author	: Le Vinh - Vietnam
 * Time	 	: 25/6/2013
 * Name		: DataSource Class
 * Function	: Global variable for project
 * Others	: Static, and globale
 */
public class DataSource extends Activity{
	
	// Datasource for AutoCompleteTextView
	public static AutoCompleteList srcSearchList = new AutoCompleteList();
	public static boolean checkExistFile(String fileName)
	{
		ArrayList<AutoCompleteObject> srcSearch = DataSource.srcSearchList.getList();
		for (int i = 0; i < srcSearch.size(); i++)
		{
			   AutoCompleteObject item = srcSearch.get(i);
			   if(fileName.equals(item.getFileName())) return true; // da ton tai file cung ten
		}
		return false;
	}
	
	// Datasource for Result ListView
	public static DBAdapter dataFile;
	
	// Datasource for Content Program
	public static TileData myProgram;
}
