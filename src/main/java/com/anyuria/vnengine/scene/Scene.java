package com.anyuria.vnengine.scene;

import com.anyuria.vnengine.character.Character;
import java.util.List;

public class Scene {

    private int id;
    private String text;
    private String backgroundPath;
    private List<Character> characters;

    public Scene(
            int id,
            String text,
            String backgroundPath,
            List<Character> characters
    ) {

        this.id = id;
        this.text = text;
        this.backgroundPath = backgroundPath;
        this.characters = characters;
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

    public List<Character> getCharacters() {
        return characters;
    }
}