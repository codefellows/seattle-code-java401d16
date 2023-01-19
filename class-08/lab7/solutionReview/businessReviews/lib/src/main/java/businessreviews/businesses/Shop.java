package businessreviews.businesses;

import businessreviews.reviews.Review;

import java.util.ArrayList;

public class Shop {
  // props
  String name;
  String description;
  String priceCategory;
  float stars;
  ArrayList<Review> reviews;

  //methods
  Shop(String name, String description, String priceCategory) {
    this.name = name;
    this.description = description;
    this.priceCategory = priceCategory;
    this.stars = 0;
    reviews = new ArrayList<Review>();
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
