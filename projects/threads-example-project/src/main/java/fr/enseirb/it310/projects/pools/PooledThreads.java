package fr.enseirb.it310.projects.pools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PooledThreads {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(100);
        
        // Try with 100, 1000, 3000
        for (int i = 0; i < 3000; i++) {
            es.execute(UnePersonnePolie.creeUneNouvellePersonne());
        }
        
        es.shutdown();
    }
}
