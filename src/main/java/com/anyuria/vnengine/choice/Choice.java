package com.anyuria.vnengine.choice;

public class Choice {

    private String text;
    private int targetSceneId;

    public Choice(String text, int targetSceneId) {

        this.text = text;
        this.targetSceneId = targetSceneId;
    }

    public String getText() {
        return text;
    }

    public int getTargetSceneId() {
        return targetSceneId;
    }
}