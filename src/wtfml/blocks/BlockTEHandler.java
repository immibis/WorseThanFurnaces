package wtfml.blocks;

import mc.World;
import wtfml.tiles.WTFTile;

public interface BlockTEHandler {
	public WTFTile createTile();
	
	BlockTEHandler defaultHandler = new BlockTEHandler() {
		@Override public WTFTile createTile() {return null;}
	};
}
