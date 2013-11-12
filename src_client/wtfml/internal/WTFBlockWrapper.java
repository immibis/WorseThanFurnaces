package wtfml.internal;

import wtfml.blocks.WTFBlockType;
import mc.Block;
import mc.IBlockAccess;
import mc.Icon;
import mc.IconRegister;
import mc.Material;

class WTFBlockWrapperCL extends WTFBlockWrapperSH {
	protected WTFBlockWrapperCL(WTFBlockType par1avf) {
		super(par1avf);
	}

	@Override
	public void registerIcons(IconRegister par1pt) {
		wraps.iconHandler.registerIcons(par1pt);
	}
	
	@Override
	public Icon func_0_IconIBlockAccessIIII(IBlockAccess par1afx, int par2Int, int par3Int, int par4Int, int par5Int) {
		return wraps.iconHandler.getIcon(par1afx, par2Int, par3Int, par4Int, par5Int);
	}
	
	@Override
	public Icon getIcon(int par1Int, int par2Int) {
		return wraps.iconHandler.getItemIcon(par1Int, par2Int);
	}
}

public class WTFBlockWrapper extends WTFBlockWrapperCL {
	public WTFBlockWrapper(WTFBlockType wraps) {
		super(wraps);
	}
}
