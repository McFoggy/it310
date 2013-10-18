package fr.enseirb.it310.projects.banqueroute;

public class Guichet {
	private final CompteEnBanque compte;

	public Guichet(CompteEnBanque n) {
		this.compte = n;
	}

	/*
	 * Il n'est pas possible de retirer si le solde n'est pas positif
	 */
	public void retrait() {
		if (compte.getSolde() > 0) { 
			Thread.yield();
			compte.retireUn();
		}
	}
}
