package wtfml.blocks;

import mc.Explosion;

public interface BlockExplosionDropHandler {
	public boolean shouldDropBlockOnExplosion(Explosion e);
	
	BlockExplosionDropHandler defaultHandler = new BlockExplosionDropHandler() {
		@Override 
		public boolean shouldDropBlockOnExplosion(Explosion e) {
			return true;
		}
	};
}
