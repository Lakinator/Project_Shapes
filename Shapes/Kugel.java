package Shapes;

import javax.swing.*;

/**
 * 22.02.2017
 * Created by user Schalk (Lukas Schalk).
 */

public class Kugel implements Shape {
    private final ShapeType type = ShapeType._3D;
    private double radius = 0, durchmesser = 0, volumen = 0, oberflaeche = 0;

    @Override
    public ShapeType getType() {
        return type;
    }

    @Override
    public String[] getVarNames() {
        return new String[]{"Radius", "Durchmesser", "Volumen", "Oberfläche"};
    }

    @Override
    public double[] getVariables() {
        return new double[]{radius, durchmesser, volumen, oberflaeche};
    }

    @Override
    public boolean handleCalculations() {

        if(radius != 0) {
            durchmesser = radius*2;
            volumen = ((4/3) * Math.PI) * (radius*radius*radius);
            oberflaeche = (4 * Math.PI) * (radius*radius);
            return true;
        }

        if(durchmesser != 0) {
            radius = durchmesser / 2;
            volumen = ((4/3) * Math.PI) * (radius*radius*radius);
            oberflaeche = (4 * Math.PI) * (radius*radius);
            return true;
        }

        if(volumen != 0) {
            radius = Math.pow(volumen / ((4/3) * Math.PI) , 1.0/3.0);
            durchmesser = radius*2;
            oberflaeche = (4 * Math.PI) * (radius*radius);
            return true;
        }

        if(oberflaeche != 0) {
            radius = Math.sqrt(oberflaeche / (4 * Math.PI));
            durchmesser = radius*2;
            volumen = ((4/3) * Math.PI) * (radius*radius*radius);
            return true;
        }


        return false;
    }



    public void updateValues(final JTextArea[] textAreas) {
        radius = Double.parseDouble(textAreas[0].getText());
        durchmesser = Double.parseDouble(textAreas[1].getText());
        volumen = Double.parseDouble(textAreas[2].getText());
        oberflaeche = Double.parseDouble(textAreas[3].getText());
    }

    @Override
    public String errorMsg() {
        return "Mindestens ein Parameter benötigt";
    }

    public void resetAll() {
        radius = 0;
        durchmesser = 0;
        volumen = 0;
        oberflaeche = 0;
    }
}
