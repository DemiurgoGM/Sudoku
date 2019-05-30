package br.com.poli.Interface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private static String link = "interfaceMenuInicial.fxml";
	public static Stage stage;

	public static String getLink() {
		return link;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(link));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage = primaryStage;
			stage.setScene(scene);
			stage.setTitle("Sudoku");
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void setLink(String newlink) {
		link = newlink + ".fxml";
	}

	public static void main(String[] args) {
		launch();
	}
}
