package fr.enseirb.it310.projects.banqueroute.atomic;

public class Guichet {
	private final CompteEnBanque compte;

	public Guichet(CompteEnBanque n) {
		this.compte = n;
	}

	/*
	 * Il n'est pas possible de retirer si le solde n'est pas positif
	 */
	public void retrait() {
		while (compte.getSolde() > 0) {
			Thread.yield();
			int actualVisibleSolde = compte.getSolde();
			if (actualVisibleSolde > 0 && compte.retireUn(actualVisibleSolde)) {
				return;
			}
		}
	}
}
