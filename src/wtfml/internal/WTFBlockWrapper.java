package wtfml.internal;

import java.util.Random;

import mc.Block;
import mc.EntityLiving;
import mc.Explosion;
import mc.IBlockAccess;
import mc.Icon;
import mc.IconRegister;
import mc.ItemStack;
import mc.World;
import wtfml.blocks.BlockExplodeEvent;
import wtfml.blocks.BlockPlaceEvent;
import wtfml.blocks.BlockRandomTickEvent;
import wtfml.blocks.BlockUpdateEvent;
import wtfml.blocks.WTFBlockType;

public class WTFBlockWrapper extends Block {

	private WTFBlockType wraps;
	
	public WTFBlockWrapper(WTFBlockType wraps) {
		super(wraps.material);
		this.wraps = wraps;
		func_0_Block_BlockCreativeTab(wraps.creativeTab);
		func_2_Block_BlockF(wraps.hardness);
		func_1_Block_BlockString(wraps.unlocalizedName);
	}
	
	@Override
	public void registerIcons(IconRegister par1pt) {
		wraps.iconHandler.registerIcons(par1pt);
	}
	
	@Override
	public Icon func_0_IconIBlockAccessIIII(IBlockAccess par1afx, int par2Int, int par3Int, int par4Int, int par5Int) {
		return wraps.iconHandler.getIcon(par1afx, par2Int, par3Int, par4Int, par5Int);
	}
	
	@Override
	public Icon getIcon(int par1Int, int par2Int) {
		return wraps.iconHandler.getItemIcon(par1Int, par2Int);
	}
	
	public void func_0_VWorldIIIEntityLivingItemStack(World par1afn, int par2Int, int par3Int, int par4Int, EntityLiving par5rh, ItemStack par6abp) {
		wraps.eventBus.post(BlockPlaceEvent.get(par1afn, par2Int, par3Int, par4Int, par5rh, par6abp));
	}
	
	public boolean func_8_Z() {
		return wraps.causesRedstoneUpdates;
	}
	
	@Override
	public int func_0_IIBlockAccessIIII(IBlockAccess par1afx, int par2Int, int par3Int, int par4Int, int par5Int) {
		return wraps.redstoneHandler.getWeakEmittedPower(par1afx, par2Int, par3Int, par4Int, par5Int^1);
	}
	
	@Override
	public int func_1_IIBlockAccessIIII(IBlockAccess par1afx, int par2Int, int par3Int, int par4Int, int par5Int) {
		return wraps.redstoneHandler.getStrongEmittedPower(par1afx, par2Int, par3Int, par4Int, par5Int^1);
	}
	
	@Override
	public void func_0_VWorldIIIBlock(World par1afn, int par2Int, int par3Int, int par4Int, Block par5ahu) {
		wraps.eventBus.post(BlockUpdateEvent.get(par1afn, par2Int, par3Int, par4Int));
	}
	
	@Override
	public void func_0_VWorldIIIRandom(World par1afn, int par2Int, int par3Int, int par4Int, Random par5Random) {
		wraps.eventBus.post(BlockRandomTickEvent.get(par1afn, par2Int, par3Int, par4Int));
	}
	
	@Override
	public boolean func_6_Block_Z() {
		return wraps.hasRandomTicks;
	}
	
	@Override
	public boolean func_0_ZExplosion(Explosion par1afi) {
		return wraps.explosionDropHandler.shouldDropBlockOnExplosion(par1afi);
	}
	
	@Override
	public void func_0_VWorldIIIExplosion(World par1afn, int par2Int, int par3Int, int par4Int, Explosion par5afi) {
		wraps.eventBus.post(BlockExplodeEvent.get(par1afn, par2Int, par3Int, par4Int, par5afi));
	}

	public WTFBlockType getWrapped() {
		return wraps;
	}
}
