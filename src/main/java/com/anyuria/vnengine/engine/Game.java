package com.anyuria.vnengine.engine;

import javax.swing.JFrame;

import com.anyuria.vnengine.scene.SceneManager;

public class Game extends JFrame {

    private static final long serialVersionUID = 1L;

    public Game(SceneManager sceneManager) {

        setTitle("Java Visual Novel Engine");

        setSize(1280, 720);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);

        setVisible(true);
    }
}