package wtfml;

import java.util.Arrays;
import java.util.List;

import mc.Block;
import mc.ItemBlock;
import mc.RegistryNamespaced;

/**
 * Main interface to WTFML.
 * 
 * Methods starting with h_ are to be called by WTFML's base edits.
 * Mods should not call these.
 *
 */
public class WTFML {
	
	private static List<Mod> mods = Arrays.<Mod>asList(new wtf.ModWTF());
	
	public static void h_initBlocks(RegistryNamespaced registry) {
		for(Mod m : mods) m.initBlocks(registry);
	}

	public static void h_initItems(RegistryNamespaced registry) {
		for(Mod m : mods) m.initItems(registry);
	}

	public static ItemBlock h_createItemBlock(Block block) {
		return null;
	}
}
