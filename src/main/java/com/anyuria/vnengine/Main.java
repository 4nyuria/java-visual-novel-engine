package com.anyuria.vnengine;

import javax.swing.SwingUtilities;

import java.util.Arrays;
import java.util.List;

import com.anyuria.vnengine.character.Character;
import com.anyuria.vnengine.character.CharacterPosition;
import com.anyuria.vnengine.engine.Game;
import com.anyuria.vnengine.scene.Scene;
import com.anyuria.vnengine.scene.SceneManager;
import com.anyuria.vnengine.dialogue.Dialogue;
import com.anyuria.vnengine.choice.Choice;
import com.anyuria.vnengine.action.SetFlagAction;
import com.anyuria.vnengine.item.Item;
import com.anyuria.vnengine.loader.StoryLoader;
import com.anyuria.vnengine.action.AddItemAction;
public class Main {

    public static void main(String[] args) {
    
    	StoryLoader storyLoader = new StoryLoader();

    	List<Scene> loadedScenes =
    	        storyLoader.loadScenes();

    	System.out.println(
    	        "Escenas creadas: "
    	        + loadedScenes.size()
    	);
    	
        SceneManager sceneManager = new SceneManager();
        //items
        Item key = new Item(
                "key",
                "Llave",
                "Una vieja llave oxidada."
        );
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
                        "ir a la puerta",
                        2,
                        null,
                        "key"
                )
        );
        scene1.addChoice(
                new Choice(
                        "Salir a la calle",
                        3
                )
        );
        
        //otra escena
        
        Scene scene2 = new Scene(
                2,Arrays.asList(),
                "/backgrounds/window.jpeg",
                Arrays.asList(protagonist,
                		otherCharacter)
        );
        //agregar una accion
        scene2.addAction(
                new SetFlagAction(
                        "foundKey",
                        true
                )
        );

        scene2.addAction(
                new AddItemAction(key)
        );
        //despues otra escena
        Scene scene3 = new Scene(
                3,Arrays.asList(dialogue3),
                "/backgrounds/street.jpeg",
                Arrays.asList(protagonist,
                		otherCharacter)
        );
        scene3.addChoice(
                new Choice(
                        "Abrir la puerta",
                        2,
                        "foundKey"
                )
        );

        scene3.addChoice(
                new Choice(
                        "Alejarse",
                        1
                )
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