package tuat.crocotitle.program;

import com.example.testproject01.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class PrintTile extends Tile {
	ImageView frame;
	ValueBox valueBox;
	static TextView console;
	static final int MAX_LINES=256;
	
	public PrintTile(Context mContext, int argumentTileTypeId) {
		super(mContext, argumentTileTypeId);
		frame = new ImageView(mContext);
		frame.setImageResource(R.drawable.print);
		this.addView(frame);
		valueBox = new ValueBox(mContext,this,ValueBox.getValueBoxType(true,true,true,true));
		FrameLayout.LayoutParams headerMarginParams = new FrameLayout.LayoutParams(200,140);
		headerMarginParams.gravity = Gravity.LEFT; 
		headerMarginParams.setMargins(108,0,0,15);
		valueBox.setLayoutParams(headerMarginParams);
		this.addView(valueBox);
		// TODO Auto-generated constructor stub
		type = VOID;
	}

	public PrintTile(Context mContext, int argumentTileTypeId,
			AttributeSet attrs) {
		super(mContext, argumentTileTypeId, attrs);
		// TODO Auto-generated constructor stub
		type = VOID;
	}

	public PrintTile(Context mContext, int argumentTileTypeId,
			AttributeSet attrs, int defStyle) {
		super(mContext, argumentTileTypeId, attrs, defStyle);
		// TODO Auto-generated constructor stub
		type = VOID;
	}
	public static void consoleClear(){
		console.setText("");
	}
	public static void consoleInit(TextView getter){
		console = getter;
		console.setLines(MAX_LINES);
		console.setText("");
	}
	public double exec(){
		Tile tl;
		double value;
		String output;
		tl = valueBox.getValueTile();
		value = tl.exec();
		switch(tl.type){
		case INT:
		case BOOL:
			output = Integer.toString((int)value);
			break;
		case FLOAT:
			output = Float.toString((float)value);
			break;
		case CHAR:
			output = Character.toString((char)((int)value));
			break;
		case VOID:
			output = "void";
			break;
		default:
			output = "";
			break;
		}
		console.setText(output + "\n" + console.getText());
		return 0.0;
	}
}
