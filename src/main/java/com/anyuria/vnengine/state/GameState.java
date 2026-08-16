package com.anyuria.vnengine.state;

import java.util.HashMap;
import java.util.Map;

public class GameState {

    private Map<String, Boolean> flags;

    public GameState() {

        flags = new HashMap<>();
    }

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
}