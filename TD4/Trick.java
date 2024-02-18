package td.tarot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

final class Trick {
  private final ArrayList<Card> cards = new ArrayList<>(4);

  public boolean add(Card c) {
    return this.cards.add(c);
  }

  public static Trick of(Card... cards) {
    Trick t = new Trick();
    for (Card c : cards) {
      t.add(c);
    }
    return t;
  }

  public Card winner() {
    if (this.cards.isEmpty()) {
      throw new IllegalStateException("No cards");
    }
    if (this.cards.size() == 1) {
      return this.cards.get(0);
    }
    return Collections.max(this.cards, new TrickComparator(
        this.cards.get(0) == Trump.FOOL
        ? this.cards.get(1)
        : this.cards.get(0)));
  }

  public int winnerIndex() {
    return this.cards.indexOf(this.winner());
  }

  public static final class TrickComparator implements Comparator<Card> {

    private final Card leading;

    TrickComparator(Card leading) {
      this.leading = leading;
    }

    private static boolean isTrump(Card c) {
      return c instanceof Trump;
    }

    private boolean isLeading(Card c) {
      return this.leading.hasSameKind(c);
    }

    @Override
    public int compare(Card c1, Card c2) {
      if (isTrump(c1) || isTrump(c2) || c1.hasSameKind(c2)) {
        return c1.compareTo(c2);
      }
      if (isLeading(c1)) { return 1; }
      if (isLeading(c2)) { return -1; }
      return c1.compareTo(c2);
    }
  }
}
