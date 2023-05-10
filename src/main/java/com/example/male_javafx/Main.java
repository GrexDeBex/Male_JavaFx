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



		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {

				Button button = new Button();
				button.setMinSize(100,100);
				button.setMaxSize(100,100);
				grid.add(button, c, r);
				button.setStyle("-fx-background-color:#CD7F32; -fx-border-color: #000000; -fx-border-width: 2px");

				button.setOnMouseClicked(event -> {
					if (!Objects.equals(button.getGraphic().getId(), "0")) {
						if (button.getStyle().equals("-fx-background-color:#CD7F32; -fx-border-color: #000000; -fx-border-width: 2px")) {
							button.setStyle("-fx-background-color:#228B22; -fx-border-color: #000000; -fx-border-width: 2px");
						} else {
							button.setStyle("-fx-background-color:#CD7F32; -fx-border-color: #000000; -fx-border-width: 2px");
						}
					}
				});

				ImageView imageView = new ImageView();
				imageView.setId("0");



				if (r == 1)
					imageView = new ImageView(ettur_must);
				if (r == 6)
					imageView = new ImageView(ettur_valge);


				if (r == 0){
					if (c == 0 || c == 7)
						imageView = new ImageView(vanker_must);
					if (c == 1 || c == 6)
						imageView = new ImageView(ratsu_must);
					if (c == 2 || c == 5)
						imageView = new ImageView(oda_must);
					if (c == 3)
						imageView = new ImageView(lipp_must);
					if (c == 4)
						imageView = new ImageView(kuningas_must);
				}

				if (r == 7){
					if (c == 0 || c == 7)
						imageView = new ImageView(vanker_valge);
					if (c == 1 || c == 6)
						imageView = new ImageView(ratsu_valge);
					if (c == 2 || c == 5)
						imageView = new ImageView(oda_valge);
					if (c == 3)
						imageView = new ImageView(lipp_valge);
					if (c == 4)
						imageView = new ImageView(kuningas_valge);
				}


				imageView.setFitHeight(100);
				imageView.setFitWidth(100);
				button.setGraphic(imageView);

			}
		}







		ScrollPane scrollPane = new ScrollPane(grid);
		stage.setScene(new Scene(scrollPane));
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}