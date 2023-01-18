package ZorksPettingZoo.animals;

public class Goat extends Animal implements JumpingAnimals{
    public Goat(Boolean hasTail, Integer age, Integer numberOfLegs, String name, String color) {
        super(hasTail, age, numberOfLegs, name, color);
    }

    @Override
    public String jump(int howHigh) {
        return "Goat jumps high";
    }

    @Override
    public String leap(int howFar) {
        return "";
    }

    @Override
    public String vaulting() {
        return null;
    }


    public void faint(){}
}
