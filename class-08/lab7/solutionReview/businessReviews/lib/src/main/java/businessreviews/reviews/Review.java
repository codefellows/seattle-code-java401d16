package businessreviews.reviews;

public class Review {
  // props
  public String body;
  public String author;
  public int stars;

  // methods

  public Review(String body, String author, int stars) {
    this.body = body;
    this.author = author;
    this.stars = stars;
  }

  @Override
  public String toString() {
    return "Review{" +
      "body:'" + body + '\'' +
      ", author:'" + author + '\'' +
      ", stars:" + stars +
      '}';
  }
}
