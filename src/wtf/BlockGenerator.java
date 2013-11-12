package wtf;

import com.google.common.eventbus.Subscribe;

import mc.Block;
import mc.Blocks;
import mc.IBlockAccess;
import mc.Icon;
import mc.IconRegister;
import mc.Material;
import mc.World;
import wtfml.WTFML;
import wtfml.blocks.BlockIconHandler;
import wtfml.blocks.BlockUpdateEvent;
import wtfml.blocks.WTFBlockType;
import wtfml.blocks.util.BlockIconHandlers;

class BlockGenerator {
	static void init(WTFBlockType block) {
		block.unlocalizedName="blockgen";
		
		block.iconHandler = BlockIconHandlers.mimic(WTFML.getBlockType("minecraft:obsidian").iconHandler);
		
		block.eventBus.register(new Object() {
			@Subscribe
			public void onUpdate(BlockUpdateEvent evt) {
				spawn(evt.world, evt.x-1, evt.y, evt.z);
				spawn(evt.world, evt.x+1, evt.y, evt.z);
				spawn(evt.world, evt.x, evt.y-1, evt.z);
				spawn(evt.world, evt.x, evt.y+1, evt.z);
				spawn(evt.world, evt.x, evt.y, evt.z-1);
				spawn(evt.world, evt.x, evt.y, evt.z+1);
			}
			
			private void spawn(World world, int x, int y, int z) {
				if(world.getBlockId(x, y, z) == Blocks.air) {
					Block[] blocks = new Block[] {
						Blocks.cobblestone,
						Blocks.stone,
						Blocks.dirt,
						Blocks.gravel,
						Blocks.sand,
						Blocks.iron_ore,
						Blocks.gold_ore,
						Blocks.redstone_ore,
						Blocks.diamond_ore,
						Blocks.emerald_ore,
						Blocks.lapis_ore,
						Blocks.coal_ore,
					};
					Block block = blocks[world.rand.nextInt(blocks.length)];
					world.func_0_World_ZIIIBlockII(x, y, z, block, 0, 3);
				}
			}
		});
	}
}
