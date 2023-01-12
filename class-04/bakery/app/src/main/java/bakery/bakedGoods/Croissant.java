package bakery.bakedGoods;


import java.math.BigDecimal;

// a croissant IS a type of BakedGood
public class Croissant extends BakedGood{
    boolean isFlaky;
    boolean isDropped;

    public Croissant(BigDecimal price, String flavor, String size, boolean isFlaky, boolean isDropped) {
        super(price, flavor, size);
        this.isFlaky = isFlaky;
        this.isDropped = isDropped;
    }
}
