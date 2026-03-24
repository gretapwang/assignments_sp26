import java.util.Collections;

/**
 * Searches cards using linear search.
 * 
 * @author Greta Wang
 * @version Spring 2026
 */
public class LinearSearch {

  /**
   * Returns whether the given CardPile contains the target card.
   * Does not record steps.
   * 
   * @param cards CardPile to search
   * @param target Card to search for
   * @return True if cards contains target, false otherwise
   */
  public static boolean search(CardPile cards, Card target) {
    return search(cards, target, null);
  }

  /**
   * Returns whether the given CardPile contains the target card.
   * Records steps in the given SortRecorder.
   * 
   * @param cards CardPile to search
   * @param target Card to search for
   * @param record Recorder
   * @return True if cards contains target, false otherwise
   */
  public static boolean search(CardPile cards, Card target, SortRecorder record) {
    recordCurrStep(cards, record);
    for (Card card : cards) { // for each card, return true if it matches target
      recordNewStep(cards, record);
      if (card.compareTo(target) == 0) {
        return true;
      }
    }
    return false;
  }

  /**
   * Moves the given SortRecorder to the next step and adds the CardPile,
   * unless the recorder is null.
   * 
   * @param cards CardPile to record
   * @param record Recorder
   */
  public static void recordNewStep(CardPile cards, SortRecorder record) {
    if (record != null) {
      record.next();
      record.add(cards);
    }
  }

  /**
   * Moves the given SortRecorder to the next step and adds the two CardPiles,
   * unless the recorder is null.
   * 
   * @param pile1 One pile to record
   * @param pile2 Other pile to record
   * @param record Recorder
   */
  public static void recordNewStep(CardPile pile1, CardPile pile2, SortRecorder record) {
    recordNewStep(pile1, record);
    recordCurrStep(pile2, record);
  }

  /**
   * Adds the given CardPile to the SortRecorder, unless the recorder is null.
   * 
   * @param cards CardPile to record
   * @param record Recorder
   */
  public static void recordCurrStep(CardPile cards, SortRecorder record) {
    if (record != null) {
      record.add(cards);
    }
  }

  /**
   * Demonstrates linear search with card display.
   * 
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    SortRecorder recorder = new SortRecorder();
    Card.loadImages(recorder);
    CardPile cards = new CardPile(Card.newDeck(true), 2, 2);
    Card target = cards.getFirst();
    // to make target not be in the the pile:
    // cards.removeFirst();
    Collections.shuffle(cards);
    if (search(cards, target, recorder)) {
      System.out.println(target + " found!");
    } else {
      System.out.println(target + " not found.");
    }
    recorder.display("Linear Search for " + target);
  }
}
