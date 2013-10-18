package fr.enseirb.it310.projects.pools;

import java.util.concurrent.atomic.AtomicInteger;

public class UnePersonnePolie implements Runnable {
    private String name;

    private UnePersonnePolie(final String name) {
        this.name = name;
    }
    
    @Override
    public void run() {
        System.out.println("Bonjour, Je m'appelle " + name + ". Je m'exécute sur le thread : " + Thread.currentThread().getName());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        
        System.out.println("[" + name + "] Au revoir.");
    }
    
    public static UnePersonnePolie creeUneNouvellePersonne() {
        return new UnePersonnePolie("PERS-" + id.incrementAndGet());
    }
    
    private final static AtomicInteger id = new AtomicInteger(0);
}
