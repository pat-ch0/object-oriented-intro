package td.heritage;

class FullVehicleException extends IllegalStateException { }
class EmptyVehicleException extends IllegalStateException { }

interface Vehicle {
  boolean enter(Person p);
  boolean leave(Person p);
  boolean isFull();
  boolean isEmpty();
  void move();
  int maxSize();
}
