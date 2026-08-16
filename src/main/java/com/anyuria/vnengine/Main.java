package com.anyuria.vnengine;

import java.util.List;

import javax.swing.SwingUtilities;

import com.anyuria.vnengine.engine.Game;
import com.anyuria.vnengine.loader.StoryLoader;
import com.anyuria.vnengine.scene.Scene;
import com.anyuria.vnengine.scene.SceneManager;

public class Main {

    public static void main(String[] args) {

        // CARGAR HISTORIA DESDE JSON

        StoryLoader storyLoader = new StoryLoader();

        List<Scene> scenes =
                storyLoader.loadScenes();

        System.out.println(
                "Escenas cargadas: "
                + scenes.size()
        );

        // CREAR SCENE MANAGER

        SceneManager sceneManager =
                new SceneManager();

        // AGREGAR ESCENAS

        for (Scene scene : scenes) {

            sceneManager.addScene(scene);

            System.out.println(
                    "Escena agregada: "
                    + scene.getId()
            );
        }

        // INICIAR JUEGO
        SwingUtilities.invokeLater(() -> {

            new Game(sceneManager);

        });
    }
}
