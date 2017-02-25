package Shapes;

import javax.swing.*;

/**
 * 25.02.2017
 * Created by user Schal (Lukas Schalk).
 */

public class Quader implements Shape {
    private final ShapeType type = ShapeType._3D;
    private double width = 0, length = 0, height = 0, volumen = 0, oberflaeche = 0;

    @Override
    public ShapeType getType() {
        return type;
    }

    @Override
    public String[] getVarNames() {
        return new String[]{"Breite", "Länge", "Höhe", "Volumen", "Oberfläche"};
    }

    @Override
    public double[] getVariables() {
        return new double[]{width, length, height, volumen, oberflaeche};
    }

    @Override
    public boolean handleCalculations() {

        if (width != 0 && length != 0 && height != 0) {
            volumen = width * length * height;
            oberflaeche = (2*width*length) + (2*width*height) + (2*length*height);

            return true;
        }

        if (width != 0 && length != 0 && volumen != 0) {
            height = volumen / (width*length);
            oberflaeche = (2*width*length) + (2*width*height) + (2*length*height);

            return true;
        }

        if (width != 0 && volumen != 0 && height != 0) {
            length = volumen / (width*height);
            oberflaeche = (2*width*length) + (2*width*height) + (2*length*height);

            return true;
        }

        if (volumen != 0 && length != 0 && height != 0) {
            width = volumen / (length*height);
            oberflaeche = (2*width*length) + (2*width*height) + (2*length*height);

            return true;
        }


        return false;
    }

    @Override
    public void resetAll() {
        width = 0;
        length = 0;
        height = 0;
        volumen = 0;
        oberflaeche = 0;
    }

    @Override
    public void updateValues(JTextArea[] textAreas) {
        width = Double.parseDouble(textAreas[0].getText());
        length = Double.parseDouble(textAreas[1].getText());
        height = Double.parseDouble(textAreas[2].getText());
        volumen = Double.parseDouble(textAreas[3].getText());
        oberflaeche = Double.parseDouble(textAreas[4].getText());
    }

    @Override
    public String errorMsg() {
        return "Mindestens 2 Seiten mit Volumen benötigt";
    }

    @Override
    public String toString() {
        return "{Breite: " + width + ", Länge: " + length + ", Höhe: " + height + ", Volumen: " + volumen + ", Oberfläche: " + oberflaeche + "}";
    }
}
