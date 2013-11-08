package wtfml;

import mc.CreativeTab;
import mc.Icon;
import mc.IconRegister;
import mc.Material;
import mc.World;

import com.google.common.eventbus.EventBus;

public final class WTFBlockType {

	public final EventBus eventBus = new EventBus();
	
	public BlockIconHandler iconHandler = BlockIconHandler.defaultHandler;
	public BlockMetaHandler metaHandler = BlockMetaHandler.defaultHandler;
	public BlockRedstoneHandler redstoneHandler = BlockRedstoneHandler.defaultHandler;
	
	// XXX notch-hack; canProvidePower in MCP. Remove if possible
	public boolean causesRedstoneUpdates = false;

	public Material material;
	public float hardness;
	public String unlocalizedName;
	public CreativeTab creativeTab;
}
