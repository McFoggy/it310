package fr.enseirb.it310.projects.concurrency.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Calculateur {
	private final Calcul calcul;
	private final ReadWriteLock protectionDeCalcul;
	private int valeurDeBase;
	private int valeurCalculee;
	
	public Calculateur(Calcul c) {
		this.calcul =c;
		this.protectionDeCalcul = new ReentrantReadWriteLock(true);
	}
	
	public void faitLeCalcul(int nouvelleValeurDeBase) {
		Lock writeLock = protectionDeCalcul.writeLock();
		writeLock.lock();
		try {
			valeurDeBase = nouvelleValeurDeBase;
			valeurCalculee = calcul.effectueLeCalcul(nouvelleValeurDeBase);
		} finally {
			writeLock.unlock();
		}
	}
	
	public int getValeurDeBase() {
		Lock readLock = protectionDeCalcul.readLock();
		readLock.lock();
		try {
			return valeurDeBase;
		} finally {
			readLock.unlock();
		}
	}

	public int getValeurCalculee() {
		Lock readLock = protectionDeCalcul.readLock();
		readLock.lock();
		try {
			return valeurCalculee;
		} finally {
			readLock.unlock();
		}
	}

	public interface Calcul {
		/**
		 * Effectue un calcule complexe
		 * @param valeurDeBase
		 * @return la valeur calculée
		 */
		public int effectueLeCalcul(int valeurDeBase);
	}
}
