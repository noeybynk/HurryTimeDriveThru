package orderingFood;

import java.util.ArrayList;
import java.util.List;

public class EdiblesList {

    private static EdiblesList instance = null;
    private List<String> list;
    private String name;
    private int price;
    private int quantity;

    private EdiblesList(){ list = new ArrayList<>(); }
    public static EdiblesList getInstance() {
        if(instance == null) {
            instance = new EdiblesList();
        }
        return instance;
    }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setPrice(int price) {this.price = price; }
    public int getPrice() {return price;}

    public void setQuantity(int quantity) {this.quantity = quantity; }
    public int getQuantity() {return quantity;}

    public void setPayment(int price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }
    public int getPayment() {
        return price*quantity;
    }

    public void addList(String name, int price, int quantity) {
        list.add(name + " " + price*quantity + "Baht.");
    }
    public List<String> getList() {
        return this.list;
    }
}
