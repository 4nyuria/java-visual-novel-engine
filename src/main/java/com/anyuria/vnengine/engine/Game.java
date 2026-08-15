package com.anyuria.vnengine.engine;

import javax.swing.JFrame;

import com.anyuria.vnengine.scene.SceneManager;

public class Game extends JFrame {

    private static final long serialVersionUID = 1L;
    /*Identificador de versión de una clase serializable,
     * para comprobar que una clase que fue guardada/serializada
     *  sea compatible con la versión de la clase 
     * que intenta leerla posteriormente */

    public Game(SceneManager sceneManager) {

        setTitle("Java Visual Novel Engine");

        setSize(1280, 720);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);

        setVisible(true);
        
        GamePanel gamePanel = new GamePanel(sceneManager);

        add(gamePanel);

        setVisible(true);
    }
}