package RenderGUI;

import Shapes.Shape;

import javax.swing.*;

/**
 * 25.02.2017
 * Created by user Schalk (Lukas Schalk).
 */

public class ShapeRenderWindow {
    private JFrame jf;
    private int width = 350, height = 300;
    private DrawPanel dr;

    static Shape renderShape;

    public ShapeRenderWindow(Shape renderShape) {
        jf = new JFrame();

        jf.setTitle("Render Window");
        jf.setSize(width, height);
        jf.setLayout(null);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setLocationRelativeTo(null);

        System.out.println("Render Shape: " + renderShape.getClass().getName() + "@" + renderShape.toString());

        dr = new DrawPanel();
        dr.setBounds(0, 0, width, height);
        jf.add(dr);

        ShapeRenderWindow.renderShape = renderShape;

        switch (renderShape.getClass().getName()) {
            default:
                System.out.println("Unbekannte Render Shape");
                break;
            case "Shapes.Rechteck":
                dr.drawRechteck = true;
                break;
            case "Shapes.Kreis":
                dr.drawKreis = true;
                break;
            case "Shapes.Dreieck":
                dr.drawDreieck = true;
                break;
            case "Shapes.Kugel":
                dr.drawKugel = true;
                break;
            case "Shapes.Rechteck3D":
                dr.draw3DRechteck = true;
                break;
        }


        jf.setVisible(true);
    }
}
