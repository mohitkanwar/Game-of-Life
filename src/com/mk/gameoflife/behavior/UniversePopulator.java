
package com.mk.gameoflife.behavior;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.mk.gameoflife.start.Configurations;
import com.mk.gameoflife.types.Cell;
import com.mk.gameoflife.types.Universe;


public class UniversePopulator {
	
	private static UniversePopulator instance=null;
	private UniversePopulator(){
		
	}
	public static UniversePopulator getInstance(){
		if(instance==null){
			instance= new UniversePopulator();
		}
		return instance;
	}
	public  Universe getPopulatedUniverse(String inMode) {
		Universe aUniverse;
		switch(inMode){
		case Configurations.BLOCK_PATTERN:
			aUniverse = readInputA();
			break;
		case Configurations.BOAT_PATTERN:
			aUniverse = readInputB();
			break;
		case Configurations.BLINKER_PATTERN:
			aUniverse = readInputC();
			break;
		case Configurations.TOAD_PATTERN:
			aUniverse = readInputD();
			break;
		default:
			aUniverse = readInputFromFile(inMode);
		}
		return aUniverse;
	}

	private  Universe readInputA() {
		Universe setOfCells = new Universe();
		setOfCells.add(new Cell(0, 0, true));
		setOfCells.add(new Cell(0, 1, true));
		setOfCells.add(new Cell(1, 0, true));
		setOfCells.add(new Cell(1, 1, true));
		return setOfCells;
	}

	
	private  Universe readInputB() {
		Universe setOfCells = new Universe();
		setOfCells.add(new Cell(0, 0, true));
		setOfCells.add(new Cell(0, 1, true));
		setOfCells.add(new Cell(0, 2, false));
		setOfCells.add(new Cell(1, 0, true));
		setOfCells.add(new Cell(1, 1, false));
		setOfCells.add(new Cell(1, 2, true));
		setOfCells.add(new Cell(2, 0, false));
		setOfCells.add(new Cell(2, 1, true));
		setOfCells.add(new Cell(2, 2, false));
		return setOfCells;
	}

	private  Universe readInputC() {
		Universe setOfCells = new Universe();
		setOfCells.add(new Cell(0, 0, false));
		setOfCells.add(new Cell(0, 1, true));
		setOfCells.add(new Cell(0, 2, false));
		setOfCells.add(new Cell(1, 0, false));
		setOfCells.add(new Cell(1, 1, true));
		setOfCells.add(new Cell(1, 2, false));
		setOfCells.add(new Cell(2, 0, false));
		setOfCells.add(new Cell(2, 1, true));
		setOfCells.add(new Cell(2, 2, false));
		return setOfCells;
	}


	private  Universe readInputD() {

		Universe setOfCells = new Universe();
		setOfCells.add(new Cell(0, 0, false));
		setOfCells.add(new Cell(1, 0, true));
		setOfCells.add(new Cell(2, 0, true));
		setOfCells.add(new Cell(3, 0, true));
		setOfCells.add(new Cell(0, 1, true));
		setOfCells.add(new Cell(1, 1, true));
		setOfCells.add(new Cell(2, 1, true));
		setOfCells.add(new Cell(3, 1, false));

		return setOfCells;
	}

	
	private  Universe readInputFromFile(String fileName) {
		Universe setOfCells = new Universe();
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(fileName)));
			String line;
			int yAxis = 0;
			boolean life;
			char[] charArray;
			while (null != (line = br.readLine())) {
				charArray = line.toCharArray();
				for (int xAxis = 0; xAxis < charArray.length; xAxis++) {
					if (charArray[xAxis] == Configurations.LIVING_CELL_SYMBOL.charAt(0)) {
						life = true;
					} else {
						life = false;
					}
					setOfCells.add(new Cell(xAxis, yAxis, life));
				}
				yAxis++;
			}
		} catch (FileNotFoundException e) {
			System.out.println(Configurations.FILE_NOT_FOUND_ERROR_MESSAGE + fileName);
			System.exit(1);
		} catch (IOException e) {
			System.out.println(Configurations.IO_EXCEPTION_ERROR_MESSAGE + fileName);
			System.exit(1);
		}
		return setOfCells;
	}
}
