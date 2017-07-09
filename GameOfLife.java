package application;

import java.util.Arrays;
import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameOfLife extends Application {
	public static void main(String args[]) {
		launch(args);
	}

	public void start(Stage st) {
		final int felder = 8;
		int marken = 25;
		int hatNachbarn = 0;
		boolean b[][] = new boolean[felder][felder];
		boolean c[][] = new boolean[felder][felder];
		BorderPane bp = new BorderPane();
		GridPane grid = new GridPane();
		Scene sz = new Scene(bp, 500, 500);
		Rectangle r[][] = new Rectangle[felder][felder];
		bp.setCenter(grid);
		// Verteilen der Marken
		while (marken > 0) {
			Random rnd = new Random();
			for (int i = 0; i < felder; i++) {
				for (int j = 0; j < felder; j++) {
					r[i][j] = new Rectangle(30, 30, Color.BEIGE);
					r[i][j].setStroke(Color.BLACK);
					grid.add(r[i][j], i, j);
					// Zusätzliche Überprüfung, ob wir noch Marken haben
					if (marken > 0) {
						b[i][j] = rnd.nextBoolean();
						if (b[i][j] == true) {
							r[i][j].setFill(Color.RED);
							marken--;
						}
						// System.out.println(i + " " + j + " " + b[i][j]);
					}
				}
			}
		} // Ende while
			// Checken ob es Nachbarn gibt

		for (int spalte = 0; spalte < felder; spalte++) {
			for (int reihe = 0; reihe < felder; reihe++) {
				c[spalte][reihe] = b[spalte][reihe];
				//Ränder auslassen
				if ((spalte > 0) && (reihe > 0) && (spalte < felder - 1) && (reihe < felder - 1)) {
					if (c[spalte - 1][reihe] == true) {
						hatNachbarn++;
					}
					else if (c[spalte - 1][reihe + 1] == true) {
						hatNachbarn++;
					}
					else if (c[spalte - 1][reihe - 1] == true) {
						hatNachbarn++;
					}
					else if (c[spalte + 1][reihe] == true) {
						hatNachbarn++;
					}
					else if (c[spalte + 1][reihe + 1] == true) {
						hatNachbarn++;
					}
					else if (c[spalte + 1][reihe - 1] == true) {
						hatNachbarn++;
					}
					else if (c[spalte][reihe - 1] == true) {
						hatNachbarn++;
					}
					else if (c[spalte][reihe + 1] == true) {
						hatNachbarn++;
					}
				}
				//Linker Rand
				else if(spalte == 0){
					//oben links
					if (reihe == 0){
						if (c[spalte + 1][reihe] == true) {
							hatNachbarn++;
						}
						else if (c[spalte + 1][reihe + 1] == true) {
							hatNachbarn++;
						}
						else if (c[spalte][reihe + 1] == true) {
							hatNachbarn++;
						}
					}
					//unten links
					else if (reihe == felder-1){
						if (c[spalte][reihe -1] == true) {
							hatNachbarn++;
						}
						else if (c[spalte + 1][reihe - 1] == true) {
							hatNachbarn++;
						}
						else if (c[spalte + 1][reihe] == true) {
							hatNachbarn++;
						}
					}
					//dazwischen
					else if ((reihe > 0) && (reihe < felder -2)){
						if (c[spalte][reihe - 1] == true) {
							hatNachbarn++;
						}
						else if (c[spalte][reihe + 1] == true) {
							hatNachbarn++;
						}
						else if (c[spalte + 1][reihe] == true) {
							hatNachbarn++;
						}
						else if (c[spalte + 1][reihe- 1] == true) {
							hatNachbarn++;
						}
						else if (c[spalte + 1][reihe + 1] == true) {
							hatNachbarn++;
						}
					}
				}
				//Rechter Rand
				else if (spalte == felder-1){
					//Oben rechts
					if (reihe == 0){
						if (c[spalte - 1][reihe] == true) {
							hatNachbarn++;
						}
						else if (c[spalte - 1][reihe + 1] == true) {
							hatNachbarn++;
						}
						else if (c[spalte][reihe + 1] == true) {
							hatNachbarn++;
						}
					}
					//unten rechts
					else if (reihe == felder -1){
						if (c[spalte - 1][reihe] == true) {
							hatNachbarn++;
						}
						else if (c[spalte - 1][reihe - 1] == true) {
							hatNachbarn++;
						}
						else if (c[spalte][reihe - 1] == true) {
							hatNachbarn++;
						}
					}
					//dazwischen
					else if ((reihe > 0) && (reihe < felder -2)){
						if (c[spalte][reihe - 1] == true) {
							hatNachbarn++;
						}
						else if (c[spalte][reihe + 1] == true) {
							hatNachbarn++;
						}
						else if (c[spalte - 1][reihe] == true) {
							hatNachbarn++;
						}
						else if (c[spalte - 1][reihe- 1] == true) {
							hatNachbarn++;
						}
						else if (c[spalte - 1][reihe + 1] == true) {
							hatNachbarn++;
						}
					}
				}
				//Oberer Rand
				else if (reihe == 0){
					if ((spalte > 0) && (spalte < felder - 1)){
						if (c[spalte][reihe + 1] == true) {
							hatNachbarn++;
						}
						else if (c[spalte  + 1][reihe] == true) {
							hatNachbarn++;
						}
						else if (c[spalte - 1][reihe] == true) {
							hatNachbarn++;
						}
						else if (c[spalte - 1][reihe + 1] == true) {
							hatNachbarn++;
						}
						else if (c[spalte + 1][reihe + 1] == true) {
							hatNachbarn++;
						}
					}
				}
				//Unterer Rand
				else if (reihe == felder -1){
					if ((spalte > 0) && (spalte < felder - 1)){
						if (c[spalte][reihe - 1] == true) {
							hatNachbarn++;
						}
						else if (c[spalte  + 1][reihe] == true) {
							hatNachbarn++;
						}
						else if (c[spalte - 1][reihe] == true) {
							hatNachbarn++;
						}
						else if (c[spalte - 1][reihe - 1] == true) {
							hatNachbarn++;
						}
						else if (c[spalte + 1][reihe - 1] == true) {
							hatNachbarn++;
						}
					}
				}
					
				// Überprüfen ob tot
				if ((hatNachbarn > 3) || (hatNachbarn < 2)) {
					System.out.println(spalte + " " + reihe + "  ist tot");
					// r[i][j].setFill(Color.BEIGE);
				}
				// Überprüfen ob überlebt
				else if ((hatNachbarn == 2) || (hatNachbarn == 3)) {
					System.out.println(spalte + " " + reihe + " hat überlebt");
				}
				// Überprüfen ob geburt
				else if (hatNachbarn == 3) {
					System.out.println(spalte + " " + reihe + "   wurde geboren");
					// r[i][j].setFill(Color.BLUE);
				}
			}
		}
		st.setScene(sz);
		st.show();

		/*int count = 0;
		for (int i = 0; i < felder; i++) {
			for (int j = 0; j < felder; j++) {
				System.out.println("Checktrue " + i + " " + j + " " + b[i][j]);
				if (b[i][j]) {
					count++;
				}
			}
		}
		System.out.println(count);*/

	}
}
