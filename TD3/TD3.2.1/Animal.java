
package td.heritage;

import static java.util.Objects.requireNonNull;

abstract class Animal {
  private final String name;

  Animal(String name) {
    this.name = requireNonNull(name);
  }

  public String name() {
    return this.name;
  }

  public abstract String makeNoise();

  public void run() {
    System.out.printf("%s is running...%n", this.name());
  }
}
