package br.com.poli.Sudoku;

public class SudokuEasy extends Sudoku{

	public SudokuEasy(Player player) {
		super(player);
		initializeSudoku();
	}

	@Override
	public void initializeSudoku() {
		createValidGrid(5, 4); //Sempre acessará 4 valores na subGrid
		
	}

}
