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

import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import com.anyuria.vnengine.engine.resource.ResourceLoader;

import com.anyuria.vnengine.scene.Scene;
import com.anyuria.vnengine.scene.SceneManager;
import com.anyuria.vnengine.character.CharacterPosition;

public class GamePanel extends JPanel implements KeyListener {

    private static final long serialVersionUID = 1L;

    private SceneManager sceneManager;

    private String displayedText = "";
    private int characterIndex = 0;
    private int dialogueIndex = 0;
    
    private boolean showContinueIndicator = false;
    private boolean indicatorVisible = true;

    private Timer textTimer;
    private Timer indicatorTimer;
    
    private BufferedImage backgroundImage;
    
    private List<BufferedImage> characterImages;
    

    public GamePanel(SceneManager sceneManager) {

        this.sceneManager = sceneManager;

        setBackground(Color.BLACK);

        setFocusable(true);

        addKeyListener(this);

        requestFocusInWindow();
        
        loadBackground();
        
        loadCharacters();

        startText();
        startIndicatorAnimation();
        
        repaint();
    }
    

    private void loadBackground() {

        Scene currentScene = sceneManager.getCurrentScene();

        if (currentScene == null) {
            return;
        }

        backgroundImage = ResourceLoader.loadImage(
                currentScene.getBackgroundPath()
        );
    }
    
    private void loadCharacters() {

        Scene currentScene = sceneManager.getCurrentScene();

        if (currentScene == null) {
            return;
        }

        characterImages = new ArrayList<>();

        for (com.anyuria.vnengine.character.Character character
                : currentScene.getCharacters()) {

            BufferedImage image =
                    ResourceLoader.loadImage(
                            character.getImagePath()
                    );

            characterImages.add(image);
        }
    }
    
    private void startText() {

        Scene currentScene = sceneManager.getCurrentScene();

        if (currentScene == null) {
            return;
        }

        if (currentScene.getDialogues().isEmpty()) {
            return;
        }

        displayedText = "";
        characterIndex = 0;
        
        showContinueIndicator = false;
        indicatorVisible = true;

        String text = currentScene
                .getDialogues()
                .get(dialogueIndex)
                .getText();

        textTimer = new Timer(50, e -> {

            // Efecto máquina de escribir
            if (characterIndex < text.length()) {

                displayedText += text.charAt(characterIndex);

                characterIndex++;

                repaint();

            } else {

                textTimer.stop();
                showContinueIndicator = true;

                repaint();
            }
        });

        textTimer.start();
    }

    private boolean isTextComplete() {

        Scene currentScene = sceneManager.getCurrentScene();

        if (currentScene == null) {
            return true;
        }

        if (currentScene.getDialogues().isEmpty()) {
            return true;
        }

        String text = currentScene
                .getDialogues()
                .get(dialogueIndex)
                .getText();

        return characterIndex >= text.length();
    }

    private void finishText() {

        Scene currentScene = sceneManager.getCurrentScene();

        if (currentScene == null) {
            return;
        }

        if (currentScene.getDialogues().isEmpty()) {
            return;
        }

        displayedText = currentScene
                .getDialogues()
                .get(dialogueIndex)
                .getText();

        characterIndex = displayedText.length();
        
        showContinueIndicator = true;
        
        if (textTimer != null) {
            textTimer.stop();
        }

        repaint();
    }
    
    private void drawWrappedText(
            Graphics2D g2,
            String text,
            int x,
            int y,
            int maxWidth
    ) {

        String[] words = text.split(" ");

        String line = "";
        int lineHeight = 30;

        for (String word : words) {

            String testLine = line.isEmpty()
                    ? word
                    : line + " " + word;

            int textWidth = g2.getFontMetrics()
                    .stringWidth(testLine);

            if (textWidth > maxWidth && !line.isEmpty()) {

                g2.drawString(
                        line,
                        x,
                        y
                );

                line = word;
                y += lineHeight;

            } else {

                line = testLine;
            }
        }

        if (!line.isEmpty()) {

            g2.drawString(
                    line,
                    x,
                    y
            );
        }
    }
    
