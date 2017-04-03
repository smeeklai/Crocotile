package tuat.crocotitle.program.models;

public class RuledLineData {
	TileData tiles[] = new TileData[10];
	
	public RuledLineData() {

	}
	
	//This is the test function.
	public void setTile(int TileTypeId){
		tiles[0] = new TileData(TileTypeId); 
	}

}
