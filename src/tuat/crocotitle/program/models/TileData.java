package tuat.crocotitle.program.models;

import java.util.ArrayList;

import tuat.crocotitle.program.Note;

public class TileData {
	int tileTypeId;
	private String name;
	private int		valueInt;
	private float   valueFloat;
	private String  valueChar;
	private boolean valueBoolean;
	ArrayList<RuledLineData> statement =  new ArrayList<RuledLineData>();
	TileData valueBox[] = new TileData[3];
	public TileData(int mTileTypeId) {
		tileTypeId = mTileTypeId;
		if(mTileTypeId == Note.PROGRAM){
			for(int i = 0; i <Note.LINENUMBER; i++){
				statement.add(i, new RuledLineData());
			}
			statement.get(1).setTile(Note.CONSTANTINTTILE);
		}
	}
	public void setValueBox(int i,TileData data){
		valueBox[i] = data;
	}
	public void setValue(int mValue){
		valueInt = mValue;
	}
	public void setValue(float mValue){
		valueFloat = mValue;
	}
	public void setValue(String mValue){
		valueChar = mValue;
	}
	public void setValue(Boolean mValue){
		valueBoolean = mValue;
	}
	public void setName(String mValue){
		name = mValue;
	}
}
