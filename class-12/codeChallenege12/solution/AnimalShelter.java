import java.util.HashSet;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AnimalShelter extends ConcurrentLinkedQueue {
  public void enqueue(Animal newAnimal) {
    this.add(newAnimal);
  }

  public Animal dequeue(String pref) {
    // if the pref is not "cat" or "dog", return null
    if (!pref.equals("cat") && !pref.equals("dog")) {
      return null;
    }

    // create a HashSet to track animals we've already seen
    // !!check each Animal the same way (start of loop)
    // look at the animal at the front of the queue
    // if it is the preferred animal
      // return it
    // else if its not the preferred animal and it hasn't been seen before
      // add the animal to the seenAnimals HashSet
      // dequeue the animal
      // add it to the back
    // else
      // **this means its not the preferred type AND we've seen it before**
      // return null (or break out of the while loop)
    // !!check the next one the same way (end of loop)
    HashSet<Animal> seenAnimals = new HashSet<Animal>();
    while (true) {
      Animal currentFrontAnimal = this.peek();
      if(currentFrontAnimal.species.equals(pref)) {
        return this.remove(currentFrontAnimal);
      } else if (!seenAnimals.contains(currentFrontAnimal)) {
        seenAnimals.add(currentFrontAnimal);
        this.remove(currentFrontAnimal);
        this.add(currentFrontAnimal);
      } else {
        break;
      }
    }

    return null;
  }
}