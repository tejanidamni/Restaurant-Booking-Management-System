
public class RStatePending implements RState{
	public String genMsg(Reservation r) {
		return "Pending";
		
	}
	@Override
	public boolean equals(Object o) {
//		RState s=(RState)o;
		if (o==null) {
			return false;
		}
		if (!(o instanceof RStatePending)) {
			return false;
			
		}
		return true;
	}

}
