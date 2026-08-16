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

    public boolean goToScene(int sceneId) {

        for (int i = 0; i < scenes.size(); i++) {

            if (scenes.get(i).getId() == sceneId) {

                currentSceneIndex = i;

                return true;
            }
        }

        return false;
    }
    public int getNextSceneId() {

        if (!hasNextScene()) {
            return -1;
        }

        return scenes
                .get(currentSceneIndex + 1)
                .getId();
    }
}
