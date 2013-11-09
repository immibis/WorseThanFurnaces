package wtf;

import wtfml.WTFCommonBlocks;
import wtfml.blocks.WTFBlockType;

class ShatteredOre {
	static void init(WTFBlockType block) {
		WTFCommonBlocks.initFallingBlock(block);
		
		block.unlocalizedName = "shattered_ore";
	}
}
