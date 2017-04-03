package tuat.crocotitle.program;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Program extends Tile {
	//public ArrayList<Tile> statementTiles = new ArrayList<Tile>();
	private LinearLayout innerLinearLayout;
	//private final int LINENUMBER = 10;
	private Note note;
	private ArrayList <RuledLine> ruledLines = new ArrayList<RuledLine>();
	public Program(Context mContext, int argumentTileTypeId,int lineNum) {
		super(mContext, argumentTileTypeId);
		innerLinearLayout = new LinearLayout(mContext);
		innerLinearLayout.setOrientation(LinearLayout.VERTICAL);
		ViewGroup.LayoutParams lineParams = new ViewGroup.LayoutParams(Note.WIDTH, ViewGroup.LayoutParams.WRAP_CONTENT);
		for(int i = 0; i < lineNum;i++){
			ruledLines.add(i,new RuledLine(context));
			innerLinearLayout.addView(ruledLines.get(i),i,lineParams);
			//innerLinearLayout.addView(ruledLines.get(i),i);
			//linearLayout.addView(ruledLines.get(i),i,mRuledLine.getLayoutParams());
		}
		this.addView(innerLinearLayout);
		// TODO Auto-generated constructor stub
		type = VOID;
	}

	public Program(Context mContext, int argumentTileTypeId, AttributeSet attrs) {
		super(mContext, argumentTileTypeId, attrs);
		// TODO Auto-generated constructor stub
		type = VOID;
	}

	public Program(Context mContext, int argumentTileTypeId,
			AttributeSet attrs, int defStyle) {
		super(mContext, argumentTileTypeId, attrs, defStyle);
		// TODO Auto-generated constructor stub
		type = VOID;
	}
	@Override
	public void resize(){
		for(int i = 0;i < ruledLines.size();i++){
			RuledLine r = ruledLines.get(i);
			r.resize();
		}
	}
	/*
	public void addViewToLinearLayout(int ){
		this.innerLinearLayout.addView(mView);
	}*/
	public void addViewToLinearLayout(View mView,ViewGroup.LayoutParams params){
		this.innerLinearLayout.addView(mView,params);
	}
	public void setOrientation(int param){
		innerLinearLayout.setOrientation(param);
	}
	public ArrayList<Tile> getRuledLineTile(int i){
		RuledLine rl = ruledLines.get(i);
		return rl.getTiles();
	}
	public ArrayList<Tile> getTiles(){
		ArrayList<Tile> result = new ArrayList<Tile>();
		for(int i=0;i<ruledLines.size();i++){
			RuledLine rl = ruledLines.get(i);
			ArrayList<Tile> tl = rl.getTiles();
			for(int j = 0; j < tl.size();j++){
				result.add(tl.get(j));
			}
		}
		return result;
	}
	public double exec(){
		Tile oldTile,nowTile;
		double returnValue;
		for(int i=0;i<ruledLines.size();i++){
			RuledLine rl = ruledLines.get(i);
			ArrayList<Tile> tl = rl.getTiles();
			oldTile = this;
			for(int j = 0;j<tl.size();j++){
				nowTile = tl.get(j);
				if(nowTile == null)break;
				returnValue = nowTile.exec();
				if(nowTile.getName().equals("AssignmentTile")){
					oldTile.setVariable(returnValue);
				}
				oldTile = nowTile;
			}
		}
		return 0;
	}
}
