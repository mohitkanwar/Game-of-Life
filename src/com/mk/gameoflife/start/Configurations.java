
package com.mk.gameoflife.start;

public final class Configurations {
	
	//Input modes constants
	public static final String TOAD_PATTERN = "D";
	public static final String BLINKER_PATTERN = "C";
	public static final String BOAT_PATTERN = "B";
	public static final String BLOCK_PATTERN = "A";

	//Cell status symbols
	public static final String LIVING_CELL_SYMBOL = "X";
	public static final String DEAD_CELL_SYMBOL = " ";
	
	//Messages to be displayed to end user
	public static final String IO_EXCEPTION_ERROR_MESSAGE = "Some I/O exception has occured with File provided-->";
	public static final String FILE_NOT_FOUND_ERROR_MESSAGE = "File provided was not found! -->";
	public static final String MESSAGE_FOR_GENERATION = "\n Generation: ";
	public static final String MESSAGE_FOR_INPUT = "Input :\n";
	
	
	/***********************Default values********************/
	public static final int DEFAULT_NO_OF_GENERATIONS = -1;
	public static final String DEFAULT_INPUT_MODE = TOAD_PATTERN;
	
	

}
