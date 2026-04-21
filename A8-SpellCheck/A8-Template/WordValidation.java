import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Store valid words and generate suggestions for misspelled words.
 */
public class WordValidation implements SpellingOperations {
  private HashSetDictionary dictionary;

  /**
   * Create a validator from a dictionary file.
   *
   * @param filename the file containing valid words
   */
  public WordValidation(String filename) {
    Scanner file = null;
    try {
      file = new Scanner(new File(filename));
    } catch (FileNotFoundException e) {
      System.err.println("Cannot locate file.");
      System.exit(-1);
    }
    this.dictionary = new HashSetDictionary();
    while (file.hasNextLine()) {
      this.dictionary.add(file.nextLine().strip().toLowerCase().replaceAll("[^a-z]", ""));
    }
    file.close();
  }

  /**
   * Check whether the dictionary contains a word.
   *
   * @param query the word to check
   * @return true if the word is in the dictionary
   */
  public boolean containsWord(String query) {
    return this.dictionary.contains(query.strip().toLowerCase().replaceAll("[^a-z]", ""));
  }

  /**
   * Generate valid near misses for a query word.
   *
   * @param query the word to check
   * @return a set of valid suggestions that are one edit away
   */
  public Set<String> nearMisses(String query) {
    query = query.strip().toLowerCase().replaceAll("[^a-z]", "");
    HashSet<String> suggestions = new HashSet<String>();
    for (int i = 0; i < query.length(); i++) {
      String firstChunk = query.substring(0, i);
      String lastChunk = query.substring(i);
      String endOfLastChunk = lastChunk.substring(1);
      this.trySuggestion(suggestions, firstChunk + endOfLastChunk); // deletions
      for (char letter = 'a'; letter <= 'z'; letter++) {
        this.trySuggestion(suggestions, firstChunk + letter + lastChunk); // insertions
        this.trySuggestion(suggestions, firstChunk + letter + endOfLastChunk); // substitutions
      }
      if (!firstChunk.isEmpty()) {
        this.trySuggestion(suggestions, firstChunk.substring(0, firstChunk.length() - 1) + lastChunk.charAt(0)
          + firstChunk.charAt(firstChunk.length() - 1) + endOfLastChunk); // transpositions
      }
      if (this.containsWord(firstChunk) && this.containsWord(lastChunk)) {
        suggestions.add(firstChunk + " " + lastChunk); // splits
      }
    }
    for (char letter = 'a'; letter <= 'z'; letter++) {
      this.trySuggestion(suggestions, query + letter); // insertions at the end
    }
    return suggestions;
  }

  /**
   * Adds the given word to the set of suggestions if it is valid 
   * and not already in the set.
   * 
   * @param suggestions Set of valid words
   * @param word Word to check and add
   */
  private void trySuggestion(Set<String> suggestions, String word) {
    if (this.containsWord(word)) {
      suggestions.add(word);
    }
  }
}
