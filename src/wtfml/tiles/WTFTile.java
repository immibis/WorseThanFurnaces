package wtfml.tiles;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import mc.World;

/**
 * WTFML version of a tile entity.
 */
public final class WTFTile {
	public World world;
	public int x, y, z;
	
	public List<TileTickHandler> tickers = new CopyOnWriteArrayList<TileTickHandler>();
}
