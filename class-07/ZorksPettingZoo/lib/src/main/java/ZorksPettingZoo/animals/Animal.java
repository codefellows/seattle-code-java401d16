package ZorksPettingZoo.animals;

public abstract class Animal {
    private Boolean hasTail;
    private Integer age;
    private Integer numberOfLegs;
    private String name;
    private String color;

    public Animal() {
    }

    public Animal(Boolean hasTail, Integer age, Integer numberOfLegs, String name, String color) {
        this.hasTail = hasTail;
        this.age = age;
        this.numberOfLegs = numberOfLegs;
        this.name = name;
        this.color = color;
    }

    // Methods

    // eat
    public void eat(){
        System.out.println("NOM NOM NOM");
    }
    // poop
    public void poop(){
        System.out.println("Straining noises");
    }
    // move
    public String move(){
        System.out.println("Crawl, walk, or slither away");
        return "Crawl, walk, or slither away";
    }
    // makeNoise
    public void makeNoise(){
        System.out.println("SCREEEEE");
    }

    // Getters and Setters
    public Boolean getHasTail() {
        return hasTail;
    }

    public void setHasTail(Boolean hasTail) {
        this.hasTail = hasTail;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getNumberOfLegs() {
        return numberOfLegs;
    }

    public void setNumberOfLegs(Integer numberOfLegs) {
        this.numberOfLegs = numberOfLegs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
