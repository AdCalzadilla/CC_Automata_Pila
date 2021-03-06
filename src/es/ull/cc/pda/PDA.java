package es.ull.cc.pda;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PDA {
	
	private ArrayList<QState>myQStates;
	private ArrayList<String>myAlphabet;
	private ArrayList<String>myStackAlphabet;
	private Stack myStack;
	private String qStart;
	private String stackStart;
	private boolean finishStates;
	
	public PDA(String route) { 
		File document;
		FileReader myFile;
		BufferedReader br;
		
		myQStates = new ArrayList<QState>();
		myAlphabet = new ArrayList<String>();
		myStackAlphabet = new ArrayList<String>();
		myStack = new Stack();
		finishStates = false;
		
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
						myAlphabet.add("-");
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
							myStack.add(stackStart);
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
									finishStates = true;
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
	
	private QState getQState(String qName){
		String temp;
		for(int i=0; i<myQStates.size();i++){
			temp = myQStates.get(i).getqName();
			if(temp.equals(qName)){
				return myQStates.get(i);
			}
		}
		return new QState("-1");
	}
	
	private boolean isMachineStop(String cadena){
		if(cadena.charAt(0) == '.' && cadena.charAt(1) == '.'){
			return true;
		}
		else if(myStack.isEmpty()){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	private boolean aceptString(String cadena, QState qActual){
		if(cadena.charAt(0) != '.' || cadena.charAt(1) != '.'){
			return false;
		}
		else{
			if(finishStates){
				if(qActual.isqFinal()){
					return true;
				}
				else{
					return false;
				}
			}
			else if(myStack.isEmpty()){
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
	
	private void printTable(QState qActual, String cadena, char popStack, Key charStack){
		// -- Estado
		System.out.print(" | "+ qActual.getqName());
		// -- Cadena
		System.out.print(" \t| "+ cadena );
		// -- Pila
		System.out.print("\t| "+ popStack+" ");
		myStack.printStack();
		// -- Acción
		System.out.print("\t| ("+cadena.charAt(1)+", "+popStack+", "+qActual.getHashMapValues(charStack).geteState()+", "+qActual.getHashMapValues(charStack).geteStack()+") |\n");
		
	}
	
	public void execute(String cadena){
		System.out.println(" | Estado\t| Cadena\t| Pila\t| Acción |");
		System.out.println(" | ------\t| ------\t| ----\t| ------ |");
		// ------ 0 --------------
		cadena = "."+ cadena + ".";
		boolean stop = false;
		QState qActual= new QState();
		qActual = getQState(qStart);
		char indexCad = cadena.charAt(1);
		char popStack = myStack.pop();
		String insertStack = "";
		Key charStack = new Key(Character.toString(indexCad), Character.toString(popStack));
		// ------- 1 -------------
		while(stop == false){ // Revisar todo el while. transiciones sin quemar caracter.
			if(qActual.containsKey(charStack)){
				// -- Quemo el caracter de la cadena de entrada.
				printTable(qActual, cadena, popStack, charStack);
				cadena = cadena.substring(2, cadena.length());
				cadena = "." + cadena;
				// -- Si la es una epsilon transición:
				/*if(indexCad == 'E'){
					
				}*/
				// -- Introduzco nuevo elemento en la pila.
				insertStack = qActual.getHashMapValues(charStack).geteStack();
				myStack.add(insertStack);
				// -- Cambio el estado actual.
				qActual = getQState(qActual.getHashMapValues(charStack).geteState());
				// ---- Final??
				if(isMachineStop(cadena)){
					if(myStack.isEmpty()){
						popStack = '-';
					}
					stop = true;
				}
				else{
					indexCad = cadena.charAt(1);
					popStack = myStack.pop();
					insertStack = "";
					charStack = new Key(Character.toString(indexCad), Character.toString(popStack));
				}
			}
			else{
				stop = true;
			}
		}
		printTable(qActual, cadena, popStack, charStack);
		if(aceptString(cadena, qActual)){
			System.out.println(" - Cadena aceptada.");
		}
		else{
			System.out.println(" - Cadena rechazada.");
		}
		reloadStack();
	}
	
	private void reloadStack(){
		myStack.clear();
		myStack.add(stackStart);
	}
	
	private void printMyQStates(){
		System.out.print(" { ");
		for(int i=0; i< myQStates.size();i++){
			System.out.print(myQStates.get(i).getqName()+" ");
		}
		System.out.print(" } ");
	}
	
	private void printMyAlphabet(){
		System.out.print(" { ");
		for(int i=0; i< myAlphabet.size();i++){
			System.out.print(myAlphabet.get(i)+" ");
		}
		System.out.print(" } ");
	}
	
	private void printFinalStates(){
		for(int i=0; i< myQStates.size();i++){
			if(myQStates.get(i).isqFinal()){
				System.out.print(myQStates.get(i).getqName()+" ");
			}
		}
	}
	
	public void printFormalAutomate(){
		System.out.print(" Q = ");
		printMyQStates();
		System.out.print("\n E = ");
		printMyAlphabet();
		System.out.print("\n T = ");
		myStack.printStack();
		System.out.println(" s = "+ qStart);
		System.out.println(" f = ");
		printFinalStates();
	}
}
