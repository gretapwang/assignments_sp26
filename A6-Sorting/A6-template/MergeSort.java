import java.util.ArrayDeque;
import java.util.Collections;

/**
 * Sorts cards using merge sort.
 * 
 * @author Greta Wang
 * @version Spring 2026
 */
public class MergeSort {
  
  /**
   * Returns a sorted version of the given CardPile using merge sort.
   * Original list is unchanged.
   * Does not record steps.
   * 
   * @param unsorted Cards to sort
   * @return Sorted cards
   */
  public static CardPile sort(CardPile unsorted) {
    return sort(unsorted, null);
  }

  /**
   * Returns a sorted version of the given CardPile using merge sort.
   * Original list is unchanged.
   * Records steps in the given SortRecorder.
   * 
   * @param unsorted Cards to sort
   * @param record Recorder
   * @return Sorted cards
   */
  public static CardPile sort(CardPile unsorted, SortRecorder record) {
    LinearSearch.recordCurrStep(unsorted, record);
    if (unsorted.isEmpty()) { // return original pile if empty
      return unsorted;
    }
    ArrayDeque<CardPile> queue = new ArrayDeque<CardPile>();
    for (Card card : unsorted) { // add each individual card to queue
      CardPile singleton = new CardPile();
      singleton.add(card);
      queue.add(singleton);
    }
    recordNewStep(queue, record);
    while (queue.size() > 1) { // merge piles until only one left
      queue.add(merge(queue.remove(), queue.remove()));
      recordNewStep(queue, record);
    }
    return queue.remove();
  }

  /**
   * Given two sorted CardPiles, returns a sorted CardPile of the two combined.
   * Original lists are emptied.
   * 
   * @param pile1 One list to merge
   * @param pile2 Other list to merge
   * @return Merged list
   */
  private static CardPile merge(CardPile pile1, CardPile pile2) {
    CardPile merged = new CardPile();
    while (!pile1.isEmpty() && !pile2.isEmpty()) { // compare first elements, move smallest one to merged
      if (pile1.getFirst().compareTo(pile2.getFirst()) < 0) {
        merged.addLast(pile1.removeFirst());
      } else {
        merged.addLast(pile2.removeFirst());
      }
    }
    if (pile1.isEmpty()) { // append nonempty list to merged
      merged.append(pile2);
    } else {
      merged.append(pile1);
    }
    return merged;
  }

  /**
   * Moves the given SortRecorder to the next step and adds all CardPiles
   * in the queue, unless the recorder is null.
   * 
   * @param queue Queue of CardPiles to record
   * @param record Recorder
   */
  public static void recordNewStep(ArrayDeque<CardPile> queue, SortRecorder record) {
    if (record != null) {
      record.next();
      for (CardPile pile : queue) {
        record.add(pile);
      }
    }
  }

  /**
   * Demonstrates merge sort with card display.
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
    recorder.display("Merge Sort");
  }
}
