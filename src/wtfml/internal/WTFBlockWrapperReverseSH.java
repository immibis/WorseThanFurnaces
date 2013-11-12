package wtfml.internal;

import java.sql.BatchUpdateException;

import com.google.common.eventbus.Subscribe;

import wtfml.blocks.BlockExplodeEvent;
import wtfml.blocks.BlockExplosionDropHandler;
import wtfml.blocks.BlockIconHandler;
import wtfml.blocks.BlockRandomTickEvent;
import wtfml.blocks.BlockUpdateEvent;
import wtfml.blocks.WTFBlockType;
import mc.Block;
import mc.Blocks;
import mc.CreativeTab;
import mc.Explosion;
import mc.IBlockAccess;
import mc.Icon;
import mc.IconRegister;

class WTFBlockWrapperReverseSH {
	static WTFBlockType wrap(final Block wraps) {
		WTFBlockType block = new WTFBlockType();
		
		if(wraps.hasRandomTicks()) {
			block.hasRandomTicks = true;
		
			block.eventBus.register(new Object() {
				@Subscribe
				public void onRandomUpdate(BlockRandomTickEvent evt) {
					wraps.func_0_VWorldIIIRandom(evt.world, evt.x, evt.y, evt.z, evt.world.rand);
				}
			});
		}
			
		block.eventBus.register(new Object() {
			@Subscribe
			public void onBlockUpdate(BlockUpdateEvent evt) {
				// XXX doesn't pass the correct updating block
				wraps.func_0_VWorldIIIBlock(evt.world, evt.x, evt.y, evt.z, Blocks.stone);
			}
			
			@Subscribe
			public void onExplosion(BlockExplodeEvent evt) {
				wraps.func_0_VWorldIIIExplosion(evt.world, evt.x, evt.y, evt.z, evt.explosion);
			}
		});
		
		block.explosionDropHandler = new BlockExplosionDropHandler() {
			@Override
			public boolean shouldDropBlockOnExplosion(Explosion e) {
				return wraps.func_0_ZExplosion(e);
			}
		};
		
		block.hardness = wraps.func_0_Block_FWorldIII(null, 0, 0, 0);
		block.unlocalizedName = wraps.getUnlocalizedName().substring(5);
		block.material = wraps.func_0_Block_Material();
		
		return block;
	}
}
