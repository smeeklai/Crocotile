package tuat.crocotitle.program;

import tuat.crocotitle.quickaction.lib.QuickAction;

import com.example.testproject01.R;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class Division extends Tile {
	ValueBox valueBox1;
	ValueBox valueBox2;
	//int height,width;
	FrameLayout.LayoutParams valueBox1MarginParams;
	FrameLayout.LayoutParams valueBox2MarginParams;
	public Division(Context mContext, int argumentTileTypeId) {
		super(mContext, argumentTileTypeId);
		frame.setImageResource(R.drawable.division);
		tileTypeId = argumentTileTypeId;
		frame.setScaleType(ImageView.ScaleType.FIT_XY);
		valueBox1 = new ValueBox(mContext, this, ValueBox.getValueBoxType(true, true, false, false));
		valueBox2 = new ValueBox(mContext, this, ValueBox.getValueBoxType(true, true, false, false));
		this.addView(frame);
		this.addView(valueBox2);
		this.addView(valueBox1);
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
		frame.setOnClickListener(onClickListener); 
	}
	public void resize(){
		Toast toast2 = Toast.makeText(context, this.getParent().getClass().getSimpleName(), Toast.LENGTH_SHORT);
		toast2.show();
		if(this.getParent().getClass().getSimpleName().equals("ValueBox")){
			valueBox1MarginParams = new FrameLayout.LayoutParams(85,130);
			valueBox1MarginParams.gravity = Gravity.LEFT; 
			valueBox1MarginParams.setMargins(5,15,0,0);
			valueBox1.setLayoutParams(valueBox1MarginParams);
			valueBox2MarginParams = new FrameLayout.LayoutParams(85,130);
			valueBox2MarginParams.gravity = Gravity.RIGHT; 
			valueBox2MarginParams.setMargins(0,15,5,0);
			valueBox2.setLayoutParams(valueBox2MarginParams);
		}
		if(this.getParent().getClass().getSimpleName().equals("LinearLayout")){
			valueBox1MarginParams = new FrameLayout.LayoutParams(200,150);
			valueBox1MarginParams.gravity = Gravity.LEFT; 
			valueBox1MarginParams.setMargins(10,12,0,4);
			valueBox1.setLayoutParams(valueBox1MarginParams);
			valueBox2MarginParams = new FrameLayout.LayoutParams(200,150);
			valueBox2MarginParams.gravity = Gravity.RIGHT; 
			valueBox2MarginParams.setMargins(0,10,12,4);
			valueBox2.setLayoutParams(valueBox2MarginParams);
		}
	}
	public Tile getValueTile(int select){
		if(select == 1){
			return valueBox1.getValueTile();
		}else{
			return valueBox2.getValueTile();
		}
	}
	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {
		// TODO Auto-generated method stub
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
		this.resize();
	}

	public Division(Context mContext, int argumentTileTypeId, AttributeSet attrs) {
		super(mContext, argumentTileTypeId, attrs);
		// TODO Auto-generated constructor stub
	}

	public Division(Context mContext, int argumentTileTypeId,
			AttributeSet attrs, int defStyle) {
		super(mContext, argumentTileTypeId, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

}
