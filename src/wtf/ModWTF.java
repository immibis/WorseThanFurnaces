package wtf;

import mc.Blocks;
import mc.Material;

import com.google.common.eventbus.Subscribe;

import wtf.electricity.Cable;
import wtf.oreproc.OreProcessing;
import wtfml.blocks.BlockExplodeEvent;
import wtfml.blocks.BlockRandomTickEvent;
import wtfml.blocks.BlockUpdateEvent;

import wtfml.Mod;
import wtfml.WTFML;
import wtfml.blocks.WTFBlockType;
import wtfml.blocks.util.BlockExplosionDropHandlers;

public class ModWTF extends Mod {
	
	WTFBlockType blockSensor = new WTFBlockType();
	WTFBlockType blockHole = new WTFBlockType();
	WTFBlockType blockGen = new WTFBlockType();
	
	@Override
	public void init() {
		BlockSensor.init(blockSensor);
		WTFML.addBlock("wtf:block-sensor-new", blockSensor);
		
		BlockHole.init(blockHole);
		WTFML.addBlock("wtf:block-hole", blockHole);
		
		BlockGenerator.init(blockGen);
		WTFML.addBlock("wtf:block-testing-generator", blockGen);
		
		OreProcessing.init();
		
		Cable.init();
		
		// stone explodes into cobblestone
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
