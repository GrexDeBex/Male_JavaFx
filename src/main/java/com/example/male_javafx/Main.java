package com.example.male_javafx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		GridPane mang = new GridPane();							// Mängu ruudustiku alus
		VBox menuu = new VBox();								// Menüü alus

		String tavaStiil = "-fx-background-color:#CD7F32; -fx-border-color: #000000; -fx-border-width: 2px; -fx-font-size: 40px";
		Button jatka = new Button("Jätka");
		Button alusta = new Button("Alusta");
		Button sulge = new Button("Sulge");

		Button[] menuuNupud = {jatka, alusta, sulge};

		for (Button nupp : menuuNupud) {
			nupp.setStyle(tavaStiil);
			menuu.getChildren().add(nupp);
			nupp.setPrefSize(200, 100);
		}


		sulge.setOnMouseClicked(event -> {
			System.exit(0);
		});

		ColumnConstraints veerg = new ColumnConstraints();		// Kannab hoolt, et akent saaks korralikult suurendada
		veerg.setPercentWidth(12.5);
		RowConstraints rida = new RowConstraints();
		rida.setPercentHeight(12.5);
		for (int i = 0; i < 8; i++) {
			mang.getColumnConstraints().add(veerg);
			mang.getRowConstraints().add(rida);
		}




		menuu.setAlignment(Pos.CENTER);							// Menüü nuppude paigutus
		menuu.setSpacing(100);
		menuu.setMaxSize(1000,1000);

		mang.setMaxSize(2000,2000);
		Laud mangulaud = new Laud(mang);

		Scene scene = new Scene(menuu);
		Scene manguStseen = new Scene(mang);

		stage.setScene(scene);
		stage.show();

		stage.setMinHeight(800);
		stage.setMinWidth(800);

		scene.getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event1 -> {		// Salvestab mängu sulgemisel laua seisu
			try {
				mangulaud.closeWindowEvent(event1);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});

		alusta.setOnMouseClicked(event -> {								// Kustutab logid ja loob uue ruudustiku
			try {
				looUus(stage);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});

		jatka.setOnMouseClicked(event -> {								// Jätkab eelmist mängu
			stage.setScene(manguStseen);
		});

	}

	/**
	 * Loob uue mängu
	 *
	 * @param stage	Aken, kuhu mäng luuakse
	 * @throws IOException
	 */
	public static void looUus(Stage stage) throws IOException {
		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("logi.dat"))){
			dos.writeBoolean(false);	// Kustutab andmed
		}

		GridPane mang = new GridPane();				// Tehakse samad protsessid nagu peameetodis


		ColumnConstraints veerg = new ColumnConstraints();
		veerg.setPercentWidth(12.5);
		RowConstraints rida = new RowConstraints();
		rida.setPercentHeight(12.5);
		for (int i = 0; i < 8; i++) {
			mang.getColumnConstraints().add(veerg);
			mang.getRowConstraints().add(rida);
		}


		mang.setMaxSize(2000,2000);
		Laud mangulaud = new Laud(mang);
		Scene manguStseen = new Scene(mang);
		stage.show();
		stage.setScene(manguStseen);

		manguStseen.getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event1 -> {
			try {
				mangulaud.closeWindowEvent(event1);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}



	public static void main(String[] args) {
		launch();
	}
}