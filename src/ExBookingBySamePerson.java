
public class ExBookingBySamePerson extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExBookingBySamePerson() { super("Booking by the same person for the dining date already exists!"); }
	public ExBookingBySamePerson(String message) { super(message); }

}
