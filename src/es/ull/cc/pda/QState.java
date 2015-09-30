package es.ull.cc.pda;

import java.util.HashMap;

public class QState {
	
	private String qName;
	private int numQElements;
	private int numStackElements;
	private HashMap<String, HashMap<String, DuoTransition>>matrix;
	private DuoTransition[][] transitions;
	
	public QState(String qName){
		this.setqName(qName);
		this.numQElements = 0;
		this.numStackElements = 0;
		this.matrix = new HashMap<String,HashMap<String, DuoTransition>>();
		this.transitions = new DuoTransition[numQElements][numStackElements];
	}
	
	public QState(String qName, int numQElements, int numStackElements){
		this.setqName(qName);
		this.numQElements = numQElements;
		this.numStackElements = numStackElements;
		this.matrix = new HashMap<String,HashMap<String, DuoTransition>>();
		this.transitions = new DuoTransition[numQElements][numStackElements];
	}

	public DuoTransition getTransitions(int qElement, int eStack) {
		return transitions[qElement][eStack];
	}

	public void setTransitions(int qElement, int eStack, DuoTransition tran) {
		this.transitions[qElement][eStack] = tran;
	}

	public String getqName() {
		return qName;
	}

	public void setqName(String qName) {
		this.qName = qName;
	}
	
	public void setHashMap(String index1, HashMap<String,DuoTransition> transition1){
		this.matrix.put(index1, transition1);
	}

}
