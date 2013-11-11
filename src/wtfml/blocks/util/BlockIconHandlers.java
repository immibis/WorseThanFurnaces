package wtfml.blocks.util;

import wtfml.blocks.BlockIconHandler;
import mc.IBlockAccess;
import mc.Icon;
import mc.IconRegister;

public final class BlockIconHandlers {
	private BlockIconHandlers() {}
	
	/**
	 * Creates an icon handler which uses the same icon for all sides.
	 * 
	 * @param name The name of the icon to use.
	 * @return The icon handler.
	 */
	public static BlockIconHandler create(final String name) {
		return new BlockIconHandler() {
			private Icon icon;
			
			@Override
			public void registerIcons(IconRegister register) {
				icon = register.registerIcon(name);
			}
			
			@Override
			public Icon getIcon(IBlockAccess world, int x, int y, int z, int side) {
				return icon;
			}
			
			@Override
			public Icon getItemIcon(int side, int damageValue) {
				return icon;
			}
		};
	}
	
}
