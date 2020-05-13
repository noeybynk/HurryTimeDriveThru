package orderingFood;

public class EdiblesItem {
    private String name;
    private int price;
    private int quantity;

    public EdiblesItem(Edibles edibles, int quantity) {
        this.name = edibles.nameWithSpace(edibles.toString());
        this.price = Integer.parseInt(edibles.getPrice());
        this.quantity = quantity;
    }

    public String getName() { return this.name; }
    public int getPrice() { return price*quantity; }

    public void setQuantity(int quantity) {this.quantity = quantity;}
    public int getQuantity() { return this.quantity; }

    public void setPrice(int price) {
        this.price = price;
    }

    public String toString() {
        return getName() + " " + getPrice() + "Baht.";
    }


}
