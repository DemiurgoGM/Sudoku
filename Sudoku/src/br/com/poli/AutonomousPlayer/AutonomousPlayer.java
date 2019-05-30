package br.com.poli.AutonomousPlayer;

import br.com.poli.Exception.CellIsFixedException;
import br.com.poli.Exception.CellValueException;
import br.com.poli.Exception.GenericException;
import br.com.poli.Sudoku.Sudoku;

public class AutonomousPlayer {

	public Sudoku sudoku;
	public long timeTook;

	public <T extends Sudoku> AutonomousPlayer(T t) {
		this.sudoku = t;

	}

	public boolean executeMove() {
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				for (int j2 = 1; j2 < 10; j2++)
					if (sudoku.getGridPlayerCellValue(j, i) == 0 && sudoku.checkColumn(j2, j) && sudoku.checkRow(j2, i)
							&& sudoku.checkGrid3x3(j2, j - j % 3, i - i % 3))
						try {
							sudoku.setValue(j2, j, i);
							return true;
						} catch (GenericException e) {
							// Impossível cair nas exceções
						} catch (CellIsFixedException e) {
							// Impossível cair nas exceções
						} catch (CellValueException e) {
							// Impossível cair nas exceções
						}

		return false;
	}

	public boolean executeGame() {
		long startTime = System.nanoTime(); // Contar o tempo do programa.

		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++) // Percorrendo o Sudoku
				if (sudoku.getGridPlayerCellValue(j, i) == 0) { // Se a célula não foi preenchida, preencha-a
					for (int j2 = 1; j2 < 10; j2++) // Tentando recursivamente preencher com todos os valores
													// possíveis
						if (sudoku.checkColumn(j2, j) && sudoku.checkRow(j2, i)
								&& sudoku.checkGrid3x3(j2, j - j % 3, i - i % 3)) {

							try {

								sudoku.setValue(j2, j, i);

							} catch (GenericException e) {
								// Impossível cair nas exceções
							} catch (CellIsFixedException e) {
								// Impossível cair nas exceções
							} catch (CellValueException e) {
								// Impossível cair nas exceções
							}

							if (executeGame()) {
								return true;

							} else {
								sudoku.emptyCell(j, i);

							}
						}

					return false; // Sudoku não tem valor permitido na célula, retornar à célula anterior
				}

		long endTime = System.nanoTime();
		timeTook = (endTime - startTime); // Parte 2.c.III do projeto

		return true; // Sudoku finalizado
	}

}
