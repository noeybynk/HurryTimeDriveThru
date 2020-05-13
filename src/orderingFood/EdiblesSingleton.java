package orderingFood;

import java.util.ArrayList;
import java.util.List;

/**
 * A singleton class for collecting EdiblesItem class to the list. They will show in order view page
 * and can use for increasing-decreasing the quantity in textfield.
 * So I use singleton for multipurpose.
 */
public class EdiblesSingleton {

    private static EdiblesSingleton instance = null;
    private List<EdiblesItem> list;
    private String name;

    //constructor must be private.
    private EdiblesSingleton(){ list = new ArrayList<>(); }
    public static EdiblesSingleton getInstance() {
        if(instance == null) {
            instance = new EdiblesSingleton();
        }
        return instance;
    }

//    public void setName(String name) { this.name = name; }
//    public String getName() { return this.name; }

    /** take edible items to add in the list.*/
    public void addList(Edibles edibles, int quantity) {
        if(list.size() > 0) {
            for(int i=0; i<list.size(); i++) {
                if(list.get(i).getName().equals(edibles.nameWithSpace(edibles.toString()))) {
                    list.get(i).setQuantity(quantity);
                    list.get(i).setPrice(Integer.parseInt(edibles.getPrice()));
                    return;
                }
            }
            list.add(new EdiblesItem(edibles, quantity));
        } else {
            list.add(new EdiblesItem(edibles, quantity));
        }
    }

    /** delete edible items from the list.*/
    public void removeList(Edibles edibles, int quantity) {
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getName().equals(edibles.nameWithSpace(edibles.toString()))) {
                    list.get(i).setQuantity(quantity);
                    list.get(i).setPrice(Integer.parseInt(edibles.getPrice()));
                    if(list.get(i).getPrice() == 0) {
                        list.remove(list.get(i));
                    }
                    return;
                }
            }
            list.remove(new EdiblesItem(edibles, quantity));
        } else {
            list.remove(new EdiblesItem(edibles, quantity));
        }
    }
    /** get the list to use.*/
    public List<EdiblesItem> getList() {
        return this.list;
    }

    /** check for increasing quantity, if its name have happened before.*/
    public int findQuantity(String name) {
        if(list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getName().equals(name)) {
                    return list.get(i).getQuantity();
                }
            }
        }
        return 0;
    }

}
