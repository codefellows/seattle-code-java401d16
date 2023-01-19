package businessreviews.businesses;

import businessreviews.reviews.MovieReview;
import businessreviews.reviews.Review;

import java.util.ArrayList;

public class Theater {
  public String name;
  public float stars;
  public ArrayList<String> movies;
  public ArrayList<MovieReview> reviews;

  //methods
  public Theater(String name, float stars) {
    this.name = name;
    this.stars = stars;
    this.movies = new ArrayList<String>();
    this.reviews = new ArrayList<MovieReview>();
  }

  public void addMovie(String newMovie) {
    if(!movies.contains(newMovie)) {
      movies.add(newMovie);
    }
  }

  public void removeMovie(String movieToRemove) {
    if(movies.contains(movieToRemove)) {
      movies.remove(movieToRemove);
    }
  }

  public void addReview(MovieReview newReview) {
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
