package fr.enseirb.it310.projects.threads.stop;

public class StopByInterruptionV1 implements Runnable {
    private Thread runningThread;

    public static void main(String[] args) {
        StopByInterruptionV1 stopableAction = new StopByInterruptionV1();
        Thread t = new Thread(stopableAction);
        t.start();

        // Let's wait
        try {
            Thread.sleep(2500l);
        } catch (InterruptedException ignore) {
        }

        stopableAction.stop();
    }

    @Override
    public void run() {
        runningThread = Thread.currentThread();

        int i = 0;
        try {
            while (true) {
                System.out.println("Je sais compter jusqu'à " + ++i);
                Thread.sleep(200l);
            }
        } catch (InterruptedException e) {
            System.out.println("On nous a arrété.");
            Thread.interrupted();
        }
        
        // nettoyage de ressources
    }

    public void stop() {
        System.out.format("Arrêt demandé par [%s]%n", Thread.currentThread().getName());
        runningThread.interrupt();
    }
}
