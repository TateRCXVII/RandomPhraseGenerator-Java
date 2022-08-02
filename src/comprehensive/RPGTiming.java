package comprehensive;

import java.util.ArrayList;

public class RPGTiming {
	public static void main(String args[]) {
		// ArrayList<String> city =
		// GrammerGenerator.readCitiesFile("src/comprehensive/cities.txt");
		// GrammerGenerator.generateFile1("src/comprehensive/GrammarTest.txt", city,
		// GrammerGenerator.nonTerminal(city, 10000),5, 5);
		//a();
		//b();
		d();


		// GrammerGenerator.generateFile("src/comprehensive/GrammarTest.txt", city,
		// GrammerGenerator.nonTerminal(city, 5),5, 3);
	}

	public static String generateGrammar() {
		StringBuilder str = new StringBuilder("{\n<start>\n");
		for (int i = 0; i < 10000; i++) {
			str.append("<>");
		}
		str.append("dodg.");
		str.append("\n}\n");
		str.append("\n{\n<>\ncat\ncow\n}");
		return str.toString();
	}

	public static void a() {
		int timesToLoop = 1000;

		for (int n = 1000; n <= 10000; n += 1000) {

			// Generate a new "random" library of size n.
			// String randWord = generateWord(n);

			long startTime, midpointTime, stopTime;

			ArrayList<String> city = GrammerGenerator.readCitiesFile("src/comprehensive/cities.txt");
			GrammerGenerator.generateFile1("src/comprehensive/GrammarTest.txt", city,
					GrammerGenerator.nonTerminal(city, n), 5, 5);

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test.
			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				RandomPhraseGenerator.parse("src/comprehensive/GrammarTest.txt");
				RandomPhraseGenerator.phraseBuilder(5);
			}

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop and
			// generating a new
			// arraylist
			for (int i = 0; i < timesToLoop; i++) {
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;

			System.out.println(n + "\t" + averageTime);
		}
	}
	
	public static void b() {
		int timesToLoop = 500;

		for (int n = 1000; n <= 10000; n += 1000) {

			// Generate a new "random" library of size n.
			// String randWord = generateWord(n);

			long startTime, midpointTime, stopTime;

			ArrayList<String> city = GrammerGenerator.readCitiesFile("src/comprehensive/cities.txt");
			GrammerGenerator.generateFile1("src/comprehensive/GrammarTest.txt", city,
					GrammerGenerator.nonTerminal(city, 100), 7, n);

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test.
			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				RandomPhraseGenerator.parse("src/comprehensive/GrammarTest.txt");
				RandomPhraseGenerator.phraseBuilder(5);
			}

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop and
			// generating a new
			// arraylist
			for (int i = 0; i < timesToLoop; i++) {
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;

			System.out.println(n + "\t" + averageTime);
			}
		}
		
		public static void d() {
		int timesToLoop = 500;

		for (int n = 1000; n <= 10000; n += 1000) {

			// Generate a new "random" library of size n.
			// String randWord = generateWord(n);

			long startTime, midpointTime, stopTime;

			ArrayList<String> city = GrammerGenerator.readCitiesFile("src/comprehensive/cities.txt");
			GrammerGenerator.generateFile1("src/comprehensive/GrammarTest.txt", city,
					GrammerGenerator.nonTerminal(city, 100), 5, 7);

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test.
			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				RandomPhraseGenerator.parse("src/comprehensive/GrammarTest.txt");
				RandomPhraseGenerator.phraseBuilder(n);
			}

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop and
			// generating a new
			// arraylist
			for (int i = 0; i < timesToLoop; i++) {
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;

			System.out.println(n + "\t" + averageTime);
			// }
		}
	}
}
