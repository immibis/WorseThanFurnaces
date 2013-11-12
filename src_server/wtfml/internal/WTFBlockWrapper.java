package wtfml.internal;

import wtfml.blocks.WTFBlockType;
import mc.Block;
import mc.Material;

class WTFBlockWrapperSV extends WTFBlockWrapperSH {

	protected WTFBlockWrapperSV(WTFBlockType wraps) {
		super(wraps);
	}
	
	
}


public class WTFBlockWrapper extends WTFBlockWrapperSV {
	public WTFBlockWrapper(WTFBlockType wraps) {
		super(wraps);
	}
}