package td.tarot;

import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.ArrayList;

class Trump extends Card {
  private static final int MIN_VALUE = 1;
  private static final int MAX_VALUE = 21;

  public static final Trump FOOL = new Trump(0) {
    @Override
    protected boolean isValid(int value) {
      return value == 0;
    }

    @Override
    public int compareTo(Card other) {
      return -1;
    }

    @Override
    public String toString() {
      return "Fool";
    }
  };

  private Trump(int value) {
    super(value);
  }

  @Override
  protected boolean isValid(int value) {
    return (value >= MIN_VALUE && value <= MAX_VALUE);
  }

  private static final class TrumpGenerator implements Iterator<Card> {
    private int current = 0;

    public boolean hasNext() {
      return current <= MAX_VALUE;
    }

    public Card next() {
      Trump next;
      try {
        next = Trump.of(this.current);
        this.current++;
      } catch (IllegalCardValueException e) {
        throw new NoSuchElementException(e.toString());
      }
      return next;
    }
  }

  public static Iterable<Card> generator() {
    return () -> new TrumpGenerator();
  }

  public static Trump of(int value) {
    return value == 0 ? FOOL : new Trump(value);
  }

  @Override
  public int compareTo(Card other) {
    if (!(other instanceof Trump)) { return 1; }
    return super.compareTo(other);
  }

}
