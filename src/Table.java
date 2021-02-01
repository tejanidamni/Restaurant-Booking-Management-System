
public class Table implements Comparable<Table> {
	
	//Instance fields
		private String name;
		private Reservation r;
		private Day diningDate;
		
		
		//Constructor
		public Table(String aName, Reservation aReservation,Day aDay) {
			this.name=aName;
			this.r=aReservation;
			this.diningDate=aDay;
			
		}
		
		//Assessor method
		public String getName() {
			return name;
		}
		
		//Assessor method
		public Reservation getReservation() {
			return r;
		}
		
		//Assessor method
		public Day getDiningDate() {
			return diningDate;
		}
		
		public void setReservation(Reservation r1) {
			this.r=r1;
		}
		
		@Override
		public int compareTo(Table another)
		{
			if (this.name.substring(0,1).equals(another.name.substring(0,1))) {
				if((Integer.parseInt(this.name.substring(1,3))>(Integer.parseInt(another.name.substring(1,3))))){
					return 1;
				}else {
					return -1;
				}
				
			}
			else if (this.name.substring(0,1).equals("T")) return -1;
			else if (this.name.substring(0,1).equals("F") && !(another.name.substring(0,1).equals("T"))) return -1;
			
			
			else return 1;
		}
}
