package wtf.oreproc;

import mc.BlockFalling;
import mc.EntityFallingSand;
import mc.World;

import com.google.common.eventbus.Subscribe;

import wtfml.RunLater;
import wtfml.RunLater.ZeroGarbageBlockActionHandler;
import wtfml.blocks.BlockPlaceEvent;
import wtfml.blocks.BlockUpdateEvent;
import wtfml.blocks.WTFBlockType;

class StoneDust {
	public static void init(WTFBlockType block) {
		private static final int blockFallAction = RunLater.register(new ZeroGarbageBlockActionHandler() {
			@Override
			public void runBlockAction(World world, int x, int y, int z, int actionID, int parameter) {
				if(canFall(world, x, y, z)) {
					EntityFallingSand var9 = new EntityFallingSand(world, x+0.5, y+0.5, z+0.5, world.getBlockId(x, y, z), world.func_0_IIII(x, y, z));
					world.spawnEntity(var9);
				}
			}
		});
		private static boolean canFall(World w, int x, int y, int z) {
			return !w.isRemote && y > 0 && BlockFalling.func_0_BlockFalling_ZWorldIII(w, x, y-1, z);
		}
		public static void initFallingBlock(WTFBlockType block) {
			block.eventBus.register(new Object() {
				@Subscribe
				public void onPlaced(BlockPlaceEvent e) {
					if(canFall(e.world, e.x, e.y, e.z))
						RunLater.onBlock(2, e.world, e.x, e.y, e.z, blockFallAction, 0);
				}
				@Subscribe
				public void onUpdate(BlockUpdateEvent e) {
					if(canFall(e.world, e.x, e.y, e.z))
						RunLater.onBlock(2, e.world, e.x, e.y, e.z, blockFallAction, 0);
				}
			});
		}
	}
}
