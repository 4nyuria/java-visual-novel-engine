package com.anyuria.vnengine.loader;

import java.io.InputStream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import com.anyuria.vnengine.scene.Scene;

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

            JsonNode scenesNode =
                    root.get("scenes");

            for (JsonNode sceneNode : scenesNode) {

                int id =
                        sceneNode
                                .get("id")
                                .asInt();

                String background =
                        sceneNode
                                .get("background")
                                .asText();

                /*
                 * Por ahora creamos la escena
                 * sin diálogos.
                 *
                 * Los diálogos los agregaremos
                 * en el siguiente paso.
                 */

                Scene scene = new Scene(
                        id,
                        new ArrayList<>(),
                        background,
                        new ArrayList<>()
                );

                scenes.add(scene);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return scenes;
    }
}
