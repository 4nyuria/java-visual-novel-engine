package com.anyuria.vnengine.action;

import com.anyuria.vnengine.state.GameState;

public class SetFlagAction {

    private String flagName;
    private boolean value;

    public SetFlagAction(String flagName, boolean value) {

        this.flagName = flagName;
        this.value = value;
    }

    public void execute(GameState gameState) {

        gameState.setFlag(flagName, value);
        System.out.println(
                "FLAG: " + flagName + " = " + value
        );
    }

    public String getFlagName() {

        return flagName;
    }

    public boolean getValue() {

        return value;
    }
}