package com.example.male_javafx;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.util.Objects;

public class Laud {
	private boolean aktiveeritud;
	private final GridPane grid;
	private final Image[] nupuPildid;
	private Button[][] mangulaud;
	private Button ajutine;

	public Laud(GridPane grid, Image[] nupuPildid) {
		this.nupuPildid = nupuPildid;
		this.grid = grid;
		genereeri();
	}

	public void genereeri(){
		mangulaud = new Button[8][8];

		String aktiveeritudStiil = "-fx-background-color:#228B22; -fx-border-color: #000000; -fx-border-width: 2px";
		String tavaStiil = "-fx-background-color:#CD7F32; -fx-border-color: #000000; -fx-border-width: 2px";


		for (int rida = 0; rida < 8; rida++) {
			for (int veerg = 0; veerg < 8; veerg++) {


				Button button = new Button();
				button.setMinSize(100,100);
				button.setMaxSize(100,100);
				grid.add(button, veerg, rida);
				button.setStyle(tavaStiil);

				button.setOnMouseClicked(event -> {
					if (!Objects.equals(button.getGraphic().getId(), "0") && !aktiveeritud) {
						if (button.getStyle().equals(tavaStiil)) {
							button.setStyle(aktiveeritudStiil);
						} else {
							button.setStyle(tavaStiil);
						}
						aktiveeritud = true;
					} else if (button.getStyle().equals(aktiveeritudStiil)){
						button.setStyle(tavaStiil);
						aktiveeritud = false;
					}


					if (aktiveeritud){
						aktiveeritud = false;

						ImageView temp = (ImageView) button.getGraphic();

					}
				});

				ImageView imageView = new ImageView();
				imageView.setId("0");

				if (rida == 1)
					imageView = new ImageView(nupuPildid[0]);
				if (rida == 6)
					imageView = new ImageView(nupuPildid[6]);


				if (rida == 0){
					if (veerg < 5)
						imageView = new ImageView(nupuPildid[veerg+1]);
					else
						imageView = new ImageView(nupuPildid[8-veerg]);
				}

				if (rida == 7){
					if (veerg < 5)
						imageView = new ImageView(nupuPildid[veerg+7]);
					else
						imageView = new ImageView(nupuPildid[14-veerg]);
				}


				imageView.setFitHeight(100);
				imageView.setFitWidth(100);
				button.setGraphic(imageView);
				mangulaud[rida][veerg] = button;
			}
		}
	}

}
