package wtfml;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import mc.Block;
import mc.ItemBlock;
import mc.Minecraft;
import mc.RegistryNamespaced;
import wtfml.internal.WTFBlockWrapper;

import com.google.common.eventbus.EventBus;

/**
 * Main interface to WTFML.
 * 
 * Methods starting with h_ are to be called by WTFML's base edits.
 * Mods should not call these.
 *
 */
public class WTFML {
	
	public static final EventBus eventBus = new EventBus();
	
	private static List<Mod> mods = Arrays.<Mod>asList(new wtf.ModWTF());
	
	public static void fatalError(String message, Throwable cause) {
		throw new RuntimeException(message, cause);
	}
	
	//// FILESYSTEM LAYOUT \\\\
	private static File minecraftDir = Minecraft.getMinecraft().mcDir;
	private static File configDir = new File(minecraftDir, "config");
	private static File idMapFile = new File(configDir, "id-map.txt");
	static {
		if(!configDir.isDirectory() && !configDir.mkdirs())
			throw new RuntimeException("Failed to create "+configDir);
	}
	
	//// BLOCK/ITEM REGISTRATION \\\\
	private static Properties idProps;
	private static int getID(String name, boolean isForItem) {
		if(idProps == null) {
			idProps = new Properties();
			try {
				if(idMapFile.exists())
					idProps.load(new FileReader(idMapFile));
			} catch(IOException e) {
				fatalError("Failed to read ID map file", e);
			}
		}
		name = (isForItem ? "I_" : "B_") + name;
		if(idProps.containsKey(name))
			return Integer.parseInt(idProps.getProperty(name));
		
		String nextKey = (isForItem ? "NextI" : "NextB");
		
		int id = Integer.parseInt(idProps.getProperty(nextKey, isForItem ? "31999" : "4095"));
		idProps.setProperty(name, String.valueOf(id));
		idProps.setProperty(nextKey, String.valueOf(id - 1));
		
		return id;
	}
	
	public static void addBlock(String name, Block block) {
		int id = getID(name, false);
		System.out.println("Adding block "+name+" with ID "+id);
		Block.field_4_Block_L.func_0_VIStringObject(id, name, block);
	}
	
	public static void addBlock(String name, WTFBlockType block) {
		addBlock(name, new WTFBlockWrapper(block));
	}
	
	
	
	
	//// BASE EDIT HOOKS \\\\
	public static void h_initBlocks(RegistryNamespaced registry) {
		for(Mod m : mods) m.initBlocks();
	}

	public static void h_initItems(RegistryNamespaced registry) {
		for(Mod m : mods) m.initItems();
		
		try {
			idProps.store(new FileWriter(idMapFile), "");
		} catch (IOException e) {
			fatalError("Failed to write ID map file", e);
		}
	}

	public static ItemBlock h_createItemBlock(Block block) {
		return null;
	}
	
	public static void h_tick(boolean server) {
		eventBus.post(TickEvent.get(server));
	}
}
