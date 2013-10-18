package fr.enseirb.it310.projects.pools;

public class NonPooledThreads {
    public static void main(String[] args) {
        // Try with 100, 1000, 10000
        for (int i = 0; i < 10000; i++) {
            new Thread(UnePersonnePolie.creeUneNouvellePersonne()).start();
        }
    }
}
