package com.anyuria.vnengine;

import java.util.List;

import javax.swing.SwingUtilities;

import com.anyuria.vnengine.engine.Game;
import com.anyuria.vnengine.loader.StoryLoader;
import com.anyuria.vnengine.scene.Scene;
import com.anyuria.vnengine.scene.SceneManager;

public class Main {

    public static void main(String[] args) {

        // ==========================================
        // CARGAR HISTORIA DESDE JSON
        // ==========================================

        StoryLoader storyLoader =
                new StoryLoader();

        List<Scene> loadedScenes =
                storyLoader.loadScenes();

        System.out.println(
                "Escenas creadas: "
                + loadedScenes.size()
        );

        // ==========================================
        // CREAR SCENE MANAGER
        // ==========================================

        SceneManager sceneManager =
                new SceneManager();

        // ==========================================
        // AGREGAR ESCENAS CARGADAS
        // ==========================================

        for (Scene scene : loadedScenes) {

            sceneManager.addScene(scene);

            System.out.println(
                    "Escena: "
                    + scene.getId()
            );

            System.out.println(
                    "Diálogos: "
                    + scene.getDialogues().size()
            );

            System.out.println(
                    "Personajes: "
                    + scene.getCharacters().size()
            );

            System.out.println(
                    "Choices: "
                    + scene.getChoices().size()
            );
        }

        // ==========================================
        // INICIAR JUEGO
        // ==========================================

        SwingUtilities.invokeLater(() -> {

            new Game(sceneManager);

        });
    }
}
