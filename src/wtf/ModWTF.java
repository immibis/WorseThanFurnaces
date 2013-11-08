package wtf;

import mc.Block;
import mc.BlockLog;
import mc.CreativeTab;
import mc.Material;
import mc.RegistryNamespaced;
import mc.World;
import wtfml.Mod;
import wtfml.WTFBlockType;
import wtfml.WTFML;
import wtfml.RunLater.ZeroGarbageBlockActionHandler;

public class ModWTF extends Mod {
	@Override
	public void initBlocks() {
		WTFBlockType blockSensor = new WTFBlockType();
		BlockSensor.init(blockSensor);
		WTFML.addBlock("wtf:block-sensor-new", blockSensor);
	}
}
