package wtf;

import mc.Block;
import mc.BlockLog;
import mc.CreativeTab;
import mc.Material;
import mc.RegistryNamespaced;
import wtfml.Mod;
import wtfml.WTFML;

public class ModWTF extends Mod {
	@Override
	public void initBlocks() {
		WTFML.addBlock("wtf:block-sensor", new BlockBlockSensor());
	}
}
