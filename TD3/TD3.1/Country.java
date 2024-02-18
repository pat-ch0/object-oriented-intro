
package td.villes;

import java.util.Set;
import java.util.HashSet;
import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Enumeration;

import static java.util.Objects.requireNonNull;

public class Country extends AbstractCollection<City> {

  private final Set<City> cities = new HashSet<>();
  private final String name;
  private Capital capital;

  public Country(String name, Capital capital) {
    this.name = requireNonNull(name);
    this.setCapital(capital);
  }

  public String name() {
    return this.name;
  }

  public Capital getCapital() {
    return this.capital;
  }

  public boolean setCapital(Capital capital) {
    if (this.capital == capital) {
      return false;
    }
    this.capital = requireNonNull(capital);
    return true;
  }

  @Override
  public boolean add(City city) {
    requireNonNull(city);
    if (city instanceof Capital) {
      return this.setCapital((Capital) city);
    }
    return this.cities.add(city);
  }

  private static final class CountryIterator implements Iterator<City> {
    private boolean started = false;
    private Iterator<City> cities;
    private Capital capital;

    CountryIterator(Capital c, Iterator<City> it) {
      this.capital = c;
      this.cities = it;
    }

    public boolean hasNext() {
      if (!started) {
        return true;
      }
      return cities.hasNext();
    }

    public City next() {
      if (!started) {
        started = true;
        return this.capital;
      }
      return cities.next();
    }

    public void remove() {
      if (!started) {
        throw new IllegalStateException("Can't remove the country's capital");
      }
      this.cities.remove();
    }
  }

  @Override
  public Iterator<City> iterator() {
    return new CountryIterator(this.capital, this.cities.iterator());
  }

  @Override
  public int size() {
    return this.cities.size() + 1;
  }

  public boolean addCities(Enumeration<City> cities) {
    boolean result = false;
    while (cities.hasMoreElements()) {
      result |= this.add(cities.nextElement());
    }
    return result;
  }
}
