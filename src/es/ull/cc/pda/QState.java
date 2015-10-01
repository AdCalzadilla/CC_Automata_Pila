package es.ull.cc.pda;

import java.util.HashMap;
import java.util.Map;


public class QState {
	
	private String qName;
	private HashMap<Key, DuoTransition>matrix;
	
	public QState(String qName){
		this.setqName(qName);
		this.matrix = new HashMap<Key, DuoTransition>();
	}
	
	public QState(String qName, int numQElements, int numStackElements){
		this.setqName(qName);
		this.matrix = new HashMap<Key, DuoTransition>();
	}

	public String getqName() {
		return qName;
	}

	public void setqName(String qName) {
		this.qName = qName;
	}
	
	public void setHashMap(Key index1, DuoTransition transition1){
		this.matrix.put(index1, transition1);
	}
	
	public void printMap(){
		System.out.println(" - Matriz de transición del estado "+ this.getqName());
		for (Map.Entry entry : matrix.entrySet()) {
	    	String key = entry.getKey().toString();
	        Object value = entry.getValue();
	        System.out.println(key + " : " + value);
	    }
	}
	
	public int numMapElement(){
		return matrix.size();
	}

}
