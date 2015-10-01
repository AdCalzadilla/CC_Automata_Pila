package es.ull.cc.pda;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PDA {
	
	private ArrayList<QState>myQStates;
	private ArrayList<String>myAlphabet;
	private ArrayList<String>myStackAlphabet;
	private ArrayList<String>myFinalStates;
	private String qStart;
	private String stackStart;
	
	public PDA(String route) { 
		File document;
		FileReader myFile;
		BufferedReader br;
		
		myQStates = new ArrayList<QState>();
		myAlphabet = new ArrayList<String>();
		myStackAlphabet = new ArrayList<String>();
		myFinalStates = new ArrayList<String>();
		
		try{
			document = new File(route);
			myFile = new FileReader(document);
			br = new BufferedReader(myFile);
			readFile(br);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("\n//////////////////////");
		System.out.println(" - myQStates");
		for(int i= 0; i < myQStates.size();i++){
			System.out.println(myQStates.get(i).getqName());
			System.out.print(" - numMatrixElement: ");
			myQStates.get(i).numMapElement();
			myQStates.get(i).printMap();
			System.out.println("\n----------------------");
		}
		System.out.println(" - myQAlphabet");
		System.out.println(myAlphabet);
		System.out.println(" - myStackAlphabet");
		System.out.println(myStackAlphabet);
		System.out.println(" - Estado inicial");
		System.out.println(qStart);
		System.out.println(" - Simbolo inicial de la pila");
		System.out.println(stackStart);
		System.out.println(" - Estados finales");
		System.out.println(myFinalStates);
		
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
							myFinalStates.add(cadena_token);
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
						DuoTransition temporalyDuo;
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
									temporalyDuo = new DuoTransition(tranStd, tranStack);
									
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

}
