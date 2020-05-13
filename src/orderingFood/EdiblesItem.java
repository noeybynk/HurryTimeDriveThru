package orderingFood;

/**
 * They are edible items for Order view page(order history). When you choose food, it will appear in history.
 * You can set new value for price or quantity to update edible items in history. Then, you will get the correct
 * order list for your payment.
 */
public class EdiblesItem {
    private String name;
    private int price;
    private int quantity;

    //the constructor for calling new class in another class.
    public EdiblesItem(Edibles edibles, int quantity) {
        this.name = edibles.nameWithSpace(edibles.toString());
        this.price = Integer.parseInt(edibles.getPrice());
        this.quantity = quantity;
    }

    //you can get the name of edible to check the name case.
    public String getName() { return this.name; }
    //you can get the price of edible to check and get to use for many case.
    public int getPrice() { return price*quantity; }

    //to set the new quantity
    public void setQuantity(int quantity) {this.quantity = quantity;}
    //you can get the quantity of edible to check and get to use for number textfield.
    public int getQuantity() { return this.quantity; }

    //to set the new price
    public void setPrice(int price) {
        this.price = price;
    }

    /** Pattern of string to show in order view page.*/
    public String toString() {
        return getName() + " " + getPrice() + "Baht.";
    }


}
