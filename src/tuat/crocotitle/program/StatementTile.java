package tuat.crocotitle.program;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;

public abstract class StatementTile extends Tile {
	Program statement;
	public StatementTile(Context mContext, int argumentTileTypeId) {
		super(mContext, argumentTileTypeId);
		// TODO Auto-generated constructor stub
	}

	public StatementTile(Context mContext, int argumentTileTypeId,
			AttributeSet attrs) {
		super(mContext, argumentTileTypeId, attrs);
		// TODO Auto-generated constructor stub
	}

	public StatementTile(Context mContext, int argumentTileTypeId,
			AttributeSet attrs, int defStyle) {
		super(mContext, argumentTileTypeId, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	public ArrayList<Tile> getStatementTiles(){
		return statement.getTiles();
	}
}
