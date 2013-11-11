package wtfml.blocks.util;

import wtfml.blocks.BlockExplosionDropHandler;
import mc.Explosion;

public final class BlockExplosionDropHandlers {
	private BlockExplosionDropHandlers() {}
	
	/**
	 * Creates an explosion drop handler which always drops the block.
	 * @return The explosion drop handler.
	 */
	public static BlockExplosionDropHandler always() {
		return new BlockExplosionDropHandler() {
			@Override
			public boolean shouldDropBlockOnExplosion(Explosion e) {
				return true;
			}
		};
	}
	
	/**
	 * Creates an explosion drop handler which never drops the block.
	 * @return The explosion drop handler.
	 */
	public static BlockExplosionDropHandler never() {
		return new BlockExplosionDropHandler() {
			@Override
			public boolean shouldDropBlockOnExplosion(Explosion e) {
				return false;
			}
		};
	}
}
