
public class ExBookingNotFound extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExBookingNotFound() { super("Booking Not Found!"); }
	public ExBookingNotFound(String message) { super(message); }

}
