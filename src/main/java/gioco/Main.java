package gioco;

import java.util.Scanner;


public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		while(true) {
			try(Scanner scanner=new Scanner(System.in)){
				String menu;
				menu=scanner.nextLine();
	
				switch(menu) {
				case "quit" :
					System.exit(0);
					
					default: 
						break;
				
				}
			}catch(Exception e) {
				
			}
		}



	}

}
