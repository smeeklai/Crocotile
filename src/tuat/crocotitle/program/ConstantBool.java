package tuat.crocotitle.program;

import com.example.testproject01.R;

import tuat.crocotitle.quickaction.lib.QuickAction;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ConstantBool extends ConstantTile {
	boolean value = false;
	TextView valueText; 
	ToggleButton toggle;
	public EditText edtInput;
	public ConstantBool(Context mContext, int argumentTileTypeId) {
		super(mContext, argumentTileTypeId);
		valueText = new TextView(mContext);
		tileTypeId = argumentTileTypeId;
		frame.setImageResource(R.drawable.cons_boolean);
		frame.setScaleType(ImageView.ScaleType.FIT_XY);
		this.addView(frame);
		this.addView(valueText); 
		FrameLayout.LayoutParams textMarginParams = new FrameLayout.LayoutParams(-2, -2);
		textMarginParams.gravity = Gravity.CENTER; 
		valueText.setLayoutParams(textMarginParams);
		setValue(false);
		editValue.setOnClickListener(new OnClickListener() {  
			@Override  
			public void onClick(View v) {  
				editDialog();
			}  						
		}); 
		
		onClickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
			    QuickAction qa = new QuickAction(v); 
			    qa.addActionItem(editValue);
			    qa.addActionItem(deleteTile);
			    qa.addActionItem(moveTile);
			    qa.addActionItem(copyTile);
			    qa.setAnimStyle(QuickAction.ANIM_AUTO);  	      
			    qa.show();  
				// TODO Auto-generated method stub
			}
		};
		frame.setOnClickListener(onClickListener); 
		/*
		valueText.setClickable(true);
		valueText.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
			}
		});*/
		// TODO Auto-generated constructor stub
	}

	public ConstantBool(Context mContext, int argumentTileTypeId,
			AttributeSet attrs) {
		super(mContext, argumentTileTypeId, attrs);
		// TODO Auto-generated constructor stub
	}

	public ConstantBool(Context mContext, int argumentTileTypeId,
			AttributeSet attrs, int defStyle) {
		super(mContext, argumentTileTypeId, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	public void setValue(boolean setValue){
		value = setValue;
		if(setValue){
			valueText.setText("TRUE");
		}else{
			valueText.setText("FALSE");
		}
	}
	public boolean getValue(){
		return value;
	}
	private void editDialog(){
		edtInput = new EditText(context);
		edtInput.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_SIGNED);
		toggle = new ToggleButton(context);
		toggle.setTextOff("FALSE");
		toggle.setTextOn("TRUE");
		toggle.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	            if(isChecked){
	            	setValue(true);
	            }else{
	            	setValue(false);
	            }
	        }
	    });
		new AlertDialog.Builder(context)
		.setIcon(android.R.drawable.ic_secure)
		.setView(toggle)
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			}
		})
		.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			}
		})
		.show();
	}
}
