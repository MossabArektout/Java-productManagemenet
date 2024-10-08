package labs.pm.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import static java.math.RoundingMode.HALF_UP;

/**
 * @author Mossab Arektout
 **/
public sealed abstract class Product implements Rateable<Product> permits Food, Drink{
    private final int id;
    private final String name;
    private final BigDecimal price;
    public static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.1);
    private Rating rating;

    Product(int id, String name, BigDecimal price, Rating rating){
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

//    public void setId(final int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

//    public void setName(final String name) {
//        this.name = name;
//    }

    public BigDecimal getPrice() {
        return price;
    }

//    public void setPrice(final BigDecimal price) {
//        this.price = price;
//    }

    public Rating getRating() {
        return rating;
    }

    public BigDecimal getDiscount(){
        return price.multiply(DISCOUNT_RATE).setScale(2, HALF_UP);
    }

    public abstract Product applyRating(Rating newRating);

    public LocalDate getBestBefore() {
        return LocalDate.now();
    }

    @Override
    public String toString() {
        return id+", "+name+", "+price+", "+getDiscount()+", "+rating.getStars()+" " + getBestBefore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Product product) {
            return id == product.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


}
