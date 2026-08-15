package com.anyuria.vnengine;

import javax.swing.SwingUtilities;
import java.util.Arrays;
import com.anyuria.vnengine.character.Character;
import com.anyuria.vnengine.character.CharacterPosition;
import com.anyuria.vnengine.engine.Game;
import com.anyuria.vnengine.scene.Scene;
import com.anyuria.vnengine.scene.SceneManager;
import com.anyuria.vnengine.dialogue.Dialogue;
public class Main {

    public static void main(String[] args) {

        SceneManager sceneManager = new SceneManager();
        Character protagonist = new Character(
                "cat",
                "/characters/cat.png",
                CharacterPosition.CENTER
        );
        Character otherCharacter = new Character(
                "other",
                "/characters/other.png",
                CharacterPosition.RIGHT
        );
        Dialogue dialogue1 = new Dialogue(
                protagonist,
                "¿Dónde estoy?"
        );
        Dialogue dialogue2 = new Dialogue(
                otherCharacter,
                "No lo sé..."
        );

        Dialogue dialogue3 = new Dialogue(
                protagonist,
                "Entonces tendremos que averiguarlo."
        );
        
        Scene scene1 = new Scene(
                1,
                Arrays.asList(dialogue1,
                		dialogue2),
                "/backgrounds/room.jpg",
                Arrays.asList(protagonist)
        );

        Scene scene2 = new Scene(
                2,Arrays.asList(),
                "/backgrounds/window.jpeg",
                Arrays.asList(protagonist,
                		otherCharacter)
        );

        Scene scene3 = new Scene(
                3,Arrays.asList(dialogue3),
                "/backgrounds/street.jpeg",
                Arrays.asList(protagonist,
                		otherCharacter)
        );

        sceneManager.addScene(scene1);
        sceneManager.addScene(scene2);
        sceneManager.addScene(scene3);

        SwingUtilities.invokeLater(() -> {
            new Game(sceneManager);
        });
    }
}