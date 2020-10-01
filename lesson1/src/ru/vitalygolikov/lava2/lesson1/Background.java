package ru.vitalygolikov.lava2.lesson1;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Background extends JPanel {

    private Color color;
    private float red;
    private float green;
    private float blue;

    private int changeSpeedRed;
    private int changeSpeedGreen;
    private int changeSpeedBlue;
    float[] comps;

    GameCanvas canvas;

    public Background(GameCanvas canvas)
    {
        this.canvas = canvas;
        Random random = new Random();

        red = (float)random.nextInt(255);
        green = (float)random.nextInt(255);
        blue = (float)random.nextInt(255);

        changeSpeedRed = 10 + random.nextInt(255);
        changeSpeedGreen = 10 + random.nextInt(255);
        changeSpeedBlue = 10 + random.nextInt(255);
    }

    public Color getColor()
    {
        return color;
    }


    public void update(float deltaTime)
    {
        red += changeSpeedRed*deltaTime;
        if(red >= 255)
        {
            changeSpeedRed *= -1;
            red = 255;
        }
        else if(red < 0)
        {
            red = 0;
            changeSpeedRed *= -1;
        }

        green += changeSpeedGreen*deltaTime;
        if(green >= 255)
        {
            changeSpeedGreen *= -1;
            green = 255;
        }
        else if(green < 0)
        {
            green = 0;
            changeSpeedGreen *= -1;
        }

        blue += changeSpeedBlue*deltaTime;
        if(blue >= 255)
        {
            changeSpeedBlue *= -1;
            blue = 255;
        }
        else if(blue < 0)
        {
            blue = 0;
            changeSpeedBlue *= -1;
        }

        color = new Color((int)red, (int)green, (int)blue);
        canvas.setBackground(color);
    }
}
