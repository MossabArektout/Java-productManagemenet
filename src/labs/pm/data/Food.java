package labs.pm.data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Mossab Arektout
 **/
public final class Food extends Product{
    private LocalDate bestBefore;

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    Food(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        super(id, name, price, rating);
        this.bestBefore = bestBefore;
    }


    @Override
    public BigDecimal getDiscount() {
        return (bestBefore.isEqual(LocalDate.now()))
                ?super.getDiscount() : BigDecimal.ZERO;
    }

    @Override
    public Product applyRating(Rating newRating) {
        return new Food(getId(), getName(), getPrice(), newRating,bestBefore);
    }
}
