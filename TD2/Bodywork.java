
package poo.td.cars;

public class Bodywork {
  private static final int MAX_DOORS = 4;
  private static final int MIN_DOORS = 2;

  private final Door[] doors;

  public Bodywork(int doorsNumber) {
    if (doorsNumber < MIN_DOORS || doorsNumber > MAX_DOORS) {
      throw new IllegalArgumentException("Invalid number of doors: " + doorsNumber);
    }
    this.doors = new Door[doorsNumber];
    for (int i = 0; i < doorsNumber; i++) {
      this.doors[i] = new Door();
    }
  }

  public int numberOfDoors() {
    return this.doors.length;
  }

}
