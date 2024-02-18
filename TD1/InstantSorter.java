import java.util.List;

public final class InstantSorter {
  public static Instant getNext(Instant t, Iterable<Instant> instants) {
    Instant next = null;
    for (Instant current : instants) {
      if (current == null) { continue; }
      if ((t == null || (current.compareTo(t) > 0)) 
          && (next == null || (current.compareTo(next)) < 0)) {
        next = current;
      }
    }
    return next;
  }

  public static void sort(List<Instant> instants) {
    Instant current = null;
    for (int i = 0; i < instants.size() - 1; i++) {
      current = getNext(current, instants);
      instants.remove(current);
      instants.add(i, current);
    }
  }
}
