package ru.vitalygolikov.lava2.lesson1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainCircles extends JFrame {

    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private final int SPRITES_DEFAULT_COUNT = 10;

    Sprite[] sprites = new Sprite[SPRITES_DEFAULT_COUNT*2];
    private int spritesCount;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainCircles();
            }
        });
    }

    private MainCircles() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        GameCanvas canvas = new GameCanvas(this);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if(e.getButton() == MouseEvent.BUTTON1)
                {
                    addBall( new Ball(e.getX(), e.getY()) );
                }
                else if(e.getButton() == MouseEvent.BUTTON3)
                {
                    removeBall();
                }
            }
        });
        initApplication();
        add(canvas);
        setResizable(false);
        setTitle("Circles");
        setVisible(true);
    }

    private void initApplication() {
        spritesCount = SPRITES_DEFAULT_COUNT;
        for (int i = 0; i < spritesCount; i++) {
            sprites[i] = new Ball();
        }
    }

    void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime); // obnovlenie // S = v * t
        render(canvas, g);         // otrisovka

    }

    private void addBall(Ball ball)
    {
        if( ++spritesCount > sprites.length )
        {

            Sprite[] tmp = new Ball[spritesCount*2];

            for(int i = 0; i < spritesCount - 1; i++)
            {
                tmp[i] = sprites[i];
            }
        }

        sprites[spritesCount - 1] = ball;

    }

    private  void removeBall()
    {
        if( spritesCount > 0)
        {
            spritesCount--;
        }

    }

    private void update(GameCanvas canvas, float deltaTime) {
        for (int i = 0; i < spritesCount; i++) {
            sprites[i].update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        for (int i = 0; i < spritesCount; i++) {
            sprites[i].render(canvas, g);
        }
    }

}
