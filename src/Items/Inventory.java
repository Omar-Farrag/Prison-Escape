package Items;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> itemsList;
    private int maxSize;

    public Inventory(int maxSize) {
        itemsList = new ArrayList<>();
        this.maxSize = maxSize;
    }

    public boolean addToInventory(Item i) {
        if (itemsList.size() == maxSize)
            return false;

        itemsList.add(i);
        return true;
    }

    public boolean removeFromInventory(Item i) {
        if (itemsList.size() == 0)
            return false;

        itemsList.remove(i);
        return true;
    }

    public void clear() {
        itemsList.clear();
    }

    public ArrayList<Item> getItemsList() {
        return itemsList;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public Item findItem(String name) {
        name = name.toLowerCase().trim();
        for (Item i : itemsList) {
            if (name.contains(i.getName().toLowerCase().trim()))
                return i;
        }
        return null;
    }

    public boolean contains(String name) {
        return findItem(name) != null;
    }

    @Override
    public String toString() {
        String result = "";
        for (Item i : itemsList)
            result += i.toString() + "/";
        return result;
    }

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("fs");
        strings.add("ds");
        strings.add("ts");
        strings.add("gs");
        strings.add("ps");
        System.out.println(strings);
        strings.remove(null);
        System.out.println(strings);
        strings.remove("ds");
        System.out.println(strings);
    }
}
