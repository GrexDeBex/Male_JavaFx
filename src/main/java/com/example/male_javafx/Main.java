package com.example.male_javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

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


				if (r == 1){
					ImageView imageView = new ImageView(ettur_must);
					imageView.setFitHeight(100);
					imageView.setFitWidth(100);
					button.setGraphic(imageView);
				}

				if (r == 6){
					ImageView imageView = new ImageView(ettur_valge);
					imageView.setFitHeight(100);
					imageView.setFitWidth(100);
					button.setGraphic(imageView);
				}

				if (c == 0 || c == 7){
					if (r == 0){
						ImageView imageView = new ImageView(vanker_must);
						imageView.setFitHeight(100);
						imageView.setFitWidth(100);
						button.setGraphic(imageView);
					}
					if (r == 7){
						ImageView imageView = new ImageView(vanker_valge);
						imageView.setFitHeight(100);
						imageView.setFitWidth(100);
						button.setGraphic(imageView);
					}
				}


				if (r == 0){
					if (c == 0 || c == 7){
						ImageView imageView = new ImageView(vanker_must);
						imageView.setFitHeight(100);
						imageView.setFitWidth(100);
						button.setGraphic(imageView);
					}
					if (c == 1 || c == 6){
						ImageView imageView = new ImageView(ratsu_must);
						imageView.setFitHeight(100);
						imageView.setFitWidth(100);
						button.setGraphic(imageView);
					}
					if (c == 2 || c == 5){
						ImageView imageView = new ImageView(oda_must);
						imageView.setFitHeight(100);
						imageView.setFitWidth(100);
						button.setGraphic(imageView);
					}
					if (c == 3){
						ImageView imageView = new ImageView(lipp_must);
						imageView.setFitHeight(100);
						imageView.setFitWidth(100);
						button.setGraphic(imageView);
					}
					if (c == 4){
						ImageView imageView = new ImageView(kuningas_must);
						imageView.setFitHeight(100);
						imageView.setFitWidth(100);
						button.setGraphic(imageView);
					}
				}

				if (r == 7){
					if (c == 0 || c == 7){
						ImageView imageView = new ImageView(vanker_valge);
						imageView.setFitHeight(100);
						imageView.setFitWidth(100);
						button.setGraphic(imageView);
					}
					if (c == 1 || c == 6){
						ImageView imageView = new ImageView(ratsu_valge);
						imageView.setFitHeight(100);
						imageView.setFitWidth(100);
						button.setGraphic(imageView);
					}
					if (c == 2 || c == 5){
						ImageView imageView = new ImageView(oda_valge);
						imageView.setFitHeight(100);
						imageView.setFitWidth(100);
						button.setGraphic(imageView);
					}
					if (c == 3){
						ImageView imageView = new ImageView(lipp_valge);
						imageView.setFitHeight(100);
						imageView.setFitWidth(100);
						button.setGraphic(imageView);
					}
					if (c == 4){
						ImageView imageView = new ImageView(kuningas_valge);
						imageView.setFitHeight(100);
						imageView.setFitWidth(100);
						button.setGraphic(imageView);
					}
				}


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