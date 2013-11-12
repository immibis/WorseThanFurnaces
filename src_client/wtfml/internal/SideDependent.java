package wtfml.internal;

import java.io.File;
import java.util.List;

import mc.Minecraft;

public final class SideDependent {
	private SideDependent() {}
	
	public static File getMinecraftDir() {
		return Minecraft.getMinecraft().mcDir;
	}

	public static void initResourcePacks(List list) {
		list.add(new WTFResourcePack());
	}
}
