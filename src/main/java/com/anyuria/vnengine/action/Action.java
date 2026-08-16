package com.anyuria.vnengine.action;

import com.anyuria.vnengine.state.GameState;

public interface Action {

    void execute(GameState gameState);
}