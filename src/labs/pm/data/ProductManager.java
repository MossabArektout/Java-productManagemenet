package labs.pm.data;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;

/**
 * @author Mossab Arektout
 **/
public class ProductManager{
    private Product product;
    private Review[] review = new Review[5];
    private Locale locale;
    private ResourceBundle resources;
    private DateTimeFormatter dateFormat;
    private NumberFormat moneyFormat;

    public ProductManager(Locale locale) {
        this.locale = locale;
        resources = ResourceBundle.getBundle("labs.pm.data.resources");
        dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).localizedBy(locale);
        moneyFormat = NumberFormat.getCurrencyInstance(locale);
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore){
        product = new Food(id, name, price, rating, bestBefore);
        return product;
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating){
        product = new Drink(id, name, price, rating);
        return product;
    }
    public Product reviewProduct(Product product, Rating rating, String comments){
        if (review[review.length-1] != null){
            review = Arrays.copyOf(review, review.length+5);
        }
        int sum = 0, i = 0;
        boolean reviewed = false;
        while(i < review.length && !reviewed){
            if (review[i] == null) {
                review[i] = new Review(rating, comments);
                reviewed = true;
            }
            sum += review[i].rating().ordinal();
            i++;
        }
        this.product = product.applyRating(Rateable.convert(Math.round((float)sum/i)));
        return this.product;
    }

    public void printProductReport(){
        StringBuilder txt = new StringBuilder();
        String type = switch(product){
            case Food food -> resources.getString("food");
            case Drink drink -> resources.getString("drink");
        };
        txt.append(MessageFormat.format(resources.getString("product"),
                    product.getName(),
                    moneyFormat.format(product.getPrice()),
                    product.getRating().getStars(),
                    dateFormat.format(product.getBestBefore()),
                    type));
        txt.append('\n');
        for (Review rev : review){
            if (rev == null){
                break;
            }
            txt.append(MessageFormat.format(resources.getString("review"),
                    rev.rating().getStars(),
                    rev.comments()));
            txt.append('\n');
        }
        if (review[0] == null){
            txt.append(resources.getString("no.reviews"));
            txt.append('\n');
        }
        System.out.println(txt);
    }


}
