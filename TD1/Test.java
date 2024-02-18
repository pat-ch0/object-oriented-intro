
import java.util.List;
import java.util.LinkedList;
import static java.util.Arrays.asList;

public class Test {
  public static void main(String[] args) {
    Instant[] instants = {
      new Instant(0, 10, 5),
      new Instant(0, 0, 1),
      null,
      new Instant(2, 0, 10),
      new Instant(0, 5, 0),
    };
  
    List<Instant> lst = new LinkedList<>(asList(instants));
    InstantSorter.sort(lst);
    System.out.println(lst);

  }
}
