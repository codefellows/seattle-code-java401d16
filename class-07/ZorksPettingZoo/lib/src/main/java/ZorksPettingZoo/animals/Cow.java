package ZorksPettingZoo.animals;

public class Cow extends Animal implements JumpingAnimals{

    public Cow(Boolean hasTail, Integer age, Integer numberOfLegs, String name, String color) {
        super(hasTail, age, numberOfLegs, name, color);
    }

    public void digest(){}


    @Override
    public String jump(int howHigh) {
        return null;
    }

    @Override
    public String leap(int howFar) {
        return null;
    }

    @Override
    public String vaulting() {
        return null;
    }
}
