import java.util.Collections;
import java.util.ListIterator;

/**
 * Sorts cards using insertion sort.
 * 
 * @author Greta Wang
 * @version Spring 2026
 */
public class InsertionSort {

  /**
   * Returns a sorted version of the given CardPile using insertion sort.
   * Original list is emptied.
   * Does not record steps.
   * 
   * @param unsorted Cards to sort
   * @return Sorted cards
   */
  public static CardPile sort(CardPile unsorted) {
    return sort(unsorted, null);
  }
  
  /**
   * Returns a sorted version of the given CardPile using insertion sort.
   * Original list is emptied.
   * Records steps in the given SortRecorder.
   * 
   * @param unsorted Cards to sort
   * @param record Recorder
   * @return Sorted cards
   */
  public static CardPile sort(CardPile unsorted, SortRecorder record) {
    LinearSearch.recordCurrStep(unsorted, record);
    CardPile sorted = new CardPile();
    while (!unsorted.isEmpty()) {
      Card curr = unsorted.removeFirst();
      ListIterator<Card> sortedIter = sorted.listIterator();
      while (sortedIter.hasNext()) { // use iterator to find the right spot for curr
        if (sortedIter.next().compareTo(curr) >= 0) {
          sortedIter.previous();
          break;
        }
      }
      sortedIter.add(curr);
      LinearSearch.recordNewStep(unsorted, sorted, record);
    }
    return sorted;
  }

  /**
   * Demonstrates insertion sort with card display.
   * 
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    SortRecorder recorder = new SortRecorder();
    Card.loadImages(recorder);
    CardPile cards = new CardPile(Card.newDeck(true), 2, 2);
    Collections.shuffle(cards);
    cards = sort(cards, recorder);
    System.out.println(cards);
    recorder.display("Insertion Sort");
  }
}
