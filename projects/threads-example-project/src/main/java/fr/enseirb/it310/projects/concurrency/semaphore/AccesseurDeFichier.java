package fr.enseirb.it310.projects.concurrency.semaphore;

import java.io.File;
import java.util.concurrent.Semaphore;

public class AccesseurDeFichier {
	private final Semaphore limiteur;
	public AccesseurDeFichier(int maxAcces) {
		limiteur = new Semaphore(maxAcces, true);
	}
	
	public File ouvreFichier(String cheminDuFichier) {
		try {
			limiteur.acquire();
			return new File(cheminDuFichier);
		} catch (InterruptedException e) {
			return null;
		}
	}
	
	public void libereFichier(File f) {
		// nettoyer si besoin
		limiteur.release();
	}
}
