package wtfml.blocks;

import mc.IBlockAccess;
import mc.Icon;
import mc.IconRegister;

public interface BlockIconHandler {
	public Icon getIcon(IBlockAccess world, int x, int y, int z, int side);
	public Icon getItemIcon(int side, int damageValue);
	public void registerIcons(IconRegister register);
	
	BlockIconHandler defaultHandler = new BlockIconHandler() {
		@Override public Icon getIcon(IBlockAccess world, int x, int y, int z, int side) {return null;}
		@Override public Icon getItemIcon(int side, int damageValue) {return null;}
		@Override public void registerIcons(IconRegister register) {}
	};
}
