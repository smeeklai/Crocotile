package tuat.crocotitle.program;

import java.util.ArrayList;

import com.example.testproject01.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.View.OnClickListener;

public class RuledLine extends FrameLayout {
	private ImageView frame;
	private LinearLayout linearLayout;
	public static final int TILESNUM = 10;
	private Tile[] tiles = new Tile[TILESNUM];
	private Tile mTile;
	private static final int BOTTOM = 0x50;
	private Context context;
	private ViewGroup.LayoutParams layoutParams;
	//private RuledLine ruledLine;
	/*
	private OnTouchListener touchListener = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			int a = note.getSelectedTileType();
			ConstantInt mTile = new ConstantInt(context,a);
		//	addTile(mTile);
			return true;
		}
	};*/
	public RuledLine(Context mContext) {
		super(mContext);
		context = mContext;
		//Log.d("debug",note.toString());
		//ruledLine = this;
		this.setForegroundGravity(BOTTOM);
		frame = new ImageView(context);
		frame.setBackgroundColor(R.drawable.bg);
		frame.setImageResource(R.drawable.note_bg);
		frame.setScaleType(ImageView.ScaleType.FIT_XY);
		frame.setClickable(true);
		frame.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ProgramActivity pa = (ProgramActivity)context;
				Note note = pa.getNote();
				//int tileTypeId =  pa.getSelectedTileType();
				int tileTypeId =  note.getSelectedTileType();
				mTile = note.getClipBoardTile(context,tileTypeId);
				for(int i = 0;i < TILESNUM;i++){
					if(tiles[i] == null){
						tiles[i] = mTile;
						linearLayout.addView(mTile);
						mTile.setFocusable(true);
						mTile.setFocusableInTouchMode(true);
						mTile.requestFocus();
						break;
					}
				}
				// TODO Auto-generated method stub				
			}
		});
		this.addView(frame);
		linearLayout = new LinearLayout(context);
		linearLayout.setOrientation(linearLayout.HORIZONTAL);
		linearLayout.setGravity(android.view.Gravity.CENTER_VERTICAL);
		layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		this.addView(linearLayout,layoutParams);
		//this.setOnTouchListener(touchListener);
		// TODO Auto-generated constructor stub
	}
/*	public void addNewTile(int TileTypeId){
		ProgramActivity pa = (ProgramActivity)context;
		Note note = pa.getNote();
		mTile = note.getNewTile(context,TileTypeId);
		addTile(mTile);
	}*/
	public void resize(){
		for(int i =0;i < TILESNUM; i++ ){
			if(tiles[i] == null){
				break;
			}
			tiles[i].resize();
		}
	}
	public void addTile(Tile dTile){
		for(int i = 0;i < TILESNUM;i++){
			if(tiles[i] == null){
				tiles[i] = dTile;
				linearLayout.addView(dTile,i);
				dTile.setIndex(i);
				break;
				
			}
		}
	}
	public void deleteTile(int index,Tile dTile){
		linearLayout.removeView(dTile);
		tiles[index] = null;
		for(int i = index; i < TILESNUM-1;i++){
			if(tiles[i]==null){
				tiles[i]=tiles[i++];
				tiles[i]= null;
			}
			//Log.d("debug",tiles.toString());
		}
	}
	public ArrayList<Tile> getTiles(){
		ArrayList<Tile> tileList = new ArrayList<Tile>();
		for(int i = 0; i < TILESNUM;i++){
			tileList.add(i, tiles[i]);
		}
 		return tileList;
	}
}
