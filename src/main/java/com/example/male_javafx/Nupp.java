package com.example.male_javafx;

/**
 * Nupu klass
 * <p>
 * Klassis omastatakse igale nupule vajalikud omadused
 */


public class Nupp {
	private String nimi;                    // Nuppu eristav nimetus
	final private char varv;                // Määrab nupu värvi
	private int asukohty;                    // Nupu veerg
	private int asukohtx;                    // Nupu rida
	private boolean enPassant;                // Määrab, kas on võimalik seda nuppu rünnata en passantiga
	private boolean kasPoleLiikunud;        // Määrab, kas nupp on oma algpositsioonilt liikunud

	public Nupp(String nimi, char varv, int asukohtx, int asukohty) {
		this.nimi = nimi;
		this.varv = varv;
		this.enPassant = false;
		this.kasPoleLiikunud = true;
		this.asukohtx = asukohtx;
		this.asukohty = asukohty;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public char getVarv() {
		return varv;
	}

	public int getAsukohtx() {
		return asukohtx;
	}

	public void setAsukohtx(int asukohtx) {
		this.asukohtx = asukohtx;
	}

	public int getAsukohty() {
		return asukohty;
	}

	public void setAsukohty(int asukohty) {
		this.asukohty = asukohty;
	}

	public boolean isEnPassant() {
		return enPassant;
	}

	public void setEnPassant(boolean väärtus) {
		this.enPassant = väärtus;
	}

	public boolean kasPoleLiikunud() {
		return this.kasPoleLiikunud;
	}

	public void setKasLiikunud(boolean bool) {
		this.kasPoleLiikunud = bool;
	}
}
