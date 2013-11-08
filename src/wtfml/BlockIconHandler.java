package wtfml;

import mc.IBlockAccess;
import mc.Icon;
import mc.IconRegister;
import mc.World;

public interface BlockIconHandler {
	public Icon getIcon(IBlockAccess par1afx, int x, int y, int z, int side);
	public void registerIcons(IconRegister register);
	
	BlockIconHandler defaultHandler = new BlockIconHandler() {
		@Override public Icon getIcon(IBlockAccess world, int x, int y, int z, int side) {return null;}
		@Override public void registerIcons(IconRegister register) {}
	};
}
