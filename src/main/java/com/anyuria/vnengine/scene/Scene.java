package com.anyuria.vnengine.scene;

public class Scene {

    private int id;
    private String text;

    public Scene(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}