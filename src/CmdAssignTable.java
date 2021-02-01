



public class CmdAssignTable extends RecordedCommand{
	
	Reservation r,r1;
	Table t;
	BookingOffice booking= BookingOffice.getInstance();
	TableBooking tbooking=TableBooking.getInstance();
	int j;
	String[] cmdParts1;
	
	@Override
	public void execute(String[] cmdParts) {
		cmdParts1=cmdParts;
	
		
		try {
			
			if (cmdParts.length < 4 ) {
				
				throw new ExNotEnoughArguments();
			}
			r = booking.outputReservation(cmdParts[1], Integer.parseInt(cmdParts[2]));
			int countOfPersons=r.getNoOfPersons();
			
			for(int i=0;i<cmdParts.length-3;i++) {
				if (!tbooking.isCodeFound(cmdParts[3+i])) {
					j=i;
					throw new ExTableCodeNotFound();
				}
				
			}
			
			if(tbooking.isTableAlreadyAssignedToThisBooking(r)) {
				throw new ExTableAssignedForThisBooking();
			}else {
				for(int i=0;i<cmdParts.length-3;i++) {
					j=i;
					if(tbooking.isTableAlreadyAssignedForOtherBooking(cmdParts[3+i],cmdParts[1])) {
						throw new ExTableAssignedForOtherBooking();
					}
				}
			}
			
			
			int totSeats=0;
			for(int i=0;i<cmdParts.length-3;i++) {
				totSeats+=tbooking.NoOfSeats(cmdParts[3+i]);
			}
			if (countOfPersons>totSeats) {
				throw new ExNotEnoughSeats();
				
			}
				
			for(int i=0;i<cmdParts.length-3;i++) {
				tbooking.addTableReservation(cmdParts[3+i], r, r.getDatedine());
			}	
			
			booking.editReservation(r,new RStateTableAllocated());
			
			
			r1 = booking.outputReservation(cmdParts[1], Integer.parseInt(cmdParts[2]));
			
			for(int i=0;i<cmdParts.length-3;i++) {
				tbooking.EditReservations(cmdParts[i+3], r1, r.getDatedine());
			}
			
			addUndoCommand(this);
			clearRedoList();
			
			System.out.println("Done.");
		} catch (NumberFormatException e) {
			System.out.println("Wrong number format!");			
		} catch (ExBookingNotFound e) {
			System.out.println(e.getMessage());
		} catch (ExNotEnoughArguments e) {
			System.out.println(e.getMessage());	
		}catch (ExDateHasAlreadyPassed e) {
			System.out.println(e.getMessage());
		} catch (ExTableCodeNotFound e) {
			System.out.println("Table code "+ cmdParts[3+j] +" not found!");
		} catch (ExTableAssignedForThisBooking e) {
			System.out.println(e.getMessage());
		} catch (ExTableAssignedForOtherBooking e) {
			System.out.print(String.format("Table %s is already reserved by another booking!",cmdParts[3+j]));
			
		} catch (ExNotEnoughSeats e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	@Override
	public void undoMe() {
		booking.editReservation(tbooking.getReservations(cmdParts1[4],new Day(cmdParts1[1])),new RStatePending());
		for(int i=0;i<cmdParts1.length-3;i++) {
			t=tbooking.getTable(cmdParts1[3+i],new Day(cmdParts1[1]));
			tbooking.removeTableReservation(t);
			
		}
	
		addRedoCommand(this);
		
	}
	@Override
	public void redoMe() {
		
		for(int i=0;i<cmdParts1.length-3;i++) {
			tbooking.addTableReservation(cmdParts1[3+i],r,new Day(cmdParts1[1]));
			
		}	
		
		booking.editReservation(r,new RStateTableAllocated());
		
		for(int i=0;i<cmdParts1.length-3;i++) {
			tbooking.EditReservations(cmdParts1[i+3], r1,new Day(cmdParts1[1]));
		}
		
		addUndoCommand(this);
		
		
	}
	
}
