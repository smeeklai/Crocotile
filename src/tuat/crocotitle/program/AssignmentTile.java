package tuat.crocotitle.program;

import com.example.testproject01.R;

import tuat.crocotitle.quickaction.lib.QuickAction;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.FrameLayout.LayoutParams;

public class AssignmentTile extends Tile {
	ImageView frame;
	ValueBox valueBox;
	public AssignmentTile(Context mContext, int argumentTileTypeId) {
		super(mContext, argumentTileTypeId);
		valueBox = new ValueBox(mContext,this,ValueBox.getValueBoxType(true,true,true,true));
		frame = new ImageView(mContext);
		frame.setImageResource(R.drawable.assignment);
		onClickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
			    QuickAction qa = new QuickAction(v); 
			    qa.addActionItem(deleteTile);
			    qa.addActionItem(moveTile);
			    qa.addActionItem(copyTile);
			    qa.setAnimStyle(QuickAction.ANIM_AUTO);  	      
			    qa.show();  
				// TODO Auto-generated method stub
			}
		};
		this.setOnClickListener(onClickListener); 
		/*
		MarginLayoutParams marginParams = (MarginLayoutParams)footerImage.getLayoutParams();
		marginParams.setMargins(0,0,30,0);
		footerImage.setLayoutParams(marginParams);
		*/
		
		//headerImage.set
		//frameLinearLayout = new LinearLayout(mContext);
		/*
		frameLinearLayout.setOrientation(LinearLayout.VERTICAL);
		frameLinearLayout.addView(headerImage);
		frameLinearLayout.addView(statement);
		frameLinearLayout.addView(footerImage);
		this.addView(frameLinearLayout);
		*/
		//headImage = 
		//this.addView(child);
		// TODO Auto-generated constructor stub
		this.addView(frame);	
		frame.setScaleType(ImageView.ScaleType.FIT_XY);
		this.addView(valueBox);
		// TODO Auto-generated constructor stub
		type = VOID;
	}

	public AssignmentTile(Context mContext, int argumentTileTypeId,
			AttributeSet attrs) {
		super(mContext, argumentTileTypeId, attrs);
		// TODO Auto-generated constructor stub
		type = VOID;
	}

	public AssignmentTile(Context mContext, int argumentTileTypeId,
			AttributeSet attrs, int defStyle) {
		super(mContext, argumentTileTypeId, attrs, defStyle);
		// TODO Auto-generated constructor stub
		type = VOID;
	}
	public void setValueBox(Tile mTile){
		valueBox.setValueTile(mTile);
	}
	public Tile getValueBox(){
		return valueBox.getValueTile();
	}
	@Override
	public void resize(){
		int height = this.getHeight();
		int width = this.getWidth();
		FrameLayout.LayoutParams headerMarginParams = new FrameLayout.LayoutParams(200,150);
		headerMarginParams.gravity = Gravity.LEFT; 
		headerMarginParams.setMargins(168,34,0,0);
		Log.d("debug", String.valueOf(this.getWidth()));
		valueBox.setLayoutParams(headerMarginParams);
	}
	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {
		// TODO Auto-generated method stub
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
		this.resize();
	}
	public double exec(){
		Tile tl;
		tl = valueBox.getValueTile();
		type = tl.getType();
		return tl.exec();
	}
}
