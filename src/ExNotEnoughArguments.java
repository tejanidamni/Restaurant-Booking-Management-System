
public class ExNotEnoughArguments extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExNotEnoughArguments() { super("Insufficient command arguments!"); }
	public ExNotEnoughArguments(String message) { super(message); }

}
