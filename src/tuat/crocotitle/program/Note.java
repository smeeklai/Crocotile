package tuat.crocotitle.program;

import java.util.ArrayList;

import tuat.crocotitle.program.models.TileData;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


public class Note extends ScrollView {
	private static final String TAG = "Note";
	public static final int PROGRAM = 999;
	public static final int CONSTANTINTTILE = 1;
	public static final int CONSTANTFLOATTILE = 2;
	public static final int CONSTANTCHARTILE = 3;
	public static final int CONSTANTBOOLEANTILE = 4;
	public static final int ASSIGNTILE = 5;
	public static final int IFTILE = 6;
	public static final int VARIABLEINTTILE= 7;
	public static final int VARIABLEFLOATTILE = 8;
	public static final int VARIABLECHARTILE = 9;
	public static final int VARIABLEBOOLEANTILE = 10;
	public static final int REPEATTILE = 11;
	public static final int ADDITIONTILE = 12;
	public static final int SUBTRACTIONTILE = 13;
	public static final int MULTIPLYTILE = 14;
	public static final int DIVISIONTILE = 15;
	public static final int MODULATIONTILE = 16;
	public static final int PRINTTILE = 17;
	public static final int EQUAL = 18;
	public static final int NOTEQUAL = 19;
	public static final int GREATERTHAN = 20;
	public static final int LOWERTHAN = 21;
	public static final int GREATERTHANEQUALTO = 22;
	public static final int LOWERTHANEQUALTO = 23;
	public static final int WIDTH = 5000;
	public static final int LINENUMBER = 256;
    private ScaleGestureDetector mGestureDetector;
    private HorizontalScrollView horizonalScrollView;
    private Drawable _image;
    private float _scaleFactor = 1.0f;
    private Toast toast;
    //private LinearLayout linearLayout;
    private Program program;
    private int selectedTileType = CONSTANTINTTILE;
    private RuledLine pRuledLine;
    private Tile clipBoardTile;
    private Context context;
    
