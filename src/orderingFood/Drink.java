package orderingFood;

/**
 * We want to get link and price to handle many kind of edibles. A good way to handle them is an enum.
 * We will use enum to get link of image and price of each drink to show in the main.
 */
public enum Drink implements Edibles {

    Coke(
            "https://s3-ap-southeast-1.amazonaws.com/img-in-th/ea888966555dde6b35f58a47a0adc55c.png",
            "10"),
    Espresso(
            "https://s3-ap-southeast-1.amazonaws.com/img-in-th/1521a268c4d0a0630eb742d6ad9d25f0.png",
            "35"),
    Fresh_Water(
            "https://s3-ap-southeast-1.amazonaws.com/img-in-th/66c5167b561f5807e078d77ea19bf010.png",
            "10"),
    Iced_Cocoa(
            "https://s3-ap-southeast-1.amazonaws.com/img-in-th/aec64bb0725f9d66e528bdc5363c65b5.png",
            "45"),
    Lemontea("https://uppic.cc/d/634h",
            "30"),
    PinkMilk(
            "https://s3-ap-southeast-1.amazonaws.com/img-in-th/dd1aa09b316d8a4c5b44b24ed283487a.png",
            "35"),
    Redlime_Soda("https://s3-ap-southeast-1.amazonaws.com/img-in-th/3a5c4614c638bf16c3287ee57a5fb3a8.png",
            "55"),
    Thaitea_blackpearl("https://s3-ap-southeast-1.amazonaws.com/img-in-th/754b5eebfbdd547bddac363b9957a8ca.png",
            "45");

    // the first attribute of the enum members is link
    private final String link;
    // the second attribute of the enum members is price
    private final String price;

    // methods are just like in a class
    public String getLink() { return link; }
    public String getPrice() { return price; }

    // enum constructor must be private
    private Drink(String link, String price) {
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
