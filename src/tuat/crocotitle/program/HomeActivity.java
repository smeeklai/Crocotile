package tuat.crocotitle.program;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import com.example.testproject01.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import tuat.crocotitle.save.SavePageActivity;
import tuat.crocotitle.save.models.AutoCompleteList;
import tuat.crocotitle.save.models.DBAdapter;
import tuat.crocotitle.save.models.DataSource;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * This class was created for creating a home page of this application
 * @author S.Meeklai
 *
 */
public class HomeActivity extends Activity{
	// Vinh
	public void initialDatabase() {
		try {
			FileInputStream in = openFileInput("AutoCompleteList.json");
			Reader reader = new InputStreamReader(in);
			Gson gson = new GsonBuilder().create();
			DataSource.srcSearchList = gson.fromJson(reader,
					AutoCompleteList.class);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		DataSource.dataFile = new DBAdapter(this);
		DataSource.dataFile.open();
	}

	//
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homescreen);
		
		Button saveButton = (Button)findViewById(R.id.save_button);
		Button newProgramButton = (Button)findViewById(R.id.newProgram_button);
		
		//Link to the saved data page
		saveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), SavePageActivity.class);
				startActivity(i);
			}
		});
		
		//Link to the new program page
		newProgramButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), ProgramActivity.class);
				startActivity(i);
			}
		});
		// Vinh
		initialDatabase();
	}
}
