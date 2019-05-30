package br.com.poli.Sudoku;

public enum Difficulty {
	EASY("Dificuldade Fácil Escolhida"),
	NORMAL("Dificuldade Média Escolhida"),
	HARD("Dificuldade Difícil Escolhida");
	
	private String description;
	
	private Difficulty(String description ) {
		 this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
}
