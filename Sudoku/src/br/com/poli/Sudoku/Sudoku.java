package br.com.poli.Sudoku;

import java.util.Random;

import br.com.poli.Exception.CellIsFixedException;
import br.com.poli.Exception.CellValueException;
import br.com.poli.Exception.GenericException;

public abstract class Sudoku extends Game {

	private Cell[][] gridPlayer = new Cell[9][9]; // Criando a grid do jogador

	private long totalTime;

	public static final int[][] result = new int[9][9];

	public Sudoku(Player player) {
		super(player);

	}

	protected abstract void initializeSudoku();

	public long getTotalTime() {
		totalTime = (super.getEndTime() - super.getStartTime()) / 1000;
		return totalTime;

	}

	public void endGame() {
		super.endGame();

	}

	public void setValue(int value, int x, int y) throws GenericException, CellIsFixedException, CellValueException {
		if (checkValidation(value, x, y)) {
			this.gridPlayer[y][x].setValid(true);
			this.gridPlayer[y][x].setValue(value);

		}

	}

	public void emptyCell(int x, int y) {
		if (!this.gridPlayer[y][x].isFixed()) {
			this.gridPlayer[y][x].setValue(0);
			this.gridPlayer[y][x].setValid(false);
		}
	}

	private boolean checkValidation(int value, int x, int y)
			throws CellIsFixedException, CellValueException, GenericException {
		if (!gridPlayer[y][x].isFixed()) {
			if (checkValueCell(value)) {
				if (checkColumn(value, x, y) && checkRow(value, x, y) && checkAllGrids3x3(value, x, y)) {
					return true;

				} else {
					throw new GenericException();

				}
			} else {
				throw new CellValueException();

			}
		} else {
			throw new CellIsFixedException();

		}

	}

	public boolean checkValueCell(int value) {
		if (value < 10 && value > 0) {
			return true;

		} else {
			return false;

		}
	}

	public boolean checkColumn(int value, int x) {
		for (int i = 0; i < 9; i++) {
			if (value == gridPlayer[i][x].getValue()) {
				// int erro = (i+1);
				// System.out.println("Valor já inserido na posição " + erro + " do eixo Y.");
				return false;

			}
		}
		return true;

	}

	private boolean checkColumn(int value, int x, int y) {
		for (int i = 0; i < 9; i++) {
			if (value == gridPlayer[i][x].getValue() && i != y) {
				// int erro = (i+1);
				// System.out.println("Valor já inserido na posição " + erro + " do eixo Y.");
				return false;

			}
		}
		return true;

	}

	public boolean checkRow(int value, int y) {
		for (int i = 0; i < 9; i++) {
			if (value == gridPlayer[y][i].getValue()) {
				// int erro = (i+1);
				// System.out.println("Valor já inserido na posição " + erro + " do eixo X.");
				return false;

			}
		}
		return true;

	}

	private boolean checkRow(int value, int x, int y) {
		for (int i = 0; i < 9; i++) {
			if (value == gridPlayer[y][i].getValue() && x != i) {
				// int erro = (i+1);
				// System.out.println("Valor já inserido na posição " + erro + " do eixo X.");
				return false;

			}
		}
		return true;

	}

	private boolean checkAllGrids3x3(int value, int x, int y) {
		if (x < 3) {
			if (y < 3) {
				checkGrid3x3(value, 0, 0);

			} else if (y < 6) {
				checkGrid3x3(value, 0, 3);

			} else {
				checkGrid3x3(value, 0, 6);

			}

		} else if (x < 6) {
			if (y < 3) {
				checkGrid3x3(value, 3, 0);

			} else if (y < 6) {
				checkGrid3x3(value, 3, 3);

			} else {
				checkGrid3x3(value, 3, 6);

			}

		} else {
			if (y < 3) {
				checkGrid3x3(value, 6, 0);

			} else if (y < 6) {
				checkGrid3x3(value, 6, 3);

			} else {
				checkGrid3x3(value, 6, 6);

			}
		}
		return true;

	}

