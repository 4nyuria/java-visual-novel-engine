package com.anyuria.vnengine.character;

public class Character {

    private String name;
    private String imagePath;
    private CharacterPosition position;

    public Character(
            String name,
            String imagePath,
            CharacterPosition position
    ) {

        this.name = name;
        this.imagePath = imagePath;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public CharacterPosition getPosition() {
        return position;
    }
}