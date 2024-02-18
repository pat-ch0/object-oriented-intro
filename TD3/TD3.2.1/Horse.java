package td.heritage;

import static java.util.Objects.requireNonNull;

class Horse extends Animal {
  Horse(String name) {
    super(name);
  }

  @Override
  public String makeNoise() {
    return "Hiiiiiii...";
  }
}


