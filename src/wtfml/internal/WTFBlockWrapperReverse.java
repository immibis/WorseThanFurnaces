package wtfml.internal;

import java.sql.BatchUpdateException;

import com.google.common.eventbus.Subscribe;

import wtfml.blocks.BlockIconHandler;
import wtfml.blocks.BlockRandomTickEvent;
import wtfml.blocks.BlockUpdateEvent;
import wtfml.blocks.WTFBlockType;
import mc.Block;
import mc.Blocks;
import mc.IBlockAccess;
import mc.Icon;
import mc.IconRegister;

public class WTFBlockWrapperReverse {
	public static WTFBlockType wrap(final Block wraps) {
		WTFBlockType block = new WTFBlockType();
		
		block.iconHandler = new BlockIconHandler() {
			@Override
			public void registerIcons(IconRegister register) {
				wraps.registerIcons(register);
			}
			@Override
			public Icon getItemIcon(int side, int damageValue) {
				return wraps.getIcon(side, damageValue);
			}
			@Override
			public Icon getIcon(IBlockAccess world, int x, int y, int z, int side) {
				return wraps.func_0_IconIBlockAccessIIII(world, x, y, z, side);
			}
		};
		
		if(wraps.func_6_Block_Z()) {
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
		});
		
		block.hardness = wraps.func_0_Block_FWorldIII(null, 0, 0, 0);
		block.unlocalizedName = wraps.func_2_Block_String().substring(5);
		block.creativeTab = wraps.func_0_Block_CreativeTab();
		block.material = wraps.func_0_Block_Material();
		
		return block;
	}
}
