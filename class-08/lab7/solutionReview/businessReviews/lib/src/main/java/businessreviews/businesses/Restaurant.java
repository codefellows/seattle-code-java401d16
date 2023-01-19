package businessreviews.businesses;

import businessreviews.reviews.Review;

import java.util.ArrayList;

public class Restaurant {
  //props
  public String name;
  public float stars;
  public String priceCategory;
  public ArrayList<Review> reviews;

  //methods
  Restaurant(String name, String priceCategory) {
    this.name = name;
    this.priceCategory = priceCategory;
    this.stars = 0f;
    this.reviews = new ArrayList<Review>();
  }

  public void addReview(Review newReview) {
    if(!reviews.contains(newReview)) {
      reviews.add(newReview);

      int totalStars = 0;
      for (Review restaurantReview: reviews) {
        totalStars += restaurantReview.stars;
      }
      this.stars = (float)totalStars / (float)reviews.size();
    }
  }

  @Override
  public String toString() {
    return null;
  }
}
