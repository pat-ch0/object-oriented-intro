package td.heritage;

import static java.util.Objects.requireNonNull;

class Cat extends Animal {
  Cat(String name) {
    super(name);
  }

  @Override
  public String makeNoise() {
    return "Meaw...";
  }
}
