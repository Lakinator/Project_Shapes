package RenderGUI;

import javax.swing.*;
import java.awt.*;

/**
 * 25.02.2017
 * Created by user Schalk (Lukas Schalk).
 */

class DrawPanel extends JPanel {
    boolean drawRechteck = false;
    boolean drawKreis = false;
    boolean drawDreieck = false;
    boolean drawKugel = false;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(ShapeSettingsWindow.color);

        if (drawRechteck) g.fillRect(10, 10, (int) ShapeRenderWindow.renderShape.getVariables()[1], (int) ShapeRenderWindow.renderShape.getVariables()[0]);
        if (drawKreis) g.fillOval(10, 10, (int) ShapeRenderWindow.renderShape.getVariables()[0], (int) ShapeRenderWindow.renderShape.getVariables()[0]);
        if (drawDreieck) System.out.println("Coming Soon!");
        if (drawKugel) System.out.println("Coming Soon!");
    }
}
