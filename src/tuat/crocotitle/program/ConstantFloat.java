package tuat.crocotitle.program;

import com.example.testproject01.R;

import tuat.crocotitle.quickaction.lib.QuickAction;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class ConstantFloat extends ConstantTile {
	float value = 0;
	TextView valueText; 
	public EditText edtInput;
	public ConstantFloat(Context mContext, int argumentTileTypeId) {
		super(mContext, argumentTileTypeId);
		valueText = new TextView(mContext);
		tileTypeId = argumentTileTypeId;
		frame.setImageResource(R.drawable.cons_float);
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
		// TODO Auto-generated constructor stub
		type = FLOAT;
	}

	public ConstantFloat(Context mContext, int argumentTileTypeId,
			AttributeSet attrs) {
		super(mContext, argumentTileTypeId, attrs);
		// TODO Auto-generated constructor stub
		type = FLOAT;
	}

	public ConstantFloat(Context mContext, int argumentTileTypeId,
			AttributeSet attrs, int defStyle) {
		super(mContext, argumentTileTypeId, attrs, defStyle);
		// TODO Auto-generated constructor stub
		type = FLOAT;
	}
	private void editDialog(){
		edtInput = new EditText(context);
		edtInput.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_SIGNED|InputType.TYPE_NUMBER_FLAG_DECIMAL);
		new AlertDialog.Builder(context)
		.setIcon(android.R.drawable.ic_secure)
		.setTitle("Edit Value")
		//.setTitle("�ｽ�ｽ�ｽ�ｽ�ｽ�ｽ�ｽﾍゑｿｽ�ｽﾄゑｿｽ�ｽ�ｽ�ｽ�ｽ�ｽ�ｽ")
		.setView(edtInput)
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				/* OK�ｽ{�ｽ^�ｽ�ｽ�ｽ�ｽ�ｽN�ｽ�ｽ�ｽb�ｽN�ｽ�ｽ�ｽ�ｽ�ｽ�ｽ�ｽﾌ擾ｿｽ�ｽ�ｽ */
				setValue(Float.parseFloat(edtInput.getText().toString()));//edtInput.getText().toString()
			}
		})
		.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				/* Cancel �ｽ{�ｽ^�ｽ�ｽ�ｽ�ｽ�ｽN�ｽ�ｽ�ｽb�ｽN�ｽ�ｽ�ｽ�ｽ�ｽ�ｽ�ｽﾌ擾ｿｽ�ｽ�ｽ */
			}
		})
		.show();
	}
	public void setValue(float setValue){
		valueText.setText(Float.toString(setValue));
		value = setValue;
	}
	public float getValue(){
		return value;
	}
	public double exec(){
		return (double)getValue();
	}
}
