package td.heritage;

import static java.util.Objects.requireNonNull;

class Dog extends Animal {
  Dog(String name) {
    super(name);
  }

  @Override
  public String makeNoise() {
    return "Woofff...";
  }
}

