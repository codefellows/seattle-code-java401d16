package bakery.bakedGoods;

import java.math.BigDecimal;

public class Donut extends BakedGood{
    String shape;
    boolean hasHole;
    boolean isGlazed;

    public Donut(BigDecimal price, String flavor, String size, String shape, boolean hasHole, boolean isGlazed) {
        super(price, flavor, size);
        this.shape = shape;
        this.hasHole = hasHole;
        this.isGlazed = isGlazed;
    }
}
