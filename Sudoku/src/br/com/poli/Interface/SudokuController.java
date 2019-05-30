/**
 * Sample Skeleton for 'interfaceSudoku.fxml' Controller Class
 */

package br.com.poli.Interface;

import br.com.poli.AutonomousPlayer.AutonomousPlayer;
import br.com.poli.Exception.CellIsFixedException;
import br.com.poli.Exception.CellValueException;
import br.com.poli.Exception.GenericException;
import br.com.poli.Sudoku.Sudoku;
import br.com.poli.Sudoku.SudokuEasy;
import br.com.poli.Sudoku.SudokuHard;
import br.com.poli.Sudoku.SudokuNormal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class SudokuController {

	@FXML
	private SudokuEasy sudokuEasy;

	@FXML
	private SudokuNormal sudokuNormal;

	@FXML
	private SudokuHard sudokuHard;

	@FXML
	private AutonomousPlayer autonomousPlayer;

	@FXML // fx:id="sudokuAnchorPane"
	private AnchorPane sudokuAnchorPane; // Value injected by FXMLLoader

	@FXML // fx:id="sudokuGridPane"
	private GridPane sudokuGridPane; // Value injected by FXMLLoader

	@FXML // fx:id="labelWelcomePlayer"
	private Label labelWelcomePlayer; // Value injected by FXMLLoader

	@FXML // fx:id="labelWarningTime"
	private Label labelWarningTime; // Value injected by FXMLLoader

	@FXML // fx:id="labelPlayedTime"
	private Label labelPlayedTime; // Value injected by FXMLLoader

	@FXML
	private Label labelSudokuTitle;

	@FXML
	private Label labelOBS;

	@FXML
	private Label labelWarningENTER;

	@FXML
	private Label labelLegenda;

	@FXML
	private Label labelErrYellow;

	@FXML
	private Label labelErrRed;

	@FXML
	private Label labelErrPurple;

	@FXML
	private Pane PaneErrRed;

	@FXML
	private Pane paneErrYellow;

	@FXML
	private Pane PaneErrPurple;

	@FXML
	private Button ButtonAutonomousPlayerExecuteMove;

	@FXML
	private Label labelAutonomousPlayerIndicator;

	@FXML
	private Button ButtonAutonomousPlayerExecuteGame;

	@FXML
	private boolean autonomousPlayerExecutedGame;

	public void initialize() {
		labelWelcomePlayer.setText("Player: " + Controller.getPlayer().getName() + "!");

		switch (Controller.getSudokuDifficulty()) {
		case 'e':
			sudokuEasy = new SudokuEasy(Controller.getPlayer());
			createEasyFixedGrid();
			autonomousPlayer = new AutonomousPlayer(sudokuEasy);

			break;

		case 'n':
			sudokuNormal = new SudokuNormal(Controller.getPlayer());
			createNormalFixedGrid();
			autonomousPlayer = new AutonomousPlayer(sudokuNormal);

			break;

		case 'h':
			sudokuHard = new SudokuHard(Controller.getPlayer());
			createHardFixedGrid();
			autonomousPlayer = new AutonomousPlayer(sudokuHard);

			break;

		default:
			sudokuNormal = new SudokuNormal(Controller.getPlayer());
			createNormalFixedGrid();
			autonomousPlayer = new AutonomousPlayer(sudokuNormal);

			break;

		}

	}

	@FXML
	void ButtonAutonomousPlayerExecuteGame(ActionEvent event) {
		autonomousPlayer.executeGame();
		autonomousPlayerExecutedGame = true;

		switch (Controller.getSudokuDifficulty()) {
		case 'e':
			fillSudokuEasyCellsInterface();

			break;

		case 'n':
			fillSudokuNormalCellsInterface();

			break;

		case 'h':
			fillSudokuHardCellsInterface();

			break;

		default:
			fillSudokuNormalCellsInterface();

			break;

		}
		endGame();

	}

	@FXML
	void ButtonAutonomousPlayerExecuteMove(ActionEvent event) {
		autonomousPlayer.executeMove();

		switch (Controller.getSudokuDifficulty()) {
		case 'e':
			fillSudokuEasyCellsInterface();

			break;

		case 'n':
			fillSudokuNormalCellsInterface();

			break;

		case 'h':
			fillSudokuHardCellsInterface();

			break;

		default:
			fillSudokuNormalCellsInterface();

			break;

		}

	}

	private void checkEndGame() {
		char answers = 0;

		switch (Controller.getSudokuDifficulty()) {
		case 'e':
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (sudokuEasy.getGridPlayerCellValue(j, i) == Sudoku.result[i][j]) {
						answers++;
						if (answers == 81) {
							endGame();
						}
					} else if (sudokuEasy.getGridPlayerCellValue(j, i) == 0) { // Se há uma célula sem valor, é
																				// impossível o jogo finalizar
						return;
					}
				}
			}
			break;

		case 'n':
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (sudokuNormal.getGridPlayerCellValue(j, i) == Sudoku.result[i][j]) {
						answers++;
						if (answers == 81) {
							endGame();
						}
					} else if (sudokuNormal.getGridPlayerCellValue(j, i) == 0) { // Se há uma célula sem valor, é
																					// impossível o jogo finalizar
						return;
					}
				}
			}
			break;

		case 'h':
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (sudokuHard.getGridPlayerCellValue(j, i) == Sudoku.result[i][j]) {
						answers++;
						if (answers == 81) {
							endGame();
						}
					} else if (sudokuHard.getGridPlayerCellValue(j, i) == 0) { // Se há uma célula sem valor, é
																				// impossível o jogo finalizar
						return;
					}
				}
			}
			break;

		default:
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (sudokuNormal.getGridPlayerCellValue(j, i) == Sudoku.result[i][j]) {
						answers++;
						if (answers == 81) {
							endGame();
						}
					} else if (sudokuNormal.getGridPlayerCellValue(j, i) == 0) { // Se há uma célula sem valor, é
																					// impossível o jogo finalizar
						return;
					}
				}
			}
			break;

		}

	}

	private void endGame() {
		Alert endWindow = new Alert(AlertType.NONE);
		ButtonType closeEndWindow = new ButtonType("Encerrar");
		endWindow.setHeaderText("Você venceu!");
		endWindow.setTitle("Vitória!");

		if (autonomousPlayerExecutedGame) {
			switch (Controller.getSudokuDifficulty()) {
			case 'e':
				sudokuEasy.endGame();
				endWindow.setContentText("O AutonomousPlayer demorou " + autonomousPlayer.timeTook + " nanossegundos.");
				break;

			case 'n':
				sudokuNormal.endGame();
				endWindow.setContentText("O AutonomousPlayer demorou " + autonomousPlayer.timeTook + " nanossegundos.");
				break;

			case 'h':
				sudokuHard.endGame();
				endWindow.setContentText("O AutonomousPlayer demorou " + autonomousPlayer.timeTook + " nanossegundos.");
				break;

			default:
				sudokuNormal.endGame();
				endWindow.setContentText("O AutonomousPlayer demorou " + autonomousPlayer.timeTook + " nanossegundos.");
				break;

			}

		} else {
			switch (Controller.getSudokuDifficulty()) {
			case 'e':
				sudokuEasy.endGame();
				endWindow.setContentText("Aberte o botão para terminar o programa. Você demorou "
						+ (int) (sudokuEasy.getTotalTime() / 60) + " : " + sudokuEasy.getTotalTime() % 60);
				break;

			case 'n':
				sudokuNormal.endGame();
				endWindow.setContentText("Aberte o botão para terminar o programa. Você demorou "
						+ (int) (sudokuNormal.getTotalTime() / 60) + " : " + sudokuNormal.getTotalTime() % 60);
				break;

			case 'h':
				sudokuHard.endGame();
				endWindow.setContentText("Aberte o botão para terminar o programa. Você demorou "
						+ (int) (sudokuHard.getTotalTime() / 60) + " : " + sudokuHard.getTotalTime() % 60);
				break;

			default:
				sudokuNormal.endGame();
				endWindow.setContentText("Aberte o botão para terminar o programa. Você demorou "
						+ (int) (sudokuNormal.getTotalTime() / 60) + " : " + sudokuNormal.getTotalTime() % 60);
				break;

			}

		}

		endWindow.getButtonTypes().add(closeEndWindow);

		endWindow.setOnCloseRequest(event -> {
			System.exit(0);

		});

		endWindow.showAndWait().ifPresent(b -> {
			if (b == closeEndWindow) {
				System.exit(0);

			}

		});

	}

	@FXML
	void setValue00(ActionEvent event) {
		txt00.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt00.getText()), 0, 0);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt00.getText()), 0, 0);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt00.getText()), 0, 0);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt00.getText()), 0, 0);
				break;

			}
		} catch (NumberFormatException e) {
			txt00.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt00.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt00.setStyle("-fx-background-color:red");
		}

		checkEndGame();

	}

	@FXML
	void setValue01(ActionEvent event) {
		txt01.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt01.getText()), 1, 0);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt01.getText()), 1, 0);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt01.getText()), 1, 0);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt01.getText()), 1, 0);
				break;

			}
		} catch (NumberFormatException e) {
			txt01.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt01.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt01.setStyle("-fx-background-color:red");
		}

		checkEndGame();

	}

	@FXML
	void setValue02(ActionEvent event) {
		txt02.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt02.getText()), 2, 0);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt02.getText()), 2, 0);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt02.getText()), 2, 0);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt02.getText()), 2, 0);
				break;

			}
		} catch (NumberFormatException e) {
			txt02.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt02.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt02.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue03(ActionEvent event) {
		txt03.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt03.getText()), 3, 0);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt03.getText()), 3, 0);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt03.getText()), 3, 0);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt03.getText()), 3, 0);
				break;

			}
		} catch (NumberFormatException e) {
			txt03.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt03.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt03.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue04(ActionEvent event) {
		txt04.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt04.getText()), 4, 0);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt04.getText()), 4, 0);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt04.getText()), 4, 0);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt04.getText()), 4, 0);
				break;

			}
		} catch (NumberFormatException e) {
			txt04.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt04.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt04.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue05(ActionEvent event) {
		txt05.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt05.getText()), 5, 0);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt05.getText()), 5, 0);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt05.getText()), 5, 0);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt05.getText()), 5, 0);
				break;

			}
		} catch (NumberFormatException e) {
			txt05.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt05.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt05.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue06(ActionEvent event) {
		txt06.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt06.getText()), 6, 0);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt06.getText()), 6, 0);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt06.getText()), 6, 0);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt06.getText()), 6, 0);
				break;

			}
		} catch (NumberFormatException e) {
			txt06.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt06.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt06.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue07(ActionEvent event) {
		txt07.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt07.getText()), 7, 0);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt07.getText()), 7, 0);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt07.getText()), 7, 0);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt07.getText()), 7, 0);
				break;

			}
		} catch (NumberFormatException e) {
			txt07.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt07.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt07.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue08(ActionEvent event) {
		txt08.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt08.getText()), 8, 0);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt08.getText()), 8, 0);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt08.getText()), 8, 0);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt08.getText()), 8, 0);
				break;

			}
		} catch (NumberFormatException e) {
			txt08.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt08.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt08.setStyle("-fx-background-color:red");
		}

		checkEndGame();

	}

	@FXML
	void setValue10(ActionEvent event) {
		txt10.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt10.getText()), 0, 1);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt10.getText()), 0, 1);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt10.getText()), 0, 1);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt10.getText()), 0, 1);
				break;

			}
		} catch (NumberFormatException e) {
			txt10.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt10.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt10.setStyle("-fx-background-color:red");
		}

		checkEndGame();

	}

	@FXML
	void setValue11(ActionEvent event) {
		txt11.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt11.getText()), 1, 1);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt11.getText()), 1, 1);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt11.getText()), 1, 1);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt11.getText()), 1, 1);
				break;

			}
		} catch (NumberFormatException e) {
			txt11.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt11.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt11.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue12(ActionEvent event) {
		txt12.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt12.getText()), 2, 1);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt12.getText()), 2, 1);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt12.getText()), 2, 1);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt12.getText()), 2, 1);
				break;

			}
		} catch (NumberFormatException e) {
			txt12.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt12.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt12.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue13(ActionEvent event) {
		txt13.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt13.getText()), 3, 1);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt13.getText()), 3, 1);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt13.getText()), 3, 1);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt13.getText()), 3, 1);
				break;

			}
		} catch (NumberFormatException e) {
			txt13.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt13.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt13.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue14(ActionEvent event) {
		txt14.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt14.getText()), 4, 1);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt14.getText()), 4, 1);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt14.getText()), 4, 1);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt14.getText()), 4, 1);
				break;

			}
		} catch (NumberFormatException e) {
			txt14.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt14.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt14.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue15(ActionEvent event) {
		txt15.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt15.getText()), 5, 1);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt15.getText()), 5, 1);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt15.getText()), 5, 1);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt15.getText()), 5, 1);
				break;

			}
		} catch (NumberFormatException e) {
			txt15.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt15.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt15.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue16(ActionEvent event) {
		txt16.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt16.getText()), 6, 1);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt16.getText()), 6, 1);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt16.getText()), 6, 1);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt16.getText()), 6, 1);
				break;

			}
		} catch (NumberFormatException e) {
			txt16.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt16.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt16.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue17(ActionEvent event) {
		txt17.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt17.getText()), 7, 1);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt17.getText()), 7, 1);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt17.getText()), 7, 1);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt17.getText()), 7, 1);
				break;

			}
		} catch (NumberFormatException e) {
			txt17.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt17.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt17.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue18(ActionEvent event) {
		txt18.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt18.getText()), 8, 1);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt18.getText()), 8, 1);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt18.getText()), 8, 1);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt18.getText()), 8, 1);
				break;

			}
		} catch (NumberFormatException e) {
			txt18.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt18.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt18.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue20(ActionEvent event) {
		txt20.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt20.getText()), 0, 2);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt20.getText()), 0, 2);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt20.getText()), 0, 2);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt20.getText()), 0, 2);
				break;

			}
		} catch (NumberFormatException e) {
			txt20.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt20.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt20.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue21(ActionEvent event) {
		txt21.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt21.getText()), 1, 2);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt21.getText()), 1, 2);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt21.getText()), 1, 2);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt21.getText()), 1, 2);
				break;

			}
		} catch (NumberFormatException e) {
			txt21.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt21.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt21.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue22(ActionEvent event) {
		txt22.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt22.getText()), 2, 2);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt22.getText()), 2, 2);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt22.getText()), 2, 2);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt22.getText()), 2, 2);
				break;

			}
		} catch (NumberFormatException e) {
			txt22.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt22.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt22.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue23(ActionEvent event) {
		txt23.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt23.getText()), 3, 2);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt23.getText()), 3, 2);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt23.getText()), 3, 2);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt23.getText()), 3, 2);
				break;

			}
		} catch (NumberFormatException e) {
			txt23.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt23.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt23.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue24(ActionEvent event) {
		txt24.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt24.getText()), 4, 2);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt24.getText()), 4, 2);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt24.getText()), 4, 2);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt24.getText()), 4, 2);
				break;

			}
		} catch (NumberFormatException e) {
			txt24.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt24.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt24.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue25(ActionEvent event) {
		txt25.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt25.getText()), 5, 2);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt25.getText()), 5, 2);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt25.getText()), 5, 2);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt25.getText()), 5, 2);
				break;

			}
		} catch (NumberFormatException e) {
			txt25.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt25.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt25.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue26(ActionEvent event) {
		txt26.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt26.getText()), 6, 2);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt26.getText()), 6, 2);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt26.getText()), 6, 2);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt26.getText()), 6, 2);
				break;

			}
		} catch (NumberFormatException e) {
			txt26.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt26.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt26.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue27(ActionEvent event) {
		txt27.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt27.getText()), 7, 2);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt27.getText()), 7, 2);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt27.getText()), 7, 2);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt27.getText()), 7, 2);
				break;

			}
		} catch (NumberFormatException e) {
			txt27.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt27.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt27.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue28(ActionEvent event) {
		txt28.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt28.getText()), 8, 2);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt28.getText()), 8, 2);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt28.getText()), 8, 2);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt28.getText()), 8, 2);
				break;

			}
		} catch (NumberFormatException e) {
			txt28.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt28.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt28.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue30(ActionEvent event) {
		txt30.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt30.getText()), 0, 3);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt30.getText()), 0, 3);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt30.getText()), 0, 3);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt30.getText()), 0, 3);
				break;

			}
		} catch (NumberFormatException e) {
			txt30.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt30.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt30.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue31(ActionEvent event) {
		txt31.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt31.getText()), 1, 3);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt31.getText()), 1, 3);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt31.getText()), 1, 3);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt31.getText()), 1, 3);
				break;

			}
		} catch (NumberFormatException e) {
			txt31.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt31.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt31.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue32(ActionEvent event) {
		txt32.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt32.getText()), 2, 3);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt32.getText()), 2, 3);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt32.getText()), 2, 3);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt32.getText()), 2, 3);
				break;

			}
		} catch (NumberFormatException e) {
			txt32.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt32.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt32.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue33(ActionEvent event) {
		txt33.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt33.getText()), 3, 3);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt33.getText()), 3, 3);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt33.getText()), 3, 3);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt33.getText()), 3, 3);
				break;

			}
		} catch (NumberFormatException e) {
			txt33.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt33.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt33.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue34(ActionEvent event) {
		txt34.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt34.getText()), 4, 3);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt34.getText()), 4, 3);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt34.getText()), 4, 3);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt34.getText()), 4, 3);
				break;

			}
		} catch (NumberFormatException e) {
			txt34.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt34.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt34.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue35(ActionEvent event) {
		txt35.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt35.getText()), 5, 3);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt35.getText()), 5, 3);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt35.getText()), 5, 3);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt35.getText()), 5, 3);
				break;

			}
		} catch (NumberFormatException e) {
			txt35.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt35.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt35.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue36(ActionEvent event) {
		txt36.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt36.getText()), 6, 3);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt36.getText()), 6, 3);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt36.getText()), 6, 3);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt36.getText()), 6, 3);
				break;

			}
		} catch (NumberFormatException e) {
			txt36.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt36.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt36.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue37(ActionEvent event) {
		txt37.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt37.getText()), 7, 3);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt37.getText()), 7, 3);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt37.getText()), 7, 3);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt37.getText()), 7, 3);
				break;

			}
		} catch (NumberFormatException e) {
			txt37.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt37.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt37.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue38(ActionEvent event) {
		txt38.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt38.getText()), 8, 3);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt38.getText()), 8, 3);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt38.getText()), 8, 3);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt38.getText()), 8, 3);
				break;

			}
		} catch (NumberFormatException e) {
			txt38.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt38.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt38.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue40(ActionEvent event) {
		txt40.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt40.getText()), 0, 4);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt40.getText()), 0, 4);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt40.getText()), 0, 4);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt40.getText()), 0, 4);
				break;

			}
		} catch (NumberFormatException e) {
			txt40.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt40.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt40.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue41(ActionEvent event) {
		txt41.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt41.getText()), 1, 4);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt41.getText()), 1, 4);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt41.getText()), 1, 4);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt41.getText()), 1, 4);
				break;

			}
		} catch (NumberFormatException e) {
			txt41.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt41.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt41.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue42(ActionEvent event) {
		txt42.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt42.getText()), 2, 4);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt42.getText()), 2, 4);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt42.getText()), 2, 4);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt42.getText()), 2, 4);
				break;

			}
		} catch (NumberFormatException e) {
			txt42.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt42.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt42.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue43(ActionEvent event) {
		txt43.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt43.getText()), 3, 4);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt43.getText()), 3, 4);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt43.getText()), 3, 4);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt43.getText()), 3, 4);
				break;

			}
		} catch (NumberFormatException e) {
			txt43.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt43.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt43.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue44(ActionEvent event) {
		txt44.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt44.getText()), 4, 4);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt44.getText()), 4, 4);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt44.getText()), 4, 4);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt44.getText()), 4, 4);
				break;

			}
		} catch (NumberFormatException e) {
			txt44.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt44.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt44.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue45(ActionEvent event) {
		txt45.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt45.getText()), 5, 4);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt45.getText()), 5, 4);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt45.getText()), 5, 4);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt45.getText()), 5, 4);
				break;

			}
		} catch (NumberFormatException e) {
			txt45.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt45.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt45.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue46(ActionEvent event) {
		txt46.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt46.getText()), 6, 4);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt46.getText()), 6, 4);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt46.getText()), 6, 4);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt46.getText()), 6, 4);
				break;

			}
		} catch (NumberFormatException e) {
			txt46.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt46.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt46.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue47(ActionEvent event) {
		txt47.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt47.getText()), 7, 4);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt47.getText()), 7, 4);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt47.getText()), 7, 4);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt47.getText()), 7, 4);
				break;

			}
		} catch (NumberFormatException e) {
			txt47.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt47.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt47.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue48(ActionEvent event) {
		txt48.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt48.getText()), 8, 4);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt48.getText()), 8, 4);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt48.getText()), 8, 4);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt48.getText()), 8, 4);
				break;

			}
		} catch (NumberFormatException e) {
			txt48.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt48.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt48.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue50(ActionEvent event) {
		txt50.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt50.getText()), 0, 5);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt50.getText()), 0, 5);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt50.getText()), 0, 5);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt50.getText()), 0, 5);
				break;

			}
		} catch (NumberFormatException e) {
			txt50.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt50.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt50.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue51(ActionEvent event) {
		txt51.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt51.getText()), 1, 5);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt51.getText()), 1, 5);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt51.getText()), 1, 5);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt51.getText()), 1, 5);
				break;

			}
		} catch (NumberFormatException e) {
			txt51.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt51.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt51.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue52(ActionEvent event) {
		txt52.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt52.getText()), 2, 5);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt52.getText()), 2, 5);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt52.getText()), 2, 5);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt52.getText()), 2, 5);
				break;

			}
		} catch (NumberFormatException e) {
			txt52.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt52.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt52.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue53(ActionEvent event) {
		txt53.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt53.getText()), 3, 5);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt53.getText()), 3, 5);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt53.getText()), 3, 5);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt53.getText()), 3, 5);
				break;

			}
		} catch (NumberFormatException e) {
			txt53.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt53.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt53.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue54(ActionEvent event) {
		txt54.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt54.getText()), 4, 5);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt54.getText()), 4, 5);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt54.getText()), 4, 5);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt54.getText()), 4, 5);
				break;

			}
		} catch (NumberFormatException e) {
			txt54.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt54.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt54.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue55(ActionEvent event) {
		txt55.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt55.getText()), 5, 5);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt55.getText()), 5, 5);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt55.getText()), 5, 5);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt55.getText()), 5, 5);
				break;

			}
		} catch (NumberFormatException e) {
			txt55.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt55.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt55.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue56(ActionEvent event) {
		txt56.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt56.getText()), 6, 5);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt56.getText()), 6, 5);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt56.getText()), 6, 5);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt56.getText()), 6, 5);
				break;

			}
		} catch (NumberFormatException e) {
			txt56.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt56.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt56.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue57(ActionEvent event) {
		txt57.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt57.getText()), 7, 5);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt57.getText()), 7, 5);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt57.getText()), 7, 5);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt57.getText()), 7, 5);
				break;

			}
		} catch (NumberFormatException e) {
			txt57.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt57.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt57.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue58(ActionEvent event) {
		txt58.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt58.getText()), 8, 5);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt58.getText()), 8, 5);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt58.getText()), 8, 5);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt58.getText()), 8, 5);
				break;

			}
		} catch (NumberFormatException e) {
			txt58.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt58.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt58.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue60(ActionEvent event) {
		txt60.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt60.getText()), 0, 6);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt60.getText()), 0, 6);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt60.getText()), 0, 6);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt60.getText()), 0, 6);
				break;

			}
		} catch (NumberFormatException e) {
			txt60.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt60.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt60.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue61(ActionEvent event) {
		txt61.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt61.getText()), 1, 6);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt61.getText()), 1, 6);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt61.getText()), 1, 6);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt61.getText()), 1, 6);
				break;

			}
		} catch (NumberFormatException e) {
			txt61.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt61.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt61.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue62(ActionEvent event) {
		txt62.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt62.getText()), 2, 6);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt62.getText()), 2, 6);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt62.getText()), 2, 6);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt62.getText()), 2, 6);
				break;

			}
		} catch (NumberFormatException e) {
			txt62.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt62.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt62.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue63(ActionEvent event) {
		txt63.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt63.getText()), 3, 6);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt63.getText()), 3, 6);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt63.getText()), 3, 6);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt63.getText()), 3, 6);
				break;

			}
		} catch (NumberFormatException e) {
			txt63.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt63.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt63.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue64(ActionEvent event) {
		txt64.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt64.getText()), 4, 6);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt64.getText()), 4, 6);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt64.getText()), 4, 6);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt64.getText()), 4, 6);
				break;

			}
		} catch (NumberFormatException e) {
			txt64.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt64.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt64.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue65(ActionEvent event) {
		txt65.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt65.getText()), 5, 6);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt65.getText()), 5, 6);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt65.getText()), 5, 6);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt65.getText()), 5, 6);
				break;

			}
		} catch (NumberFormatException e) {
			txt65.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt65.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt65.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue66(ActionEvent event) {
		txt66.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt66.getText()), 6, 6);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt66.getText()), 6, 6);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt66.getText()), 6, 6);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt66.getText()), 6, 6);
				break;

			}
		} catch (NumberFormatException e) {
			txt66.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt66.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt66.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue67(ActionEvent event) {
		txt67.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt67.getText()), 7, 6);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt67.getText()), 7, 6);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt67.getText()), 7, 6);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt67.getText()), 7, 6);
				break;

			}
		} catch (NumberFormatException e) {
			txt67.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt67.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt67.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue68(ActionEvent event) {
		txt68.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt68.getText()), 8, 6);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt68.getText()), 8, 6);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt68.getText()), 8, 6);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt68.getText()), 8, 6);
				break;

			}
		} catch (NumberFormatException e) {
			txt68.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt68.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt68.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue70(ActionEvent event) {
		txt70.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt70.getText()), 0, 7);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt70.getText()), 0, 7);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt70.getText()), 0, 7);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt70.getText()), 0, 7);
				break;

			}
		} catch (NumberFormatException e) {
			txt70.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt70.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt70.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue71(ActionEvent event) {
		txt71.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt71.getText()), 1, 7);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt71.getText()), 1, 7);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt71.getText()), 1, 7);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt71.getText()), 1, 7);
				break;

			}
		} catch (NumberFormatException e) {
			txt71.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt71.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt71.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue72(ActionEvent event) {
		txt72.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt72.getText()), 2, 7);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt72.getText()), 2, 7);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt72.getText()), 2, 7);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt72.getText()), 2, 7);
				break;

			}
		} catch (NumberFormatException e) {
			txt72.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt72.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt72.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue73(ActionEvent event) {
		txt73.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt73.getText()), 3, 7);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt73.getText()), 3, 7);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt73.getText()), 3, 7);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt73.getText()), 3, 7);
				break;

			}
		} catch (NumberFormatException e) {
			txt73.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt73.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt73.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue74(ActionEvent event) {
		txt74.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt74.getText()), 4, 7);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt74.getText()), 4, 7);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt74.getText()), 4, 7);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt74.getText()), 4, 7);
				break;

			}
		} catch (NumberFormatException e) {
			txt74.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt74.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt74.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue75(ActionEvent event) {
		txt75.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt75.getText()), 5, 7);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt75.getText()), 5, 7);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt75.getText()), 5, 7);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt75.getText()), 5, 7);
				break;

			}
		} catch (NumberFormatException e) {
			txt75.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt75.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt75.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue76(ActionEvent event) {
		txt76.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt76.getText()), 6, 7);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt76.getText()), 6, 7);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt76.getText()), 6, 7);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt76.getText()), 6, 7);
				break;

			}
		} catch (NumberFormatException e) {
			txt76.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt76.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt76.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue77(ActionEvent event) {
		txt77.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt77.getText()), 7, 7);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt77.getText()), 7, 7);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt77.getText()), 7, 7);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt77.getText()), 7, 7);
				break;

			}
		} catch (NumberFormatException e) {
			txt77.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt77.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt77.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue78(ActionEvent event) {
		txt78.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt78.getText()), 8, 7);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt78.getText()), 8, 7);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt78.getText()), 8, 7);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt78.getText()), 8, 7);
				break;

			}
		} catch (NumberFormatException e) {
			txt78.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt78.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt78.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue80(ActionEvent event) {
		txt80.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt80.getText()), 0, 8);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt80.getText()), 0, 8);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt80.getText()), 0, 8);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt80.getText()), 0, 8);
				break;

			}
		} catch (NumberFormatException e) {
			txt80.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt80.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt80.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue81(ActionEvent event) {
		txt81.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt81.getText()), 1, 8);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt81.getText()), 1, 8);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt81.getText()), 1, 8);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt81.getText()), 1, 8);
				break;

			}
		} catch (NumberFormatException e) {
			txt81.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt81.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt81.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue82(ActionEvent event) {
		txt82.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt82.getText()), 2, 8);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt82.getText()), 2, 8);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt82.getText()), 2, 8);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt82.getText()), 2, 8);
				break;

			}
		} catch (NumberFormatException e) {
			txt82.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt82.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt82.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue83(ActionEvent event) {
		txt83.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt83.getText()), 3, 8);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt83.getText()), 3, 8);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt83.getText()), 3, 8);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt83.getText()), 3, 8);
				break;

			}
		} catch (NumberFormatException e) {
			txt83.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt83.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt83.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue84(ActionEvent event) {
		txt84.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt84.getText()), 4, 8);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt84.getText()), 4, 8);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt84.getText()), 4, 8);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt84.getText()), 4, 8);
				break;

			}
		} catch (NumberFormatException e) {
			txt84.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt84.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt84.setStyle("-fx-background-color:red");
		}

		checkEndGame();

	}

	@FXML
	void setValue85(ActionEvent event) {
		txt85.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt85.getText()), 5, 8);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt85.getText()), 5, 8);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt85.getText()), 5, 8);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt85.getText()), 5, 8);
				break;

			}
		} catch (NumberFormatException e) {
			txt85.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt85.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt85.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue86(ActionEvent event) {
		txt86.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt86.getText()), 6, 8);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt86.getText()), 6, 8);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt86.getText()), 6, 8);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt86.getText()), 6, 8);
				break;

			}
		} catch (NumberFormatException e) {
			txt86.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt86.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt86.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	@FXML
	void setValue87(ActionEvent event) {
		txt87.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt87.getText()), 7, 8);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt87.getText()), 7, 8);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt87.getText()), 7, 8);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt87.getText()), 7, 8);
				break;

			}
		} catch (NumberFormatException e) {
			txt87.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt87.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt87.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	// LISTA GRID
	@FXML // fx:id="txt01"
	private TextField txt00; // Value injected by FXMLLoader

	@FXML // fx:id="txt01"
	private TextField txt01; // Value injected by FXMLLoader

	@FXML // fx:id="txt02"
	private TextField txt02; // Value injected by FXMLLoader

	@FXML // fx:id="txt03"
	private TextField txt03; // Value injected by FXMLLoader

	@FXML // fx:id="txt12"
	private TextField txt12; // Value injected by FXMLLoader

	@FXML // fx:id="txt11"
	private TextField txt11; // Value injected by FXMLLoader

	@FXML // fx:id="txt10"
	private TextField txt10; // Value injected by FXMLLoader

	@FXML // fx:id="txt08"
	private TextField txt08; // Value injected by FXMLLoader

	@FXML // fx:id="txt07"
	private TextField txt07; // Value injected by FXMLLoader

	@FXML // fx:id="txt06"
	private TextField txt06; // Value injected by FXMLLoader

	@FXML // fx:id="txt05"
	private TextField txt05; // Value injected by FXMLLoader

	@FXML // fx:id="txt04"
	private TextField txt04; // Value injected by FXMLLoader

	@FXML // fx:id="txt31"
	private TextField txt31; // Value injected by FXMLLoader

	@FXML // fx:id="txt32"
	private TextField txt32; // Value injected by FXMLLoader

	@FXML // fx:id="txt33"
	private TextField txt33; // Value injected by FXMLLoader

	@FXML // fx:id="txt30"
	private TextField txt30; // Value injected by FXMLLoader

	@FXML // fx:id="txt28"
	private TextField txt28; // Value injected by FXMLLoader

	@FXML // fx:id="txt27"
	private TextField txt27; // Value injected by FXMLLoader

	@FXML // fx:id="txt26"
	private TextField txt26; // Value injected by FXMLLoader

	@FXML // fx:id="txt25"
	private TextField txt25; // Value injected by FXMLLoader

	@FXML // fx:id="txt24"
	private TextField txt24; // Value injected by FXMLLoader

	@FXML // fx:id="txt23"
	private TextField txt23; // Value injected by FXMLLoader

	@FXML // fx:id="txt22"
	private TextField txt22; // Value injected by FXMLLoader

	@FXML // fx:id="txt21"
	private TextField txt21; // Value injected by FXMLLoader

	@FXML // fx:id="txt20"
	private TextField txt20; // Value injected by FXMLLoader

	@FXML // fx:id="txt18"
	private TextField txt18; // Value injected by FXMLLoader

	@FXML // fx:id="txt17"
	private TextField txt17; // Value injected by FXMLLoader

	@FXML // fx:id="txt16"
	private TextField txt16; // Value injected by FXMLLoader

	@FXML // fx:id="txt15"
	private TextField txt15; // Value injected by FXMLLoader

	@FXML // fx:id="txt14"
	private TextField txt14; // Value injected by FXMLLoader

	@FXML // fx:id="txt13"
	private TextField txt13; // Value injected by FXMLLoader

	@FXML // fx:id="txt41"
	private TextField txt41; // Value injected by FXMLLoader

	@FXML // fx:id="txt42"
	private TextField txt42; // Value injected by FXMLLoader

	@FXML // fx:id="txt43"
	private TextField txt43; // Value injected by FXMLLoader

	@FXML // fx:id="txt44"
	private TextField txt44; // Value injected by FXMLLoader

	@FXML // fx:id="txt40"
	private TextField txt40; // Value injected by FXMLLoader

	@FXML // fx:id="txt38"
	private TextField txt38; // Value injected by FXMLLoader

	@FXML // fx:id="txt37"
	private TextField txt37; // Value injected by FXMLLoader

	@FXML // fx:id="txt36"
	private TextField txt36; // Value injected by FXMLLoader

	@FXML // fx:id="txt35"
	private TextField txt35; // Value injected by FXMLLoader

	@FXML // fx:id="txt61"
	private TextField txt61; // Value injected by FXMLLoader

	@FXML // fx:id="txt60"
	private TextField txt60; // Value injected by FXMLLoader

	@FXML // fx:id="txt58"
	private TextField txt58; // Value injected by FXMLLoader

	@FXML // fx:id="txt57"
	private TextField txt57; // Value injected by FXMLLoader

	@FXML // fx:id="txt56"
	private TextField txt56; // Value injected by FXMLLoader

	@FXML // fx:id="txt55"
	private TextField txt55; // Value injected by FXMLLoader

	@FXML // fx:id="txt54"
	private TextField txt54; // Value injected by FXMLLoader

	@FXML // fx:id="txt48"
	private TextField txt48; // Value injected by FXMLLoader

	@FXML // fx:id="txt50"
	private TextField txt50; // Value injected by FXMLLoader

	@FXML // fx:id="txt47"
	private TextField txt47; // Value injected by FXMLLoader

	@FXML // fx:id="txt46"
	private TextField txt46; // Value injected by FXMLLoader

	@FXML // fx:id="txt51"
	private TextField txt51; // Value injected by FXMLLoader

	@FXML // fx:id="txt52"
	private TextField txt52; // Value injected by FXMLLoader

	@FXML // fx:id="txt53"
	private TextField txt53; // Value injected by FXMLLoader

	@FXML // fx:id="txt34"
	private TextField txt34; // Value injected by FXMLLoader

	@FXML // fx:id="txt64"
	private TextField txt64; // Value injected by FXMLLoader

	@FXML // fx:id="txt65"
	private TextField txt65; // Value injected by FXMLLoader

	@FXML // fx:id="txt66"
	private TextField txt66; // Value injected by FXMLLoader

	@FXML // fx:id="txt67"
	private TextField txt67; // Value injected by FXMLLoader

	@FXML // fx:id="txt68"
	private TextField txt68; // Value injected by FXMLLoader

	@FXML // fx:id="txt70"
	private TextField txt70; // Value injected by FXMLLoader

	@FXML // fx:id="txt71"
	private TextField txt71; // Value injected by FXMLLoader

	@FXML // fx:id="txt72"
	private TextField txt72; // Value injected by FXMLLoader

	@FXML // fx:id="txt73"
	private TextField txt73; // Value injected by FXMLLoader

	@FXML // fx:id="txt74"
	private TextField txt74; // Value injected by FXMLLoader

	@FXML // fx:id="txt75"
	private TextField txt75; // Value injected by FXMLLoader

	@FXML // fx:id="txt76"
	private TextField txt76; // Value injected by FXMLLoader

	@FXML // fx:id="txt77"
	private TextField txt77; // Value injected by FXMLLoader

	@FXML // fx:id="txt78"
	private TextField txt78; // Value injected by FXMLLoader

	@FXML // fx:id="txt62"
	private TextField txt62; // Value injected by FXMLLoader

	@FXML // fx:id="txt63"
	private TextField txt63; // Value injected by FXMLLoader

	@FXML // fx:id="txt80"
	private TextField txt80; // Value injected by FXMLLoader

	@FXML // fx:id="txt45"
	private TextField txt45; // Value injected by FXMLLoader

	@FXML // fx:id="txt81"
	private TextField txt81; // Value injected by FXMLLoader

	@FXML // fx:id="txt82"
	private TextField txt82; // Value injected by FXMLLoader

	@FXML // fx:id="txt84"
	private TextField txt84; // Value injected by FXMLLoader

	@FXML // fx:id="txt83"
	private TextField txt83; // Value injected by FXMLLoader

	@FXML // fx:id="txt85"
	private TextField txt85; // Value injected by FXMLLoader

	@FXML // fx:id="txt86"
	private TextField txt86; // Value injected by FXMLLoader

	@FXML // fx:id="txt87"
	private TextField txt87; // Value injected by FXMLLoader

	@FXML // fx:id="txt88"
	private TextField txt88; // Value injected by FXMLLoader

	@FXML
	void setValue88(ActionEvent event) {
		txt88.setStyle("");

		try {
			switch (Controller.getSudokuDifficulty()) {

			case 'e':
				sudokuEasy.setValue(Integer.parseInt(txt88.getText()), 8, 8);
				break;

			case 'n':
				sudokuNormal.setValue(Integer.parseInt(txt88.getText()), 8, 8);
				break;

			case 'h':
				sudokuHard.setValue(Integer.parseInt(txt88.getText()), 8, 8);
				break;

			default:
				sudokuNormal.setValue(Integer.parseInt(txt88.getText()), 8, 8);
				break;

			}
		} catch (NumberFormatException e) {
			txt88.setStyle("-fx-background-color:purple");
		} catch (CellIsFixedException e) { // Devido ao método setEditable(), é impossível cair nessa exceção
			System.out.println("Célula Fixa");
			e.printStackTrace();
		} catch (CellValueException e) {
			txt88.setStyle("-fx-background-color:yellow");
		} catch (GenericException e) {
			txt88.setStyle("-fx-background-color:red");
		}

		checkEndGame();
	}

	private void createHardFixedGrid() {
		if (sudokuHard.isGridPlayerCellFixed(0, 0)) {
			txt00.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(0, 0)));
			txt00.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(1, 0)) {
			txt01.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(1, 0)));
			txt01.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(2, 0)) {
			txt02.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(2, 0)));
			txt02.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(3, 0)) {
			txt03.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(3, 0)));
			txt03.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(4, 0)) {
			txt04.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(4, 0)));
			txt04.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(5, 0)) {
			txt05.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(5, 0)));
			txt05.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(6, 0)) {
			txt06.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(6, 0)));
			txt06.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(7, 0)) {
			txt07.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(7, 0)));
			txt07.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(8, 0)) {
			txt08.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(8, 0)));
			txt08.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(0, 1)) {
			txt10.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(0, 1)));
			txt10.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(0, 2)) {
			txt20.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(0, 2)));
			txt20.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(0, 3)) {
			txt30.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(0, 3)));
			txt30.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(0, 4)) {
			txt40.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(0, 4)));
			txt40.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(0, 5)) {
			txt50.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(0, 5)));
			txt50.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(0, 6)) {
			txt60.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(0, 6)));
			txt60.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(0, 7)) {
			txt70.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(0, 7)));
			txt70.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(0, 8)) {
			txt80.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(0, 8)));
			txt80.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(1, 1)) {
			txt11.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(1, 1)));
			txt11.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(1, 2)) {
			txt21.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(1, 2)));
			txt21.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(1, 3)) {
			txt31.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(1, 3)));
			txt31.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(1, 4)) {
			txt41.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(1, 4)));
			txt41.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(1, 5)) {
			txt51.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(1, 5)));
			txt51.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(1, 6)) {
			txt61.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(1, 6)));
			txt61.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(1, 7)) {
			txt71.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(1, 7)));
			txt71.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(1, 8)) {
			txt81.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(1, 8)));
			txt81.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(2, 1)) {
			txt12.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(2, 1)));
			txt12.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(2, 2)) {
			txt22.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(2, 2)));
			txt22.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(2, 3)) {
			txt32.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(2, 3)));
			txt32.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(2, 4)) {
			txt42.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(2, 4)));
			txt42.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(2, 5)) {
			txt52.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(2, 5)));
			txt52.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(2, 6)) {
			txt62.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(2, 6)));
			txt62.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(2, 7)) {
			txt72.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(2, 7)));
			txt72.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(2, 8)) {
			txt82.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(2, 8)));
			txt82.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(3, 1)) {
			txt13.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(3, 1)));
			txt13.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(3, 2)) {
			txt23.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(3, 2)));
			txt23.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(3, 3)) {
			txt33.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(3, 3)));
			txt33.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(3, 4)) {
			txt43.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(3, 4)));
			txt43.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(3, 5)) {
			txt53.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(3, 5)));
			txt53.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(3, 6)) {
			txt63.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(3, 6)));
			txt63.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(3, 7)) {
			txt73.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(3, 7)));
			txt73.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(3, 8)) {
			txt83.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(3, 8)));
			txt83.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(4, 1)) {
			txt14.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(4, 1)));
			txt14.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(4, 2)) {
			txt24.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(4, 2)));
			txt24.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(4, 3)) {
			txt34.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(4, 3)));
			txt34.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(4, 4)) {
			txt44.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(4, 4)));
			txt44.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(4, 5)) {
			txt54.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(4, 5)));
			txt54.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(4, 6)) {
			txt64.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(4, 6)));
			txt64.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(4, 7)) {
			txt74.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(4, 7)));
			txt74.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(4, 8)) {
			txt84.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(4, 8)));
			txt84.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(5, 1)) {
			txt15.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(5, 1)));
			txt15.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(5, 2)) {
			txt25.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(5, 2)));
			txt25.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(5, 3)) {
			txt35.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(5, 3)));
			txt35.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(5, 4)) {
			txt45.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(5, 4)));
			txt45.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(5, 5)) {
			txt55.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(5, 5)));
			txt55.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(5, 6)) {
			txt65.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(5, 6)));
			txt65.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(5, 7)) {
			txt75.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(5, 7)));
			txt75.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(5, 8)) {
			txt85.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(5, 8)));
			txt85.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(6, 1)) {
			txt16.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(6, 1)));
			txt16.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(6, 2)) {
			txt26.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(6, 2)));
			txt26.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(6, 3)) {
			txt36.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(6, 3)));
			txt36.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(6, 4)) {
			txt46.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(6, 4)));
			txt46.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(6, 5)) {
			txt56.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(6, 5)));
			txt56.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(6, 6)) {
			txt66.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(6, 6)));
			txt66.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(6, 7)) {
			txt76.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(6, 7)));
			txt76.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(6, 8)) {
			txt86.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(6, 8)));
			txt86.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(7, 1)) {
			txt17.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(7, 1)));
			txt17.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(7, 2)) {
			txt27.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(7, 2)));
			txt27.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(7, 3)) {
			txt37.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(7, 3)));
			txt37.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(7, 4)) {
			txt47.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(7, 4)));
			txt47.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(7, 5)) {
			txt57.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(7, 5)));
			txt57.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(7, 6)) {
			txt67.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(7, 6)));
			txt67.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(7, 7)) {
			txt77.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(7, 7)));
			txt77.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(7, 8)) {
			txt87.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(7, 8)));
			txt87.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(8, 1)) {
			txt18.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(8, 1)));
			txt18.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(8, 2)) {
			txt28.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(8, 2)));
			txt28.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(8, 3)) {
			txt38.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(8, 3)));
			txt38.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(8, 4)) {
			txt48.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(8, 4)));
			txt48.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(8, 5)) {
			txt58.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(8, 5)));
			txt58.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(8, 6)) {
			txt68.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(8, 6)));
			txt68.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(8, 7)) {
			txt78.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(8, 7)));
			txt78.setEditable(false);
		}
		if (sudokuHard.isGridPlayerCellFixed(8, 8)) {
			txt88.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(8, 8)));
			txt88.setEditable(false);
		}

	}

	private void createNormalFixedGrid() {
		if (sudokuNormal.isGridPlayerCellFixed(0, 0)) {
			txt00.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(0, 0)));
			txt00.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(1, 0)) {
			txt01.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(1, 0)));
			txt01.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(2, 0)) {
			txt02.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(2, 0)));
			txt02.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(3, 0)) {
			txt03.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(3, 0)));
			txt03.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(4, 0)) {
			txt04.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(4, 0)));
			txt04.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(5, 0)) {
			txt05.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(5, 0)));
			txt05.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(6, 0)) {
			txt06.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(6, 0)));
			txt06.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(7, 0)) {
			txt07.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(7, 0)));
			txt07.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(8, 0)) {
			txt08.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(8, 0)));
			txt08.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(0, 1)) {
			txt10.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(0, 1)));
			txt10.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(0, 2)) {
			txt20.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(0, 2)));
			txt20.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(0, 3)) {
			txt30.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(0, 3)));
			txt30.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(0, 4)) {
			txt40.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(0, 4)));
			txt40.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(0, 5)) {
			txt50.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(0, 5)));
			txt50.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(0, 6)) {
			txt60.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(0, 6)));
			txt60.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(0, 7)) {
			txt70.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(0, 7)));
			txt70.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(0, 8)) {
			txt80.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(0, 8)));
			txt80.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(1, 1)) {
			txt11.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(1, 1)));
			txt11.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(1, 2)) {
			txt21.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(1, 2)));
			txt21.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(1, 3)) {
			txt31.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(1, 3)));
			txt31.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(1, 4)) {
			txt41.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(1, 4)));
			txt41.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(1, 5)) {
			txt51.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(1, 5)));
			txt51.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(1, 6)) {
			txt61.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(1, 6)));
			txt61.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(1, 7)) {
			txt71.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(1, 7)));
			txt71.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(1, 8)) {
			txt81.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(1, 8)));
			txt81.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(2, 1)) {
			txt12.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(2, 1)));
			txt12.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(2, 2)) {
			txt22.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(2, 2)));
			txt22.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(2, 3)) {
			txt32.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(2, 3)));
			txt32.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(2, 4)) {
			txt42.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(2, 4)));
			txt42.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(2, 5)) {
			txt52.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(2, 5)));
			txt52.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(2, 6)) {
			txt62.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(2, 6)));
			txt62.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(2, 7)) {
			txt72.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(2, 7)));
			txt72.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(2, 8)) {
			txt82.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(2, 8)));
			txt82.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(3, 1)) {
			txt13.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(3, 1)));
			txt13.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(3, 2)) {
			txt23.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(3, 2)));
			txt23.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(3, 3)) {
			txt33.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(3, 3)));
			txt33.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(3, 4)) {
			txt43.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(3, 4)));
			txt43.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(3, 5)) {
			txt53.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(3, 5)));
			txt53.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(3, 6)) {
			txt63.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(3, 6)));
			txt63.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(3, 7)) {
			txt73.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(3, 7)));
			txt73.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(3, 8)) {
			txt83.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(3, 8)));
			txt83.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(4, 1)) {
			txt14.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(4, 1)));
			txt14.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(4, 2)) {
			txt24.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(4, 2)));
			txt24.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(4, 3)) {
			txt34.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(4, 3)));
			txt34.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(4, 4)) {
			txt44.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(4, 4)));
			txt44.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(4, 5)) {
			txt54.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(4, 5)));
			txt54.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(4, 6)) {
			txt64.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(4, 6)));
			txt64.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(4, 7)) {
			txt74.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(4, 7)));
			txt74.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(4, 8)) {
			txt84.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(4, 8)));
			txt84.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(5, 1)) {
			txt15.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(5, 1)));
			txt15.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(5, 2)) {
			txt25.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(5, 2)));
			txt25.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(5, 3)) {
			txt35.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(5, 3)));
			txt35.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(5, 4)) {
			txt45.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(5, 4)));
			txt45.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(5, 5)) {
			txt55.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(5, 5)));
			txt55.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(5, 6)) {
			txt65.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(5, 6)));
			txt65.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(5, 7)) {
			txt75.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(5, 7)));
			txt75.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(5, 8)) {
			txt85.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(5, 8)));
			txt85.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(6, 1)) {
			txt16.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(6, 1)));
			txt16.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(6, 2)) {
			txt26.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(6, 2)));
			txt26.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(6, 3)) {
			txt36.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(6, 3)));
			txt36.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(6, 4)) {
			txt46.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(6, 4)));
			txt46.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(6, 5)) {
			txt56.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(6, 5)));
			txt56.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(6, 6)) {
			txt66.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(6, 6)));
			txt66.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(6, 7)) {
			txt76.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(6, 7)));
			txt76.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(6, 8)) {
			txt86.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(6, 8)));
			txt86.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(7, 1)) {
			txt17.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(7, 1)));
			txt17.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(7, 2)) {
			txt27.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(7, 2)));
			txt27.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(7, 3)) {
			txt37.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(7, 3)));
			txt37.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(7, 4)) {
			txt47.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(7, 4)));
			txt47.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(7, 5)) {
			txt57.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(7, 5)));
			txt57.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(7, 6)) {
			txt67.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(7, 6)));
			txt67.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(7, 7)) {
			txt77.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(7, 7)));
			txt77.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(7, 8)) {
			txt87.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(7, 8)));
			txt87.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(8, 1)) {
			txt18.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(8, 1)));
			txt18.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(8, 2)) {
			txt28.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(8, 2)));
			txt28.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(8, 3)) {
			txt38.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(8, 3)));
			txt38.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(8, 4)) {
			txt48.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(8, 4)));
			txt48.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(8, 5)) {
			txt58.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(8, 5)));
			txt58.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(8, 6)) {
			txt68.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(8, 6)));
			txt68.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(8, 7)) {
			txt78.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(8, 7)));
			txt78.setEditable(false);
		}
		if (sudokuNormal.isGridPlayerCellFixed(8, 8)) {
			txt88.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(8, 8)));
			txt88.setEditable(false);
		}

	}

	private void createEasyFixedGrid() {
		if (sudokuEasy.isGridPlayerCellFixed(0, 0)) {
			txt00.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(0, 0)));
			txt00.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(1, 0)) {
			txt01.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(1, 0)));
			txt01.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(2, 0)) {
			txt02.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(2, 0)));
			txt02.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(3, 0)) {
			txt03.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(3, 0)));
			txt03.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(4, 0)) {
			txt04.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(4, 0)));
			txt04.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(5, 0)) {
			txt05.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(5, 0)));
			txt05.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(6, 0)) {
			txt06.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(6, 0)));
			txt06.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(7, 0)) {
			txt07.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(7, 0)));
			txt07.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(8, 0)) {
			txt08.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(8, 0)));
			txt08.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(0, 1)) {
			txt10.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(0, 1)));
			txt10.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(0, 2)) {
			txt20.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(0, 2)));
			txt20.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(0, 3)) {
			txt30.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(0, 3)));
			txt30.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(0, 4)) {
			txt40.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(0, 4)));
			txt40.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(0, 5)) {
			txt50.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(0, 5)));
			txt50.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(0, 6)) {
			txt60.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(0, 6)));
			txt60.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(0, 7)) {
			txt70.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(0, 7)));
			txt70.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(0, 8)) {
			txt80.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(0, 8)));
			txt80.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(1, 1)) {
			txt11.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(1, 1)));
			txt11.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(1, 2)) {
			txt21.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(1, 2)));
			txt21.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(1, 3)) {
			txt31.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(1, 3)));
			txt31.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(1, 4)) {
			txt41.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(1, 4)));
			txt41.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(1, 5)) {
			txt51.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(1, 5)));
			txt51.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(1, 6)) {
			txt61.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(1, 6)));
			txt61.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(1, 7)) {
			txt71.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(1, 7)));
			txt71.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(1, 8)) {
			txt81.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(1, 8)));
			txt81.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(2, 1)) {
			txt12.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(2, 1)));
			txt12.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(2, 2)) {
			txt22.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(2, 2)));
			txt22.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(2, 3)) {
			txt32.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(2, 3)));
			txt32.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(2, 4)) {
			txt42.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(2, 4)));
			txt42.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(2, 5)) {
			txt52.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(2, 5)));
			txt52.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(2, 6)) {
			txt62.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(2, 6)));
			txt62.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(2, 7)) {
			txt72.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(2, 7)));
			txt72.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(2, 8)) {
			txt82.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(2, 8)));
			txt82.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(3, 1)) {
			txt13.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(3, 1)));
			txt13.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(3, 2)) {
			txt23.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(3, 2)));
			txt23.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(3, 3)) {
			txt33.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(3, 3)));
			txt33.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(3, 4)) {
			txt43.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(3, 4)));
			txt43.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(3, 5)) {
			txt53.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(3, 5)));
			txt53.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(3, 6)) {
			txt63.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(3, 6)));
			txt63.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(3, 7)) {
			txt73.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(3, 7)));
			txt73.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(3, 8)) {
			txt83.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(3, 8)));
			txt83.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(4, 1)) {
			txt14.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(4, 1)));
			txt14.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(4, 2)) {
			txt24.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(4, 2)));
			txt24.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(4, 3)) {
			txt34.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(4, 3)));
			txt34.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(4, 4)) {
			txt44.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(4, 4)));
			txt44.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(4, 5)) {
			txt54.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(4, 5)));
			txt54.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(4, 6)) {
			txt64.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(4, 6)));
			txt64.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(4, 7)) {
			txt74.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(4, 7)));
			txt74.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(4, 8)) {
			txt84.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(4, 8)));
			txt84.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(5, 1)) {
			txt15.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(5, 1)));
			txt15.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(5, 2)) {
			txt25.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(5, 2)));
			txt25.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(5, 3)) {
			txt35.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(5, 3)));
			txt35.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(5, 4)) {
			txt45.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(5, 4)));
			txt45.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(5, 5)) {
			txt55.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(5, 5)));
			txt55.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(5, 6)) {
			txt65.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(5, 6)));
			txt65.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(5, 7)) {
			txt75.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(5, 7)));
			txt75.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(5, 8)) {
			txt85.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(5, 8)));
			txt85.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(6, 1)) {
			txt16.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(6, 1)));
			txt16.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(6, 2)) {
			txt26.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(6, 2)));
			txt26.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(6, 3)) {
			txt36.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(6, 3)));
			txt36.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(6, 4)) {
			txt46.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(6, 4)));
			txt46.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(6, 5)) {
			txt56.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(6, 5)));
			txt56.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(6, 6)) {
			txt66.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(6, 6)));
			txt66.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(6, 7)) {
			txt76.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(6, 7)));
			txt76.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(6, 8)) {
			txt86.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(6, 8)));
			txt86.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(7, 1)) {
			txt17.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(7, 1)));
			txt17.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(7, 2)) {
			txt27.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(7, 2)));
			txt27.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(7, 3)) {
			txt37.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(7, 3)));
			txt37.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(7, 4)) {
			txt47.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(7, 4)));
			txt47.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(7, 5)) {
			txt57.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(7, 5)));
			txt57.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(7, 6)) {
			txt67.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(7, 6)));
			txt67.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(7, 7)) {
			txt77.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(7, 7)));
			txt77.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(7, 8)) {
			txt87.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(7, 8)));
			txt87.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(8, 1)) {
			txt18.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(8, 1)));
			txt18.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(8, 2)) {
			txt28.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(8, 2)));
			txt28.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(8, 3)) {
			txt38.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(8, 3)));
			txt38.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(8, 4)) {
			txt48.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(8, 4)));
			txt48.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(8, 5)) {
			txt58.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(8, 5)));
			txt58.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(8, 6)) {
			txt68.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(8, 6)));
			txt68.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(8, 7)) {
			txt78.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(8, 7)));
			txt78.setEditable(false);
		}
		if (sudokuEasy.isGridPlayerCellFixed(8, 8)) {
			txt88.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(8, 8)));
			txt88.setEditable(false);
		}

	}

	private void fillSudokuNormalCellsInterface() {
		if (sudokuNormal.getGridPlayerCellValue(0, 0) != 0) {
			txt00.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(0, 0)));
		}
		if (sudokuNormal.getGridPlayerCellValue(1, 0) != 0) {
			txt01.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(1, 0)));
		}
		if (sudokuNormal.getGridPlayerCellValue(2, 0) != 0) {
			txt02.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(2, 0)));
		}
		if (sudokuNormal.getGridPlayerCellValue(3, 0) != 0) {
			txt03.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(3, 0)));
		}
		if (sudokuNormal.getGridPlayerCellValue(4, 0) != 0) {
			txt04.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(4, 0)));
		}
		if (sudokuNormal.getGridPlayerCellValue(5, 0) != 0) {
			txt05.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(5, 0)));
		}
		if (sudokuNormal.getGridPlayerCellValue(6, 0) != 0) {
			txt06.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(6, 0)));
		}
		if (sudokuNormal.getGridPlayerCellValue(7, 0) != 0) {
			txt07.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(7, 0)));
		}
		if (sudokuNormal.getGridPlayerCellValue(8, 0) != 0) {
			txt08.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(8, 0)));
		}
		if (sudokuNormal.getGridPlayerCellValue(0, 1) != 0) {
			txt10.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(0, 1)));
		}
		if (sudokuNormal.getGridPlayerCellValue(0, 2) != 0) {
			txt20.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(0, 2)));
		}
		if (sudokuNormal.getGridPlayerCellValue(0, 3) != 0) {
			txt30.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(0, 3)));
		}
		if (sudokuNormal.getGridPlayerCellValue(0, 4) != 0) {
			txt40.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(0, 4)));
		}
		if (sudokuNormal.getGridPlayerCellValue(0, 5) != 0) {
			txt50.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(0, 5)));
		}
		if (sudokuNormal.getGridPlayerCellValue(0, 6) != 0) {
			txt60.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(0, 6)));
		}
		if (sudokuNormal.getGridPlayerCellValue(0, 7) != 0) {
			txt70.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(0, 7)));
		}
		if (sudokuNormal.getGridPlayerCellValue(0, 8) != 0) {
			txt80.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(0, 8)));
		}
		if (sudokuNormal.getGridPlayerCellValue(1, 1) != 0) {
			txt11.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(1, 1)));
		}
		if (sudokuNormal.getGridPlayerCellValue(1, 2) != 0) {
			txt21.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(1, 2)));
		}
		if (sudokuNormal.getGridPlayerCellValue(1, 3) != 0) {
			txt31.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(1, 3)));
		}
		if (sudokuNormal.getGridPlayerCellValue(1, 4) != 0) {
			txt41.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(1, 4)));
		}
		if (sudokuNormal.getGridPlayerCellValue(1, 5) != 0) {
			txt51.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(1, 5)));
		}
		if (sudokuNormal.getGridPlayerCellValue(1, 6) != 0) {
			txt61.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(1, 6)));
		}
		if (sudokuNormal.getGridPlayerCellValue(1, 7) != 0) {
			txt71.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(1, 7)));
		}
		if (sudokuNormal.getGridPlayerCellValue(1, 8) != 0) {
			txt81.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(1, 8)));
		}
		if (sudokuNormal.getGridPlayerCellValue(2, 1) != 0) {
			txt12.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(2, 1)));
		}
		if (sudokuNormal.getGridPlayerCellValue(2, 2) != 0) {
			txt22.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(2, 2)));
		}
		if (sudokuNormal.getGridPlayerCellValue(2, 3) != 0) {
			txt32.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(2, 3)));
		}
		if (sudokuNormal.getGridPlayerCellValue(2, 4) != 0) {
			txt42.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(2, 4)));
		}
		if (sudokuNormal.getGridPlayerCellValue(2, 5) != 0) {
			txt52.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(2, 5)));
		}
		if (sudokuNormal.getGridPlayerCellValue(2, 6) != 0) {
			txt62.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(2, 6)));
		}
		if (sudokuNormal.getGridPlayerCellValue(2, 7) != 0) {
			txt72.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(2, 7)));
		}
		if (sudokuNormal.getGridPlayerCellValue(2, 8) != 0) {
			txt82.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(2, 8)));
		}
		if (sudokuNormal.getGridPlayerCellValue(3, 1) != 0) {
			txt13.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(3, 1)));
		}
		if (sudokuNormal.getGridPlayerCellValue(3, 2) != 0) {
			txt23.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(3, 2)));
		}
		if (sudokuNormal.getGridPlayerCellValue(3, 3) != 0) {
			txt33.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(3, 3)));
		}
		if (sudokuNormal.getGridPlayerCellValue(3, 4) != 0) {
			txt43.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(3, 4)));
		}
		if (sudokuNormal.getGridPlayerCellValue(3, 5) != 0) {
			txt53.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(3, 5)));
		}
		if (sudokuNormal.getGridPlayerCellValue(3, 6) != 0) {
			txt63.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(3, 6)));
		}
		if (sudokuNormal.getGridPlayerCellValue(3, 7) != 0) {
			txt73.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(3, 7)));
		}
		if (sudokuNormal.getGridPlayerCellValue(3, 8) != 0) {
			txt83.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(3, 8)));
		}
		if (sudokuNormal.getGridPlayerCellValue(4, 1) != 0) {
			txt14.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(4, 1)));
		}
		if (sudokuNormal.getGridPlayerCellValue(4, 2) != 0) {
			txt24.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(4, 2)));
		}
		if (sudokuNormal.getGridPlayerCellValue(4, 3) != 0) {
			txt34.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(4, 3)));
		}
		if (sudokuNormal.getGridPlayerCellValue(4, 4) != 0) {
			txt44.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(4, 4)));
		}
		if (sudokuNormal.getGridPlayerCellValue(4, 5) != 0) {
			txt54.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(4, 5)));
		}
		if (sudokuNormal.getGridPlayerCellValue(4, 6) != 0) {
			txt64.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(4, 6)));
		}
		if (sudokuNormal.getGridPlayerCellValue(4, 7) != 0) {
			txt74.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(4, 7)));
		}
		if (sudokuNormal.getGridPlayerCellValue(4, 8) != 0) {
			txt84.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(4, 8)));
		}
		if (sudokuNormal.getGridPlayerCellValue(5, 1) != 0) {
			txt15.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(5, 1)));
		}
		if (sudokuNormal.getGridPlayerCellValue(5, 2) != 0) {
			txt25.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(5, 2)));
		}
		if (sudokuNormal.getGridPlayerCellValue(5, 3) != 0) {
			txt35.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(5, 3)));
		}
		if (sudokuNormal.getGridPlayerCellValue(5, 4) != 0) {
			txt45.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(5, 4)));
		}
		if (sudokuNormal.getGridPlayerCellValue(5, 5) != 0) {
			txt55.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(5, 5)));
		}
		if (sudokuNormal.getGridPlayerCellValue(5, 6) != 0) {
			txt65.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(5, 6)));
		}
		if (sudokuNormal.getGridPlayerCellValue(5, 7) != 0) {
			txt75.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(5, 7)));
		}
		if (sudokuNormal.getGridPlayerCellValue(5, 8) != 0) {
			txt85.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(5, 8)));
		}
		if (sudokuNormal.getGridPlayerCellValue(6, 1) != 0) {
			txt16.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(6, 1)));
		}
		if (sudokuNormal.getGridPlayerCellValue(6, 2) != 0) {
			txt26.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(6, 2)));
		}
		if (sudokuNormal.getGridPlayerCellValue(6, 3) != 0) {
			txt36.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(6, 3)));
		}
		if (sudokuNormal.getGridPlayerCellValue(6, 4) != 0) {
			txt46.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(6, 4)));
		}
		if (sudokuNormal.getGridPlayerCellValue(6, 5) != 0) {
			txt56.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(6, 5)));
		}
		if (sudokuNormal.getGridPlayerCellValue(6, 6) != 0) {
			txt66.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(6, 6)));
		}
		if (sudokuNormal.getGridPlayerCellValue(6, 7) != 0) {
			txt76.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(6, 7)));
		}
		if (sudokuNormal.getGridPlayerCellValue(6, 8) != 0) {
			txt86.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(6, 8)));
		}
		if (sudokuNormal.getGridPlayerCellValue(7, 1) != 0) {
			txt17.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(7, 1)));
		}
		if (sudokuNormal.getGridPlayerCellValue(7, 2) != 0) {
			txt27.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(7, 2)));
		}
		if (sudokuNormal.getGridPlayerCellValue(7, 3) != 0) {
			txt37.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(7, 3)));
		}
		if (sudokuNormal.getGridPlayerCellValue(7, 4) != 0) {
			txt47.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(7, 4)));
		}
		if (sudokuNormal.getGridPlayerCellValue(7, 5) != 0) {
			txt57.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(7, 5)));
		}
		if (sudokuNormal.getGridPlayerCellValue(7, 6) != 0) {
			txt67.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(7, 6)));
		}
		if (sudokuNormal.getGridPlayerCellValue(7, 7) != 0) {
			txt77.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(7, 7)));
		}
		if (sudokuNormal.getGridPlayerCellValue(7, 8) != 0) {
			txt87.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(7, 8)));
		}
		if (sudokuNormal.getGridPlayerCellValue(8, 1) != 0) {
			txt18.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(8, 1)));
		}
		if (sudokuNormal.getGridPlayerCellValue(8, 2) != 0) {
			txt28.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(8, 2)));
		}
		if (sudokuNormal.getGridPlayerCellValue(8, 3) != 0) {
			txt38.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(8, 3)));
		}
		if (sudokuNormal.getGridPlayerCellValue(8, 4) != 0) {
			txt48.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(8, 4)));
		}
		if (sudokuNormal.getGridPlayerCellValue(8, 5) != 0) {
			txt58.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(8, 5)));
		}
		if (sudokuNormal.getGridPlayerCellValue(8, 6) != 0) {
			txt68.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(8, 6)));
		}
		if (sudokuNormal.getGridPlayerCellValue(8, 7) != 0) {
			txt78.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(8, 7)));
		}
		if (sudokuNormal.getGridPlayerCellValue(8, 8) != 0) {
			txt88.setText(Integer.toString(sudokuNormal.getGridPlayerCellValue(8, 8)));
		}

	}

	private void fillSudokuEasyCellsInterface() {
		if (sudokuEasy.getGridPlayerCellValue(0, 0) != 0) {
			txt00.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(0, 0)));
		}
		if (sudokuEasy.getGridPlayerCellValue(1, 0) != 0) {
			txt01.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(1, 0)));
		}
		if (sudokuEasy.getGridPlayerCellValue(2, 0) != 0) {
			txt02.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(2, 0)));
		}
		if (sudokuEasy.getGridPlayerCellValue(3, 0) != 0) {
			txt03.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(3, 0)));
		}
		if (sudokuEasy.getGridPlayerCellValue(4, 0) != 0) {
			txt04.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(4, 0)));
		}
		if (sudokuEasy.getGridPlayerCellValue(5, 0) != 0) {
			txt05.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(5, 0)));
		}
		if (sudokuEasy.getGridPlayerCellValue(6, 0) != 0) {
			txt06.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(6, 0)));
		}
		if (sudokuEasy.getGridPlayerCellValue(7, 0) != 0) {
			txt07.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(7, 0)));
		}
		if (sudokuEasy.getGridPlayerCellValue(8, 0) != 0) {
			txt08.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(8, 0)));
		}
		if (sudokuEasy.getGridPlayerCellValue(0, 1) != 0) {
			txt10.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(0, 1)));
		}
		if (sudokuEasy.getGridPlayerCellValue(0, 2) != 0) {
			txt20.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(0, 2)));
		}
		if (sudokuEasy.getGridPlayerCellValue(0, 3) != 0) {
			txt30.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(0, 3)));
		}
		if (sudokuEasy.getGridPlayerCellValue(0, 4) != 0) {
			txt40.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(0, 4)));
		}
		if (sudokuEasy.getGridPlayerCellValue(0, 5) != 0) {
			txt50.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(0, 5)));
		}
		if (sudokuEasy.getGridPlayerCellValue(0, 6) != 0) {
			txt60.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(0, 6)));
		}
		if (sudokuEasy.getGridPlayerCellValue(0, 7) != 0) {
			txt70.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(0, 7)));
		}
		if (sudokuEasy.getGridPlayerCellValue(0, 8) != 0) {
			txt80.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(0, 8)));
		}
		if (sudokuEasy.getGridPlayerCellValue(1, 1) != 0) {
			txt11.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(1, 1)));
		}
		if (sudokuEasy.getGridPlayerCellValue(1, 2) != 0) {
			txt21.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(1, 2)));
		}
		if (sudokuEasy.getGridPlayerCellValue(1, 3) != 0) {
			txt31.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(1, 3)));
		}
		if (sudokuEasy.getGridPlayerCellValue(1, 4) != 0) {
			txt41.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(1, 4)));
		}
		if (sudokuEasy.getGridPlayerCellValue(1, 5) != 0) {
			txt51.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(1, 5)));
		}
		if (sudokuEasy.getGridPlayerCellValue(1, 6) != 0) {
			txt61.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(1, 6)));
		}
		if (sudokuEasy.getGridPlayerCellValue(1, 7) != 0) {
			txt71.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(1, 7)));
		}
		if (sudokuEasy.getGridPlayerCellValue(1, 8) != 0) {
			txt81.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(1, 8)));
		}
		if (sudokuEasy.getGridPlayerCellValue(2, 1) != 0) {
			txt12.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(2, 1)));
		}
		if (sudokuEasy.getGridPlayerCellValue(2, 2) != 0) {
			txt22.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(2, 2)));
		}
		if (sudokuEasy.getGridPlayerCellValue(2, 3) != 0) {
			txt32.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(2, 3)));
		}
		if (sudokuEasy.getGridPlayerCellValue(2, 4) != 0) {
			txt42.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(2, 4)));
		}
		if (sudokuEasy.getGridPlayerCellValue(2, 5) != 0) {
			txt52.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(2, 5)));
		}
		if (sudokuEasy.getGridPlayerCellValue(2, 6) != 0) {
			txt62.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(2, 6)));
		}
		if (sudokuEasy.getGridPlayerCellValue(2, 7) != 0) {
			txt72.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(2, 7)));
		}
		if (sudokuEasy.getGridPlayerCellValue(2, 8) != 0) {
			txt82.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(2, 8)));
		}
		if (sudokuEasy.getGridPlayerCellValue(3, 1) != 0) {
			txt13.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(3, 1)));
		}
		if (sudokuEasy.getGridPlayerCellValue(3, 2) != 0) {
			txt23.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(3, 2)));
		}
		if (sudokuEasy.getGridPlayerCellValue(3, 3) != 0) {
			txt33.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(3, 3)));
		}
		if (sudokuEasy.getGridPlayerCellValue(3, 4) != 0) {
			txt43.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(3, 4)));
		}
		if (sudokuEasy.getGridPlayerCellValue(3, 5) != 0) {
			txt53.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(3, 5)));
		}
		if (sudokuEasy.getGridPlayerCellValue(3, 6) != 0) {
			txt63.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(3, 6)));
		}
		if (sudokuEasy.getGridPlayerCellValue(3, 7) != 0) {
			txt73.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(3, 7)));
		}
		if (sudokuEasy.getGridPlayerCellValue(3, 8) != 0) {
			txt83.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(3, 8)));
		}
		if (sudokuEasy.getGridPlayerCellValue(4, 1) != 0) {
			txt14.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(4, 1)));
		}
		if (sudokuEasy.getGridPlayerCellValue(4, 2) != 0) {
			txt24.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(4, 2)));
		}
		if (sudokuEasy.getGridPlayerCellValue(4, 3) != 0) {
			txt34.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(4, 3)));
		}
		if (sudokuEasy.getGridPlayerCellValue(4, 4) != 0) {
			txt44.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(4, 4)));
		}
		if (sudokuEasy.getGridPlayerCellValue(4, 5) != 0) {
			txt54.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(4, 5)));
		}
		if (sudokuEasy.getGridPlayerCellValue(4, 6) != 0) {
			txt64.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(4, 6)));
		}
		if (sudokuEasy.getGridPlayerCellValue(4, 7) != 0) {
			txt74.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(4, 7)));
		}
		if (sudokuEasy.getGridPlayerCellValue(4, 8) != 0) {
			txt84.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(4, 8)));
		}
		if (sudokuEasy.getGridPlayerCellValue(5, 1) != 0) {
			txt15.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(5, 1)));
		}
		if (sudokuEasy.getGridPlayerCellValue(5, 2) != 0) {
			txt25.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(5, 2)));
		}
		if (sudokuEasy.getGridPlayerCellValue(5, 3) != 0) {
			txt35.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(5, 3)));
		}
		if (sudokuEasy.getGridPlayerCellValue(5, 4) != 0) {
			txt45.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(5, 4)));
		}
		if (sudokuEasy.getGridPlayerCellValue(5, 5) != 0) {
			txt55.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(5, 5)));
		}
		if (sudokuEasy.getGridPlayerCellValue(5, 6) != 0) {
			txt65.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(5, 6)));
		}
		if (sudokuEasy.getGridPlayerCellValue(5, 7) != 0) {
			txt75.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(5, 7)));
		}
		if (sudokuEasy.getGridPlayerCellValue(5, 8) != 0) {
			txt85.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(5, 8)));
		}
		if (sudokuEasy.getGridPlayerCellValue(6, 1) != 0) {
			txt16.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(6, 1)));
		}
		if (sudokuEasy.getGridPlayerCellValue(6, 2) != 0) {
			txt26.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(6, 2)));
		}
		if (sudokuEasy.getGridPlayerCellValue(6, 3) != 0) {
			txt36.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(6, 3)));
		}
		if (sudokuEasy.getGridPlayerCellValue(6, 4) != 0) {
			txt46.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(6, 4)));
		}
		if (sudokuEasy.getGridPlayerCellValue(6, 5) != 0) {
			txt56.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(6, 5)));
		}
		if (sudokuEasy.getGridPlayerCellValue(6, 6) != 0) {
			txt66.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(6, 6)));
		}
		if (sudokuEasy.getGridPlayerCellValue(6, 7) != 0) {
			txt76.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(6, 7)));
		}
		if (sudokuEasy.getGridPlayerCellValue(6, 8) != 0) {
			txt86.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(6, 8)));
		}
		if (sudokuEasy.getGridPlayerCellValue(7, 1) != 0) {
			txt17.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(7, 1)));
		}
		if (sudokuEasy.getGridPlayerCellValue(7, 2) != 0) {
			txt27.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(7, 2)));
		}
		if (sudokuEasy.getGridPlayerCellValue(7, 3) != 0) {
			txt37.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(7, 3)));
		}
		if (sudokuEasy.getGridPlayerCellValue(7, 4) != 0) {
			txt47.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(7, 4)));
		}
		if (sudokuEasy.getGridPlayerCellValue(7, 5) != 0) {
			txt57.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(7, 5)));
		}
		if (sudokuEasy.getGridPlayerCellValue(7, 6) != 0) {
			txt67.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(7, 6)));
		}
		if (sudokuEasy.getGridPlayerCellValue(7, 7) != 0) {
			txt77.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(7, 7)));
		}
		if (sudokuEasy.getGridPlayerCellValue(7, 8) != 0) {
			txt87.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(7, 8)));
		}
		if (sudokuEasy.getGridPlayerCellValue(8, 1) != 0) {
			txt18.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(8, 1)));
		}
		if (sudokuEasy.getGridPlayerCellValue(8, 2) != 0) {
			txt28.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(8, 2)));
		}
		if (sudokuEasy.getGridPlayerCellValue(8, 3) != 0) {
			txt38.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(8, 3)));
		}
		if (sudokuEasy.getGridPlayerCellValue(8, 4) != 0) {
			txt48.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(8, 4)));
		}
		if (sudokuEasy.getGridPlayerCellValue(8, 5) != 0) {
			txt58.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(8, 5)));
		}
		if (sudokuEasy.getGridPlayerCellValue(8, 6) != 0) {
			txt68.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(8, 6)));
		}
		if (sudokuEasy.getGridPlayerCellValue(8, 7) != 0) {
			txt78.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(8, 7)));
		}
		if (sudokuEasy.getGridPlayerCellValue(8, 8) != 0) {
			txt88.setText(Integer.toString(sudokuEasy.getGridPlayerCellValue(8, 8)));
		}
	}

	private void fillSudokuHardCellsInterface() {
		if (sudokuHard.getGridPlayerCellValue(0, 0) != 0) {
			txt00.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(0, 0)));
		}
		if (sudokuHard.getGridPlayerCellValue(1, 0) != 0) {
			txt01.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(1, 0)));
		}
		if (sudokuHard.getGridPlayerCellValue(2, 0) != 0) {
			txt02.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(2, 0)));
		}
		if (sudokuHard.getGridPlayerCellValue(3, 0) != 0) {
			txt03.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(3, 0)));
		}
		if (sudokuHard.getGridPlayerCellValue(4, 0) != 0) {
			txt04.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(4, 0)));
		}
		if (sudokuHard.getGridPlayerCellValue(5, 0) != 0) {
			txt05.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(5, 0)));
		}
		if (sudokuHard.getGridPlayerCellValue(6, 0) != 0) {
			txt06.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(6, 0)));
		}
		if (sudokuHard.getGridPlayerCellValue(7, 0) != 0) {
			txt07.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(7, 0)));
		}
		if (sudokuHard.getGridPlayerCellValue(8, 0) != 0) {
			txt08.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(8, 0)));
		}
		if (sudokuHard.getGridPlayerCellValue(0, 1) != 0) {
			txt10.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(0, 1)));
		}
		if (sudokuHard.getGridPlayerCellValue(0, 2) != 0) {
			txt20.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(0, 2)));
		}
		if (sudokuHard.getGridPlayerCellValue(0, 3) != 0) {
			txt30.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(0, 3)));
		}
		if (sudokuHard.getGridPlayerCellValue(0, 4) != 0) {
			txt40.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(0, 4)));
		}
		if (sudokuHard.getGridPlayerCellValue(0, 5) != 0) {
			txt50.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(0, 5)));
		}
		if (sudokuHard.getGridPlayerCellValue(0, 6) != 0) {
			txt60.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(0, 6)));
		}
		if (sudokuHard.getGridPlayerCellValue(0, 7) != 0) {
			txt70.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(0, 7)));
		}
		if (sudokuHard.getGridPlayerCellValue(0, 8) != 0) {
			txt80.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(0, 8)));
		}
		if (sudokuHard.getGridPlayerCellValue(1, 1) != 0) {
			txt11.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(1, 1)));
		}
		if (sudokuHard.getGridPlayerCellValue(1, 2) != 0) {
			txt21.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(1, 2)));
		}
		if (sudokuHard.getGridPlayerCellValue(1, 3) != 0) {
			txt31.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(1, 3)));
		}
		if (sudokuHard.getGridPlayerCellValue(1, 4) != 0) {
			txt41.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(1, 4)));
		}
		if (sudokuHard.getGridPlayerCellValue(1, 5) != 0) {
			txt51.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(1, 5)));
		}
		if (sudokuHard.getGridPlayerCellValue(1, 6) != 0) {
			txt61.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(1, 6)));
		}
		if (sudokuHard.getGridPlayerCellValue(1, 7) != 0) {
			txt71.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(1, 7)));
		}
		if (sudokuHard.getGridPlayerCellValue(1, 8) != 0) {
			txt81.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(1, 8)));
		}
		if (sudokuHard.getGridPlayerCellValue(2, 1) != 0) {
			txt12.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(2, 1)));
		}
		if (sudokuHard.getGridPlayerCellValue(2, 2) != 0) {
			txt22.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(2, 2)));
		}
		if (sudokuHard.getGridPlayerCellValue(2, 3) != 0) {
			txt32.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(2, 3)));
		}
		if (sudokuHard.getGridPlayerCellValue(2, 4) != 0) {
			txt42.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(2, 4)));
		}
		if (sudokuHard.getGridPlayerCellValue(2, 5) != 0) {
			txt52.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(2, 5)));
		}
		if (sudokuHard.getGridPlayerCellValue(2, 6) != 0) {
			txt62.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(2, 6)));
		}
		if (sudokuHard.getGridPlayerCellValue(2, 7) != 0) {
			txt72.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(2, 7)));
		}
		if (sudokuHard.getGridPlayerCellValue(2, 8) != 0) {
			txt82.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(2, 8)));
		}
		if (sudokuHard.getGridPlayerCellValue(3, 1) != 0) {
			txt13.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(3, 1)));
		}
		if (sudokuHard.getGridPlayerCellValue(3, 2) != 0) {
			txt23.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(3, 2)));
		}
		if (sudokuHard.getGridPlayerCellValue(3, 3) != 0) {
			txt33.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(3, 3)));
		}
		if (sudokuHard.getGridPlayerCellValue(3, 4) != 0) {
			txt43.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(3, 4)));
		}
		if (sudokuHard.getGridPlayerCellValue(3, 5) != 0) {
			txt53.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(3, 5)));
		}
		if (sudokuHard.getGridPlayerCellValue(3, 6) != 0) {
			txt63.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(3, 6)));
		}
		if (sudokuHard.getGridPlayerCellValue(3, 7) != 0) {
			txt73.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(3, 7)));
		}
		if (sudokuHard.getGridPlayerCellValue(3, 8) != 0) {
			txt83.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(3, 8)));
		}
		if (sudokuHard.getGridPlayerCellValue(4, 1) != 0) {
			txt14.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(4, 1)));
		}
		if (sudokuHard.getGridPlayerCellValue(4, 2) != 0) {
			txt24.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(4, 2)));
		}
		if (sudokuHard.getGridPlayerCellValue(4, 3) != 0) {
			txt34.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(4, 3)));
		}
		if (sudokuHard.getGridPlayerCellValue(4, 4) != 0) {
			txt44.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(4, 4)));
		}
		if (sudokuHard.getGridPlayerCellValue(4, 5) != 0) {
			txt54.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(4, 5)));
		}
		if (sudokuHard.getGridPlayerCellValue(4, 6) != 0) {
			txt64.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(4, 6)));
		}
		if (sudokuHard.getGridPlayerCellValue(4, 7) != 0) {
			txt74.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(4, 7)));
		}
		if (sudokuHard.getGridPlayerCellValue(4, 8) != 0) {
			txt84.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(4, 8)));
		}
		if (sudokuHard.getGridPlayerCellValue(5, 1) != 0) {
			txt15.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(5, 1)));
		}
		if (sudokuHard.getGridPlayerCellValue(5, 2) != 0) {
			txt25.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(5, 2)));
		}
		if (sudokuHard.getGridPlayerCellValue(5, 3) != 0) {
			txt35.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(5, 3)));
		}
		if (sudokuHard.getGridPlayerCellValue(5, 4) != 0) {
			txt45.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(5, 4)));
		}
		if (sudokuHard.getGridPlayerCellValue(5, 5) != 0) {
			txt55.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(5, 5)));
		}
		if (sudokuHard.getGridPlayerCellValue(5, 6) != 0) {
			txt65.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(5, 6)));
		}
		if (sudokuHard.getGridPlayerCellValue(5, 7) != 0) {
			txt75.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(5, 7)));
		}
		if (sudokuHard.getGridPlayerCellValue(5, 8) != 0) {
			txt85.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(5, 8)));
		}
		if (sudokuHard.getGridPlayerCellValue(6, 1) != 0) {
			txt16.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(6, 1)));
		}
		if (sudokuHard.getGridPlayerCellValue(6, 2) != 0) {
			txt26.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(6, 2)));
		}
		if (sudokuHard.getGridPlayerCellValue(6, 3) != 0) {
			txt36.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(6, 3)));
		}
		if (sudokuHard.getGridPlayerCellValue(6, 4) != 0) {
			txt46.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(6, 4)));
		}
		if (sudokuHard.getGridPlayerCellValue(6, 5) != 0) {
			txt56.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(6, 5)));
		}
		if (sudokuHard.getGridPlayerCellValue(6, 6) != 0) {
			txt66.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(6, 6)));
		}
		if (sudokuHard.getGridPlayerCellValue(6, 7) != 0) {
			txt76.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(6, 7)));
		}
		if (sudokuHard.getGridPlayerCellValue(6, 8) != 0) {
			txt86.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(6, 8)));
		}
		if (sudokuHard.getGridPlayerCellValue(7, 1) != 0) {
			txt17.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(7, 1)));
		}
		if (sudokuHard.getGridPlayerCellValue(7, 2) != 0) {
			txt27.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(7, 2)));
		}
		if (sudokuHard.getGridPlayerCellValue(7, 3) != 0) {
			txt37.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(7, 3)));
		}
		if (sudokuHard.getGridPlayerCellValue(7, 4) != 0) {
			txt47.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(7, 4)));
		}
		if (sudokuHard.getGridPlayerCellValue(7, 5) != 0) {
			txt57.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(7, 5)));
		}
		if (sudokuHard.getGridPlayerCellValue(7, 6) != 0) {
			txt67.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(7, 6)));
		}
		if (sudokuHard.getGridPlayerCellValue(7, 7) != 0) {
			txt77.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(7, 7)));
		}
		if (sudokuHard.getGridPlayerCellValue(7, 8) != 0) {
			txt87.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(7, 8)));
		}
		if (sudokuHard.getGridPlayerCellValue(8, 1) != 0) {
			txt18.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(8, 1)));
		}
		if (sudokuHard.getGridPlayerCellValue(8, 2) != 0) {
			txt28.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(8, 2)));
		}
		if (sudokuHard.getGridPlayerCellValue(8, 3) != 0) {
			txt38.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(8, 3)));
		}
		if (sudokuHard.getGridPlayerCellValue(8, 4) != 0) {
			txt48.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(8, 4)));
		}
		if (sudokuHard.getGridPlayerCellValue(8, 5) != 0) {
			txt58.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(8, 5)));
		}
		if (sudokuHard.getGridPlayerCellValue(8, 6) != 0) {
			txt68.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(8, 6)));
		}
		if (sudokuHard.getGridPlayerCellValue(8, 7) != 0) {
			txt78.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(8, 7)));
		}
		if (sudokuHard.getGridPlayerCellValue(8, 8) != 0) {
			txt88.setText(Integer.toString(sudokuHard.getGridPlayerCellValue(8, 8)));
		}
	}

}
