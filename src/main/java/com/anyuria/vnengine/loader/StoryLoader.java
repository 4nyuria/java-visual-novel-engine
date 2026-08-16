package com.anyuria.vnengine.loader;

import java.io.InputStream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import com.anyuria.vnengine.scene.Scene;
import com.anyuria.vnengine.action.Action;
import com.anyuria.vnengine.action.AddItemAction;
import com.anyuria.vnengine.action.SetFlagAction;
import com.anyuria.vnengine.character.Character;
import com.anyuria.vnengine.character.CharacterPosition;
import com.anyuria.vnengine.dialogue.Dialogue;
import com.anyuria.vnengine.item.Item;
import com.anyuria.vnengine.choice.Choice;

public class StoryLoader {

    private ObjectMapper mapper;

    public StoryLoader() {

        mapper = new ObjectMapper();
    }

    public List<Scene> loadScenes() {

        List<Scene> scenes = new ArrayList<>();

        try {

            InputStream input =
                    getClass()
                            .getResourceAsStream(
                                    "/story/story.json"
                            );

            if (input == null) {

                System.out.println(
                        "No se encontró story.json"
                );

                return scenes;
            }

            JsonNode root =
                    mapper.readTree(input);

            // ==========================================
            // ITEMS
            // ==========================================

            java.util.Map<String, Item> items =
                    new java.util.HashMap<>();

            JsonNode itemsNode =
                    root.get("items");

            if (itemsNode != null) {

                for (JsonNode itemNode : itemsNode) {

                    String itemId =
                            itemNode
                                    .get("id")
                                    .asText();

                    String itemName =
                            itemNode
                                    .get("name")
                                    .asText();

                    String description =
                            itemNode
                                    .get("description")
                                    .asText();

                    Item item =
                            new Item(
                                    itemId,
                                    itemName,
                                    description
                            );

                    items.put(itemId, item);
                }
            }

            // ==========================================
            // ESCENAS
            // ==========================================

            JsonNode scenesNode =
                    root.get("scenes");

            if (scenesNode == null) {

                System.out.println(
                        "No existe 'scenes' en story.json"
                );

                return scenes;
            }

            for (JsonNode sceneNode : scenesNode) {

                int id =
                        sceneNode
                                .get("id")
                                .asInt();

                String background =
                        sceneNode
                                .get("background")
                                .asText();

                // ==========================================
                // PERSONAJES
                // ==========================================

                List<Character> characters =
                        new ArrayList<>();

                JsonNode charactersNode =
                        sceneNode.get("characters");

                if (charactersNode != null) {

                    for (JsonNode characterNode
                            : charactersNode) {

                        String characterId =
                                characterNode
                                        .get("id")
                                        .asText();

                        String image =
                                characterNode
                                        .get("image")
                                        .asText();

                        String positionString =
                                characterNode
                                        .get("position")
                                        .asText();

                        CharacterPosition position =
                                CharacterPosition.valueOf(
                                        positionString
                                );

                        Character character =
                                new Character(
                                        characterId,
                                        image,
                                        position
                                );

                        characters.add(character);
                    }
                }

                // ==========================================
                // DIÁLOGOS
                // ==========================================

                List<Dialogue> dialogues =
                        new ArrayList<>();

                JsonNode dialoguesNode =
                        sceneNode.get("dialogues");

                if (dialoguesNode != null) {

                    for (JsonNode dialogueNode
                            : dialoguesNode) {

                        String speakerId =
                                dialogueNode
                                        .get("speaker")
                                        .asText();

                        String text =
                                dialogueNode
                                        .get("text")
                                        .asText();

                        Character speaker = null;

                        for (Character character
                                : characters) {

                            if (character
                                    .getName()
                                    .equals(speakerId)) {

                                speaker = character;

                                break;
                            }
                        }

                        if (speaker != null) {

                            Dialogue dialogue =
                                    new Dialogue(
                                            speaker,
                                            text
                                    );

                            dialogues.add(dialogue);
                        }
                    }
                }

                // ==========================================
                // OPCIONES
                // ==========================================

                List<Choice> choices =
                        new ArrayList<>();

                JsonNode choicesNode =
                        sceneNode.get("choices");

                if (choicesNode != null) {

                    for (JsonNode choiceNode
                            : choicesNode) {

                        String text =
                                choiceNode
                                        .get("text")
                                        .asText();

                        int targetScene =
                                choiceNode
                                        .get("targetScene")
                                        .asInt();

                        String requiredFlag = null;
                        String requiredItem = null;

                        if (choiceNode.has("requiredFlag")) {

                            requiredFlag =
                                    choiceNode
                                            .get("requiredFlag")
                                            .asText();
                        }

                        if (choiceNode.has("requiredItem")) {

                            requiredItem =
                                    choiceNode
                                            .get("requiredItem")
                                            .asText();
                        }

                        Choice choice =
                                new Choice(
                                        text,
                                        targetScene,
                                        requiredFlag,
                                        requiredItem
                                );

                        choices.add(choice);
                    }
                }

                // ==========================================
                // CREAR ESCENA
                // ==========================================

                Scene scene =
                        new Scene(
                                id,
                                dialogues,
                                background,
                                characters
                        );

                // Agregar choices

                for (Choice choice : choices) {

                    scene.addChoice(choice);
                }

                // ==========================================
                // ACCIONES
                // ==========================================

                JsonNode actionsNode =
                        sceneNode.get("actions");

                if (actionsNode != null) {

                    for (JsonNode actionNode
                            : actionsNode) {

                        String type =
                                actionNode
                                        .get("type")
                                        .asText();

                        Action action = null;

                        // ------------------------------
                        // SET FLAG
                        // ------------------------------

                        if (type.equals("setFlag")) {

                            String flag =
                                    actionNode
                                            .get("flag")
                                            .asText();

                            boolean value =
                                    actionNode
                                            .get("value")
                                            .asBoolean();

                            action =
                                    new SetFlagAction(
                                            flag,
                                            value
                                    );
                        }

                        // ------------------------------
                        // ADD ITEM
                        // ------------------------------

                        else if (type.equals("addItem")) {

                            String itemId =
                                    actionNode
                                            .get("item")
                                            .asText();

                            Item item =
                                    items.get(itemId);

                            if (item != null) {

                                action =
                                        new AddItemAction(
                                                item
                                        );
                            }
                        }

                        if (action != null) {

                            scene.addAction(action);
                        }
                    }
                }

                scenes.add(scene);

                System.out.println(
                        "Escena creada: " + id
                );

                System.out.println(
                        "  Diálogos: "
                        + dialogues.size()
                );

                System.out.println(
                        "  Personajes: "
                        + characters.size()
                );

                System.out.println(
                        "  Choices: "
                        + choices.size()
                );

                if (actionsNode != null) {

                    System.out.println(
                            "  Acciones: "
                            + actionsNode.size()
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "ERROR CARGANDO STORY.JSON"
            );

            e.printStackTrace();
        }

        return scenes;
    }
}
