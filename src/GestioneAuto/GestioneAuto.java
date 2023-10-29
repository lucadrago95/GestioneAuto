package GestioneAuto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestioneAuto{
        List<Auto> listaAuto;

        public void aggiungiAuto(List<Auto> listaAuto) {
            Scanner scannerInt = new Scanner(System.in);
            Scanner scannerLine = new Scanner(System.in);
            System.out.println("Inserisci i dati per la nuova auto: ");

            System.out.println("inserisci id: ");
            int id = scannerInt.nextInt();


            System.out.println("inserisci marca: ");
            String marca = scannerLine.nextLine();

            System.out.println("inserisci modello: ");
            String modello= scannerLine.nextLine();

            System.out.println("inserisci anno: ");
            int anno = scannerInt.nextInt();

            System.out.println("inserisci targa: ");
            String targa = scannerLine.nextLine();

            System.out.println("inserisci prezzo: ");
            double prezzo = scannerInt.nextDouble();

            System.out.println("inserisci tipo di carburante: ");
            String tipoCarburante = scannerLine.nextLine();
            
            Auto auto = new Auto(id, marca,modello,anno,targa,prezzo,tipoCarburante);
            
            FileHandler.aggiungiAuto(listaAuto, auto);
            listaAuto.add(auto);
            FileHandler.sincronizza(listaAuto);
            
        }
        public void modificaAuto(List<Auto> listaAuto) {
        	
        	Auto auto =cercaAutoPerTarga(listaAuto);
        	
    		Scanner scanner = new Scanner(System.in);
    		System.out.println("Inserisci i dati per modificare l'auto: ");
    		
    		System.out.println("modifica id: ");
    		int id = scanner.nextInt();
    		
    		System.out.println("modifica marca: ");
    		String marca = scanner.nextLine();
    		
    		System.out.println("modifica modello: ");
    		String modello= scanner.nextLine();
    		
    		System.out.println("modifica anno: ");
    		int anno = scanner.nextInt();
    		
    		System.out.println("modifica prezzo: ");
    		double prezzo = scanner.nextDouble();
    		
    		System.out.println("modifica tipo di carburante: ");
    		String tipoCarburante = scanner.nextLine();
    		
    		auto.setId(id);
    		auto.setMarca(marca);
    		auto.setPrezzo(prezzo);
    		auto.setModello(modello);
    		auto.setTipoCarburante(tipoCarburante);
    		auto.setAnno(anno);
    		FileHandler.aggiungiAuto(listaAuto, auto);
			}
        
    	public void eliminaAuto(List<Auto> listaAuto) {
    		Scanner scanner = new Scanner(System.in);
    		System.out.println("Inserisci id dell'auto da eliminare: ");
    		
    		System.out.println("id: ");
    		int id = scanner.nextInt();
    		boolean IdUguale = false;
    		for(Auto auto : listaAuto) {
    			if(id == auto.getId()) {
    				IdUguale = true;
    				// recuperare dati del veicolo corrispondente
    				System.out.println("veicolo trovato: ");
    				//stampa i dati del veicolo corrispondente 
    				System.out.println(auto.toString());
    				
    				
    				FileHandler.rimuoviAuto(auto);
    				listaAuto.remove(auto);
    				FileHandler.sincronizza(listaAuto);
    			}
    		}
    		System.out.println("id non trovato."); 
    			
    	}
    	
    	public void visualizzaAuto(List<Auto> listaAuto) {
    		FileHandler.aggiungiAllAuto(listaAuto);
    		System.out.println(listaAuto);
    	}
    	
    	
    	public Auto cercaAutoPerTarga(List<Auto> listaAuto) {
    		Scanner scanner = new Scanner(System.in);
    		System.out.println("cerca per auto per targa ");
    		System.out.println("Inserisci targa: ");
    		String targa = scanner.nextLine();
    		
    		// fare un check della targa nella listaAuto
    		// se corrisponde esce risultato
    		//altrimenti veicolo non esiste
    		boolean targaUguale = false;
    		for(Auto auto : listaAuto) {
    			if(targa.equals(auto.getTarga()) ) {
    				targaUguale = true;
    				// recuperare dati del veicolo corrispondente
    				System.out.println("veicolo trovato: ");
    				//stampa i dati del veicolo corrispondente 
    				System.out.println(auto.toString());
    				return auto;
    			}
    		}
    		System.out.println("targa non trovata.");
    		return null; 

    	}
  
}        