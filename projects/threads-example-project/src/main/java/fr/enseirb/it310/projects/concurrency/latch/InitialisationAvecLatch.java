package fr.enseirb.it310.projects.concurrency.latch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class InitialisationAvecLatch {
	private static Random r = new Random();
	
	public static void main(String[] args) {
		int nbInitThread = 5;
		final CountDownLatch gate = new CountDownLatch(nbInitThread);
		
		new Thread(new LeProgramme(gate)).start();
		for (int i = 0; i < nbInitThread; i++) {
			new Thread(new Initialisation(i+1, gate)).start();
		}
		
	}
	
	static class Initialisation implements Runnable {
		private CountDownLatch gate;
		private int numero;

		public Initialisation(int numero, CountDownLatch gate) {
			this.gate = gate;
			this.numero = numero;
		}
		
		@Override
		public void run() {
			System.out.format("L'initialisation %d est en cours%n", numero);
			try {
				Thread.sleep(r.nextInt(100)+200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.format("L'initialisation %d se termine%n", numero);
			gate.countDown();
		}
	}
	static class LeProgramme implements Runnable {
		private CountDownLatch gate;

		public LeProgramme(CountDownLatch gate) {
			this.gate = gate;
		}

		@Override
		public void run() {
			System.out.println("Le programme attend les intialisations");
			
			try {
				gate.await();
			} catch (InterruptedException e) {
				System.err.println("On nous demande de ne pas démarrer");
				return;
			}
			
			System.out.println("Un programme constructif s'exécute...");
			System.out.println("et se termine.");
		}
	}
}
