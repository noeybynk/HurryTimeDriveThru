package orderingFood;

public enum Dessert implements Edibles{

    Buttery_Raspberry_Crumble_Bars("https://uppic.cc/d/6343", "85"),
    Mint_Oreo_Balls("https://uppic.cc/d/634U", "90"),
    Ultimate_Gooey_Brownies("https://uppic.cc/d/634q", "70"),
    Chocolate_And_Strawberry_Cake("https://uppic.cc/d/634z", "180"),
    Sour_Cream_Doughnuts("https://uppic.cc/d/634L", "60"),
    Butterfinger_Cookie_Dough_Cheesecake_Bars("https://uppic.cc/d/6347", "75"),
    Meyer_Lemon_Bars("https://uppic.cc/d/6348", "60"),
    Roasted_Pears_With_Espresso_Mascarpone_Cream("https://uppic.cc/d/634C", "80"),
    Chocolate_Chip_Cookies_with_Nutella_Brown_Butter_and_Sea_Salt("https://uppic.cc/d/634c", "60"),
    Tiramisu("https://uppic.cc/d/634e", "70");

    private final String link;
    private final String price;

    public String getLink() { return link; }
    public String getPrice() { return price; }

    private Dessert(String link, String price) {
        this.link = link;
        this.price = price;
    }

    //link
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
