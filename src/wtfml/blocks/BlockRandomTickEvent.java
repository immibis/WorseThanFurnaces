package wtfml.blocks;

import mc.World;

public final class BlockRandomTickEvent {
	public World world;
	public int x, y, z;
	
	public static BlockRandomTickEvent get(World world, int x, int y, int z) {
		BlockRandomTickEvent object = new BlockRandomTickEvent();
		object.world = world;
		object.x = x;
		object.y = y;
		object.z = z;
		return object;
	}
}
