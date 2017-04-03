package tuat.crocotitle.save;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import tuat.crocotitle.save.models.DataSource;

import com.example.testproject01.R;
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
 * Time	 	: 10/7/2013
 * Name		: UpdateActivity Class (activity)
 * Function	: Update program to file json and database
 * Others	: Process with View, Event, 
 */

public class UpdateActivity extends Activity {
	// Attributes from SavePage Activity
	String _fileName;
	int _id, _rating;
	/* +++++++++++++++ Attributes +++++++++++++++ */
	// Attributes about GUI
	private Button 	  updateCancel_btn, updateSave_btn;
	private EditText  updateName_edt;
	private RatingBar update_ratingBar;
	private TextView  updateError_txt;
	
	// Attributes about Event Handler
	private OnClickListener onClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (v == updateCancel_btn) finish();
			else 
				if (v == updateSave_btn)
				{
					if (checkUserInput() == true) updateProgram();
					else
					{
						updateName_edt.setText("");
						update_ratingBar.setRating(0);
					}
				}
		}
	};
	
	// Attributes about Data Model: json-auto, XML-program and SQLite-listview
	
	/* +++++++++++++++ Methods Constructors +++++++++++++++ */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);
		// Get intent that call this Activity
		Intent callerIntent = getIntent();
		Bundle packageFromCaller = callerIntent.getBundleExtra("MyPackage");
		_id	   	  = packageFromCaller.getInt("id");
		_fileName = packageFromCaller.getString("title");
		_rating   = packageFromCaller.getInt("rating");
		initialView();
	}

	/* +++++++++++++++ Methods Controller +++++++++++++++ */
	public void initialView()
	{
		updateCancel_btn  = (Button)findViewById(R.id.updateCancel_btn);
		updateSave_btn 	  = (Button)findViewById(R.id.updateSave_btn);
		updateName_edt	  = (EditText)findViewById(R.id.updateName_edt);
		update_ratingBar  = (RatingBar)findViewById(R.id.update_ratingBar);
		updateError_txt	  = (TextView)findViewById(R.id.updateError_txt);
		
		updateCancel_btn.setOnClickListener(onClick);
		updateSave_btn.setOnClickListener(onClick);
		
		updateName_edt.setText(_fileName);
		int pos = updateName_edt.getText().length();
		updateName_edt.setSelection(pos);
		update_ratingBar.setRating(_rating);
	}
	
	public boolean checkUserInput()
	{
		String tmp = updateName_edt.getText().toString();
		/* Kiem tra xem file da co chua
		Tên này phải khác với tên các file khác nhưng phải không cần khác với tên file hiện tại chính file đó */
		if(_fileName.equals(tmp)) return true;
		else
		{
			if(DataSource.checkExistFile(tmp) == true)
			{
				updateError_txt.setText("Error: File have existed, please choose other name");
				return false;
			}
		}
		
		//Check special characters
		if(tmp.contains("{") || tmp.contains("}") || tmp.contains("[") || tmp.contains("]"))
		{
			updateError_txt.setText("Error: File name mustn't contain \" , : {} []");
			return false;
		}
		if(tmp.contains("\"") || tmp.contains(",") || tmp.contains(":"))
		{
			updateError_txt.setText("Error: File name mustn't contain \" , : {} []");
			return false;
		}
		// Have to input file name
		if (tmp.equals("")) {
			updateError_txt.setText("Error: You have to input file name");
			return false;
		}
		// Check the length of file name
		if(tmp.length() > 30) {
			updateError_txt.setText("Error: File name is too long, the length < 30 char");
			return false;
		}
		return true;
	}
	
	/* +++++++++++++++ Methods Save +++++++++++++++ */
	public void updateProgram()
	{
		if(checkUserInput() == true)
		{
			String fileName = updateName_edt.getText().toString();
			int    rating	= (int)update_ratingBar.getRating();			
			
			updateProgramToSQLite(_id, fileName, rating);			
			updateProgramToJSON1(_id, fileName);
			updateProgramToJSON2(_id);
			
			// Tra ve kq o SavePage Activity
			Intent intent = getIntent(); // get SavePageActivity
			setResult(SavePageActivity.RESULT_UPDATE_TRUE, intent);
			
			finish();
		}
	}
	
	public void updateProgramToJSON1(int id, String fileName) // Save into file json for AutoComplete function
	{
		Gson gson;
		String result;
			
		DataSource.srcSearchList.updateObject(id, fileName);
		gson 	= new GsonBuilder().create();
		result 	= gson.toJson(DataSource.srcSearchList);

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
	public void updateProgramToJSON2(int id) // Save into file json for Load To Main Screen Function
	{
		
	}
	public void updateProgramToSQLite(int id, String fileName, int rating) // Save into file json for Sort & ListView Function
	{		
		DataSource.dataFile.updateRecord(id, fileName, rating);
	}
}