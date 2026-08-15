package com.anyuria.vnengine.engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import com.anyuria.vnengine.scene.Scene;
import com.anyuria.vnengine.scene.SceneManager;

public class GamePanel extends JPanel implements KeyListener {

    private static final long serialVersionUID = 1L;

    private SceneManager sceneManager;

    public GamePanel(SceneManager sceneManager) {

        this.sceneManager = sceneManager;

        setBackground(Color.BLACK);

        setFocusable(true);

        addKeyListener(this);

        requestFocusInWindow();
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

            // Caja de diálogo
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

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_SPACE
                || e.getKeyCode() == KeyEvent.VK_ENTER) {

            if (sceneManager.hasNextScene()) {

                sceneManager.nextScene();

                repaint();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No necesitamos utilizar este método.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // No necesitamos utilizar este método.
    }
}