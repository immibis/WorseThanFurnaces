package wtf;

import java.util.Random;

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
import mc.TileDispenser;
import mc.World;

public class BlockBlockSensor extends Block {

	public BlockBlockSensor() {
		super(Material.stone);
		func_2_Block_BlockF(0.2F);
		func_1_Block_BlockString("bed");
		func_0_Block_Block();
		func_2_Block_BlockString("stone");
		func_0_Block_BlockCreativeTab(CreativeTab.brewing);
	}
	
	@Override
	public void registerIcons(IconRegister par1pt) {
	}
	
	@Override
	public Icon getIcon(int par1Int, int par2Int) {
		if(par1Int == par2Int)
			return Blocks.redstone_block.func_0_Block_IconI(0);
		else
			return Blocks.stone.func_0_Block_IconI(0);
	}
	
	public void func_0_VWorldIIIEntityLivingItemStack(World par1afn, int par2Int, int par3Int, int par4Int, EntityLiving par5rh, ItemStack par6abp)
    {
		// set metadata to side, when placed
        int var7 = BlockPiston.func_0_BlockPiston_IWorldIIIEntityLiving(par1afn, par2Int, par3Int, par4Int, par5rh);
        par1afn.func_0_World_ZIIIII(par2Int, par3Int, par4Int, var7, 2);
    }
	
	private int getDesiredMeta(World par1afn, int par2Int, int par3Int, int par4Int) {
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
		
		Block block = par1afn.getBlockId(x, y, z);
		
		return (old_meta & 7) | (block != Blocks.air && block != Blocks.piston_head ? 8 : 0);		
	}
	
	public void func_0_VWorldIIIBlock(World par1afn, int par2Int, int par3Int, int par4Int, Block par5ahu)
    {
		// onNeighbourBlockChange
		int old_meta = par1afn.func_0_IIII(par2Int, par3Int, par4Int);
        
		int new_meta = getDesiredMeta(par1afn, par2Int, par3Int, par4Int);
		
		if(new_meta != old_meta) {
			// schedule delayed block tick
			par1afn.func_0_VIIIBlockI(par2Int, par3Int, par4Int, this, 2);
		}
    }
	
	// on delayed block tick
	public void func_0_VWorldIIIRandom(World par1afn, int par2Int, int par3Int, int par4Int, Random par5Random)
    {
        if (!par1afn.isRemote)
        {
        	// update metadata with block update
            par1afn.func_0_World_ZIIIII(par2Int, par3Int, par4Int, getDesiredMeta(par1afn, par2Int, par3Int, par4Int), 1);
        }
    }
	
	// canProvidePower
	public boolean func_8_Z()
    {
        return true;
    }
	
	// get redstone power level (weak or strong? idk)
	public int func_0_IIBlockAccessIIII(IBlockAccess par1afx, int par2Int, int par3Int, int par4Int, int par5Int) {
		return (par1afx.func_0_IIII(par2Int, par3Int, par4Int) & 8) != 0 ? 15 : 0;
	}
}
