package com.anyuria.vnengine.choice;

public class Choice {

    private String text;
    private int targetSceneId;

    private String requiredFlag;
    private String requiredItem;
    private String requiredVariable;
    private int requiredValue;
    
    public Choice(
            String text,
            int targetSceneId,
            String requiredFlag,
            String requiredItem,
            String requiredVariable,
            int requiredValue) {

        this.text = text;
        this.targetSceneId = targetSceneId;

        this.requiredFlag = requiredFlag;
        this.requiredItem = requiredItem;

        this.requiredVariable = requiredVariable;
        this.requiredValue = requiredValue;
    }
    
    // Constructor sin requisitos

    public Choice(
            String text,
            int targetSceneId) {

        this.text = text;
        this.targetSceneId = targetSceneId;

        this.requiredFlag = null;
        this.requiredItem = null;
        this.requiredVariable = null;
        this.requiredValue = 0;
    }

    // Constructor con FLAG

    public Choice(
            String text,
            int targetSceneId,
            String requiredFlag) {

        this.text = text;
        this.targetSceneId = targetSceneId;

        this.requiredFlag = requiredFlag;
        this.requiredItem = null;
        this.requiredVariable = null;
        this.requiredValue = 0;
    }

    // Constructor con FLAG + ITEM

    public Choice(
            String text,
            int targetSceneId,
            String requiredFlag,
            String requiredItem) {

        this.text = text;
        this.targetSceneId = targetSceneId;

        this.requiredFlag = requiredFlag;
        this.requiredItem = requiredItem;
        this.requiredVariable = null;
        this.requiredValue = 0;
    }

    // GETTERS

    public String getText() {

        return text;
    }

    public int getTargetSceneId() {

        return targetSceneId;
    }

    public String getRequiredFlag() {

        return requiredFlag;
    }

    public String getRequiredItem() {

        return requiredItem;
    }
    public String getRequiredVariable() {

        return requiredVariable;
    }

    public int getRequiredValue() {

        return requiredValue;
    }
}