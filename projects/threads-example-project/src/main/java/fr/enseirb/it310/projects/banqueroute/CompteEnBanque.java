package fr.enseirb.it310.projects.banqueroute;

public class CompteEnBanque {
    private int solde = 0;
    
    public CompteEnBanque(int soldeInitial) {
        solde = soldeInitial;
    }
    
    public synchronized int getSolde() {
        return solde;
    }
    
    public synchronized void retireUn() {
        solde--;
    }
}
