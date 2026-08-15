package com.anyuria.vnengine;

import javax.swing.SwingUtilities;

import com.anyuria.vnengine.engine.Game;
import com.anyuria.vnengine.scene.Scene;
import com.anyuria.vnengine.scene.SceneManager;

public class Main {

    public static void main(String[] args) {

        SceneManager sceneManager = new SceneManager();

        Scene scene1 = new Scene(
                1,
                "Una noche tranquila..."
        );

        Scene scene2 = new Scene(
                2,
                "La protagonista mira por la ventana."
        );

        Scene scene3 = new Scene(
                3,
                "De repente, escucha un ruido."
        );

        sceneManager.addScene(scene1);
        sceneManager.addScene(scene2);
        sceneManager.addScene(scene3);

        SwingUtilities.invokeLater(() -> {
            new Game(sceneManager);
        });
    }
}