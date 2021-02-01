
public class Reservation  implements Comparable<Reservation> {
	private String guestName;
	private String phoneNumber;
	private int totPersons;
	private Day dateDine;
	private Day dateRequest;
	private int TicketNo;
	private RState status;

	public Reservation(String guestName, String phoneNumber, int totPersons, String sDateDine)
	{	
		this.guestName=guestName;
		this.phoneNumber=phoneNumber;
		this.totPersons=totPersons;
		this.dateDine=new Day(sDateDine);
		this.dateRequest=SystemDate.getInstance().clone();
		this.status= new RStatePending();
		
	
		TicketOffice ticket=TicketOffice.getInstance();
		
		if (ticket.DateAlreadyReserved(sDateDine)) {
			this.TicketNo=ticket.getTicketNum(sDateDine);
		}
		else {
			ReservedDate d=new ReservedDate(sDateDine);
			ticket.addReservedDate(d);
			this.TicketNo=ticket.getTicketNum(sDateDine);
			
		}	
	}

	public String toString() 
	{
		//Learn: "-" means left-aligned
		return String.format("%-13s%-11s%-14s%-25s%4d       %s", guestName, phoneNumber, dateRequest, dateDine+String.format(" (Ticket %d)", TicketNo), totPersons,status.genMsg(this));
	}

	public static String getListingHeader() 
	{
		
		return String.format("%-13s%-11s%-14s%-25s%-11s%s", "Guest Name", "Phone", "Request Date", "Dining Date and Ticket", "#Persons", "Status");
	}
	
	
	public int getTicketNo() {
		return this.TicketNo;
		
	}
	public int getNoOfPersons() {
		return this.totPersons;
		
	}
	
	public String getName() {
		return guestName;
	}
	
	public String getPhone() {
		return phoneNumber;
		
	}
	public Day getDatedine() {
		return dateDine;
	}
	
	public RState getStatus() {
		return status;
	}
	
	
	public void setStatus(RState s) {
		this.status= s;
	}
	
	@Override
	public int compareTo(Reservation another) 
	
	{

		if (this.guestName.equals(another.guestName)) {
			if (this.phoneNumber.equals(another.phoneNumber)) {
				if (this.dateDine.toString().equals(another.dateDine.toString()))
					return 0;
				else if(Day.ifGreater(this.dateDine.toString(), another.dateDine.toString())) {
					return 1;
				}else {
					return -1;
				}
			}else {
				if (this.phoneNumber.compareTo(another.phoneNumber)>0) {
					return 1;
				}else
					return -1;
			}
			
		}else {
			if (this.guestName.compareTo(another.guestName)>0) {
				return 1;
			}else {
				return -1;
			}
		}
		
		
	}
	


}
