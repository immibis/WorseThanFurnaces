package wtfml.internal;

import java.io.File;
import java.util.List;

public final class SideDependent {
	private SideDependent() {}
	
	public static File getMinecraftDir() {
		return new File(".");
	}

	public static void initResourcePacks(List list) {
	}
}
