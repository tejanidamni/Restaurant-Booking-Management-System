import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Main {

	public static void main(String [] args) 
	{	
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("Please input the file pathname: ");
		String filepathname = in.nextLine();
		
		Scanner inFile = null;
		try {
			inFile = new Scanner(new File(filepathname));
		
			String cmdLine1 = inFile.nextLine();
			String[] cmdLine1Parts = cmdLine1.split("\\|"); //Split by vertical bar character '|' (Regular expression: "\|")
			System.out.println("\n> "+cmdLine1);
			SystemDate.createTheInstance(cmdLine1Parts[1]);
			
			while (inFile.hasNext())		
			{
				String cmdLine = inFile.nextLine().trim();
				
				//Blank lines exist in data file as separators.  Skip them.
				if (cmdLine.equals("")) continue;  

				System.out.println("\n> "+cmdLine);
				
				//split the words in actionLine => create an array of word strings
				String[] cmdParts = cmdLine.split("\\|"); 
				
				if (cmdParts[0].equals("request"))
					(new CmdRequest()).execute(cmdParts);
				else if (cmdParts[0].equals("assignTable"))
					(new CmdAssignTable()).execute(cmdParts);
				else if (cmdParts[0].equals("cancel"))
					(new CmdCancel()).execute(cmdParts);
				else if (cmdParts[0].equals("suggestTable"))
					(new CmdSuggestTable()).execute(cmdParts);
				else if (cmdParts[0].equals("listReservations"))
					(new CmdListReservations()).execute(cmdParts);
				else if (cmdParts[0].equals("startNewDay"))
					(new CmdStartNewDay()).execute(cmdParts);
				else if (cmdParts[0].equals("listTableAllocations"))
					(new CmdListTableAllocations()).execute(cmdParts);
				else if (cmdParts[0].equals("undo"))
					RecordedCommand.undoOneCommand();
				else if (cmdParts[0].equals("redo"))
					RecordedCommand.redoOneCommand();
				else
					throw new ExWrongCommand();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			} catch (InputMismatchException e) {
			System.out.println("File content problem. Program terminated.");
			} catch (ExWrongCommand e) {
			System.out.println("Unknown command - ignored!");
			} finally {
			if (inFile!=null) //If it has been opened successfully, close it
				inFile.close();
			in.close();
			}
	}
}
