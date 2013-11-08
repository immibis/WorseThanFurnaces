package wtfml;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import mc.World;

import com.google.common.eventbus.Subscribe;

public final class RunLater {
	private RunLater() {}
	
	public static interface ZeroGarbageBlockActionHandler {
		public void runBlockAction(World world, int x, int y, int z, int actionID, int parameter);
	}
	
	private static int curTickCount;
	private static class TQI implements Delayed {
		private int atTickCount;
		private int x, y, z;
		private World world;
		private int aid, param;
		@Override
		public int compareTo(Delayed arg0) {
			return atTickCount - ((TQI)arg0).atTickCount;
		}
		@Override
		public long getDelay(TimeUnit arg0) {
			return arg0.convert((atTickCount - curTickCount)*50, TimeUnit.MILLISECONDS);
		}
	}
	private static int nextZGBAH = 0;
	private static Map<Integer, ZeroGarbageBlockActionHandler> zgbah = new HashMap<Integer, RunLater.ZeroGarbageBlockActionHandler>();
	private static DelayQueue<TQI> tickq = new DelayQueue<TQI>();
	static {
		WTFML.eventBus.register(new Object() {
			@Subscribe
			public void onTick(TickEvent evt) {
				curTickCount++;
				for(TQI tqi = tickq.poll(); tqi != null; tqi = tickq.poll()) {
					zgbah.get(tqi.aid).runBlockAction(tqi.world, tqi.x, tqi.y, tqi.z, tqi.aid, tqi.param);
				}
			}
		});
	}
	
	
	public static int register(ZeroGarbageBlockActionHandler handler) {
		int id = nextZGBAH++;
		zgbah.put(id, handler);
		return id;
	}
	
	public static void onBlock(int ticks, World world, int x, int y, int z, int actionID, int parameter) {
		TQI i = new TQI();
		i.atTickCount = curTickCount + ticks;
		i.world = world;
		i.x = x;
		i.y = y;
		i.z = z;
		i.aid = actionID;
		i.param = parameter;
		tickq.add(i);
	}
	
	public static void onBlock(int ticks, World world, int x, int y, int z, Runnable task) {
		throw new UnsupportedOperationException();
	}
}
