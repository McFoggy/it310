package fr.enseirb.it310.projects.threads.premierspas;

public class BonjourDaemon implements Runnable {
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
        BonjourDaemon sayHello = new BonjourDaemon();
        Thread executor = new Thread(sayHello);
        executor.setDaemon(true);
        executor.start();
        
        System.out.println("C'est parti.");
        System.out.println("Attendons un peu");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}
        System.out.println("Bye Bye");
    }
}
