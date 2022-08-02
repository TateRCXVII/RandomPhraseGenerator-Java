package comprehensive;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * 
 * This class generates a random phrase given an input CFG (context free grammar) file.
 * CFG must be formatted with <start> and optionally non-terminals with the same syntax:
 * <nonterminal>, <non-terminal>, etc.
 * Declarations within the CFG file must begin with { and end with }.
 * 
 * @author Tate Reynolds & Thatcher Geary
 * @version 12/5/21 1.0
 *
 */
 
public class RandomPhraseGenerator {
	//This list will serve as the value to the K,V pair in the accompanying hashmap
	protected static ArrayList<String> terminals;
	
	//hashmap to hold non-termal key and arraylist of terminals/production rule values
	protected static HashMap<String, ArrayList<String>> nonTerminals;
	
	protected static FileReader file;
	protected static BufferedReader br;
	protected static Random rand = new Random();

	public static void main(String[] args) {
		parse(args[0]);
		if(!(Integer.parseInt(args[1]) <= 0))
			System.out.println(phraseBuilder(Integer.parseInt(args[1])));
		
	}

	/**
	 * parses the given grammar file and inputs the terminals and nonterminals into a
	 * hashmap.
	 * The key is the nonterminal, the values are the values associated with the nonterminals
	 * @param filename - the name of the file to be parsed
	 */
	public static void parse(String filename) {
		nonTerminals = new HashMap<>();
		terminals = new ArrayList<>();

		try {
			//reads in the grammar file with a buffered reader
			file = new FileReader(filename);
			br = new BufferedReader(file);
			String line = "";

			while ((line = br.readLine()) != null) {
				//checks if a declaration is starting (non terminal coming up)
				if (line.equals("{"))
					nonTerminal(br.readLine());

			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found." + e.getLocalizedMessage());

		} catch (IOException e) {
			System.out.println("Input Output exception" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * Recursive method that generates a phrase given an input
	 * non-terminal key to acceses random terminal phrases from the 
	 * hashmap.
	 * @param key - the nonterminal key needed to access the hashmap values
	 * @return a partial phrase to add to the main phrase
	 */
	private static StringBuilder generatePhrase(String key) {
		StringBuilder str = new StringBuilder();
		StringBuilder nonTerminalKey = new StringBuilder();
		boolean nonTerminal = false;
		ArrayList<String> arr = nonTerminals.get(key);
		Random rand = new Random();
		String line = arr.get(rand.nextInt(arr.size()));

		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			//check if nonterminal naming has begun
			if (c == '<' && !nonTerminal)
				nonTerminal = true;
			//create the nonTerminal key
			if (nonTerminal) {
				nonTerminalKey.append(c);
				
				//if the declaration is done, generate a phrase with the given nonterminal
				//and reset the non terminal stringbuilder
				if (c == '>') {
					nonTerminal = false;
					str.append((generatePhrase(nonTerminalKey.toString())));
					nonTerminalKey.setLength(0); /* = new StringBuilder(); */
				}
			} else //else it's a terminal part of the sentence, add it to final phrase
				str.append(c);

		}
		return str; /*.toString();*/
	}
	
	/**
	 * Driver method that starts the building process of the phrase
	 * Starts with the <start> nonterminal and calls the recursive method
	 * generatePhrase(). 
	 * @param n - the number of phrases to be generated
	 * @return a random phrase
	 */
	public static String phraseBuilder(int n) {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < n; i++)
			str.append(generatePhrase("<start>") + "\n");
		return str.toString();
	}


	/**
	 * Constructs a non terminal and puts it into a hashmap with the nonterminal as the key
	 * and its accompanying possibilities in an arraylist as the value
	 * @param nonTerminal - the nonTerminal key value
	 */
	private static void nonTerminal(String nonTerminal) {
		ArrayList<String> lines = new ArrayList<>();
		String line = "";
		try {
			while (!(line = br.readLine()).equals("}")) {
				lines.add(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found." + e.getLocalizedMessage());

		} catch (IOException e) {
			System.out.println("Input Output exception" + e.getLocalizedMessage());
		}
		
		nonTerminals.put(nonTerminal, lines);
	}

}
