package poo.td.cars;

public final class CarB extends Car {

  public CarB(int doorsNumber) {
    super(doorsNumber);
  }

  @Override
  public int numberOfWheels() {
    return 6;
  }

}
