
public class CmdStartNewDay extends RecordedCommand {
	
	
	String previous;
	SystemDate newDate;
	@Override
	public void execute(String[] cmdParts) {
		try {
			if (cmdParts.length < 2 ) {
				throw new ExNotEnoughArguments();
			}
			
			previous= SystemDate.getInstance().toString();
			SystemDate.getInstance().set(cmdParts[1]);
			
			addUndoCommand(this);
			clearRedoList();
			
			System.out.println("Done.");
		} catch (ExNotEnoughArguments e) {
			System.out.println(e.getMessage());
		}catch (NumberFormatException e) {
			System.out.println("Wrong number format!");
		}
		
	}
	
	@Override
	public void undoMe() {
		
		SystemDate.getInstance().set(previous);
		addRedoCommand(this);
	}
	
	@Override
	public void redoMe() {
		
		addUndoCommand(this);
		
	}
	
	

}
