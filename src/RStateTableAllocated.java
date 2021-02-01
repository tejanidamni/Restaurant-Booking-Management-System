

public class RStateTableAllocated implements RState {
	
	
	public String genMsg(Reservation r) {
		TableBooking b=TableBooking.getInstance();

		return "Table Assigned: " + b.outputTableName(r); 
		
	}
	@Override
	public boolean equals(Object o) {
		if (this==o) {
			return true;
		}
		return false;
	}

	

}
