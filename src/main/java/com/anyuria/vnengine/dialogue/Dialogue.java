package com.anyuria.vnengine.dialogue;

import com.anyuria.vnengine.character.Character;

public class Dialogue {

    private Character speaker;
    private String text;

    public Dialogue(Character speaker, String text) {

        this.speaker = speaker;
        this.text = text;
    }

    public Character getSpeaker() {
        return speaker;
    }

    public String getText() {
        return text;
    }
}