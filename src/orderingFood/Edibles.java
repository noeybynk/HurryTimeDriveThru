package orderingFood;

/**
 * An interface to get link of image, get price, and change underscore to blank space in each edible.
 */
public interface Edibles {

    String getLink();
    String getPrice();
    String nameWithSpace(String name);
}
