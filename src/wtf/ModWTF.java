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
		WTFML.addBlock("wtf:", new Block(Material.pumpkin) {{func_2_Block_BlockF(0.2F);func_1_Block_BlockString("bed");func_0_Block_Block();func_2_Block_BlockString("stone");func_0_Block_BlockCreativeTab(CreativeTab.brewing);}});
	}
}
