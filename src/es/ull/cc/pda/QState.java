package es.ull.cc.pda;

import java.util.HashMap;
import java.util.Map;


public class QState {
	
	private String qName;
	private boolean qFinal;
	private HashMap<Key, Transition>matrix;
	
	public QState(){
		this.setqName("vacio");
		this.setqFinal(false);
		this.matrix = new HashMap<Key, Transition>();
	}
	
	public QState(String qName){
		this.setqName(qName);
		this.setqFinal(false);
		this.matrix = new HashMap<Key, Transition>();
	}
	
	public QState(String qName, boolean qFinal){
		this.setqName(qName);
		this.setqFinal(qFinal);
		this.matrix = new HashMap<Key, Transition>();
	}
	
	public QState(String qName, int numQElements, int numStackElements){
		this.setqName(qName);
		this.matrix = new HashMap<Key, Transition>();
	}

	public String getqName() {
		return qName;
	}

	public void setqName(String qName) {
		this.qName = qName;
	}
	
	public void setHashMap(Key index1, Transition transition1){
		this.matrix.put(index1, transition1);
	}
	
	public Transition getHashMapValues(Key index1){
		return matrix.get(index1);
	}
	
	public boolean containsKey(Key question){
		if(matrix.containsKey(question)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void printMap(){
		System.out.println(" - Matriz de transición del estado "+ this.getqName());
		for (Map.Entry entry : matrix.entrySet()) {
	    	String key = entry.getKey().toString();
	        Object value = entry.getValue();
	        System.out.println(key + " : " + value);
	    }
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}
		if(!(obj instanceof QState)){
			return false;
		}
		QState theQState = (QState) obj;
		return theQState.getqName() == this.getqName();
	}

	public boolean isqFinal() {
		return qFinal;
	}

	public void setqFinal(boolean qFinal) {
		this.qFinal = qFinal;
	}
}
