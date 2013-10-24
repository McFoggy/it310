package fr.enseirb.it310.projects.banqueroute.atomic;

import java.util.concurrent.atomic.AtomicInteger;


public class CompteEnBanque {
    private final AtomicInteger solde;
    
    public CompteEnBanque(int soldeInitial) {
        solde = new AtomicInteger(soldeInitial);
    }
    
    public int getSolde() {
        return solde.get();
    }
    
    public boolean retireUn(int soldeADecrementer) {
        return solde.compareAndSet(soldeADecrementer, soldeADecrementer-1);
    }
}
