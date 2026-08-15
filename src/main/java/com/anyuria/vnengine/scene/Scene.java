package com.anyuria.vnengine.scene;

import java.util.List;
import com.anyuria.vnengine.character.Character;
import com.anyuria.vnengine.dialogue.Dialogue;


public class Scene {

    private int id;
    private List<Dialogue> dialogues;
    private String backgroundPath;
    private List<Character> characters;
 
    
    public Scene(
            int id,
            List<Dialogue> dialogues,
            String backgroundPath,
            List<Character> characters
    ) {

        this.id = id;
        this.dialogues = dialogues;
        this.backgroundPath = backgroundPath;
        this.characters = characters;
    }

    public int getId() {
        return id;
    }

    public List<Dialogue> getDialogues() {
        return dialogues;
    }

    public String getBackgroundPath() {
        return backgroundPath;
    }

    public List<Character> getCharacters() {
        return characters;
    }
}