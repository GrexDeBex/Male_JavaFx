package com.example.male_javafx;

/**
 * Mängulaua klass
 * <p>
 * Klassi abil luuakse mängulaud ning sisaldab mängu üldfunktsioone
 */


public class Mangulaud {
	final private Nupp[][] laud;                        // Mängulaua maatriks


	public Mangulaud(Mangija mangijaMust, Mangija mangijaValge) {
		Nupp[][] laud = new Nupp[8][8];
		asetaNupudLauale(laud, mangijaMust, mangijaValge);

		this.laud = laud;
	}

	public Nupp[][] getLaud() {
		return laud;
	}


	/**
	 * Loob kõik vajalikud nupud, lisab need lauale ning mängijate nuppude listidesse
	 *
	 * @param laud         Mängulaud
	 * @param mangijaMust  Mustadega mängija
	 * @param mangijaValge Valgetega mängija
	 */
	public static void asetaNupudLauale(Nupp[][] laud, Mangija mangijaMust, Mangija mangijaValge) {
		String[] nimetused = {"vanker", "ratsu", "oda", "lipp", "kuningas", "oda", "ratsu", "vanker"};    // Kõik võimalikud nupud

		for (int veerg = 0; veerg < 8; veerg++) {            // Käib läbi iga veeru

			// Must ettur
			Nupp mustEttur = new Nupp("etturM", 'm', 1, veerg);
			mangijaMust.getNupud().add(mustEttur);
			laud[1][veerg] = mustEttur;

			// Valge ettur
			Nupp valgeEttur = new Nupp("etturV", 'v', 6, veerg);
			mangijaValge.getNupud().add(valgeEttur);
			laud[6][veerg] = valgeEttur;

			// Must nupp veerus
			Nupp mustNupp = new Nupp(nimetused[veerg] + "M", 'm', 0, veerg);
			mangijaMust.getNupud().add(mustNupp);
			laud[0][veerg] = mustNupp;

			// Valge nupp veerus
			Nupp valgeNupp = new Nupp(nimetused[veerg] + "V", 'v', 7, veerg);
			mangijaValge.getNupud().add(valgeNupp);
			laud[7][veerg] = valgeNupp;

		}
	}
}
