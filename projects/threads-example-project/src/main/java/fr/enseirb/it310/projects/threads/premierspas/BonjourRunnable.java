package fr.enseirb.it310.projects.threads.premierspas;

public class BonjourRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("bonjour, " + i);
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignore) {}
        }
    }
    
    public static void main(String[] args) {
        BonjourRunnable sayHello = new BonjourRunnable();
        
        Thread executor = new Thread(sayHello);
        executor.start();
        
        System.out.println("C'est parti.");
    }
}
