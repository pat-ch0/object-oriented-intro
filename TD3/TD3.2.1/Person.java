package td.heritage;

import java.util.Set;
import java.util.HashSet;
import java.util.Optional;
import static java.util.Objects.requireNonNull;

final class Person {

  private final String name;
  private final Set<Animal> animals = new HashSet<>();
  private Vehicle vehicle;

  Person(String name) {
    this.name = requireNonNull(name);
  }

  public boolean adopt(Animal animal) {
    return this.animals.add(animal);
  }

  public Optional<Vehicle> getVehicle() {
    return Optional.ofNullable(this.vehicle);
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = requireNonNull(vehicle);
  }

  public void removeVehicle() {
    this.vehicle = null;
  }

  public boolean hasVehicle() {
    return this.vehicle != null;
  }
}
