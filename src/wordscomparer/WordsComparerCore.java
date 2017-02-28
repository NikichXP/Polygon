package wordscomparer;

import java.io.*;
import java.util.*;

public class WordsComparerCore {

	public static void main(String[] args) throws FileNotFoundException {
		String text1[] = new Scanner(new File("C:/lorem1.txt")).useDelimiter("\\A").next().split("[.,? :/]");
		String text2[] = new Scanner(new File("C:/lorem2.txt")).useDelimiter("\\A").next().split("[.,? :/]");
		HashSet<String> words1 = new HashSet<>();
		HashSet<String> words2 = new HashSet<>();
		for (String s : text1) {
			words1.add(s.toLowerCase());
		}
		for (String s : text2) {
			words2.add(s.toLowerCase());
		}
		words2.remove("");
		words1.retainAll(words2);
		System.out.println("Similar words: " + words1.size());
		System.out.println("Coverage: ");
		System.out.println("Text 1: " + (((double)words1.size()/text1.length)*100) + "%");
		System.out.println("Text 2: " + (((double)words1.size()/text2.length)*100) + "%");
	}

}
