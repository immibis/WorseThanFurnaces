package wtfml;

import mc.EntityLiving;
import mc.ItemStack;
import mc.World;

public interface BlockMetaHandler {
	// XXX should have an IBlockPlacer as not only living entities can place blocks
	public int getMetaOnPlace(World w, int x, int y, int z, EntityLiving player, ItemStack stack);
	
	BlockMetaHandler defaultHandler = new BlockMetaHandler() {
		@Override
		public int getMetaOnPlace(World w, int x, int y, int z, EntityLiving player, ItemStack stack) {
			return 0;
		}
	};
}
