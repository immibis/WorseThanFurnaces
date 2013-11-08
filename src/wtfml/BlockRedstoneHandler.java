package wtfml;

import mc.EntityLiving;
import mc.IBlockAccess;
import mc.ItemStack;
import mc.World;

public interface BlockRedstoneHandler {
	public int getWeakEmittedPower(IBlockAccess w, int x, int y, int z, int direction);
	public int getStrongEmittedPower(IBlockAccess w, int x, int y, int z, int direction);
	
	BlockRedstoneHandler defaultHandler = new BlockRedstoneHandler() {
		@Override
		public int getStrongEmittedPower(IBlockAccess w, int x, int y, int z, int direction) {
			return 0;
		}
		@Override
		public int getWeakEmittedPower(IBlockAccess w, int x, int y, int z, int direction) {
			return 0;
		}
	};
}
