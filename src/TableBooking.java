import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TableBooking {
	
	private ArrayList<Table> allAssignedTables;
	private ArrayList<String> editedAvailableTables ;
	

	private static TableBooking instance = new TableBooking(); 
	private TableBooking() { allAssignedTables=new ArrayList<Table>();}
	public static TableBooking getInstance(){ return instance; }
	
	
	public Table addTableReservation (String aName, Reservation r, Day aDay) { 
		
		Table t=new Table(aName,r,aDay);
		allAssignedTables.add(t);
		return t;
	}
	
	public void removeTableReservation(Table t1) {
		allAssignedTables.remove(t1);
	}
	
	public ArrayList<Table> outputTables(Reservation r) {
		ArrayList<Table> Result= new ArrayList<Table>();
		for(Table t: allAssignedTables) {
			if(t.getReservation().equals(r))
				Result.add(t);
		}
		return Result;	
	}
	
	public Reservation getReservations(String aName,Day aDay) {
		
		for(Table t: allAssignedTables) {
			if (t.getName().equals(aName) && t.getDiningDate().toString().equals(aDay.toString())){
				return t.getReservation();
			}
		}
		return null;
	}
	
	public Table getTable(String aName,Day aDay) {
		for(Table t: allAssignedTables) {
			if (t.getName().equals(aName) && t.getDiningDate().toString().equals(aDay.toString())){
				return t;
			}
		}
		return null;
	}
	
	public String outputTableName(Reservation r) {
		String result="";
		for(Table t: allAssignedTables) {
			if (t.getReservation().equals(r)) {
				result+= t.getName()+ " ";
			}
		}
		result=result.trim();
		return result;
		
	}
	
	public void addTableReservation (Table t) { 
		allAssignedTables.add(t);	
	}
	
	public boolean isCodeFound(String name) {
		if (name.substring(0, 1).equals("T")||name.substring(0, 1).equals("F")||name.substring(0, 1).equals("H")) {
			if (name.substring(0,1).equals("T")) {
				if (Integer.parseInt(name.substring(1,3))>0 && Integer.parseInt(name.substring(1,3))<=10) {
					return true;
				}
				
			}else if (name.substring(0,1).equals("F")) {
				if (Integer.parseInt(name.substring(1,3))>0 && Integer.parseInt(name.substring(1,3))<=6) {
					return true;
				}
				
			}else {
				if (Integer.parseInt(name.substring(1,3))>0 && Integer.parseInt(name.substring(1,3))<=3) {
					return true;
				}
				
			}
			
		}
		return false;
		
	}
	
	public int NoOfSeats(String name) {
		if (name.substring(0,1).equals("T") ) {
			return 2;
		}else if (name.substring(0,1).equals("F")) {
			return 4;
		}else{
			return 8;
		}
		
	}

	public boolean isTableAlreadyAssignedToThisBooking(Reservation r1) {
		for(Table t: allAssignedTables) {
			if (t.getReservation().equals(r1)){
				return true;
			}
		
		}
		return false;
		
	}
	
	public boolean isTableAlreadyAssignedForOtherBooking(String aName, String date) {
		for(Table t: allAssignedTables) {
			if (t.getName().equals(aName) && t.getReservation().getDatedine().toString().equals(date)){
				return true;
			}
		}
		return false;
	}
	
	public void EditReservations(String name, Reservation r,Day aDay) {
		for(Table t: allAssignedTables) {
			if (t.getName().equals(name) && t.getDiningDate().toString().equals(aDay.toString())){
				t.setReservation(r);
			}
		}
		
	}
	
	public void listTableAllocations(String date ) {
		ArrayList<Table> AssignedTablesForThisDate=new ArrayList<Table>();
		editedAvailableTables=new ArrayList<String>(Arrays.asList("T01","T02","T03","T04","T05","T06","T07","T08","T09","T10","F01","F02","F03","F04","F05","F06","H01","H02","H03"));
		boolean IsFound=false;
		System.out.print("Allocated tables: \n");
		for(Table t: allAssignedTables) {
			
			if (t.getReservation().getDatedine().toString().equals(date)) {
				editedAvailableTables.remove(t.getName());;
				AssignedTablesForThisDate.add(t);
				
				IsFound=true;
			}
		
		}
		
		if(IsFound) {
			Collections.sort(AssignedTablesForThisDate);
			for(Table t: AssignedTablesForThisDate) 
				System.out.print(String.format(t.getName() +" (Ticket %d)\n",t.getReservation().getTicketNo()));
			System.out.println();
		}else 
			System.out.println("[None]\n");
		
		System.out.print("Available tables: \n");

			for(String s: editedAvailableTables) {
				System.out.print(s.toString() + " ");
			}
			
		System.out.println("\n");
		editedAvailableTables.clear();
		
	}
	
	
	public ArrayList<String> outputAvailableTables(String date){
		editedAvailableTables = new ArrayList<String>(Arrays.asList("T01","T02","T03","T04","T05","T06","T07","T08","T09","T10","F01","F02","F03","F04","F05","F06","H01","H02","H03"));
		for(Table t: allAssignedTables) {
			
			if (t.getReservation().getDatedine().toString().equals(date)) {
				editedAvailableTables.remove(t.getName());;
				
			}
		
		}
		return editedAvailableTables;
		
	}

}
