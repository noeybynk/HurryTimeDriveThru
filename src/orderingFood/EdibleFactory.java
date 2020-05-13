package orderingFood;

/**
 * Define a EdibleFactory class that is responsible for getting the units and telling the UI
 * what kinds of edible are available. For the link of the images and price you can use an Enum,
 * or just use Strings.
 */
public class EdibleFactory {

    /** get category in Category enum to be a list of String.*/
    public static String[] getCategory() {
        Category[] categories = Category.values();
        String[] cateArray = new String[categories.length];
        for (int i = 0; i < categories.length; i++) {
            cateArray[i] = categories[i].toString();
        }
        return cateArray;
    }

    /** get the enum value in each edible by using switch case.*/
    public static Edibles[] getEdibles(String edibleName) {
        switch (edibleName) {
            case "Food":
                return Food.values();
            case "Drink":
                return Drink.values();
            case "Dessert":
                return Dessert.values();
            default:
                return new Edibles[0];
        }
    }
}
