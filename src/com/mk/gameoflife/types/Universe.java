/**
 * Universe.java
 * 
 * The universe of the Game of Life is an infinite two-dimensional
 * orthogonal grid of square cells, each of which is in one of two
 * possible states, live or dead.
 * 
 */
package com.mk.gameoflife.types;

import java.util.Set;
import java.util.TreeSet;

import com.mk.gameoflife.start.Configurations;


/**
 * This class implements status and behavior of Universe which is infinite in
 * size.
 */
public final class Universe  {

	private Set<Cell> universe= new TreeSet<Cell>();


	/**
	 * Default constructor of Universe class
	 * 
	 * @author Mohit Kanwar
	 */
	public Universe() {
	}

	/**
	 * This method is used to display the current generation of universe.
	 * Presently it displays the current generation on console. Each live cell
	 * is represented by X. Dead cell is either represented by - or not
	 * displayed at all.
	 * 
	 * @author Mohit Kanwar
	 */
	public void display() {

		if (universe.size() != 0) {
			Cell firstCell = this.first();
			Cell lastCell = this.last();
			Cell cellByPosition;
			for (int i = firstCell.getPosY() - 1; i <= lastCell.getPosY() + 1; i++) {
				for (int j = -1; j <= lastCell.getPosX() + firstCell.getPosX()+1; j++) {
					cellByPosition = this.getCellByPosition(j, i);
					System.out.print(cellByPosition);
				}
				System.out.println();
			}
		} else {
			System.out.println(Configurations.DEAD_CELL_SYMBOL);
		}
	}

	/**
	 * This method returns a cell from the universe by its position.
	 * 
	 * @param xPos
	 *            :int the x position of desired cell
	 * @param yPos
	 *            :int the y position of desired cell
	 * @return Cell
	 * @author Mohit_Kanwar
	 */
	public Cell getCellByPosition(int xPos, int yPos) {

		// create a new dead cell by position
		Cell cellByPos = new Cell(xPos, yPos, false);
		if (universe.contains(cellByPos)) {
			cellByPos.setAlive(true);
		}

		return cellByPos;
	}

	/**
	 * This method overrides the add method of parent class. It adds a cell to
	 * current universe only if it is alive.
	 * 
	 * @return boolean true if addition is successful, else false
	 * @param Cell
	 *            , cell to be added to this Universe
	 * @author Mohit Kanwar
	 */
	public boolean add(Cell cell) {
		boolean success = false;
		if (cell.isAlive()) {
			success = universe.add(cell);
		}
		return success;
	}

	/**
	 * This method overrides the first method of parent class. It returns the
	 * first element of the universe if it is not empty. else returns a dead.
	 * cell
	 * 
	 * @return boolean true if addition is successful, else false
	 * @param Cell
	 *            , cell to be added to this Universe
	 * @author Mohit Kanwar
	 */
	public Cell first() {
		Cell cell = new Cell(0, 0, false);
		if (universe.size() != 0) {
			cell = ((TreeSet<Cell>) universe).first();
		}
		return cell;
	}
	/**
	 * This method overrides the last method of parent class. It returns the
	 * first element of the universe if it is not empty. else returns a dead.
	 * cell
	 * 
	 * @return boolean true if addition is successful, else false
	 * @param Cell
	 *            , cell to be added to this Universe
	 * @author Mohit Kanwar
	 */
	public Cell last() {
		Cell cell = new Cell(0, 0, false);
		if (universe.size() != 0) {
			cell = ((TreeSet<Cell>) universe).last();
		}
		return cell;
	}

