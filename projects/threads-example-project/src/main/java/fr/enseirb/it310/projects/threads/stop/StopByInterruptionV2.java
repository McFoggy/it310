package fr.enseirb.it310.projects.threads.stop;

public class StopByInterruptionV2 implements Runnable {
    private Thread runningThread;

    public static void main(String[] args) {
        StopByInterruptionV2 stopableAction = new StopByInterruptionV2();
        Thread t = new Thread(stopableAction);
        t.start();

        // Let's wait
        try {
            Thread.sleep(250);
        } catch (InterruptedException ignore) {
        }

        stopableAction.stop();
    }

    @Override
    public void run() {
        runningThread = Thread.currentThread();

        int i = 0;
        while (!Thread.interrupted()) {
            System.out.println("Je sais compter jusqu'à " + ++i);
            Thread.yield();
        }
        System.out.println("On nous a arrété.");
    }

    private void stop() {
        System.out.format("Arrêt demandé par [%s]%n", Thread.currentThread().getName());
        runningThread.interrupt();
    }
}
