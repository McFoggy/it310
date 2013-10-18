package fr.enseirb.it310.projects.threads.premierspas;

public class BonjourThread extends Thread {
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
        BonjourThread sayHello = new BonjourThread();
        sayHello.start();
        System.out.println("C'est parti.");
    }
}