	/**
	 * This method creates the next generation after analyzing the generation
	 * passed
	 * 
	 * @param pUniverse
	 *            input universe for which next generation is to be deduced
	 * @return Universe The Next-Gen universe is returned
	 * @author Mohit Kanwar
	 */
	public  Universe getNextGen() {
		// First and last cell of current generation of universe
		Cell firstCellOfUniversePassed = (Cell) this.first();
		Cell lastCellOfUniversePassed = (Cell) this.last();

		/*
		 * indexes from which analysis should begin from an index which 
		 * might have the capability to reborn
		 */

		int indexX = firstCellOfUniversePassed.getPosX() - (lastCellOfUniversePassed.getPosX()+1);
		int indexY = firstCellOfUniversePassed.getPosY() - (lastCellOfUniversePassed.getPosY()+1);
		Cell presentCell;

		Universe newGenUniverse = new Universe();
		/*
		 * analyze all the cells in current generation of universe as well as
		 * the immediate neighbors (as they have the capability to reborn under
		 * some criteria)
		 */

		while (true) {
			// get cell for analysis
			presentCell = this.getCellByPosition(indexX, indexY);
			// get next generation of the cell and add to new generation of
			// universe
			
			newGenUniverse.add(nextGenCell(presentCell, this));
			// if all cells are covered, break out of the loop
			if ((indexX == lastCellOfUniversePassed.getPosX() + (lastCellOfUniversePassed.getPosX()+1))
					&& (indexY == lastCellOfUniversePassed.getPosY() + (lastCellOfUniversePassed.getPosY()+1))) {
				break;

				// if x-axis is covered, increment Y to check next level and
				// reset X level
			} else if (indexX == lastCellOfUniversePassed.getPosX() + (lastCellOfUniversePassed.getPosX()+1)) {
				indexY++;

				indexX = firstCellOfUniversePassed.getPosX() - (lastCellOfUniversePassed.getPosX()+1);
				// increment x-axis after each cell is checked
			} else {
				indexX++;
			}

		}

		return newGenUniverse;
	}

	/**
	 * This method calculates the value of next generation cell
	 * 
	 * @param cell
	 *            Current cell for which next generation is to be calculated
	 * @param pUniverse
	 *            present universe for analysis
	 * @return Cell New generation of this cell
	 * @author Mohit Kanwar
	 */
	private static Cell nextGenCell(Cell cell, Universe pUniverse) {
		int aliveNeighbours = getAliveNeighbours(cell, pUniverse);
		// create a new dead cell with same position as input
		Cell newCell = new Cell(cell.getPosX(), cell.getPosY(), false);
		// applying Laws of the Game of Life
		if (cell.isAlive()) {
			if ((aliveNeighbours == 2) || (aliveNeighbours == 3)) {
				newCell.setAlive(true);
			}

		} else {
			if (aliveNeighbours == 3) {
				newCell.setAlive(true);
			}
		}

		return newCell;
	}

	/**
	 * This Method returns alive neighbors of a cell.
	 * 
	 * @param cell
	 *            Whose neighbors are to be calculated
	 * @param pUniverse
	 *            Universe in which neighbors are to be calculated
	 * @return
	 * @author Mohit Kanwar
	 */
	private static int getAliveNeighbours(Cell cell, Universe pUniverse) {
		/*
		 * if cell is at (x,y) position, its neighbors will be at
		 * (x-1,y-1),(x-1,y),(x-1,y+1) (x,y-1) self
		 * (x,y+1)(x+1,y-1),(x+1,y),(x+1,y+1)
		 */

		int aliveNeighbours = 0;
		int currentCellXpos = cell.getPosX();
		int currentCellYpos = cell.getPosY();
		// calculate alive neighbors for present cell
		for (int i = currentCellXpos - 1; i <= currentCellXpos + 1; i++) {
			for (int j = currentCellYpos - 1; j <= currentCellYpos + 1; j++) {
				if ((i == currentCellXpos) && (j == currentCellYpos)) {
					continue;
				}
				//if cell is alive, it will be present in universe
				if (pUniverse.universe.contains(new Cell(i,j,true))) {
					aliveNeighbours++;
				}

			}
		}
		return aliveNeighbours;
	}

}
