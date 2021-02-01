
public class ReservedDate {
	private String businessday;
	private int lastTicketNo;
	
	public ReservedDate(String date) {
		this.businessday=date;
		this.lastTicketNo=0;
	}
	public String getBusinessday() {
		return businessday;
	}
	
	public int getLastTicketNo() {
		return lastTicketNo;
	}
	
	public void setLastTicketNo(int n) {
		this.lastTicketNo = this.lastTicketNo + n;
		
	}
	
	

}
