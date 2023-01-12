package bakery.bakedGoods;

import java.math.BigDecimal;

public class BakedGood {
    BigDecimal price;
    String flavor;
    String size;

    public BakedGood(BigDecimal price, String flavor, String size) {
        this.price = price;
        this.flavor = flavor;
        this.size = size;
    }

}
