package wtf;

import mc.IBlockAccess;
import mc.Icon;
import wtfml.WTFCommonBlocks;
import wtfml.blocks.BlockIconHandler;
import wtfml.blocks.WTFBlockType;
import wtfml.blocks.util.BlockIconHandlers;

class ShatteredOre {
	static void init(WTFBlockType block, final String type) {
		WTFCommonBlocks.initFallingBlock(block);
		
		block.unlocalizedName = "shattered_ore."+type;
		
		block.iconHandler = BlockIconHandlers.create("wtf:shattered_"+type+"_ore");
	}
}
