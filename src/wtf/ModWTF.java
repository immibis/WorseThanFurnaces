package wtf;

import mc.Blocks;

import com.google.common.eventbus.Subscribe;
import wtfml.blocks.BlockRandomTickEvent;

import wtfml.Mod;
import wtfml.WTFML;
import wtfml.blocks.WTFBlockType;

public class ModWTF extends Mod {
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
		ShatteredOre.init(shatteredIronOre);
		WTFML.addBlock("wtf:shattered-iron-ore", shatteredIronOre);
		
		WTFBlockType ironOre = WTFML.getBlockType("minecraft:iron_ore");
		ironOre.hasRandomTicks = true;
		ironOre.eventBus.register(new Object() {
			@Subscribe
			public void onrandomtick(BlockRandomTickEvent evt) {
				evt.world.func_0_World_ZIIIBlockII(evt.x, evt.y, evt.z, WTFML.getMinecraftBlock(shatteredIronOre), 0, 3);
			}
		});
	}
}