    private void startIndicatorAnimation() {

        indicatorTimer = new Timer(500, e -> {

        	if (showContinueIndicator && indicatorVisible) {
                indicatorVisible = !indicatorVisible;

                repaint();
            }
        });

        indicatorTimer.start();
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
        
     // Personajes
        Scene currentScene = sceneManager.getCurrentScene();

        if (currentScene != null && characterImages != null) {

            List<com.anyuria.vnengine.character.Character> characters =
                    currentScene.getCharacters();

            for (int i = 0; i < characters.size(); i++) {

                com.anyuria.vnengine.character.Character character =
                        characters.get(i);

                BufferedImage image = characterImages.get(i);

                if (image == null) {
                    continue;
                }

                int characterWidth = 400;
                int characterHeight = 600;

                int characterX;

                CharacterPosition position =
                        character.getPosition();

                if (position == CharacterPosition.LEFT) {

                    characterX = 50;

                } else if (position == CharacterPosition.RIGHT) {

                    characterX = getWidth() - characterWidth - 50;

                } else {

                    characterX =
                            (getWidth() - characterWidth) / 2;
                }

                int characterY =
                        getHeight() - characterHeight;

                g2.drawImage(
                        image,
                        characterX,
                        characterY,
                        characterWidth,
                        characterHeight,
                        null
                );
            }
        }
        
        // Escena actual
        if (currentScene != null) {

        	// Caja principal de diálogo
        	int dialogueBoxX = 50;
        	int dialogueBoxY = getHeight() - 180;
        	int dialogueBoxWidth = getWidth() - 100;
        	int dialogueBoxHeight = 120;

        	g2.setColor(new Color(0, 0, 0, 210));

        	g2.fillRoundRect(
        	        dialogueBoxX,
        	        dialogueBoxY,
        	        dialogueBoxWidth,
        	        dialogueBoxHeight,
        	        20,
        	        20
        	);
        	
        	// Placa del nombre
        	if (!currentScene.getDialogues().isEmpty()
        	        && currentScene.getDialogues()
        	                .get(dialogueIndex)
        	                .getSpeaker() != null) {

        	    String speakerName = currentScene
        	            .getDialogues()
        	            .get(dialogueIndex)
        	            .getSpeaker()
        	            .getName();

        	    int nameBoxX = 70;
        	    int nameBoxY = dialogueBoxY - 25;
        	    int nameBoxWidth = 180;
        	    int nameBoxHeight = 40;

        	    g2.setColor(new Color(30, 30, 30, 230));

        	    g2.fillRoundRect(
        	            nameBoxX,
        	            nameBoxY,
        	            nameBoxWidth,
        	            nameBoxHeight,
        	            15,
        	            15
        	    );

        	    g2.setColor(Color.WHITE);

        	    g2.setFont(
        	            new Font(
        	                    "Arial",
        	                    Font.BOLD,
        	                    18
        	            )
        	    );

        	    g2.drawString(
        	            speakerName,
        	            nameBoxX + 15,
        	            nameBoxY + 26
        	    );
        	}


            // Texto del diálogo
            g2.setColor(Color.WHITE);

            g2.setFont(
                    new Font(
                            "Arial",
                            Font.PLAIN,
                            24
                    )
            );

            drawWrappedText(
                    g2,
                    displayedText,
                    80,
                    getHeight() - 120,
                    getWidth() - 160
            );
            
            if (showContinueIndicator&& indicatorVisible) {

                g2.setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                20
                        )
                );

                g2.setColor(Color.WHITE);

                g2.drawString(
                        "▼",
                        getWidth() - 85,
                        getHeight() - 75
                );
                }
            }
     
    }

   
    private boolean hasNextDialogue() {

        Scene currentScene = sceneManager.getCurrentScene();

        if (currentScene == null) {
            return false;
        }

        return dialogueIndex < currentScene.getDialogues().size() - 1;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_SPACE
                || e.getKeyCode() == KeyEvent.VK_ENTER) {

            // Si el texto todavía se está escribiendo
            if (!isTextComplete()) {

                finishText();

                return;
            }

            // Si hay otro diálogo dentro de la escena
            if (hasNextDialogue()) {

                dialogueIndex++;

                startText();

                repaint();

                return;
            }

            // Si no hay más diálogos, pasar a la siguiente escena
            if (sceneManager.hasNextScene()) {

                sceneManager.nextScene();

                dialogueIndex = 0;

                loadBackground();

                loadCharacters();

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