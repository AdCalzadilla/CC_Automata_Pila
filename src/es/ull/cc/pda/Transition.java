package es.ull.cc.pda;

public class Transition {
	
	private String eState;
	private String eStack;
	
	public Transition(String eState, String eStack){
		this.seteState(eState);
		this.seteStack(eStack);
	}

	public String geteState() {
		return eState;
	}

	public void seteState(String eState) {
		this.eState = eState;
	}

	public String geteStack() {
		return eStack;
	}

	public void seteStack(String eStack) {
		this.eStack = eStack;
	}

	@Override
	public String toString() {
		return "("+ eState + ", "+ eStack+")";
	}
	
	

}
