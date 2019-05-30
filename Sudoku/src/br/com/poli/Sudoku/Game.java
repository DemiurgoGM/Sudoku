package br.com.poli.Sudoku;

import java.util.Calendar;

public class Game {

	private Calendar startTime;

	private Calendar endTime;

	private Difficulty difficulty = Difficulty.NORMAL;

	@SuppressWarnings("unused")
	private Player player;

	public Game(Player player) {
		startGame(player);
	}

	public long getStartTime() {
		return startTime.getTimeInMillis();
	}

	public long getEndTime() {
		return endTime.getTimeInMillis();
	}

	public void startGame(Player player) {
		this.player = player;
		startTime = Calendar.getInstance();

	}

	public void endGame() {
		endTime = Calendar.getInstance();
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

}
