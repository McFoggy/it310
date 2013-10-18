package fr.enseirb.it310.projects.threads.stop;

public class StopLikeJavaTutorial implements Runnable {
    private volatile Thread runningThread;

    public static void main(String[] args) {
        StopLikeJavaTutorial stopableAction = new StopLikeJavaTutorial();
        Thread t = new Thread(stopableAction);
        t.start();

        // Let's wait
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ignore) {
        }

        stopableAction.stop();
    }

    @Override
    public void run() {
        runningThread = Thread.currentThread();

        int i = 0;
        while (runningThread == Thread.currentThread()) {
            System.out.println("Je sais compter jusqu'à " + ++i);
            try {
                Thread.sleep(250);
            } catch (InterruptedException ignore) {}
        }
        System.out.println("On nous a arrété.");
    }

    private void stop() {
        System.out.format("Arrêt demandé par [%s]%n", Thread.currentThread().getName());
        runningThread = null;
    }
}
