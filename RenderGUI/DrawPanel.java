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
    boolean draw3DRechteck = false;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(ShapeSettingsWindow.color);

        if (drawRechteck) g2d.fillRect(10, 10, (int) ShapeRenderWindow.renderShape.getVariables()[1], (int) ShapeRenderWindow.renderShape.getVariables()[0]);
        if (drawKreis) g2d.fillOval(10, 10, (int) ShapeRenderWindow.renderShape.getVariables()[0], (int) ShapeRenderWindow.renderShape.getVariables()[0]);
        if (drawDreieck) {
            String point1 = "";
            int x1 = 50;
            int y1 = 50;

            String point2 = "";
            int x2 = 0;
            int y2 = 0;

            String point3 = "";
            int x3 = 0;
            int y3 = 0;

            if (ShapeRenderWindow.renderShape.getVariables()[3] == 90) {
                point1 = "C";

                x2 = x1;
                y2 = y1 + (int) ShapeRenderWindow.renderShape.getVariables()[1]; //b
                point2 = "A";

                x3 = x1 + (int) ShapeRenderWindow.renderShape.getVariables()[2]; //c
                y3 = y2;
                point3 = "B";
            } else if (ShapeRenderWindow.renderShape.getVariables()[4] == 90) {
                point1 = "A";

                x2 = x1;
                y2 = y1 + (int) ShapeRenderWindow.renderShape.getVariables()[2]; //c
                point2 = "B";

                x3 = x1 + (int) ShapeRenderWindow.renderShape.getVariables()[0]; //a
                y3 = y2;
                point3 = "C";
            } else if (ShapeRenderWindow.renderShape.getVariables()[5] == 90) {
                point1 = "B";

                x2 = x1;
                y2 = y1 + (int) ShapeRenderWindow.renderShape.getVariables()[0]; //a
                point2 = "C";

                x3 = x1 + (int) ShapeRenderWindow.renderShape.getVariables()[1]; //b
                y3 = y2;
                point3 = "A";
            }


            g2d.fillPolygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);
            g2d.setColor(Color.BLACK);
            g2d.drawString(point1, x1-5, y1+5);
            g2d.drawString(point2, x2-5, y2+5);
            g2d.drawString(point3, x3-5, y3+5);
        }
        if (drawKugel) System.out.println("Coming Soon!");
        if (draw3DRechteck) g2d.fill3DRect(20, 20, (int) ShapeRenderWindow.renderShape.getVariables()[1], (int) ShapeRenderWindow.renderShape.getVariables()[2], true);
    }
}
