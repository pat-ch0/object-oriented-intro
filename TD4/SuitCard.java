package td.tarot;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

final class SuitCard extends Card {

  public static final int AS = 1;
  public static final int JACK = 11;
  public static final int KNIGHT = 12;
  public static final int QUEEN = 13;
  public static final int KING = 14;


  private static final int MIN_VALUE = AS;
  private static final int MAX_VALUE = KING;

  private final Suit suit;

  enum Suit {
    SPADE,
    HEART,
    DIAMOND,
    CLUB;
  }

  SuitCard(Suit suit, int value) {
    super(value);
    this.suit = requireNonNull(suit);
  }

  @Override
  protected boolean isValid(int value) {
    return (value >= MIN_VALUE && value <= MAX_VALUE);
  }

  @Override
  public int compareTo(Card other) {
    if (!(other instanceof SuitCard)) {
      return -1 * other.compareTo(this);
    }
    if (super.compareTo(other) == 0) {
      return this.suit.compareTo(((SuitCard) other).suit);
    }
    return super.compareTo(other);
  }

  @Override
  public boolean equals(Object other) {
    return super.equals(other)
      && other instanceof SuitCard
      && ((SuitCard) other).suit.equals(this.suit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.suit);
  }

  @Override
  public boolean hasSameKind(Card other) {
    return super.hasSameKind(other) && ((SuitCard) other).suit.equals(this.suit);
  }


  private static final class SuitCardGenerator implements Iterator<Card> {
    private int currentValue = MIN_VALUE;
    private int currentSuit = 0;
    private Suit[] suits = Suit.values();

    public boolean hasNext() {
      return currentValue <= MAX_VALUE && currentSuit < suits.length;
    }

    public Card next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      Card next = new SuitCard(suits[currentSuit], currentValue);
      if (currentValue == MAX_VALUE) {
        currentValue = MIN_VALUE;
        currentSuit++;
      } else {
        currentValue++;
      }
      return next;
    }
  }

  public static Iterable<Card> generator() {
    return () -> new SuitCardGenerator();
  }
}
