
package com.mk.gameoflife.start;

import com.mk.gameoflife.behavior.UniversePopulator;
import com.mk.gameoflife.types.Universe;

public final class LetThereBeLife {

	/**
	 * This is main method from which the application will begin
	 * 
	 * @param String
	 *            array [0] inputMode->A,B,C,D for corresponding input,
	 *            default:D array [1] repeatMode-> No of Generations (int) or
	 *            any other key for infinite, default: infinite
	 * 
	 */
	public static void main(String[] args) {

		// load the defaults if not provided
		args = loadDefaults(args);
		// populate universe with test data
		Universe myUniverse = UniversePopulator.getInstance().getPopulatedUniverse(args[0]);
		System.out.println(Configurations.MESSAGE_FOR_INPUT);
		// display the input
		myUniverse.display();
		// count the number of generations
		int noOfGenerations = calculateNumberOfGen(args);
		int i = 0;
		while (true) {
			if ((noOfGenerations != -1) && (i >= noOfGenerations)) {

				break;

			}
			i++;
			// get next generation of universe
			myUniverse = myUniverse.getNextGen();

			System.out.println(Configurations.MESSAGE_FOR_GENERATION + i);

			myUniverse.display();

		}
		
	}

	/**
	 * Refactored method to calculate number of generations on the basis of
	 * passed arguments
	 * 
	 * @param args
	 * @return
	 */
	private static int calculateNumberOfGen(String[] args) {
		int noOfGenerations = 0;
		try {
			noOfGenerations = Integer.parseInt(args[1]);
			if (noOfGenerations < 0) {
				noOfGenerations = -1;
			}
		} catch (NumberFormatException e) {
			noOfGenerations = -1;
		}
		return noOfGenerations;
	}

	/**
	 * This method is used to load default values if inputs are not provided
	 * 
	 * @param args
	 *            Input string array
	 * @author Mohit Kanwar
	 */
	private static String[] loadDefaults(String[] args) {
		String[] filteredArgs = new String[2];
		getDefaultMode(args, filteredArgs);
		getDefaultGenerations(args, filteredArgs);
		return filteredArgs;
	}

	/**
	 * Loads default input mode if it is not passed
	 * 
	 * @param args
	 * @param filteredArgs
	 * @author Mohit Kanwar
	 */
	private static void getDefaultMode(String[] args, String[] filteredArgs) {
		try {
			filteredArgs[0] = args[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			filteredArgs[0] = Configurations.DEFAULT_INPUT_MODE;
		}
	}

	/**
	 * Loads default number of generations if it is not passed
	 * 
	 * @param args
	 * @param filteredArgs
	 * @author Mohit Kanwar
	 */
	private static void getDefaultGenerations(String[] args,
			String[] filteredArgs) {
		try {
			filteredArgs[1] = args[1];
		} catch (ArrayIndexOutOfBoundsException e) {
			filteredArgs[1] = Integer.toString(Configurations.DEFAULT_NO_OF_GENERATIONS);
		}
	}

}
