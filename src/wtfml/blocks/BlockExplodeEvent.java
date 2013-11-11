package wtfml.blocks;

import mc.EntityLiving;
import mc.EntityPlayer;
import mc.Explosion;
import mc.ItemStack;
import mc.World;

public final class BlockExplodeEvent {
	public World world;
	public int x, y, z;
	public Explosion explosion;
	
	public static BlockExplodeEvent get(World world, int x, int y, int z, Explosion explosion) {
		BlockExplodeEvent object = new BlockExplodeEvent();
		object.world = world;
		object.x = x;
		object.y = y;
		object.z = z;
		object.explosion = explosion;
		return object;
	}
}
