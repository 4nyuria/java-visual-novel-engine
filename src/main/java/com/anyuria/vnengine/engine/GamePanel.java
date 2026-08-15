package com.anyuria.vnengine.engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//para timer de texto
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.image.BufferedImage;
import com.anyuria.vnengine.engine.resource.ResourceLoader;
import com.anyuria.vnengine.scene.Scene;
import com.anyuria.vnengine.scene.SceneManager;

public class GamePanel extends JPanel implements KeyListener {

    private static final long serialVersionUID = 1L;

    private SceneManager sceneManager;

    private String displayedText = "";
    private int characterIndex = 0;

    private Timer textTimer;
    
    private BufferedImage backgroundImage;

    public GamePanel(SceneManager sceneManager) {

        this.sceneManager = sceneManager;

        setBackground(Color.BLACK);

        setFocusable(true);

        addKeyListener(this);

        requestFocusInWindow();
        
        backgroundImage = ResourceLoader.loadImage(
                "/backgrounds/room.jpg"
        );


        startText();
    }

    private void startText() {

        Scene currentScene = sceneManager.getCurrentScene();

        if (currentScene == null) {
            return;
        }

        displayedText = "";
        characterIndex = 0;

        String text = currentScene.getText();
        

        textTimer = new Timer(50, e -> {
        	//timer del texto para efecto maquina de escribir

            if (characterIndex < text.length()) {

                displayedText += text.charAt(characterIndex);

                characterIndex++;

                repaint();

            } else {

                textTimer.stop();
            }
        });

        textTimer.start();
    }

    private boolean isTextComplete() {

        Scene currentScene = sceneManager.getCurrentScene();

        if (currentScene == null) {
            return true;
        }

        return characterIndex >= currentScene.getText().length();
    }

    private void finishText() {

        Scene currentScene = sceneManager.getCurrentScene();

        if (currentScene == null) {
            return;
        }

        displayedText = currentScene.getText();

        characterIndex = displayedText.length();

        if (textTimer != null) {
            textTimer.stop();
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Fondo
        if (backgroundImage != null) {

            g2.drawImage(
            		backgroundImage,
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    null
            );

        } else {

            g2.setColor(Color.DARK_GRAY);

            g2.fillRect(
                    0,
                    0,
                    getWidth(),
                    getHeight()
            );
        }
        // Escena actual
        Scene currentScene = sceneManager.getCurrentScene();

        if (currentScene != null) {

            // Caja de dialogos
            g2.setColor(new Color(0, 0, 0, 200));

            g2.fillRect(
                    50,
                    getHeight() - 180,
                    getWidth() - 100,
                    120
            );

            // Texto
            g2.setColor(Color.WHITE);

            g2.setFont(
                    new Font(
                            "Arial",
                            Font.PLAIN,
                            24
                    )
            );

            g2.drawString(
                    displayedText,
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

            // si todavia hay texto...
            if (!isTextComplete()) {

                finishText();

                return;
            }

            // si se termino, pasar a la otra escena
            if (sceneManager.hasNextScene()) {

                sceneManager.nextScene();

                startText();

                repaint();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}