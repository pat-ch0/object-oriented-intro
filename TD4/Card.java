package td.tarot;

import java.util.Objects;

class IllegalCardValueException extends IllegalArgumentException {
  IllegalCardValueException() {
    super();
  }
  IllegalCardValueException(String message) {
    super(message);
  }
}

abstract class Card implements Comparable<Card> {

  private final int value;

  Card(int value) {
    this.value = validateValue(value);
  }

  /**
   * Validates the card value.
   *
   * @throws IllegalCardValueException if the value is not valid.
   * @return the value itself if it's valid
   */
  private int validateValue(int value) {
    if (!this.isValid(value)) {
      throw new IllegalCardValueException(String.format(
            "Can't create a %s with %d",
            this.getClass().getName(), value));
    }
    return value;
  }

  /**
   * Checks if the value is valid.
   */
  protected abstract boolean isValid(int value);

  @Override
  public String toString() {
    return String.format("%s{value=%d}", this.getClass().getSimpleName(), this.value);
  }

  @Override
  public int compareTo(Card other) {
    return Integer.compare(this.value, other.value);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) { return false; }
    if (o == this) { return true; }
    if (!(o instanceof Card)) { return false; }
    return this.compareTo((Card) o) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.value);
  }

  public boolean hasSameKind(Card other) {
    if (other == null) { return false; }
    if (other == this) { return true; }
    return this.getClass().equals(other.getClass());
  }
}
