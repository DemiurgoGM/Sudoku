package br.com.poli.Interface;

import br.com.poli.Sudoku.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Controller {

	@FXML
	private static char sudokuDifficulty; // atributo de referência paara escolha da dificuldade do Sudoku

	@FXML
	private static Player player;

	@FXML
	private boolean checkValidName; // atributo de referência para erro de começar o jogo sem nome

	@FXML
	private AnchorPane defaultAnchorPane;

	@FXML
	private Label labelSudokuTitle;

	@FXML
	private MenuButton menuButtonDifficulty;

	@FXML
	private Label labelNamePlayer;

	@FXML
	private TextField playerUsername;

	@FXML
	private MenuItem sudokuDifficultyEasy;

	@FXML
	private MenuItem sudokuDifficultyNormal;

	@FXML
	private MenuItem sudokuDifficultyHard;

	@FXML
	private Label labelDifficulty;

	@FXML
	private Button startButton;

	@FXML
	private Label labelNoNameException;

	@FXML
	private Label labelSelectedDifficulty;

	@FXML
	void setPlayerUsername(ActionEvent event) {
		labelNoNameException.setVisible(false);
		player = new Player(playerUsername.getText());
		playerUsername.deleteText(0, playerUsername.getText().length());
		checkValidName = true;
	}

	@FXML
	void setSudokuDifficultyEasy(ActionEvent event) {
		sudokuDifficulty = 'e';
		labelSelectedDifficulty.setText("Dificuldade Fácil Selecionada");

	}

	@FXML
	void setSudokuDifficultyHard(ActionEvent event) {
		sudokuDifficulty = 'h';
		labelSelectedDifficulty.setText("Dificuldade Difícil Selecionada");

	}

	@FXML
	void setSudokuDifficultyNormal(ActionEvent event) {
		sudokuDifficulty = 'n';
		labelSelectedDifficulty.setText("Dificuldade Média Selecionada");

	}

	@FXML
	void startSudokuGame(ActionEvent event) {
		if (checkValidName) {
			new SceneTrade("interfaceSudoku").start(Main.stage);

		} else {
			labelNoNameException.setVisible(true);

		}
	}

	public static char getSudokuDifficulty() {
		return sudokuDifficulty;

	}

	public static Player getPlayer() {
		return player;

	}

}
