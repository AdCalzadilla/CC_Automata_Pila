package es.ull.cc.pda;

import java.util.HashMap;

public class QState {
	
	private String qName;
	private int numQElements;
	private int numStackElements;
	private HashMap<String, HashMap<String, DuoTransition>>matrix;
	
	public QState(String qName){
		this.setqName(qName);
		this.numQElements = 0;
		this.numStackElements = 0;
		this.matrix = new HashMap<String,HashMap<String, DuoTransition>>();
	}
	
	public QState(String qName, int numQElements, int numStackElements){
		this.setqName(qName);
		this.numQElements = numQElements;
		this.numStackElements = numStackElements;
		this.matrix = new HashMap<String,HashMap<String, DuoTransition>>();
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
