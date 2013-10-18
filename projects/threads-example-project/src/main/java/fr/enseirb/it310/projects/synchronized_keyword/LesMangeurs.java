package fr.enseirb.it310.projects.synchronized_keyword;

import java.util.Random;

public class LesMangeurs {
    private Object leCouteau = new Couteau();
    private Object laFourchette = new Fourchette();
    private Object[] lesCouverts = new Object[] {leCouteau, laFourchette};
    
    public void vontAuRestaurant() {
        new Thread(new UnMangeur("Astérix")).start();
        new Thread(new UnMangeur("Obélix")).start();
    }
    
    public static void main(String[] args) {
        new LesMangeurs().vontAuRestaurant();
    }
    
    private class Couteau {
        @Override
        public String toString() {
            return "le couteau";
        }
    }
    private class Fourchette {
        @Override
        public String toString() {
            return "la fourchette";
        }
    }
    
    private class UnMangeur implements Runnable {
        private Random r = new Random(new Object().hashCode());
        private String nom;
        
        UnMangeur(String nom) {
            this.nom = nom;
        }
        
        @Override
        public void run() {
            while (!Thread.interrupted()) {
                passeATable();
                respireUnPeu();
            }
        }

        private void respireUnPeu() {
            try {
                Thread.sleep(10 + r.nextInt(50));
            } catch (InterruptedException e) {}
        }

        private void passeATable() {
            int couvertIndex = r.nextInt(2); 
            Object couvert1 = lesCouverts[couvertIndex];
            Object couvert2 = lesCouverts[1 - couvertIndex];
            
            System.out.println(String.format("Le mangeur [%s] passe à table", nom));
            synchronized (couvert1) {
                System.out.println(String.format("Le mangeur [%s] a pris %s", nom, couvert1.toString()));
                respireUnPeu();
                synchronized (couvert2) {
                    System.out.println(String.format("Le mangeur [%s] a pris %s", nom, couvert2.toString()));
                    mange();
                    respireUnPeu();
                }
                System.out.println(String.format("Le mangeur [%s] a posé %s", nom, couvert2.toString()));
            }
            System.out.println(String.format("Le mangeur [%s] a posé %s", nom, couvert1.toString()));
        }

        private void mange() {
            System.out.println(String.format("Le mangeur [%s] mange", nom));
        }
    }
}
