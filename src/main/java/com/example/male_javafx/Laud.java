package com.example.male_javafx;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Laud {
	private boolean aktiveeritud;
	private final GridPane grid;
	private final Image[] nupuPildid;
	private Button[][] mangulaudNupud;
	private Button ajutine;
	private Mangija mangija1, mangija2, kaiguTegija, vastane;
	private Mangulaud mangulaud;

	public Laud(GridPane grid) throws FileNotFoundException {
		Image ettur_must = new Image(new FileInputStream("pildid/ettur_must.png"));
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

		genereeri();
		kaivitaMang();
	}

	public void genereeri(){

		String tavaStiil = "-fx-background-color:#CD7F32; -fx-border-color: #000000; -fx-border-width: 2px";
		mangulaudNupud = new Button[8][8];

		for (int rida = 0; rida < 8; rida++) {
			for (int veerg = 0; veerg < 8; veerg++) {

				Button button = new Button();
				button.setMinSize(100,100);
				button.setStyle(tavaStiil);
				button.setOnMouseClicked(event -> nuppEvent(button));
				button.setId(nupuTuup(rida, veerg));
				grid.add(button, veerg, rida);
				mangulaudNupud[rida][veerg] = button;
			}
		}
		laePildid();
	}

	public void laePildid(){
		for (Button[] rida : mangulaudNupud) {
			for (Button nupp : rida) {
				nupp.setGraphic(leiaPilt(nupp.getId()));
			}
		}
	}

	public void nuppEvent(Button button){
		String aktiveeritudStiil = "-fx-background-color:#228B22; -fx-border-color: #000000; -fx-border-width: 2px";
		String tavaStiil = "-fx-background-color:#CD7F32; -fx-border-color: #000000; -fx-border-width: 2px";

		boolean mangijaNupp = false;
		if (!aktiveeritud) {
			for (Nupp nupp : kaiguTegija.getNupud()) {
				if (nupp.getNimi().equals(button.getId())) {
					mangijaNupp = true;
					break;
				}
			}
		}

		if (button.getStyle().equals(aktiveeritudStiil)){
			button.setStyle(tavaStiil);
			aktiveeritud = false;
			return;
		}

		if (aktiveeritud){
			int[] koord1 = leiaKooridnaadid(ajutine);
			int[] koord2 = leiaKooridnaadid(button);

			if (sooritaKaik(koord1[0], koord1[1], koord2[0], koord2[1])){
				aktiveeritud = false;
				laePildid();
				ajutine.setStyle(tavaStiil);

				boolean lopp = true;
				for (Nupp nupp : kaiguTegija.getNupud()) {
					if (nupp.getNimi().equals("kuningasV") || nupp.getNimi().equals("kuningasM")){
						lopp = false;
					}
				}

				if (lopp)
					System.exit(0);
			}
			return;
		}

		if (mangijaNupp) {
			button.setStyle(aktiveeritudStiil);
			aktiveeritud = true;
			ajutine = button;
		}
	}


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

	public String nupuTuup(int rida, int veerg) {
		if (rida == 1)
			return "etturM";
		if (rida == 6)
			return "etturV";

		if (rida == 0){
			if (veerg == 0 || veerg == 7)
				return "vankerM";
			if (veerg == 1 || veerg == 6)
				return "ratsuM";
			if (veerg == 2 || veerg == 5)
				return "odaM";
			if (veerg == 3)
				return "lippM";
			if (veerg == 4)
				return "kuningasM";
		}

		if (rida == 7){
			if (veerg == 0 || veerg == 7)
				return "vankerV";
			if (veerg == 1 || veerg == 6)
				return "ratsuV";
			if (veerg == 2 || veerg == 5)
				return "odaV";
			if (veerg == 3)
				return "lippV";
			if (veerg == 4)
				return "kuningasV";
		}

		return "0";
	}


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

		for (int rida = 0; rida < 8; rida++) {
			for (int veerg = 0; veerg < 8; veerg++) {
				Nupp nupp = mangulaud.getLaud()[rida][veerg];
				if (nupp != null)
					mangulaudNupud[rida][veerg].setId(nupp.getNimi());
				else
					mangulaudNupud[rida][veerg].setId("0");
			}
		}

		return true;
	}

	public int[] leiaKooridnaadid(Button button){
		for (int rida = 0; rida < 8; rida++)
			for (int veerg = 0; veerg < 8; veerg++)
				if (button.equals(mangulaudNupud[rida][veerg]))
					return new int[]{rida, veerg};

		return null;
	}
}
