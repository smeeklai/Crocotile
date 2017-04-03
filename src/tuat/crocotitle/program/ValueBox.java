package tuat.crocotitle.program;

import com.example.testproject01.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class ValueBox extends FrameLayout {
	private Note note;
	private Tile valueTile;
	private Tile pTile;
	private ImageView frame;
	private Context context;
	private int type;
	FrameLayout.LayoutParams tileLayoutParams;
	private static final int INT = 1,
			FLOAT = 2,
			CHAR = 4,
			BOOL = 8;
	public ValueBox(Context mContext,Tile mTile,int mType) {
		super(mContext);
		note = ((ProgramActivity)mContext).getNote();
		context = mContext;
		type = mType;
		pTile = mTile;
		frame = new ImageView(mContext);
		frame.setImageResource(R.drawable.valuebox);
		this.setClickable(true);
		this.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int tileTypeId = note.getSelectedTileType();
				valueTile = note.getClipBoardTile(context,tileTypeId);
				int num = type;
				tileLayoutParams = new FrameLayout.LayoutParams(v.getWidth(), v.getHeight());
				switch(tileTypeId){
					case Note.CONSTANTBOOLEANTILE:
					case Note.VARIABLEBOOLEANTILE:
						if((num>>3)==1){
							addViewL();
							return;
						}
						break;
					case Note.CONSTANTCHARTILE:
					case Note.VARIABLECHARTILE:
						if((num>>2)%2==1){
							addViewL();
							return;
						}
						break;
					case Note.CONSTANTFLOATTILE:
					case Note.VARIABLEFLOATTILE:
						if((num>>1)%2==1){
							addViewL();
							return;
						}
						break;
					case Note.CONSTANTINTTILE:
					case Note.VARIABLEINTTILE:
						if((num%2)==1){
							addViewL();
							return;
						}
						break;
					case Note.ADDITIONTILE:
					case Note.MODULATIONTILE:
					case Note.MULTIPLYTILE:
					case Note.SUBTRACTIONTILE:
					case Note.DIVISIONTILE:
						if((num>>3)==1&&(num>>2)%2==1&&(num>>1)%2==1&&(num%2)==1){
							addViewL();
							return;
						}
					break;
				}
				valueTile = null;
				/*
				if(tileTypeId!=Note.REPEATTILE){
					valueTile.setInValueBoxTrue();
					valuebox.addView(valueTile);
				}*/
				// TODO Auto-generated method stub				
			}
		});
		frame.setScaleType(ImageView.ScaleType.FIT_XY);
		this.addView(frame);
		
		// TODO Auto-generated constructor stub
	}

	public ValueBox(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public ValueBox(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	public void deleteValueTile(){
		this.removeView(valueTile);
		valueTile = null;
	}
	public void setValueTile(Tile mTile){
		valueTile = mTile;
	}
	public Tile getValueTile(){
		return valueTile;
	}
	public static int getValueBoxType(boolean intFlag,boolean floatFlag,boolean charFlag,boolean boolFlag){
		int value = 0;
		if(intFlag)value += INT;
		if(floatFlag)value += FLOAT;
		if(charFlag)value += CHAR;
		if(boolFlag)value += BOOL;
		return value;
	}
	private void addViewL(){
		this.addView(valueTile);
		valueTile.setInValueBoxTrue();
		valueTile.resize();
	}
}
