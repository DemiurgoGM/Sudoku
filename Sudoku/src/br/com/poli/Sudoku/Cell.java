package br.com.poli.Sudoku;


public class Cell {

	private int value;
	
	private boolean valid;
	
	private boolean fixed;

	
	
	
	public Cell(int valor , boolean valid , boolean fixo) {
		this.valid = valid ;
		this.fixed = fixo ;
		this.value = valor ;
	}
	
	public Cell(int value, boolean valid) {
		this.value = value;
		this.valid = valid;
	}

	public void setValue(int valor) {
		this.value = valor ;
	}

	public int getValue() {
		return value;
	}
	
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public boolean isFixed(){
		return fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}	

}
