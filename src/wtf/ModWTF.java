package wtf;

import mc.Block;
import mc.BlockLog;
import mc.CreativeTab;
import mc.Material;
import mc.RegistryNamespaced;
import wtfml.Mod;

public class ModWTF extends Mod {
	@Override
	public void initBlocks(RegistryNamespaced registry) {
		registry.func_0_VIStringObject(255, "wtf:", new Block(Material.field_2_Material_L) {{func_2_Block_BlockF(0.2F);func_1_Block_BlockString("bed");func_0_Block_Block();func_2_Block_BlockString("bed");func_0_Block_BlockCreativeTab(CreativeTab.brewing);}});
	}
}
