package fr.enseirb.it310.projects.threads.volatilekeyword;

public class VolatileStopper {
	private static boolean stopFlag;

	public static void main(String[] args) {
		Thread waiter = new Thread(new StopWaiter());
		Thread stopper = new Thread(new Stopper());
		
		stopper.start();
		waiter.start();
	}
	
	static class StopWaiter implements Runnable {
		@Override
		public void run() {
			boolean stop = stopFlag;
			while (!stop) {
				System.out.format("I am waiting for someone to stop me%n");
				try {
					Thread.sleep(400L);
				} catch (InterruptedException e) {
				}
				stop = stopFlag;
			}
		}
	}
	static class Stopper implements Runnable {
		@Override
		public void run() {
			// Let's wait 2 seconds and then stop
			try {
				Thread.sleep(2000L);
			} catch (InterruptedException e) {
			}

			System.out.format("Let's stop%n");
			stopFlag = true;
		}
	}
}
