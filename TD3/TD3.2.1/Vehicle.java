
package td.heritage;

import java.util.Set;
import java.util.HashSet;

class FullVehiculeException extends IllegalStateException { }
class EmptyVehiculeException extends IllegalStateException { }


abstract class Vehicle {

  private final Set<Person> occupants = new HashSet<>();

  public abstract int maxSize();

  public boolean enter(Person p) {
    if (this.occupants.contains(p)) {
      return false;
    }
    if (this.isFull()) {
      throw new FullVehiculeException();
    }
    return this.occupants.add(p);
  }

  public boolean leave(Person p) {
    return this.occupants.remove(p);
  }

  public boolean isFull() {
    return this.occupants.size() >= this.maxSize();
  }

  public boolean isEmpty() {
    return this.occupants.isEmpty();
  }

  public void move() {
    if (this.isEmpty()) {
      throw new EmptyVehiculeException();
    }
    System.out.println("The vehicle moves...");
  }
}
