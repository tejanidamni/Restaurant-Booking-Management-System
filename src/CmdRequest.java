
public class CmdRequest extends RecordedCommand {
	
	Reservation r;
	BookingOffice booking= BookingOffice.getInstance();
	TicketOffice ticket = TicketOffice.getInstance();
	ReservedDate t;
	
	@Override
	public void execute(String[] cmdParts) {
		
		try {
			
			
			if (cmdParts.length < 5 ) {
				
				throw new ExNotEnoughArguments();
			}
			r = booking.addReservation(cmdParts[1],cmdParts[2],Integer.parseInt(cmdParts[3]),cmdParts[4]);
			
			
			addUndoCommand(this);
			clearRedoList();
			
			System.out.print(String.format("Done. Ticket code for %s: %d\n",cmdParts[4], r.getTicketNo()));
			
		}catch (ExNotEnoughArguments e) {
			System.out.println(e.getMessage());
		}catch (NumberFormatException e) {
			System.out.println("Wrong number format!");
		}catch (ExDateHasAlreadyPassed e) {
			System.out.println(e.getMessage());
		}catch (ExBookingBySamePerson e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
		
		
	}
	
	@Override
	public void undoMe() {
		
		if (r.getTicketNo()==1) {
			t= ticket.outputReservedDate(r.getDatedine().toString(), 1);
			ticket.removeReservedDate(t);
		}else {
			ticket.changeTicketNo(r.getDatedine().toString(),-1);
		}
		
		booking.removeReservation(r);
		
		
		addRedoCommand(this);
		
	}
	@Override
	public void redoMe() {
		
		if (r.getTicketNo()==1)
				ticket.addReservedDate(t);
		else {
			ticket.changeTicketNo(r.getDatedine().toString(),1);
		}
		booking.addReservation(r);
		
		
		addUndoCommand(this);
		
	}

}
