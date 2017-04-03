package tuat.crocotitle.save;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.ArrayList;

import com.example.testproject01.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import tuat.crocotitle.program.ProgramActivity;
import tuat.crocotitle.save.models.AutoCompleteList;
import tuat.crocotitle.save.models.AutoCompleteObject;
import tuat.crocotitle.save.models.DataSource;
import tuat.crocotitle.save.models.ResultListAdapter;
import tuat.crocotitle.save.models.ResultListItem;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

/*
 * Author	: Le Vinh - Vietnam
 * Time	 	: 20/6/2013
 * Name		: SaveInputActivity Class (activity)
 * Function	: Save program to file json and database
 * Others	: Process with View, Event, 
 */

public class SavePageActivity extends Activity implements TextWatcher{
	/* +++++++++++++++ Global var for this class +++++++++++++++ */
	public static final int REQUEST_CODE_UPDATE = 10;
	public static final int RESULT_UPDATE_TRUE  = 1;
	public static final int RESULT_UPDATE_FALSE = 0;
	String Orderby = null;
	String selection = null;
	/* +++++++++++++++ Attributes +++++++++++++++ */
	// Attributes about GUI
	private AutoCompleteTextView searchFile_txt;
	private Button searchFile_btn, deleteFile_btn;
	private ListView listFile;
	//private TextView test;
	
	/*Attributes about data source*/
	// For Search in AutoCompleteTextView
	private ArrayList<AutoCompleteObject>	 srcSearch	   = new ArrayList<AutoCompleteObject>();
	private ArrayAdapter<AutoCompleteObject> adapterSearch = null;
	
	// For result listview
	private ArrayList<ResultListItem> 		 srcResult	   = new ArrayList<ResultListItem>();
	private ResultListAdapter 				 adapterResult = null;
	Cursor cursor; // cursor is used to store table result from query database
	protected Object mActionMode;
	int selectedItem;

