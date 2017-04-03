package tuat.crocotitle.program;

import java.util.ArrayList;

import com.example.testproject01.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import tuat.crocotitle.program.models.TileData;
import tuat.crocotitle.save.SaveInputActivity;
import tuat.crocotitle.save.models.AutoCompleteList;
import tuat.crocotitle.save.models.DBAdapter;
import tuat.crocotitle.save.models.DataSource;
import tuat.crocotitle.save.models.ResultListItem;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SlidingDrawer;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.app.TabActivity;
import android.content.Intent;
import android.database.Cursor;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.TextView;

public class ProgramActivity extends TabActivity {
	SlidingDrawer slidingDrawer;
	ScrollView scroll;
	FrameLayout noteFrame;
	Note note;	
	TileData titleProgram; 
	//TileData tiltleProgram = new TileData(Note.PROGRAM);
	// +++++ Vinh
	//TileData titleProgram; 
	String jsonPro;
	int _id;
	public void loadProgram(){
		// Get intent that call this Activity - SavePageActivity
		Intent callerIntent = getIntent();
		Bundle packageFromCaller = callerIntent.getBundleExtra("MyProgram");
		if(packageFromCaller != null){
			_id = packageFromCaller.getInt("id");
			String selection = "id = " + Integer.toString(_id);
			
			Cursor cursor = DataSource.dataFile.getJsonProgram(selection);
			int _id2		  = cursor.getColumnIndex("id");
	        int _program  = cursor.getColumnIndex("program");
	        
	        // Repeat to get Data
	        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
	        {
	        	// cursor.getInt(_id2);
	        	jsonPro = cursor.getString(_program);
	        }
	        cursor.close();
			
			Gson gson = new GsonBuilder().create();
			titleProgram = gson.fromJson(jsonPro, TileData.class);
			Toast toast = Toast.makeText(ProgramActivity.this, Integer.toString(_id)+jsonPro, Toast.LENGTH_SHORT);
			toast.show();
		}		
	}
	// +++++
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.program_screen);

		ImageButton runButton = (ImageButton) findViewById(R.id.runButton);
		//scroll = (ScrollView) findViewById(R.id.noteScroll);
		slidingDrawer = (SlidingDrawer) findViewById(R.id.result);
		note = new Note(this);
		PrintTile.consoleInit((TextView)findViewById(R.id.console));
		VariableTile.hashInit();
		// Generate tab
		genTab();
		
		
		runButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				slidingDrawer.toggle();
			}
		});
		slidingDrawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
			@Override
			public void onDrawerOpened() {
				// TODO Auto-generated method stub
				note.getProgram().exec();
			}
		});
		slidingDrawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
			@Override
			public void onDrawerClosed() {
				// TODO Auto-generated method stub
				PrintTile.consoleClear();
			}
		});
		
		//Invoke the setupUI method in this class
		//setupUI(findViewById(R.id.noteScroll));
		noteFrame = (FrameLayout)findViewById(R.id.noteFrame);
		note.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
		ViewGroup.LayoutParams.MATCH_PARENT));
		noteFrame.addView(note);
		
		// +++++ Vinh
		loadProgram();
		// +++++
	}

	/**
	 * Create options from the program page
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.program_menu, menu);
		return true;
	}

	/**
	 * Create the selection handle for each option of the program page
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		// respond to menu item selection
		switch (item.getItemId()) {
		case R.id.reset:
			// do something to reset
			break;
			
		case R.id.save:
			Gson gson;
			titleProgram = note.getTileData();
			DataSource.myProgram = titleProgram;
			gson 	= new GsonBuilder().create();
			jsonPro	= gson.toJson(DataSource.myProgram);
			
			Bundle bundle = new Bundle();
	 		bundle.putString("json", jsonPro);
			Intent saveInputDialog = new Intent(ProgramActivity.this, SaveInputActivity.class);
			saveInputDialog.putExtra("MyJson", bundle);
			startActivity(saveInputDialog);
			break;
			
		default:
			return super.onOptionsItemSelected(item);
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * This method is made for generating and controlling the variables,
	 * constants, statement and operation tabs.
	 */
	public void genTab() {
		TabHost tabHost = getTabHost();
		TestNote testNote = new TestNote(note);
		// Create the variable tab and get the its contents from the
		// VariableActivity class
		TabSpec varspec = tabHost.newTabSpec("Variables");
		varspec.setIndicator("V");
		Intent varIntent = new Intent(this, VariableActivity.class);
		varIntent.putExtra("testNote",testNote);
		varspec.setContent(varIntent);

		// Create the constant tab and get the its contents from the
		// ConstantActivity class
		TabSpec conspec = tabHost.newTabSpec("Constant");
		conspec.setIndicator("C");
		Intent conIntent = new Intent(this, ConstantActivity.class);
		conIntent.putExtra("testNote",testNote);
		conspec.setContent(conIntent);
		// Create the statement tab and get the its contents from the
		// StatementActivity class
		TabSpec statespec = tabHost.newTabSpec("Statement");
		statespec.setIndicator("S");
		Intent stateIntent = new Intent(this, StatementActivity.class);
		stateIntent.putExtra("testNote",testNote);
		statespec.setContent(stateIntent);

		// Create the operator tab and get the its contents from the
		// OperatorActivity class
		TabSpec operspec = tabHost.newTabSpec("Operator");
		operspec.setIndicator("O");
		Intent operIntent = new Intent(this, OperatorActivity.class);
		operIntent.putExtra("testNote",testNote);
		operspec.setContent(operIntent);

		// Add those tabs to the panel
		tabHost.addTab(varspec);
		tabHost.addTab(conspec);
		tabHost.addTab(statespec);
		tabHost.addTab(operspec);
	}

	/**
	 * This method is made for closing the result panel when the users touch at
	 * the other area.
	 * 
	 * @param view
	 */
	public void setupUI(View view) {

		// Set up touch listener for non-text box views to result panel
		view.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				// Close the result panel when the user touch at the other area
				slidingDrawer.close();
				return false;
			}
		});

		// If a layout container, iterate over children and seed recursion.
		if (view instanceof android.view.ViewGroup) {

			for (int i = 0; i < ((android.view.ViewGroup) view).getChildCount(); i++) {

				View innerView = ((android.view.ViewGroup) view).getChildAt(i);

				setupUI(innerView);
			}
		}
	}
	public void onFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		//Log.d("debug", note.getProgram().toString());
		note.resize();
	}
	public Note getNote(){
		return note;
	}
	public int getSelectedTileType(){
		return note.getSelectedTileType();
	}
	/*
	public ArrayList<RuledLine> getRuledLines(){
		
		return  
	}*/
	public Tile getClipBoardTile(int tileTypeId){
		return note.getClipBoardTile(this,tileTypeId);
	}
}
