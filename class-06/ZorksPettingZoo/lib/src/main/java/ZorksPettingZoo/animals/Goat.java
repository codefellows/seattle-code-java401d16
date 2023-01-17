package ZorksPettingZoo.animals;

public class Goat extends Animal{
    public Goat(Boolean hasTail, Integer age, Integer numberOfLegs, String name, String color) {
        super(hasTail, age, numberOfLegs, name, color);
    }

    public void jump(){
        System.out.println("BOING!");
    }

    public void faint(){}
}
