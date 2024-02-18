package td.tarot;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class Test {

  private int tests = 0;
  private int failed = 0;

  private void assertEquals(Object a, Object b) {
    assertThat(String.format("%s == %s ?\t", a, b),
        () -> a.equals(b));
  }

  private void assertNotEquals(Object a, Object b) {
    assertThat(String.format("%s != %s ?\t", a, b),
        () -> !a.equals(b));
  }

  private void assertThat(String msg, BooleanSupplier test) {
    System.out.print("  " + msg + '\t');
    if (test.getAsBoolean()) {
      System.out.println("OK");
    } else {
      System.out.println("FAILED");
      failed++;
    }
    tests++;
  }

  private void fails() {
    tests++;
    failed++;
  }

  private void assertRaises(String msg, Supplier<Object> test, Class<?> exception) {
    System.out.print("  " + msg + '\t');
    try {
      test.get();
      fails();
    } catch (Exception e) {
      assertEquals(e.getClass(), exception);
    }
  }


  public void report() {
    System.out.println("-------------");
    System.out.printf("Tests: %d, failures: %d%n", this.tests, this.failed);
  }

  private void testEqualsSuit() {
    System.out.println("testEqualsSuit");
    Card c = new SuitCard(SuitCard.Suit.SPADE, SuitCard.KING);
    Card c2 = new SuitCard(SuitCard.Suit.SPADE, SuitCard.KING);
    Card c3 = new SuitCard(SuitCard.Suit.HEART, 10);
    Card c4 = new SuitCard(SuitCard.Suit.SPADE, SuitCard.QUEEN);
    Card c5 = new SuitCard(SuitCard.Suit.HEART, SuitCard.KING);

    assertEquals(c, c2);
    assertNotEquals(c, c3);
    assertNotEquals(c, c4);
    assertNotEquals(c, c5);
    assertEquals(c.hashCode(), c2.hashCode());

    assertThat("using compareTo", () -> c.compareTo(c2) == 0);
    assertThat("using compareTo", () -> c.compareTo(c3) != 0);
    assertThat("using compareTo", () -> c.compareTo(c4) != 0);
    assertThat("using compareTo", () -> c.compareTo(c5) != 0);
  }

  private void testEqualsTrump() {
    System.out.println("testEqualsTrump");
    Card c = Trump.of(10);
    Card ca = Trump.of(10);
    Card c2 = Trump.of(5);
    Card c3 = new SuitCard(SuitCard.Suit.HEART, 8);

    assertEquals(c, ca);
    assertNotEquals(c, c2);
    assertNotEquals(c, c3);
    assertEquals(c.hashCode(), ca.hashCode());

    assertThat("using compareTo", () -> c.compareTo(ca) == 0);
    assertThat("using compareTo", () -> c.compareTo(c2) != 0);
    assertThat("using compareTo", () -> c.compareTo(c3) != 0);
  }

  private void testCompareSuit() {
    System.out.println("testCompareSuit");
    Card c = new SuitCard(SuitCard.Suit.SPADE, SuitCard.KING);
    Card c2 = new SuitCard(SuitCard.Suit.SPADE, SuitCard.KING);
    Card c3 = new SuitCard(SuitCard.Suit.HEART, 10);
    Card c4 = new SuitCard(SuitCard.Suit.SPADE, SuitCard.QUEEN);
    Card c5 = new SuitCard(SuitCard.Suit.HEART, SuitCard.KING);

    assertThat("using compareTo =", () -> c.compareTo(c2) == 0);
    assertThat("using compareTo =", () -> c2.compareTo(c) == 0);
    assertThat("using compareTo >", () -> c.compareTo(c3) > 0);
    assertThat("using compareTo <", () -> c3.compareTo(c) < 0);
    assertThat("using compareTo >", () -> c.compareTo(c4) > 0);
    assertThat("using compareTo <", () -> c.compareTo(c5) < 0);
  }

  private void testCompareTrump() {
    System.out.println("testCompareTrump");
    Card c = Trump.of(10);
    Card ca = Trump.of(10);
    Card c2 = Trump.of(5);
    Card c3 = new SuitCard(SuitCard.Suit.HEART, 10);

    assertThat("using compareTo =", () -> c.compareTo(ca) == 0);
    assertThat("using compareTo =", () -> ca.compareTo(c) == 0);
    assertThat("using compareTo >", () -> c.compareTo(c2) > 0);
    assertThat("using compareTo <", () -> c2.compareTo(c) < 0);
    assertThat("using compareTo >", () -> c.compareTo(c3) > 0);
    assertThat("using compareTo <", () -> c3.compareTo(c) < 0);
    assertThat("using compareTo >", () -> c2.compareTo(c3) > 0);
    assertThat("using compareTo <", () -> c3.compareTo(c2) < 0);
  }

  public void testEmptyTrick() {
    assertRaises("empty", () -> Trick.of().winner(), IllegalStateException.class);
  }

  public void testWinner() {
    Card c1 = new SuitCard(SuitCard.Suit.HEART, 8);
    Card c2 = new SuitCard(SuitCard.Suit.SPADE, SuitCard.KING);
    Card c3 = Trump.of(5);
    Card c4 = new SuitCard(SuitCard.Suit.HEART, 10);
    Card c5 = new SuitCard(SuitCard.Suit.DIAMOND, 11);

    assertEquals(Trick.of(c1, c2, c3, c4).winner(), c3);
    assertEquals(Trick.of(c1, c2, c5, c4).winner(), c4);
  }

  public void testWinnerWithFool() {
    Card c1 = new SuitCard(SuitCard.Suit.HEART, 8);
    Card c2 = new SuitCard(SuitCard.Suit.SPADE, SuitCard.KING);
    Card c3 = Trump.FOOL;
    Card c4 = new SuitCard(SuitCard.Suit.HEART, 10);
    Card c5 = new SuitCard(SuitCard.Suit.HEART, 7);

    assertEquals(Trick.of(c1, c2, c3, c4).winner(), c4);
    assertEquals(Trick.of(c3, c1, c2, c5).winner(), c1);
  }

  public void testWinnerIndex() {
    Card c1 = new SuitCard(SuitCard.Suit.HEART, 8);
    Card c2 = new SuitCard(SuitCard.Suit.SPADE, SuitCard.KING);
    Card c3 = Trump.of(5);
    Card c4 = new SuitCard(SuitCard.Suit.HEART, 10);
    Card c5 = new SuitCard(SuitCard.Suit.DIAMOND, 11);

    assertEquals(Trick.of(c1, c2, c3, c4).winnerIndex(), 2);
    assertEquals(Trick.of(c1, c2, c5, c4).winnerIndex(), 3);
    assertEquals(Trick.of(c1).winnerIndex(), 0);
    assertEquals(Trick.of(Trump.FOOL, c2).winnerIndex(), 1);
  }


  public static void main(String[] args) {

    Test t = new Test();

    t.testEqualsSuit();
    t.testCompareSuit();
    t.testEqualsTrump();
    t.testCompareTrump();
    t.testEmptyTrick();
    t.testWinner();
    t.testWinnerWithFool();
    t.testWinnerIndex();


    t.report();

    // System.out.println(c);
    //
    //
    // Deck d = Deck.from(Trump::generator, SuitCard::generator);
    // System.out.println(d);
    // System.out.println(d.size());




  }
}
