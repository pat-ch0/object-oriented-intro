package td.heritage;

class Horse extends Animal implements Vehicle {

  Vehicle v = new AbstractVehicle() {
    @Override
    public int maxSize() {
      return 2;
    }

    @Override
    protected void checkEmptiness() {
      // I don't care, since a horse can move by itself...
    }
  };

  Horse(String name) {
    super(name);
  }

  @Override
  public String makeNoise() {
    return "Hiiiiiii...";
  }

  @Override
  public int maxSize() {
    return this.v.maxSize();
  }

  @Override
  public void move() {
    this.run();
    this.v.move();
  }

  @Override
  public boolean enter(Person p) {
    return this.v.enter(p);
  }

  @Override
  public boolean leave(Person p) {
    return this.v.leave(p);
  }

  @Override
  public boolean isFull() {
    return this.v.isFull();
  }

  @Override
  public boolean isEmpty() {
    return this.v.isEmpty();
  }

}
