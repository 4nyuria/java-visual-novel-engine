package com.anyuria.vnengine.action;

import com.anyuria.vnengine.state.GameState;

public class AddVariableAction implements Action {

    private String variable;
    private int amount;

    public AddVariableAction(
            String variable,
            int amount) {

        this.variable = variable;
        this.amount = amount;
    }

    @Override
    public void execute(GameState gameState) {

        gameState.addVariable(
                variable,
                amount
        );

        System.out.println(
                "VARIABLE MODIFICADA: "
                + variable
                + " += "
                + amount
        );
    }
}