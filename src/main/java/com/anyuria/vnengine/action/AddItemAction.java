package com.anyuria.vnengine.action;

import com.anyuria.vnengine.item.Item;
import com.anyuria.vnengine.state.GameState;

public class AddItemAction implements Action {

    private Item item;

    public AddItemAction(Item item) {

        this.item = item;
    }

    @Override
    public void execute(GameState gameState) {

        if (gameState.hasItem(item)) {

            System.out.println(
                    "El jugador ya tiene: "
                    + item.getName()
            );

            return;
        }

        gameState.addItem(item);

        System.out.println(
                "ITEM OBTENIDO: "
                + item.getName()
        );
    }

    public Item getItem() {

        return item;
    }
}