// questa classe legge e scrive sul file.txt
package GestioneAuto;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileHandler {
	//private static String nomeFile = "macchine.txt";
	private static File nomeFile = new File("C:\\Users\\Kiseldow\\Desktop\\Corso Java\\Parcheggio\\macchine.txt");
	private static File nomeFile_backup = new File("C:\\Users\\Kiseldow\\Desktop\\Corso Java\\Parcheggio\\macchineOF.txt");
	private static Path pathFile = nomeFile.toPath();
	private static Path pathFile_backup = nomeFile_backup.toPath();
	private Auto auto;
	
	public FileHandler(String nomeFile) {
		//this.nomeFile = nomeFile;
	}
	
	
	//riceve in input una lista di auto e ne scrive i dettagli sul file di testo
	public static void scriviSuFile(List<Auto> macchine, Boolean append) {
	try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeFile, append))) {
		for(Auto auto : macchine) {
			writer.write(auto.mostraDettagli());
			writer.newLine();
			
		}
		
	} catch (IOException e) {
		e.printStackTrace();
		}
	}
	
	public static List<Auto> leggiDaFile() {
	    List<Auto> auto = new ArrayList<>();

	    try (BufferedReader reader = new BufferedReader(new FileReader(nomeFile))) {
	        String riga;
	        while ((riga = reader.readLine()) != null) {
	            String[] parti = riga.split(",");
	            if (parti.length == 7) {
	                int id = Integer.parseInt(parti[0]);
	                String marca = parti[1];
	                String modello = parti[2];
	                int anno = Integer.parseInt(parti[3]);
	                String targa = parti[4];
	                double prezzo = Double.parseDouble(parti[5]);
	                String tipoCarburante = parti[6];
	                Auto newAuto = new Auto(id, marca, modello, anno, targa, prezzo, tipoCarburante);
	                auto.add(newAuto);
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return auto;
	}
	
	//Controllo se i file del DB sono identici, altrimenti il backup sovrascrive il file di testo
	public static Boolean checkBackup() {
		if (Files.exists(pathFile_backup)) {
            try {
                if (Files.readAllLines(pathFile).equals(Files.readAllLines(pathFile_backup))) {
                    return true;
                } else {
                    Files.copy(pathFile_backup, pathFile, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Il file originale è stato ripristinato dal backup.");
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Il file di backup non esiste.");
            //creo la copia se non esiste
            try {
                Files.copy(pathFile, pathFile_backup, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
	
	// aggiorno il backup con le rispettive modifiche
	public static void sovrascrivi() {
		 try {
		        Files.copy(pathFile, pathFile_backup, StandardCopyOption.REPLACE_EXISTING);
		        System.out.println("Backup aggiornato");
		    } catch (IOException e) {
		        System.err.println("Errore durante l'operazione di sovrascrittura del backup: " + e.getMessage());
		        e.printStackTrace();
		    }
	}
	
	//aggiungo le auto al db
		//
		public static void aggiungiAllAuto(List<Auto> listaAuto) {
			//sincronizzo il db con il file di testo prima di procedere;
			
			if (checkBackup()) {
				scriviSuFile(listaAuto, false);
				System.out.println("Auto aggiunte al DB");
				sovrascrivi();
				//sincronizza(listaAuto);
			}
		}
	
	//aggiungo una singola auto al db
	//
	public static void aggiungiAuto(List<Auto> listaAuto, Auto auto) {
		//sincronizzo il db con il file di testo prima di procedere;
		listaAuto.add(auto);
		
		if (checkBackup()) {
			scriviSuFile(listaAuto, false);
			System.out.println("Auto aggiunte al DB");
			sovrascrivi();
			//sincronizza(listaAuto);
		}
	}
	
	//questo metodo serve per rimuovere un auto
	public static void rimuoviAuto(Auto autoDaRimuovere) {
	    List<Auto> listaAuto = leggiDaFile();

	    if (listaAuto != null && checkBackup()) {
	        // Verifica se l'auto da rimuovere è presente nella lista
	        if (listaAuto.contains(autoDaRimuovere)) {
	            // Rimuovi l'auto dalla lista
	            listaAuto.remove(autoDaRimuovere);

	            // Rimuovi tutte le righe nel file e scrivi di nuovo la lista
	            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeFile, false))) {
	                for (Auto auto : listaAuto) {
	                    writer.write(auto.mostraDettagli());
	                    writer.newLine();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }

	            System.out.println("Auto rimossa con successo.");
	            
	            sovrascrivi(); // Aggiorna il file di backup
	        } else {
	            System.out.println("Auto non trovata nella lista.");
	        }
	    }
	}
	
	//copia le auto presenti sul file all'interno della listaAuto, sovrascrivendola
	public static boolean sincronizza(List<Auto> listaAuto) {
	    if (checkBackup()) {
		    scriviSuFile(listaAuto, false);
		    System.out.println("Sincronizzazione completata");
		    return true;
		} else {
		    System.out.println("Errore di sincronizzazione");
		    return false;
		}
	}
	
}
