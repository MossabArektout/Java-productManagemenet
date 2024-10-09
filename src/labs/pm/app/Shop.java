package labs.pm.app;

import labs.pm.data.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

/**
 * @author Mossab Arektout
 **/
public class Shop {
    public static void main(String[] args) {

        ProductManager pm = new ProductManager(Locale.UK);

        Product p1 = pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.NOT_RATED);
        pm.printProductReport(101);
        p1 = pm.reviewProduct(101, Rating.FOUR_STAR, "Nice Hot Cup of Tea");
        p1 = pm.reviewProduct(101, Rating.TWO_STAR, "Rather weak Tea");
        p1 = pm.reviewProduct(101, Rating.FOUR_STAR, "Fine Tea");
        p1 = pm.reviewProduct(101, Rating.FOUR_STAR, "Good Tea");
        p1 = pm.reviewProduct(101, Rating.FIVE_STAR, "Perfect Tea");
        p1 = pm.reviewProduct(101, Rating.THREE_STAR, "Just add some lemon");
        pm.printProductReport(101);

        Product p2 = pm.createProduct(102, "Coffee", BigDecimal.valueOf(1.99), Rating.NOT_RATED);
        p2 = pm.reviewProduct(102, Rating.THREE_STAR, "Coffee was ok");
        p2 = pm.reviewProduct(102, Rating.ONE_STAR, "Where is the Milk ?");
        p2 = pm.reviewProduct(102, Rating.FIVE_STAR, "It's perfect");
        pm.printProductReport(102);

        Product p3 = pm.createProduct(103, "Cake", BigDecimal.valueOf(3.99), Rating.NOT_RATED, LocalDate.now().plusDays(2));
        p3 = pm.reviewProduct(103, Rating.FIVE_STAR, "CVery nice cake");
        p3 = pm.reviewProduct(103, Rating.FOUR_STAR, "Good but i've expected more");
        p3 = pm.reviewProduct(103, Rating.FIVE_STAR, "This cake is perfect");
        pm.printProductReport(103);
    }
}