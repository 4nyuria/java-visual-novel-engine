package com.anyuria.vnengine.character;

public class Character {

    private String name;
    private String imagePath;

    public Character(String name, String imagePath) {

        this.name = name;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }
}