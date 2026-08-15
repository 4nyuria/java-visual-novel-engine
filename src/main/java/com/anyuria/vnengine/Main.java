package com.anyuria.vnengine;

import javax.swing.SwingUtilities;
import com.anyuria.vnengine.character.Character;
import com.anyuria.vnengine.character.CharacterPosition;
import com.anyuria.vnengine.engine.Game;
import com.anyuria.vnengine.scene.Scene;
import com.anyuria.vnengine.scene.SceneManager;

public class Main {

    public static void main(String[] args) {

        SceneManager sceneManager = new SceneManager();
        Character protagonist = new Character(
                "cat",
                "/characters/cat.png",
                CharacterPosition.CENTER
        );
        Scene scene1 = new Scene(
                1,
                "Una noche tranquila...",
                "/backgrounds/room.jpg",
                protagonist
        );

        Scene scene2 = new Scene(
                2,
                "La protagonista mira por la ventana.",
                "/backgrounds/window.jpeg",
                protagonist
        );

        Scene scene3 = new Scene(
                3,
                "De repente, escucha un ruido.",
                "/backgrounds/street.jpeg",
                protagonist
        );

        sceneManager.addScene(scene1);
        sceneManager.addScene(scene2);
        sceneManager.addScene(scene3);

        SwingUtilities.invokeLater(() -> {
            new Game(sceneManager);
        });
    }
}