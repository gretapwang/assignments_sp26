import java.util.Collections;

public class LinearSearch {

  public static boolean search(CardPile cards, Card target) {
    return search(cards, target, null);
  }

  public static boolean search(CardPile cards, Card target, SortRecorder record) {
    // ***********************************************************
    // Search through the pile one card at a time.
    // Return true as soon as you find target.
    // If the whole pile is checked and target is never found,
    // return false.
    //
    // If you are recording the search visually, take one snapshot
    // per comparison so the viewer shows the search progression.
    // ***********************************************************

    if (record != null) {
      record.add(cards);
    }
    CardPile checked = new CardPile();
    while (cards.size() > 0) {
      Card curr = cards.removeFirst();
      checked.addLast(curr);
      if (record != null) {
        record.next();
        record.add(cards);
        record.add(checked);
      }
      if (curr.compareTo(target) == 0) {
        return true;
      }
    }
    return false;
  }

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
