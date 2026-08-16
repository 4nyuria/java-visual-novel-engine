package com.anyuria.vnengine.state;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import com.anyuria.vnengine.item.Item;

public class GameState {
	private List<Item> inventory;
	
    private Map<String, Boolean> flags;

    public GameState() {
    	
    	inventory = new ArrayList<>();
        flags = new HashMap<>();
    }
    //flags
    public void setFlag(String name, boolean value) {

        flags.put(name, value);
    }
    public boolean getFlag(String name) {

        return flags.getOrDefault(name, false);
    }

    public boolean hasFlag(String name) {

        return flags.containsKey(name);
    }

    public void removeFlag(String name) {

        flags.remove(name);
    }
    
    //items
    public void addItem(Item item) {

        inventory.add(item);
    }
    public void removeItem(Item item) {

        inventory.remove(item);
    }
    public boolean hasItem(String itemId) {

        for (Item item : inventory) {

            if (item.getId().equals(itemId)) {
                return true;
            }
        }

        return false;
    }
    public List<Item> getInventory() {

        return inventory;
    }
}