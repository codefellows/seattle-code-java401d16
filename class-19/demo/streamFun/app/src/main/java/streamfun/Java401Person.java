package streamfun;

public class Java401Person implements Comparable<Java401Person> {
  String name;
  boolean isTired;
  int numPets;
  int numOfCoffees;
  public String[] petNames;
  boolean isCurrentlyConfused;
  String hobby;
  boolean isSmart;

  public Java401Person(String name, boolean isTired, int numPets, int numOfCoffees, String[] petNames,
                       boolean isCurrentlyConfused, String hobby, boolean isSmart) {
    this.name = name;
    this.isTired = isTired;
    this.numPets = numPets;
    this.numOfCoffees = numOfCoffees;
    this.petNames = petNames;
    this.isCurrentlyConfused = isCurrentlyConfused;
    this.hobby = hobby;
    this.isSmart = isSmart;
  }

  @Override
  public int compareTo(Java401Person o) {
    return this.name.compareTo(o.name);
  }

  @Override
  public String toString() {
    return name;
  }

  public String getName() {
    return name;
  }
}
