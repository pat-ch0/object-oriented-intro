
package td.tarot;

@FunctionalInterface
public interface CardsFactory {
  Iterable<Card> get();
}
