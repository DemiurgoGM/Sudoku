package br.com.poli.Sudoku;

public class SudokuNormal extends Sudoku{

	public SudokuNormal(Player player) {
		super(player);
		initializeSudoku();
	}

	@Override
	public void initializeSudoku() {
		createValidGrid(5, 3); //Subgrid com 3 ou 4 espaços fixos
		
	}

}
