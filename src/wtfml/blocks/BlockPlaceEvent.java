package wtfml.blocks;

import mc.EntityLiving;
import mc.EntityPlayer;
import mc.ItemStack;
import mc.World;

public final class BlockPlaceEvent {
	public World world;
	public int x, y, z;
	public EntityLiving player; // XXX should have an IBlockPlacer if we ever want deployers
	public ItemStack stack;
	
	public static BlockPlaceEvent get(World world, int x, int y, int z, EntityLiving par5rh, ItemStack stack) {
		BlockPlaceEvent object = new BlockPlaceEvent();
		object.world = world;
		object.x = x;
		object.y = y;
		object.z = z;
		object.player = par5rh;
		object.stack = stack;
		return object;
	}
}
