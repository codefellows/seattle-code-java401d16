package ZorksPettingZoo.animals;

public class Llama extends Animal{
    public Llama(Boolean hasTail, Integer age, Integer numberOfLegs, String name, String color) {
        super(hasTail, age, numberOfLegs, name, color);
    }

    @Override
    public String move() {
        return "Clop Clop Clop";
    }

    public void spit(){
        System.out.println("Patooie");
    }

    public void shear(){
        System.out.println("BZZZZZZZZ");
    }

}
