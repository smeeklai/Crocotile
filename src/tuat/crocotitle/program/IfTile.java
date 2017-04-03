package tuat.crocotitle.program;

import java.util.ArrayList;

import com.example.testproject01.R;

import tuat.crocotitle.quickaction.lib.QuickAction;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.FrameLayout.LayoutParams;

public class IfTile extends StatementTile {
	ImageView frame;
	ImageView headerImage;
	ImageView footerImage;
	LinearLayout frameLinearLayout;
	FrameLayout statementFrame;
	FrameLayout headerFrame;
	ValueBox valueBox;
	
	public IfTile(Context mContext, int argumentTileTypeId) {
		super(mContext, argumentTileTypeId);
		headerImage = new ImageView(mContext);
		statementFrame = new FrameLayout(mContext);
		headerFrame = new FrameLayout(mContext);
		valueBox = new ValueBox(mContext,this,ValueBox.getValueBoxType(false,false,false,true));
		footerImage = new ImageView(mContext);
		headerImage.setImageResource(R.drawable.ifheader);
		frame = new ImageView(mContext);
		frame.setImageResource(R.drawable.repeatframe);
		headerImage.setScaleType(ImageView.ScaleType.FIT_START);
		footerImage.setImageResource(R.drawable.repeatfooter);
		footerImage.setScaleType(ImageView.ScaleType.FIT_START);
		frameLinearLayout = new LinearLayout(mContext);
		frameLinearLayout.setOrientation(LinearLayout.VERTICAL);
		statement = new Program(mContext, argumentTileTypeId,10);
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
		headerImage.setOnClickListener(onClickListener); 
		/*
		MarginLayoutParams marginParams = (MarginLayoutParams)footerImage.getLayoutParams();
		marginParams.setMargins(0,0,30,0);
		footerImage.setLayoutParams(marginParams);
		*/
		FrameLayout.LayoutParams statementMarginParams = new FrameLayout.LayoutParams(-2, -2);
		statementMarginParams.gravity = Gravity.LEFT; 
		statementMarginParams.setMargins(110,0,0,0);//110
		statement.setLayoutParams(statementMarginParams);
		FrameLayout.LayoutParams frameMarginParams = new FrameLayout.LayoutParams(18,LayoutParams.MATCH_PARENT);
		frameMarginParams.gravity = Gravity.LEFT; 
		frameMarginParams.setMargins(92,0,0,0);
		frame.setLayoutParams(frameMarginParams);
		FrameLayout.LayoutParams headerMarginParams = new FrameLayout.LayoutParams(-2, -2);
		headerMarginParams.gravity = Gravity.LEFT; 
		headerMarginParams.setMargins(113,2,0,0);
		valueBox.setLayoutParams(headerMarginParams);
		
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
		headerFrame.addView(headerImage);
		headerFrame.addView(valueBox);
		frameLinearLayout.addView(headerFrame);
		statementFrame.addView(frame);
		statementFrame.addView(statement);
		frameLinearLayout.addView(statementFrame);
		frameLinearLayout.addView(footerImage);
		this.addView(frameLinearLayout);
		frame.setScaleType(ImageView.ScaleType.FIT_XY);
	}

	public IfTile(Context mContext, int argumentTileTypeId, AttributeSet attrs) {
		super(mContext, argumentTileTypeId, attrs);
		// TODO Auto-generated constructor stub
	}

	public IfTile(Context mContext, int argumentTileTypeId, AttributeSet attrs,
			int defStyle) {
		super(mContext, argumentTileTypeId, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	public void setValueBox(Tile mTile){
		valueBox.setValueTile(mTile);
	}
	public Tile getValueBox(){
		return valueBox.getValueTile();
	}
}
