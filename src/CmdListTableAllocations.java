
public class CmdListTableAllocations implements Command {
	@Override
	public void execute(String[] cmdParts) {
		TableBooking tbooking= TableBooking.getInstance();
		BookingOffice booking= BookingOffice.getInstance();
		
		tbooking.listTableAllocations(cmdParts[1]);
		booking.printNoOfPendingRequestsAndPersons(cmdParts[1]);
	}
	

}
