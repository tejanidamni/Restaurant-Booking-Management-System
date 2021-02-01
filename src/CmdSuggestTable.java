
import java.util.ArrayList;

public class CmdSuggestTable implements Command{
	
	BookingOffice booking= BookingOffice.getInstance();
	TableBooking tbooking=TableBooking.getInstance();
	Reservation r;
	ArrayList <String> availableTables =new ArrayList <String>();
	ArrayList <String> EightSeatedTables=new ArrayList <String>();
	ArrayList <String> FourSeatedTables=new ArrayList <String>();
	ArrayList <String> TwoSeatedTables=new ArrayList <String>();
	
	int DesiredNoOfEights=0;
	int DesiredNoOfFours=0;
	int DesiredNoOfTwos=0;
	int NoOfEightsAvailable=0;
	int NoOfFoursAvailable=0;
	int NoOfTwosAvailable=0;
	int NoOfEightsAssigned=0;
	int NoOfFoursAssigned=0;
	int NoOfTwosAssigned=0;
	
	@Override
	public void execute(String[] cmdParts) {
		try {
			if (cmdParts.length < 3 ) {
				throw new ExNotEnoughArguments();
			}
			r = booking.outputReservation(cmdParts[1], Integer.parseInt(cmdParts[2]));
			if(tbooking.isTableAlreadyAssignedToThisBooking(r)) {
				throw new ExTableAssignedForThisBooking();
			}
			availableTables=tbooking.outputAvailableTables(cmdParts[1]);
			for(String s:availableTables ) {
				if (s.substring(0,1).equals("T"))
					TwoSeatedTables.add(s);
				else if (s.substring(0,1).equals("F"))
					FourSeatedTables.add(s);
				else if (s.substring(0,1).equals("H"))
					EightSeatedTables.add(s);
			}
			NoOfEightsAvailable=EightSeatedTables.size();
			NoOfFoursAvailable= FourSeatedTables.size();
			NoOfTwosAvailable= TwoSeatedTables.size();
			
			int NoOfPersons=r.getNoOfPersons();
			int TotalAvailableSeats= NoOfEightsAvailable*8 + NoOfFoursAvailable*4 + NoOfTwosAvailable*2;
			
			if (NoOfPersons>TotalAvailableSeats) {
				System.out.print("Suggestion for "+ NoOfPersons+" persons: "+"Not enough tables");
			}else {
				System.out.print("Suggestion for "+ NoOfPersons+" persons: ");
				DesiredCombination(NoOfPersons);
				AssignTables();
				int i=0;
				for(String s: EightSeatedTables) {
					if(i< NoOfEightsAssigned) {
						System.out.print(s.toString()+ " ");
						i+=1;
						
					}
				}
				i=0;
				for(String s: FourSeatedTables) {
					if(i<NoOfFoursAssigned) {
						System.out.print(s.toString()+ " ");
						i+=1;
					}
				}
				i=0;
				for(String s: TwoSeatedTables) {
					if (i< NoOfTwosAssigned) {
						System.out.print(s.toString()+ " ");
						i+=1;
					}
				}
				System.out.println();
				
			}
			
		}catch (NumberFormatException e) {
			System.out.println("Wrong number format!");	
		 }catch (ExBookingNotFound e) {
			System.out.println(e.getMessage());
		} catch (ExNotEnoughArguments e) {
			System.out.println(e.getMessage());
		} catch (ExTableAssignedForThisBooking e) {
			System.out.println(e.getMessage());
		} catch (ExDateHasAlreadyPassed e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void DesiredCombination(int NoOfPersons) {
		if (NoOfPersons<=2)
			DesiredNoOfTwos+=1;
		else if (NoOfPersons<=4)
			DesiredNoOfFours+=1;
		else if (NoOfPersons<=8) {
			if ((NoOfPersons-4)<=(8-NoOfPersons)) {
				DesiredNoOfFours+=1;
				DesiredNoOfTwos+=1;
			}else {
				DesiredNoOfEights+=1;
			}
		}else {
			DesiredNoOfEights+=(NoOfPersons/8);
			DesiredCombination( NoOfPersons%8);
		}
		
	}
	
	public void AssignTables() {
		if(DesiredNoOfEights<=NoOfEightsAvailable) {
			NoOfEightsAssigned=DesiredNoOfEights;
			NoOfEightsAvailable-=NoOfEightsAssigned;
			
		}else {
			NoOfEightsAssigned=NoOfEightsAvailable;
			NoOfEightsAvailable=0;
			DesiredNoOfFours+=((DesiredNoOfEights-NoOfEightsAssigned)*2);
		}
		
		if(DesiredNoOfFours<=NoOfFoursAvailable) {
			NoOfFoursAssigned=DesiredNoOfFours;
			NoOfFoursAvailable-=NoOfFoursAssigned;
			
		}else {
			NoOfFoursAssigned=NoOfFoursAvailable;
			NoOfFoursAvailable=0;
			DesiredNoOfTwos+=((DesiredNoOfFours-NoOfFoursAssigned)*2);
		}
		NoOfTwosAssigned=DesiredNoOfTwos;
		NoOfTwosAvailable-=NoOfTwosAssigned;
		
		
	}
	

}
