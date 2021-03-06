package wtf;

import mc.Block;
import mc.BlockPiston;
import mc.Blocks;
import mc.CreativeTab;
import mc.EntityLiving;
import mc.IBlockAccess;
import mc.Icon;
import mc.IconRegister;
import mc.ItemStack;
import mc.Material;
import mc.World;
import wtfml.RunLater;
import wtfml.RunLater.ZeroGarbageBlockActionHandler;
import wtfml.blocks.BlockIconHandler;
import wtfml.blocks.BlockPlaceEvent;
import wtfml.blocks.BlockRedstoneHandler;
import wtfml.blocks.BlockUpdateEvent;
import wtfml.blocks.WTFBlockType;

import com.google.common.eventbus.Subscribe;

class BlockSensor {
	static void init(WTFBlockType block) {
		final int updateBlockActionID = RunLater.register(new ZeroGarbageBlockActionHandler() {
			@Override
			public void runBlockAction(World world, int x, int y, int z, int actionID, int parameter) {
				world.func_0_World_ZIIIII(x, y, z, getDesiredMeta(world, x, y, z), 1);
			}
		});
		
		block.eventBus.register(new Object() {
			@Subscribe
			public void onBlockNeighbourChange(BlockUpdateEvent evt) {
				int old_meta = evt.world.func_0_IIII(evt.x, evt.y, evt.z);
		        
				int new_meta = getDesiredMeta(evt.world, evt.x, evt.y, evt.z);
				
				if(new_meta != old_meta) {
					RunLater.onBlock(2, evt.world, evt.x, evt.y, evt.z, updateBlockActionID, 0);
				}
			}
			
			@Subscribe
			public void onPlace(BlockPlaceEvent evt) {
				int meta = BlockPiston.func_0_BlockPiston_IWorldIIIEntityLiving(evt.world, evt.x, evt.y, evt.z, evt.player);
				evt.world.func_0_World_ZIIIII(evt.x, evt.y, evt.z, meta, 2);
			}
		});
		
		block.iconHandler = new BlockIconHandler() {
			
			Icon stone, redstone;
			
			@Override
			public Icon getIcon(IBlockAccess w, int x, int y, int z, int side) {
				int meta = w.func_0_IIII(x, y, z) & 7;
				if(meta == side)
					return redstone;
				else
					return stone;
			}
			
			@Override
			public void registerIcons(IconRegister register) {
				redstone = register.registerIcon("redstone_block");
				stone = register.registerIcon("stone");
			}

			@Override
			public Icon getItemIcon(int side, int meta) {
				if(1 == side)
					return redstone;
				else
					return stone;
			}
		};
		
		block.redstoneHandler = new BlockRedstoneHandler() {
			@Override
			public int getWeakEmittedPower(IBlockAccess w, int x, int y, int z, int direction) {
				return (w.func_0_IIII(x, y, z) & 8) != 0 ? 15 : 0;
			}
			
			@Override
			public int getStrongEmittedPower(IBlockAccess w, int x, int y, int z, int direction) {
				return 0;
			}
		};
		
		block.causesRedstoneUpdates = true;
		
		block.hardness = 0.2f;
		block.unlocalizedName = "blocksensor";
		block.creativeTab = CreativeTab.brewing;
	}
	
	static int getDesiredMeta(World par1afn, int par2Int, int par3Int, int par4Int) {
		int old_meta = par1afn.func_0_IIII(par2Int, par3Int, par4Int);
        
		int x = par2Int, y = par3Int, z = par4Int;
		switch(old_meta & 7) {
		case 0: y--; break;
		case 1: y++; break;
		case 2: z--; break;
		case 3: z++; break;
		case 4: x--; break;
		case 5: x++; break;
		}
		
		Block block_under = ((old_meta & 7) == 0 ? Blocks.air : par1afn.getBlockId(par2Int*2-x, par3Int*2-y, par4Int*2-z));
		Block block = par1afn.getBlockId(x, y, z);
		
		return (old_meta & 7) | ((block_under == Blocks.air ? block != Blocks.air && block != Blocks.piston_head : block_under == block) ? 8 : 0);		
	}
}
