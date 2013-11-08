package wtfml;

import mc.CreativeTab;
import mc.Material;

import com.google.common.eventbus.EventBus;

public final class WTFBlockType {

	public final EventBus eventBus = new EventBus();
	
	public BlockIconHandler iconHandler = BlockIconHandler.defaultHandler;
	public BlockMetaHandler metaHandler = BlockMetaHandler.defaultHandler;
	public BlockRedstoneHandler redstoneHandler = BlockRedstoneHandler.defaultHandler;
	
	public float hardness;
	public String unlocalizedName;
	public CreativeTab creativeTab;
	
	// XXX notch-hacks. Remove if possible
	@Deprecated public boolean causesRedstoneUpdates = false; // MCP: canProvidePower
	@Deprecated public Material material;
	
}
