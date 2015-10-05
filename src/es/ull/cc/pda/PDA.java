package es.ull.cc.pda;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class PDA {
	
	private ArrayList<QState>myQStates;
	private ArrayList<String>myAlphabet;
	private ArrayList<String>myStackAlphabet;
	private Stack myStack;
	private String qStart;
	private String stackStart;
	
	public PDA(String route) { 
		File document;
		FileReader myFile;
		BufferedReader br;
		
		myQStates = new ArrayList<QState>();
		myAlphabet = new ArrayList<String>();
		myStackAlphabet = new ArrayList<String>();
		
		try{
			document = new File(route);
			myFile = new FileReader(document);
			br = new BufferedReader(myFile);
			readFile(br);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void readFile(BufferedReader br){
		String cadena_linea;
		String cadena_token;
		StringTokenizer st;
		int numLines = 1;
		
		try {
			cadena_linea = br.readLine();
			while(br != null && cadena_linea != null){
				if(cadena_linea.charAt(0) == '#'){
					cadena_linea = br.readLine();
				}
				st = new StringTokenizer(cadena_linea);
				switch(numLines){
					case 1:
						while(st.hasMoreTokens()){
							cadena_token = st.nextToken();
							if(cadena_token.charAt(0) == '#'){
								break;
							}
							QState temporaryQState = new QState(cadena_token); 
							myQStates.add(temporaryQState);
						}
						numLines++;
						cadena_linea = br.readLine();
						break;
					case 2:
						while(st.hasMoreTokens()){
							cadena_token = st.nextToken();
							if(cadena_token.charAt(0) == '#'){
								break;
							} 
							myAlphabet.add(cadena_token);
						}
						numLines++;
						cadena_linea = br.readLine();
						break;
					case 3:
						while(st.hasMoreTokens()){
							cadena_token = st.nextToken();
							if(cadena_token.charAt(0) == '#'){
								break;
							} 
							myStackAlphabet.add(cadena_token);
						}
						numLines++;
						cadena_linea = br.readLine();
						break;
					case 4:
						while(st.hasMoreTokens()){
							cadena_token = st.nextToken();
							if(cadena_token.charAt(0) == '#'){
								break;
							} 
							qStart = cadena_token;
						}
						numLines++;
						cadena_linea = br.readLine();
						break;
					case 5:
						while(st.hasMoreTokens()){
							cadena_token = st.nextToken();
							if(cadena_token.charAt(0) == '#'){
								break;
							} 
							stackStart = cadena_token;
							myStack.add(stackStart.charAt(0));
						}
						numLines++;
						cadena_linea = br.readLine();
						break;
					case 6:
						while(st.hasMoreTokens()){
							cadena_token = st.nextToken();
							if(cadena_token.charAt(0) == '#'){
								break;
							} 
							for(int i=0; i< myQStates.size();i++){
								if(cadena_token == myQStates.get(i).getqName()){
									myQStates.get(i).setqFinal(true);
									break;
								}
							}
						}
						numLines++;
						cadena_linea = br.readLine();
						break;
					default:
						// Transiciones.
						String key_1;
						String key_2;
						String tranStd;
						String tranStack;
						Key theKey;
						Transition temporalyDuo;
						while(st.hasMoreTokens()){
							cadena_token = st.nextToken();
							if(cadena_token.charAt(0) == '#'){
								break;
							}
							for(int i=0; i< myQStates.size();i++){
								if(cadena_token.equals(myQStates.get(i).getqName())){
									cadena_token = st.nextToken();
									key_1 = cadena_token;
									cadena_token = st.nextToken();
									key_2 = cadena_token;
									theKey = new Key(key_1, key_2);
									cadena_token = st.nextToken();
									tranStd = cadena_token;
									cadena_token = st.nextToken();
									tranStack = cadena_token;
									temporalyDuo = new Transition(tranStd, tranStack);
									
									myQStates.get(i).setHashMap(theKey, temporalyDuo);
									
									break;
								}
							}
						}
						numLines++;
						cadena_linea = br.readLine();
				}
			} 
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public QState getQState(String qName){
		for(int i=0; i<myQStates.size();i++){
			if(qName.equals(myQStates.get(i))){
				return myQStates.get(i);
			}
		}
		return new QState("-1");
	}
	
	public QState getQState(Transition tran){
		QState temp = new QState();
		temp = getQState(tran.geteState());
		return temp;
	}
	
	public boolean isEnd(String cadena){
		if(cadena.length() == 0){
			return true;
		}
		else if(myStack.isEmpty()){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public boolean aceptString(String cadena, QState qActual){
		if(cadena.length() != 0){
			return false;
		}
		else{
			if(myStack.isEmpty()){
				return true;
			}
			else if(qActual.isqFinal()){
				return true;
			}
			else{
				return false;
			}
		}
	}
	
	public void execute(String cadena){
		System.out.println(" | Estado\t| Cadena\t| Pila\t| Acción |");
		System.out.println("| ------\t| ------\t| ----\t| ------ |");
		// ------ 0 --------------
		boolean stop = false;
		QState qActual= new QState();
		qActual = getQState(qStart);
		char indexCad = cadena.charAt(0);
		char popStack = myStack.pop();
		String insertStack = "";
		Key charStack = new Key(Character.toString(indexCad), Character.toString(popStack));
		// ------- 1 -------------
		while(stop == false){
			if(qActual.containsKey(charStack)){
				// -- Quemo el caracter de la cadena de entrada.
				cadena.substring(0, 1);
				// -- Introduzco nuevo elemento en la pila.
				insertStack = qActual.getHashMapValues(charStack).geteStack();
				myStack.add(insertStack);
				// -- Cambio el estado actual.
				qActual = getQState(qActual.getHashMapValues(charStack).geteState());
				// ---- Final??
				if(isEnd(cadena)){
					stop = true;
				}
			}
			else{
				stop = true;
			}
		}
		if(aceptString(cadena, qActual)){
			System.out.println(" - Cadena aceptada.");
		}
		else{
			System.out.println(" - Cadena rechazada.");
		}
	}

}
