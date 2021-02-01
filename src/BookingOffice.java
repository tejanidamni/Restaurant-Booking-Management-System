
import java.util.ArrayList;
import java.util.Collections; //Provides sorting

public class BookingOffice{
	private ArrayList<Reservation> allReservations;	

	private static BookingOffice instance = new BookingOffice(); 
	private BookingOffice() { allReservations=new ArrayList<Reservation>();}
	public static BookingOffice getInstance(){ return instance; }

	public Reservation addReservation(String guestName, String phoneNumber, int totPersons, String sDateDine) throws ExDateHasAlreadyPassed, ExBookingBySamePerson  
	{
		
			if(Day.ifGreater(sDateDine, SystemDate.getInstance().toString())==false) {
				throw new ExDateHasAlreadyPassed();
				
			}
			for (Reservation r: allReservations) {
				if(r.getName().equals(guestName) && r.getPhone().equals(phoneNumber) && r.getDatedine().toString().equals(sDateDine)) {
					throw new ExBookingBySamePerson();
				}
			}
			Reservation r = new Reservation(guestName,phoneNumber,totPersons,sDateDine);
			allReservations.add(r);
			Collections.sort(allReservations); // allReservations
			return r; //Why return?  Ans: You'll see that it is useful in CmdRequest.java in Q2. 
		
	}

	public void listReservations()
	{
		System.out.println(Reservation.getListingHeader()); // Reservation
		for (Reservation r: allReservations)
			System.out.println(r); // r or r.toString()
	}
	
	public void removeReservation(Reservation r) {
		allReservations.remove(r);
		
	}
	
	public void addReservation(Reservation r) {
		
		allReservations.add(r);
		Collections.sort(allReservations);
		
	}
	public void editReservation(Reservation r1, RState s) {
		for (Reservation r: allReservations) {
			if (r.equals(r1)) {
				r.setStatus(s);
			}
			
		}
		
	}
	
	public Reservation outputReservation(String date, int ticketNo) throws ExBookingNotFound, ExDateHasAlreadyPassed{
		
		if(Day.ifGreater(date, SystemDate.getInstance().toString())==false) {
			throw new ExDateHasAlreadyPassed();
			
		}
		
		for (Reservation r: allReservations) {
			if(r.getDatedine().toString().equals(date) && r.getTicketNo() == ticketNo )
				return r;
		}
		throw new ExBookingNotFound();
		
	}
	
	public void printNoOfPendingRequestsAndPersons(String date){
		
		int countOfPending=0;
		int NoOfPeople=0;
		for (Reservation r: allReservations) {
			
			if (r.getDatedine().toString().equals(date)&&r.getStatus().equals(new RStatePending())){
				
				countOfPending+=1;
				NoOfPeople+=r.getNoOfPersons();
			}
				
		}
		System.out.print(String.format("Total number of pending requests = %d (Total number of persons = %d) \n", countOfPending, NoOfPeople));
		
		
		
	}
	
	

	
}






