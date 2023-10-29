package GestioneAuto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
	
	public void menuPrincipale() {
		
		Scanner scannerLine = new Scanner(System.in);
		Scanner scannerInt = new Scanner(System.in);
		List<Auto> listaAuto= new ArrayList<Auto>();
		GestioneAuto ga = new GestioneAuto();
		boolean continua = true;
		while(continua) {
			
		System.out.println("1/ Aggiungi auto, 2/ modificato auto, 3/Elimina auto, 4/visualizza tutte le auto, 5/Cerca auto per targa, 6/ per uscire");
		
		
		
			int response =scannerInt.nextInt();
			switch(response) {
			//AGGIUNGO AUTO NEL FILE TXT
			case 1:
	
				ga.aggiungiAuto(listaAuto);
					
				break;
			
			//MODIFICO AUTO
			case 2:
				ga.modificaAuto(listaAuto);
				
				break;
			//ELIMINA AUTO
			case 3:
				ga.eliminaAuto(listaAuto);
							
				break;
			//VISUALIZZA AUTO
			case 4:
				ga.visualizzaAuto(listaAuto);
				
				break;
			//CERCA AUTO PER TARGA
			case 5:
				ga.cercaAutoPerTarga(listaAuto);
				
				break;
			case 6:
				continua= false;
			}
		}
		
	}

}