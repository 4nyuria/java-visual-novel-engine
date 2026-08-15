package com.anyuria.vnengine.scene;

public class Scene {

    private int id;
    private String text;
    private String backgroundPath;

    public Scene(int id, String text, String backgroundPath) {

        this.id = id;
        this.text = text;
        this.backgroundPath = backgroundPath;
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
}