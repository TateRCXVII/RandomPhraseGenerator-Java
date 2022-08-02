package comprehensive;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class GrammerGenerator {

	public static void main(String[] args) {
		ArrayList<String> city = readCitiesFile("src/comprehensive/cities.txt");
		generateFile("src/comprehensive/GrammarTest.txt", city, nonTerminal(city, 100),4, 4);
	}

	public static ArrayList<String> readCitiesFile(String filename) {
		File fileOfCities;
		Scanner scan = null;
		try {
			fileOfCities = new File(filename);
			scan = new Scanner(fileOfCities, "utf-8");
		} catch (Exception e) {

		}

		// Scanner scan = new Scanner (fileOfCities);
		String[] citiesArr = new String[Integer.parseInt(scan.nextLine())];
		int i = 0;
		while (scan.hasNextLine()) {
			String Line = scan.nextLine();
			citiesArr[i] = Line;
			i++;
		}
		scan.close();
		ArrayList<String> list = new ArrayList<>();
		for (String d : citiesArr) {
			String[] arr = d.split(" ");
			for (int j = 0; j < arr.length; j++) {
				list.add(arr[j]);
			}
		}
		return list;
	}

	public static ArrayList<String> nonTerminal(ArrayList<String> list, int n) {
		Collections.shuffle(list);
		ArrayList<String> output = new ArrayList<>();
		for (int i = 0;  output.size() < n; i++) {
			if(!output.contains("<" + list.get(i) + ">"))
			output.add("<" + list.get(i) + ">");
		}
		
		return output;
	}

	public static void generateFile(String filename, ArrayList<String> words, ArrayList<String> nonTerminal, int sentenceLength, int paragraphLength) {
		Random rand = new Random();
		StringBuilder grammar = new StringBuilder();
		grammar.append("{\n");
		grammar.append("<start>\n");
		for (int i = 0; i < nonTerminal.size(); i++) {
			grammar.append(nonTerminal.get(i) + " ");
		}
		grammar.append("\n}\n");

		for (int i = 0; i < nonTerminal.size(); i++) {
			grammar.append("{\n" + nonTerminal.get(i) + "\n");
			for (int z = 0; z < paragraphLength; z++) {
				for (int j = 0; j < sentenceLength; j++) {
					grammar.append(words.get(rand.nextInt(words.size())) + " ");
					if (z == 4 && j ==4)
						grammar.append(nonTerminal.get(rand.nextInt(nonTerminal.size())) + " ");
				}
				grammar.append("\n");
			}
			grammar.append("}\n\n");
		}
		System.out.println(grammar);
		try {
			PrintWriter output = new PrintWriter(new FileWriter(filename));
			output.println(grammar);
			output.close();
		} catch (Exception e) {

		}

	}
	
	public static void generateFile1(String filename, ArrayList<String> words, ArrayList<String> nonTerminal, int sentenceLength, int paragraphLength) {
		PrintWriter output = null;
		try {
			output = new PrintWriter(new FileWriter(filename));
		} catch (Exception e) {

		}
		Random rand = new Random();
		output.print("{\n");
		output.print("<start>\n");
		for (int i = 0; i < nonTerminal.size(); i++) {
			output.print(nonTerminal.get(i) + " ");
		}
		output.print("\n}\n");

		for (int i = 0; i < nonTerminal.size(); i++) {
			output.print("{\n" + nonTerminal.get(i) + "\n");
			for (int z = 0; z < paragraphLength; z++) {
				for (int j = 0; j < sentenceLength; j++) {
					output.print(words.get(rand.nextInt(words.size())) + " ");
					if (z == z-1 && j ==j-1)
						output.print(nonTerminal.get(rand.nextInt(nonTerminal.size())) + " ");
				}
				output.print("\n");
			}
			output.print("}\n\n");
		}		
		output.close();

	}

}
