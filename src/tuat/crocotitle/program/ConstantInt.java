package tuat.crocotitle.program;

import com.example.testproject01.R;

import tuat.crocotitle.quickaction.lib.ActionItem;
import tuat.crocotitle.quickaction.lib.QuickAction;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.DialogInterface;

@SuppressLint("ViewConstructor")
public class ConstantInt extends ConstantTile{
	int value = 0;
	TextView valueText; 
	public EditText edtInput;
	public ConstantInt(Context mContext , int argumentTileTypeId) {
		super(mContext, argumentTileTypeId);
		valueText = new TextView(mContext);
		tileTypeId = argumentTileTypeId;
		frame.setImageResource(R.drawable.cons_int);
		frame.setScaleType(ImageView.ScaleType.FIT_XY);
		this.addView(frame);
		this.addView(valueText); 
		FrameLayout.LayoutParams textMarginParams = new FrameLayout.LayoutParams(-2, -2);
		textMarginParams.gravity = Gravity.CENTER; 
		valueText.setLayoutParams(textMarginParams);
		setValue(0);
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
		type = INT;
	}
	public ConstantInt(Context mContext, int argumentTileTypeId,AttributeSet attrs) {
		super(mContext,argumentTileTypeId,attrs);
		type = INT;
	}
	public ConstantInt(Context mContext, int argumentTileTypeId,AttributeSet attrs,int defStyle) {
		super(mContext,argumentTileTypeId,attrs,defStyle);
		type = INT;
	}
	public void setValue(int setValue){
		valueText.setText(Integer.toString(setValue));
		value = setValue;
	}
	public int getValue(){
		return value;
	}
	private void editDialog(){
		edtInput = new EditText(context);
		edtInput.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_SIGNED);
		new AlertDialog.Builder(context)
		.setIcon(android.R.drawable.ic_secure)
		.setTitle("Edit Value")
		//.setTitle("�ｽ�ｽ�ｽ�ｽ�ｽ�ｽ�ｽﾍゑｿｽ�ｽﾄゑｿｽ�ｽ�ｽ�ｽ�ｽ�ｽ�ｽ")
		.setView(edtInput)
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				/* OK�ｽ{�ｽ^�ｽ�ｽ�ｽ�ｽ�ｽN�ｽ�ｽ�ｽb�ｽN�ｽ�ｽ�ｽ�ｽ�ｽ�ｽ�ｽﾌ擾ｿｽ�ｽ�ｽ */
				setValue(Integer.parseInt(edtInput.getText().toString()));//edtInput.getText().toString()
			}
		})
		.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				/* Cancel �ｽ{�ｽ^�ｽ�ｽ�ｽ�ｽ�ｽN�ｽ�ｽ�ｽb�ｽN�ｽ�ｽ�ｽ�ｽ�ｽ�ｽ�ｽﾌ擾ｿｽ�ｽ�ｽ */
			}
		})
		.show();
	}
	public double exec(){
		return (double)getValue();
	}
	
}
