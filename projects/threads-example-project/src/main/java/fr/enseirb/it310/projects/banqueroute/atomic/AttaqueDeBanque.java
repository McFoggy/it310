package fr.enseirb.it310.projects.banqueroute.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AttaqueDeBanque {
    public static void main(String[] args) {
        final CompteEnBanque monCompte = new CompteEnBanque(100);
        
        Runnable unCopain = new Runnable() {
            @Override
            public void run() {
                // mon copain va a un guichet
                Guichet g = new Guichet(monCompte);
                
                // Il retire/tente de retirer 5 fois des sous
                for (int i = 0; i < 5; i++) {
                    g.retrait();
                }
            }
        };
        
        ExecutorService es = Executors.newCachedThreadPool();
        
        // J'ai 500 copains qui vont tenter de retirer en même temps
        for (int i = 0; i < 200; i++) {
            es.execute(unCopain);
        }
        
        es.shutdown();
        try {
            // On attend 5 secondes les retardataires
            es.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {}
        
        // L'état du compte
        System.out.println("Crédit du compte: " + monCompte.getSolde());
        
    }
}
