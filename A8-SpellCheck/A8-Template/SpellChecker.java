import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

/**
 * Run the spell checker from the command line.
 */
public class SpellChecker {
  private static final String DEFAULT_DICTIONARY = "words.txt";
  private WordValidation validator;

  /**
   * Create a spell checker object.
   *
   * This constructor uses the default dictionary file.
   */
  public SpellChecker() {
    this(DEFAULT_DICTIONARY);
  }

  /**
   * Create a spell checker object using a particular dictionary file.
   *
   * This constructor is optional, but many students find it convenient.
   *
   * @param filename the dictionary file to use
   */
  public SpellChecker(String filename) {
    this.validator = new WordValidation(filename);
  }

  /**
   * Check one word and print the result.
   *
   * @param word the word to check
   * @param printCorrectWords true to print result even when words are spelled correctly
   */
  public void checkWord(String word, boolean printCorrectWords) {
    if (this.validator.containsWord(word)) {
      if (printCorrectWords) {
        System.out.println("'" + word + "' is spelled correctly.");
      }
    } else {
      System.out.println("Not found: " + word);
      System.out.println("  Suggestions: " + checkSpelling(word).get(word));
    }
  }

  /**
   * Check the spelling of one word and return any suggestions.
   *
   * @param query the word to check
   * @return a map from the misspelled query to its suggestions
   */
  public Map<String, HashSet<String>> checkSpelling(String query) {
    HashSet<String> suggestions = new HashSet<>(this.validator.nearMisses(query));
    Map<String, HashSet<String>> result = new HashMap<>();

    if (!this.validator.containsWord(query)) {
      result.put(query, suggestions);
    }

    return result;
  }

  /**
   * Reads all words from System.in and returns them as a set with no duplicates.
   * 
   * @return Words read from System.in
   */
  public static HashSet<String> readWords() {
    Scanner s = new Scanner(System.in);
    HashSet<String> words = new HashSet<String>();
    while (s.hasNext()) {
      words.add(s.next().toLowerCase().replaceAll("[^a-z]", "")); // make words identical to avoid duplicates
    }
    s.close();
    return words;
  }

  /**
   * Launch the spell checker in argument mode or standard-input mode.
   *
   * A common approach is to make argument mode work first, then extend the
   * program so it can read many words from {@code System.in}.
   *
   * @param args command-line arguments to spell-check
   */
  public static void main(String[] args) {
    SpellChecker checker = new SpellChecker();
    if (args.length > 0) {
      for (String word : args) {
        checker.checkWord(word, true);
      }
    } else {
      for (String word : readWords()) {
        checker.checkWord(word, false);
      }
    }
  }
}
