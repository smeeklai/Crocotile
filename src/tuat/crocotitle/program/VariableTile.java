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
import java.util.HashMap;

public class VariableTile extends Tile {
	String value = "";
	TextView valueText; 
	public EditText edtInput;
	static HashMap<String,Double> variableMap;
	
	public VariableTile(Context mContext, int argumentTileTypeId) {
		super(mContext, argumentTileTypeId);
		valueText = new TextView(mContext);
		tileTypeId = argumentTileTypeId;
		switch(argumentTileTypeId){
		case Note.VARIABLEINTTILE:
			frame.setImageResource(R.drawable.int_var);
			break;
		case Note.VARIABLEFLOATTILE:
			frame.setImageResource(R.drawable.float_var);
			break;
		case Note.VARIABLECHARTILE:
			frame.setImageResource(R.drawable.char_var);
			break;
		case Note.VARIABLEBOOLEANTILE:
			frame.setImageResource(R.drawable.boolean_var);
			break;
		}
		frame.setScaleType(ImageView.ScaleType.FIT_XY);
		this.addView(frame);
		this.addView(valueText); 
		FrameLayout.LayoutParams textMarginParams = new FrameLayout.LayoutParams(-2, -2);
		textMarginParams.gravity = Gravity.CENTER; 
		valueText.setLayoutParams(textMarginParams);
		setValue("");
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
		type = argumentTileTypeId - 7;
	}

	public VariableTile(Context mContext, int argumentTileTypeId,
			AttributeSet attrs) {
		super(mContext, argumentTileTypeId, attrs);
		// TODO Auto-generated constructor stub
		type = argumentTileTypeId - 7;
	}

	public VariableTile(Context mContext, int argumentTileTypeId,
			AttributeSet attrs, int defStyle) {
		super(mContext, argumentTileTypeId, attrs, defStyle);
		// TODO Auto-generated constructor stub
		type = argumentTileTypeId - 7;
	}

	public void setValue(String setValue){
		valueText.setText(setValue);
		value = setValue;
	}
	public String getValue(){
		return value;
	}
	public static void hashInit(){
		variableMap = new HashMap<String,Double>();
	}
	private void editDialog(){
		edtInput = new EditText(context);
		edtInput.setInputType(InputType.TYPE_CLASS_TEXT);
		new AlertDialog.Builder(context)
		.setIcon(android.R.drawable.ic_secure)
		.setTitle("Edit Value")
		//.setTitle("�ｽ�ｽ�ｽ�ｽ�ｽ�ｽ�ｽﾍゑｿｽ�ｽﾄゑｿｽ�ｽ�ｽ�ｽ�ｽ�ｽ�ｽ")
		.setView(edtInput)
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				/* OK�ｽ{�ｽ^�ｽ�ｽ�ｽ�ｽ�ｽN�ｽ�ｽ�ｽb�ｽN�ｽ�ｽ�ｽ�ｽ�ｽ�ｽ�ｽﾌ擾ｿｽ�ｽ�ｽ */
				setValue(edtInput.getText().toString());//edtInput.getText().toString()
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
		if(variableMap.containsKey(this.getValue()))
			return variableMap.get(this.getValue());
		return 0.0;
	}
	public void setVariable(double getter){
		double variable;
		switch(type){
		case CHAR:
		case INT:
			variable = (int)getter;
			break;
		case FLOAT:
			variable = getter;
			break;
		case VOID:
			variable = -1.0;
			break;
		case BOOL:
			variable = (getter!=0)?1.0:0.0;
			break;
		default:
			variable = 0.0;
			break;
		}
		variableMap.put(this.getValue(), new Double(variable));
	}
}
