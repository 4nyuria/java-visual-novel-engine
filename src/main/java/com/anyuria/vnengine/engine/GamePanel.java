package com.anyuria.vnengine.engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.anyuria.vnengine.scene.Scene;
import com.anyuria.vnengine.scene.SceneManager;

public class GamePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private SceneManager sceneManager;

    public GamePanel(SceneManager sceneManager) {

        this.sceneManager = sceneManager;

        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Fondo de prueba
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Obtener escena actual
        Scene currentScene = sceneManager.getCurrentScene();

        if (currentScene != null) {

            // Caja de diálogo :3
            g2.setColor(new Color(0, 0, 0, 200));

            g2.fillRect(
                    50,
                    getHeight() - 180,
                    getWidth() - 100,
                    120
            );

            // Texto
            g2.setColor(Color.WHITE);

            g2.setFont(new Font("Arial", Font.PLAIN, 24));

            g2.drawString(
                    currentScene.getText(),
                    80,
                    getHeight() - 120
            );
        }

        g2.dispose();
    }
}