package wtfml;

import mc.World;

public final class BlockUpdateEvent {
	public World world;
	public int x, y, z;
	
	//private BlockNeighbourEvent object = new BlockNeighbourEvent();
	public static BlockUpdateEvent get(World world, int x, int y, int z) {
		BlockUpdateEvent object = new BlockUpdateEvent();
		object.world = world;
		object.x = x;
		object.y = y;
		object.z = z;
		return object;
	}
}
