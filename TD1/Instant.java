
public class Instant implements Comparable<Instant> {

  public static final int SECONDS_IN_MINUTE = 60;
  public static final int MINUTES_IN_HOUR = 60;
  public static final int SECONDS_IN_HOUR = MINUTES_IN_HOUR * SECONDS_IN_MINUTE;
  public static final int MAX_HOURS = 24;
  public static final int MAX_SECONDS = secondsFrom(MAX_HOURS, 0, 0);

  private final int totalSeconds;

  private static int secondsFrom(int hours, int minutes, int seconds) {
   return (hours * SECONDS_IN_HOUR) + (minutes * SECONDS_IN_MINUTE) + seconds; 
  }

  public Instant(int h, int m, int s) {
    this(secondsFrom(h, m, s));
  }

  public Instant(int total) {
    this.totalSeconds = validateTotalSeconds(total);
  }

  private int validateTotalSeconds(int s) {
    if (s < 0 || s >= MAX_SECONDS) {
      throw new IllegalArgumentException(String.format(
            "Total seconds must be between 0 and %d and got %d", MAX_SECONDS, s));
    }
    return s;
  }

  public int hours() {
    return this.totalSeconds / SECONDS_IN_HOUR;
  }

  public int minutes() {
    return (this.totalSeconds / SECONDS_IN_MINUTE) % MINUTES_IN_HOUR;
  }

  public int seconds() {
    return this.totalSeconds % SECONDS_IN_MINUTE;
  }

  public int totalSeconds() {
    return this.totalSeconds;
  }

  public Instant minus(Instant other) {
    if (other == null) {
      return this;
    }
    return new Instant(Math.abs(this.totalSeconds() - other.totalSeconds()));
  }

  @Override
  public int compareTo(Instant other) {
    if (other.getClass().equals(this.getClass())) {
      return Integer.compare(this.totalSeconds(), other.totalSeconds());
    }
    return -1 * other.compareTo(this);
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) { return true; }
    if (o == null) { return false; }
    if (!(o instanceof Instant)) { return false; }
    return this.compareTo((Instant) o) == 0;
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(this.totalSeconds());
  }

  @Override
  public String toString() {
    return String.format("%dh%02dm%02ds", this.hours(), this.minutes(), this.seconds());
  }

}
