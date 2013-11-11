package wtf;

import mc.Blocks;
import mc.Material;

import com.google.common.eventbus.Subscribe;

import wtfml.blocks.BlockExplodeEvent;
import wtfml.blocks.BlockRandomTickEvent;
import wtfml.blocks.BlockUpdateEvent;

import wtfml.Mod;
import wtfml.WTFML;
import wtfml.blocks.WTFBlockType;
import wtfml.blocks.util.BlockExplosionDropHandlers;

public class ModWTF extends Mod {
	
	private static void initOreShatterOnExplosions(final WTFBlockType ore, final WTFBlockType shattered) {
		ore.eventBus.register(new Object() {
			@Subscribe
			public void onexplode(BlockExplodeEvent evt) {
				boolean touchingLava = false;
				/**/ if(evt.world.getBlockId(evt.x-1, evt.y, evt.z).func_0_Block_Material() == Material.lava) touchingLava = true;
				else if(evt.world.getBlockId(evt.x+1, evt.y, evt.z).func_0_Block_Material() == Material.lava) touchingLava = true;
				else if(evt.world.getBlockId(evt.x, evt.y-1, evt.z).func_0_Block_Material() == Material.lava) touchingLava = true;
				else if(evt.world.getBlockId(evt.x, evt.y+1, evt.z).func_0_Block_Material() == Material.lava) touchingLava = true;
				else if(evt.world.getBlockId(evt.x, evt.y, evt.z-1).func_0_Block_Material() == Material.lava) touchingLava = true;
				else if(evt.world.getBlockId(evt.x, evt.y, evt.z+1).func_0_Block_Material() == Material.lava) touchingLava = true;
				
				if(touchingLava) {
					// replace with shattered block
					evt.world.func_0_World_ZIIIBlockII(evt.x, evt.y, evt.z, WTFML.getMinecraftBlock(shattered), 0, 3);
					shattered.eventBus.post(BlockUpdateEvent.get(evt.world, evt.x, evt.y, evt.z));	
				} else
					// drop block as usual, but 100% chance
					WTFML.getMinecraftBlock(ore).func_0_VWorldIIIIFI(evt.world, evt.x, evt.y, evt.z, 0, 1.0F /* / evt.explosion.field_0_Explosion_F*/, 0);
			}
		});
		ore.explosionDropHandler = BlockExplosionDropHandlers.never();
	}
	
	@Override
	public void initBlocks() {
		WTFBlockType blockSensor = new WTFBlockType();
		BlockSensor.init(blockSensor);
		WTFML.addBlock("wtf:block-sensor-new", blockSensor);
		
		WTFBlockType blockHole = new WTFBlockType();
		BlockHole.init(blockHole);
		WTFML.addBlock("wtf:block-hole", blockHole);
		
		WTFBlockType blockGen = new WTFBlockType();
		BlockGenerator.init(blockGen);
		WTFML.addBlock("wtf:block-testing-generator", blockGen);
		
		final WTFBlockType shatteredIronOre = new WTFBlockType();
		ShatteredOre.init(shatteredIronOre, "iron");
		WTFML.addBlock("wtf:shattered-iron-ore", shatteredIronOre);
		
		final WTFBlockType shatteredGoldOre = new WTFBlockType();
		ShatteredOre.init(shatteredGoldOre, "gold");
		WTFML.addBlock("wtf:shattered-gold-ore", shatteredGoldOre);
		
		initOreShatterOnExplosions(WTFML.getBlockType("minecraft:iron_ore"), shatteredIronOre);
		initOreShatterOnExplosions(WTFML.getBlockType("minecraft:gold_ore"), shatteredGoldOre);
		
		WTFBlockType stone = WTFML.getBlockType("minecraft:stone");
		stone.explosionDropHandler = BlockExplosionDropHandlers.never();
		stone.eventBus.register(new Object() {
			@Subscribe
			public void onExplode(BlockExplodeEvent evt) {
				evt.world.func_0_World_ZIIIBlockII(evt.x, evt.y, evt.z, Blocks.cobblestone, 0, 3);
			}
		});
	}
}
