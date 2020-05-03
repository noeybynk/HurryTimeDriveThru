package orderingFood;

public class EdibleFactory {

    public static String[] getCategory() {
        Category[] categories = Category.values();
        String[] cateArray = new String[categories.length];
        for (int i = 0; i < categories.length; i++) {
            cateArray[i] = categories[i].toString();
        }
        return cateArray;
    }

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
