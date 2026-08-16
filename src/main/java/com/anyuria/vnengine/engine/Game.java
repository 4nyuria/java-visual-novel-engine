package com.anyuria.vnengine.engine;

import javax.swing.JFrame;

import com.anyuria.vnengine.scene.SceneManager;
import com.anyuria.vnengine.state.GameState;

public class Game extends JFrame {

    private GameState gameState;

    private static final long serialVersionUID = 1L;

    public Game(SceneManager sceneManager) {

        gameState = new GameState();

        setTitle("Java Visual Novel Engine");

        setSize(1280, 720);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);

        GamePanel gamePanel =
                new GamePanel(sceneManager, gameState);

        add(gamePanel);

        setVisible(true);

        gamePanel.requestFocusInWindow();
    }
}