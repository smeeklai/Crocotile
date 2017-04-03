package tuat.crocotitle.program;

import com.example.testproject01.R;

import tuat.crocotitle.quickaction.lib.ActionItem;
import tuat.crocotitle.quickaction.lib.QuickAction;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
public abstract class Tile extends FrameLayout {
	int tileTypeId;
	ImageView frame;
	Context context;
	ActionItem editValue;
	ActionItem deleteTile;
	ActionItem moveTile;
	ActionItem copyTile;
	QuickAction qa;
	ViewParent parent;
	RuledLine ruledLine;
	Tile tile;
	Boolean inValueBox = false;
	int index;
	static final int VOID = -1, INT = 0, FLOAT = 1, CHAR = 2, BOOL = 3;
	int type;
	
	private void ActionItemInit(){
		editValue = new ActionItem();
		editValue.setTitle("Edit Value");
		editValue.setIcon(getResources().getDrawable(R.drawable.edit));  
		editValue.setOnClickListener(new OnClickListener() {  
		   @Override  
		   public void onClick(View v) {
			   
		   }  
		  });  
		deleteTile = new ActionItem();
		deleteTile.setTitle("Delete Tile");
		deleteTile.setIcon(getResources().getDrawable(R.drawable.delete3));
		deleteTile.setOnClickListener(new OnClickListener() {  
		   @Override  
		   public void onClick(View v) {
			   if(inValueBox == true){
				   if(tile != null){
					   ((ValueBox)tile.getParent()).deleteValueTile();
					   tile = null;
				   }
			   }else{
				   if(tile != null){
					   ruledLine = (RuledLine)tile.getParent().getParent();
					   ruledLine.deleteTile(index,tile); 
					   tile = null;
				   }
			   }
		   }  
		  }); 
		moveTile = new ActionItem();
		moveTile.setTitle("Move Tile");
		moveTile.setIcon(getResources().getDrawable(R.drawable.move));
		moveTile.setOnClickListener(new OnClickListener() {  
		   @Override  
		   public void onClick(View v) {
		   }  
		  });
		copyTile = new ActionItem();
		copyTile.setTitle("Copy Tile");
		copyTile.setIcon(getResources().getDrawable(R.drawable.copy));
		copyTile.setOnClickListener(new OnClickListener() {  
		   @Override  
		   public void onClick(View v) {
			   copyTileToClipBoard();
		   }  
		  });
	}
	View.OnClickListener onClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
		    qa = new QuickAction(v);  	      
		    qa.addActionItem(deleteTile);  
		    qa.setAnimStyle(QuickAction.ANIM_AUTO);  	      
		    qa.show();
			// TODO Auto-generated method stub
		}
	};
	public Tile(Context mContext,int argumentTileTypeId){
		super(mContext);
		context = mContext;	
		tileTypeId = argumentTileTypeId;
		tile = this;
		//ruledLine = new RuledLine(mContext);
		frame = new ImageView(mContext);
		ActionItemInit();
		//frame.setOnClickListener(onClickListener);
		type = VOID;
	}
	public Tile(Context mContext,int argumentTileTypeId,AttributeSet attrs){
		super(mContext,attrs);
		tileTypeId = argumentTileTypeId;
		ActionItemInit();
		type = VOID;
	}
	public Tile(Context mContext,int argumentTileTypeId,AttributeSet attrs,int defStyle){
		super(mContext,attrs,defStyle);
		tileTypeId = argumentTileTypeId;
		ActionItemInit();
		type = VOID;
	}
	public int getTileTypeID(){
		return tileTypeId;
	}
	public void setIndex(int mIndex){
		index = mIndex;
	}
	
	
	public void copyTileToClipBoard(){
		Note mNote = ((ProgramActivity)context).getNote();
		mNote.setSelectedTileType(tileTypeId);
		mNote.setClipBoardTile(this);
		/*
		mNote.setClipBoardTile((Tile)this.clone());
		*/
	}
	public void setInValueBoxTrue(){
		inValueBox = true;
	}
	public void setInValueBoxFalse(){
		inValueBox = false;
	}
	public Boolean getInValueBox(){
		return inValueBox;
	}
	public Object clone(){
        try{
            return super.clone();
        }catch(CloneNotSupportedException e){
            throw new InternalError(e.toString());
        }
    }
	public void resize(){
		ProgramActivity pa = (ProgramActivity)context; 
		Note n = pa.getNote();
		n.setFocusable(true);
		n.setFocusableInTouchMode(true);
		n.requestFocus();
	}
	public String getName(){
		return this.getClass().getSimpleName();
	}
	public void setVariable(double getter){}
	public int getType(){
		return type;
	}
	public double exec(){
		return 0.0;
	}
}