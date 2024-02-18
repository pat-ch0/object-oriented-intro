package td.tarot;

import java.util.LinkedList;
import java.util.Collections;
import java.util.Iterator;
import java.util.AbstractCollection;

public final class Deck extends AbstractCollection<Card> {

  private final LinkedList<Card> cards = new LinkedList<>();

  private Deck() {
    // private constructor, use the static factory method
  }

  private void init(CardsFactory... factories) {
    for (CardsFactory factory : factories) {
      for (Card card : factory.get()) {
        this.cards.add(card);
      }
    }
  }

  public int size() {
    return this.cards.size();
  }

  public boolean isEmpty() {
    return this.cards.isEmpty();
  }

  public void shuffle() {
    Collections.shuffle(this.cards);
  }

  public Card draw() {
    return this.cards.pop();
  }

  public boolean remove(Card c) {
    return this.cards.remove(c);
  }

  public Iterator<Card> iterator() {
    return this.cards.iterator();
  }

  public static Deck from(CardsFactory... factories) {
    Deck d = new Deck();
    d.init(factories);
    d.shuffle();
    return d;
  }
}
