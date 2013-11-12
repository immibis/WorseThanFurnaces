package wtfml.internal;

import mc.Block;
import mc.IBlockAccess;
import mc.Icon;
import mc.IconRegister;
import wtfml.blocks.BlockIconHandler;
import wtfml.blocks.WTFBlockType;

public class WTFBlockWrapperReverse {
	public static WTFBlockType wrap(final Block wraps) {
		WTFBlockType block = WTFBlockWrapperReverseSH.wrap(wraps);
		
		block.iconHandler = new BlockIconHandler() {
			@Override
			public void registerIcons(IconRegister register) {
				wraps.registerIcons(register);
			}
			@Override
			public Icon getItemIcon(int side, int damageValue) {
				return wraps.getIcon(side, damageValue);
			}
			@Override
			public Icon getIcon(IBlockAccess world, int x, int y, int z, int side) {
				return wraps.func_0_IconIBlockAccessIIII(world, x, y, z, side);
			}
		};
		
		block.creativeTab = wraps.func_0_Block_CreativeTab();
		
		return block;
	}
}
