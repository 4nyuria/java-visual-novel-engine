package com.anyuria.vnengine.choice;

public class Choice {

    private String text;
    private int targetSceneId;

    private String requiredFlag;

    public Choice(
            String text,
            int targetSceneId) {

        this.text = text;
        this.targetSceneId = targetSceneId;

        this.requiredFlag = null;
    }

    public Choice(
            String text,
            int targetSceneId,
            String requiredFlag) {

        this.text = text;
        this.targetSceneId = targetSceneId;
        this.requiredFlag = requiredFlag;
    }

    public String getText() {
        return text;
    }

    public int getTargetSceneId() {
        return targetSceneId;
    }

    public String getRequiredFlag() {
        return requiredFlag;
    }
}