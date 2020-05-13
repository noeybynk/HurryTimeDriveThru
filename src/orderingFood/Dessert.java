package orderingFood;

/**
 * We want to get link and price to handle many kind of edibles. A good way to handle them is an enum.
 * We will use enum to get link of image and price of each dessert to show in the main.
 */
public enum Dessert implements Edibles{

    Buttery_Raspberry_Crumble_Bars("https://uppic.cc/d/6343", "85"),
    Mint_Oreo_Balls("https://uppic.cc/d/634U", "90"),
    Ultimate_Gooey_Brownies("https://uppic.cc/d/634q", "70"),
    Chocolate_And_Strawberry_Cake("https://uppic.cc/d/634z", "180"),
    Sour_Cream_Doughnuts("https://uppic.cc/d/634L", "60"),
    Butterfinger_Cheesecake_Bars("https://uppic.cc/d/6347", "75"),
    Meyer_Lemon_Bars("https://uppic.cc/d/6348", "60"),
    Roasted_Pears_Mascarpone_Cream("https://uppic.cc/d/634C", "80"),
    Chocolate_Chip_Cookies_with_Nutella("https://uppic.cc/d/634c", "60"),
    Tiramisu("https://uppic.cc/d/634e", "70");

    // the first attribute of the enum members is link
    private final String link;
    // the second attribute of the enum members is price
    private final String price;

    // methods are just like in a class
    public String getLink() { return link; }
    public String getPrice() { return price; }

    // enum constructor must be private
    private Dessert(String link, String price) {
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
