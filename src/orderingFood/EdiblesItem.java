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
    public int getPrice() { return this.price; }
    public int getQuantity() {return this.quantity; }
    public void SetTotalPrice(int price, int quantity) {
        this.price = price*quantity;
    }

    public String toString() {
        return getName() + " " + getPrice()*getQuantity() + "Baht.";
    }
}
