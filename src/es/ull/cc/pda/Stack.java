package es.ull.cc.pda;

import java.util.LinkedList;

public class Stack {
	
	private LinkedList<Character>theStack;
	
	public Stack(){
		theStack = new LinkedList<Character>();
	}
	
	public void add(String cadena){
		for(int i=0; i< cadena.length();i++){
			if(cadena.charAt(i) != '-'){
				theStack.add(cadena.charAt(i));
			}
		}
	}
	
	public void add(char stackElement){
		if(stackElement != '-'){
			theStack.add(stackElement);
		}
	}
	
	public char pop(){
		return theStack.pop();
	}
	
	public boolean isEmpty(){
		return theStack.isEmpty();
	}

}
