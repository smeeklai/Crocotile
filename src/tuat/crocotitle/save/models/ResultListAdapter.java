package tuat.crocotitle.save.models;

import java.util.ArrayList;

import tuat.crocotitle.save.ResultListView;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/*
 * Author	: Le Vinh - Vietnam
 * Time	 	: 25/6/2013
 * Name		: ResultListAdapter Class
 * Function	: extends ArrayAdapter for Custom Listview
 * Others	: 
 */

public class ResultListAdapter extends ArrayAdapter<ResultListItem> {
	Activity 				  context = null;
	ArrayList<ResultListItem> array   = null;
	int layoutId;

	// Contructor này dùng để lấy về những giá trị được truyền vào từ Activity
	public ResultListAdapter(Activity context, int textViewResourceId, ArrayList<ResultListItem> objects) {
		super(context, textViewResourceId, objects);
		this.context 	= context;
        this.layoutId 	= textViewResourceId;
        this.array 		= objects;
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent){
		
        ResultListView view;
        if (convertView != null){
        	view = (ResultListView) convertView;
        } else {
        	view = new ResultListView(this.context);
        }
        ResultListItem item = array.get(position);
        view.setListItem(item);
        return view;
    }

}
