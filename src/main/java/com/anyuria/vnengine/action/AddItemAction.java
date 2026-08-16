package com.anyuria.vnengine.action;

import com.anyuria.vnengine.item.Item;
import com.anyuria.vnengine.state.GameState;

public class AddItemAction {

    private Item item;

    public AddItemAction(Item item) {

        this.item = item;
    }

    public void execute(GameState gameState) {

        gameState.addItem(item);
    }

    public Item getItem() {

        return item;
    }
}