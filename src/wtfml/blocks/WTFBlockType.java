package wtfml.blocks;

import mc.CreativeTab;
import mc.Material;

import com.google.common.eventbus.EventBus;

public final class WTFBlockType {

	public final EventBus eventBus = new EventBus();
	
	public BlockIconHandler iconHandler = BlockIconHandler.defaultHandler;
	public BlockMetaHandler metaHandler = BlockMetaHandler.defaultHandler;
	public BlockRedstoneHandler redstoneHandler = BlockRedstoneHandler.defaultHandler;
	public BlockTEHandler tileEntityHandler = BlockTEHandler.defaultHandler;
	
	/** Only takes effect at block registration time. */
	public float hardness = 0.2f;
	/** This gets "tile." prepended and ".name" appended. Only takes effect at block registration time. */
	public String unlocalizedName = "bed";
	/** Only takes effect at block registration time. */
	public CreativeTab creativeTab = CreativeTab.misc;
	/** Only takes effect at block registration time. */
	public boolean hasTileEntities = false;
	
	
	// XXX notch-hacks. Remove if possible
	@Deprecated public boolean causesRedstoneUpdates = false; // MCP: canProvidePower
	@Deprecated public Material material = Material.stone;
	
}
