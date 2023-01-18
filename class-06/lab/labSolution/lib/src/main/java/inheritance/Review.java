package inheritance;

public class Review {
    private String text;
    private String author;
    private int numOfStars;

    public Review(String text, String author, int numOfStars) {
        this.text = text;
        this.author = author;
        this.numOfStars = numOfStars;
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }

    public int getNumOfStars() {
        return numOfStars;
    }

    @Override
    public  String toString() {
        return "some text for body " + text + "By " + author + "star rating is " + numOfStars;
    }

}
