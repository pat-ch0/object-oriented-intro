package poo.td.cars;

public final class CarA extends Car {

  public CarA(int doorsNumber) {
    super(doorsNumber);
  }

  @Override
  public int numberOfWheels() {
    return 4;
  }

}
