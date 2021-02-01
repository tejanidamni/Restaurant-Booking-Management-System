import java.util.ArrayList;

public class TicketOffice {
	
	private ArrayList<ReservedDate> allReservedDates;	

	private static TicketOffice instance = new TicketOffice(); 
	private TicketOffice() { allReservedDates=new ArrayList<ReservedDate>();}
	public static TicketOffice getInstance(){ return instance; }
	
	public void addReservedDate(ReservedDate r) {
		allReservedDates.add(r);
			
	}
	public void removeReservedDate(ReservedDate r) {
		allReservedDates.remove(r);	
		
	}
	public ReservedDate outputReservedDate(String date, int t) {
		for (ReservedDate d: allReservedDates) {
			if (d.getBusinessday().equals(date) && d.getLastTicketNo()==t) {
				return d;
				
			}
			
		}
		return null;
		
	}
	
	public  boolean DateAlreadyReserved(String date) {
		for (ReservedDate d: allReservedDates) {
			if (d.getBusinessday().equals(date)) {
				return true;
				
			}
		}
		return false;
	}
	
	
	public int getTicketNum(String date) {
		for (ReservedDate d: allReservedDates) {
			if (d.getBusinessday().equals(date)) {
				
				d.setLastTicketNo(1);
			 	return d.getLastTicketNo();
				
			}
		}
		return -1;
		
		
	}
	
	public void changeTicketNo(String date,int n) {
		for (ReservedDate d: allReservedDates) {
			if (d.getBusinessday().equals(date)) {
				d.setLastTicketNo(n);
			}
			
		}
		
		
	}
	
	

}
