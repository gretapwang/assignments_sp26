import java.util.Collections;
import java.util.ListIterator;

/**
 * Sorts cards using selection sort.
 * 
 * @author Greta Wang
 * @version Spring 2026
 */
public class SelectionSort {

  /**
   * Returns a sorted version of the given CardPile using selection sort.
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
   * Returns a sorted version of the given CardPile using selection sort.
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
      ListIterator<Card> unsortedIter = unsorted.listIterator();
      Card smallest = unsortedIter.next();
      unsortedIter.remove();
      while (unsortedIter.hasNext()) { // find smallest unsorted card
        Card curr = unsortedIter.next();
        if (curr.compareTo(smallest) < 0) { // pull out smallest found so far
          unsortedIter.remove();
          unsortedIter.add(smallest);
          smallest = curr;
        }
      }
      sorted.addLast(smallest);
      LinearSearch.recordNewStep(sorted, unsorted, record);
    }
    return sorted;
  }

  /**
   * Demonstrates selection sort with card display.
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
    recorder.display("Selection Sort");
  }
}
