package fr.enseirb.it310.projects.compteurs;

public class Compteur {
    private int valeur = 0;
    
    /**
     * Incremente & renvoie la valeur du compteur
     * @return la valeur du compteur incrémentée de 1
     */
    public synchronized int incremente() {
    	return valeur++;
    }
    
    /**
     * Renvoie la valeur actuelle du compteur
     * @return la valeur du compteur
     */
    public int getValeur() {
        return valeur;
    }
}
