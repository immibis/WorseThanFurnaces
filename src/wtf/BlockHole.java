package wtf;

import com.google.common.eventbus.Subscribe;

import mc.Blocks;
import mc.IBlockAccess;
import mc.Icon;
import mc.IconRegister;
import mc.Material;
import wtfml.WTFML;
import wtfml.blocks.BlockIconHandler;
import wtfml.blocks.BlockUpdateEvent;
import wtfml.blocks.WTFBlockType;
import wtfml.blocks.util.BlockIconHandlers;

class BlockHole {
	static void init(WTFBlockType block) {
		block.unlocalizedName="blockhole";
		
		block.iconHandler = BlockIconHandlers.mimic(WTFML.getBlockType("minecraft:obsidian").iconHandler);
		
		block.eventBus.register(new Object() {
			@Subscribe
			public void onUpdate(BlockUpdateEvent evt) {
				evt.world.func_0_World_ZIIIBlockII(evt.x-1, evt.y, evt.z, Blocks.air, 0, 3);
				evt.world.func_0_World_ZIIIBlockII(evt.x+1, evt.y, evt.z, Blocks.air, 0, 3);
				evt.world.func_0_World_ZIIIBlockII(evt.x, evt.y-1, evt.z, Blocks.air, 0, 3);
				evt.world.func_0_World_ZIIIBlockII(evt.x, evt.y+1, evt.z, Blocks.air, 0, 3);
				evt.world.func_0_World_ZIIIBlockII(evt.x, evt.y, evt.z-1, Blocks.air, 0, 3);
				evt.world.func_0_World_ZIIIBlockII(evt.x, evt.y, evt.z+1, Blocks.air, 0, 3);
			}
		});
	}
}
