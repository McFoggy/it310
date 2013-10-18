package fr.enseirb.it310.projects.compteurs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LeZenithFAitLePlein {
    public static void main(String[] args) {
        // 10000 personnes entrent dans une salle par 10 portes
        // A chaque personne qui rentre, un compteur central est incrémenté
        final int NB_PERSONNES = 10000;
        final int NB_PORTES = 10;
        
        ExecutorService es = Executors.newFixedThreadPool(NB_PORTES);
        final Compteur compteur = new Compteur();
        
        for (int i = 0; i < NB_PERSONNES; i++) {
            Runnable quelqunPasseUnePorte = new Runnable() {
                @Override
                public void run() {
                    // quelqu'un passe la porte
                    compteur.incremente();
                }
            };
            es.execute(quelqunPasseUnePorte);
        }

        // On attend que tout le monde soit rentré
        es.shutdown();
        try {
            es.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
        }
        
        System.out.println(String.format("On a compté: %d passages", compteur.getValeur()));
    }
}
