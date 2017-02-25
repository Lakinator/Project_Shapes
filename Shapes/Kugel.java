package Shapes;

import javax.swing.*;

/**
 * 22.02.2017
 * Created by user Schalk (Lukas Schalk).
 */

public class Kugel implements Shape {
    private final ShapeType type = ShapeType._3D;
    private final static int varLength = 4;
    private double radius = 0, durchmesser = 0, volumen = 0, oberflaeche = 0;

    @Override
    public ShapeType getType() {
        return type;
    }

    @Override
    public int getVarLength() {
        return varLength;
    }

    @Override
    public String[] getVarNames() {
        return new String[]{"Radius", "Durchmesser", "Volumen", "Oberfläche"};
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
        setRadius( Double.parseDouble(textAreas[0].getText()) );
        setDurchmesser( Double.parseDouble(textAreas[1].getText()) );
        setVolumen( Double.parseDouble(textAreas[2].getText()) );
        setOberflaeche( Double.parseDouble(textAreas[3].getText()) );
    }

    public void resetAll() {
        setRadius(0);
        setDurchmesser(0);
        setVolumen(0);
        setOberflaeche(0);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getDurchmesser() {
        return durchmesser;
    }

    public void setDurchmesser(double durchmesser) {
        this.durchmesser = durchmesser;
    }

    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    public double getOberflaeche() {
        return oberflaeche;
    }

    public void setOberflaeche(double oberflaeche) {
        this.oberflaeche = oberflaeche;
    }
}
