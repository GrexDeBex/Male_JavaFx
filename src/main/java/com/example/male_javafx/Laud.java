package com.example.male_javafx;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Laud {
	private boolean aktiveeritud;		// Kontrollib, kas mõni ruut on hetkel roheline
	private final GridPane grid;		// Mängulaua alus
	private final Image[] nupuPildid;	// Kõigi nuppude pildid
	private Button[][] mangulaudNupud;	// Kõigi kastide massiiv
	private Button ajutine;				// Jätab meelde, milline nupp on hetkel aktiveeritud
	private Mangija mangija1, mangija2, kaiguTegija, vastane;
	private Mangulaud mangulaud;		// Käikude kontrollimiseks male massiiv

	/**
	 * Loob laua objekti
	 *
	 * @param grid	Alus, millele ruudustik luuakse
	 * @throws IOException Erind
	 */
	public Laud(GridPane grid) throws IOException {
		Image ettur_must = new Image(new FileInputStream("pildid/ettur_must.png"));				// Laeb pildid sisse
		Image vanker_must = new Image(new FileInputStream("pildid/vanker_must.png"));
		Image ratsu_must = new Image(new FileInputStream("pildid/ratsu_must.png"));
		Image oda_must = new Image(new FileInputStream("pildid/oda_must.png"));
		Image lipp_must = new Image(new FileInputStream("pildid/lipp_must.png"));
		Image kuningas_must = new Image(new FileInputStream("pildid/kuningas_must.png"));

		Image ettur_valge = new Image(new FileInputStream("pildid/ettur_valge.png"));
		Image vanker_valge = new Image(new FileInputStream("pildid/vanker_valge.png"));
		Image ratsu_valge = new Image(new FileInputStream("pildid/ratsu_valge.png"));
		Image oda_valge = new Image(new FileInputStream("pildid/oda_valge.png"));
		Image lipp_valge = new Image(new FileInputStream("pildid/lipp_valge.png"));
		Image kuningas_valge = new Image(new FileInputStream("pildid/kuningas_valge.png"));

		this.grid = grid;
		this.nupuPildid = new Image[]{ettur_must, vanker_must, ratsu_must, oda_must, lipp_must, kuningas_must,
				ettur_valge, vanker_valge, ratsu_valge, oda_valge, lipp_valge, kuningas_valge};

		try {
			if (!loeSisse())					// Kontrollib, kas on olemas eelmise mängu andmed
				kaivitaMang();					// Kui ei ole, siis luuakse uus mäng
		}catch (FileNotFoundException e){
			kaivitaMang();
		}

		genereeriRuudustik();	// Loob ruudustiku
	}

	/**
	 * Loob nähtava maleruudustiku
	 *
	 */
	public void genereeriRuudustik(){

		String tavaStiil = "-fx-background-color:#CD7F32; -fx-border-color: #000000; -fx-border-width: 2px";
		mangulaudNupud = new Button[8][8];

		for (int rida = 0; rida < 8; rida++) {
			for (int veerg = 0; veerg < 8; veerg++) {

				Button button = new Button();
				button.setMinSize(100,100);
				button.setPrefSize(100, 100);
				button.setMaxSize(Integer.MAX_VALUE,Integer.MAX_VALUE);
				button.setStyle(tavaStiil);
				button.setOnMouseClicked(event -> nuppEvent(button));
				grid.add(button, veerg, rida);
				mangulaudNupud[rida][veerg] = button;

			}
		}
		sunkroniseeriLauad();	// viib arvti kontroll lauaga nähtava malelaua kooskõlasse
	}

	/**
	 * Paigutab igale ruudule vastava pildi
	 */
	public void laePildid(){
		for (Button[] rida : mangulaudNupud) {
			for (Button nupp : rida) {
				nupp.setGraphic(leiaPilt(nupp.getId()));
			}
		}
	}

	/**
	 * Kui vajutada nupu peale, siis läheb nupu taust roheliseks, kui uuesti selle peale vajutada, läheb tagasi.
	 * Kui vajutada peale roheliseks tegemist sobiva käigu peale, siis see sooritatakse, ebasobivat ei lasta teha
	 *
	 * @param button Nupp
	 */
	public void nuppEvent(Button button){
		String aktiveeritudStiil = "-fx-background-color:#228B22; -fx-border-color: #000000; -fx-border-width: 2px";
		String tavaStiil = "-fx-background-color:#CD7F32; -fx-border-color: #000000; -fx-border-width: 2px";

		boolean mangijaNupp = false;					// Kontrollib, kas vajutatud nupp on mängija nupp
		if (!aktiveeritud) {
			for (Nupp nupp : kaiguTegija.getNupud()) {
				if (nupp.getNimi().equals(button.getId())) {
					mangijaNupp = true;
					break;
				}
			}
		}

		if (button.getStyle().equals(aktiveeritudStiil)){	// Kui vajutatakse sama nuppu uuesti
			button.setStyle(tavaStiil);
			aktiveeritud = false;
			return;
		}

		if (aktiveeritud){									// Kui üritatakse käiku sooritada
			int[] koord1 = leiaKooridnaadid(ajutine);
			int[] koord2 = leiaKooridnaadid(button);

			if (sooritaKaik(koord1[0], koord1[1], koord2[0], koord2[1])){

				aktiveeritud = false;
				laePildid();
				ajutine.setStyle(tavaStiil);

				boolean lopp = true;								// Kontrollib mängu lõppu
				for (Nupp nupp : kaiguTegija.getNupud()) {
					if (nupp.getNimi().equals("kuningasV") || nupp.getNimi().equals("kuningasM")){
						lopp = false;
						break;
					}
				}

				if (lopp)
					System.exit(0);
			}
			return;
		}

		if (mangijaNupp) {							// Tehakse valitud nupp roheliseks
			button.setStyle(aktiveeritudStiil);
			aktiveeritud = true;
			ajutine = button;
		}
	}

	/**
	 * Leiab nuputüübile vastava pildi
	 *
	 * @param tuup Nuputüüp
	 * @return	Pilt
	 */
	public ImageView leiaPilt(String tuup){
		ImageView imageView = new ImageView();
		List<String> tuubid = Arrays.asList("etturM", "vankerM", "ratsuM", "odaM", "lippM", "kuningasM", "etturV",
		 "vankerV", "ratsuV", "odaV", "lippV", "kuningasV");

		int indeks = tuubid.indexOf(tuup);

		if (indeks != -1)
			imageView = new ImageView(nupuPildid[indeks]);

		imageView.setFitHeight(100);
		imageView.setFitWidth(100);

		return imageView;
	}


	/**
	 *	Loob uue mängulaua, mida kasutatkase kontrollimiskes.
	 */
	public void kaivitaMang() {
		mangija1 = new Mangija();					// Luuakse mängijad
		mangija2 = new Mangija();
		Mangija.seaMängijateVärvid(mangija1, mangija2);		// Jaotatkse värvid

		if (mangija1.getVarv() == 'v') {					// Määratakse mängijate järjekord ja luuakse mängulaud
			kaiguTegija = mangija1;
			vastane = mangija2;
			mangulaud = new Mangulaud(mangija2, mangija1);
		} else {
			kaiguTegija = mangija2;
			vastane = mangija1;
			mangulaud = new Mangulaud(mangija1, mangija2);
		}
	}


	/**
	 * Kontrollib, kas käik on sobilik
	 *
	 * @param algusRida	Nupu rida
	 * @param algusVeerg Nuou veerg
	 * @param loppRida	Sihtkoha rida
	 * @param loppVeerg	Sihtkoha veerg
	 * @return Kas käiku sai sooritada
	 */
	public boolean sooritaKaik(int algusRida, int algusVeerg, int loppRida, int loppVeerg){


		Nupp valitudNupp = mangulaud.getLaud()[algusRida][algusVeerg];
		if (!kaiguTegija.getNupud().contains(valitudNupp))
			return false;



		if (!KaiguKontroll.kontroll(valitudNupp, loppRida, loppVeerg, mangulaud.getLaud(), vastane))
			return false;

		if (kaiguTegija == mangija1) {				// Määrab, kelle kord on järgmine käik teha
			kaiguTegija = mangija2;
			vastane = mangija1;
		} else {
			kaiguTegija = mangija1;
			vastane = mangija2;
		}

		sunkroniseeriLauad();

		return true;
	}

	/**
	 * Sünkroniseerib kontrollitava mängulaua selle mängulaua, mida mängija näeb
	 */
	public void sunkroniseeriLauad(){
		for (int rida = 0; rida < 8; rida++) {
			for (int veerg = 0; veerg < 8; veerg++) {
				Nupp nupp = mangulaud.getLaud()[rida][veerg];
				if (nupp != null)
					mangulaudNupud[rida][veerg].setId(nupp.getNimi());
				else
					mangulaudNupud[rida][veerg].setId("0");
			}
		}
		laePildid();
	}

	/**
	 * Leiab nupu kordinaadid ruudustikul
	 *
	 * @param button Nupp
	 * @return Massiiv, kus on rida ja veerg
	 */
	public int[] leiaKooridnaadid(Button button){
		for (int rida = 0; rida < 8; rida++)
			for (int veerg = 0; veerg < 8; veerg++)
				if (button.equals(mangulaudNupud[rida][veerg]))
					return new int[]{rida, veerg};

		return null;
	}

	/**
	 * Mängu sulgemisel salvestab seisu
	 *
	 * @throws IOException	Erind
	 */
	public void closeWindowEvent() throws IOException {
		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("logi.dat"))){

			dos.writeBoolean(true);


			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (mangulaud.getLaud()[i][j] != null)
						dos.writeBoolean(true);
					else {
						dos.writeBoolean(false);
						continue;
					}

					Nupp nupp = mangulaud.getLaud()[i][j];
					dos.writeUTF(nupp.getNimi());
					dos.writeChar(nupp.getVarv());
					dos.writeInt(nupp.getAsukohty());
					dos.writeInt(nupp.getAsukohtx());
					dos.writeBoolean(nupp.isEnPassant());
					dos.writeBoolean(nupp.kasPoleLiikunud());
				}
			}

			if (kaiguTegija.equals(mangija1)) {
				dos.writeInt(1);
			} else {
				dos.writeInt(2);
			}

		}
	}

	/**
	 * Loeb eelmise mängu seisu sisse
	 *
	 * @return	Kas eelmine mäng leiti
	 * @throws IOException	Erind
	 */
	public boolean loeSisse() throws IOException {
		try (DataInputStream dis = new DataInputStream(new FileInputStream("logi.dat"))){

			if (!dis.readBoolean())		// Peab järge, kas andmeid on või ei ole
				return false;



			Nupp[][] tabel = new Nupp[8][8];
			ArrayList<Nupp> mangijaNupudValge = new ArrayList<>();
			ArrayList<Nupp> mangijaNupudMust = new ArrayList<>();

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (!dis.readBoolean())
						continue;


					Nupp nupp = new Nupp(dis.readUTF(), dis.readChar(), dis.readInt(), dis.readInt(), dis.readBoolean(), dis.readBoolean());
					tabel[i][j] = nupp;

					if (nupp.getVarv() == 'v')
						mangijaNupudValge.add(nupp);
					else
						mangijaNupudMust.add(nupp);
				}
			}


			this.mangulaud = new Mangulaud(tabel);
			this.mangija1 = new Mangija('v', mangijaNupudValge);
			this.mangija2 = new Mangija('m', mangijaNupudMust);

			if (dis.readInt() == 1){
				this.kaiguTegija = mangija1;
				this.vastane = mangija2;
			}else {
				this.kaiguTegija = mangija2;
				this.vastane = mangija1;
			}
		}

		return true;
	}
}
