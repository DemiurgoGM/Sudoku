package br.com.poli.Sudoku;

public class SudokuHard extends Sudoku{

	public SudokuHard(Player player) {
		super(player);
		initializeSudoku();
	}

	@Override
	public void initializeSudoku() {
		createValidGrid(4, 2); //Subgrid com 2 ou 3
		
	}

}
