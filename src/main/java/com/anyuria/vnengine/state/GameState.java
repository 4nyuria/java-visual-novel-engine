package com.anyuria.vnengine.state;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.anyuria.vnengine.item.Item;

public class GameState {

    private Map<String, Boolean> flags;
    private List<Item> inventory;
    private Map<String, Integer> variables;
    
    public GameState() {

        flags = new HashMap<>();
        inventory = new ArrayList<>();
        variables = new HashMap<>();
    }

    // FLAGS
    public void setFlag(String name, boolean value) {

        flags.put(name, value);
    }

    public boolean getFlag(String name) {

        return flags.getOrDefault(name, false);
    }
    
 // VARIABLES

	 public void setVariable(String name, int value) {
	
	     variables.put(name, value);
	 }
	
	 public int getVariable(String name) {
	
	     return variables.getOrDefault(name, 0);
	 }
	
	 public void addVariable(String name, int amount) {
	
	     int currentValue =
	             variables.getOrDefault(name, 0);
	
	     variables.put(
	             name,
	             currentValue + amount
	     );
	 }
	
	 public boolean hasVariable(
	         String name,
	         int requiredValue) {
	
	     return getVariable(name) >= requiredValue;
	 }
    // INVENTARIO
    public void addItem(Item item) {

        inventory.add(item);
    }

    public void removeItem(Item item) {

        inventory.remove(item);
    }

    // Buscar por ID
    public boolean hasItem(String itemId) {

        for (Item item : inventory) {

            if (item.getId().equals(itemId)) {

                return true;
            }
        }

        return false;
    }

    // Buscar directamente por objeto
    public boolean hasItem(Item item) {

        return hasItem(item.getId());
    }

    public List<Item> getInventory() {

        return inventory;
    }
}