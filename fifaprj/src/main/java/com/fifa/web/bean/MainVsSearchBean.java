package com.fifa.web.bean;

import org.apache.ibatis.type.IntegerTypeHandler;

public class MainVsSearchBean {

	private String player1;
	private String player2;
	private int winScore;
	private int loseScore;
	private double score;
	private String vsMatch;
	
	/*
	 * public MainVsSearchBean(String player1, String player2, int winScore, int
	 * loseScore, String vsMatch){ setPlayer1(player1); setPlayer2(player2);
	 * setWinScore(winScore); setLoseScore(loseScore); setScore(winScore,loseScore);
	 * setVsMatch(vsMatch); }
	 */
	public String getPlayer1() {
		return player1;
	}
	public void setPlayer1(String player1) {
		this.player1 = player1;
	}
	public String getPlayer2() {
		return player2;
	}
	public void setPlayer2(String player2) {
		this.player2 = player2;
	}
	public int getWinScore() {
		return winScore;
	}
	public void setWinScore(int winScore) {
		this.winScore = winScore;
	}
	public int getLoseScore() {
		return loseScore;
	}
	public void setLoseScore(int loseScore) {
		this.loseScore = loseScore;
	}
	public double getScore() {
		return score;
	}
	public void setScore(String winScore, String loseScore) {
		int sum=Integer.parseInt(winScore)+Integer.parseInt(loseScore);
		this.score = (double)(sum/Integer.parseInt(winScore));
	}
	public String getVsMatch() {
		return vsMatch;
	}
	public void setVsMatch(String vsMatch) {
		this.vsMatch = vsMatch;
	}
	
}
