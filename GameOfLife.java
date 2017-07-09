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

		for (int i = 0; i < felder; i++) {
			for (int j = 0; j < felder; j++) {
				c[i][j] = b[i][j];
				//Ränder auslassen
				if ((i > 0) && (j > 0) && (i < felder - 1) && (j < felder - 1)) {
					if (c[i - 1][j] == true) {
						hatNachbarn++;
					}
					if (c[i - 1][j + 1] == true) {
						hatNachbarn++;
					}
					if (c[i - 1][j - 1] == true) {
						hatNachbarn++;
					}
					if (c[i + 1][j] == true) {
						hatNachbarn++;
					}
					if (c[i + 1][j + 1] == true) {
						hatNachbarn++;
					}
					if (c[i + 1][j - 1] == true) {
						hatNachbarn++;
					}
					if (c[i][j - 1] == true) {
						hatNachbarn++;
					}
					if (c[i][j + 1] == true) {
						hatNachbarn++;
					}
				}
				// Überprüfen ob tot
				if ((hatNachbarn > 3) || (hatNachbarn < 2)) {
					System.out.println(i + " " + j + "  ist tot");
					// r[i][j].setFill(Color.BEIGE);
				}
				// Überprüfen ob überlebt
				if ((hatNachbarn == 2) || (hatNachbarn == 3)) {
					System.out.println(i + " " + j + " hat überlebt");
				}
				// Überprüfen ob geburt
				if (hatNachbarn == 3) {
					System.out.println(i + " " + j + "   wurde geboren");
					// r[i][j].setFill(Color.BLUE);
				}
			}
		}
		st.setScene(sz);
		st.show();

		int count = 0;
		for (int i = 0; i < felder; i++) {
			for (int j = 0; j < felder; j++) {
				System.out.println("Checktrue " + i + " " + j + " " + b[i][j]);
				if (b[i][j]) {
					count++;
				}
			}
		}
		System.out.println(count);

	}
}
