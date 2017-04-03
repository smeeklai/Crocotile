package tuat.crocotitle.save;

import com.example.testproject01.R;

import tuat.crocotitle.save.models.ResultListItem;
import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
 * Author	: Le Vinh - Vietnam
 * Time	 	: 25/6/2013
 * Name		: ResultListView Class (activity)
 * Function	: Get View from listview_row.xml
 * Others	:  
 */
public class ResultListView extends LinearLayout {
	private ImageView ratingListviewItem;
	private TextView  nameListviewItem;
	private TextView  dateListviewItem;
	private CheckBox  boxListviewItem;

	public ResultListView(Context context) {
		super(context);

		// read listview.xml to get the components
		LayoutInflater linflater = (LayoutInflater) ((SavePageActivity)context)
									.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
		linflater.inflate(R.layout.listview_row, this);

		// Get the components
		this.ratingListviewItem = (ImageView) findViewById(R.id.ratingListviewItem);
		this.nameListviewItem	= (TextView)  findViewById(R.id.nameListviewItem);
		this.dateListviewItem   = (TextView)  findViewById(R.id.dateListviewItem);
		this.boxListviewItem    = (CheckBox)  findViewById(R.id.boxListviewItem);
		
		// Event handler for Checkbox
		boxListviewItem.setOnCheckedChangeListener(new OnCheckedChangeListener() {			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
			}
		});
	}
	
	/**
     * Set data into ListView
     * @param item
     */
    public void setListItem(ResultListItem item){
    	switch(item.getRating())
    	{
    	case 1:
    		this.ratingListviewItem.setImageResource(R.drawable.one_star);
    		break;
    	case 2:
    		this.ratingListviewItem.setImageResource(R.drawable.two_star);
    		break;
    	case 3:
    		this.ratingListviewItem.setImageResource(R.drawable.three_star);
    		break;
    	default:
    		this.ratingListviewItem.setImageResource(R.drawable.no_star);
    		break; 		
    	}
        this.nameListviewItem.setText(item.getName()+Integer.toString(item.getId()));
        this.dateListviewItem.setText(item.getDate());
    }	

}
