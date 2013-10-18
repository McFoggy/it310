package fr.enseirb.it310.projects.threads.stop;


public class StopThreadBad implements Runnable {
    public static void main(String[] args) {
        Thread t = new Thread(new StopThreadBad());
        t.start();
        
        // Let's wait
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ignore) {}
        
        // DO NOT DO THIS
        System.out.format("Arrêt forcé par [%s]%n", Thread.currentThread().getName());
        t.stop();
    }

    @Override
    public void run() {
        int i = 0;
        
        while (true) {
            System.out.println("Je sais compter jusqu'à " + ++i);
            try {
                Thread.sleep(200l);
            } catch (InterruptedException e) {
            }
        }
    }
}
