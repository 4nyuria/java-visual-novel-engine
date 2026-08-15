package com.anyuria.vnengine.scene;

import java.util.ArrayList;
import java.util.List;

public class SceneManager {

    private List<Scene> scenes;
    private int currentSceneIndex;

    public SceneManager() {
        scenes = new ArrayList<>();
        currentSceneIndex = 0;
    }

    public void addScene(Scene scene) {
        scenes.add(scene);
    }

    public Scene getCurrentScene() {
        if (scenes.isEmpty()) {
            return null;
        }

        return scenes.get(currentSceneIndex);
    }

    public void nextScene() {
        if (currentSceneIndex < scenes.size() - 1) {
            currentSceneIndex++;
        }
    }

    public boolean hasNextScene() {
        return currentSceneIndex < scenes.size() - 1;
    }
}