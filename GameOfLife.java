package application;

import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameOfLife extends Application {
	int marken = 25;
	int locMarken = marken;
	final int felder = 10;
	//int hatNachbarn = 0;
	int gen = 0;
	boolean aktGen[][] = new boolean[felder][felder];
	boolean nxtGen[][] = new boolean[felder][felder];
	public static void main(String args[]) {
		launch(args);
	}

	public void start(Stage st) {
		
		BorderPane bp = new BorderPane();
		GridPane grid = new GridPane();
		Scene sz = new Scene(bp);
		Rectangle r[][] = new Rectangle[felder][felder];
		Button next = new Button("Next");
		Button exit = new Button("Exit");
		Button start = new Button("Start");
		Label l = new Label("Generation " + gen);
		HBox btn = new HBox(4.0, start, next, exit, l);
		btn.setAlignment(Pos.BOTTOM_CENTER);
		bp.setCenter(grid);
		bp.setBottom(btn);
		//Spielfeld anlegen
		for (int i = 0; i < felder; i++) {
			for (int j = 0; j < felder; j++) {
				r[i][j] = new Rectangle(30, 30, Color.BEIGE);
				r[i][j].setStroke(Color.BLACK);
				grid.add(r[i][j], i, j);
			}
		}
		
		start.setOnAction((e) -> {
			locMarken = init(locMarken, felder, r, grid, aktGen);
			locMarken = marken;
			gen = 1;
			l.setText("Generation "+ gen);
		});
		next.setOnAction((e) -> {
			aktGen=ausgabe(felder, aktGen, nxtGen, r);
			gen++;
			l.setText("Generation "+ gen);			
		});
		exit.setOnAction((e) -> {
			st.close();
		});
		st.setResizable(false);
		st.setScene(sz);
		st.show();

	}

	private int init(int marken, int felder, Rectangle[][] r, GridPane grid, boolean[][] aktGen) {
		while (marken > 0) {
			Random rnd = new Random();
			for (int i = 0; i < felder; i++) {
				for (int j = 0; j < felder; j++) {
					r[i][j] = new Rectangle(30, 30, Color.BEIGE);
					r[i][j].setStroke(Color.BLACK);
					grid.add(r[i][j], i, j);
					// Zusätzliche Überprüfung, ob wir noch Marken haben
					if (marken > 0) {
						aktGen[i][j] = rnd.nextBoolean();
						if (aktGen[i][j] == true) {
							r[i][j].setFill(Color.RED);
							marken--;
						}
						// System.out.println(i + " " + j + " " + b[i][j]);
					}
				}
			}
		} // Ende while
		return marken;		
	}

	private boolean[][] ausgabe(int felder, boolean aktGen[][], boolean nxtGen[][], Rectangle r[][]) {
		for (int spalte = 0; spalte < felder; spalte++) {
			for (int reihe = 0; reihe < felder; reihe++) {
				int hatNachbarn = 0;
				nxtGen[spalte][reihe] = aktGen[spalte][reihe];
				//Ränder auslassen
				if ((spalte > 0) && (reihe > 0) && (spalte < felder - 1) && (reihe < felder - 1)) {
					if (nxtGen[spalte - 1][reihe] == true) {
						hatNachbarn++;
					}
					if (nxtGen[spalte - 1][reihe + 1] == true) {
						hatNachbarn++;
					}
					if (nxtGen[spalte - 1][reihe - 1] == true) {
						hatNachbarn++;
					}
					if (nxtGen[spalte + 1][reihe] == true) {
						hatNachbarn++;
					}
					if (nxtGen[spalte + 1][reihe + 1] == true) {
						hatNachbarn++;
					}
					if (nxtGen[spalte + 1][reihe - 1] == true) {
						hatNachbarn++;
					}
					if (nxtGen[spalte][reihe - 1] == true) {
						hatNachbarn++;
					}
					if (nxtGen[spalte][reihe + 1] == true) {
						hatNachbarn++;
					}
				}
				//Linker Rand
				else if(spalte == 0){
					//oben links
					if (reihe == 0){
						if (nxtGen[spalte + 1][reihe] == true) {
							hatNachbarn++;
						}
						if (nxtGen[spalte + 1][reihe + 1] == true) {
							hatNachbarn++;
						}
						if (nxtGen[spalte][reihe + 1] == true) {
							hatNachbarn++;
						}
					}
					//unten links
					else if (reihe == felder-1){
						if (nxtGen[spalte][reihe -1] == true) {
							hatNachbarn++;
						}
						if (nxtGen[spalte + 1][reihe - 1] == true) {
							hatNachbarn++;
						}
						if (nxtGen[spalte + 1][reihe] == true) {
							hatNachbarn++;
						}
					}
					//dazwischen
					else if ((reihe > 0) && (reihe < felder -2)){
						if (nxtGen[spalte][reihe - 1] == true) {
							hatNachbarn++;
						}
						if (nxtGen[spalte][reihe + 1] == true) {
							hatNachbarn++;
						}
						if (nxtGen[spalte + 1][reihe] == true) {
							hatNachbarn++;
						}
						if (nxtGen[spalte + 1][reihe- 1] == true) {
							hatNachbarn++;
						}
						if (nxtGen[spalte + 1][reihe + 1] == true) {
							hatNachbarn++;
						}
					}
				}
				//Rechter Rand
				else if (spalte == felder-1){
					//Oben rechts
					if (reihe == 0){
						if (nxtGen[spalte - 1][reihe] == true) {
							hatNachbarn++;
						}
						if (nxtGen[spalte - 1][reihe + 1] == true) {
							hatNachbarn++;
						}
						if (nxtGen[spalte][reihe + 1] == true) {
							hatNachbarn++;
						}
					}
					//unten rechts
					else if (reihe == felder -1){
						if (nxtGen[spalte - 1][reihe] == true) {
							hatNachbarn++;
						}
						if (nxtGen[spalte - 1][reihe - 1] == true) {
							hatNachbarn++;
						}
						if (nxtGen[spalte][reihe - 1] == true) {
							hatNachbarn++;
						}
					}
					//dazwischen
					else if ((reihe > 0) && (reihe < felder -2)){
						if (nxtGen[spalte][reihe - 1] == true) {
							hatNachbarn++;
						}
						if (nxtGen[spalte][reihe + 1] == true) {
							hatNachbarn++;
						}
						if (nxtGen[spalte - 1][reihe] == true) {
							hatNachbarn++;
						}
						if (nxtGen[spalte - 1][reihe- 1] == true) {
							hatNachbarn++;
						}
						if (nxtGen[spalte - 1][reihe + 1] == true) {
							hatNachbarn++;
						}
					}
				}
				//Oberer Rand
				else if (reihe == 0){
					if ((spalte > 0) && (spalte < felder - 1)){
						if (nxtGen[spalte][reihe + 1] == true) {
							hatNachbarn++;
						}
						if (nxtGen[spalte  + 1][reihe] == true) {
							hatNachbarn++;
						}
						if (nxtGen[spalte - 1][reihe] == true) {
							hatNachbarn++;
						}
						if (nxtGen[spalte - 1][reihe + 1] == true) {
							hatNachbarn++;
						}
						if (nxtGen[spalte + 1][reihe + 1] == true) {
							hatNachbarn++;
						}
					}
				}
				//Unterer Rand
				else if (reihe == felder -1){
					if ((spalte > 0) && (spalte < felder - 1)){
						if (nxtGen[spalte][reihe - 1] == true) {
							hatNachbarn++;
						}
						if (nxtGen[spalte  + 1][reihe] == true) {
							hatNachbarn++;
						}
						if (nxtGen[spalte - 1][reihe] == true) {
							hatNachbarn++;
						}
						if (nxtGen[spalte - 1][reihe - 1] == true) {
							hatNachbarn++;
						}
						if (nxtGen[spalte + 1][reihe - 1] == true) {
							hatNachbarn++;
						}
					}
				}
					
				// Überprüfen ob tot
				if ((hatNachbarn > 3) || (hatNachbarn < 2)) {
					System.out.println(spalte + " " + reihe + "  ist tot");
					r[spalte][reihe].setFill(Color.BEIGE);
				}
				// Überprüfen ob überlebt
				if ((hatNachbarn == 2) || (hatNachbarn == 3)) {
					if (nxtGen[spalte][reihe] == true){
						System.out.println(spalte + " " + reihe + " hat überlebt");
					}
				}
				// Überprüfen ob geburt
				if (hatNachbarn == 3) {
					System.out.println(spalte + " " + reihe + "   wurde geboren");
					r[spalte][reihe].setFill(Color.RED);
					nxtGen[spalte][reihe] = true;
				}
			}
		}
		return nxtGen;
	}
}