	public boolean checkGrid3x3(int value, int x, int y) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (value == gridPlayer[i + y][j + x].getValue()) {
					return false;

				}
			}
		}
		return true;

	}

	protected void createValidGrid(int quantMaxPerSubGridExclusive, int quantMinPerSubGridInclusive) {

		createRandSudokuGrid();

		createFixedCells(quantMaxPerSubGridExclusive, quantMinPerSubGridInclusive);

		createPlayableGrid();

	}

	private void createRandSudokuGrid() {
		// Criando uma Grid 9x9 zerada
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				gridPlayer[i][j] = new Cell(0, true);

			}
		}

		// Preencher aleatoriamente as células da diagonal principal
		createRandDiagonal();
		fillRandSudokuGame(3, 0); // X=3 para pular o primeiro quadrado 3x3, que foi preenchido no método anterior

		// Criando o resultado do jogo
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				result[i][j] = gridPlayer[i][j].getValue();

			}

		}

	}

	private void createRandDiagonal() {
		int rand;

		for (int i = 0; i < 9; i += 3) { // 3 diagonais
			for (int j = 0; j < 3; j++) {// 3 colunas
				for (int j2 = 0; j2 < 3; j2++) {// 3 linhas
					do {
						rand = createValidRandomNumber(10, 1);

					} while (!checkGrid3x3(rand, i, i));
					gridPlayer[j + i][j2 + i].setValue(rand);

				}

			}
		}

	}

	// método recursivo para preencher o restante do Sudoku
	private boolean fillRandSudokuGame(int x, int y) {

		// Algumas subGrids são puladas por que fazem parte da subGrid diagonal, já
		// preenchida

		if (y >= 9 && x >= 9) { // Se for o final do Sudoku, termine o método
			return true;

		}

		if (x >= 9 && y < 8) { // Se X alcançou o final da fila e não é a última fila, desça 1 fila e recomece
								// a preencher
			x = 0;
			y++;

		}

		if (y < 3) {
			if (x < 3) { // Se estiver na subGrid superior-esquerda, faça o x pular essa subGrid
				x = 3;

			}

		} else if (y < 6) {
			if (x == (int) (y / 3) * 3) { // Se estiver na subGrid central, vá para a subGrid central-direita
				x += 3;

			}

		} else {
			if (x == 6) { // Se for a subGrid inferior-direita, desça uma fila e recomece a preencher
				x = 0;
				y++;
				if (y >= 9) // Se Y chegou ao fim do Sudoku, termine o método
					return true;

			}
		}

		for (int numeroIterado = 1; numeroIterado <= 9; numeroIterado++) {
			if (checkGrid3x3(numeroIterado, x - x % 3, y - y % 3) && checkColumn(numeroIterado, x)
					&& checkRow(numeroIterado, y)) {

				gridPlayer[y][x].setValue(numeroIterado);

				if (fillRandSudokuGame(x + 1, y))// Célula preenchida, usar o método recursivo indo para a direita
					return true;

				gridPlayer[y][x].setValue(0);

			}
		}

		return false;

	}

	private void createFixedCells(int quantMaxPerSubGridExclusive, int quantMinPerSubGridInclusive) {

		int randValuePerGrid, randRow, randColumn;
		int maxValueRow = 3, maxValueColumn = 3;
		int minValueRow = 0, minValueColumn = 0, contadorTotal = 0;

		do {
			randValuePerGrid = createValidRandomNumber(quantMaxPerSubGridExclusive, quantMinPerSubGridInclusive);

			for (int i = 0; i < randValuePerGrid; i++) {
				do {
					randRow = createValidRandomNumber(maxValueRow, minValueRow);
					randColumn = createValidRandomNumber(maxValueColumn, minValueColumn);

				} while (gridPlayer[randColumn][randRow].isFixed()); // Garantindo que não acessará a mesma célula
				gridPlayer[randColumn][randRow].setFixed(true);

			}

			// ContadorTotal Referência:
			// 0 1 2
			// 3 4 5
			// 6 7 8

			if (contadorTotal == 2 || contadorTotal == 5 || contadorTotal == 8) { // Movimento Vertical e à esquerda
				maxValueColumn = 3;
				minValueColumn = 0;
				maxValueRow += 3;
				minValueRow += 3;

			} else { // Movimento à direita, da subgrid esquerda à central à direita
				maxValueColumn += 3;
				minValueColumn += 3;

			}

			contadorTotal++;

		} while (contadorTotal < 9);

	}

	private void createPlayableGrid() {
		for (int i = 0; i < gridPlayer.length; i++) {
			for (int j = 0; j < gridPlayer.length; j++) {
				if (!gridPlayer[i][j].isFixed())
					gridPlayer[i][j].setValue(0);

			}
		}

	}

	private int createValidRandomNumber(int maxPossibleExclusive, int minPossibleInclusive) {
		Random random = new Random();
		int randomReturn = 0;

		do {
			randomReturn = random.nextInt(maxPossibleExclusive);

		} while (randomReturn < minPossibleInclusive);

		return randomReturn;
	}

	public int getGridPlayerCellValue(int x, int y) {
		return gridPlayer[y][x].getValue();

	}

	public boolean isGridPlayerCellFixed(int x, int y) {
		return gridPlayer[y][x].isFixed();

	}

	public void printSudoku() {
		System.out.println();
		for (int j = 0; j < gridPlayer.length; j++) {
			for (int j2 = 0; j2 < gridPlayer.length; j2++) {
				System.out.print(gridPlayer[j][j2].getValue() + " ");

			}
			System.out.println();
		}
		System.out.println();

	}

}
