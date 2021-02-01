
public class ExTableAssignedForThisBooking extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExTableAssignedForThisBooking() { super("Table(s) already assigned for this booking!"); }
	public ExTableAssignedForThisBooking(String message) { super(message); }

}
