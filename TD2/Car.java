package poo.td.cars;

import java.util.Arrays;

public abstract class Car {
  private final Bodywork bodywork;
  private Wheel[] wheels;

  Car(int doorsNumber) {
    this.bodywork = new Bodywork(doorsNumber);
    initWheels();
  }

  public int numberOfDoors() {
    return this.bodywork.numberOfDoors();
  }

  public Wheel[] getWheels() {
    return Arrays.copyOf(this.wheels, this.wheels.length);
  }

  public abstract int numberOfWheels();

  @Override
  public String toString() {
    return String.format("%s{doors=%d, wheels=%s}",
        this.getClass().getName(),
        this.numberOfDoors(),
        Arrays.toString(this.wheels));
  }

  protected void initWheels() {
    this.wheels = new Wheel[this.numberOfWheels()];
    for (int i = 0; i < this.wheels.length; i++) {
      this.wheels[i] = new Wheel();
    }
  }

}
