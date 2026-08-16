package com.anyuria.vnengine.scene;

import java.util.ArrayList;
import java.util.List;
import com.anyuria.vnengine.character.Character;
import com.anyuria.vnengine.dialogue.Dialogue;
import com.anyuria.vnengine.choice.Choice;

import com.anyuria.vnengine.action.Action;

public class Scene {

    private int id;
    private List<Dialogue> dialogues;
    private String backgroundPath;
    private List<Character> characters;
    private List<Choice> choices;
    private List<Action> actions;
    
    public Scene(
            int id,
            List<Dialogue> dialogues,
            String backgroundPath,
            List<Character> characters
    ) {

        this.id = id;
        this.dialogues = dialogues;
        this.backgroundPath = backgroundPath;
        this.characters = new ArrayList<>();
        
        
        for (Character character : characters) {
            this.characters.add(character);
        }
        
        this.choices = new ArrayList<>();
        actions = new ArrayList<>();
    }

    public int getId() {
        return id;
    }
    
   public List<Character> getCharacters() {
        return characters;
    }
   
    public List<Dialogue> getDialogues() {
        return dialogues;
    }
    
    public void addChoice(Choice choice) {

        choices.add(choice);
    }
    public List<Choice> getChoices() {

        return choices;
    }
    
    public void addAction(Action action) {
        actions.add(action);
    }
    
    public List<Action> getActions() {

        return actions;
    }
    
    public String getBackgroundPath() {
        return backgroundPath;
    }
}

