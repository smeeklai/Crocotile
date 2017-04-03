package tuat.crocotitle.save;

import com.example.testproject01.R;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import tuat.crocotitle.save.models.AutoCompleteObject;
import tuat.crocotitle.save.models.DataSource;
import tuat.crocotitle.save.models.DateUtility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

/*
 * Author	: Le Vinh - Vietnam
 * Time	 	: 20/6/2013
 * Name		: SaveInputActivity Class (activity)
 * Function	: Save program to file json and database
 * Others	: Process with View, Event, 
 */

public class SaveInputActivity extends Activity {
	// Attributes from Program Activity
	String _json;
	
	/* +++++++++++++++ Attributes +++++++++++++++ */
	// Attributes about GUI
	private Button 	  saveInputCancel_btn, saveInputRefresh_btn, saveInputSave_btn;
	private EditText  saveInputName_edt;
	private RatingBar saveInput_ratingBar;
	private TextView  saveInputError_txt;
	
	// Attributes about Event Handler
	private OnClickListener onClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(v == saveInputCancel_btn) finish();
			else 
				if(v == saveInputRefresh_btn)
				{
					saveInputName_edt.setText("");
					saveInput_ratingBar.setRating(0);
				}
				else
					if(v == saveInputSave_btn) 
						{
							if(checkUserInput() == true) saveProgram();
							else
							{
								saveInputName_edt.setText("");
								saveInput_ratingBar.setRating(0);
							}
						}
		}
	};
	
	// Attributes about Data Model: json-auto, XML-program and SQLite-listview
	private AutoCompleteObject autoObject;	
	
	/* +++++++++++++++ Methods Constructors +++++++++++++++ */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.save_input);

		// Get intent that call this Activity
		Intent callerIntent 	 = getIntent();
		Bundle packageFromCaller = callerIntent.getBundleExtra("MyJson");
		_json = packageFromCaller.getString("json");
		initialView();
	}

	/* +++++++++++++++ Methods Controller +++++++++++++++ */
	public void initialView()
	{
		saveInputCancel_btn  = (Button)findViewById(R.id.saveInputCancel_btn);
		saveInputRefresh_btn = (Button)findViewById(R.id.saveInputRefresh_btn);
		saveInputSave_btn 	 = (Button)findViewById(R.id.saveInputSave_btn);
		saveInputName_edt	 = (EditText)findViewById(R.id.saveInputName_edt);
		saveInput_ratingBar  = (RatingBar)findViewById(R.id.saveInput_ratingBar);
		saveInputError_txt	 = (TextView)findViewById(R.id.saveInputError_txt);
		//SsaveInputError_txt.setText(_json);
		
		saveInputCancel_btn.setOnClickListener(onClick);
		saveInputRefresh_btn.setOnClickListener(onClick);
		saveInputSave_btn.setOnClickListener(onClick);
	}
	
	public boolean checkUserInput()
	{
		String tmp = saveInputName_edt.getText().toString();
		//tmp = tmp.trim();
		// Kiem tra xem file da co chua
		if(DataSource.checkExistFile(tmp) == true)
		{
			saveInputError_txt.setText("Error: File have existed, please choose other name");
			return false;
		}
		//Check special characters
		if(tmp.contains("{") || tmp.contains("}") || tmp.contains("[") || tmp.contains("]"))
		{
			saveInputError_txt.setText("Error: File name mustn't contain \" , : {} []");
			return false;
		}
		if(tmp.contains("\"") || tmp.contains(",") || tmp.contains(":"))
		{
			saveInputError_txt.setText("Error: File name mustn't contain \" , : {} []");
			return false;
		}
		// Have to input file name
		if (tmp.equals("")) {
			saveInputError_txt.setText("Error: You have to input file name");
			return false;
		}
		// Check the length of file name
		if(tmp.length() > 30) {
			saveInputError_txt.setText("Error: File name is too long, the length < 30 char");
			return false;
		}
		return true;
	}
	
	/* +++++++++++++++ Methods Save +++++++++++++++ */
	public void saveProgram()
	{
		if(checkUserInput() == true)
		{
			String fileName = saveInputName_edt.getText().toString();
			int    rating	= (int)saveInput_ratingBar.getRating();
			String date		= DateUtility.now("yyyy-MM-dd HH:mm:ss");			
			
			int id; // Do id trong SQLite tang tu dong ko trung nen lay no lam id chuan
			id = saveProgramToSQLite(fileName, rating, date, _json);
			
			saveProgramToJSON1(id, fileName);			
			finish();
		}
	}
	
	public void saveProgramToJSON1(int id, String fileName) // Save into file json for AutoComplete function
	{
		Gson gson;
		String result;
		
		autoObject = new AutoCompleteObject(id, fileName);		
		DataSource.srcSearchList.addObject(autoObject);
		gson 	= new GsonBuilder().create();
		result 	= gson.toJson(DataSource.srcSearchList);
		
		//saveInputError_txt.setText(DateUtility.now("yyyy-MM-dd HH:mm:ss"));
		//saveInputError_txt.setText(result);
		try {
			FileOutputStream   out 	  = openFileOutput("AutoCompleteList.json", Context.MODE_PRIVATE);
			OutputStreamWriter writer = new OutputStreamWriter(out);
			writer.write(result);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	// Save into file json for Sort & ListView Function
	public int saveProgramToSQLite(String fileName, int rating, String date, String program)
	{		
		return DataSource.dataFile.insertRecord(fileName, rating, date, program);
	}	
}
