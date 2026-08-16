package com.anyuria.vnengine.action;

import com.anyuria.vnengine.state.GameState;

public class SetVariableAction implements Action {

    private String variable;
    private int value;

    public SetVariableAction(
            String variable,
            int value) {

        this.variable = variable;
        this.value = value;
    }

    @Override
    public void execute(GameState gameState) {

        gameState.setVariable(
                variable,
                value
        );

        System.out.println(
                "VARIABLE: "
                + variable
                + " = "
                + value
        );
    }
}