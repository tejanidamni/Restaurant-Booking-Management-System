
import java.util.ArrayList;

public class CmdCancel extends RecordedCommand {
	
	Reservation r;
	BookingOffice booking= BookingOffice.getInstance();
	TableBooking tbooking=TableBooking.getInstance();
	ArrayList<Table> tables;
	
	
	public void execute(String[] cmdParts) {
		try {
			
			if (cmdParts.length < 3 ) {
				
				throw new ExNotEnoughArguments();
			}
			r = booking.outputReservation(cmdParts[1], Integer.parseInt(cmdParts[2]));
			booking.removeReservation(r);
			tables =tbooking.outputTables(r);
			for(Table t: tables) {
				tbooking.removeTableReservation(t);
			}
			
			addUndoCommand(this);
			clearRedoList();
			
			System.out.println("Done.");
			
		}catch (ExNotEnoughArguments e) {
			System.out.println(e.getMessage());
		}catch (NumberFormatException e) {
			System.out.println("Wrong number format!");
		}catch (ExDateHasAlreadyPassed e) {
			System.out.println(e.getMessage());
		}catch (ExBookingNotFound e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	@Override
	public void undoMe() {
		booking.addReservation(r);
		for(Table t: tables) {
			tbooking.addTableReservation(t);
		}
		addRedoCommand(this);
		
	}
	@Override
	public void redoMe() {
		
		booking.removeReservation(r);
		tables =tbooking.outputTables(r);
		for(Table t: tables) {
			tbooking.removeTableReservation(t);
		}
		addUndoCommand(this);
	}


}
