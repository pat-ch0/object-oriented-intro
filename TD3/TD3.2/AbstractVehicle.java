package td.heritage;

import java.util.Set;
import java.util.HashSet;

abstract class AbstractVehicle implements Vehicle {

  private final Set<Person> occupants = new HashSet<>();

  public abstract int maxSize();

  public boolean enter(Person p) {
    if (this.occupants.contains(p)) {
      return false;
    }
    if (this.isFull()) {
      throw new FullVehicleException();
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

  protected void checkEmptiness() {
    if (this.isEmpty()) {
      throw new EmptyVehicleException();
    }
  }

  public void move() {
    checkEmptiness();
    System.out.printf("The %s moves...%n", this.getClass().getName());
  }
}

