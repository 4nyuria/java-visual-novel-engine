package com.anyuria.vnengine.engine.resource;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ResourceLoader {

    public static BufferedImage loadImage(String path) {

        try {

            InputStream inputStream =
                    ResourceLoader.class.getResourceAsStream(path);

            if (inputStream == null) {

                System.out.println(
                        "No se encontró el recurso: " + path
                );

                return null;
            }

            return ImageIO.read(inputStream);

        } catch (IOException e) {

            e.printStackTrace();

            return null;
        }
    }
}