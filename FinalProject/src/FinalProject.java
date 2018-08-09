import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class FinalProject{
	public static void main(String[] args) throws FileNotFoundException{
		Scanner input;
		
		// read books.txt
		// contains file paths to the books
		File books = new File("src/books.txt");
		Scanner booksScanner = new Scanner(books);
		ArrayList<String> booksArray = new ArrayList<String>();
		while (booksScanner.hasNext()) {
			String buffer = booksScanner.nextLine();
			// assume one line corresponds to one book, no spaces
			booksArray.add(buffer);
		}
		booksScanner.close();
		
		// read words.txt
		// contains set of comparison words
		File words = new File("src/words.txt");
		Scanner wordsScanner = new Scanner(words);
		// put words in words.txt into hashmap
		HashMap<String, Boolean> wordsHash = readWords(wordsScanner, false);
		
		// read book in books.txt
		ArrayList<HashMap<String, Boolean>> booksHashArray = new ArrayList<HashMap<String, Boolean>>();
		for (String bookFilePath:booksArray) {
			booksHashArray.add(readInBook(bookFilePath));
		}
		
		// Compare each book w.r.t. the first book
		for (int i = 1; i < booksHashArray.size(); i++) {
			compareBooks(booksHashArray.get(0), booksArray.get(0), booksHashArray.get(i), booksArray.get(i), wordsHash);
		}
		System.out.println("");
		compareBookWithWordList(booksHashArray.get(0), booksArray.get(0), wordsHash);
		// total unique words, proper nouns, comparison book
		// words in both hash maps, words in reference book, words in comparison book
		// words in focus book which are also in vocabulary list
		// words in vocabulary list not in focus book
		// must use mergesort to sort the words	
	}
	
	public static void compareBooks(HashMap<String, Boolean> book1, 
			String book1Name, 
			HashMap<String, Boolean> book2, 
			String book2Name,
			HashMap<String, Boolean> words) {
		printBookStats(book1, book1Name, true);
		printBookStats(book2, book2Name, false);
		int wordsInBoth = 0;
		int book1Only = 0;
		int book2Only = 0;
		int wordListBook1Only = 0;
		int wordListBook2Only = 0;
		for (String word: book1.keySet()) {
			if (book2.get(word) != null) {
				wordsInBoth++;
			}
			else {
				book1Only++;
				if (words.get(word) != null) {
					wordListBook1Only++;
				}
			}
		}
		for (String word: book2.keySet()) {
			if (book1.get(word) == null) {
				book2Only++;
				if (words.get(word) != null) {
					wordListBook2Only++;
				}
			}
		}
		System.out.println("Words in both: " + wordsInBoth);
		System.out.println("Words in " + book1Name + " only: " + book1Only);
		System.out.println("Words in " + book2Name + " only: " + book2Only);
		System.out.println("Wordlist " + book1Name + " only: " + wordListBook1Only);
		System.out.println("Wordlist " + book2Name + " only: " + wordListBook2Only);
	}
	
	public static void printBookStats(HashMap<String, Boolean> book, String name, Boolean focus) {
		int properNounCount = 0;
		for (String word: book.keySet()) {
			if (book.get(word)) {
				properNounCount++;
			}
		}
		if (focus) {
			System.out.println("Focus Book: " + name);
		}
		else {
			System.out.println("Comparison Book: " + name);
		}
		System.out.println("Total words: " + book.size());
		System.out.println("Proper Nouns: " + properNounCount);
		System.out.println("");
	}
	
	public static void compareBookWithWordList(HashMap<String, Boolean> book, String name, HashMap<String, Boolean> words) {
		System.out.println("Words in " + name + " which are also in vocabulary list: ");
		for (String word: words.keySet()) {
			if (book.get(word) != null) {
				System.out.println(word);
			}
		}
		System.out.println("");
		System.out.println("Words in vocabulary list not in " + name + ":");
		for (String word: words.keySet()) {
			if (book.get(word) == null) {
				System.out.println(word);
			}
		}
	}
	
	public static HashMap<String, Boolean> readInBook(String bookFilePath) throws FileNotFoundException{
		// read book in books.txt
		// chop out punctuation and whitespace before reading in word
		File book = new File(bookFilePath);
		Scanner input = new Scanner(book);
		HashMap<String, Boolean> focusBookWords = readWords(input, true);
		// drop all stem words from hashmaps
		focusBookWords = dropStemWords(focusBookWords);
		return focusBookWords;
	}
	
	public static HashMap<String, Boolean> dropStemWords(HashMap<String, Boolean> map){
		String[] wordArray = map.keySet().toArray(new String[map.size()]);
		for (String word: wordArray) {
			if (word.endsWith("ing") && word.length() > 3) {
				// check if root word exists
				String trimmed = word.substring(word.length() - 4, word.length() - 1);
				if (map.get(trimmed) != null) {
					map.remove(word);
				}
			}
			if (word.endsWith("ed") && word.length() > 2) {
				// check if root word exists
				String trimmed = word.substring(word.length() - 3, word.length() - 1);
				if (map.get(trimmed) != null) {
					map.remove(word);
				}
			}
		}
		return map;
	}
	
	public static HashMap<String, Boolean> readWords(Scanner input, boolean process){
		HashMap<String, Boolean> words = new HashMap<String, Boolean>();
		String[] buffArray;
		boolean properNoun = false;
		boolean endOfSentence = false;
		while (input.hasNext()) {
			String buffer = input.nextLine();
			buffArray = buffer.split(" ");
			for (int i = 0; i < buffArray.length; i++) {
				if (process) {
					if (buffArray[i].isEmpty() || buffArray[i] == null) {
						endOfSentence = true;
						continue;
					}
					// check if proper noun
					if ((buffArray[i].charAt(0) >= 'A' &&
							buffArray[i].charAt(0) <= 'Z') &&
							!endOfSentence) {
						properNoun = true;
					}
					else {
						properNoun = false;
					}
					// check if end of sentence
					endOfSentence = checkEndOfSentence(buffArray[i]);
					// strip punctuation at start and end of word
					// numbers and letters are not punctuation
					buffArray[i] = stripPunctuation(buffArray[i]);
				}
				else {
					properNoun = false;
				}
				// continue to next word if string is now empty or null
				if (buffArray[i] == null || buffArray[i].isEmpty()) {
					// a proper noun may have previously started the sentence
					// and have been inserted without the proper noun flag
					// remedy this if this is the case
					if (properNoun) {
					}
					continue;
				}
				// if not empty, put string into hashmap if not already in
				if (words.get(buffArray[i]) == null){
					if (properNoun) {
//						System.out.println(buffArray[i]);
					}
					words.put(buffArray[i], properNoun);
				}
				else if (properNoun && !words.get(buffArray[i])) {
//					System.out.println("CHECK: " + buffArray[i]);
					words.put(buffArray[i], properNoun);
				}
			}
		}
		return words;
	}
	
	public static boolean checkEndOfSentence(String s) {
		boolean endOfSentence = false;
		for (int i = s.length() - 1; i >= 0; i--) {
			if (!(Character.isLetter(s.charAt(i)) || Character.isDigit(s.charAt(i)))) {
				if (s.charAt(i) == '.' || s.charAt(i) == '!' || s.charAt(i) == '?') {
					endOfSentence = true;
					return endOfSentence;
				}
			}
			else {
				endOfSentence = false;
			}
		}
		return endOfSentence;
	}
	
	public static String stripPunctuation(String s) {
		StringBuilder tmp = new StringBuilder(s);
		// delete punctuation from beginning
		while (!(Character.isLetter(tmp.charAt(0)) || 
				Character.isDigit(tmp.charAt(0)))) {
			tmp.deleteCharAt(0);
			if (tmp.length() == 0) {
				return null;
			}
		}
		// delete punctuation from end
		char lastCharacter = tmp.charAt(tmp.length() - 1);
		while (!(Character.isLetter(lastCharacter) || 
				Character.isDigit(lastCharacter))) {
			tmp.deleteCharAt(tmp.length() - 1);
			lastCharacter = tmp.charAt(tmp.length() - 1);
		}
		return tmp.toString();
	}
		
}
