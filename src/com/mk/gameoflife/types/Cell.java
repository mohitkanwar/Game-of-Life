/**
 * Cell.java
 * This class defines a cell which form a universe.
 * Each cell can be in one of two possible states, live or dead. 
 * Every cell interacts with its eight neighbors, 
 * which are the cells that are directly horizontally, vertically, or diagonally adjacent. 
 * At each step in time, the following transitions occur:
 *	Any live cell with fewer than two live neighbors dies, as if by loneliness.
 *	Any live cell with more than three live neighbors dies, as if by overcrowding.
 *	Any live cell with two or three live neighbors lives, unchanged, to the next generation.
 *	Any dead cell with exactly three live neighbors comes to life.
 * 
 */
package com.mk.gameoflife.types;

import com.mk.gameoflife.start.Configurations;

/**
 * This class is used to define a cell in universe. It implements Comparable
 * interface
 */
public final class Cell implements Comparable<Cell> {
	private boolean isAlive;
	private int posX;
	private int posY;
	
	/**
	 * This constructor creates a new cell
	 * 
	 * @param xAxis
	 *            position of cell on x axis
	 * @param yAxis
	 *            position of cell on y axis
	 * @param state
	 *            true : Alive state of the cell false: Dead state of the cell
	 * @author Mohit Kanwar
	 */
	public Cell(int xAxis, int yAxis, boolean state) {
		this.posX = xAxis;
		this.posY = yAxis;
		this.isAlive = state;
	}

	

	/**
	 * Setter for cell's state
	 * 
	 * @param isAlive
	 *            boolean (true if alive else false)
	 * @author Mohit Kanwar
	 */
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	/**
	 * Getter for cell's state
	 * 
	 * @return boolean true, if alive else false
	 * @author Mohit Kanwar
	 */
	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * Setter for x-axis position of the cell
	 * 
	 * @param posX
	 *            , int x-axis position of the cell
	 * @author Mohit Kanwar
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * Getter for x-axis position of the cell
	 * 
	 * @return int, x-axis position of the cell
	 * @author Mohit Kanwar
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * Setter for y-axis position of the cell
	 * 
	 * @param posY
	 *            int, y-axis position
	 * @author Mohit Kanwar
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * Getter for y-axis of the cell
	 * 
	 * @return int, y axis position of the cell
	 * @author Mohit Kanwar
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * This is an overridden method to compare two cells. 
	 * (For Comparable interface)
	 * Assumption is that a smaller y-axis cell is smaller than other
	 * if both the cells are on same level (y-axis), then x-axis will be considered
	 * 
	 * @param pCell, Cell to which current cell will be compared
	 * @return int
	 * 			-1 if current cell comes before the input cell
	 * 			1  if current cell comes after the input cell
	 * 			0 if both are at same position
	 * @author Mohit Kanwar
	 */
	@Override
	public int compareTo(Cell pCell) {
		int finalResult;
		int yRes = ((Integer) this.posY).compareTo(((Cell) pCell).getPosY());
		if (yRes == 0) {
			finalResult = ((Integer) this.posX).compareTo(((Cell) pCell)
					.getPosX());
		} else {
			finalResult = yRes;
		}
		return finalResult;
	}
	/**
	 * This method displays the character according to life status of Cell
	 * @return String
	 * @author Mohit Kanwar
	 */
	public String toString(){
		return (this.isAlive)?Configurations.LIVING_CELL_SYMBOL:Configurations.DEAD_CELL_SYMBOL;
	}
}
