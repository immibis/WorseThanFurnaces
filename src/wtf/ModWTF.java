package wtf;

import wtfml.Mod;
import wtfml.WTFML;
import wtfml.blocks.WTFBlockType;

public class ModWTF extends Mod {
	@Override
	public void initBlocks() {
		WTFBlockType blockSensor = new WTFBlockType();
		BlockSensor.init(blockSensor);
		WTFML.addBlock("wtf:block-sensor-new", blockSensor);
		
		WTFBlockType blockHole = new WTFBlockType();
		BlockHole.init(blockHole);
		WTFML.addBlock("wtf:block-hole", blockHole);
		
		WTFBlockType blockGen = new WTFBlockType();
		BlockGenerator.init(blockGen);
		WTFML.addBlock("wtf:block-testing-generator", blockGen);
	}
}
