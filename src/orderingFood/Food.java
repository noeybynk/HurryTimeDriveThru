package orderingFood;

/**
 * We want to get link and price to handle many kind of edibles. A good way to handle them is an enum.
 * We will use enum to get link of image and price of each Food to show in the main.
 */
public enum Food implements Edibles{

    Waffle_fries("https://uppic.cc/d/63Je", "60"),
    Blizzard("https://uppic.cc/d/63Jc", "50"),
    Bacon_cheeseburger("https://uppic.cc/d/62dW", "65"),
    Chicken_nuggets("https://uppic.cc/d/63Jd", "40"),
    Pretzel("https://uppic.cc/d/63KM", "40"),
    Taco("https://uppic.cc/d/63Ky", "55"),
    Cajun_fries("https://s3-ap-southeast-1.amazonaws.com/img-in-th/62e2a26396bf116ad6b634f392c8241f.jpg", "55"),
    Baked_apple_pie("https://s3-ap-southeast-1.amazonaws.com/img-in-th/e2b26f9cbc4ea172a2d7abe70dee7d59.jpg", "35"),
    Cheese_curds("https://s3-ap-southeast-1.amazonaws.com/img-in-th/6c4235880a637da718acf377ca443be5.jpg", "50"),
    Mashed_potatos_and_Cajun_gravy(
            "https://s3-ap-southeast-1.amazonaws.com/img-in-th/7a2c940ca7e1a8d6652f331a578da7ba.jpg",
            "65");

    // the first attribute of the enum members is link
    private final String link;
    // the second attribute of the enum members is price
    private final String price;

    // methods are just like in a class
    public String getLink() { return link; }
    public String getPrice() { return price; }

    // enum constructor must be private
    private Food(String link, String price) {
        this.link = link;
        this.price = price;
    }

    /** To change the underscore to blank space.*/
    public String nameWithSpace(String name) {
        char changes = '_';
        char[] char_text = name.toCharArray();
        char[] new_text = new char[char_text.length];

        for(int i=0; i<char_text.length; i++) {
            if(char_text[i] == changes) {
                char_text[i] = ' ';
            }
            new_text[i] = char_text[i];
        }
        return new String(new_text);
    }

}
