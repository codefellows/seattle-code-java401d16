package inheritance;

import java.util.ArrayList;

public class Restaurant {
    private String name;
    private String price;
    private float numOfStars;
    private ArrayList<Review> reviews;

    public Restaurant(String name, String price) {
        this.name = name;
        this.price = price;
        this.numOfStars = 0;
        this.reviews = new ArrayList<>();
    }

    // Methods
    public void addReview(Review review){
        int sumOfReviewStars = 0;
        // 1. Add it to reviews List
        reviews.add(review);
        // 2. iterate over and get total sum of values
        for (Review arrayReview: reviews) {
            sumOfReviewStars += arrayReview.getNumOfStars();
        }
        // 3. calculate averageNum of stars
        float averagNumOfStars = (sumOfReviewStars/reviews.size());
        // 4. call setNumOfStars with new value
        setNumOfStars(averagNumOfStars);
    }


    // GETTERS and SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public float getNumOfStars() {
        return numOfStars;
    }

    public void setNumOfStars(float numOfStars) {
        this.numOfStars = numOfStars;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }
    @Override
    public  String toString() {
        return "Restaurant " + name + "prices are " + price + "star rating is " + numOfStars;
    }

}