	/*Attributes about event handler*/
	private android.view.View.OnClickListener btnListener = new View.OnClickListener() {		
		@Override
		public void onClick(View v) {
			if(v == searchFile_btn){
				String keyword = searchFile_txt.getText().toString();
				if(keyword.equalsIgnoreCase("")) selection = null;
				else selection = "filename LIKE '%" + keyword + "%'";
				// Tim kiem lo di chu hoa, chu thuong mien la co key word la tim duoc
				// ke ca keyword o giua tu
				Toast toast = Toast.makeText(SavePageActivity.this, selection, Toast.LENGTH_SHORT);
		 		toast.show();
				// Update Listview
				srcResult.clear();
				getSourceListResult(selection, Orderby);
				adapterResult.notifyDataSetChanged();
			}
			else 
				if(v == deleteFile_btn){
					AlertDialog.Builder alert = new AlertDialog.Builder(SavePageActivity.this);			
					alert.setTitle("Delete File");
					alert.setMessage("Are you sure you want to delete this file ?");
					alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {				
						@Override
						public void onClick(DialogInterface dialog, int which) {
							for(int i = listFile.getChildCount()-1;i>=0;i--)
							 {
								 // lấy ra dòng thứ i trong ListView
								 View tmp = listFile.getChildAt(i);
								 //Ta chỉ lấy CheckBox ra kiểm tra
								 CheckBox chk =(CheckBox) tmp.findViewById(R.id.boxListviewItem);
								 //Nếu nó Checked thì xóa ra khỏi arrEmployee
								 if(chk.isChecked())
								 {
									 //xóa phần tử thứ i ra khỏi danh sách
									 AutoCompleteObject temp = srcSearch.get(i);
									 deleteRecord(temp.getId());
								 }
							 }
							 //Sau khi xóa xong thì gọi update giao diện
							srcSearch	   = DataSource.srcSearchList.getList();
							adapterSearch  = new ArrayAdapter<AutoCompleteObject>(SavePageActivity.this, android.R.layout.simple_list_item_1, srcSearch);
							searchFile_txt.setAdapter(adapterSearch);
							
							srcResult.clear();
							getSourceListResult(selection, Orderby);
							adapterResult.notifyDataSetChanged();
						}
					});
					alert.setNegativeButton("No", new DialogInterface.OnClickListener() {				
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					});
					alert.create().show();					 
				}			
		}
	};
	/* +++++++++++++++ Methods Constructors +++++++++++++++ */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.saved_screen);
		initialView();
		getSourceAutoCompleteTextView();
		getSourceListResult(selection, Orderby);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_file, menu);
		return true;
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.listview, menu);
	}
	// Xử lý kết quả trả về từ UpdateActivity ở đây
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE_UPDATE) {
			switch (resultCode) {
			case RESULT_UPDATE_TRUE:
				break;
			default:
				return;
			}
			// Sau khi update dữ liệu gọi update giao diện
			srcSearch = DataSource.srcSearchList.getList();
			adapterSearch = new ArrayAdapter<AutoCompleteObject>(SavePageActivity.this, android.R.layout.simple_list_item_1, srcSearch);
			searchFile_txt.setAdapter(adapterSearch);

			srcResult.clear();
			getSourceListResult(selection, Orderby);
			adapterResult.notifyDataSetChanged();
		}
	}
	
	/* +++++++++++++++ Methods Controller +++++++++++++++ */
	public void initialView()
	{
		searchFile_txt = (AutoCompleteTextView)findViewById(R.id.searchFileAutoComplete_txt);
		searchFile_btn = (Button)findViewById(R.id.searchFile_btn);
		deleteFile_btn = (Button)findViewById(R.id.del_button);
		listFile 	   = (ListView)findViewById(R.id.listFile);
		//test		   = (TextView)findViewById(R.id.test);
		
		searchFile_btn.setOnClickListener(btnListener);
		deleteFile_btn.setOnClickListener(btnListener);
		
		srcSearch	   = DataSource.srcSearchList.getList();
		adapterSearch  = new ArrayAdapter<AutoCompleteObject>(this, android.R.layout.simple_list_item_1, srcSearch);
		searchFile_txt.setAdapter(adapterSearch);
		searchFile_txt.addTextChangedListener(this);	
		
		adapterResult  = new ResultListAdapter(this, R.layout.listview_row, srcResult);
		listFile.setAdapter(adapterResult);
		listFile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
				//test.setText("position : " + arg2 + "; value =" + srcSearch.get(arg2));
				ResultListItem tmp = adapterResult.getItem(position);
		 		Bundle bundle = new Bundle();
		 		bundle.putInt("id", tmp.getId());
		 		Intent load = new Intent(SavePageActivity.this, ProgramActivity.class);
		 		load.putExtra("MyProgram", bundle);	 		
		 		startActivity(load);
			}
		});
		
		listFile.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				selectedItem = position; // Vi positon tinh tu 0, trong khi id trong csdl tinh tu 1
		        openContextMenu(listFile);
		        return true;
			}
		});
		registerForContextMenu(listFile);
	}
	
	// Get data source for AutoCompleteTextView, from FILE JSON
	public void getSourceAutoCompleteTextView()
	{
		Gson gson = new GsonBuilder().create();
		try {
			FileInputStream in = openFileInput("AutoCompleteList.json");
			Reader reader 	   = new InputStreamReader(in);
			DataSource.srcSearchList = gson.fromJson(reader, AutoCompleteList.class);
			/*srcSearch.addAll(srcSearchList.getList());
			Phai dung phuong thuc addAll, ko the gan truc tiep srcSearch=srcSearchArray.getList() vi ArrayAdapter ko nhan tham chieu*/
			in.close();			
			/*String tmp = "";
			if(srcSearch != null)
			{
				for (int i = 0; i < srcSearch.size(); i++)
				{
					   AutoCompleteObject item = srcSearch.get(i);
					   ResultListItem temp 	   = new ResultListItem();
					   temp.setName(item.getFileName());
					   srcResult.add(temp);
					   tmp += Integer.toString(item.getId()) + item.getFileName();
				}
			}
			else tmp = null;
			tmp += "\n + array:";
			test.setText(tmp);*/			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void getSourceListResult(String selection, String orderBy)
	{
		cursor = DataSource.dataFile.getAll(selection, orderBy);
		int _id		  = cursor.getColumnIndex("id");
        int _filename = cursor.getColumnIndex("filename");
        int _rating   = cursor.getColumnIndex("rating");
        int _date  	  = cursor.getColumnIndex("date");
        
        // Repeat to get Data
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
        	ResultListItem temp = new ResultListItem();
        	temp.setId(cursor.getInt(_id));
        	temp.setName(cursor.getString(_filename));
        	temp.setRating(cursor.getInt(_rating));
        	temp.setDate(cursor.getString(_date));
        	srcResult.add(temp);
        }
        cursor.close();
	}
	
	/* +++++++++++++++ Methods Event Handler for AutoCompleteTextView +++++++++++++++ */
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count)
	{
		
	}
	@Override
	public void afterTextChanged(Editable s)
	{		
	}
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) 
	{		
	}
	
	/* +++++++++++++++ Methods Event Handler for Context Menu Listview +++++++++++++++ */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	 	case R.id.renameFile:
	 		// Tao goi package truyen qua Update Activity
	 		ResultListItem tmp = adapterResult.getItem(selectedItem);
	 		Bundle bundle = new Bundle();
	 		bundle.putInt("id", tmp.getId());
	 		bundle.putString("title", tmp.getName());
	 		bundle.putInt("rating", tmp.getRating());
	 		Intent updateDialog = new Intent(SavePageActivity.this, UpdateActivity.class);
	 		updateDialog.putExtra("MyPackage", bundle);	 		
	 		startActivityForResult(updateDialog, REQUEST_CODE_UPDATE); //call startActivityForResult
			// Update giao dien
			srcSearch	   = DataSource.srcSearchList.getList();
			adapterSearch  = new ArrayAdapter<AutoCompleteObject>(SavePageActivity.this, android.R.layout.simple_list_item_1, srcSearch);
			searchFile_txt.setAdapter(adapterSearch);
			srcResult.clear();
			getSourceListResult(selection, Orderby);
			adapterResult.notifyDataSetChanged();
	        break;			        
	 	case R.id.deleteFile:
			AlertDialog.Builder alert = new AlertDialog.Builder(SavePageActivity.this);			
			alert.setTitle("Delete File");
			alert.setMessage("Are you sure you want to delete this file ?");
			alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					ResultListItem tmp = adapterResult.getItem(selectedItem);
					deleteRecord(tmp.getId());
					// Không biết tại sao nhưng mà cần phải tạo lại Adapter mới có thể cập nhật AutoComplete box được
					// Gan lai source for listview and autocomplete box
					srcSearch	   = DataSource.srcSearchList.getList();
					adapterSearch  = new ArrayAdapter<AutoCompleteObject>(SavePageActivity.this, android.R.layout.simple_list_item_1, srcSearch);
					searchFile_txt.setAdapter(adapterSearch);
					srcResult.clear();
					getSourceListResult(selection, Orderby);
					adapterResult.notifyDataSetChanged();
				}
			});
			alert.setNegativeButton("No", new DialogInterface.OnClickListener() {				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
			alert.create().show();
			break;
		case R.id.cancel:
			
			return false;
      default:
    	  return false;
      }
		Toast toast = Toast.makeText(SavePageActivity.this, Integer.toString(selectedItem), Toast.LENGTH_SHORT);
 		toast.show();
		return super.onContextItemSelected(item);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.ascname:
			Orderby = "filename ASC";
			srcResult.clear();
			getSourceListResult(selection, Orderby);
			adapterResult.notifyDataSetChanged();
			break;
		case R.id.ascdate:
			Orderby = "id ASC";
			srcResult.clear();
			getSourceListResult(selection, Orderby);
			adapterResult.notifyDataSetChanged();
			break;
		case R.id.ascrate:
			Orderby = "rating ASC";
			srcResult.clear();
			getSourceListResult(selection, Orderby);
			adapterResult.notifyDataSetChanged();
			break;
		case R.id.dscname:
			Orderby = "filename DESC";
			srcResult.clear();
			getSourceListResult(selection, Orderby);
			adapterResult.notifyDataSetChanged();
			break;
		case R.id.dscdate:
			Orderby = "id DESC";
			srcResult.clear();
			getSourceListResult(selection, Orderby);
			adapterResult.notifyDataSetChanged();
			break;
		case R.id.dscrate:
			Orderby = "rating DESC";
			srcResult.clear();
			getSourceListResult(selection, Orderby);
			adapterResult.notifyDataSetChanged();
			break;
		case R.id.clear:
			clearDatabase();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/* +++++++++++++++ Utilities methods +++++++++++++++ */
	public void clearDatabase(){
		// Clear JSON-1
		 DataSource.srcSearchList.clearObject();
		 try {
				FileOutputStream   out 	  = openFileOutput("AutoCompleteList.json", Context.MODE_PRIVATE);
				OutputStreamWriter writer = new OutputStreamWriter(out);
				Gson gson 	  = new GsonBuilder().create();
				String result = gson.toJson(DataSource.srcSearchList);
				writer.write(result);
				writer.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		 
		 // Clear table and database, vì delete table chi xóa bản ghi trong bảng đó
		 DataSource.dataFile.deleteTable();
		 deleteDatabase("qlfile.db");
	}
	public void deleteRecord(int id){
		AutoCompleteObject tmp = DataSource.srcSearchList.getObject(id);
		DataSource.srcSearchList.removeObject(tmp);
		//srcSearch.remove(DataSource.srcSearchList.ConvertIndexToId(tmp.getId()));
		writeJson();		
		DataSource.dataFile.deleteRecord(id);
	}
	
	public  void writeJson()
	{
		Gson gson 	  = new GsonBuilder().create();
		String result = gson.toJson(DataSource.srcSearchList);
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
	
}
