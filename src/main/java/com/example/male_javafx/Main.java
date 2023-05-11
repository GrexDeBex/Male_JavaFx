package com.example.male_javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.FileNotFoundException;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws FileNotFoundException {
		GridPane grid = new GridPane();

		new Laud(grid);

		ScrollPane scrollPane = new ScrollPane(grid);
		stage.setScene(new Scene(scrollPane));
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}