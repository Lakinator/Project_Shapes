package RenderGUI;

import Shapes.Kreis;
import Shapes.Rechteck;

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

        g.setColor(Color.RED);

        if (drawRechteck) g.fillRect(10, 10, (int) ( (Rechteck) ShapeRenderer.renderShape).getLength(), (int) ( (Rechteck) ShapeRenderer.renderShape).getWidth());
        if (drawKreis) g.fillOval(10, 10, (int) ( (Kreis) ShapeRenderer.renderShape).getRadius(), (int) ( (Kreis) ShapeRenderer.renderShape).getRadius());
        if (drawDreieck) System.out.println("Coming Soon!");
        if (drawKugel) System.out.println("Coming Soon!");
    }
}
