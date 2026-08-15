package com.anyuria.vnengine.scene;

import com.anyuria.vnengine.character.Character;

public class Scene {

    private int id;
    private String text;
    private String backgroundPath;
    private Character character;

    public Scene(
            int id,
            String text,
            String backgroundPath,
            Character character
    ) {

        this.id = id;
        this.text = text;
        this.backgroundPath = backgroundPath;
        this.character = character;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getBackgroundPath() {
        return backgroundPath;
    }

    public Character getCharacter() {
        return character;
    }
}