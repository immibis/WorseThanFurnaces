package wtf.oreproc;

import mc.BlockFalling;
import mc.EntityFallingSand;
import mc.Material;
import mc.World;

import com.google.common.eventbus.Subscribe;

import wtfml.RunLater;
import wtfml.RunLater.ZeroGarbageBlockActionHandler;
import wtfml.blocks.BlockPlaceEvent;
import wtfml.blocks.BlockUpdateEvent;
import wtfml.blocks.WTFBlockType;
import wtfml.blocks.util.BlockIconHandlers;

/**
 * Stone dust.
 * Like sand, but floats in water.
 */
class StoneDust {
	private static final int blockFallAction = RunLater.register(new ZeroGarbageBlockActionHandler() {
		@Override
		public void runBlockAction(World world, int x, int y, int z, int actionID, int parameter) {
			if(canFallUp(world, x, y, z)) {
				
			} else if(canFall(world, x, y, z)) {
				EntityFallingSand var9 = new EntityFallingSand(world, x+0.5, y+0.5, z+0.5, world.getBlockId(x, y, z), world.func_0_IIII(x, y, z));
				world.spawnEntity(var9);
			}
		}
	});
	private static boolean canFall(World w, int x, int y, int z) {
		return !w.isRemote && y > 0 && BlockFalling.func_0_BlockFalling_ZWorldIII(w, x, y-1, z);
	}
	private static boolean canFallUp(World w, int x, int y, int z) {
		return !w.isRemote && y < 256 && w.getBlockId(x, y+1, z).func_0_Block_Material() == Material.water;
	}
	
	public static void init(WTFBlockType block) {
		block.unlocalizedName = "wtf.stone_dust";
		
		block.iconHandler = BlockIconHandlers.create("wtf:stone_dust");
		
		block.eventBus.register(new Object() {
			@Subscribe
			public void onPlaced(BlockPlaceEvent e) {
				if(canFallUp(e.world, e.x, e.y, e.z))
					RunLater.onBlock(2, e.world, e.x, e.y, e.z, blockFallAction, 0);
				else if(canFall(e.world, e.x, e.y, e.z))
					RunLater.onBlock(2, e.world, e.x, e.y, e.z, blockFallAction, 0);
			}
			@Subscribe
			public void onUpdate(BlockUpdateEvent e) {
				if(canFallUp(e.world, e.x, e.y, e.z))
					RunLater.onBlock(2, e.world, e.x, e.y, e.z, blockFallAction, 0);
				else if(canFall(e.world, e.x, e.y, e.z))
					RunLater.onBlock(2, e.world, e.x, e.y, e.z, blockFallAction, 0);
			}
		});
	}
}
