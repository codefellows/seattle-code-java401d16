package bakery.bakedGoods;

import bakery.Bakery;

import java.math.BigDecimal;
import java.util.List;

public class Muffin extends BakedGood {
     private boolean isBaked;
     private List<String> ingredients;

    // NEED A CONSTRUCTOR

    public Muffin(BigDecimal price, String flavor, String size, boolean isBaked, List<String> ingredients) {
        super(price, flavor, size);
        this.isBaked = isBaked;
        this.ingredients = ingredients;
    }
H
    public boolean isBaked() {
        return isBaked;
    }

    public void setBaked(boolean baked) {
        isBaked = baked;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
