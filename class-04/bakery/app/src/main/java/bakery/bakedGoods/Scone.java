package bakery.bakedGoods;

import java.math.BigDecimal;

public class Scone extends BakedGood{
    boolean isGlazed;

    public Scone(BigDecimal price, String flavor, String size, boolean isGlazed) {
        super(price, flavor, size);
        this.isGlazed = isGlazed;
    }
}
