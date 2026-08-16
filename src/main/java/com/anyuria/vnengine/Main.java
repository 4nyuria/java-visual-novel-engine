package com.anyuria.vnengine;

import javax.swing.SwingUtilities;

import java.util.Arrays;
import com.anyuria.vnengine.character.Character;
import com.anyuria.vnengine.character.CharacterPosition;
import com.anyuria.vnengine.engine.Game;
import com.anyuria.vnengine.scene.Scene;
import com.anyuria.vnengine.scene.SceneManager;
import com.anyuria.vnengine.dialogue.Dialogue;
import com.anyuria.vnengine.choice.Choice;

public class Main {

    public static void main(String[] args) {

        SceneManager sceneManager = new SceneManager();
        //characters
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
        //dialogos
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
                "Entonces Este es un texto bastante"
                + " largo para comprobar que el"
                + " motor puede "
                + "dividir automáticamente el diálogo"
                + " en varias líneas sin que el texto "
                + "se salga de la caja xddd676767"
        );
        //nuevas escenas
        Scene scene1 = new Scene(
                1,
                Arrays.asList(dialogue1,
                		dialogue2),
                "/backgrounds/room.jpg",
                Arrays.asList(protagonist)
        );
        //opciones en escena 1
        scene1.addChoice(
                new Choice(
                        "Investigar la casa",
                        2
                )
        );

        scene1.addChoice(
                new Choice(
                        "Salir a la calle",
                        3
                )
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

//scene manager
        sceneManager.addScene(scene1);
        sceneManager.addScene(scene2);
        sceneManager.addScene(scene3);

        SwingUtilities.invokeLater(() -> {
            new Game(sceneManager);
        });
    }
}