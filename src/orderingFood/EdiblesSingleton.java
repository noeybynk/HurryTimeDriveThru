package orderingFood;

import java.util.ArrayList;
import java.util.List;

public class EdiblesSingleton {

    private static EdiblesSingleton instance = null;
    private List<EdiblesItem> list;
    private String name;

    private EdiblesSingleton(){ list = new ArrayList<>(); }
    public static EdiblesSingleton getInstance() {
        if(instance == null) {
            instance = new EdiblesSingleton();
        }
        return instance;
    }

    public void setName(String name) { this.name = name; }
    public String getName() { return this.name; }

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
    public List<EdiblesItem> getList() {
        return this.list;
    }

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