    private SimpleOnScaleGestureListener simpleListener
    = new ScaleGestureDetector.SimpleOnScaleGestureListener() {
        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            Log.d(TAG, "onScaleBegin : "+ detector.getScaleFactor());
            toast.show();
            invalidate();
            return super.onScaleBegin(detector);
        }
        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            Log.d(TAG, "onScaleEnd : "+ detector.getScaleFactor());
            _scaleFactor *= detector.getScaleFactor();
            invalidate();
            super.onScaleEnd(detector);
        }
 
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            Log.d(TAG, "onScale : "+ detector.getScaleFactor());
            _scaleFactor *= detector.getScaleFactor();
            Log.v(TAG, "OK");
            invalidate();
            return true;
        };
	};
	@Override
	public boolean onTouchEvent(MotionEvent motionEvent) {
		super.onTouchEvent(motionEvent);
	    return mGestureDetector.onTouchEvent(motionEvent);
	}
	@Override
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.LTGRAY);
    }
	//private ScaleGestureDetector.SimpleOnScaleGestureListener stureListener;
	public Note(Context mContext) {
		super(mContext);
		context = mContext;
		horizonalScrollView = new HorizontalScrollView(context);
		pRuledLine = new RuledLine(context);
		//linearLayout = new LinearLayout(context);
		program = new Program(context,PROGRAM,LINENUMBER);
		//linearLayout.setOrientation(LinearLayout.VERTICAL);
		//program.setOrientation(LinearLayout.VERTICAL);
		ViewGroup.LayoutParams lineParams = new ViewGroup.LayoutParams(this.WIDTH, ViewGroup.LayoutParams.MATCH_PARENT);
		horizonalScrollView.addView(program);
		this.addView(horizonalScrollView);
		//linearLayout.addView(child);
		toast = Toast.makeText(context, "OK", Toast.LENGTH_SHORT);
		mGestureDetector =new ScaleGestureDetector(context,simpleListener);
		// TODO Auto-generated constructor stub
	}
	public Note(Context context,Program main) {
		super(context);
		/*
		linearLayout = new LinearLayout(context);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		this.addView(linearLayout);
		for(Tile element : main.tiles){
			//linearLayout.addView(element);
		}
		toast = Toast.makeText(context, "OK", Toast.LENGTH_SHORT);
		mGestureDetector =new ScaleGestureDetector(context,simpleListener);
		// TODO Auto-generated constructor stub
		 * 
		 */
	}	
	public Note(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	public Note(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	public int getSelectedTileType(){
		return selectedTileType;
	}
	public void setSelectedTileType(int num){
		selectedTileType = num;
		clipBoardTile = null;
	}
	public Tile getNewTile(Context mContext,int tileTypeId){
		Tile mTile;
		switch(tileTypeId){
			case CONSTANTINTTILE:
				mTile = new ConstantInt(mContext,tileTypeId);
				return mTile;
			case REPEATTILE:
				mTile = new Repeat(mContext,tileTypeId);
				return mTile;
		}
		return null;
	}
	public void setClipBoardTile(Tile mTile){
		clipBoardTile = mTile;
	}
	public Tile getClipBoardTile(Context mContext,int tileTypeId){
		Tile mTile;
		if(clipBoardTile == null){
			switch(tileTypeId){
			case CONSTANTINTTILE:
				mTile = new ConstantInt(mContext,tileTypeId);
				return mTile;
			case CONSTANTFLOATTILE:
				mTile = new ConstantFloat(mContext,tileTypeId);
				return mTile;
			case CONSTANTCHARTILE:
				mTile = new ConstantChar(mContext,tileTypeId);
				return mTile;
			case CONSTANTBOOLEANTILE:
				mTile = new ConstantBool(mContext,tileTypeId);
				return mTile;
			case REPEATTILE:
				mTile = new Repeat(mContext,tileTypeId);
				return mTile;
			case VARIABLEBOOLEANTILE:
			case VARIABLECHARTILE:
			case VARIABLEFLOATTILE:
			case VARIABLEINTTILE:
				mTile = new VariableTile(mContext, tileTypeId);
				return mTile;
			case IFTILE:
				mTile = new IfTile(mContext,tileTypeId);
				return mTile;
			case ADDITIONTILE:
				mTile = new AdditionTile(mContext,tileTypeId);
				return mTile;
			case ASSIGNTILE:
				mTile = new AssignmentTile(mContext,tileTypeId);
				return mTile;
			case PRINTTILE:
				mTile = new PrintTile(mContext, tileTypeId);
				return mTile;
			case SUBTRACTIONTILE:
				mTile = new Subtraction(mContext, tileTypeId);
				return mTile;
			case MULTIPLYTILE:
				mTile = new Multiplication(mContext,tileTypeId);
				return mTile;
			case DIVISIONTILE:
				mTile = new Division(mContext, tileTypeId);
				return mTile;
			case MODULATIONTILE:
				mTile = new Modulus(mContext, tileTypeId);
				return mTile;
			case EQUAL:
				mTile = new Equal(mContext,tileTypeId);
				return mTile;
			case NOTEQUAL:
				mTile = new NotEqual(mContext, tileTypeId);
				return mTile;
			case GREATERTHAN:
				mTile = new GreaterThan(mContext, tileTypeId);
				return mTile;
			case LOWERTHAN:
				mTile = new LowerThan(mContext, tileTypeId);
				return mTile;
			case GREATERTHANEQUALTO:
				mTile = new GreaterThanOrEqualTo(mContext,tileTypeId);
				return mTile;
			case LOWERTHANEQUALTO:
				mTile = new LowerThanOrEqualTo(mContext, tileTypeId);
				return mTile;
			}
		}else{
			switch(clipBoardTile.getTileTypeID()){
			case CONSTANTINTTILE:
				ConstantInt clipBoardIntTile = (ConstantInt)clipBoardTile;
				ConstantInt newIntTile = new ConstantInt(mContext,tileTypeId);
				newIntTile.setValue(clipBoardIntTile.getValue());
				return newIntTile;
			case CONSTANTFLOATTILE:
				ConstantFloat clipBoardFloatTile = (ConstantFloat)clipBoardTile;
				ConstantFloat newFloatTile = new ConstantFloat(mContext,tileTypeId);
				newFloatTile.setValue(clipBoardFloatTile.getValue());
				return newFloatTile;
			case CONSTANTCHARTILE:
				ConstantChar clipBoardmTile = (ConstantChar)clipBoardTile;
				ConstantChar newcharTile = new ConstantChar(mContext,tileTypeId);
				newcharTile.setValue(clipBoardmTile.getValue());
				return newcharTile;
			case CONSTANTBOOLEANTILE:
				ConstantBool clipBoardBooleanTile = (ConstantBool)clipBoardTile;
				ConstantBool newBooleanTile = new ConstantBool(mContext,tileTypeId);
				newBooleanTile.setValue(clipBoardBooleanTile.getValue());
				return newBooleanTile;
			case REPEATTILE:
				Repeat clipBoardRepeatTile = (Repeat)clipBoardTile;
				Repeat newRepeatTile = new Repeat(mContext,tileTypeId);
				//newRepeatTile.setValueBox(clipBoardTile.getValueBox());
				return newRepeatTile;
			case VARIABLEINTTILE:
			case VARIABLEFLOATTILE:
			case VARIABLECHARTILE:
			case VARIABLEBOOLEANTILE:
				VariableTile clipBoardVariableTile = (VariableTile)clipBoardTile;
				VariableTile newVariableTile = new VariableTile(mContext, tileTypeId);
				String v = clipBoardVariableTile.getValue();
				newVariableTile.setValue(v);
				return newVariableTile;
			}
		}
		return null;
	}
	public void resize(){
		program.resize();
	}
	public TileData getTileData(){
		TileData data = new TileData(this.PROGRAM);
		/*
		ArrayList<Tile> mainProgram = program.getTiles();
		for(int i = 0; i < mainProgram.size()  ;i++){
			
		}*/
		//program
		/*
		ArrayList<Tile> mainProgram = getProgram();
		for(int i = 0;mainProgram.size())
		*/
		return  data;
	}
	//public ArrayList<Tile> getProgram(){
		//return program.getTiles();
	//}
	public void setProgram(Program mProgram){
		program = mProgram;
	}
	public Tile getProgram(){
		return program;
	}
}
