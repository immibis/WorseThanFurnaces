package wtf;

import wtfml.Mod;
import wtfml.WTFBlockType;
import wtfml.WTFML;

public class ModWTF extends Mod {
	@Override
	public void initBlocks() {
		WTFBlockType blockSensor = new WTFBlockType();
		BlockSensor.init(blockSensor);
		WTFML.addBlock("wtf:block-sensor-new", blockSensor);
	}
}
