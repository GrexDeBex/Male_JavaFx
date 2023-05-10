package com.example.male_javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		GridPane grid = new GridPane();

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

		Image[] nupuPildid = {ettur_must, vanker_must, ratsu_must, oda_must, lipp_must, kuningas_must, ettur_valge,
				vanker_valge, ratsu_valge, oda_valge, lipp_valge, kuningas_valge};


		Laud laud = new Laud(grid, nupuPildid);

		ScrollPane scrollPane = new ScrollPane(grid);
		stage.setScene(new Scene(scrollPane));
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}