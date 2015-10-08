package es.ull.cc.pda;

import java.util.Scanner;


public class Main {
	
	public static void menuInit(){
		System.out.println(" = Autómata de Pila = ");
		System.out.println(" ==================== ");
		System.out.print(" Introduce autómata (fichero): ");
	}
	public static void menu(){
		System.out.println(" \n 1.- Representación formal del autómata ");
		System.out.println(" 2.- Ejecutar ");
		System.out.println(" 3.- Salir ");
		System.out.print(" - Opcion: ");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String rute;
		int choice;
		String cadena;
		
		menuInit();
		rute = sc.nextLine();
		rute = "files/"+ rute;
		//PDA myPDA = new PDA("files/maq2.txt");
		PDA myPDA = new PDA(rute);
		menu();
		choice = sc.nextInt();
		while(choice != 3){
			switch(choice){
			case 1:
				myPDA.printFormalAutomate();
				break;
			case 2:
				System.out.print(" - Introduce cadena para comprobar: ");
				cadena = sc.next();
				myPDA.execute(cadena);
				break;
			}
			menu();
			choice = sc.nextInt();
		}
		sc.close();
	}

}
