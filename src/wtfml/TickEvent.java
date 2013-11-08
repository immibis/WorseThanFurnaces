package wtfml;

public final class TickEvent {
	
	public final boolean server;
	
	private TickEvent(boolean s) {server=s;}
	
	private static final TickEvent cl = new TickEvent(false), sv = new TickEvent(true);
	public static TickEvent get(boolean server) {
		return server ? sv : cl;
	}
}
