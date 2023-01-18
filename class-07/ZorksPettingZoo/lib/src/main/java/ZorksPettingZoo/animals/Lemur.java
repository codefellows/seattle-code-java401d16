package ZorksPettingZoo.animals;

public class Lemur extends Animal implements JumpingAnimals{
    public Lemur(Boolean hasTail, Integer age, Integer numberOfLegs, String name, String color) {
        super(hasTail, age, numberOfLegs, name, color);
    }

    public void climb(){}
    public void party(){}

    @Override
    public String jump(int howHigh) {
        return null;
    }

    @Override
    public String leap(int howFar) {
        return "lemur leaps far";
    }

    @Override
    public String vaulting() {
        return null;
    }

}
