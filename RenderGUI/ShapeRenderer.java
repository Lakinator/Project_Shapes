package RenderGUI;

import Shapes.*;

import javax.swing.*;

/**
 * 25.02.2017
 * Created by user Schalk (Lukas Schalk).
 */

public class ShapeRenderer {
    private JFrame jf;
    private int width = 350, height = 300;
    private DrawPanel dr;

    static Shape renderShape;

    public ShapeRenderer(Shape renderShape) {
        jf = new JFrame();

        jf.setTitle("Render Window");
        jf.setSize(width, height);
        jf.setLayout(null);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setLocationRelativeTo(null);

        System.out.println("Render Shape: " + renderShape.getClass().getName());

        dr = new DrawPanel();
        dr.setBounds(0, 0, width, height);
        jf.add(dr);

        ShapeRenderer.renderShape = renderShape;

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
        }


        jf.setVisible(true);
    }
}
