package wtfml.internal;

import mc.Block;
import mc.CreativeTab;
import wtfml.blocks.WTFBlockType;

public class WTFBlockWrapperReverse {
	public static WTFBlockType wrap(Block b) {
		WTFBlockType block = WTFBlockWrapperReverseSH.wrap(b);
		
		block.creativeTab = CreativeTab.misc;
		
		return block;
	}
}
